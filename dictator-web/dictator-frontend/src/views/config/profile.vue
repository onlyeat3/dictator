<template>
  <div class="app-container">
    <el-header>
      <el-button icon="el-icon-circle-plus-outline" @click="handleAddProfile">增加</el-button>
    </el-header>
    <el-table :data="tableData"
              v-loading.body="listLoading"
              border fit highlight-current-row>
      <el-table-column prop="id" label="id" align="center" />
      <el-table-column prop="profileName" label="环境名" align="center"/>
      <el-table-column prop="profileCode" label="环境代码" align="center"/>
      <el-table-column prop="profileDesc" label="环境描述" align="center" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="handleEditProfile(scope.row)" class="el-button--primary" icon="el-icon-edit"></el-button>
          <el-button @click="handleDeleteProfile(scope.row)" icon="el-icon-delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="editProfileForm.showForm" width="500px" :before-close="clearForm">
      <el-form v-model="editProfileForm" label-width="80px">
        <el-form-item label="环境名">
          <el-input v-model="editProfileForm.profileName" />
        </el-form-item>
        <el-form-item label="环境代码">
          <el-input v-model="editProfileForm.profileCode" />
        </el-form-item>
        <el-form-item label="环境描述">
          <el-input v-model="editProfileForm.profileDesc" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleProfileUpdate">保存</el-button>
          <el-button @click="clearForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import '@/styles/common.scss';
  import {clearAttrs} from "@/utils";
  import {listAllProfile,updateProfile,deleteProfile} from "@/api/profile";
  import {MessageBox} from 'element-ui';

  export default {
    name: 'profile',
    data() {
      return {
        listLoading: true,
        tableData: [],
        editProfileForm:{
          showForm:false,
          profileName:'',
          profileCode:'',
          profileDesc:'',
          id:''
        }
      }
    },
    methods: {
      fetchData() {
        this.listLoading = true;
        listAllProfile()
          .then(response => {
            let {data} = response;
            this.tableData = data;
            this.listLoading = false;
          }).catch(() => {
          this.listLoading = false;
        });
      },
      handleAddProfile(){
        this.editProfileForm.showForm = true;
      },
      handleEditProfile(row){
        Object.assign(this.editProfileForm,row);
        this.editProfileForm.showForm = true;
      },
      handleProfileUpdate(){
        updateProfile(this.editProfileForm)
          .then(resp=>{
            this.clearForm();
            this.fetchData();
          })
      },
      handleDeleteProfile(row){
        MessageBox.confirm("点击删除，环境会被直接删除，配置从当前配置移动到历史配置。确定删除？","警告",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(()=>{
          deleteProfile(row.id)
            .then(resp => {
              this.fetchData();
            })
        });
      },
      clearForm(){
        clearAttrs(this.editProfileForm);
      }
    },
    created() {
      this.fetchData();
    }
  }
</script>
