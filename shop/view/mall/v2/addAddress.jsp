<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn" %>

<!DOCTYPE html>
<html>
	<head>
		<title>会员中心</title>
		<link rel="stylesheet" type="text/css" href="/shop/store/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/member_index.css">
		<link rel="stylesheet" type="text/css" href="/shop/sto/css/global.css">
	</head>
	<body>
		<div class="row">
			<aside class="col-md-2 clear-pa aside-center">
				<%@ include file="include/aside.jsp"%>
			</aside>			
			<article class="col-md-10 ">
				<div class="panel panel-default">
					 <div class="panel-heading">
				        <h3 class="panel-title">我的订单</h3>
				    </div>
					<div class="panel-body">
						<ul class="nav nav-pills">
						  	<li><a href="/shop/mall/address.shtm">收货地址管理</a></li>
						  	<li class="active"><a href="/shop/mall/addAddress.shtm">新增收货地址</a></li>
						</ul>
						<form action="/shop/mall/addNewAddress.shtm" method="post">
							<table cellspacing="0" cellpadding="0" border="0" class="box-td">	
								<tbody>
									<tr>
										<th>默认收货地址：</th>
										<td><label><input type="radio" checked="checked" value="0" name="defAddr">否</label> 
										<label><input type="radio" value="1" name="defAddr">是</label></td>
									</tr>
									<tr>
										<th><em></em>姓名：</th>
										<td><input type="text" class="input_text " isrequired="true" name="name" autocomplete="off"></td>
									</tr>
							
									<tr>
										<th>手机：</th>
										<td><input type="text" class="input_text" name="mobile" id="mobile" autocomplete="off"></td>
									</tr>
							
									<tr style="display: none;">
										<th >电话：</th>
										<td><input type="text" class="input_text" name="address.tel" id="tel" datatype="tel_num" autocomplete="off"><span class="infotips" style="display: none;">其中联系电话和联系手机必须填写一项</span></td>
									</tr>
											
									<tr style="display: none;">
										<th><em></em>地区：</th>
										<td>		
										<span>
										<select name="province_id" id="province_id" class="">
											<option value="0">请选择</option>
													<option value="1">北京</option>
												<option value="2">上海</option>
										</select>&nbsp;
										<input type="hidden" name="province" id="province">

										<select name="city_id" id="city_id" class=""><option value="0">请选择</option></select>&nbsp;
										<input type="hidden" name="city" id="city">

										<select name="region_id" id="region_id" class="fail"><option value="0">请选择</option></select><span class="tip error">地区信息不完整</span>&nbsp;
										<input type="hidden" name="region" id="region">	
										</span>
										</td>
									</tr>
									<tr>
										<th><em></em>地址：</th>
										<td><textarea rows="3" cols="30" name="address.addr" isrequired="true" type="textarea"></textarea></td>
									</tr>
									<tr>
										<th><em></em>邮编：</th>
										<td><input class="input_text fail" isrequired="true" name="zip" id="zipcode" autocomplete="off" datatype="post_code"><span class="tip error">此项为必填</span></td>
									</tr>
									<tr>
										<th></th>
							
										<td>
											<div class="btn"><input type="submit" name="submit" class="yellow_btn" value="保存并返回"></div>
										</td>						
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</article>
		</div>
	</body>
</html>