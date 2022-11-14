<template>
    <div class="app-container">
        <div style="margin-bottom:10px;">
            <el-button type="danger" size="mini" @click="batchRemove()">批量删除</el-button>
        </div>
        <!-- 表格 -->
        <el-table
            :data="list"
            border
            stripe
            @selection-change="handleSelectionChange">
            <el-table-column type="selection"/>
            <el-table-column label="ID" width="50">
                <!-- 使用连续的序号 -->
                <template slot-scope="scope">
                {{ (currentPage - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>
            <el-table-column prop="title" label="推荐位名称"/>
            
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="toUpdate(scope.row.id)">更改</el-button>
                <el-button type="danger" size="mini" @click="removeById(scope.row)">删除</el-button>         
                </template>
            </el-table-column>
        </el-table>
        <!--分页组件-->
        <el-pagination
            :current-page="currentPage"
            :page-size="limit"
            :total="total"
            :page-sizes="[5, 10, 15, 20]"
            style="padding: 12px 8px; text-align: center"
            layout="sizes, prev, pager, next, jumper, ->, total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
    </div>
</template>
<script>
import adtypeApi from '@/api/adType'

export default {
    data() {            //定义数据
        return {
            list: [],   // 广告位列表数据
            total: 0,   // 总记录数
            currentPage: 1, //当前页
            limit: 10,      //每页记录数
            multipleSelection: []   //选中的记录列表
        }
    },
    created(){          //当页面加载时获取数据
        this.getAdTypeList();
    },
    methods: {
        getAdTypeList(){         //调用api里面的函数  
            adtypeApi.pageList(this.currentPage,this.limit).then(resp => {
                this.list = resp.data.rows
                this.total = resp.data.total          
            })
        },
        //改变每页条数
        handleSizeChange(size){
            this.limit = size;
            this.getAdTypeList();
        },
        // 改变页码
        handleCurrentChange(page){
            this.currentPage = page;
            this.getAdTypeList();
        },
        // 删除单个广告位
        removeById(data){
            this.$confirm('此操作将永久删除'+data.name+', 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    //调用删除的api
                    adtypeApi.deleteAdTypeById(data.id).then(resp => {
                        this.getAdTypeList();
                        this.$message({
                            type: 'success',
                            message: resp.message
                        });
                    })
                    
                });
        },
        //多选点击事件
        handleSelectionChange(selection){
            this.multipleSelection = selection
            console.log(selection)
        },
        //批量删除
        batchRemove(){
            //判断是否选中数据
            if(this.multipleSelection.length === 0){
                this.$message({
                    type: 'warning',
                    message: '请选择数据'
                })
                return
            }
            this.$confirm('此操作将永久删除这些数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(() => {
                    let idList = []
                    this.multipleSelection.forEach(element => {
                        idList.push(element.id)
                    });
                    // console.log(idList)
                    //调用删除的api
                    adtypeApi.batchRemove(idList).then(resp => {
                        this.getAdTypeList();
                    })
                    this.$message({
                        type: 'success',
                        message: resp.message
                    });
                });
        },

        //去更改页面
        toUpdate(id) {
            this.$router.push({path:`/ad/type-edit/${id}`})
        }
    }
}
</script>
