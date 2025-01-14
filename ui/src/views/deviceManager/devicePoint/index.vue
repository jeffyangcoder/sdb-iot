<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="设备名称" prop="deviceId">
                <a-select v-model="queryParam.deviceId" allow-clear>
                  <a-select-option v-for="(d, index) in this.deviceList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="位号名称" prop="pointId">
                <a-select v-model="queryParam.pointId" allow-clear>
                  <a-select-option v-for="(d, index) in this.pointList" :key="index" :value="d.id">{{ d.value }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <template v-if="advanced">
              <a-col :md="8" :sm="24">
                <a-form-item label="位号属性id" prop="pointInfoId">
                  <a-input v-model="queryParam.pointInfoId" placeholder="请输入位号属性id" allow-clear/>
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
        <a-button type="primary" @click="$refs.createForm.handleAdd()" v-hasPermi="['deviceManager:devicePoint:add']">
          <a-icon type="plus" />新增
        </a-button>
        <a-button type="primary" :disabled="single" @click="$refs.createForm.handleUpdate(undefined, ids)" v-hasPermi="['deviceManager:devicePoint:edit']">
          <a-icon type="edit" />修改
        </a-button>
        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['deviceManager:devicePoint:remove']">
          <a-icon type="delete" />删除
        </a-button>
        <a-button type="primary" @click="handleExport" v-hasPermi="['deviceManager:devicePoint:export']">
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
      <!-- 增加修改 -->
      <create-form
        ref="createForm"
        @ok="getList"
      />
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
          <a-divider type="vertical" v-hasPermi="['deviceManager:devicePoint:edit']" />
          <a @click="$refs.createForm.handleUpdate(record, undefined)" v-hasPermi="['deviceManager:devicePoint:edit']">
            <a-icon type="edit" />修改
          </a>
          <a-divider type="vertical" v-hasPermi="['deviceManager:devicePoint:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['deviceManager:devicePoint:remove']">
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
import { listDevicePoint, delDevicePoint, exportDevicePoint } from '@/api/deviceManager/devicePoint'
import { listDevice } from '@/api/deviceManager/device'
import { listPointInfo } from '@/api/driverManager/pointInfo'
import { listPoint } from '@/api/profileManager/point'
import CreateForm from './modules/CreateForm'

export default {
  name: 'DevicePoint',
  components: {
    CreateForm
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      // 设备列表
      deviceList: null,
      // 位号列表
      pointList: null,
      // 位号属性列表
      pointInfoList: null,
      // 高级搜索 展开/关闭
      advanced: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      ids: [],
      loading: false,
      total: 0,
      // 查询参数
      queryPointInfo: {
        pointInfoId: null,
        pointInfoName: null
      },
      queryDevice: {
        deviceId: null,
        deviceName: null
      },
      queryPoint: {
        pointId: null,
        pointName: null
      },
      queryParam: {
        deviceId: null,
        pointId: null,
        pointInfoId: null,
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
          title: '设备名称',
          dataIndex: 'deviceId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '位号名称',
          dataIndex: 'pointId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '位号属性',
          dataIndex: 'pointInfoId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '内容',
          dataIndex: 'value',
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
          title: '操作',
          dataIndex: 'operation',
          width: '18%',
          scopedSlots: { customRender: 'operation' },
          align: 'center'
        }
      ]
    }
  },
  filters: {
  },
  created () {
    this.getList()
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询位号配置列表 */
    getList () {
      this.loading = true
      listDevicePoint(this.queryParam).then(response => {
        this.list = response.rows
        listPoint(this.queryPoint).then(response => {
          this.pointList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.pointList.length; j++) {
              if (this.list[i].pointId === this.pointList[j].id) {
                this.list[i].pointId = this.pointList[j].value
              }
            }
          }
        })
        listDevice(this.queryDevice).then(response => {
          this.deviceList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.deviceList.length; j++) {
              if (this.list[i].deviceId === this.deviceList[j].id) {
                this.list[i].deviceId = this.deviceList[j].name
              }
            }
          }
        })
        listPointInfo(this.queryPointInfo).then(response => {
          this.pointInfoList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.pointInfoList.length; j++) {
              if (this.list[i].pointInfoId === this.pointInfoList[j].id) {
                this.list[i].pointInfoId = this.pointInfoList[j].displayValue
              }
            }
          }
        })
        this.total = response.total
      })
      this.loading = false
    },

    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        deviceId: undefined,
        pointId: undefined,
        pointInfoId: undefined,
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
          return delDevicePoint(ids)
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
          return exportDevicePoint(that.queryParam)
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
