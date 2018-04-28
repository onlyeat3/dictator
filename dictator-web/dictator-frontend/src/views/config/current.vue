<template>
  <div class="app-container">
    <el-header>
      <el-form :model="listQuery" :inline="true">
        <el-form-item label="应用ID">
          <el-input v-model="listQuery.appId"/>
        </el-form-item>
        <el-form-item label="配置名">
          <el-input v-model="listQuery.configName"/>
        </el-form-item>
        <el-form-item label="环境">
          <el-select v-model="listQuery.profileId">
            <el-option
              v-for="item in profileList"
              :key="item.profileName"
              :label="item.profileName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分组">
          <el-select v-model="listQuery.groupId">
            <el-option
              v-for="item in groupList"
              :key="item.groupName"
              :label="item.groupName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="onQueryFormSubmit">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-circle-plus-outline" @click="handleAdd">增加</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-circle-plus-outline" @click="batchForm.showForm= true">导入</el-button>
        </el-form-item>
      </el-form>
    </el-header>
    <el-header>
      <el-pagination
        background
        layout="sizes, prev, pager, next"
        @current-change="onPageNumChange"
        @size-change="onPageSizeChange"
        :data="listQuery"
        :current-page.sync="listQuery.pageNum"
        :total="listQuery.total">
      </el-pagination>
    </el-header>
    <el-table :data="tableData"
              v-loading.body="listLoading"
              border fit highlight-current-row>
      <el-table-column prop="id" label="" align="center" type="expand">
          <el-form slot-scope="props" label-position="left">
            <el-form-item label="id">
              <span>{{props.row.id}}</span>
            </el-form-item>
            <el-form-item label="应用ID">
              <span>{{props.row.appId}}</span>
            </el-form-item>
            <el-form-item label="环境">
              <span>{{props.row.profileName}}</span>
            </el-form-item>
            <el-form-item label="分组">
              <span>{{props.row.groupName}}</span>
            </el-form-item>
            <el-form-item label="配置名">
              <span>{{props.row.configName}}</span>
            </el-form-item>
            <el-form-item label="属性值">
              <span>{{props.row.configValue}}</span>
            </el-form-item>
            <el-form-item label="版本">
              <span>{{props.row.version}}</span>
            </el-form-item>
            <el-form-item label="备注">
              <span>{{props.row.remark}}</span>
            </el-form-item>
            <el-form-item label="创建时间">
              <span>{{props.row.createdTime}}</span>
            </el-form-item>
            <el-form-item label="修改时间">
              <span>{{props.row.updatedTime}}</span>
            </el-form-item>
          </el-form>
      </el-table-column>
      <el-table-column prop="appId" label="应用ID" align="center"/>
      <el-table-column prop="profileName" label="环境" align="center"/>
      <el-table-column prop="groupName" label="分组" align="center"/>
      <el-table-column prop="configName" label="配置名" align="center"/>
      <el-table-column prop="configValue" label="属性值" align="center" class-name="long-text"/>
      <el-table-column prop="remark" label="备注" align="center"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" @click="handleEdit(scope.row)"/>
          <el-button icon="el-icon-delete" @click="handleDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <el-footer>
      <el-pagination
        background
        layout="sizes, prev, pager, next"
        @current-change="onPageNumChange"
        @size-change="onPageSizeChange"
        :data="listQuery"
        :current-page.sync="listQuery.pageNum"
        :total="listQuery.total">
      </el-pagination>
    </el-footer>
    <el-dialog :visible.sync="editForm.showForm" :before-close="clearForm" width="600px" title="配置">
      <el-form v-model="editForm" label-width="80px">
        <el-input type="hidden" v-model="editForm.id"/>
        <el-form-item label="应用ID">
          <el-input v-model="editForm.appId"/>
        </el-form-item>
        <el-form-item label="环境">
          <el-select v-model="editForm.profileId">
            <el-option
              v-for="item in profileList"
              :key="item.profileName"
              :label="item.profileName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分组">
          <el-select v-model="editForm.groupId">
            <el-option
              v-for="item in groupList"
              :key="item.groupName"
              :label="item.groupName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="配置名">
          <el-input v-model="editForm.configName"/>
        </el-form-item>
        <el-form-item label="属性值">
          <el-input type="textarea" v-model="editForm.configValue"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="editForm.remark"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button @click="clearForm">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog :visible.sync="batchForm.showForm" :before-close="clearBatchForm" width="60%" title="文件导入">
      <el-form v-model="batchForm" label-width="80px">
        <el-form-item label="应用ID">
          <el-input v-model="editForm.appId"/>
        </el-form-item>
        <el-form-item label="环境">
          <el-select v-model="editForm.profileId">
            <el-option
              v-for="item in profileList"
              :key="item.profileName"
              :label="item.profileName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分组">
          <el-select v-model="editForm.groupId">
            <el-option
              v-for="item in groupList"
              :key="item.groupName"
              :label="item.groupName"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="贴一贴">
          <el-input type="textarea" :rows="20" v-model="batchForm.content"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="clearBatchForm">取消</el-button>
          <el-button type="primary" @click="onSubmitBatchImport">导入</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import '@/styles/config.scss';
  import '@/styles/common.scss';
  import request from '@/utils/request';
  import {MessageBox} from 'element-ui';
  import {listAllProfile} from "@/api/profile";
  import {listAllGroup} from "@/api/group";
  import {listConfig,saveOrUpdateConfig,batchAddConfig} from "@/api/config";
  import {clearAttrs} from "@/utils";

  export default {
    name: 'configTable',
    data() {
      return {
        listLoading: true,
        tableData: [],
        listQuery: {
          pageNum: 1,
          pageSize: 10,
          configName: '',
          groupName: ''
        },
        editForm: {
          showForm: false,
          id: -1,
          appId: '',
          deploymentId: '',
          profileId: '',
          groupId: '',
          configName: '',
          configValue: ''
        },
        batchForm:{
          showForm:false,
          content:''
        },
        profileList: [],
        groupList: [],
      }
    },
    methods: {
      onSubmitBatchImport(){
        Object.assign(this.batchForm,this.editForm);
        batchAddConfig(this.batchForm)
          .then(()=>{
            this.clearBatchForm();
            this.fetchData();
          });
      },
      clearBatchForm(){
        clearAttrs(this.editForm);
      },
      onSubmit() {
        saveOrUpdateConfig(this.editForm)
          .then(() => {
            this.clearForm();
            this.fetchData();
          });
      },
      onQueryFormSubmit() {
        this.fetchData();
      },
      onPageNumChange(currentPage) {
        this.listQuery.pageNum = currentPage;
        this.fetchData();
      },
      onPageSizeChange(pageSize) {
        this.listQuery.pageSize = pageSize;
        this.fetchData();
      },
      handleDelete(row) {
        MessageBox.confirm("删除后可以在配置历史里恢复,确认删除?", "提示", {
          type: "warning",
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        })
          .then(() => {
            request({
              url: "config/delete",
              data: {
                id: row.id
              }
            }).then(() => {
              this.fetchData();
            })
          })
          .catch(() => {
          })
      },
      handleEdit(row) {
        Object.assign(this.editForm, row);
        this.handleAdd();
      },
      handleAdd() {
        this.fetchProfile();
        this.fetchGroup();
        this.editForm.showForm = true;
      },
      clearForm() {
        clearAttrs(this.editForm);
      },
      fetchData() {
        this.listLoading = true;
        listConfig(this.listQuery).then(response => {
          let {data} = response;
          let {list} = data;
          this.tableData = list;
          this.listQuery.currentPage = data.pageSize;
          this.listQuery.total = data.total;
          this.listLoading = false;
        }).catch(() => {
          this.listLoading = false;
        });
      },
      fetchProfile() {
        listAllProfile()
          .then(response => {
            let {data} = response;
            this.profileList = data;
          });
      },
      fetchGroup() {
        listAllGroup()
          .then(response => {
            let {data} = response;
            this.groupList = data;
          })
      }
    },
    created() {
      this.fetchData();
      this.fetchGroup();
      this.fetchProfile();
    }
  }
</script>
<style>
.demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>
