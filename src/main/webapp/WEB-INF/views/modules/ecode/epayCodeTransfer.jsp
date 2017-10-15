<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>E码付管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchForm").validate({
				submitHandler: function(form){
                   	if ($("#amount").val()=="" || $("#amount").val()==0){
                        $("#amount").focus();
                        top.$.jBox.tip('请输入划拨数量!','warning');
                    }else if ($("#amount").val() > ${amount}){
                        $("#amount").focus();
                        top.$.jBox.tip('输入的数量超过了可划拨数量!','warning');
                    }
                    else{
                        loading('正在提交，请稍等...');
                        form.submit();
                    }
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ecode/epayCode/">库存列表</a></li> 
		<shiro:hasPermission name="ecode:epayCode:edit"><li class="active"><a href="${ctx}/ecode/epayCode/from">库存划拨</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="epayCode" action="${ctx}/ecode/epayCode/transfer" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>  
		<ul class="ul-form">			
			<li><label>划拨机构：</label><sys:treeselect id="transferOffice" name="transferOffice.id" value="" labelName="transferOffice.name" labelValue="" 
				title="划拨机构" url="/ecode/epayCode/treeData" cssClass="input-small" allowClear="true"/></li>
			<li><label>划拨数量：</label>
				<form:input path="amount" htmlEscape="false" type="number" maxlength="10" class="input-medium"/>
			</li>
			<li>
				<span ><b>&nbsp;&nbsp;&nbsp;&nbsp; 可划拨数量：${amount}&nbsp;&nbsp;</b> </span>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="划拨"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>划拨机构</th>
				<th>划拨数量</th>
				<th>批次号</th>				
				<th>更新时间</th>
				<!--  
				<shiro:hasPermission name="ecode:epayCode:edit"><th>操作</th></shiro:hasPermission>
				-->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="transferDetail">
			<tr>
				<td>
					${transferDetail.transferOffice.name}
				</td>
				<td>
					${transferDetail.amount}
				</td>
				<td>
					${transferDetail.batchNo}
				</td>
				<td>
					<fmt:formatDate value="${transferDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!--  
				<shiro:hasPermission name="ecode:epayCode:edit"><td>
    				<a href="${ctx}/ecode/epayCode/form?id=${epayCode.id}">修改</a>
					<a href="${ctx}/ecode/epayCode/delete?id=${epayCode.id}" onclick="return confirmx('确认要删除该商户二维码吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				-->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>