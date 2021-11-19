<template>
  <div>
    <el-steps simple :active="0">
      <el-step title="输入证书编号" icon="el-icon-edit"></el-step>
      <el-step title="证书下载" icon="el-icon-upload"></el-step>
    </el-steps>
    <el-card class="box-card">
      <div style="height: 20px">
      </div>
      <div>
        <span><i class="el-icon-search"></i>证书查询</span>
      </div>
      <div class="space">
      </div>
      <div>
        <el-form :model="form" align="center" :rules="rules">
          <el-form-item prop="certificate_no">
            <el-input v-model="form.certificate_no" placeholder="请输入证书编号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" class="btn123" @click="handleClick" :loading="loading">
              立即查询
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { search } from '@/api/search'

export default {
  name: 'one',
  data() {
    return {
      username: this.$store.getters.name,
      form: {
        certificate_no: ''
      },
      rules: {
        certificate_no: [
          { required: true, message: '证书编号不能为空！', trigger: 'blur' }
        ]
      },
      count: 0,
      loading: false
    }
  },
  methods: {
    dataURLtoBlob(dataurl) {
      const bstr = atob(dataurl)
      let n = bstr.length
      const u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new Blob([u8arr], { type: 'pdf' })
    },
    handleClick() {
      this.loading = true
      let name = this.username
      search(this.form.certificate_no).then(res => {
        console.log(res)
        // let blob = new Blob([res], {
        //   //下载的文件类型；
        //   type: 'application/pdf'
        // })
        if (res.code != 200) {
          this.$message.warning(res.msg)
          this.loading = false
          this.count = 0
          return
        }
        let URL = this.dataURLtoBlob(res.data)
        // window.open(window.URL.createObjectURL(URL));
        var reader = new FileReader()
        reader.readAsDataURL(URL)
        reader.onload = function(e) {
          // 兼容IE
          if (window.navigator.msSaveOrOpenBlob) {
            var bstr = atob(e.target.result.split(',')[1])
            var n = bstr.length
            var u8arr = new Uint8Array(n)
            while (n--) {
              u8arr[n] = bstr.charCodeAt(n)
            }
            var blob = new Blob([u8arr])
            console.log(name)
            window.navigator.msSaveOrOpenBlob(blob, '链聚智联区块链可信学历证书-' + name + '.pdf')
          } else {
            // 转换完成，创建一个a标签用于下载
            let link = document.createElement('a')
            link.download = '链聚智联区块链可信学历证书-' + name + '.pdf'
            link.href = e.target.result
            link.click()
            link.remove()
          }
        }
        this.loading = false
        this.count = 0
        this.$router.push({ path: '/search/two' })
      }).catch(() => {
        this.count++
        if (this.count >= 2) {
          this.$message.error('查询失败！')
          this.loading = false
          this.count = 0
          return
        }
        this.handleClick()
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
