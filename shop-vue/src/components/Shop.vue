<template>
  <el-main>
    <el-form :inline="true" :model="queryFrom" class="demo-form-inline">
      <el-form-item label="商品名称">
        <el-input v-model="queryFrom.name" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="商品状态">
        <el-input v-model="queryFrom.status" placeholder=""></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <el-button type="text" @click="add">添加</el-button>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="id"
        label="#"
        width="120">
      </el-table-column>
      <el-table-column
        prop="name"
        label="商品名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="num"
        label="数量"
        width="120">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="120">
      </el-table-column>
      <el-table-column
        label="照片"
        width="120">
        <template slot-scope="scope">
          <img :src="scope.row.img" style="height: 40px;">
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="120">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="出厂日期"
        width="120"
        >
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" @click="detail(scope.row.id)">查看</el-button>
          <el-button type="text" @click="del(scope.row.id)">删除</el-button>
          <el-button type="text" @click="update(scope.row.id)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      layout="prev, pager, next"
      @current-change="gotoPage"
      :page-count="pages">
    </el-pagination>


<!--添加编辑-->



    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :inline="true" :model="saveFrom" class="demo-form-inline">
        <el-form-item label="商品名称">
          <el-input v-model="saveFrom.name" placeholder=""></el-input>
        </el-form-item><br>
        <el-form-item label="商品数量">
          <el-input v-model="saveFrom.num" placeholder=""></el-input>
        </el-form-item><br>
        <el-form-item label="商品价格">
          <el-input v-model="saveFrom.price" placeholder=""></el-input>
        </el-form-item><br>
        <el-form-item label="商品图片">
          <el-upload
            class="avatar-uploader"
            action="http://127.0.0.1/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess">
            <img v-if="saveFrom.img" :src="saveFrom.img" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item><br>
        <el-form-item label="商品状态">
          <el-input v-model="saveFrom.status" placeholder=""></el-input>
        </el-form-item><br>
        <el-form-item label="出厂日期">
          <el-date-picker
            v-model="saveFrom.createTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
      </el-form>

    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
    </el-dialog>

<!--查看-->
    <el-dialog
      title="提示"
      :visible.sync="detaildialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :inline="true" :model="detailData" class="demo-form-inline">
        <el-form-item label="商品名称">
          {{detailData.name}}
        </el-form-item><br>
        <el-form-item label="商品数量">
          {{detailData.num}}
        </el-form-item><br>
        <el-form-item label="商品价格">
          {{detailData.price}}
        </el-form-item><br>
        <el-form-item label="商品图片">
            <img :src="detailData.img" style="height: 50px;">
        </el-form-item><br>
        <el-form-item label="商品状态">
          {{detailData.status}}
        </el-form-item><br>
        <el-form-item label="出厂日期">
          {{detailData.createTime}}
        </el-form-item>
      </el-form>

      <el-button @click="detaildialogVisible = false">取 消</el-button>
    </el-dialog>







  </el-main>
</template>

<script>
  const axios=require("axios");
  const basePath="http://127.0.0.1/";
    export default {
        name: "Shop",
        data(){
            return{
                detailData:{},
                saveFrom:{},
                tableData:[],
                queryFrom:{
                    pageNo:1,
                    pageSize:2
                },
                pages:0,
                dialogVisible:false,
                detaildialogVisible:false


            }
        },
        methods:{
            handleSelectionChange(rows){
              let ids=null;
              rows.forEach(row=>{
                  if(ids==null){
                      ids=row.id
                  }else{
                      ids=ids+","+row.id
                  }
              })
                this.ids=ids;
            },
            del(id){
              let self=this;
              this.$confirm("确认删除吗?","提示",{
                  confirmButtonText:"确认",
                  cancelButtonText:"取消",
                  type:"waring"
              }).then(()=>{
                  axios({
                      url:basePath+"shop/delByIds",
                      method:"get",
                      params:{ids:id}
                  }).then(res=>{
                      self.$message({
                          message:"成功",
                          type:"success"
                      })
                      self.queryFrom.pageNo=1;
                      self.getList()
                  })
              }).catch(()=>{
                  this.$message({
                      message:"失败",
                      type:"info"
                  })
                })
            },
            handleAvatarSuccess(res){
              this.saveFrom.img=res;
            },
            handleClose(){
                this.dialogVisible=false;
            },
            save(){
                const self=this;
                this.dialogVisible=false;
                axios({
                    url:basePath+"shop/insert",
                    method:"post",
                    data:this.saveFrom
                }).then(res=>{
                    self.saveFrom=res.data;
                    self.getList()
                })
            },
            add(){
                this.dialogVisible=true;
                this.saveFrom={}
            },
            detail(id){
                this.detaildialogVisible=true;
                const self=this;
                axios({
                    url:basePath+"shop/detail",
                    method:"get",
                    params:{id:id}
                }).then(res=>{
                    self.detailData=res.data;
                })
            },
            update(id){
                const self=this;
                this.dialogVisible=true;
                axios({
                    url:basePath+"shop/detail",
                    method:"get",
                    params:{id:id}
                }).then(res=>{
                    self.saveFrom=res.data;
                    self.getList()
                })
            },
            gotoPage(pageNo){
                this.queryFrom.pageNo=pageNo;
                this.getList()
            },
            onSubmit(){
                this.queryFrom.pageNo=1;
                this.getList()
            },
            getList(){
                const self=this;
                axios({
                    url:basePath+"shop/list",
                    method:"get",
                    params:this.queryFrom
                }).then(res=>{
                    self.tableData=res.data.list;
                    self.pages=res.data.pages;

                })
            }
        },
        created() {
            this.getList()
        }
    }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
