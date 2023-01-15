<template>
  <div class="container">
    <!-- <button @click="getAll">发送</button> -->
    <div>
      <el-input
        placeholder="请输入内容"
        v-model="input3"
        class="input-with-select"
        style="width: 400px"
      >
        <el-select
          v-model="select"
          slot="prepend"
          placeholder="请选择"
          style="width: 110px"
        >
          <el-option label="编号" value="1"></el-option>
          <el-option label="姓名" value="2"></el-option>
          <!-- <el-option label="用户电话" value="3"></el-option> -->
        </el-select>
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-button
        type="danger"
        icon="el-icon-delete"
        circle
        style="float: right; margin-right: 20px"
        @click="selectDelete()"
      ></el-button>
      <el-button
        type="success"
        icon="el-icon-plus"
        circle
        style="float: right; margin-right: 10px"
        @click="dialogInsertFormVisible = true"
      ></el-button>
    </div>
    <!-- <div style="height: 508px;overflow: auto;"> -->
    <el-table
      height="510"
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%; overflow: auto"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center">
      </el-table-column>
      <el-table-column align="center" prop="uid" label="编号" width="120">
      </el-table-column>
      <el-table-column label="日期" width="280" align="center">
        <template slot-scope="scope">{{ scope.row.createDate }}</template>
      </el-table-column>

      <el-table-column prop="username" label="姓名" width="180" align="center">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        show-overflow-tooltip
        align="center"
      ></el-table-column>
      <el-table-column
        align="center"
        prop="phoneNum"
        label="联系方式"
        width="160"
      >
      </el-table-column>
      <el-table-column
        label="操作"
        width="160"
        show-overflow-tooltip
        align="center"
      >
        <template slot-scope="scope">
          <el-row>
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="eidtUser(scope.row.uid)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="centerDialogVisible = true"
            ></el-button>
          </el-row>
          <!-- 删除提示框 -->
          <el-dialog
            title="提示"
            :visible.sync="centerDialogVisible"
            width="30%"
            center
          >
            <span style="display: block; text-align: center"
              >确定要删除此条记录吗</span
            >
            <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="deleteUser(scope.row.uid)"
                >确 定</el-button
              >
            </span>
          </el-dialog>
        </template>
      </el-table-column>
    </el-table>
    <!-- </div> -->

    <!-- <div style="margin-top: 20px">
      <el-button @click="toggleSelection([tableData[1], tableData[2]])"
        >切换第二、第三行的选中状态</el-button
      >
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div> -->
    <!-- 分页 -->
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[7, 10, 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>

    <!-- 修改提示框 -->
    <el-dialog title="修改操作" :visible.sync="updateFormVisible">
      <el-form :model="updataForm">
        <el-form-item label="编 号" :label-width="formLabelWidth">
          <el-input
            v-model="updataForm.uid"
            autocomplete="off"
            disabled
            class="updateWidth"
          ></el-input>
        </el-form-item>
        <el-form-item label="姓 名" :label-width="formLabelWidth">
          <el-input v-model="updataForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="日 期" :label-width="formLabelWidth">
          <el-input
            v-model="updataForm.createDate"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="地 址" :label-width="formLabelWidth">
          <el-input v-model="updataForm.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="updataForm.phoneNum" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="活动区域" :label-width="formLabelWidth">
      <el-select v-model="updataForm.region" placeholder="请选择活动区域">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
    </el-form-item> -->
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
        style="margin-right: 100px; padding-top: 0px"
      >
        <el-button @click="updateFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser()">修 改</el-button>
      </div>
    </el-dialog>

    <!-- 新增提示框 -->
    <el-dialog title="新增操作" :visible.sync="dialogInsertFormVisible">
      <el-form :model="updataForm">
        <!-- <el-form-item label="编 号" :label-width="formLabelWidth">
          <el-input
            v-model="updataForm.uid"
            autocomplete="off"
            disabled
            class="updateWidth"
          ></el-input>
        </el-form-item> -->
        <el-form-item label="姓 名" :label-width="formLabelWidth">
          <el-input v-model="insertForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="日 期" :label-width="formLabelWidth">
          <el-input v-model="insertForm.date" autocomplete="off"></el-input>
        </el-form-item> -->
        <el-form-item label="地 址" :label-width="formLabelWidth">
          <el-input v-model="insertForm.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="insertForm.phoneNum" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="活动区域" :label-width="formLabelWidth">
      <el-select v-model="updataForm.region" placeholder="请选择活动区域">
        <el-option label="区域一" value="shanghai"></el-option>
        <el-option label="区域二" value="beijing"></el-option>
      </el-select>
    </el-form-item> -->
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
        style="margin-right: 100px; padding-top: 0px"
      >
        <el-button @click="dialogInsertFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="insertUser()">新 增</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      messageType: "",
      tableData: [],
      multipleSelection: [],

      currentPage: 1,
      pageSize: 7,

      message: "",
      total: 0,
      input1: "",
      input2: "",
      input3: "",
      select: "",

      centerDialogVisible: false,
      updateFormVisible: false,
      updataForm: {},
      formLabelWidth: "120px",
      insertForm: {
        // uid: "",
        // date: "",
        username: "",
        address: "",
        phoneNum: "",
      },
      dialogInsertFormVisible: false,
    };
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(val) {
      this.getByPage(val, 1);

      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getByPage(this.pageSize, val);
      // console.log(`当前页: ${val}`);
    },
    getByPage(pageSize, currentPage) {
      this.request
        .get("/getByPage", {
          params: {
            pageSize,
            currentPage,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data.list;
            this.total = res.data.total;
            this.pageSize = res.data.pageSize;
            // this.currentPage = res.data.currentPage;
            // console.log(res.data.currentPage);
            // console.log(this.currentPage);
          }
          // console.log(this.tableData);
        });
    },
    // getAll() {
    //   this.request.get("/allUser").then((res) => {
    //     // console.log(res)
    //     if (res.code == 200) {
    //       this.tableData = res.data.list;
    //       this.total = res.data.total;
    //       this.pageSize = res.data.pageSize;
    //       this.currentPage = res.data.currentPage;
    //     }
    //     // console.log(this.tableData);
    //   });
    // },
    eidtUser(uid) {
      this.updateFormVisible = true;
      this.request.get("/getUserById/" + uid).then((res) => {
        if (res.code == 200) {
          this.updataForm = res.data;
        }
      });
    },

    deleteUser(uid) {
      this.request
        .delete("/deleteUserById/" + uid)
        .then((res) => {
          this.massage = res.message;
          // console.log(res);
          this.showMessage(res);
        })
        .finally(() => {
          this.getByPage(this.pageSize, this.currentPage);
          // console.log(
          //   "this.total % this.pageSize:" + ((this.total - 1) % this.pageSize)
          // );
          // console.log(this.pageSize);
          // console.log(this.currentPage);
          // console.log(this.total);
          // console.log(this.total % this.pageSize);
          this.centerDialogVisible = false;
        });
      // console.log(this.pageSize);
      // console.log(this.currentPage);
      // console.log(this.total);
      // console.log(this.total % this.pageSize);
      if ((this.total - 1) % this.pageSize == 0) {
        this.currentPage = this.currentPage - 1;
      }
      // console.log(this.currentPage);
      this.getByPage(this.pageSize, this.currentPage);
    },
    insertUser() {
      this.dialogInsertFormVisible = false;
      this.request
        .post("insertUser", this.insertForm)
        .then((res) => {
          console.log(res);
          this.massage = res.massage;
          // this.insertForm = null;
          this.insertForm.username = null;
          this.insertForm.address = null;
          this.insertForm.phoneNum = null;
          this.showMessage(res);
        })
        .finally(() => {
          // console.log(this);
          this.getByPage(this.pageSize, this.currentPage);
        });
    },
    updateUser() {
      this.request
        .put("updateUser", this.updataForm)
        .then((res) => {
          this.updateFormVisible = false;
          console.log(res);
          this.showMessage(res);
        })
        .finally(() => {
          // console.log(this);
          this.getByPage(this.pageSize, this.currentPage);
        });
    },
    showMessage(res) {
      this.message = res.message;
      if (res.code == 200) {
        this.messageType = "success";
      } else if (res.code == 404) {
        this.messageType = "erro";
      }
      this.$message({
        message: this.message,
        type: this.messageType,
      });
    },
    // ordinaryMessage() {
    //   this.$message({
    //     message: this.message,
    //   });
    // },
    // successMessage() {
    //   this.$message({
    //     message: this.message,
    //     type: "success",
    //   });
    // },

    // warnMessage() {
    //   this.$message({
    //     message: this.message,
    //     type: "warning",
    //   });
    // },

    // open4() {
    //   this.$message({
    //     message: this.message,
    //     type: "error",
    //   });
    // },
  },
  created() {
    this.getByPage(this.pageSize, this.currentPage);
    // this.ordinaryMessage();
  },
};
</script>

<style scoped>
main.el-main {
  line-height: 50px;
}
.el-table__cell {
  line-height: 30px;
}
.block {
  margin-top: 30px;
  float: right;
}
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
/* .el-input{
  width: 50%;
} */
.el-form {
  width: 80%;
  margin: 0px auto;
}
</style>