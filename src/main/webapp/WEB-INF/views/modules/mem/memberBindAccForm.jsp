<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现银行卡管理</title>
	<meta name="decorator" content="default"/>
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
		
		function getBankSubList(){
			var bankId = $("#bankId").val();
			var areaId = $("#areaId").val();
			var subHtml = "<option value=\"\">请选择</option>";
			if(bankId=="" || areaId==""){
				$("#subName").html(subHtml);
				return;
			}
			$.ajax({
				url:"${ctx }/sys/bankSub/getBankSubList",
				data:{bankId:bankId,cityId:areaId},
				type:'post',
				cache:false, 
				async:false,
				dataType:'json',
				success:function(data) {
					
					var list = data.subList;
					for(var i=0;i<list.length;i++){
						subHtml = subHtml+ "<option value=\""+list[i].id+"_"+list[i].subId+"\">"+list[i].subName+"</option>";	
					}
					
					$("#subName").html(subHtml);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			        alert("请求出错");
			    }
			});

		}
		function areaTreeselectCallBack(){
			$("#area_id").val($("#areaId").val());
			getBankSubList();
		}
		function setSubId(){
			var subName = $("#subName").val();
			if(subName!=""){
				$("#subId").val(subName.split("_")[1]);
			}
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mem/memberBindAcc/">提现银行卡列表</a></li>
		<li  class="active"><a href="${ctx}/mem/memberBindAcc/form">提现银行卡添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="memberBindAcc" action="${ctx}/mem/memberBindAcc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="areaId" id="area_id"/>
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">开户行:</label>
			<div class="controls">
                <form:select path="bankId" class="input-xlarge" id="bankId" onchange="getBankSubList();" >
					<form:option value="" label="请选择"/>
					<form:options items="${bankList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="" labelName="area.name" labelValue=""
					title="区域" url="/sys/area/treeData" cssClass="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">开户分行:</label>
			<div class="controls">
                <form:select path="subName" class="input-xlarge" id="subName" onchange="setSubId();" >
					<form:option value="" label="请选择"/>
					
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联行号:</label>
			<div class="controls">
				<form:input path="subId" htmlEscape="false" maxlength="50" class="required" id="subId" readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">持卡人姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号:</label>
			<div class="controls">
				<form:input path="acc" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">持卡人身份证号码:</label>
			<div class="controls">
				<form:input path="certNo" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">手机号:</label>
			<div class="controls">
				
				<form:input path="mobilePhone" htmlEscape="false" maxlength="11" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		<div class="form-actions">
			<shiro:hasPermission name="mem:memberBindAcc:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>