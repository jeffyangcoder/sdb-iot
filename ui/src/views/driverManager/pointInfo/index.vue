<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="驱动名称" prop="driverId">
                <a-select v-model="queryParam.driverId">
                  <a-select-option v-for="(d,index) in driverList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="显示名称" prop="displayValue">
                <a-input v-model="queryParam.displayValue" placeholder="请输入显示名称" allow-clear/>
              </a-form-item>
            </a-col>
            <template v-if="advanced">
              <a-col :md="8" :sm="24">
                <a-form-item label="属性" prop="value">
                  <a-input v-model="queryParam.value" placeholder="请输入属性" allow-clear/>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="类型" prop="type">
                  <a-select placeholder="请选择类型" v-model="queryParam.type" style="width: 100%" allow-clear>
                    <a-select-option v-for="(d, index) in typeOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="默认值" prop="def">
                  <a-input v-model="queryParam.def" placeholder="请输入默认值" allow-clear/>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="备注" prop="description">
                  <a-input v-model="queryParam.description" placeholder="请输入备注" allow-clear/>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="创建日期" prop="createTime">
                  <a-date-picker style="width: 100%" v-model="queryParam.createTime" format="YYYY-MM-DD HH:mm:ss" allow-clear/>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="修改日期" prop="modifyTime">
                  <a-date-picker style="width: 100%" v-model="queryParam.modifyTime" format="YYYY-MM-DD HH:mm:ss" allow-clear/>
                </a-form-item>
              </a-col>
            </template>
            <a-col :md="!advanced && 8 || 24" :sm="24">
              <span class="table-page-search-submitButtons" :style="advanced && { float: 'right', overflow: 'hidden' } || {} ">
                <a-button type="primary" @click="handleQuery"><a-icon type="search" />查询</a-button>
                <a-button style="margin-left: 8px" @click="resetQuery"><a-icon type="redo" />重置</a-button>
                <a @click="toggleAdvanced" style="margin-left: 8px">
                  {{ advanced ? '收起' : '展开' }}
                  <a-icon :type="advanced ? 'up' : 'down'"/>
                </a>
              </span>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <!-- 操作 -->
      <div class="table-operations">
<!--        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['driverManager:pointInfo:remove']">-->
<!--          <a-icon type="delete" />删除-->
<!--        </a-button>-->
        <a-button type="primary" @click="handleExport" v-hasPermi="['driverManager:pointInfo:export']">
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
        <span slot="type" slot-scope="text, record">
          {{ typeFormat(record) }}
        </span>
        <span slot="createTime" slot-scope="text, record" >
          {{ parseTime(record.createTime) }}
        </span>
        <span slot="modifyTime" slot-scope="text, record">
          {{ parseTime(record.modifyTime) }}
        </span>
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['driverManager:pointInfo:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['driverManager:pointInfo:remove']">
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
import { listPointInfo, delPointInfo, exportPointInfo } from '@/api/driverManager/pointInfo'
import { listDriver } from '@/api/driverManager/driver'

export default {
  name: 'PointInfo',
  components: {
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      // driver
      driverList: null,
      // 高级搜索 展开/关闭
      advanced: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      ids: [],
      loading: false,
      total: 0,
      // 类型字典
      typeOptions: [],
      // 查询参数
      queryParam: {
        driverId: null,
        displayValue: null,
        value: null,
        type: null,
        def: null,
        description: null,
        createTime: null,
        modifyTime: null,
        pageNum: 1,
        pageSize: 10
      },
      columns: [
        // {
        //   title: 'id',
        //   dataIndex: 'id',
        //   ellipsis: true,
        //   align: 'center'
        // },
        {
          title: '驱动',
          dataIndex: 'driverId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '显示名称',
          dataIndex: 'displayValue',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '属性',
          dataIndex: 'value',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '类型',
          dataIndex: 'type',
          scopedSlots: { customRender: 'type' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '默认值',
          dataIndex: 'def',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '备注',
          dataIndex: 'description',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '创建日期',
          dataIndex: 'createTime',
          scopedSlots: { customRender: 'createTime' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '修改日期',
          dataIndex: 'modifyTime',
          scopedSlots: { customRender: 'modifyTime' },
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
    this.getDicts('sdb_iot_attribute_type').then(response => {
      this.typeOptions = response.data
    })
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询位号属性列表 */
    getList () {
      this.loading = true
      listPointInfo(this.queryParam).then(response => {
        this.list = response.rows
        this.total = response.total
        listDriver(this.query).then(response => {
          this.driverList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.driverList.length; j++) {
              if (this.list[i].driverId === this.driverList[j].id) {
                this.list[i].driverId = this.driverList[j].name
              }
            }
          }
        })
        this.loading = false
      })
    },
    // 类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type)
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        driverId: undefined,
        displayValue: undefined,
        value: undefined,
        type: undefined,
        def: undefined,
        description: undefined,
        createTime: undefined,
        modifyTime: undefined,
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
          return delPointInfo(ids)
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
          return exportPointInfo(that.queryParam)
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
