<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="/scanManager/static/ECharts/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 年月
			var listMonth = ${listMonth};
			// 数据
			var listData  = ${listData};
			
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
			
	     	// 指定图表的配置项和数据
	        var option = {
	        		color: ['#ff3d3d'],
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        x: 'middle',
				        padding: [10, 20, 0, -30],
				        data: ['用户数量'],
				        selected: {
				            '用户数量': true,
				        }
				    },
				    grid: {
				        left: '2%',
				        right: '2%',
				        bottom: '3%',
				        top: '13%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        splitLine: { //网格线
				            show: true,
				            lineStyle: {
				                color: ['#b1b1b1'],
				                type: 'dashed'
				            }
				        },
				        data:listMonth
				    },
				    yAxis: {
				    	type: 'value',
				        name: '用户增长（万户）',
				        splitLine: { //网格线
				            show: true,
				            lineStyle: {
				                color: ['#b1b1b1'],
				                type: 'dashed'
				            }
				        }
				    },
				    series: [{
				        name: '用户数量',
				        type: 'line',
				        data: listData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    }]
			};			    
	     	// 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mem/member/monthMemberNum">平台商户统计</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>年月</th>
				<c:forEach items="${list}" var="member">
					<th>${member.txMonth }</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
		
			<tr>
			<td>用户数量</td>
			<c:forEach items="${list}" var="member">
				<td>
					${member.num}
				</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<div id="main"style="height:400px;border:1px solid #ccc;"></div>
</body>
</html>