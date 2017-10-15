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
		<li class="active"><a href="javascript:;">我的分润</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="payBillMonthMyProfitRpt" action="${ctx}/report/payBillMonthMyProfitRpt/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden"/>
		<ul class="ul-form">
			<li><label>年份：</label>
				<form:input id="year" path="year" type="text" readonly="readonly" maxlength="6" class="input-medium Wdate"
					value="${payBillMonthMyProfitRpt.year}"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true});"/>
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
				<th>月份</th>
				<th>所属机构</th>
				
				<th>D0交易笔数</th>
				<th>D0交易金额</th>
				<th>T1交易笔数</th>
				<th>T1交易金额</th>
				
				<th>快捷(有积分)交易笔数</th>
				<th>快捷(有积分)交易金额</th>
				<th>快捷(无积分)交易笔数</th>
				<th>快捷(无积分)交易金额</th>
				
				<th>交易总笔数</th>
				<th>交易总金额</th>
				
				<th>D0预期分润</th>
				<th>T1预期分润</th>
				<th>快捷(有积分)预期分润</th>
				<th>快捷(无积分)预期分润</th>
				
				<th>预期分润合计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payBillMonthProfitRpt">
			<tr>
				<td>
					${payBillMonthProfitRpt.month}
				</td>
				<td>
					${payBillMonthProfitRpt.office.name}
				</td>
				<td>
					${payBillMonthProfitRpt.d0Count}
				</td>
				<td>
					${payBillMonthProfitRpt.d0Amount}
				</td>
				<td>
					${payBillMonthProfitRpt.t1Count}
				</td>
				<td>
					${payBillMonthProfitRpt.t1Amount}
				</td>
				<!-- 快捷 -->
				<td>
					${payBillMonthProfitRpt.mlJfCount}
				</td>
				<td>
					${payBillMonthProfitRpt.mlJfAmount}
				</td>
				<td>
					${payBillMonthProfitRpt.mlWjfCount}
				</td>
				<td>
					${payBillMonthProfitRpt.mlWjfAmount}
				</td>
				
				<td>
					${payBillMonthProfitRpt.totalCount}
				</td>
				<td>
					${payBillMonthProfitRpt.totalAmount}
				</td>
				
				<td>
					${payBillMonthProfitRpt.d0Profit}
				</td>
				<td>
					${payBillMonthProfitRpt.t1Profit}
				</td>
				<!-- 快捷 -->
				<td>
					${payBillMonthProfitRpt.mlJfProfit}
				</td>
				<td>
					${payBillMonthProfitRpt.mlWjfProfit}
				</td>
				
				<td>
					${payBillMonthProfitRpt.totalProfit}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>