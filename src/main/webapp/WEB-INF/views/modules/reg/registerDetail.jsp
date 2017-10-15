<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户审核管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/lightbox2/css/lightbox.min.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/lightbox2/js/lightbox-plus-jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			//alert(${register.certPic1});
			photo01Preview(); 
		});
		function photo01Preview(){
			var li, urls = $("#photo01").val().split("|");
			var src = "D:\\upload\\member\\20161207162111101046.jpg";
			//$("#photo01Preview").children().remove();
			if (${register.certPic1 != ""}){//
				li = "<div id=\"photo01uploadinfo\"><img onclick=\"window.parent.showImage('身份证正面照',this.src)\" title=\"身份证正面照\" src=\""+src+"\" url=\""+src+"\" style=\"width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;\">";//
				li += "&nbsp;&nbsp;</div></div>";
				$("#photo01Preview").append(li);
			}
			/*
			for (var i=0; i<urls.length; i++){
				if (urls[i]!=""){//
					li = "<div id=\"photo01uploadinfo\"><img onclick=\"window.parent.showImage('身份证正面照',this.src)\" title=\"身份证正面照\" src=\""+urls[i]+"\" url=\""+urls[i]+"\" style=\"width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;\">";//
					li += "&nbsp;&nbsp;</div></div>";
					$("#photo01Preview").append(li);
				}
			}
			*/
			if ($("#photo01Preview").text() == ""){ 
				var onclick="onclick=\"photo01FileSelect();\"";
				$("#photo01Preview").html("<div id=\"photo01uploadinfo\"  style=\"cursor:pointer;margin-bottom:5px;border:1px solid #EEE;text-align:center;line-height:108px;width:108px;height:108px;_height:108px;padding:3px;\">身份证正面照</div>");
			}
		} 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/reg/register/">商户注册列表</a></li>
		<li class="active"><a href="${ctx}/reg/register/form?id=${register.id}">商户注册详情</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="register" action="${ctx}/reg/register/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered info-table" align="center">
		<tbody>
			<tr>
	        	<td colspan="4" class="info-table-group"><div class="text-center">商户注册信息</div></td>
	        </tr>        
	        <tr>
				<td class="info-table-label"><div class="text-right">商户编号：</div></td>
				<td width="30%">${register.code}</td>
				<td class="info-table-label"><div class="text-right">商户名称：</div></td>
				<td width="30%">${register.name}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">商户简称：</div></td>
				<td width="30%">${register.shortName}</td>
				<td class="info-table-label"><div class="text-right">经营类目：</div></td>
				<td width="30%">${register.category}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">审核状态：</div></td>
				<td width="30%">${fns:getDictLabel(register.status,'register_status','审核状态')}</td>
				<td class="info-table-label"><div class="text-right">E码付编号：</div></td>
				<td width="30%">${register.payCode}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">身份证号：</div></td>
				<td width="30%">${register.certNbr}</td>
				<td class="info-table-label"><div class="text-right">手机号码：</div></td>
				<td width="30%">${register.mobilePhone}</td>				
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">联系人：</div></td>
				<td width="30%">${register.contact}</td>
				<td class="info-table-label"><div class="text-right">联系地址：</div></td>
				<td width="30%">${register.addr}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">微信商户编号：</div></td>
				<td width="30%">${register.wxMemberCode}</td>
				<td class="info-table-label"><div class="text-right">支付宝商户编号：</div></td>
				<td width="30%">${register.zfbMemberCode}</td>
			</tr>
			<tr>
	        <td colspan="4" class="info-table-group"><div class="text-center">银行账号信息</div></td>
	        </tr>
			<tr>
				<td class="info-table-label"><div class="text-right">结算方式：</div></td>
				<td width="30%">${fns:getDictLabel(register.settleType,'settle_type','结算方式')}</td>
				<td class="info-table-label"><div class="text-right">开户行：</div></td>
				<td width="30%">${register.bankOpen}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">账号名称：</div></td>
				<td width="30%">${register.accountName}</td>
				<td class="info-table-label"><div class="text-right">银行账号：</div></td>
				<td width="30%">${register.accountNumber}</td>
				
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">联行号：</div></td>
				<td width="30%">${register.subId}</td>
				<td class="info-table-label"><div class="text-right">备注：</div></td>
				<td width="30%">${register.remarks}</td>
			</tr>   					     	
		</tbody>
 		</table>
 		<div class="text-center">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>