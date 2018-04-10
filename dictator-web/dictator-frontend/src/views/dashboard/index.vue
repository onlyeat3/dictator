<template>
  <div class="dashboard-container">
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
