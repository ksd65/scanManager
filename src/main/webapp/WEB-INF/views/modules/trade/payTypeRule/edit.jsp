<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getRouteMerchantList('routeCode','merchantCode','');
			//addItem1();
			
		});
		
		function formatMoneyWith2digts( obj ){  
		    var p1 = /[^\d\.]/g;    // 过滤非数字及小数点 /g :所有范围中过滤  
		    var p2 = /(\.\d{2})\d*$/g;  
		    var p4 = /(\.)(\d*)\1/g;

		    var len1 = obj.value.substr(0,1);  
		    var len2 = obj.value.substr(1,1);  
		    if(obj.value.length > 1 && len1==0 && len2 != '.'){  
		    	obj.value=obj.value.replace(obj.value, '0.');
		    } 
		    obj.value = obj.value.replace(p1, "").replace(p2, "$1").replace(p4,"$1$2");  
		      
		  
		    obj.value=obj.value.replace(/[^0-9.]/g, '');  
		  
		  
		    // fix bug: many char'.'  
		    var p5 = /\.+/g;    //多个点的话只取1个点，屏蔽1....234的情况  
		    obj.value = obj.value.replace(p5, ".")  
		  
		    var p6 = /(\.+)(\d+)(\.+)/g; //屏蔽1....234.的情况  
		    obj.value = obj.value.replace(p6, "$1$2")// 屏蔽最后一位的.  
		    // end fix bug: many char'.'  
		}
		
		
		
		function showLimit(val){
			if(val=='1'){
				$("#limitTr").show();
			}else{
				$("#limitTr").hide();
			}
		}
		
		
		function addItem1(){
			var addNumberTemp = $($("tbody.payBak")[0]);
			var dateTime = new Date().getTime();
			var str = "<tbody class=\"pay\" id=\""+dateTime+"\">";
			str+="<tr><td class=\"info-table-label\"><div class=\"text-right\">交易通道:</div></td><td width=\"30%\">";
			str+="<select id=\"routeCode_"+dateTime+"\" name=\"routeCode\" class=\"input-medium\" onchange=\"getRouteMerchantList('routeCode_"+dateTime+"','merchantCode_"+dateTime+"','')\">";		
			$("#routeCode option").each(function() {
				str+="<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
			});
			str+="</select></td>";
			str+="<td class=\"info-table-label\"><div class=\"text-right\">子商户:</div></td><td width=\"30%\">";
			str+="<select id=\"merchantCode_"+dateTime+"\" name=\"merchantCode\" class=\"input-xlarge\">";
			$("#merchantCode option").each(function() {
				str+="<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
			});
			str+="</select></td></tr>";
			
			str+="<tr><td class=\"info-table-label\"><div class=\"text-right\">轮询概率:</div></td><td width=\"30%\">";
			str+="<input id=\"ruleRate_"+dateTime+"\" name=\"ruleRate\" class=\"input-medium\" type=\"text\" value=\"\" maxlength=\"9\" onkeyup=\"formatMoneyWith2digts(this);\"></td>";
			str+="<td class=\"info-table-label\" colspan=\"2\"><input id=\"btndel_"+dateTime+"\" class=\"btn\" type=\"button\" value=\"删 除\" onclick=\"deleteItem('"+dateTime+"')\"/></td>";
			str+="</tr></tbody>";
			$("#mtb").append(str);
			$("#routeCode_"+dateTime).select2();
		//	$("#merchantCode_"+dateTime).select2();
			
		}
		
		function deleteItem(oid){
			$("#"+oid).remove();
		}
		
		
		function saveMem(){
			var list = new Array();
			var payMethod = $.trim($("#payMethod").val());
			if(payMethod==""){
				alert("请选择交易方式");
				$("#payMethod").focus();
				return;
			}
			var payType = $.trim($("#payType").val());
			if(payType==""){
				alert("请选择交易类型");
				$("#payType").focus();
				return;
			}
			var memberId = $.trim($("#memberId").val());
			if(memberId==""){
				alert("请选择商户");
				$("#memberId").focus();
				return;
			}
			var str = "";
			var flag = true;
			var totalRate = 0;
			$("tbody.pay").each(function() {
				var routeCodeObj =  $(this).find("select[name='routeCode']");
				var routeCode = $.trim(routeCodeObj.val());
				if(routeCode == ""){
					flag = false;
					alert("请选择交易通道");
					routeCodeObj.focus();
					return false;
				}
				var merchantCodeObj =  $(this).find("select[name='merchantCode']");
				var merchantCode = $.trim(merchantCodeObj.val());
				if(merchantCode == ""){
					flag = false;
					alert("请选择子商户");
					merchantCodeObj.focus();
					return false;
				}
				var ruleRateObj =  $(this).find("input[name='ruleRate']");
				var ruleRate = $.trim(ruleRateObj.val());
				if(ruleRate == ""){
					flag = false;
					alert("请输入轮询概率");
					ruleRateObj.focus();
					return false;
				}
				var arr = routeCode.split("_");
				routeCode = arr[0];
				var aisleType = " ";
				if(arr.length>1){
					aisleType = arr[1];
				}
				totalRate = totalRate + Number(ruleRate);
				str = str + routeCode +"#"+aisleType+"#"+merchantCode+"#"+ruleRate+";";
			});
			if(!flag){
				return;
			}
			if(str == ""){
				alert("请配置通道交易商户信息");
				return;
			}
			if(totalRate!=1){
				alert("概率和必须为1");
				return;
			}
			$.ajax({
				url:"${ctx }/paytype/rule/saveMem",
				data:{payMethod:payMethod,payType:payType,memberId:memberId,merInfo:str},
				type:'post',
				cache:false,
				async:false,
				dataType:'json',
				success:function(data) {
					if(data.returnCode=="0000"){//请求成功
						alert("规则配置成功");
						window.location.href="${ctx}/paytype/rule/";
					}else{
						alert(data.returnMsg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			        alert("请求出错");
			    }
			});

		}
		
		function getRouteMerchantList(oid,mid,dvalue){
			
			//$("#"+mid).text("");
			var routeCode = $("#"+oid).val();
			var subHtml = "";
			if(routeCode=="" || routeCode==""){
				$("#"+mid).html(subHtml);
				return;
			}
			routeCode = routeCode.split("_")[0];
			$.ajax({
				url:"${ctx }/paytype/rule/getRouteMerchantList",
				data:{routeCode:routeCode},
				type:'post',
				cache:false, 
				async:false,
				dataType:'json',
				success:function(data) {
					
					var list = data.merList;
					for(var i=0;i<list.length;i++){
						var sel = "";
						if(dvalue==list[i].merchantCode){
							sel = "selected='selected'";
						}
						subHtml = subHtml+ "<option value=\""+list[i].merchantCode+"\" "+sel+">"+list[i].merchantName+"("+list[i].merchantCode+")"+"</option>";	
					}
					$("#"+mid).html(subHtml);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			       // alert("请求出错"+textStatus);
			    }
			});

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/paytype/rule/">通道规则列表</a></li>
		<li  class="active"><a href="${ctx}/paytype/rule/form">通道规则添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="payTypeRule" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered info-table" align="center" id="mtb">
			<tr>
	        	<td colspan="4" class="info-table-group"><div class="text-center">交易信息</div></td>
	        </tr>
        
	        <tr>
				<td class="info-table-label"><div class="text-right">交易方式:</div></td>
				<td width="30%">
					<form:select id="payMethod" path="payMethod" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('rule_txn_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td class="info-table-label"><div class="text-right">交易类型:</div></td>
				<td width="30%">
					<form:select id="payType" path="payType" class="input-medium">
						<form:option value="" label="请选择"/>
						<form:options items="${fns:getDictList('rule_txn_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">商户:</div></td>
				<td width="30%">
					<form:select id="memberId" path="memberId" class="input-medium">
						<form:option value="0" label="默认商户"/>
						<form:options items="${memberList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
				</td>
				<td class="info-table-label" colspan="2"></td>
				
			</tr>
			
			
			
			<tr>
				<td colspan="4" class="info-table-group"><div class="text-center">通道商户配置信息
					<input id="btnAdd" class="btn" type="button" value="添 加" onclick="addItem1()"/>
					<span style="color:#FF0000"><b>&nbsp&nbsp轮询概率输入格式：例如80%请输入0.8，所有子商户概率和必须为1</b> </span>
				</div>
				</td>
	        </tr>
	        <tbody class="payBak hide">
	        <tr>
				<td class="info-table-label"><div class="text-right">交易通道:</div></td>
				<td width="30%">
					<select id="routeCode" name="routeCode" class="input-medium" onchange="getRouteMerchantList('routeCode','merchantCode')">
						<c:forEach items="${routeList}" var="mlist">
							<option value="${mlist.routeCode }_${mlist.aisleType }">${mlist.routeName }</option>
						</c:forEach>
					</select>
				</td>
				<td class="info-table-label"><div class="text-right">子商户:</div></td>
				<td width="30%">
					<select id="merchantCode" name="merchantCode" class="input-medium">
						
					</select>
				</td>
			</tr>
	        <tr>
				<td class="info-table-label"><div class="text-right">轮询概率:</div></td>
				<td width="30%">
					<input id="ruleRate" name="ruleRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);">
				</td>
				<td class="info-table-label" colspan="2"></td>
				
			</tr>
			
			
			</tbody>
			
			<c:forEach items="${ruleList }" var="plist" varStatus="status">
				
				<tbody class="pay" id="${status.index }">
				
					<tr>
						<td class="info-table-label"><div class="text-right">交易通道:</div></td>
						<td width="30%">
							<select id="routeCode_${status.index }" name="routeCode" class="input-medium" onchange="getRouteMerchantList('routeCode_${status.index }','merchantCode_${status.index }','')">
								<c:forEach items="${routeList}" var="mlist">
									<c:choose>
										<c:when test="${plist.routeCode==mlist.routeCode }">
											<option value="${mlist.routeCode }_${mlist.aisleType }" selected="selected">${mlist.routeName }</option>
										</c:when>
										<c:otherwise>
											<option value="${mlist.routeCode }_${mlist.aisleType }">${mlist.routeName }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
						<td class="info-table-label"><div class="text-right">子商户:</div></td>
						<td width="30%">
							<select id="merchantCode_${status.index }" name="merchantCode" class="input-xlarge">
								
							</select>
						</td>
					</tr>
			        <tr>
						<td class="info-table-label"><div class="text-right">轮询概率:</div></td>
						<td width="30%">
							<input id="ruleRate_${status.index }" name="ruleRate" class="input-medium" type="text" value="${plist.ruleRate }" maxlength="9" onkeyup="formatMoneyWith2digts(this);">
						</td>
						<td class="info-table-label" colspan="2">
						<input id="btndel_${status.index }" class="btn" type="button" value="删  除" onclick="deleteItem('${status.index }')"/>
						</td>
						
					</tr>
				</tbody>
				<script type="text/javascript">
					getRouteMerchantList('routeCode_${status.index }','merchantCode_${status.index }','${plist.merchantCode}')
				</script>
				
				
			
			</c:forEach>
			
			
			
		</table>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="mem:member:edit"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="saveMem()" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>