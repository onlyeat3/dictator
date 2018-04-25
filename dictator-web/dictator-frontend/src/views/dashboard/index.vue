<template>
  <div class="dashboard-container">
    <div>这是一个预留的仪表盘，准备放概要统计数据</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'dashboard',
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'resourceList'
    ])
  },
  created() {
    if (!this.resourceList.includes('admin')) {
      this.currentRole = 'editorDashboard'
    }
  }
}
</script>
