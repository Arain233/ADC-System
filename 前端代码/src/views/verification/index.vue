<template>
  <div>
    <el-card class="box-card">
      <div style="height: 20px">
      </div>
      <div>
        <span><i class="el-icon-document-checked"></i>证书核验</span>
      </div>
      <div class="space">
      </div>
      <div>
        <el-form :model="form" :rules="rules" align="center">
          <el-form-item prop="certificate_no">
            <el-input v-model="form.certificate_no" placeholder="请输入证书编号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="btn123" @click="VerifyADC" :loading="loading">
              立即核验
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { verification } from '@/api/verification'

export default {
  name: 'Verification',
  data() {
    return {
      form: {
        certificate_no: ''
      },
      rules: {
        certificate_no: [{
          required: true, message: '证书编号不能为空！', trigger: 'blur'
        }]
      },
      count: 0,
      loading: false
    }
  },
  methods: {
    VerifyADC() {
      this.loading = true
      verification(this.form.certificate_no).then(res => {
        if (res.code != 200) {
          this.$message.warning(res.msg)
          this.loading = false
          this.count = 0
          return
        }
        this.$alert(`你所输入的证书编号所对应的学历证书经核验为有效！<br/>所对应的特征信息为：<br/>姓名：${res.data.name}   学校：${res.data.university}  <br/> 毕（结）业日期：${res.data.end_date}`, '证书核验成功！', {
          confirmButtonText: '好的', dangerouslyUseHTMLString: true
        })
        this.loading = false
        this.count = 0
      }).catch(() => {
        this.count++
        if (this.count >= 2) {
          this.$message.error('核验失败！')
          this.loading = false
          this.count = 0
          return
        }
        this.VerifyADC()
      })
    }
  }
}
</script>

<style scoped>
.box-card {
  margin-top: 100px;
  margin-left: 420px;
  width: 480px;
  height: 350px;
}

span {
  margin-left: 140px;
  font-size: 30px;
}

.space {
  height: 50px;
}

.btn123 {
  margin-top: 40px;
  margin-left: 170px;
}

.el-input >>> .el-input__inner::placeholder {
  text-align: center;
}

.el-input >>> .el-input__inner {
  text-align: center;
}
</style>
