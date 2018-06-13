<template>
    <el-container class="app-container">
        <el-header>
            <el-button icon="el-icon-circle-plus-outline" @click="handleAddApp">增加</el-button>
        </el-header>
        <el-main>
            <el-table :data="tableData"
              v-loading.body="listLoading"
              border fit highlight-current-row>
                <el-table-column prop="appCode" label="应用代码" align="center"/>
                <el-table-column prop="appName" label="应用名" align="center"/>
                <el-table-column prop="ownerEmail" label="负责人邮箱" align="center"/>
                <el-table-column prop="enableString" label="启用" align="center"/>
                <el-table-column prop="createdTime" label="创建时间" align="center"/>
                <el-table-column prop="updatedTime" label="最后修改时间" align="center"/>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                    <el-button-group>
                        <el-button icon="el-icon-edit" @click="handleEdit(scope.row)"/>
                    </el-button-group>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
        <el-dialog :visible.sync="editForm.showForm" title="应用编辑" :before-close="clearEditForm" width="500px">
            <el-form v-model="editForm" label-width="100px">
                <el-form-item label="应用名" >
                    <el-input v-model="editForm.appName" />
                </el-form-item>
                <el-form-item label="应用代码">
                    <el-input v-model="editForm.appCode" />
                </el-form-item>
                <el-form-item label="负责人邮箱">
                    <el-input v-model="editForm.ownerEmail" />
                </el-form-item>
                <el-form-item label="启用">
                    <el-checkbox-group v-model="editForm.enable">
                        <el-checkbox label="启用" name="enable"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="saveOrUpdateApp">提交</el-button>
                    <el-button @click="clearEditForm">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </el-container>
</template>
<script>
import "@/styles/common.scss";
import request from "@/utils/request";
import { MessageBox } from "element-ui";
import appApi from "@/api/app";
import { listAllGroup } from "@/api/group";
import { clearAttrs } from "@/utils";
export default {
  name: "app",
  data() {
    return {
        listLoading:true,
        tableData:[],
        editForm:{
            showForm:false,
            id:"",
            appName:"",
            appCode:""
        }
    };
  },
  methods: {
      loadAll(){
          appApi.listAll()
          .then(({data})=>{
              this.listLoading = false;
              this.tableData = data;
          });
      },
      handleAddApp(){
          this.editForm.showForm = true;
      },
      handleEdit(row){
          Object.assign(this.editForm,row);
          this.editForm.showForm = true;
      },
      clearEditForm(){
          clearAttrs(this.editForm);
      },
      saveOrUpdateApp(){
          appApi.saveOrUpdateApp(this.editForm)
          .then(()=>{
              this.clearEditForm();
              this.loadAll();
          });
      }
  },
  created(){
      this.loadAll();
  }
};
</script>
