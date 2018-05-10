<template>
  <div class="app-container">
    <el-header>
        <el-button icon="el-icon-circle-plus-outline" type="primary" @click=handleAdd>增加</el-button>
    </el-header>
    <el-main>
      <el-table :data="tableData"
              v-loading.body="listLoading"
              border fit highlight-current-row>
        <el-table-column prop="roleName" label="角色名" align="center"/>
        <el-table-column prop="permissions" label="已有资源权限" align="center"/>
        <el-table-column prop="profilePermissions" label="已有环境权限" align="center"/>
        <el-table-column prop="createdAt" label="创建时间" align="center"/>
        <el-table-column prop="updatedAt" label="更新时间" align="center"/>
        <el-table-column label="操作">
        <template slot-scope="scope">
          <el-tooltip content="编辑" placement="top">
            <el-button icon="el-icon-edit" @click="handleEdit(scope.row)"/>
          </el-tooltip>
          <el-tooltip content="资源授权" placement="top">
            <el-button icon="el-icon-setting" @click="handleUpdatePermission(scope.row)"/>
          </el-tooltip>
          <el-tooltip content="环境授权" placement="top">
            <el-button icon="el-icon-setting" @click="handleUpdateProfilePermission(scope.row)"/>
          </el-tooltip>
          <el-tooltip content="删除" placement="top">
            <el-button icon="el-icon-delete" @click="handleDelete(scope.row)"/>
          </el-tooltip>
        </template>
      </el-table-column>
      </el-table>
    </el-main>
    <el-dialog :visible.sync="editForm.showForm" :before-close="clearForm" width="600px" title="配置">
      <el-form v-model="editForm" label-width="80px">
        <el-input type="hidden" v-model="editForm.id"/>
        <el-form-item label="角色名">
          <el-input v-model="editForm.roleName"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveOrUpdateRole">立即创建</el-button>
          <el-button @click="clearForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog :visible.sync="permissionForm.showForm" :before-close="clearGrantPermissionForm" width="600px" title="资源权限">
      <el-form v-model="permissionForm" label-width="80px">
        <el-input type="hidden" v-model="permissionForm.id"/>
        <el-form-item label="权限">
          <el-tree
            :props="treeProps"
            :data="treeData"
            @check="handleResourceCheckChange"
            node-key="id"
            default-expand-all
            :default-checked-keys="treeCheckedList"
            :expand-on-click-node="false"
            show-checkbox>
          </el-tree>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="grantPermission">授权</el-button>
          <el-button @click="clearGrantPermissionForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog :visible.sync="profilePermissionForm.showForm" :before-close="clearGrantProfilePermissionForm" width="600px" title="环境权限">
      <el-form v-model="profilePermissionForm" label-width="80px">
        <el-input type="hidden" v-model="profilePermissionForm.roleId"/>
        <el-form-item label="授权环境">
          <el-select
            v-model="profilePermissionForm.profileIdList"
            multiple
            filterable
            allow-create
            placeholder="选择环境">
            <el-option
              v-for="item in allProfile"
              :key="item.id"
              :label="item.profileName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="grantProfilePermission">授权</el-button>
          <el-button @click="clearGrantProfilePermissionForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import roleApi from "@/api/role";
import {listAllProfile} from "@/api/profile";
import { clearAttrs } from "@/utils";

export default {
  name: "roleTable",
  data() {
    return {
      listLoading: true,
      tableData: [],
      allProfile:[],
      editForm: {
        id: "",
        showForm: false
      },
      profilePermissionForm:{
        profileIdList:[],
        roleId:-1
      },
      treeProps:{
        label:'resourceName'
      },
      treeData:[],
      treeCheckedList:[],
      permissionForm: {
        roleId:-1,
        showForm: false
      }
    };
  },
  methods: {
    handleAdd() {
      this.editForm.showForm = true;
    },
    handleEdit(data) {
      Object.assign(this.editForm, data);
      this.editForm.showForm = true;
    },
    handleUpdateProfilePermission(row){
      this.fetchProfile();
      this.profilePermissionForm.roleId = row.id;
      this.profilePermissionForm.showForm = true;
    },
    handleUpdatePermission(row) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });
      roleApi.loadDetail(row).then(({data}) => {
        this.treeData = data.permissionList;
        this.treeCheckedList = data.checkedPermissionIdList;
        this.permissionForm.roleId = row.id;
        this.permissionForm.showForm = true;
        loading.close();
      });
    },
    handleDelete(data) {
      roleApi.deleteRole(data)
      .then(()=>{
        this.fetchData();
      });
    },
    saveOrUpdateRole() {
      roleApi.saveOrUpdateRole(this.editForm).then(() => {
        this.fetchData();
        this.clearForm();
      });
    },
    handleResourceCheckChange(obj,checkedNodes){
      this.permissionForm.resourceIdList = checkedNodes.checkedKeys;
    },
    grantPermission(){
      roleApi.grantPermission(this.permissionForm)
      .then(()=>{
        this.clearGrantPermissionForm();
      });
    },
    grantProfilePermission(){
      roleApi.grantPermission(this.profilePermissionForm)
      .then(()=>{
        this.clearGrantProfilePermissionForm();
      });
    },
    clearGrantPermissionForm(){
      clearAttrs(this.permissionForm);
    },
    clearGrantProfilePermissionForm(){
      clearAttrs(this.profilePermissionForm);
    },
    clearForm() {
      clearAttrs(this.editForm);
    },
    fetchData() {
      this.listLoading = true;
      roleApi
        .listAll()
        .then(({ data }) => {
          this.tableData = data.list;
          this.listLoading = false;
        })
        .catch(() => {
          this.listLoading = false;
        });
    },
    fetchProfile(){
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });
      listAllProfile()
      .then(({data})=>{
        this.allProfile = data;
        loading.close();
      });
    }
  },
  created() {
    this.fetchData();
  }
};
</script>
