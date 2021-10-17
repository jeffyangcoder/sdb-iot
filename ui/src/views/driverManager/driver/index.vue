<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="驱动名称" prop="name">
                <a-input v-model="queryParam.name" placeholder="请输入驱动名称" allow-clear/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="驱动服务" prop="server">
                <a-input v-model="queryParam.server" placeholder="请输入驱动服务" allow-clear/>
              </a-form-item>
            </a-col>
            <a-col :md="!advanced && 8 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="handleQuery"><a-icon type="search" />查询</a-button>
                <a-button style="margin-left: 8px" @click="resetQuery"><a-icon type="redo" />重置</a-button>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <!-- 操作 -->
      <div class="table-operations">
        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['driverManager:driver:remove']">
          <a-icon type="delete" />删除
        </a-button>
        <a-button type="primary" @click="handleExport" v-hasPermi="['driverManager:driver:export']">
          <a-icon type="download" />导出
        </a-button>
        <a-button
          type="dashed"
          shape="circle"
          :loading="loading"
          :style="{float: 'right'}"
          icon="reload"
          @click="getList" />
      </div>
      <!-- 数据展示 -->
      <a-table
        :loading="loading"
        :size="tableSize"
        rowKey="id"
        :columns="columns"
        :data-source="list"
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :pagination="false">
        <span slot="enable" slot-scope="text, record">
          {{ enableFormat(record) }}
        </span>
        <span slot="modifyDate" slot-scope="text, record">
          {{ parseTime(record.modifyDate) }}
        </span>
        <span slot="createDate" slot-scope="text, record">
          {{ parseTime(record.createDate) }}
        </span>
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['driverManager:driver:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['driverManager:driver:remove']">
            <a-icon type="delete" />删除
          </a>
        </span>
      </a-table>
      <!-- 分页 -->
      <a-pagination
        class="ant-table-pagination"
        show-size-changer
        show-quick-jumper
        :current="queryParam.pageNum"
        :total="total"
        :page-size="queryParam.pageSize"
        :showTotal="total => `共 ${total} 条`"
        @showSizeChange="onShowSizeChange"
        @change="changeSize"
      />
    </a-card>
  </page-header-wrapper>
</template>

<script>
import { listDriver, delDriver, exportDriver } from '@/api/driverManager/driver'
// import CreateForm from './modules/CreateForm'

export default {
  name: 'Driver',
  components: {
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      // 高级搜索 展开/关闭
      advanced: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      ids: [],
      loading: false,
      total: 0,
      // 驱动状态字典
      enableOptions: [],
      // 查询参数
      queryParam: {
        name: null,
        server: null,
        pageNum: 1,
        pageSize: 10
      },
      columns: [
        // {
        //   title: '驱动id',
        //   dataIndex: 'id',
        //   ellipsis: true,
        //   align: 'center'
        // },
        {
          title: '驱动名称',
          dataIndex: 'name',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '驱动服务',
          dataIndex: 'server',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '驱动状态',
          dataIndex: 'enable',
          scopedSlots: { customRender: 'enable' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '驱动主机',
          dataIndex: 'host',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '驱动端口',
          dataIndex: 'port',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '备注',
          dataIndex: 'remark',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '修改日期',
          dataIndex: 'modifyDate',
          scopedSlots: { customRender: 'modifyDate' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '创建日期',
          dataIndex: 'createDate',
          scopedSlots: { customRender: 'createDate' },
          ellipsis: true,
          align: 'center'
        }
        // {
        //   title: '操作',
        //   dataIndex: 'operation',
        //   width: '18%',
        //   scopedSlots: { customRender: 'operation' },
        //   align: 'center'
        // }
      ]
    }
  },
  filters: {
  },
  created () {
    this.getList()
    this.getDicts('sdb_iot_driver_enable').then(response => {
      this.enableOptions = response.data
    })
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询驱动状态列表 */
    getList () {
      this.loading = true
      listDriver(this.queryParam).then(response => {
        this.list = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 驱动状态字典翻译
    enableFormat (row, column) {
      return this.selectDictLabel(this.enableOptions, row.enable)
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        name: undefined,
        server: undefined,
        pageNum: 1,
        pageSize: 10
      }
      this.handleQuery()
    },
    onShowSizeChange (current, pageSize) {
      this.queryParam.pageSize = pageSize
      this.getList()
    },
    changeSize (current, pageSize) {
      this.queryParam.pageNum = current
      this.queryParam.pageSize = pageSize
      this.getList()
    },
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
      this.ids = this.selectedRows.map(item => item.id)
      this.single = selectedRowKeys.length !== 1
      this.multiple = !selectedRowKeys.length
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const ids = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        content: '当前选中编号为' + ids + '的数据',
        onOk () {
          return delDriver(ids)
            .then(() => {
              that.onSelectChange([], [])
              that.getList()
              that.$message.success(
                '删除成功',
                3
              )
          })
        },
        onCancel () {}
      })
    },
    /** 导出按钮操作 */
    handleExport () {
      var that = this
      this.$confirm({
        title: '是否确认导出?',
        content: '此操作将导出当前条件下所有数据而非选中数据',
        onOk () {
          return exportDriver(that.queryParam)
            .then(response => {
              that.download(response.msg)
              that.$message.success(
                '导出成功',
                3
              )
          })
        },
        onCancel () {}
      })
    }
  }
}
</script>
