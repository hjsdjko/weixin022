<template>
	<view>
		<view class="header-container">
			<view @tap="leaveTap" class="left-container">
				<image class="icon" src="../../static/center/l5.png" mode=""></image>
				<text>交卷</text>
			</view>
			<view class="center-container">
				倒计时：{{SecondToDate}}
			</view>
			<view class="right-container">
				题目： {{currentItem}}/{{list.length}}
			</view>
		</view>
		<view v-for="(item,index) in list" :key="index">
			<view class="exam-container">
				<view class="title-container">
					<text class="title">
						{{item.questionname}}
					</text>
				</view>
				<view class="answer-container" v-if="!isSubmit">
					<view v-if="item.type==0" class="answer-item"
						v-for="(option,indexs) in item.options" v-bind:key="indexs">
						<checkbox @tap="itemCheckTap(option.checked,index,indexs)" :value="String(index)"
							:checked="option.checked" />
						<text class="text">{{option.text}}</text>
					</view>
					<view class="tiankong-container" v-if="item.type==4">
						<text>填写答案：</text>
						<textarea v-model="item.myanswer" class="answer" auto-height />
					</view>
				</view>
				<view v-if="isSubmit&&isEndFlag" class="tip-container">
					<view class="par">
						您的答案是：{{item.myanswer}}
					</view>
					<view class="par">
						题目分析：{{item.analysis}}
					</view>
				</view>
				<!-- {{score}} -->
			</view>
		</view>
		<button v-if="!isSubmit" @tap="submitTap" class="btn-submit" type="primary">提交答案</button>
		<button v-if="isSubmit&&isEndFlag" @tap="endClick" class="btn-submit" type="primary">退出</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 心理测试
				paper: {},
				// 是否提交
				isSubmit: false,
				// 填空题答案
				answer: '',
				// 当前题目
				currentItem: 1,
				// 总分
				score: 0,
				// 倒计时定时器
				inter: null,
				// 题目列表
				"list": [],
				// 用户
				user: {},
				// 倒计时
				count: 0,
				// 是否查看解析
				isEndFlag: false,
			}
		},
		async onLoad(options) {
			this.score = 0;
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data;
			// 获取心理测试
			res = await this.$api.info('exampaper', options.id);
			this.paper = res.data;
			// 获取试题
			res = await this.$api.list(`examquestion`, {
				page: 1,
				limit: 999,
				sort: "sequence",
				paperid: options.id
			});
			this.list = res.data.list;
			// 开启定时 
			this.count = this.paper.time*60;
			if (this.count > 0) {
				var _this = this;
				this.inter = setInterval(function() {
					_this.count = _this.count - 1;
					if (_this.count < 0) {
						clearInterval(_this.inter);
						uni.showModal({
							title: '答题结束',
							content: '本次考试成绩：' + _this.score,
							showCancel: false,
							success: async function(res) {
								if (res.confirm) {
									uni.navigateBack({
										delta: 1
									})
								}
							}
						});
					}
				}, 1000);
			}
			for (let i = 0; i < this.list.length; i++) {
				if(this.list[i].type>2)continue
				let options = JSON.parse(this.list[i].options);
				for (let j = 0; j < options.length; j++) {
					options[j].checked = false;
				}
				this.list[i].options = options;
			}
		},
		destroyed: function() {
			clearInterval(this.inter);
		},
		computed: {
			SecondToDate: function() {
				var time = this.count;
				if (null != time && "" != time) {
					if (time > 60 && time < 60 * 60) {
						time =
							parseInt(time / 60.0) +
							"分钟" +
							parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60) +
							"秒";
					} else if (time >= 60 * 60 && time < 60 * 60 * 24) {
						time =
							parseInt(time / 3600.0) +
							"小时" +
							parseInt(
								(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
							) +
							"分钟" +
							parseInt(
								(parseFloat(
										(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
									) -
									parseInt(
										(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
									)) *
								60
							) +
							"秒";
					} else if (time >= 60 * 60 * 24) {
						time =
							parseInt(time / 3600.0 / 24) +
							"天" +
							parseInt(
								(parseFloat(time / 3600.0 / 24) - parseInt(time / 3600.0 / 24)) *
								24
							) +
							"小时" +
							parseInt(
								(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
							) +
							"分钟" +
							parseInt(
								(parseFloat(
										(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
									) -
									parseInt(
										(parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60
									)) *
								60
							) +
							"秒";
					} else {
						time = parseInt(time) + "秒";
					}
				}
				return time;
			}
		},
		methods: {
			leaveTap() {
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '确定离开考试?未答题目按0分计算',
					success: async function(res) {
						if (res.confirm) {
							_this.$utils.msgBack('本次考试成绩：' +
								_this.score);
						}
					}
				});
			},
			itemCheckTap(checked, index, indexs) {
				if (this.list[index].type == 0 || this.list[index].type == 2) {
					for (let i = 0; i < this.list[index].options.length; i++) {
						this.list[index].options[i].checked = false;
					}
				}
				this.list[index].options[indexs].checked = !checked;
				if (this.list[index].type == 1) {
					let answer = []
					for (let i = 0; i < this.list[index].options.length; i++) {
						if (this.list[index].options[i].checked) {
							answer.push(this.list[index].options[i].code)
						}
					}
					this.list[index].myanswer = answer.sort().join(",");
				}
				if (this.list[index].type != 1) {
					this.list[index].myanswer = this.list[index].options[indexs].code;
				}
			},
			async submitTap() {
				var _this = this;
				uni.showModal({
					content: '是否完成作答？',
					async success(obj) {
						if (obj.confirm) {
							let score = 0
							for (let x in _this.list) {
								if (_this.list[x].type != 4) {
									for(let i in _this.list[x].options){
										if (_this.list[x].myanswer == _this.list[x].options[i].code) {
											_this.list[x].myscore = _this.list[x].options[i].score
										}
									}
								} else {
									_this.list[x].myscore = 0
								}
								score += Number(_this.list[x].myscore)
								await _this.saverecord(_this.list[x])
							}
							_this.isSubmit = true;
							_this.score = score
							uni.showModal({
								title: '答题结束',
								content: '本次考试成绩：' + _this.score,
								cancelText: '查看解析',
								confirmText: '退出',
								success: async function(res) {
									if (res.confirm) {
										uni.navigateBack({
											delta: 1
										})
									} else if (res.cancel) {
										_this.isEndFlag = true
									}
								}
							});
						}
					},
				})
			},
			async saverecord(row) {
				let record = {
					userid: this.user.id,
					username: this.user.yonghuzhanghao,
					paperid: this.paper.id,
					papername: this.paper.name,
					questionid: row.id,
					questionname: row.questionname,
					options: JSON.stringify(row.options),
					score: row.score,
					answer: row.answer,
					analysis: row.analysis,
					myscore: row.myscore,
					myanswer: row.myanswer
				};
				await this.$api.add(`examrecord`, record);
			},
			endClick() {
				uni.navigateBack({
					delta: 1
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.header-container {
		display: flex;
		align-items: center;
		background: #FFFFFF;
		border-bottom: 1rpx solid #EEEEEE;
		border-top: 1rpx solid #EEEEEE;
		padding: 10rpx;

		.left-container {
			display: flex;
			align-items: center;
			margin: 10rpx;

			.icon {
				margin-right: 10rpx;
				width: 40rpx;
				height: 40rpx;
			}
		}

		.center-container {
			flex: 1;
			text-align: center;
		}

		.right-container {
			margin: 10rpx;
		}
	}

	.exam-container {
		background: #FFFFFF;
		margin: 30rpx;
		border-radius: 20rpx;
		padding: 20rpx;

		.title-container {
			padding: 20rpx;

			.type {
				background: #EEEEEE;
				padding: 10rpx;
				border-radius: 10rpx;
			}

			.title {
				margin-left: 20rpx;
			}
		}

		.answer-item {
			margin: 20rpx;

			.text {
				margin-left: 20rpx;
			}
		}

		.tiankong-container {
			display: flex;
			align-items: center;
			margin: 20rpx;

			.answer {
				border: 1rpx solid #EEEEEE;
				height: 60rpx;
				margin: 0 20rpx;
			}
		}

		.btn-submit {
			height: 60rpx;
			font-size: 30rpx;
			line-height: 60rpx;
			margin-top: 60rpx;
			border-radius: 50rpx;
		}

		.btn-next {
			height: 60rpx;
			font-size: 30rpx;
			line-height: 60rpx;
			margin-top: 60rpx;
			border-radius: 50rpx;
		}
	}

	.tip-container {
		margin-top: 50rpx;
		border-top: 1rpx solid #EEEEEE;
		padding: 20rpx;

		.par {
			margin-bottom: 20rpx;
		}
	}
</style>
