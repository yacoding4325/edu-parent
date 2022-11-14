<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="javascript:void(0);" @click="searchOne('')">全部</a>
                </li>
                <li v-for="(item,index) in subjectNestedList" v-bind:key="index" :class="{active:oneIndex == index}">
                  <a :title="item.title" href="javascript:void(0);" @click="searchOne(item.id,index)">{{item.title}}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(item,index) in subSubjectList" v-bind:key="index" :class="{active:twoIndex == index}">
                  <a :title="item.title" href="javascript:void(0);" @click="searchTwo(item.id,index)">{{item.title}}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">          
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="关注度" href="javascript:void(0);" @click="searchByOrder('1','','')">关注度&nbsp;↓</a>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchByOrder('','1','')">最新&nbsp;↓</a>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchByOrder('','','1')">价格&nbsp;↓</a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list" v-if="data.total>0">
            <ul class="of" id="bna">
              <li v-for="item in data.items" :key="item.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="item.cover" class="img-responsive" alt="item.title">
                    <div class="cc-mask">
                      <a :href="'/course/'+item.id" :title="item.title" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/course/'+item.id" :title="item.title" class="course-title fsize18 c-333">{{item.title}}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green" v-if="Number(item.price) === 0">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{item.viewCount}}人学习</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a :class="{undisable: !data.hasPrevious}" href="#" title="首页" @click="gotoPage(1)">首</a>
            <a :class="{undisable: !data.hasPrevious}" href="#" title="前一页" @click="gotoPage(data.current-1)">&lt;</a>
            <a v-for="page in data.pages" :key="page" :class="{current: data.current == page,undisable: data.current == page}"
              :title="'第'+page+'页'" href="#" @click="gotoPage(page)">{{page}}</a>
            <a v-if="data.hasNext" href="#" title="后一页" @click="gotoPage(data.current+1)">&gt;</a>
            <a v-else href="#" title="后一页">&gt;</a>
            <a :class="{undisable: !data.hasNext}" href="#" title="末页" @click="gotoPage(data.pages)">末</a>
            <div class="clear"></div>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import course from '@/api/course'
export default {
  data() {
    return {
      page: 1,    //当前所在页，默认第一页
      data: {},   //课程列表
      subjectNestedList: [],  //一级分类列表
      subSubjectList: [],     //二级分类列表
      searchObj: {},          //查询条件对象
      oneIndex: -1,           //一级分类被点击的索引
      twoIndex: -1,           //二级分类被点击的索引
      buyCountSort: "",       //按销量排序
      gmtCreateSort: "",      //按最新排序
      priceSort: ""           //按价格排序
    }
  },

  created() {
    //获取课程列表
    this.initCourse()
    //获取分类
    this.initSubject()
  },

  methods: {
    //获取课程列表
    initCourse(){
      course.getPageList(1,8,this.searchObj).then(response => {
        this.data = response.data
      })
    },

    //获取一级分类
    initSubject(){
      course.getNestedTreeList().then(response => {
        this.subjectNestedList = response.data.items
      })
    },

    //跳转页面
    gotoPage(page) {
      this.page = page
      course.getPageList(page,8,this.searchObj).then(response => {
        this.data = response.data
      })
    },

    //点击一级分类，显示对应的二级分类，并且执行查询
    searchOne(subjectParentId,index) {
      this.page = 1
      this.oneIndex = index
      this.twoIndex = -1
      this.searchObj.subjectId = ""
      this.subSubjectList = []

      this.searchObj.subjectParentId = subjectParentId
      this.gotoPage(this.page)
      for(let i=0;i<this.subjectNestedList.length;i++){
        if (this.subjectNestedList[i].id === subjectParentId){
          this.subSubjectList = this.subjectNestedList[i].children
        }
      }
    },

    //点击二级分类，执行查询
    searchTwo(subjectId,index) {
      this.page = 1
      this.twoIndex = index
      this.searchObj.subjectId = subjectId
      this.gotoPage(this.page)      
    },

    //按关注度、最新时间、价格倒序排序
    searchByOrder(buyCountSort,gmtCreateSort,priceSort){
      this.page = 1
      this.buyCountSort = buyCountSort
      this.gmtCreateSort = gmtCreateSort
      this.priceSort = priceSort
      this.searchObj.buyCountSort = buyCountSort
      this.searchObj.gmtCreateSort = gmtCreateSort
      this.searchObj.priceSort = priceSort
      this.gotoPage(this.page)      
    }
  }
};
</script>