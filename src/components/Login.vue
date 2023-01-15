<template>
  <div class="login">
    <h2 class="title">欢迎来到XX管理系统</h2>
    <el-form ref="user" :model="user" label-width="80px" class="form">
      <el-form-item>
        <el-input
          placeholder="请输入账号"
          v-model="user.username"
           prefix-icon="el-icon-user-solid"
        >

        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          placeholder="请输入密码"
          v-model="user.password"
          show-password
          prefix-icon="el-icon-lock"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loginBtn">登录</el-button>
        <el-button @click="quitBtn">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    loginBtn() {
      console.log("submit!");
      // axios
      //   .get("http://localhost:8080/SpringMVC/login", {
      //     params: {
      //       username: this.user.username,
      //       password: this.user.password,
      //     },
      //   })
      //   .then((res) => {
      //     if (res.data.user !== null) {
      //       this.$router.push({
      //         path: "/main",
      //       });
      //     }
      //   });
      this.$router.push({
        path: "/main",
      });
    },
    quitBtn() {
      (this.user.username = ""), (this.user.password = "");
    },
  },
};
</script>

<style scoped>
* {
  margin: 0px;
  padding: 0px;
}
.login {
  width: 456px;
  margin: 0px auto;
  margin-top: 10%;
  padding: 30px;
  background-color: rgb(235, 234, 234);
  /* padding-left: 20%; */
}
.form {
  padding-right: 20%;
  /* border: 1px solid red; */
}
.title {
  padding-right: 40px;
  margin: 30px 0px;
}
</style>