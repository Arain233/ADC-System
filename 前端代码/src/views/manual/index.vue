<template>
  <div>
    <h1 align="center">学历证书手动生成</h1>
    <el-form label-position="right" label-width="120px" :model="form" :rules="rules" ref="form"
             style="width: 700px;margin-left: 255px"
    >
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期" prop="birth">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.birth" style="width: 100%;"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="入学日期" prop="start_date">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.start_date" style="width: 100%;"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="毕（结）业日期" prop="end_date">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.end_date" style="width: 100%;"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="学校名称" prop="university">
        <el-input v-model="form.university"></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="profession">
        <el-input v-model="form.profession"></el-input>
      </el-form-item>
      <el-form-item label="学历类别" prop="type">
        <el-input v-model="form.type"></el-input>
      </el-form-item>
      <el-form-item label="学制" prop="school_system">
        <el-input v-model="form.school_system">
          <template slot="append">年</template>
        </el-input>
      </el-form-item>
      <el-form-item label="学习形式" prop="form">
        <el-input v-model="form.form"></el-input>
      </el-form-item>
      <el-form-item label="层次" prop="level">
        <el-select v-model="form.level" placeholder="请选择相应层次" clearable>
          <el-option value="专科">专科</el-option>
          <el-option value="本科">本科</el-option>
          <el-option value="硕士研究生">硕士研究生</el-option>
          <el-option value="博士研究生">博士研究生</el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证书编号" prop="certificate_no">
        <el-input v-model="form.certificate_no"></el-input>
      </el-form-item>
      <el-form-item label="毕（结）业" prop="graduation_attributes">
        <el-radio-group v-model="form.graduation_attributes">
          <el-radio label="毕业"></el-radio>
          <el-radio label="结业"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="校（院）长姓名" prop="principaln_name">
        <el-input v-model="form.principaln_name"></el-input>
      </el-form-item>
      <el-form-item align="center" style="margin-right: 35px">
        <el-button type="primary" @click="submitForm('form')" :loading="loading">立即生成</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { manualcert } from '@/api/generate'

export default {
  name: 'Manual',
  data() {
    const validateSystem = (rule, value, callback) => {
      if (!(/(^[1-9]\d*$)/.test(value))) {
        callback(new Error('学制只能为正整数'))
      } else {
        callback()
      }
    }
    return {
      form: {
        name: '',
        sex: '',
        birth: '',
        start_date: '',
        end_date: '',
        university: '',
        profession: '',
        type: '',
        school_system: '',
        form: '',
        level: '',
        certificate_no: '',
        graduation_attributes: '',
        principaln_name: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入姓名！', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择性别！', trigger: 'blur' }
        ],
        birth: [
          { required: true, message: '请输入出生日期！', trigger: 'blur' }
        ],
        start_date: [
          { required: true, message: '请输入入学日期！', trigger: 'blur' }
        ],
        end_date: [
          { required: true, message: '请输入毕（结）业日期！', trigger: 'blur' }
        ],
        university: [
          { required: true, message: '请输入学校名称！', trigger: 'blur' }
        ],
        profession: [
          { required: true, message: '请输入专业！', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请输入学历类别！', trigger: 'blur' }
        ],
        school_system: [
          { required: true, message: '请输入学制！', trigger: 'blur' },
          { message: '学制只能为数字！', trigger: 'change', validator: validateSystem }
        ],
        form: [
          { required: true, message: '请输入学习形式！', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择相应层次！', trigger: 'blur' }
        ],
        certificate_no: [
          { required: true, message: '请输入证书编号！', trigger: 'blur' }
        ],
        graduation_attributes: [
          { required: true, message: '请选择毕（结）业信息！', trigger: 'blur' }
        ],
        principaln_name: [
          { required: true, message: '请输入校（院）长姓名！', trigger: 'blur' }
        ]
      },
      loading: false,
      count: 0
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true
          console.log(this.form)
          manualcert(this.form).then(res => {
              if (res.code != 200) {
                this.$message.error(res.msg)
                this.loading = false
                this.count = 0
                return
              }
              this.form.school_system += ' 年'
              this.$alert('您的学历证书已经成功手动导入系统！', '证书生成成功！', {
                  confirmButtonText: '好的'
                }
              )
              this.loading = false
              this.$refs.form.resetFields()
              this.count = 0
            }
          ).catch(() => {
            this.count++
            if (this.count > 2) {
              this.$message.error('导入失败！')
              this.loading = false
              this.count = 0
              return
            }
            this.submitForm(formName)
          })
          console.log(this.form)
        } else {
          this.$message.error('请补全所需信息！')
          console.log(this.form)
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.$message.info('重置成功！')
    }
  }
}
</script>

<style scoped>

</style>
