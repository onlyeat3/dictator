<template>
  <div class="app-container">
    <el-header>
        <el-button class="icon-plus" type="primary" @click="appendNode(null)">增加</el-button>
      </el-header>
    <el-main>
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
              @click="() => editNode(data)">
              编辑
            </el-button>
            <el-button
              type="text"
              @click="() => deleteNode(data)">
              删除
            </el-button>
          </span>
        </span>
      </el-tree>
    </el-main>
    <el-dialog :visible.sync="editForm.showForm" title="增加资源" :before-close="closeAndClear" width="500px">
      <el-form v-model="editForm" label-width="80px">
        <el-form-item label="父节点">
          <el-cascader
            :props="parentIdProps"
            :options="resourceData"
            v-model="editForm.parentIdList"
            @change="handleParentIdListChange"
            change-on-select
            >
          </el-cascader>
        </el-form-item>
        <el-form-item label="资源名">
          <el-input v-model="editForm.resourceName" />
        </el-form-item>
        <el-form-item label="资源URI">
          <el-input v-model="editForm.targetUri" />
        </el-form-item>
        <el-form-item label="资源类型">
          <el-radio v-model="editForm.resourceType" label="1" border>菜单</el-radio>
          <el-radio v-model="editForm.resourceType" label="2" border>按钮</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveOrUpdateResource">保存</el-button>
          <el-button @click="closeAndClear">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import {listAll} from "@/api/resource";
  import {saveOrUpdateResource} from "@/api/resource";
  import {deleteResource} from "@/api/resource";
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
        parentIdProps:{
          label:'resourceName',
          value:"id"
        },
        listQuery: {
          pageNum: 1,
          pageSize: 30
        },
        editForm:{
          id: '',
          parentId: '',
          parentIdList: [],
          resourceName:'',
          resourceType:'',
          showForm:false
        }
      }
    },
    methods: {
      clearChildren(arr){
        arr.forEach(element => {
          if(element.children != null && element.children.length === 0){
            element.children = null;
          }else{
            this.clearChildren(element.children);
          }
        });
      },
      fetchData() {
        this.listLoading = true;
        listAll(this.listQuery).then(response => {
          let {data} = response;
          this.clearChildren(data);
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
        if(data){
          this.editForm.parentId = data.id;
        }else{
          this.editForm.parentId = '0';
        }
      },
      editNode(data){
        if(data.id === 0){
          this.$message({
            type: 'info',
            message: `根节点不能操作`
          });
          return;
        }
        this.appendNode(data);
        Object.assign(this.editForm,data);
        this.editForm.resourceType = data.resourceType + '';
      },
      deleteNode(data){
        if(data.id === 0){
          this.$message({
            type: 'info',
            message: `根节点不能操作`
          });
          return;
        }
        this.$confirm('删除后无法恢复, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteResource(data)
          .then(()=>{
            this.fetchData();
          });
        }).catch(() => {
        });
      },
      handleParentIdListChange(current){
        let v = current[current.length - 1];
        if(v === this.editForm.id){
          this.$message({
            type: 'info',
            message: `父节点不能是自己`
          });
          current = [];
          return;
        }
        this.editForm.parentId = v;
      },
      saveOrUpdateResource(){
        saveOrUpdateResource(this.editForm)
        .then(({data})=>{
          this.closeAndClear();
          this.fetchData();
        })
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
