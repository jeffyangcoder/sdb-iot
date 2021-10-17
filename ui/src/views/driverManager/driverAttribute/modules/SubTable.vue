<template>
  <a-modal
    ref="subTableModel"
    :title="'驱动状态'"
    width="80%"
    :visible="visible"
    @cancel="close"
  >
    <template slot="footer">
      <a-button type="dashed" @click="close">
        关闭
      </a-button>
    </template>
    <a-card :bordered="false">
      <!-- 操作 -->
      <div class="table-operations">
        <a-button type="primary" @click="$refs.createSubForm.handleAdd()">
          <a-icon type="plus" />新增
        </a-button>
        <a-button type="danger" :disabled="multiple" @click="handleDelete">
          <a-icon type="delete" />删除
        </a-button>
      </div>
      <!-- 增加修改 -->
      <create-sub-form
        ref="createSubForm"
        @add="handleAddsdbIotDriver"
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
          <a-divider type="vertical" />
          <a @click="handleDelete(record)">
            <a-icon type="delete" />删除
          </a>
        </span>
      </a-table>
    </a-card>
  </a-modal>
</template>

<script>
import CreateSubForm from './CreateSubForm'

export default {
  name: 'SubTable',
  components: {
    CreateSubForm
  },
  props: {
    list: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      visible: false,
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
      columns: [
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
          ellipsis: true,
          align: 'center'
        },
        {
          title: '创建日期',
          dataIndex: 'createDate',
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
  },
  computed: {
  },
  watch: {
  },
  methods: {
    // 关闭模态框
    close () {
      this.visible = false
      this.selectedRowKeys = []
      this.selectedRows = []
    },
    // 打开抽屉(由外面的组件调用)
    show () {
      this.visible = true
    },
    onSelectChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
      this.ids = selectedRowKeys.map(item => item - 1)
      this.single = selectedRowKeys.length !== 1
      this.multiple = !selectedRowKeys.length
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      var that = this
      if (row) {
        var index = (this.list || []).findIndex((item) => item === row)
      }
      const ids = index !== undefined ? index : this.ids
      this.$confirm({
        title: '确认删除所选中数据?',
        content: '当前选中序号为' + ids + '的数据',
        onOk () {
          that.$emit('delete', index, ids)
        },
        onCancel () {}
      })
    },
    handleAddsdbIotDriver (row) {
      this.$emit('add', row)
    }
  }
}
</script>
