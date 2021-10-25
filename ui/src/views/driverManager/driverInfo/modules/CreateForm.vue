<template>
  <a-drawer width="35%" :label-col="4" :wrapper-col="14" :visible="open" @close="onClose">
    <a-divider orientation="left">
      <b>{{ formTitle }}</b>
    </a-divider>
    <a-form-model ref="form" :model="form" :rules="rules">
      <a-form-model-item label="驱动名称" prop="driverId" >
        <a-select aria-placeholder="请选择驱动" v-model="form.driverId" >
          <a-select-option v-for="(d,index) in driverList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="驱动配置" prop="driverAttributeId">
        <a-select v-model="form.driverAttributeId" >
          <a-select-opt-group v-for="(d,index) in driverList" :key="index" :value="d.id">
            <span slot="label"><a-icon type="user" />{{ d.name }}</span>
            <a-select-option v-for="(a,num) in driverAttributeList" v-if="a.driverId === d.id" :key="num" :value="a.id">{{ a.name }}</a-select-option>
          </a-select-opt-group>
        </a-select>
        </a-form-model-item>
        <!--          TODO:双重循环查找-->
      <a-form-model-item label="模板" prop="profileId" >
        <a-select aria-placeholder="请选择模板" v-model="form.profileId" >
          <a-select-option v-for="(d, index) in profileList" :key="index" :value="d.id">{{ d.name }}</a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="内容值" prop="value" >
        <a-input v-model="form.value" placeholder="请输入内容值" />
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
import { getDriverInfo, addDriverInfo, updateDriverInfo } from '@/api/driverManager/driverInfo'
import { listDriver } from '@/api/driverManager/driver'
import { listDriverAttribute } from '@/api/driverManager/driverAttribute'
import { listProfile } from '@/api/profileManager/profile'

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
      // driver
      driverList: null,
      // driverAttribute
      driverAttributeList: null,
      driverAttributeList1: null,
      // profile
      profileList: null,
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
      query3: {
        driverAttributeId: null,
        driverId: null,
        driverAttributeName: null
      },
      // 表单参数
      form: {
        id: null,
        driverId: null,
        driverAttributeId: null,
        profileId: null,
        value: null,
        description: null,
        createTime: null,
        modifyTime: null
      },
      // 1增加,2修改
      formType: 1,
      open: false,
      rules: {
        driverId: [
          { required: true, message: '驱动id不能为空', trigger: 'blur' }
        ],
        driverAttributeId: [
          { required: true, message: '驱动配置id不能为空', trigger: 'blur' }
        ],
        profileId: [
          { required: true, message: '模板id不能为空', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '内容值不能为空', trigger: 'blur' }
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
    listDriver(this.query).then(response => {
      this.driverList = response.rows
    })
    listDriverAttribute(this.query1).then(response => {
      this.driverAttributeList = response.rows
    })
    listProfile(this.query2).then(response => {
      this.profileList = response.rows
    })
    listDriverAttribute(this.query3).then(response => {
      for (let i = 0; i < this.driverList.length; i++) {
        console.log(this.driverList[i].id + this.driverList[i].name)
      }
      this.driverAttributeList1 = response.rows
    })
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
        driverId: null,
        driverAttributeId: null,
        profileId: null,
        value: null,
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
      getDriverInfo(id).then(response => {
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
            updateDriverInfo(this.form).then(response => {
              this.$message.success(
                '修改成功',
                3
              )
              this.open = false
              this.$emit('ok')
            })
          } else {
            addDriverInfo(this.form).then(response => {
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
