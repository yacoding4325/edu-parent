<template>
  <div class="app-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
            <el-select v-model="searchObj.type" clearable placeholder="请选择">
                <el-option label="学员登录数统计" value="login_num"/>
                <el-option label="学员注册数统计" value="register_num"/>
                <el-option label="课程播放数统计" value="video_view_num"/>
                <el-option label="每日课程数统计" value="course_num"/>                
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-date-picker v-model="searchObj.begin" type="date" placeholder="选择开始日期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-form-item>
            <el-date-picker v-model="searchObj.end" type="date" placeholder="选择截止日期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <el-button type="primary" @click="showChart()">查询</el-button>
      </el-form>
      <div class="chart-container">
          <div id="chart" class="chart" style="height:500px;width:100%" />
      </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import statisticsApi from '@/api/statistics'
export default {
    data() {
        return {
            searchObj: {
                type: '',
                begin: '',
                end: ''
            },
            chart: null,
            xData: [],
            yData: []
        }        
    },
    methods: {

        showChart(){
            this.initChartData()            
        },
        //准备图表数据
        initChartData() {
            statisticsApi.showChart(this.searchObj).then(response => {
                this.yData = response.data.dataList
                this.xData = response.data.dateList
                this.setChart()
            })
        },

        setChart() {
            this.chart = echarts.init(document.getElementById('chart'));
            var option;

            option = {
            xAxis: {
                type: 'category',
                data: this.xData
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                data: this.yData,
                type: 'line'
                }
            ]
            };

            this.chart.setOption(option);
        }
    }
}
</script>

<style>

</style>