<template>
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>{{ formTitle }}</b>
    </a-divider>
    <a-form-model ref="form" :model="form" :rules="rules">
      <a-form-model-item label="模板id" prop="profileId" >
        <a-input v-model="form.profileId" placeholder="请输入模板id" />
      </a-form-model-item>
      <a-form-model-item label="位号值" prop="value" >
        <a-input v-model="form.value" placeholder="请输入位号值" />
      </a-form-model-item>
      <a-form-model-item label="类型" prop="type" >
        <a-select placeholder="请选择类型" v-model="form.type">
          <a-select-option v-for="(d, index) in typeOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="读/写" prop="rw" >
        <a-select placeholder="请选择读/写" v-model="form.rw">
          <a-select-option v-for="(d, index) in rwOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="基础值" prop="base" >
        <a-input v-model="form.base" placeholder="请输入基础值" />
      </a-form-model-item>
      <a-form-model-item label="最小值" prop="minimum" >
        <a-input v-model="form.minimum" placeholder="请输入最小值" />
      </a-form-model-item>
      <a-form-model-item label="最大值" prop="maximum" >
        <a-input v-model="form.maximum" placeholder="请输入最大值" />
      </a-form-model-item>
      <a-form-model-item label="倍数" prop="multiple" >
        <a-input v-model="form.multiple" placeholder="请输入倍数" />
      </a-form-model-item>
      <a-form-model-item label="累计标识" prop="accrue" >
        <a-select placeholder="请选择累计标识" v-model="form.accrue">
          <a-select-option v-for="(d, index) in accrueOptions" :key="index" :value="d.dictValue" >{{ d.dictLabel }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="数据格式" prop="format" >
        <a-input v-model="form.format" placeholder="请输入数据格式" />
      </a-form-model-item>
      <a-form-model-item label="单位" prop="unit" >
        <a-input v-model="form.unit" placeholder="请输入单位" />
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
import { getPoint, addPoint, updatePoint } from '@/api/profileManager/point'

export default {
  name: 'CreateForm',
  props: {
    typeOptions: {
      type: Array,
      required: true
    },
    rwOptions: {
      type: Array,
      required: true
    },
    accrueOptions: {
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
        id: null,
        profileId: null,
        value: null,
        type: null,
        rw: null,
        base: null,
        minimum: null,
        maximum: null,
        multiple: null,
        accrue: null,
        format: null,
        unit: null,
        modifyTime: null,
        createTime: null
      },
      // 1增加,2修改
      formType: 1,
      open: false,
      rules: {
        profileId: [
          { required: true, message: '模板id不能为空', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '位号值不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '类型不能为空', trigger: 'change' }
        ],
        rw: [
          { required: true, message: '读写不能为空', trigger: 'change' }
        ],
        multiple: [
          { required: true, message: '倍数不能为空', trigger: 'blur' }
        ],
        accrue: [
          { required: true, message: '累计标识不能为空', trigger: 'change' }
        ],
        format: [
          { required: true, message: '数据格式不能为空', trigger: 'blur' }
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
        id: null,
        profileId: null,
        value: null,
        type: null,
        rw: null,
        base: null,
        minimum: null,
        maximum: null,
        multiple: null,
        accrue: null,
        format: null,
        unit: null,
        modifyTime: null,
        createTime: null
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
      getPoint(id).then(response => {
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
            updatePoint(this.form).then(response => {
              this.$message.success(
                '修改成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          } else {
            addPoint(this.form).then(response => {
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
