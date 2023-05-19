<template>
<div :style='{"width":"calc(100% - 40px)","padding":"30px 20px 40px","margin":"20px auto 0","position":"relative","background":"rgba(255,255,255,.3)"}'>
    <el-form
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="视频编号" prop="shipinbianhao">
              <el-input v-model="ruleForm.shipinbianhao" placeholder="视频编号" readonly></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="视频名称" prop="shipinmingcheng">
            <el-input v-model="ruleForm.shipinmingcheng" 
                placeholder="视频名称" clearable ></el-input>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}'  label="视频分类" prop="shipinfenlei">
            <el-select v-model="ruleForm.shipinfenlei" placeholder="请选择视频分类"  >
              <el-option
                  v-for="(item,index) in shipinfenleiOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="视频封面" v-if="type!='cross' || (type=='cross' && !ro.shipinfengmian)" prop="shipinfengmian">
            <file-upload
            tip="点击上传视频封面"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.shipinfengmian?ruleForm.shipinfengmian:''"
            @change="shipinfengmianUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' class="upload" v-else label="视频封面" prop="shipinfengmian">
                <img v-if="ruleForm.shipinfengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.shipinfengmian.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.shipinfengmian.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="视频" prop="shipin">
            <file-upload
            tip="点击上传视频"
            action="file/upload"
            :limit="1"
            :multiple="true"
            :fileUrls="ruleForm.shipin?ruleForm.shipin:''"
            @change="shipinUploadChange"
            ></file-upload>
          </el-form-item> 
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="发布日期" prop="faburiqi" >
              <el-date-picker
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd"
                  v-model="ruleForm.faburiqi" 
                  type="date"
                  placeholder="发布日期">
              </el-date-picker> 
          </el-form-item>
          <el-form-item :style='{"border":"0px solid #dfdfdf","padding":"10px","boxShadow":"0px 0px 0px #eee","margin":"0 0 8px 0","borderRadius":"8px","background":"none"}' label="视频介绍" prop="shipinjieshao">
            <editor 
                :style='{"minHeight":"200px","padding":"0","margin":"0","borderColor":"#ccc","backgroundColor":"#fff","borderRadius":"0","borderWidth":"0px","width":"100%","borderStyle":"solid","height":"auto"}'
                v-model="ruleForm.shipinjieshao" 
                class="editor" 
                action="file/upload">
            </editor>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"20px 0 20px 0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","boxShadow":"0px 0px 0px #ccc","margin":"0 20px 0 0","color":"#fff","outline":"none","borderRadius":"0px","background":"linear-gradient(320deg, rgba(48,134,185,1) 0%, rgba(197,230,250,1) 80%, rgba(48,134,185,1) 100%),#3086b9","width":"110px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"0px solid #bbb","cursor":"pointer","padding":"0","boxShadow":"0px 0px 0px #eee","margin":"0","color":"#333","outline":"none","borderRadius":"0px","background":"linear-gradient(320deg, rgba(204,204,204,1) 0%, rgba(255,255,255,1) 80%, rgba(204,204,204,1) 100%),#999","width":"110px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
</div>
</template>

<script>
  export default {
    data() {
      return {
        id: '',
        baseUrl: '',
        ro:{
            shipinbianhao : false,
            shipinmingcheng : false,
            shipinfenlei : false,
            shipinfengmian : false,
            shipin : false,
            faburiqi : false,
            shipinjieshao : false,
            clicktime : false,
            clicknum : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          shipinbianhao: this.getUUID(),
          shipinmingcheng: '',
          shipinfenlei: '',
          shipinfengmian: '',
          shipin: '',
          faburiqi: '',
          shipinjieshao: '',
          clicktime: '',
          clicknum: '',
        },
        shipinfenleiOptions: [],
        rules: {
          shipinbianhao: [
          ],
          shipinmingcheng: [
          ],
          shipinfenlei: [
          ],
          shipinfengmian: [
          ],
          shipin: [
          ],
          faburiqi: [
          ],
          shipinjieshao: [
          ],
          clicktime: [
          ],
          clicknum: [
            { validator: this.$validate.isIntNumer, trigger: 'blur' },
          ],
        },
      };
    },
    computed: {



    },
    created() {
	  //this.bg();
      let type = this.$route.query.type ? this.$route.query.type : '';
      this.init(type);
      this.baseUrl = this.$config.baseUrl;
      this.ruleForm.faburiqi = this.getCurDate()
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='shipinbianhao'){
              this.ruleForm.shipinbianhao = obj[o];
              this.ro.shipinbianhao = true;
              continue;
            }
            if(o=='shipinmingcheng'){
              this.ruleForm.shipinmingcheng = obj[o];
              this.ro.shipinmingcheng = true;
              continue;
            }
            if(o=='shipinfenlei'){
              this.ruleForm.shipinfenlei = obj[o];
              this.ro.shipinfenlei = true;
              continue;
            }
            if(o=='shipinfengmian'){
              this.ruleForm.shipinfengmian = obj[o].split(",")[0];
              this.ro.shipinfengmian = true;
              continue;
            }
            if(o=='shipin'){
              this.ruleForm.shipin = obj[o];
              this.ro.shipin = true;
              continue;
            }
            if(o=='faburiqi'){
              this.ruleForm.faburiqi = obj[o];
              this.ro.faburiqi = true;
              continue;
            }
            if(o=='shipinjieshao'){
              this.ruleForm.shipinjieshao = obj[o];
              this.ro.shipinjieshao = true;
              continue;
            }
            if(o=='clicktime'){
              this.ruleForm.clicktime = obj[o];
              this.ro.clicktime = true;
              continue;
            }
            if(o=='clicknum'){
              this.ruleForm.clicknum = obj[o];
              this.ro.clicknum = true;
              continue;
            }
          }
        }
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
          }
        });
        this.$http.get('option/shipinfenlei/shipinfenlei', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.shipinfenleiOptions = res.data.data;
          }
        });
      },

    // 多级联动参数
      // 多级联动参数
      info(id) {
        this.$http.get('dongmanshipin/detail/${id}', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
      },
      // 提交
      onSubmit() {

        //更新跨表属性
        var crossuserid;
        var crossrefid;
        var crossoptnum;
        this.$refs["ruleForm"].validate(valid => {
          if(valid) {
            if(this.type=='cross'){
                 var statusColumnName = localStorage.getItem('statusColumnName');
                 var statusColumnValue = localStorage.getItem('statusColumnValue');
                 if(statusColumnName && statusColumnName!='') {
                     var obj = JSON.parse(localStorage.getItem('crossObj'));
                     if(!statusColumnName.startsWith("[")) {
                         for (var o in obj){
                             if(o==statusColumnName){
                                 obj[o] = statusColumnValue;
                             }
                         }
                         var table = localStorage.getItem('crossTable');
                         this.$http.post(table+'/update', obj).then(res => {});
                     } else {
                            crossuserid=Number(localStorage.getItem('userid'));
                            crossrefid=obj['id'];
                            crossoptnum=localStorage.getItem('statusColumnName');
                            crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                     }
                 }
            }
            if(crossrefid && crossuserid) {
                 this.ruleForm.crossuserid=crossuserid;
                 this.ruleForm.crossrefid=crossrefid;
                 var params = {
                     page: 1,
                     limit: 10,
                     crossuserid:crossuserid,
                     crossrefid:crossrefid,
                 }
                 this.$http.get('dongmanshipin/list', {
                  params: params
                 }).then(res => {
                     if(res.data.data.total>=crossoptnum) {
                         this.$message({
                          message: localStorage.getItem('tips'),
                          type: 'success',
                          duration: 1500,
                         });
                          return false;
                     } else {
                         // 跨表计算


                          this.$http.post('dongmanshipin/add', this.ruleForm).then(res => {
                              if (res.data.code == 0) {
                                  this.$message({
                                      message: '操作成功',
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                          this.$router.go(-1);
                                      }
                                  });
                              } else {
                                  this.$message({
                                      message: res.data.msg,
                                      type: 'error',
                                      duration: 1500
                                  });
                              }
                          });
                     }
                 });
             } else {


                  this.$http.post('dongmanshipin/add', this.ruleForm).then(res => {
                     if (res.data.code == 0) {
                          this.$message({
                              message: '操作成功',
                              type: 'success',
                              duration: 1500,
                              onClose: () => {
                                  this.$router.go(-1);
                              }
                          });
                      } else {
                          this.$message({
                              message: res.data.msg,
                              type: 'error',
                              duration: 1500
                          });
                      }
                  });
             }
          }
        });
      },
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
      // 返回
      back() {
        this.$router.go(-1);
      },
      shipinfengmianUploadChange(fileUrls) {
          this.ruleForm.shipinfengmian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
      shipinUploadChange(fileUrls) {
          this.ruleForm.shipin = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  padding: 0 10px 0 0;
	  color: #666;
	  font-weight: 500;
	  width: 80px;
	  font-size: 14px;
	  line-height: 40px;
	  text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 80px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  border-radius: 4px;
	  padding: 0 12px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 400px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  border-radius: 4px;
	  padding: 0 10px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  border-radius: 4px;
	  padding: 0 10px 0 30px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 14px;
	  border-color: #9dcde9;
	  border-width: 0 0 2px;
	  border-style: dotted;
	  height: 40px;
	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  cursor: pointer;
	  border: 2px dotted #9dcde9;
	  border-radius: 6px;
	  color: #9dcde9;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 200px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: auto;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  border: 2px dotted #9dcde9;
	  border-radius: 4px;
	  padding: 12px;
	  outline: none;
	  color: #666;
	  background: linear-gradient(320deg, rgba(204,204,204,.0) 0%, rgba(255,255,255,.9) 80%, rgba(204,204,204,.0) 100%);
	  width: 400px;
	  font-size: 14px;
	  min-height: 120px;
	}
</style>
