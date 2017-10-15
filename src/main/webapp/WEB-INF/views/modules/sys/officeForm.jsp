<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					type: "required",
					phone: {remote: "${ctx}/sys/office/checkPhone?oldPhone=" + encodeURIComponent('${office.phone}')}
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
			
			if(${office.id == null}){
				$("#agtType").hide();
				if(${office.parent.type == 1}){
					document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('分公司',2));
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	$("#agtType").hide();
				}else if(${office.parent.type == 2}){
					document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	document.getElementById("type").options.add(new Option('代理商',4));
			    	$("#agtType").hide();
				}else if(${office.parent.type == 3}){
					document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	$("#agtType").hide();
				}else if(${office.parent.type == 4}){
					document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('代理商',4));
			    	$("#agtType").show();
				}else{
					
				}
			}
		});
		function typeChange(){
	        var typeVal = $("#type").val();
	        if(typeVal == '4'){//代理商
	        	$("#agtType").show();
	        }else{
	        	$("#agtType").hide();
	        }
	    }
		
		function getTypeCallBack(value){
			$("#type").val(value);
			if(${office.id == null}){		
			    if(value == 1){//总公司		    	
			    	document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('分公司',2));
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	$("#agtType").hide();		    	
			    }else if(value == 2){//分公司		    	
			    	document.getElementById("type").options.length = 0;
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	document.getElementById("type").options.add(new Option('代理商',4));
			    	$("#agtType").hide();
			    }else if(value == 3){//部门
			    	document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('部门',3));
			    	$("#agtType").hide();
			    }else if(value == 4){//代理商
			    	document.getElementById("type").options.length = 0; 
			    	document.getElementById("type").options.add(new Option('代理商',4));
			    	$("#agtType").show();
			    }else{
			    	
			    }
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/office/list?id=${office.parent.id}&parentIds=${office.parentIds}">机构列表</a></li>
		<li class="active"><a href="${ctx}/sys/office/form?id=${office.id}&parent.id=${office.parent.id}">机构<shiro:hasPermission name="sys:office:edit">${not empty office.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:office:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="office" action="${ctx}/sys/office/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<!--  
		<sys:message content="${message}"/>
		-->
		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
				<c:if test="${office.id == null}">
                <sys:treeselect id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name" labelValue="${office.parent.name}"
					title="机构" url="/sys/office/treeData" extId="${office.id}" cssClass="required" allowClear="${office.currentUser.admin}"/>
				</c:if>
				<c:if test="${office.id != null}">
					<label style="font-size:20px;">${office.parent.name}</label>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>				
			</div>
		</div>
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
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!--  
		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		-->
		<c:if test="${office.id == null}">
		<div class="control-group">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<form:select path="type" class="input-medium" onchange="typeChange()">
					<!--  
					<form:option value="" label=""/>					
					<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					-->
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>			
		</div>
		</c:if>
		<c:if test="${office.id != null}">
			<div class="control-group">
				<label class="control-label">机构类型:</label>
				<div class="controls">
					<form:select path="type" class="input-medium" onchange="typeChange()" disabled="true"> 			
						<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>			
			</div>
		</c:if>
		<c:if test="${office.id == null}">
			<div id="agtType" class="control-group">
				<label class="control-label">代理商类型:</label>
				<div class="controls">
					<form:select path="agtType" class="input-medium">						  
						<form:options items="${fns:getDictList('sys_agt_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${office.id != null}">
			<div id="agtType" class="control-group">
				<label class="control-label">代理商类型:</label>
				<div class="controls">
					<form:select path="agtType" class="input-medium" disabled="true">					  
						<form:options items="${fns:getDictList('sys_agt_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<!--  
		<div class="control-group">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主负责人:</label>
			<div class="controls">
				 <sys:treeselect id="primaryPerson" name="primaryPerson.id" value="${office.primaryPerson.id}" labelName="office.primaryPerson.name" labelValue="${office.primaryPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副负责人:</label>
			<div class="controls">
				 <sys:treeselect id="deputyPerson" name="deputyPerson.id" value="${office.deputyPerson.id}" labelName="office.deputyPerson.name" labelValue="${office.deputyPerson.name}"
					title="用户" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="50" cssClass="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!--  
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="controls">
				<form:input path="zipCode" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		-->
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
		<!--  
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<!--  
		<c:if test="${empty office.id}">
			<div class="control-group">
				<label class="control-label">快速添加下级部门:</label>
				<div class="controls">
					<form:checkboxes path="childDeptList" items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</c:if>
		-->
		<div class="form-actions">
			<shiro:hasPermission name="sys:office:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>