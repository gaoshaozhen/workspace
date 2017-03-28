
var Header = React.createClass({
    getInitialState: function(){
        return {
            user:{}
        };
    },

    componentDidMount: function () {
        $.get("/mall/data/user.json",null,function (ret) {
            this.setState({user:ret});
        }.bind(this))
    },    

    render: function() {
        var login;
        if (typeof this.state.user.userName == "undefined") {
            login = (
                <div className="col-xs-6">
                    <span>登录{this.state.user.userName}</span>|
                    <span>注册</span>
                </div>       
            );
        }
        else {
            login = (
                <div className="col-xs-6">
                    <span>您好：{this.state.user.userName}</span>|
                    <a href="#">[会员中心]</a>
                    <a href="#">[退出]</a>
                </div>       
            );   
        }
        return (
            <header className="def-color web-header">
                <div className="row">
                    {login}
                    <div className="col-md-6 text-right">
                        <a href="#">购物车</a>|
                        <a href="#">订单查询</a>|
                        <a href="#">帮助中心</a>
                    </div>
                </div>
                <div className="row web-header-info">
                    <div className="col-xs-6">网店系统</div>
                    <div className="col-lg-6 text-right">
                        正品保障 价格优势 货到付款 30天退换
                    </div>
                </div>
            </header>
        );
  }
});
var Nav = React.createClass({
    getInitialState: function(){
        return {
            menu:{}
        };
    },

    
    componentDidMount: function () {
        $.get("/mall/data/nav_menu_list.json",null,function (ret) {
            this.setState({menu:ret});
        }.bind(this))
    },    


    render :function(){
        var menu1 = [];
        for (var i in this.state.menu.menuList) {            
            var t = [];
            var size = this.state.menu.menuList[i].children.length;
            for (var j in this.state.menu.menuList[i].children){
                var tempUrl = this.state.menu.menuList[i].children[j].url +　"?familyId=" + this.state.menu.menuList[i].children[j].familyId;
                t.push(<li><a href={tempUrl}>{this.state.menu.menuList[i].children[j].name}</a></li>);
            }
            var tempUrl1 = this.state.menu.menuList[i].url + "?familyId=" + this.state.menu.menuList[i].familyId;
            if (size > 0) {
                menu1.push(
                    <li className="dropdown">
                        <a className="dropdown-toggle" data-toggle="dropdown" href={tempUrl1}>{this.state.menu.menuList[i].name}<b className="caret"></b></a>                        
                         <ul className="dropdown-menu">{t}</ul>
                    </li>);
            }
            else
            {

                menu1.push(<li><a className="dropdown-toggle" data-toggle="dropdown" href={tempUrl1}>{this.state.menu.menuList[i].name}</a></li>);    
            }            
        }
        return (
            <nav className="nav-menu-list">         
                <div className="navbar navbar-default" role="navigation">
                    <div className="container-fluid">
                        <ul className="nav navbar-nav">
                            {menu1}
                        </ul>
                    </div>
                </div>
            </nav>
        );     
    }   
});

var Search = React.createClass({
    render: function(){
        return (
            <div className="text-right">
                热门搜索：戴尔显示器| 中粮|iphone手机
                <div className="input-group search-products">
                    <span className="input-group-addon "><label className="fa fa-search"></label></span>
                    <input className="form-control"/>
                </div>
                {FilterProducts}
            </div>
        );
    }
});

var Article = React.createClass({
   render: function(){

    return (
        <article className="row">
            <div className="col-md-9"><Content/></div>
            <div className="col-md-3"><RightAside/></div>
        </article>
    );
   } 
});

var Footer = React.createClass({
    render: function(){
        return (
            <div className="container">
                <ul className="row">
                    <li className="col-md-2">
                        <ul>
                            <li>新手上路</li>
                            <li>顾客必读</li>
                            <li>积分规则</li>
                        </ul>
                    </li>
                    <li className="col-md-2">
                        <ul>
                            <li>支付</li>
                            <li>支付方式</li>
                            <li>付款常见问题</li>
                        </ul>
                    </li>
                    <li className="col-md-2">
                        <ul>
                            <li>配送</li>
                            <li>配送方式</li>
                            <li>收货与验货</li>
                        </ul>
                    </li>
                    <li className="col-md-2">
                        <ul>
                            <li>售后</li>
                            <li>售后服务</li>
                            <li>退换货保障</li>
                        </ul>
                    </li>
                    <li className="col-md-2">
                        <ul>
                            <li>新手上路</li>
                            <li>顾客必读</li>
                            <li>积分规则</li>
                        </ul>
                    </li>
                    <li className="col-md-2">
                        <ul>
                            <li>帮助</li>
                            <li>有课购物通道</li>
                            <li>购物流程</li>
                        </ul>
                    </li>
                </ul>
                <center>
                    <hr/><h6>高少振版权所有</h6>
                </center>
            </div>
        );        
    }
});
