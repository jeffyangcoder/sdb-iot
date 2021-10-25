<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="设备名称" prop="name">
                <a-input v-model="queryParam.name" placeholder="请输入设备名称" allow-clear/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="模板名称" prop="profileId">
                <a-select v-model="queryParam.profileId" palaceholder="请选择模板" allow-clear>
                  <a-select-option v-for="(d,index) in profileList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
            <template v-if="advanced">
              <a-col :md="8" :sm="24">
                <a-form-item label="所属分组名称" prop="groupId">
                  <a-input v-model="queryParam.groupId" placeholder="请输入所属分组id" allow-clear/>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="在线状态" prop="enable">
                  <a-select placeholder="请选择在线状态" v-model="queryParam.enable" style="width: 100%" allow-clear>
                    <a-select-option v-for="(d, index) in enableOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
                  </a-select>
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
        <a-button type="primary" @click="$refs.createForm.handleAdd()" v-hasPermi="['deviceManager:device:add']">
          <a-icon type="plus" />新增
        </a-button>
        <a-button type="primary" :disabled="single" @click="$refs.createForm.handleUpdate(undefined, ids)" v-hasPermi="['deviceManager:device:edit']">
          <a-icon type="edit" />修改
        </a-button>
        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['deviceManager:device:remove']">
          <a-icon type="delete" />删除
        </a-button>
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
      <!-- 增加修改 -->
      <create-form
        ref="createForm"
        :enableOptions="enableOptions"
        :mulitOptions="mulitOptions"
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
        <span slot="enable" slot-scope="text, record">
          {{ enableFormat(record) }}
        </span>
        <span slot="mulit" slot-scope="text, record">
          {{ mulitFormat(record) }}
        </span>
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['deviceManager:device:edit']" />
          <a @click="$refs.createForm.handleUpdate(record, undefined)" v-hasPermi="['deviceManager:device:edit']">
            <a-icon type="edit" />修改
          </a>
          <a-divider type="vertical" v-hasPermi="['deviceManager:device:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['deviceManager:device:remove']">
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
import { listDevice, delDevice, exportDevice } from '@/api/deviceManager/device'
import { listProfile } from '@/api/profileManager/profile'
import { listGroup } from '@/api/groupManager/group'
import CreateForm from './modules/CreateForm'

export default {
  name: 'Device',
  components: {
    CreateForm
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      profileList: null,
      groupList: null,
      // 高级搜索 展开/关闭
      advanced: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      ids: [],
      loading: false,
      total: 0,
      // 在线状态字典
      enableOptions: [],
      // 储存类型字典
      mulitOptions: [],
      // 查询参数
      queryProfile: {
        profileId: null,
        profileName: null
      },
      queryGroup: {

      },
      queryParam: {
        name: null,
        profileId: null,
        groupId: null,
        enable: null,
        pageNum: 1,
        pageSize: 10
      },
      columns: [
        {
          title: '设备名称',
          dataIndex: 'name',
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
          title: '分组名称',
          dataIndex: 'groupId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '在线状态',
          dataIndex: 'enable',
          scopedSlots: { customRender: 'enable' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '储存类型',
          dataIndex: 'mulit',
          scopedSlots: { customRender: 'mulit' },
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
    this.getDicts('sdb_iot_driver_enable').then(response => {
      this.enableOptions = response.data
    })
    this.getDicts('sdb_iot_device_mulit').then(response => {
      this.mulitOptions = response.data
    })
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询设备列表 */
    getList () {
      this.loading = true
      listDevice(this.queryParam).then(response => {
        this.list = response.rows
        listProfile(this.queryProfile).then(response => {
          this.profileList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.profileList.length; j++) {
              if (this.list[i].profileId === this.profileList[j].id) {
                this.list[i].profileId = this.profileList[j].name
              }
            }
          }
        })
        listGroup(this.queryGroup).then(response => {
          this.groupList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.groupList.length; j++) {
              if (this.list[i].groupId === this.groupList[j].id) {
                this.list[i].groupId = this.groupList[j].name
              }
            }
          }
        })
        this.total = response.total
        listProfile(this.queryProfile).then(response => {
          this.profileList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.profileList.length; j++) {
              if (this.list[i].profileId === this.profileList[j].id) {
                this.list[i].profileId = this.profileList[j].name
              }
            }
          }
        })
        listGroup(this.queryGroup).then(response => {
          this.groupList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.groupList.length; j++) {
              if (this.list[i].groupId === this.groupList[j].id) {
                this.list[i].groupId = this.groupList[j].name
              }
            }
          }
        })
        this.loading = false
      })
    },
    // 在线状态字典翻译
    enableFormat (row, column) {
      return this.selectDictLabel(this.enableOptions, row.enable)
    },
    // 储存类型字典翻译
    mulitFormat (row, column) {
      return this.selectDictLabel(this.mulitOptions, row.mulit)
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
        profileId: undefined,
        groupId: undefined,
        enable: undefined,
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
          return delDevice(ids)
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
          return exportDevice(that.queryParam)
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
