<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="/scanManager/static/ECharts/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 年月
			var listMonth = ${listMonth};
			// 数据
			var wxData  = ${wxData};
			var qqData  = ${qqData};
			var zfbData  = ${zfbData};
			
			var bounsQuickData  = ${bounsQuickData};
			var quickData  = ${quickData};
			var jdData     = ${jdData};
			var bdData     = ${bdData};
			
			var allData  = ${allData};
			
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
			
	     	// 指定图表的配置项和数据
	        var option = {
	        		color: ['#00a0e9','#00b419','#003d3d','#8B0000','#9932CC','#ff3d3d','#00b419'],
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        x: 'middle',
				        padding: [10, 10, 0, -80],
				        data: ['支付宝','微信','QQ','快捷(有积分)','快捷(无积分)','京东钱包','百度钱包','合计'],
				        selected: {
				            '支付宝': true,
				            '微信': true,
				            'QQ': true,
				            '快捷(有积分)':true,
				            '快捷(无积分)':true,
				            '京东钱包':true,
				            '百度钱包':true,
				            '合计': true,
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
				        name: '交易流水（万元）',
				        splitLine: { //网格线
				            show: true,
				            lineStyle: {
				                color: ['#b1b1b1'],
				                type: 'dashed'
				            }
				        }
				    },
				    series: [{
				        name: '支付宝',
				        type: 'line',
				        data: zfbData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },
				    {
				        name: '微信',
				        type: 'line',
				        data: wxData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },
				    {
				        name: 'QQ',
				        type: 'line',
				        data: qqData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },
				    
				    {
				        name: '快捷(有积分)',
				        type: 'line',
				        data: bounsQuickData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },{
				        name: '快捷(无积分)',
				        type: 'line',
				        data: quickData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },{
				        name: '京东钱包',
				        type: 'line',
				        data: jdData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },{
				        name: '百度钱包',
				        type: 'line',
				        data: bdData,
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        }
				    },
				    {
				        name: '合计',
				        type: 'line',
				        data: allData,
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
		<li class="active"><a href="${ctx}/trade/tradeDetail/monthTradeMoney">平台流水统计</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th></th>
				<c:forEach items="${list}" var="tradeDetail">
					<th>${tradeDetail.txMonth }</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>支付宝</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.zfbMoney}
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td>微信</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.wxMoney}
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td>QQ</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.qqMoney}
					</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td>快捷(有积分)</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.bounsQuickMoney}
					</td>
				</c:forEach>
			</tr>
			
			<tr>
				<td>快捷(无积分)</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.quickMoney}
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td>京东钱包</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.jdMoney}
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td>百度钱包</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.bdMoney}
					</td>
				</c:forEach>
			</tr>
			<tr>
				<td>合计</td>
				<c:forEach items="${list}" var="tradeDetail">
					<td>
						${tradeDetail.allMoney}
					</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<div id="main"style="height:400px;border:1px solid #ccc;"></div>
</body>
</html>
