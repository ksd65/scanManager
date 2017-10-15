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
		<li><a href="${ctx}/reg/register/">商户审核列表</a></li>
		<li class="active"><a href="${ctx}/reg/register/form?id=${register.id}">商户信息审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="register" action="${ctx}/reg/register/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered info-table" align="center">
		<tbody>
			<tr>
	        	<td colspan="4" class="info-table-group"><div class="text-center">商户信息审核</div></td>
	        </tr>        
	        <tr>
				<td class="info-table-label"><div class="text-right">商户编号：</div></td>
				<td width="30%">${register.code}</td>
				<td class="info-table-label"><div class="text-right">商户名称：</div></td>
				<td width="30%">${register.name}</td>
			</tr>
			<!--  
			<tr>
				<td class="info-table-label"><div class="text-right">商户类型：</div></td>
				<td width="30%">${register.type}</td>
				<td class="info-table-label"><div class="text-right">商户状态：</div></td>
				<td width="30%">${register.status}</td>
			</tr>
			-->
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
	        <td colspan="4" class="info-table-group"><div class="text-center">银行账号信息</div></td>
	        </tr>
			<tr>
				<td class="info-table-label"><div class="text-right">开户行：</div></td>
				<td width="30%">${register.bankOpen}</td>
				<td class="info-table-label"><div class="text-right">账号名称：</div></td>
				<td width="30%">${register.accountName}</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">银行账号：</div></td>
				<td width="30%">${register.accountNumber}</td>
				<td class="info-table-label"><div class="text-right">E码付编号：</div></td>
				<td width="30%">${register.payCode}</td>
			</tr>						
			<tr>
				<td colspan="4" class="info-table-group"><div class="text-center">相关证件</div></td>				 
	        </tr>	     	
		</tbody>
 		</table>
 		<div>
	     	<a class="example-image-link" href="/scanManager/file_servelt${register.certPic1}" data-lightbox="example-1"><img  src="/scanManager/file_servelt${register.certPic1}" style="width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;"alt="身份证照片" /></a>
	      	<a class="example-image-link" href="/scanManager/file_servelt${register.cardPic1}" data-lightbox="example-2" data-title="Optional caption."><img src="/scanManager/file_servelt${register.cardPic1}" style="width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;" alt="银行卡照片"/></a>
	    </div> 
 		<div class="control-group">
			<label class="control-label">是否通过:</label>
			<div class="controls">
				<form:select path="status" class="input-medium">
					<form:options items="${fns:getDictList('audit_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
 		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="reg:register:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="审核提交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>