<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报表统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		// 提交表单
		function submitForm(type){
			$("#type").val(type);
			$("#searchForm").submit();
		}
		
		function openWin(iWidth,iHeight,url){
			 var name="和融通每日交易统计";                    //网页名称，可为空;
		     //var iWidth=900;                        //弹出窗口的宽度;
		     //var iHeight=250;                       //弹出窗口的高度;
		     //获得窗口的垂直位置
		     var iTop = (window.screen.availHeight-100-iHeight)/2;
		     //获得窗口的水平位置
		     var iLeft = (window.screen.availWidth-10-iWidth)/2;
		     var params='width='+iWidth
         +',height='+iHeight
         +',top='+iTop
         +',left='+iLeft
         +',channelmode=yes'//是否使用剧院模式显示窗口。默认为 no
         +',directories=no'//是否添加目录按钮。默认为 yes
         +',fullscreen=no' //是否使用全屏模式显示浏览器
         +',location=no'//是否显示地址字段。默认是 yes
         +',menubar=no'//是否显示菜单栏。默认是 yes
         +',resizable=no'//窗口是否可调节尺寸。默认是 yes
         +',scrollbars=yes'//是否显示滚动条。默认是 yes
         +',status=yes'//是否添加状态栏。默认是 yes
         +',titlebar=yes'//默认是 yes
         +',toolbar=no'//默认是 yes
		  ;
		   window.open(url,name,params);
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">分润明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="payBillMonthDetailRpt" action="${ctx}/report/payBillMonthProfitRpt/profitDetail" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="month" name="month" type="hidden" value="${payBillMonthDetailRpt.month }"/>
		<input id="type" name="type" type="hidden"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${payBillMonthDetailRpt.office.id}" labelName="office.name" labelValue="${payBillMonthDetailRpt.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>商户名称：</label>
				<form:input id="memberName" path="memberName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('0')" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('1')" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易日期</th>
				<th>所属机构</th>
				<th>商户名称</th>
				<th>T1交易金额</th>
				<th>T1分润</th>
				<th>D0交易金额</th>
				<th>D0分润</th>
				
				<th>快捷(有积分)交易金额</th>
				<th>快捷(有积分)分润</th>
				<th>快捷(无积分)交易金额</th>
				<th>快捷(无积分)分润</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payBillMonthDetailRpt">
			<tr>
				<td>
					${payBillMonthDetailRpt.settleDate}
				</td>
				<td>
					${payBillMonthDetailRpt.office.name}
				</td>
				<td>
					${payBillMonthDetailRpt.memberName}
				</td>
				<td>
					${payBillMonthDetailRpt.t1Amount}
				</td>
				<td>
					${payBillMonthDetailRpt.t1Profit}
				</td>
				<td>
					${payBillMonthDetailRpt.d0Amount}
				</td>
				<td>
					${payBillMonthDetailRpt.d0Profit}
				</td>
				<!-- 快捷 -->
				<td>
					${payBillMonthDetailRpt.mlJfAmount}
				</td>
				<td>
					${payBillMonthDetailRpt.mlJfProfit}
				</td>
				<td>
					${payBillMonthDetailRpt.mlWjfAmount}
				</td>
				<td>
					${payBillMonthDetailRpt.mlWjfProfit}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>