<template>
  <div id="app" class="app-container">
    <el-header>
        <el-button icon="el-icon-circle-plus-outline" type="primary" @click=handleAdd>增加</el-button>
    </el-header>
    <el-main>
      <el-table :data="userList"
        v-loading.body="listLoading"
        border fit highlight-current-row
      >
          <el-table-column label="登录用户名" prop="userName" />
          <el-table-column label="手机号" prop="mobile" />
          <el-table-column label="邮箱" prop="email" />
          <el-table-column label="已有角色" prop="roles" />
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-tooltip content="编辑" placement="top" >
                <el-button icon="el-icon-edit" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top" >
                <el-button icon="el-icon-delete" />
              </el-tooltip>
            </template>
          </el-table-column>
      </el-table>
    </el-main>
     <el-dialog :visible.sync="addForm.showForm" :before-close="clearAddForm" width="600px" title="增加用户">
      <el-form v-model="addForm" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="addForm.userName" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addForm.mobile" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addForm.email" />
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="addForm.roleIdList" multiple filterable placeholder="请选择">
            <el-option
              v-for="item in allRoleIdList"
              :key="item.roleName"
              :label="item.roleName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-tag type="info">默认密码:123456</el-tag>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveOrUpdateUser">立即创建</el-button>
          <el-button @click="clearAddForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import userApi from "@/api/user";
import roleApi from "@/api/role";
import { clearAttrs } from "@/utils";
export default {
  name: "user",
  data() {
    return {
      listLoading: true,
      userList: [],
      allRoleIdList:[],
      addForm:{
        showForm: false,
        roleIdList:[]
      }
    };
  },
  methods: {
    handleAdd(){
        this.addForm.showForm = true;
    },
    saveOrUpdateUser(){
        userApi.saveOrUpdateUser(this.addForm)
        .then(()=>{
            this.clearAddForm();
            this.loadData();
        });
    },
    clearAddForm(){
        clearAttrs(this.addForm);
    },
    loadData() {
      this.listLoading = true;
      userApi.listAll().then(({ data }) => {
        this.userList = data;
        this.listLoading = false;
      });
    },
    loadRoles(){
      roleApi.listAll().then(({data})=>{
        this.allRoleIdList = data.list;
      });
    }
  },
  created() {
    this.loadData();
    this.loadRoles();
  }
};
</script>
