var RightAside = React.createClass({
	getInitialState: function(){
		return {
			data:[]
		}
	},

	componentDidMount: function(){
		$.get(AppUrl.getRecommendedProducts, null, function(ret){
			this.setState({data:ret});
		}.bind(this));
	},

	render:function(){
		// 推荐商品
		var list1 = [];
		var list2 = [];
		var list3 = [];
		for (var i in this.state.data.recommendedProducts){
			list1.push(
				<li>
					<img src={this.state.data.recommendedProducts[i].imgUrl}></img>
					<ul>
						<li>{this.state.data.recommendedProducts[i].name}</li>
						<li>{this.state.data.recommendedProducts[i].priceName}：{this.state.data.recommendedProducts[i].price}</li>
					</ul>
				</li>
			);
		}				
		for (var i in this.state.data.hotProducts){
			list2.push(
				<li>
					<img src={this.state.data.hotProducts[i].imgUrl}></img>
					<ul>
						<li>{this.state.data.hotProducts[i].name}</li>
						<li>{this.state.data.hotProducts[i].priceName}：{this.state.data.hotProducts[i].price}</li>
					</ul>
				</li>
			);
		}
		for (var i in this.state.data.recentProducts){
			list3.push(
				<li>
					<img src={this.state.data.recentProducts[i].imgUrl}></img>
					<ul>
						<li>{this.state.data.recentProducts[i].name}</li>
						<li>{this.state.data.recentProducts[i].priceName}：{this.state.data.recentProducts[i].price}</li>
					</ul>
				</li>
			);
		}		
		return(
			<div>
				<section>
					<header>推荐商品</header>
					<ul>{list1}</ul>
				</section>
				<section>
					<header>热卖排行榜</header>
					<ul>{list2}</ul>
				</section>
				<section>
					<header>最近浏览</header>
					<ul>{list3}</ul>
				</section>
			</div>
		);
	}
});

var Content = React.createClass({
	getInitialState : function(){
		return ({data:{}});	
	},

	componentDidMount : function(){
		$.get(AppUrl.getProducts, null, function(ret){
			this.setState({data:ret});
		}.bind(this));
	},

	// 获得分页组件
	getPageBox : function(total, current){
		var doms = [];
		for (var i = 1; i <= total; i++){
			if (i != current) {
				doms.push(<li ><a href="#">{i}</a></li>);		
			}
			else{
				doms.push(<li className="active"><a href="#">{i}</a></li>);			
			}
	    	
	    }
		return (
			<ul className="pagination">
			    <li><a href="#">&laquo;</a></li>
			    {doms}
			    <li><a href="#">&raquo;</a></li>
			</ul>
		);	
	},

	render : function(){
		var doms = [];
		var productsDom = [];
		var url = window.location.href;
		var pageBox = this.getPageBox(parseInt(this.state.data.total),parseInt(this.state.data.current));
		if (url.indexOf("?") < 0) {
			url += "?";
		}
		for (var i in this.state.data.brands){
			var temp = url + '&brandId=' +this.state.data.brands[i].brandId;
			doms.push(<a className="box-a" href={temp}>{this.state.data.brands[i].name}</a>);
		}
		for (var j in this.state.data.products){
			productsDom.push(
				<li className="box-products">
					<img src={this.state.data.products[j].imgUrl}></img>
					<center>{this.state.data.products[j].name}</center>
					<center>市场价：{this.state.data.products[j].marketPrice}</center>
					<center>商城价：￥{this.state.data.products[j].mallPrice}</center>
				</li>
			);
		}
		return (
			<div>
				<div className="panel panel-default">
	                <div className="panel-heading">
	                    <h3 className="panel-title">商品筛选</h3>
	                </div>
	                <div className="panel-body">
	                    品牌：{doms}
	                </div>
	            </div>
	            <div className="panel panel-default">
	                <div className="panel-body">
	              		<ul>{productsDom}</ul>      
	              		<center>{pageBox}</center>
	                </div>
	            </div>
            </div>
		);
	}
});