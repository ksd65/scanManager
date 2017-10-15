<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代理商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					type: "required",
					phone: {remote: "${ctx}/sys/agent/checkPhone?oldPhone=" + encodeURIComponent('${office.phone}')}
				},
				messages: {
					phone: {remote: "联系电话已存在"}
				},
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
		<li><a href="${ctx}/sys/agent/list?id=${office.parent.id}&parentIds=${office.parentIds}">代理商列表</a></li>
		<li class="active"><a href="${ctx}/sys/agent/form?id=${office.id}&parent.id=${office.parent.id}">代理商<shiro:hasPermission name="sys:agent:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:agent:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/agent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<!--  
		<sys:message content="${message}"/>
		-->
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
				<c:if test="${office.id == null}">
                <sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
				</c:if>
				<c:if test="${office.id != null}">
                	<label style="font-size:20px;">${office.area.name}</label>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代理商名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人:</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="50" cssClass="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<input id="oldPhone" name="oldPhone" type="hidden" value="${office.phone}">
				<form:input path="phone" htmlEscape="false" maxlength="50" cssClass="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
<!--  
		<div class="control-group">
			<label class="control-label">T0单笔提现手续费：</label>
			<div class="controls">
				<form:input path="t0DrawFee" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">T0交易手续费扣率：</label>
			<div class="controls">
				<form:input path="t0TradeRate" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">T1单笔提现手续费：</label>
			<div class="controls">
				<form:input path="t1DrawFee" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">T1交易手续费扣率：</label>
			<div class="controls">
				<form:input path="t1TradeRate" htmlEscape="false" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">快捷(有积分)扣率：</label>
			<div class="controls">
				<form:input path="bonusQuickRate" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(有积分)手续费：</label>
			<div class="controls">
				<form:input path="bonusQuickFee" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(无积分)扣率：</label>
			<div class="controls">
				<form:input path="quickRate" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(无积分)手续费：</label>
			<div class="controls">
				<form:input path="quickFee" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
-->
		<div class="form-actions">
			<shiro:hasPermission name="sys:agent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>