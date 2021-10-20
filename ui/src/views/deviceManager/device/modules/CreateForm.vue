<template>
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>{{ formTitle }}</b>
    </a-divider>
    <a-form-model ref="form" :model="form" :rules="rules">
      <a-form-model-item label="设备名称" prop="name" >
        <a-input v-model="form.name" placeholder="请输入设备名称" />
      </a-form-model-item>
      <a-form-model-item label="模板id" prop="profileId" >
        <a-input v-model="form.profileId" placeholder="请输入模板id" />
      </a-form-model-item>
      <a-form-model-item label="所属分组id" prop="groupId" >
        <a-input v-model="form.groupId" placeholder="请输入所属分组id" />
      </a-form-model-item>
      <a-form-model-item label="在线状态" prop="enable" >
        <a-select placeholder="请选择在线状态" v-model="form.enable">
          <a-select-option v-for="(d, index) in enableOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="储存类型" prop="mulit" >
        <a-select placeholder="请选择储存类型" v-model="form.mulit">
          <a-select-option v-for="(d, index) in mulitOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
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
import { getDevice, addDevice, updateDevice } from '@/api/deviceManager/device'

export default {
  name: 'CreateForm',
  props: {
    enableOptions: {
      type: Array,
      required: true
    },
    mulitOptions: {
      type: Array,
      required: true
    }
  },
  components: {
  },
  data () {
    return {
      loading: false,
      formTitle: '',
      // 表单参数
      form: {
        name: null,
        profileId: null,
        groupId: null,
        enable: null,
        mulit: null,
        description: null,
        createTime: null,
        updateTime: null
      },
      // 1增加,2修改
      formType: 1,
      open: false,
      rules: {
        profileId: [
          { required: true, message: '模板id不能为空', trigger: 'blur' }
        ],
        groupId: [
          { required: true, message: '所属分组id不能为空', trigger: 'blur' }
        ],
        enable: [
          { required: true, message: '在线状态不能为空', trigger: 'change' }
        ],
        mulit: [
          { required: true, message: '储存类型不能为空', trigger: 'change' }
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
        name: null,
        profileId: null,
        groupId: null,
        enable: null,
        mulit: null,
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
      getDevice(id).then(response => {
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
            updateDevice(this.form).then(response => {
              this.$message.success(
                '修改成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          } else {
            addDevice(this.form).then(response => {
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
