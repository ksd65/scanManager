<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
	<meta name="decorator" content="default"/>
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mem/member/">商户信息列表</a></li>
		<li class="active"><a href="${ctx}/mem/member/form?id=${member.id}">商户信息修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/mem/member/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
 	<table class="table table-bordered info-table" align="center">
	<tbody>
		<tr>
        <td colspan="4" class="info-table-group"><div class="text-center">商户基本信息</div></td>
        </tr>
        
        <tr>
			<td class="info-table-label"><div class="text-right">商户编号：</div></td>
			<td width="30%">${member.code}</td>
			<td class="info-table-label"><div class="text-right">商户名称：</div></td>
			<td width="30%"><form:input path="name" htmlEscape="false" maxlength="256" class="input-xlarge "/></td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">商户简称：</div></td>
			<td width="30%"><form:input path="shortName" htmlEscape="false" maxlength="256" class="input-xlarge "/></td>
			<td class="info-table-label"><div class="text-right">商户状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.status,'member_status','商户状态')}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">身份证号：</div></td>
			<td width="30%">${member.certNbr}</td>
			<td class="info-table-label"><div class="text-right">手机号码：</div></td>
			<td width="30%"><form:input path="mobilePhone" htmlEscape="false" maxlength="11" class="input-xlarge "/></td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">联系人：</div></td>
			<td width="30%">${member.contact}</td>
			<td class="info-table-label"><div class="text-right">联系地址：</div></td>
			<td width="30%"><form:input path="addr" htmlEscape="false" maxlength="256" class="input-xlarge "/></td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">经营类目：</div></td>
			<td width="30%">${member.category}</td>
			<td class="info-table-label"><div class="text-right">E码付编号：</div></td>
			<td width="30%">${member.payCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">微信商户编号：</div></td>
			<td width="30%">${member.wxMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道微信商户编号：</div></td>
			<td width="30%">${member.wxMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">支付宝商户编号：</div></td>
			<td width="30%">${member.zfbMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道支付宝商户编号：</div></td>
			<td width="30%">${member.zfbMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">D0提现手续费：</div></td>
			<td width="30%">
			<form:input path="t0DrawFee" htmlEscape="false" maxlength="10" class="input-xlarge "/></td>
			<td class="info-table-label"><div class="text-right">D0交易手续费扣率：</div></td>
			<td width="30%">
			<form:input path="t0TradeRate" htmlEscape="false" maxlength="10" class="input-xlarge "/></td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">T1提现手续费：</div></td>
			<td width="30%">
			<form:input path="t1DrawFee" htmlEscape="false" maxlength="10" class="input-xlarge "/></td>
			<td class="info-table-label"><div class="text-right">T1交易手续费扣率：</div></td>
			<td width="30%">
			<form:input path="t1TradeRate" htmlEscape="false" maxlength="10" class="input-xlarge "/></td>
		</tr>
		
		<tr>
        <td colspan="4" class="info-table-group"><div class="text-center">银行账号信息</div></td>
        </tr>
		<tr>
			<td class="info-table-label"><div class="text-right">结算方式：</div></td>
			<td width="30%">
				<form:select path="memberBank.settleType" class="input-xlarge required" >
					<form:options items="${fns:getDictList('settle_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="info-table-label"><div class="text-right">开户行：</div></td>
			<td width="30%"><form:input path="memberBank.bankOpen" htmlEscape="false" maxlength="256" class="input-xlarge "/></td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">账号名称：</div></td>
			<td width="30%">${member.memberBank.accountName}</td>
			<td class="info-table-label"><div class="text-right">银行账号：</div></td>
			<td width="30%"><form:input path="memberBank.accountNumber" htmlEscape="false" maxlength="256" class="input-xlarge "/></td>
		</tr>
	</tbody>
 	</table>
	<div class="text-center">
		<shiro:hasPermission name="mem:member:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
	</form:form>
</body>
</html>