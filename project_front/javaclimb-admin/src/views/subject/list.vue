<template>
    <div class="app-container">
        <el-input
            placeholder="输入关键字进行过滤"
            v-model="filterText">
        </el-input>

        <el-tree
            class="filter-tree"
            :data="subjectList"
            :props="defaultProps"
            default-expand-all
            :filter-node-method="filterNode"
            ref="tree">
        </el-tree>
    </div>
</template>
<script>
  import subjectApi from '@/api/subject'
  export default {
    name: 'SubjectList',

    data() {

      return {
          //过滤文本
        filterText: '',
        //数据列表
        subjectList: [],
        defaultProps: {
          children: 'children',
          label: 'title'
        }
      };
    },

    watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },

    created(){
        this.fetchNodeList()
    },

    methods: {
      filterNode(value, data) {
        if (!value) return true;
        return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1;
      },

      fetchNodeList() {
          subjectApi.getNestedTreeList().then(resp => {
              this.subjectList = resp.data.items
          })
      }
    },

  };
</script>