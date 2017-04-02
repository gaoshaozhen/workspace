// 个人中心侧边栏
var MemberSide = React.createClass({
	render : function(){
		return (
			<aside className="col-md-3">
				<h5>
					<span className="fa fa-2x fa-user-circle"></span>
					<a href={AppUrl.memberIndex}>会员中心首页</a>
				</h5>
				<div className="box-inline"><span className="fa fa-4x fa-address-card-o"></span></div>
				<div className="box-inline">
					<p>{this.props.user.username}</p>
					<p>会员等级：{this.props.user.grade}</p>
				</div>
				<section>
					<div><span className="fa fa-jpy"></span>交易管理</div>
					<ul>
						<li><a href={AppUrl.memberOrder}>我的订单</a></li>
						<li>我的优惠券</li>
						<li>退换货申请</li>
						<li><a href={AppUrl.memberAddress}>收货地址</a></li>
					</ul>
				</section>
				<section>
					<div><span className="fa fa-star-half-empty"></span>收藏夹</div>
					<ul>
						<li>商品收藏</li>
						<li>缺货登记</li>
					</ul>
				</section>				
				<section>
					<div><span className="fa fa-weixin"></span>评论管理</div>
					<ul>
						<li>我的评论</li>
						<li>我的咨询</li>
					</ul>
				</section>
				<section>
					<div><span className="fa fa-user"></span>账号管理</div>
					<ul>
						<li><a href={AppUrl.memberInfo}>完善基本资料</a></li>
						<li>我的积分</li>
						<li>修改密码</li>
					</ul>
				</section>
			</aside>
		)
	}
});

// 个人信息首页
var MemberIndex = React.createClass({
	render : function(){
		var orders = [];
		return(
			<div className="col-md-9">
				<section>
					<ul>
						<li>您好，{this.props.user.username}欢迎回来</li>
						<li>会员编号：{this.props.user.id}</li>
						<li>会员等级：{this.props.user.grade}</li>
						<li>消费积分：{this.props.user.num}</li>
						<li>邮箱：{this.props.user.email}</li>
					</ul>
				</section>
				<section>
					<table className="table">
	       				<caption>基本的表格布局</caption>
					    <thead>
						    <tr>
						      <th>待付款订单号</th>
						      <th>下单时间</th>
						      <th>收货人</th>
						      <th>总价</th>
						      <th>状态</th>
						      <th>付款方式</th>
						      <th>操作</th>
						    </tr>
					    </thead>
					</table>
				</section>
				<section>
					推荐商品
				</section>
			</div>
		);
	}
});

// 订单
var MemberOrder = React.createClass({
	getInitialState: function(){
		return {
			order:{}
		}
	},

	componentDidMount: function(){
		
	},


	render : function(){
		return (
			<div className="col-md-9"> 
				<section>
					<p>1、这里会显示您更多的订单</p>
					<p>1、您还可以进行查看订单详情，搜索成功订单和取消订单操作</p>
				</section>
				<section>
					<table className="table">
						<thead>
							<th><a href="">所有订单</a></th>
							<th><a href="">等待付款</a></th>
							<th><a href="">已付款待确认</a></th>
							<th><a href="">已付款</a></th>
							<th><a href="">配货中</a></th>
							<th><a href="">已发货</a></th>
							<th><a href="">已取消</a></th>
							<th><a href="">已成功</a></th>
							<th><a href="">搜索结果</a></th>
						</thead>
					</table>
				</section>
			</div>);
	}
});

// 完善基本资料

var MemberInfo = React.createClass({
	render : function(){
		return (
			<div className="col-md-9">
				<table>			
					<tr>
						<td>用户名：</td>
						<td></td>
					</tr>
					<tr>
						<td>邮箱：</td>
						<td></td>
					</tr>
					<tr>
						<td>真实姓名：</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>设置头像：</td>
						<td><input type="file"/></td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>
							<label><input type="radio" value="1"/>男</label>
							<label><input type="radio" value="1"/>女</label>
						</td>
					</tr>
					<tr>
						<td>生日：</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>居住地：</td>
						<td>
							<select>
								<option>地区</option>
							</select>
							<select>
								<option>地区</option>
							</select>
							<select>
								<option>地区</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>联系地址</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>手机：</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td>固定电话：</td>
						<td><input type="text"/></td>
					</tr>
					<tr>
						<td></td>
						<td><button>保存资料</button></td>
					</tr>
				</table>
			</div>);
	}
});

var MemberContent = React.createClass({
	render : function(){
		return (
			<div className="col-md-9">
				content
			</div>);
	}
});

// 管理收货地址
var MemberAddress = React.createClass({
	render : function(){
		return (
			<div className="col-md-9">
				<section>
					<ul>
						<li>1、这里将显示您的收货地址信息</li>
						<li>2、您还可以管理您的收货地址簿、增加新的收货地址等操作。</li>
					</ul>
				</section>
				<section>
					<ul className="nav nav-tabs">
						<li className="active">
							<a href="#addressManager" data-toggle="tab">管理收货地址</a>
						</li>
						<li>
							<a href="#addAddress" data-toggle="tab">新增收货地址</a>
						</li>
					</ul>
					<div className="tab-content">
						<div id="addressManager" className="tab-pane fade in active">
							addressManager
						</div>
						<div id="addAddress" className="tab-pane">
							<table>
								<tr>
									<td>默认收货地址：</td>
									<td>
										<label><input type="radio" name="defAddress" value="1"/>是</label>
										<label><input type="radio" name="defAddress" value="1"/>否</label>
									</td>
								</tr>
								<tr>
									<td>姓名：</td>
									<td><input type="text" name="name"/></td>
								</tr>
								<tr>
									<td>手机：</td>
									<td><input type="text" name="mobile"/></td>
								</tr>
								<tr>
									<td>地区：</td>
									<td></td>
								</tr>
								<tr>
									<td>地址：</td>
									<td><textArea></textArea></td>
								</tr>
								<tr>
									<td></td>
									<td><button>保存</button></td>
								</tr>
							</table>
						</div>
					</div>
				</section>
			</div>);
	}
});

var MemberBox = React.createClass({
	render:function(){
		var content;
		var operator = Util.getUrlParam("operator");
		if (operator.indexOf("Index") > -1) {
			content = (<MemberIndex user={this.props.user}/>);
		}
		else if (operator.indexOf("Order") > -1){
			content = (<MemberOrder/>);	
		}
		else if (operator.indexOf("Address") > -1){
			content = (<MemberAddress/>);	
		}
		else if (operator.indexOf("Info") > -1){
			content = (<MemberInfo/>);	
		}
		else{
			content = (<div className="col-md-3"> content</div>);	
		}
		return (
			<div>
				<MemberSide user={this.props.user}/>
				{content}
			</div>
		);
	}
});