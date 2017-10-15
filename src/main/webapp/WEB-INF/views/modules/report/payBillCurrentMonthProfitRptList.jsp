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
	<script type="text/javascript">
        function getSumData(){
            var officeId = $("#officeIdId").val();
            //var memberCode = $("#memberCode").val();
            var beginTime = $("#beginTime").val();
            var endTime = $("#endTime").val();

            $.ajax({
                type: "post",
                async: false,
                url: "${ctx}/report/payBillCurrentMonthProfitRpt/getSumData",
                data: "officeId="+officeId+"&beginTime="+beginTime+"&endTime="+endTime,
                success: function(data) {
                    $("#sumCount").text(data.sumMoney);
                    $("#sumMoney").text(data.sumProfit);

                    $('#sumDataModal').modal('toggle').modal('show');
                },error:function(){
                    top.$.jBox.tip("网络异常");
                }
            });

        }
	</script>
</head>
<body>

<!-- 模态框（Modal） -->
<div class="modal fade" id="sumDataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
				</button>
				<h4 class="modal-title" id="myModalLabel">统计数据</h4>
			</div>
			<div class="modal-body">
				<div style='text-align: center;'>

					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>
							<th style="text-align: center;">交易总金额</th>
							<th style="text-align: center;">分润总金额</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td id="sumCount" style="text-align: center;"></td>
							<td id="sumMoney" style="text-align: center;"></td>
						</tr>
						</tbody>
					</table>

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:;">单月分润统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="payBillCurrentMonthProfitRpt" action="${ctx}/report/payBillCurrentMonthProfitRpt/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="type" name="type" type="hidden"/>
		<ul class="ul-form">
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${payBillCurrentMonthProfitRpt.office.id}" labelName="office.name" labelValue="${payBillCurrentMonthProfitRpt.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>交易日期：</label>
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payBillCurrentMonthProfitRpt.beginTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			<span>到</span>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${payBillCurrentMonthProfitRpt.endTime}"
					onclick="WdatePicker({dateFmt:'yyyyMMdd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('0')" value="查询"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitForm('1')" value="导出"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="getSumData();" value="统计"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>交易日期</th>
				<%--<th>商户名称</th>--%>
				<th>所属机构</th>
				<th>D0交易金额</th>
				<th>D0交易分润</th>
				<th>T1交易金额</th>
				<th>T1交易分润</th>

				<th>快捷(有积分)交易金额</th>
				<th>快捷(有积分)交易分润</th>
				<th>快捷(无积分)交易金额</th>
				<th>快捷(无积分)交易分润</th>


				<th>交易金额</th>
				<th>分润金额合计</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payBillCurrentMonthProfitRpt">
			<tr>

				<td>
						${payBillCurrentMonthProfitRpt.txDate}
				</td>
				<%--<td>--%>
					<%--${payBillCurrentMonthProfitRpt.memberName}--%>
				<%--</td>--%>
				<td>
					${payBillCurrentMonthProfitRpt.office.name}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.d0Amount}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.d0Profit}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.t1Amount}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.t1Profit}
				</td>
				<!-- 快捷 -->
				<td>
						${payBillCurrentMonthProfitRpt.mlJfAmount}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.mlJfProfit}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.mlWjfAmount}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.mlWjfProfit}
				</td>
				<td>
					${payBillCurrentMonthProfitRpt.mlJfAmount + payBillCurrentMonthProfitRpt.mlWjfAmount
					+payBillCurrentMonthProfitRpt.d0Amount + payBillCurrentMonthProfitRpt.t1Amount}
				</td>
				<td>
						${payBillCurrentMonthProfitRpt.mlJfProfit + payBillCurrentMonthProfitRpt.mlWjfProfit
								+payBillCurrentMonthProfitRpt.d0Profit + payBillCurrentMonthProfitRpt.t1Profit}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>


</html>