<template>
  <page-header-wrapper>
    <a-card :bordered="false">
      <!-- 条件搜索 -->
      <div class="table-page-search-wrapper">
        <a-form layout="inline">
          <a-row :gutter="48">
            <a-col :md="8" :sm="24">
              <a-form-item label="模板名称" prop="profileId">
                <a-input v-model="queryParam.profileId" placeholder="请输入模板名称" allow-clear/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24">
              <a-form-item label="位号值" prop="value">
                <a-input v-model="queryParam.value" placeholder="请输入位号值" allow-clear/>
              </a-form-item>
            </a-col>
            <template v-if="advanced">
              <a-col :md="8" :sm="24">
                <a-form-item label="类型" prop="type">
                  <a-select placeholder="请选择类型" v-model="queryParam.type" style="width: 100%" allow-clear>
                    <a-select-option v-for="(d, index) in typeOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="读/写" prop="rw">
                  <a-select placeholder="请选择读/写" v-model="queryParam.rw" style="width: 100%" allow-clear>
                    <a-select-option v-for="(d, index) in rwOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :md="8" :sm="24">
                <a-form-item label="累计标识" prop="accrue">
                  <a-select placeholder="请选择累计标识" v-model="queryParam.accrue" style="width: 100%" allow-clear>
                    <a-select-option v-for="(d, index) in accrueOptions" :key="index" :value="d.dictValue">{{ d.dictLabel }}</a-select-option>
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
        <a-button type="primary" @click="$refs.createForm.handleAdd()" v-hasPermi="['profileManager:point:add']">
          <a-icon type="plus" />新增
        </a-button>
        <a-button type="primary" :disabled="single" @click="$refs.createForm.handleUpdate(undefined, ids)" v-hasPermi="['profileManager:point:edit']">
          <a-icon type="edit" />修改
        </a-button>
        <a-button type="danger" :disabled="multiple" @click="handleDelete" v-hasPermi="['profileManager:point:remove']">
          <a-icon type="delete" />删除
        </a-button>
        <a-button type="primary" @click="handleExport" v-hasPermi="['profileManager:point:export']">
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
        :typeOptions="typeOptions"
        :rwOptions="rwOptions"
        :accrueOptions="accrueOptions"
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
        <span slot="type" slot-scope="text, record">
          {{ typeFormat(record) }}
        </span>
        <span slot="rw" slot-scope="text, record">
          {{ rwFormat(record) }}
        </span>
        <span slot="accrue" slot-scope="text, record">
          {{ accrueFormat(record) }}
        </span>
        <span slot="modifyTime" slot-scope="text, record">
          {{ parseTime(record.modifyTime) }}
        </span>
        <span slot="createTime" slot-scope="text, record">
          {{ parseTime(record.createTime) }}
        </span>
        <span slot="operation" slot-scope="text, record">
          <a-divider type="vertical" v-hasPermi="['profileManager:point:edit']" />
          <a @click="$refs.createForm.handleUpdate(record, undefined)" v-hasPermi="['profileManager:point:edit']">
            <a-icon type="edit" />修改
          </a>
          <a-divider type="vertical" v-hasPermi="['profileManager:point:remove']" />
          <a @click="handleDelete(record)" v-hasPermi="['profileManager:point:remove']">
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
import { listPoint, delPoint, exportPoint } from '@/api/profileManager/point'
import CreateForm from './modules/CreateForm'
import { listProfile } from '@/api/profileManager/profile'

export default {
  name: 'Point',
  components: {
    CreateForm
  },
  data () {
    return {
      list: [],
      selectedRowKeys: [],
      selectedRows: [],
      profileList: null,
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
      // 读/写字典
      rwOptions: [],
      // 累计标识字典
      accrueOptions: [],
      // 查询参数
      query: {
        profileId: null,
        profileName: null
      },
      queryParam: {
        profileId: null,
        value: null,
        type: null,
        rw: null,
        accrue: null,
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
          title: '模板名称',
          dataIndex: 'profileId',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '位号值',
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
          title: '读/写',
          dataIndex: 'rw',
          scopedSlots: { customRender: 'rw' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '基础值',
          dataIndex: 'base',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '最小值',
          dataIndex: 'minimum',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '最大值',
          dataIndex: 'maximum',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '倍数',
          dataIndex: 'multiple',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '累计标识',
          dataIndex: 'accrue',
          scopedSlots: { customRender: 'accrue' },
          ellipsis: true,
          align: 'center'
        },
        {
          title: '数据格式',
          dataIndex: 'format',
          ellipsis: true,
          align: 'center'
        },
        {
          title: '单位',
          dataIndex: 'unit',
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
          title: '创建时间',
          dataIndex: 'createTime',
          scopedSlots: { customRender: 'createTime' },
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
    this.getName()
    this.getDicts('sdb_iot_attribute_type').then(response => {
      this.typeOptions = response.data
    })
    this.getDicts('sdb_iot_point_rw').then(response => {
      this.rwOptions = response.data
    })
    this.getDicts('sdb_iot_point_accrue').then(response => {
      this.accrueOptions = response.data
    })
  },
  computed: {
  },
  watch: {
  },
  methods: {
    /** 查询位号列表 */
    getList () {
      this.loading = true
      listPoint(this.queryParam).then(response => {
        this.list = response.rows
        listProfile(this.query).then(response => {
          this.profileList = response.rows
          for (let i = 0; i < this.list.length; i++) {
            for (let j = 0; j < this.profileList.length; j++) {
              if (this.list[i].profileId === this.profileList[j].id) {
                this.list[i].profileId = this.profileList[j].name
              }
            }
          }
        })
        this.total = response.total
        this.loading = false
      })
    },
    getName () {
      console.log(this.total)
    },
    // 类型字典翻译
    typeFormat (row, column) {
      return this.selectDictLabel(this.typeOptions, row.type)
    },
    // 读/写字典翻译
    rwFormat (row, column) {
      return this.selectDictLabel(this.rwOptions, row.rw)
    },
    // 累计标识字典翻译
    accrueFormat (row, column) {
      return this.selectDictLabel(this.accrueOptions, row.accrue)
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParam.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.queryParam = {
        profileId: undefined,
        value: undefined,
        type: undefined,
        rw: undefined,
        accrue: undefined,
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
          return delPoint(ids)
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
          return exportPoint(that.queryParam)
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
