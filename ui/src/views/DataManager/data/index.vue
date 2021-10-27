<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="设备id" prop="deviceId">
                <a-input v-model="queryParam.deviceId" palaceholder="请输入所属分组id" alllow-clear/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="分组id" prop="groupId">
                <a-input v-model="queryParam.groupId" palaceholder="请输入所属分组id" alllow-clear/>
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
      </div>
      <!-- 操作 -->
      <div class="table-operations">
        <a-button type="primary" @click="handleExport" v-hasPermi="['deviceManager:device:export']">
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
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['dataManager:data:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['dataManager:data:remove']">
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
import { listData, exportData, delData } from '@/api/dataManager/data'
import { listDevice } from '@/api/deviceManager/device'

export default {
  name: 'Data',
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      DeviceList: null,
    //  非单个禁用
      single: true,
    //  非多个禁用
      multiple: true,
      ids: [],
      loading: false,
      total: 0,
    //  查询函数
      queryDevice: {
        deviceId: null,
        deviceName: null
      },
      queryParam: {
        id: null,
        deviceId: null,
        groupId: null,
        devicePointId: null,
        originalValue: null,
        processValue: null,
        custom: null,
        acquisition: null,
        delay: null,
        saveDate: null,
        pageNum: 1,
        pageSize: 10
      },
      columns: [
        {
          title: '#',
          dataIndex: 'id',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '设备名称',
          dataIndex: 'deviceId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '分组id',
          dataIndex: 'groupId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '位号',
          dataIndex: 'devicePointId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '原始值',
          dataIndex: 'originalValue',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '处理值',
          dataIndex: 'processValue',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '自定义',
          dataIndex: 'custom',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '采集日期',
          dataIndex: 'acquisition',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '时延（毫秒）',
          dataIndex: 'delay',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '保存日期',
          dataIndex: 'saveDate',
          ellipsis: true,
          align: 'center'
        }
      ]
    }
  },
  filters: {
  },
  created () {
    this.getList()
    listDevice(this.queryDevice).then(response => {
      this.deviceList = response.rows
    })
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询数据列表 */
    getList () {
      this.loading = true
      listData(this.queryParam).then(response => {
        this.list = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        id: undefined,
        device: undefined,
        devicePoint: undefined,
        originalValue: undefined,
        processValue: undefined,
        custom: undefined,
        acquisition: undefined,
        delay: undefined,
        saveDate: undefined,
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
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      const ids = row.id || this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        content: '当前选中编号为' + ids + '的数据',
        onOk () {
          return delData(ids)
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
          return exportData(that.queryParam)
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
