<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <el-container height="100%">
    <el-aside width="200px"><NavBar /></el-aside>
    <el-container height="100%">
      <el-header height="80px"><Header /></el-header>
      <el-main>
        <TableList />
      </el-main>
      <!-- <el-footer>Footer</el-footer> -->
    </el-container>
  </el-container>

</template>

<script>
import NavBar from "./NavBar"
import Header from "./Header"
import TableList from "./TableList"
export default {
  data() {
    return {};
  },
  components: {
    NavBar,
    Header,
    TableList
  },
};
</script>

<style scoped>
* {
  padding: 0px;
  margin: 0px;
}
.el-header, .el-footer {
  background-color: #d8dde4;
    color: #333;
    text-align: center;
    line-height: 60px;
}
.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
  
/* .el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
} */
  
.el-container {
    margin-bottom: 40px;
    height: 100%;
  }
  
.el-container:nth-child(5) .el-aside,.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
  
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>