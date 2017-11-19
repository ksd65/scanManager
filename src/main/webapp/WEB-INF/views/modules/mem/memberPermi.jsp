<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
	<meta name="decorator" content="default"/>
	<%--FIXME 样式地方--%>
	<style>
		.cert-info .block{
			width: 18%;
			margin-left: 2%;
			text-align: center;
			float: left;
		}
	</style>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mem/member/">商户信息列表</a></li>
		<li class="active"><a href="${ctx}/mem/member/permi?id=${member.id}">商户权限设置</a></li>
	</ul><br/>

	<form:form id="inputForm" modelAttribute="member" action="${ctx}/mem/member/savePermi" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<div class="control-group">
			<label class="control-label">商户状态:</label>
			<div class="controls">
				<select id="m-status" name="status" style="width: 200px;">
					<c:forEach items="${fns:getDictList('member_status')}" var="status">
						<option value="${status.value}" <c:if test="${status.value==member.status }">selected=\"selected\"</c:if>>${status.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提现状态:</label>
			<div class="controls">
				<select id="m-status" name="status" style="width: 200px;">
					<c:forEach items="${fns:getDictList('draw_status')}" var="status">
						<option value="${status.value}" <c:if test="${status.value==member.drawStatus }">selected=\"selected\"</c:if>>${status.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单笔限额:</label>
			<div class="controls">
				<input id="m-sing-limit" type="number" name="singleLimit"   value="${member.singleLimit }"  style="text-align: center;width: 180px;">元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单日限额:</label>
			<div class="controls">
				<input id="m-day-limit" type="number" name="dayLimit"  value="${member.dayLimit }" style="text-align: center;width: 180px;"/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间段限额限额:</label>
			<div class="controls"><input id="addbtn" class="btn btn-primary" type="button" value="增加" onclick="addItem();"/></div>
			
		</div>
		<div id="timediv">
		<div class="control-group timeareaBak hide">
			<label class="control-label">时间段: </label>
			<div class="controls">
				<input type="text" id="timeRange" name="timeRange" style="text-align: center;width: 180px;">
				&nbsp;&nbsp;限额: <input id="timeLimit" type="number" name="timeLimit"  value="" style="text-align: center;width: 180px;"/>元
				&nbsp;&nbsp;<input id="delbtn" class="btn btn-primary" type="button" value="删除" onclick="removeItem(this)"/>
			</div>
		</div>
		</div>
		<div class="text-center">
	        <input class="btn" type="submit" value="保 存"/>
	        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	    </div>
	</form:form>
	
<script type="text/javascript">
//执行一个laydate实例
laydate.render({
	elem: '#timearea_1'
		  ,type: 'time'
		  ,range: true
});

function addItem(){
	$("div.timeareaBak").clone(true).appendTo($("#timediv"));
	// 处理从复制源复制出来的新模板
	var newAddNumber = $($("div.timeareaBak")[$("div.timeareaBak").length-1]);
	newAddNumber.removeClass("hide").removeClass("timeareaBak").addClass("timearea");
	var dateTime = new Date().getTime();
	newAddNumber.attr("id",dateTime);
	newAddNumber.find("input[name='timeRange']").attr("id","timeRange_" + dateTime);
	newAddNumber.find("input[name='timeLimit']").attr("id","timeLimit_" + dateTime);
	laydate.render({
		elem: '#timeRange_'+dateTime,
		type: 'time',
		range: true
	});
	
}

function removeItem(obj){
	$(obj).closest("div.timearea").remove(); 
}


</script>
</body>
</html>