<template>
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>{{ formTitle }}</b>
    </a-divider>
    <a-form-model ref="form" :model="form" :rules="rules">
      <a-form-model-item label="模板名称" prop="name" >
        <a-input v-model="form.name" placeholder="请输入模板名称" />
      </a-form-model-item>
      <a-form-model-item label="公/私有" prop="share" >
        <a-select placeholder="请选择公/私有" v-model="form.share">
          <a-select-option v-for="(d, index) in shareOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="驱动id" prop="driverId" >
        <a-input v-model="form.driverId" placeholder="请输入驱动id" />
      </a-form-model-item>
      <a-form-model-item label="备注" prop="description" >
        <a-input v-model="form.description" placeholder="请输入备注" />
      </a-form-model-item>
      <a-form-model-item label="修改时间" prop="modifyTime" >
        <a-date-picker style="width: 100%" v-model="form.modifyTime" format="YYYY-MM-DD HH:mm:ss" allow-clear/>
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
import { getProfile, addProfile, updateProfile } from '@/api/profileManager/profile'

export default {
  name: 'CreateForm',
  props: {
    shareOptions: {
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
        share: null,
        driverId: null,
        description: null,
        createTime: null,
        modifyTime: null
      },
      // 1增加,2修改
      formType: 1,
      open: false,
      rules: {
        name: [
          { required: true, message: '模板名称不能为空', trigger: 'blur' }
        ],
        share: [
          { required: true, message: '公/私有不能为空', trigger: 'change' }
        ],
        driverId: [
          { required: true, message: '驱动id不能为空', trigger: 'blur' }
        ],
        modifyTime: [
          { required: true, message: '修改时间不能为空', trigger: 'blur' }
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
        share: null,
        driverId: null,
        description: null,
        createTime: null,
        modifyTime: null
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
      getProfile(id).then(response => {
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
            updateProfile(this.form).then(response => {
              this.$message.success(
                '修改成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          } else {
            addProfile(this.form).then(response => {
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
