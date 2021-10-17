<template>
  <!-- 增加修改 -->
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>驱动状态</b>
    </a-divider>
    <a-form-model ref="form" :model="form">
      <a-form-model-item label="驱动名称" prop="name">
        <a-input v-model="form.name" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="驱动服务" prop="server">
        <a-input v-model="form.server" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="驱动状态" prop="enable">
        <a-input v-model="form.enable" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="驱动主机" prop="host">
        <a-input v-model="form.host" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="驱动端口" prop="port">
        <a-input v-model="form.port" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="备注" prop="remark">
        <a-input v-model="form.remark" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="修改日期" prop="modifyDate">
        <a-input v-model="form.modifyDate" placeholder="请输入" />
      </a-form-model-item>
      <a-form-model-item label="创建日期" prop="createDate">
        <a-input v-model="form.createDate" placeholder="请输入" />
      </a-form-model-item>
      <div class="bottom-control">
        <a-space>
          <a-button type="primary" @click="submitForm">
            保存
          </a-button>
          <a-button type="dashed" @click="cancel">
            取消
          </a-button>
        </a-space>
      </div>
    </a-form-model>
  </a-drawer>
</template>

<script>

export default {
  name: 'CreateSubForm',
  props: {
  },
  components: {
  },
  data () {
    return {
      formTitle: '增加',
      // 表单参数
      form: {
        name: undefined,
        server: undefined,
        enable: undefined,
        host: undefined,
        port: undefined,
        remark: undefined,
        modifyDate: undefined,
        createDate: undefined
      },
      open: false
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
    onClose () {
      this.open = false
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        name: undefined,
        server: undefined,
        enable: undefined,
        host: undefined,
        port: undefined,
        remark: undefined,
        modifyDate: undefined,
        createDate: undefined
      }
    },
     /** 新增按钮操作 */
    handleAdd () {
      this.open = true
      this.reset()
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$emit('add', this.form)
      this.open = false
    }
  }
}
</script>
