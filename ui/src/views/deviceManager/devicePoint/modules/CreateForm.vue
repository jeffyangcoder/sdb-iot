<template>
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>{{ formTitle }}</b>
    </a-divider>
    <a-form-model ref="form" :model="form" :rules="rules">
      <a-form-model-item label="设备id" prop="deviceId" >
        <a-input v-model="form.deviceId" placeholder="请输入设备id" />
      </a-form-model-item>
      <a-form-model-item label="模板id" prop="pointId" >
        <a-input v-model="form.pointId" placeholder="请输入模板id" />
      </a-form-model-item>
      <a-form-model-item label="位号属性id" prop="pointInfoId" >
        <a-input v-model="form.pointInfoId" placeholder="请输入位号属性id" />
      </a-form-model-item>
      <a-form-model-item label="内容" prop="value" >
        <a-input v-model="form.value" placeholder="请输入内容" />
      </a-form-model-item>
      <a-form-model-item label="备注" prop="description" >
        <a-input v-model="form.description" placeholder="请输入备注" />
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
import { getDevicePoint, addDevicePoint, updateDevicePoint } from '@/api/deviceManager/devicePoint'

export default {
  name: 'CreateForm',
  props: {
  },
  components: {
  },
  data () {
    return {
      loading: false,
      formTitle: '',
      // 表单参数
      form: {
        deviceId: null,
        pointId: null,
        pointInfoId: null,
        value: null,
        description: null,
        createTime: null,
        updateTime: null
      },
      // 1增加,2修改
      formType: 1,
      open: false,
      rules: {
        deviceId: [
          { required: true, message: '设备id不能为空', trigger: 'blur' }
        ],
        pointId: [
          { required: true, message: '模板id不能为空', trigger: 'blur' }
        ],
        pointInfoId: [
          { required: true, message: '位号属性id不能为空', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '内容不能为空', trigger: 'blur' }
        ]
      }
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
  mounted () {
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
      this.formType = 1
      this.form = {
        deviceId: null,
        pointId: null,
        pointInfoId: null,
        value: null,
        description: null,
        createTime: null,
        updateTime: null
      }
    },
    /** 新增按钮操作 */
    handleAdd (row) {
      this.reset()
      this.formType = 1
      this.open = true
      this.formTitle = '添加'
    },
    /** 修改按钮操作 */
    handleUpdate (row, ids) {
      this.reset()
      this.formType = 2
      const id = row ? row.id : ids
      getDevicePoint(id).then(response => {
        this.form = response.data
        this.open = true
        this.formTitle = '修改'
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.id !== undefined && this.form.id !== null) {
            updateDevicePoint(this.form).then(response => {
              this.$message.success(
                '修改成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          } else {
            addDevicePoint(this.form).then(response => {
              this.$message.success(
                '新增成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          }
        } else {
          return false
        }
      })
    }
  }
}
</script>
