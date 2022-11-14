<template>
  <div class="app-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="日期">
            <el-date-picker v-model="day" type="date" placeholder="选择要统计的日期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-button type="primary" @click="generateData()">生成</el-button>
      </el-form>
  </div>
</template>

<script>
import statisticsApi from '@/api/statistics'
export default {
    data() {
        return {
            day: '',
        }        
    },
    methods: {
        generateData() {
            statisticsApi.createStatistics(this.day).then(response => {
                this.$message.success(response.message)
            })
        }
    }
}
</script>

<style>

</style>