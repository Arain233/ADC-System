<template>
  <div>
    <el-calendar :range="['2021-05-17', '2021-05-23']">
    </el-calendar>
    <div class="container">
      <CardShow data-image="https://i0.hippopx.com/photos/829/724/891/divorce-separation-marriage-breakup-preview.jpg">
        <h1 slot="header">项目背景</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目背景 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/904/479/690/android-asus-phone-mobile-smart-phone-preview.jpg"
      >
        <h1 slot="header">项目功能</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目功能 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/400/716/42/cyber-security-online-computer-cyber-preview.jpg"
      >
        <h1 slot="header">项目创新</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目创新 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/87/549/114/building-corporate-building-design-architecture-preview.jpg"
      >
        <h1 slot="header" style="margin-bottom: 44px">项目前景</h1>
        <p slot="content" style="font-size: 14px">{{ items.项目前景 }}</p>
      </CardShow>
    </div>
    <!--    <el-carousel :interval="4000" type="card" height="450px">-->
    <!--      <el-carousel-item v-for="(item, index) in items" :key="index">-->
    <!--        <h1>{{ index }}</h1>-->
    <!--        <h3 class="medium">{{ item }}</h3>-->
    <!--      </el-carousel-item>-->
    <!--    </el-carousel>-->
  </div>
</template>

<script>
import CardShow from './components/card'

export default {

  name: 'Dashboard',
  components: { CardShow },
  data() {
    return {
      username: '',
      items: {
        项目背景: '随着时代的发展和社会的进步，高等教育的浪潮越来越向民众靠近，平均教育水平越来越高，与此同时，各类企业单位对于求职者的学历等硬实力的要求愈发严苛。在这种的境况下，学历造假、认证造假现象等在当今社会屡见不鲜，一方面，这造成了用人市场一定程度的混乱以及企业信任度的降低，增加了用人成本，造成了极大的社会影响；另一方面，也反映出，传统的纸质或电子学历证书在安全性和不可伪造性等方面还存在一定缺陷。基于这种社会大背景，需要一种安全性更高且防篡改的学历证书存储模式来避免种种社会问题的存在。',
        项目功能: '借助国密算法、区块链等技术，我们的学历认证管理系统主要实现了以下3种功能：\n' +
          '1、证书生成及上链：用户输入证书相关信息，确认无误后点击生成，后台将根据用户输入的证书编号进行校验，如果证书编号冲突则会拒绝生成证书并且返回提示信息。否则将生成证书并将证书信息进行加密处理后上链。\n' +
          '2、证书查询：用户输入证书编号进行查询，后台自动检索当前登陆用户名与用户查询的证书上的姓名是否匹配。若匹配，则展示证书，否则将拒绝展示并返回提示信息。\n' +
          '3、证书核验：用户输入证书编号查询证书是否已存在。',
        项目创新: '为了解决学历造假、认证造假的问题，本团队研发了基于区块链与国密算法的学位学历认证管理系统。本平台克服了传统中心化认证系统查证结果易造假、隐私不安全和验证范围局限的弊端。利用区块链的不可篡改性、防伪性与其在溯源中的应用，确保了认证结果的公信力。采用国密算法来保障系统数据安全和提高隐私保护，实现了数据加密自主化，增强了平台的安全可控能力。本系统融合区块链技术和国密算法，构建了学位学历认证管理系统，能有效杜绝学位学历造假的可能性并提高系统的隐私保护，具有良好的社会与经济效益。',
        项目前景: '链聚智联团队所实现的基于区块链的学历学位认证系统，界面简洁，功能齐全，运行稳定，能够满足时代对学历证书存储安全性的要求，能够满足市场需求，将区块链、国密算法、IPFS等技术结合在一起，文件地址与证书哈希值上链，链上所有节点参与验证，最大程度杜绝造假，提高了安全与隐私保护的能力，为学历造假的解决带来一种新的途径。'
      }
    }
  },

  created() {
    this.username = this.$store.getters.name
    this.$notify({
      title: `欢迎回来，${this.username}`,
      message: '我们需要一次不信而信的"历"险',
      type: 'success',
      duration: 4000
    })
  }
}
</script>

<style lang="scss" scoped>
p {
  text-align: center;
  font-family: "Helvetica Neue";
  font-size: 22px;
  font-weight: bolder;
}

.el-carousel__item h3 {
  color: #000000;
  font-size: 14px;
  opacity: 0.9;
  line-height: 40px;
  margin: 10px;
}

h1 {
  text-align: center;
}

hr {
  color: #d3dce6;
  opacity: 80%;
}

//：nth-child的下标是从1开始的
.el-carousel__item:nth-child(3) {
  background-color: #ec7272;
}

.el-carousel__item:nth-child(4) {
  background-color: #FCE38A;
}

.el-carousel__item:nth-child(5) {
  background-color: #94c2f0;
}

.el-carousel__item:nth-child(6) {
  background-color: #7dc667;
}

</style>
