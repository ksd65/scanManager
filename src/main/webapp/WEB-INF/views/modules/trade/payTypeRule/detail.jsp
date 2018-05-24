<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//getRouteMerchantList('routeCode','merchantCode','');
			//addItem1();
			
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/paytype/rule/">通道规则列表</a></li>
		<li  class="active"><a href="${ctx}/paytype/rule/form">通道规则添加</a></li>
	</ul><br/>
	<table class="table table-bordered info-table" align="center" id="mtb">
			<tr>
	        	<td colspan="4" class="info-table-group"><div class="text-center">交易信息</div></td>
	        </tr>
        
	        <tr>
				<td class="info-table-label"><div class="text-right">交易方式:</div></td>
				<td width="30%">
					${fns:getDictLabel(payTypeRule.payMethod,'rule_txn_method',payTypeRule.payMethod)}
				</td>
				<td class="info-table-label"><div class="text-right">交易类型:</div></td>
				<td width="30%">
					${fns:getDictLabel(payTypeRule.payType,'rule_txn_type',payTypeRule.payType)}
				</td>
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">商户:</div></td>
				<td width="30%">
					${payTypeRule.memberName }
				</td>
				<td class="info-table-label" colspan="2"></td>
				
			</tr>
			
			<tr>
				<td colspan="4" class="info-table-group"><div class="text-center">通道商户配置信息
	
				</div>
				</td>
	        </tr>
	        
			<c:forEach items="${ruleList }" var="plist" varStatus="status">
				
				<tbody class="pay" id="${status.index }">
				
					<tr>
						<td class="info-table-label"><div class="text-right">交易通道:</div></td>
						<td width="30%">
							${fns:getDictLabel(plist.routeCode,'route_code',plist.routeCode)}
						</td>
						<td class="info-table-label"><div class="text-right">子商户:</div></td>
						<td width="30%">
							${plist.merchantCode }
						</td>
					</tr>
			        <tr>
						<td class="info-table-label"><div class="text-right">轮询概率:</div></td>
						<td width="30%">
							${plist.ruleRate }
						</td>
						<td class="info-table-label" colspan="2">
						</td>
						
					</tr>
				</tbody>
				
			
			</c:forEach>
			
			
			
		</table>
		
		
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	
</body>
</html>