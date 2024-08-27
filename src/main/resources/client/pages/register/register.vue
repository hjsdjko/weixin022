<template>
<view class="content">
	<view class="box" :style='{"minHeight":"100vh","width":"100%","padding":"120rpx 44rpx 244rpx 44rpx","background":"url(http://clfile.zggen.cn/20231130/51b25124d35a4e059579e6c84f9948aa.jpg) no-repeat center top / 100%,#ffffff","height":"100%"}'>
		<view :style='{"padding":"48rpx 48rpx 48rpx 48rpx","boxShadow":"0px 0px 12rpx #dddddd","margin":"0 0 244rpx 0","borderRadius":"4rpx","background":"#ffffff","display":"block","width":"100%","position":"relative","height":"auto"}'>
			<image :style='{"width":"160rpx","margin":"0 0 40rpx 0","borderRadius":"100%","objectFit":"cover","display":"none","height":"80rpx"}' src="http://clfile.zggen.cn/20231027/f76234bafa534579beeb9a486c2d2df2.jpg" mode="aspectFill"></image>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.yonghuzhanghao" type="text" class="uni-input" name="" placeholder="用户账号" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.yonghumima" type="password" class="uni-input" name="" placeholder="用户密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.yonghumima2" type="password" class="uni-input" name="" placeholder="确认用户密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.yonghuxingming" type="text" class="uni-input" name="" placeholder="用户姓名" />
			</view>
            <view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" @tap="yonghutouxiangTap" class="">
                <view>请上传头像</view>
                <image :style='{"width":"160rpx","borderRadius":"0px","objectFit":"contain","display":"block","height":"120rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
                <image :style='{"width":"160rpx","borderRadius":"0px","objectFit":"contain","display":"block","height":"120rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
			<picker :style='{"border":"2rpx solid #00000010","padding":"0 20rpx","margin":"0 0 24rpx 0","borderRadius":"0px","background":"#ffffff","width":"100%","height":"auto"}' v-if="tableName=='yonghu'"  @change="yonghuxingbieChange" :value="yonghuxingbieIndex" :range="yonghuxingbieOptions">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#999999"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
			</picker>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.shoujihaoma" type="text" class="uni-input" name="" placeholder="手机号码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='yonghu'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.nianling" type="text" class="uni-input" name="" placeholder="年龄" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.jiaoshigonghao" type="text" class="uni-input" name="" placeholder="教师工号" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.mima" type="password" class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.jiaoshixingming" type="text" class="uni-input" name="" placeholder="教师姓名" />
			</view>
            <view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" @tap="xinlijiaoshizhaopianTap" class="">
                <view>请上传照片</view>
                <image :style='{"width":"160rpx","borderRadius":"0px","objectFit":"contain","display":"block","height":"120rpx"}' v-if="ruleForm.zhaopian" class="avator" :src="baseUrl+ruleForm.zhaopian" mode=""></image>
                <image :style='{"width":"160rpx","borderRadius":"0px","objectFit":"contain","display":"block","height":"120rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
			<picker :style='{"border":"2rpx solid #00000010","padding":"0 20rpx","margin":"0 0 24rpx 0","borderRadius":"0px","background":"#ffffff","width":"100%","height":"auto"}' v-if="tableName=='xinlijiaoshi'"  @change="xinlijiaoshixingbieChange" :value="xinlijiaoshixingbieIndex" :range="xinlijiaoshixingbieOptions">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#999999"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
			</picker>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.lianxidianhua" type="text" class="uni-input" name="" placeholder="联系电话" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.zhuanyezizhi" type="text" class="uni-input" name="" placeholder="专业资质" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.keyueshijian" type="text" class="uni-input" name="" placeholder="可约时间" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.jingyan" type="text" class="uni-input" name="" placeholder="经验" />
			</view>
			<view :style='{"width":"100%","margin":"0 0 24rpx 0","height":"auto"}' v-if="tableName=='xinlijiaoshi'" class="uni-form-item uni-column">
				<input :style='{"border":"2rpx solid #00000010","padding":"0px 24rpx 0 24rpx","margin":"0 0 0px 0","color":"#999999","borderRadius":"0px","background":"#ffffff","width":"100%","fontSize":"28rpx","height":"80rpx"}'  v-model="ruleForm.storeupnum" type="text" class="uni-input" name="" placeholder="收藏数量" />
			</view>
			<button :style='{"border":"2rpx solid #dddddd","padding":"0 0 0 0","margin":"0 0 24rpx 0","color":"#333","borderRadius":"0px","background":"#fff000","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx"}' class="btn-submit" @tap="register" type="primary">注册</button>
		</view>
	</view>
</view>
</template>

<script>
    import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
                yonghuxingbieOptions: [],
                yonghuxingbieIndex: 0,
                xinlijiaoshixingbieOptions: [],
                xinlijiaoshixingbieIndex: 0,
				ruleForm: {
                yonghuzhanghao: '',
                yonghumima: '',
                yonghuxingming: '',
                touxiang: '',
                xingbie: '',
                shoujihaoma: '',
                nianling: '',
                jiaoshigonghao: '',
                mima: '',
                jiaoshixingming: '',
                zhaopian: '',
                xingbie: '',
                lianxidianhua: '',
                zhuanyezizhi: '',
                gerenjianjie: '',
                keyueshijian: '',
                jingyan: '',
                storeupnum: '0',
				},
				tableName:""
			}
		},
        components: {
            multipleSelect
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
            this.tableName = table;

			// 自定义下拉框值
			if(this.tableName=='yonghu'){
                this.yonghuxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.yonghuxingbieOptions[0]
			}
			// 自定义下拉框值
			if(this.tableName=='xinlijiaoshi'){
                this.xinlijiaoshixingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.xinlijiaoshixingbieOptions[0]
			}
            if(`xinlijiaoshi` == this.tableName){
                this.ruleForm.storeupnum='0'
            }
			
			this.styleChange()
		},
		methods: {

            yonghutouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'file/' + res.file;
                    _this.$forceUpdate();
                });
            },
            // 下拉变化
            yonghuxingbieChange(e) {
                    this.yonghuxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.yonghuxingbieOptions[this.yonghuxingbieIndex]
            },
            xinlijiaoshizhaopianTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.zhaopian = 'file/' + res.file;
                    _this.$forceUpdate();
                });
            },
            // 下拉变化
            xinlijiaoshixingbieChange(e) {
                    this.xinlijiaoshixingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.xinlijiaoshixingbieOptions[this.xinlijiaoshixingbieIndex]
            },
            toggleTab(str) {
                this.$refs[str].show();
            },

			styleChange() {
				this.$nextTick(()=>{
					document.querySelectorAll('.radioText').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"color":"#666666","display":"inline-block"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.radioTextActive').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"color":"#333","display":"inline-block"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.checkText').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"color":"#999999"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.checkTextActive').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"color":"#666"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.uni-radio-input').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"border":"2rpx solid #eeeeee","outlineColor":"#666666","background":"#ffffff","display":"inline-block"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.uni-radio-input-checked').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"border":"2rpx solid #ffb400","textAlign":"center","background":"#fff000","display":"inline-block"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.uni-checkbox-input').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"border":"2rpx solid #eeeeee","borderColor":"#666666","borderRadius":"40rpx","background":"#ffffff"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
					document.querySelectorAll('.uni-checkbox-input-checked').forEach(el => {
					  el.removeAttribute('style')
					  const icon = {"borderColor":"#ffb400","color":"#333","borderRadius":"40rpx","background":"#fff000"}
					  Object.keys(icon).forEach((key) => {
						el.style[key] = icon[key]
					  })
					})
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				if((!this.ruleForm.yonghuzhanghao) && `yonghu` == this.tableName){
					this.$utils.msg(`用户账号不能为空`);
					return
				}
				if((!this.ruleForm.yonghumima) && `yonghu` == this.tableName){
					this.$utils.msg(`用户密码不能为空`);
					return
				}
                if(`yonghu` == this.tableName && (this.ruleForm.yonghumima!=this.ruleForm.yonghumima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if((!this.ruleForm.yonghuxingming) && `yonghu` == this.tableName){
					this.$utils.msg(`用户姓名不能为空`);
					return
				}
				if(`yonghu` == this.tableName && this.ruleForm.shoujihaoma&&(!this.$validate.isMobile(this.ruleForm.shoujihaoma))){
					this.$utils.msg(`手机号码应输入手机格式`);
					return
				}
				if((!this.ruleForm.jiaoshigonghao) && `xinlijiaoshi` == this.tableName){
					this.$utils.msg(`教师工号不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `xinlijiaoshi` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
                if(`xinlijiaoshi` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if((!this.ruleForm.jiaoshixingming) && `xinlijiaoshi` == this.tableName){
					this.$utils.msg(`教师姓名不能为空`);
					return
				}
				if(`xinlijiaoshi` == this.tableName && this.ruleForm.lianxidianhua&&(!this.$validate.isMobile(this.ruleForm.lianxidianhua))){
					this.$utils.msg(`联系电话应输入手机格式`);
					return
				}
				if(`xinlijiaoshi` == this.tableName && this.ruleForm.storeupnum&&(!this.$validate.isIntNumer(this.ruleForm.storeupnum))){
					this.$utils.msg(`收藏数量应输入整数`);
					return
				}
				await this.$api.register(`${this.tableName}`, this.ruleForm);
				this.$utils.msgBack('注册成功');;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
