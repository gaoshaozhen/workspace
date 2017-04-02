var WebSite = React.createClass({
	getInitialState: function(){
		return {
            username : "",
            password : "",
            grade : 0,            
            user:{},
            test:-1
		};
	},
	componentDidMount : function(){
		AjaxUtil.getUserInfo(null, function(ret){
			if (ret != null) {
	            this.setState({username:ret.username});
	            this.setState({password:ret.password});
	            this.setState({grade:ret.grade});
	            this.setState({
	            	user:{
	            		username:ret.username, 
	            		password:ret.password,
	            		grade:ret.grade
	            	}
	            });
	            // console.info(this.state.user);
			}
		}.bind(this));
	},
	render : function(){
		return (
			<div>
			  	<Header user={this.state.user} test={this.state.test}/>
			  	<Nav/>
			  	<Article user={this.state.user}/>
			  	<footer><Footer/></footer>
		  	</div>
		);
	}
});


ReactDOM.render(  
	<WebSite/>,	
  document.getElementById('wrapper')
);