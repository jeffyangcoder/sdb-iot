<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="模板" prop="driverId">
                <a-select v-model="queryParam.profileId" >
                  <a-select-option v-for="(d,index) in profileList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="配置名称" prop="driverAttributeId">
                <a-select v-model="queryParam.driverAttributeId" >
                  <a-select-option v-for="(d,index) in driverAttributeList" :key="index" :value="d.id">{{ d.driverId + ' ' + d.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <template v-if="advanced">
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
        <a-button type="primary" @click="$refs.createForm.handleAdd()" v-hasPermi="['driverManager:driverInfo:add']">
          <a-icon type="plus" />新增
        </a-button>
<!--        <a-button type="primary" :disabled="single" @click="$refs.createForm.handleUpdate(undefined, ids)" v-hasPermi="['driverManager:driverInfo:edit']">-->
<!--          <a-icon type="edit" />修改-->
<!--        </a-button>-->
        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['driverManager:driverInfo:remove']">
          <a-icon type="delete" />删除
        </a-button>
        <a-button type="primary" @click="handleExport" v-hasPermi="['driverManager:driverInfo:export']">
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
        <span slot="createTime" slot-scope="text, record" >
          {{ parseTime(record.createTime) }}
        </span>
        <span slot="modifyTime" slot-scope="text, record">
          {{ parseTime(record.modifyTime) }}
        </span>
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['driverManager:driverInfo:edit']" />
          <a @click="$refs.createForm.handleUpdate(record, undefined)" v-hasPermi="['driverManager:driverInfo:edit']">
            <a-icon type="edit" />修改
          </a>
          <a-divider type="vertical" v-hasPermi="['driverManager:driverInfo:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['driverManager:driverInfo:remove']">
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
import { listDriverInfo, delDriverInfo, exportDriverInfo } from '@/api/driverManager/driverInfo'
import { listProfile } from '@/api/profileManager/profile'
import { listDriverAttribute } from '@/api/driverManager/driverAttribute'
import { listDriver } from '@/api/driverManager/driver'
import CreateForm from './modules/CreateForm'

export default {
  name: 'DriverInfo',
  components: {
    CreateForm
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      // 模板数据
      profileList: null,
      // 驱动数据
      driverList: null,
      // 驱动配置数据
      driverAttributeList: null,
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
      query: {
        driverId: null,
        driverName: null
      },
      query1: {
        driverAttributeId: null,
        driverAttributeName: null
      },
      query2: {
        profileId: null,
        profileName: null
      },
      queryParam: {
        driverId: null,
        driverAttributeId: null,
        profileId: null,
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
          title: '配置名称',
          dataIndex: 'driverAttributeId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '模板名称',
          dataIndex: 'profileId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '内容值',
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
          title: '创建时间',
          dataIndex: 'createTime',
          scopedSlots: { customRender: 'createTime' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '修改时间',
          dataIndex: 'modifyTime',
          scopedSlots: { customRender: 'modifyTime' },
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
    /** 查询驱动配置列表 */
    getList () {
      this.loading = true
      listDriverInfo(this.queryParam).then(response => {
        this.list = response.rows
        this.total = response.total
        this.loading = false
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
        listDriverAttribute(this.query2).then(response => {
          this.driverAttributeList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.driverAttributeList.length; j++) {
              if (this.list[i].driverAttributeId === this.driverAttributeList[j].id) {
                this.list[i].driverAttributeId = this.driverAttributeList[j].name
              }
            }
          }
        })
        listProfile(this.query1).then(response => {
          this.profileList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            // console.log(i + 'i')
            for (let j = 0; j < this.profileList.length; j++) {
              if (this.list[i].profileId === this.profileList[j].id) {
                this.list[i].profileId = this.profileList[j].name
              }
            }
          }
        })
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
        driverId: undefined,
        driverAttributeId: undefined,
        profileId: undefined,
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
          return delDriverInfo(ids)
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
          return exportDriverInfo(that.queryParam)
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
