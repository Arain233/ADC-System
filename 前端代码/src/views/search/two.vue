<template>
  <div>
    <el-steps simple :active="1">
      <el-step title="输入证书编号" icon="el-icon-edit"></el-step>
      <el-step title="证书下载" icon="el-icon-upload"></el-step>
    </el-steps>
    <div>
      <p>
        证书已开始下载..........
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "two",
  data() {
    return {
      // pSrc: '',
      // publicPath: process.env.BASE_URL
    };
  },
  methods: {
    // loadPDF() {
    //   //baseurl ：pdf存放的文件路径，可以是本地的，也可以是远程，这个是远程的，亲测可以用
    //   let baseurl = this.$route.query.URL;
    //   //ie有缓存加个随机数解决 + '?r=' + new Date()
    //   let pSrc = baseurl + '?r=' + new Date();
    //   this.pSrc = `${publicPath}pdf/web/viewer.html` + encodeURIComponent(pSrc) + '.pdf';
    // },
  },

  mounted: function () {
    // this.loadPDF();
  }

}

</script>

<style scoped>
p{
  margin-top: 200px;
  font-size: 60px;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  text-align: center;
}
</style>
