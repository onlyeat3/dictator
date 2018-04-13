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
        <el-form-item label="部署ID">
          <el-input v-model="listQuery.deploymentId"/>
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
      </el-form>
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
            <el-form-item label="部署ID">
              <span>{{props.row.deploymentId}}</span>
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
            <el-form-item label="创建时间">
              <span>{{props.row.createdTime}}</span>
            </el-form-item>
            <el-form-item label="修改时间">
              <span>{{props.row.updatedTime}}</span>
            </el-form-item>
          </el-form>
      </el-table-column>
      <el-table-column prop="appId" label="应用ID" align="center"/>
      <el-table-column prop="deploymentId" label="部署ID" align="center"/>
      <el-table-column prop="profileName" label="环境" align="center"/>
      <el-table-column prop="groupName" label="分组" align="center"/>
      <el-table-column prop="configName" label="配置名" align="center"/>
      <el-table-column prop="configValue" label="属性值" align="center" class-name="long-text"/>
      <el-table-column>
        <template slot-scope="scope">
          <el-button @click="handleRecoveryBtnClick(scope.row)" icon="el-icon-upload2">恢复</el-button>
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
    <el-dialog :visible.sync="recoveryForm.showForm" width="500px">
      <el-form v-model="recoveryForm" label-width="80px">
        <el-form-item label="历史记录">
          <el-select v-model="recoveryForm.configId" placeholder="请选择">
            <el-option
              v-for="item in recoveryForm.historyList"
              :key="item.configValue"
              :label="item.configName"
              :value="item.id">
              <span style="float: left">{{ item.configName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.configValue }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onRecoverySubmit">立即创建</el-button>
          <el-button @click="recoveryForm.showForm = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import '@/styles/config.scss';
  import '@/styles/common.scss';
  import {listAllProfile} from "@/api/profile";
  import {listAllGroup} from "@/api/group";
  import {listConfigHistory, recovery} from "@/api/configHistory";
  import {MessageBox} from 'element-ui';

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
        pageData: {
          currentPage: 0,
          total: 0,
        },
        recoveryForm: {
          showForm: false,
          configId: '',
          historyList: []
        },
        profileList: [],
        groupList: [],
      }
    },
    methods: {
      onQueryFormSubmit() {
        this.fetchData();
      },
      onRecoverySubmit() {
      },
      onPageNumChange(currentPage) {
        this.listQuery.pageNum = currentPage;
        this.fetchData();
      },
      onPageSizeChange(pageSize) {
        this.listQuery.pageSize = pageSize;
        this.fetchData();
      },
      handleRecoveryBtnClick(row) {
        MessageBox.confirm("配置恢复并不能保证一定重新有效，可能会因为相关数据被删除或修改而无法生效，确定恢复？","提示",{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(()=>{
          recovery(row.id)
            .then(() => {
              this.fetchData();
            })
            .catch(() => {

            })
        });
      },
      fetchData() {
        this.listLoading = true;
        listConfigHistory(this.listQuery)
          .then(response => {
            let {data} = response;
            let {list} = data;
            this.tableData = list;
            this.listQuery.pageNum = data.pageNum;
            this.listQuery.pageSize = data.pageSize;
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
          })
      },
      fetchGroup() {
        listAllGroup()
          .then(response => {
            let {data} = response;
            this.groupList = data;
          })
      },
    },
    created() {
      this.fetchGroup();
      this.fetchProfile();
      this.fetchData();
    }
  }
</script>
