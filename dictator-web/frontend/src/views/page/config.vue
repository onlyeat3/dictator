<template>
  <div class="app-container">
    <el-table :data="data" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit highlight-current-row>
      <el-table-column align="center" label='id' prop="id" width="95">
      </el-table-column>
      <el-table-column label="应用名" prop="appId">
      </el-table-column>
      <el-table-column label="环境" prop="profile">
      </el-table-column>
      <el-table-column label="分组" prop="groupName">
      </el-table-column>
      <el-table-column label="版本" prop="version">
      </el-table-column>
      <el-table-column label="部署代码" prop="deploymentId">
      </el-table-column>
      <el-table-column label="配置名" prop="configName">
      </el-table-column>
      <el-table-column label="配置值" prop="configValue">
      </el-table-column>
      <el-table-column label="创建时间" prop="createdTime">
      </el-table-column>
      <el-table-column label="最后修改时间" prop="updatedTime">
      </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination 
            :data="page"
            :page-size="10"
            layout="total, prev, pager, next"
            :total="page.total">
        </el-pagination>
    </div>
  </div>
</template>

<script>
import { list } from '@/api/config'

export default {
  data() {
    return {
      data: null,
      listLoading: true,
      page:{
          total:0,
          currentPage:0
      }
    }
  },
  filters: {
    // statusFilter(status) {
    //   const statusMap = {
    //     published: 'success',
    //     draft: 'gray',
    //     deleted: 'danger'
    //   }
    //   return statusMap[status]
    // }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        this.data = response.data.list
        this.page.currentPage = response.data.pageNum
        this.page.total = response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.app-container {
    .pagination{
        margin-top:10px;
    }
}
</style>
