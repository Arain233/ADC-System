<template>
  <div>
    <div class="space"></div>
    <el-row :gutter="60">
      <el-col :span="1" :offset="9">
        <i class="el-icon-upload"></i>
      </el-col>
      <el-col :span="5" :offset="1">
        <a>学信导入</a>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <el-input v-model="code" placeholder="请输入学信网学历证书在线验证码"></el-input>
      </el-col>
    </el-row>
    <br>
    <br>
    <div class="btn1">
      <el-button type="primary" size="big" :loading="loading" plain @click.native.prevent="generateADC">生成证书</el-button>
    </div>

  </div>
</template>

<script>
import { importcert } from '@/api/generate'
import { getToken } from '@/utils/auth'

export default {
  name: 'Import',
  data() {
    return {
      code: '',
      loading: false,
      count: 0
    }
  },
  methods:
    {
      generateADC() {
        this.loading = true
        importcert(this.code).then(response => {
          if (response.code == 200) {
            this.$alert('您的学历证书已经成功从学信导入系统！', '证书生成成功！', {
              confirmButtonText: '好的'
            })
            this.loading = false
            this.count = 0
          }
          if (response.code == 500) {
            this.$message.warning(response.msg)
            this.loading = false
          }
        }).catch(() => {
          this.count++
          if (this.count >= 2) {
            this.$message.error('导入失败！')
            this.loading = false
            this.count = 0
            return
          }
          this.generateADC()
        })
      }
    }
}
</script>

<style scoped>
.el-row {
  margin-top: 45px;
}

.btn1 {
  text-align: center;
}

i {
  font-size: 54px;
}

.space {
  width: 100%;
  height: 80px;
}

a {
  font-family: "Helvetica Neue";
  cursor: default;
  font-size: 40px;
}

.el-input >>> .el-input__inner::placeholder {
  text-align: center;
}

.el-input >>> .el-input__inner {
  text-align: center;
}
</style>
