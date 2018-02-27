<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款人管理</title>
	<meta name="decorator" content="default"/>
	<style>
        .cert-info .block {
            width: 18%;
            margin-left: 2%;
            text-align: center;
            float: left;
        }
        div img{
            width: 40%;
        }
        .result{position: fixed;top:0;left:0;background: rgba(0,0,0,0.5);z-index:1000;width:100%;height:100%;display: none;}
        .imgresult{border:5px solid #fff;}
        .indiv{position: absolute;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li><a href="${ctx}/trade/payee/">收款人列表</a></li>
		<li class="active"><a href="${ctx}/trade/payee/form">收款人添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="payee" action="${ctx}/trade/payee/save" method="post" class="form-horizontal" >
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">收款类型:</label>
			<div class="controls">
				<form:select id="payType" path="payType" class="input-large required">
					<form:options items="${fns:getDictList('qr_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收款账号:</label>
			<div class="controls">
				<input id="oldPayAccount" name="oldPayAccount" type="hidden" value="">
				<form:input path="payAccount" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收款人姓名:</label>
			<div class="controls">
				<form:input path="userName" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		
		
		<div class="form-actions">
			<shiro:hasPermission name="trade:payee:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
	
	
</body>
</html>