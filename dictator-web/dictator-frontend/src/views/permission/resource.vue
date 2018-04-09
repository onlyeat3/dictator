<template>
  <div class="app-container">
    <el-tree
      :data="resourceData"
      show-checkbox
      node-key="id"
      :props="treeProps"
      default-expand-all
      :expand-on-click-node="false"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            @click="() => appendNode(data)">
            增加子节点
          </el-button>
          <el-button
            type="text"
            @click="() => deleteNode(data)">
            删除
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :visible.sync="editForm.showForm" :title="editForm.parentResourceName" width="500px">
      <el-form v-model="editForm" label-width="80px">
        <el-form-item label="资源名">
          <el-input v-model="editForm.resourceName" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary">保存</el-button>
          <el-button @click="closeAndClear">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {listAll} from "@/api/resource";
  import {clearAttrs} from "@/utils";

  export default {
    name: 'resourceTable',
    data() {
      return {
        listLoading: true,
        resourceData: [],
        treeProps:{
          label:'resourceName'
        },
        listQuery: {
          pageNum: 1,
          pageSize: 30
        },
        editForm:{
          resourceName:'',
          resourceType:'',
          parentResourceName:'',
          showForm:false
        }
      }
    },
    methods: {
      fetchData() {
        this.listLoading = true;
        listAll(this.listQuery).then(response => {
          let {data} = response;
          this.resourceData = data;
          this.listQuery.currentPage = data.pageSize;
          this.listQuery.total = data.total;
          this.listLoading = false;
        }).catch(() => {
          this.listLoading = false;
        });
      },
      appendNode(data){
        this.editForm.showForm = true;
        this.editForm.parentResourceName = data.resourceName + '的子节点';
      },
      deleteNode(data){
        console.log(data);
      },
      closeAndClear(){
        this.editForm.showForm = false;
        clearAttrs(this.editForm);
      }
    },
    created() {
      this.fetchData();
    }
  }
</script>
<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
