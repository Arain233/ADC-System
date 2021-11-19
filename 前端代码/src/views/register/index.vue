<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">学位学历认证管理系统实名注册</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="姓名"
          name="username"
          type="text"
          tabindex="1"
        />
      </el-form-item>

      <el-form-item prop="idNum">
        <span class="svg-container">
          <svg-icon icon-class="form"/>
        </span>
        <el-input
          ref="idNum"
          v-model="loginForm.idNum"
          placeholder="身份证号"
          name="idNum"
          type="text"
          tabindex="2"
        />
      </el-form-item>

      <el-form-item prop="mobile">
        <span class="svg-container">
          <svg-icon icon-class="form"/>
        </span>
        <el-input
          ref="mobile"
          v-model="loginForm.mobile"
          placeholder="手机号"
          name="mobile"
          type="text"
          tabindex="3"
        />
      </el-form-item>

      <el-form-item prop="email">
        <span class="svg-container">
          <svg-icon icon-class="form"/>
        </span>
        <el-input
          ref="email"
          v-model="loginForm.email"
          placeholder="邮箱号"
          name="email"
          type="text"
          tabindex="4"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="5"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon
            :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
          />
        </span>
      </el-form-item>

      <el-form-item prop="captcha">
        <span class="svg-container">
          <i class="el-icon-warning"/>
        </span>
        <el-input placeholder="请输入验证码" tabindex="6" v-model="loginForm.captcha" name="captcha" ref="captcha"/>
        <span class="show-captcha" @click="getCaptcha">
          <img :src="captchaUrl" alt="登录验证码">
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleRegister"
      >注册
      </el-button>
      <el-row></el-row>
      <el-button
        :loading="loading"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleBack"
        plain
      >返回登录界面
      </el-button>

    </el-form>
  </div>
</template>

<script>
import { GetCaptcha, register } from '@/api/user'

export default {
  name: 'Register',
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能少于5位！'))
      } else {
        callback()
      }
    }
    const validateIdNum = (rule, value, callback) => {
      if (value.length != 18) {
        callback(new Error('身份证号格式不正确！'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        idNum: '',
        password: '',
        mobile: '',
        email: '',
        captcha: '',
        role_id: 1
      },
      loginRules: {
        username: [
          { required: true, message: '请输入真实姓名！', trigger: 'blur' }
        ],
        idNum: [
          { required: true, trigger: 'blur', validator: validateIdNum }
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword }
        ],
        mobile: [
          { required: true, message: '请输入手机号！', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱号！', trigger: 'blur' }
        ],
        captcha: [
          { required: true, message: '请输入验证码！', trigger: 'blur' }
        ]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      captchaUrl: ''
    }
  },
  created() {
    this.getCaptcha()
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleRegister() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          register(this.loginForm).then(response => {
            if (response.code == 400) {
              this.$message.error(response.msg)
              this.loading = false
              return
            }
            this.$message.success('恭喜你注册成功！')
            this.loading = false
            this.$refs.loginForm.resetFields()
          }).catch(() => {
            this.$message.error('注册失败！')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleBack() {
      this.$router.push({ path: '/login' })
    },
    getCaptcha() {
      const params = {}
      GetCaptcha(params).then(res => {
        // btoa 创建一个 base-64 编码的字符串
        // Uint8Array 创建8位无符号整型数组
        // fromCharCode 将 Unicode 编码转为一个字符
        console.log(res)
        const img = btoa(
          new Uint8Array(res).reduce((data, byte) => data + String.fromCharCode(byte), '')
        )
        this.captchaUrl = 'data:image/png;base64,' + img
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 90%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  background-image: url("~@/assets/background.png");
  background-size: cover;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 10px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 32px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-family: "\534E\6587\884C\6977";
      font-size: 43px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .show-captcha {
    position: absolute;
    right: 0px;
    top: 1px;
    font-size: 18px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
