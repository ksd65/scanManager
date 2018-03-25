<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			addItem1();
			
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
		
		function formatMoneyWith4digts( obj ){  
		    var p1 = /[^\d\.]/g;    // 过滤非数字及小数点 /g :所有范围中过滤  
		    var p2 = /(\.\d{4})\d*$/g;  
		    var p4 = /(\.)(\d*)\1/g;

		    var len1 = obj.value.substr(0,1);  
		    var len2 = obj.value.substr(1,1);
		    var len3 = obj.value.substr(0,3);
		    var len4 = obj.value.substr(0,4);
		    if(obj.value.length > 0 && len1!=0 ){  
		    	obj.value=obj.value.replace(obj.value, '0.00');
		    }
		    if(obj.value.length > 1 && len1==0 && len2 != '.'){  
		    	obj.value=obj.value.replace(obj.value, '0.00');
		    }
		    if(obj.value.length > 2 && len3 != '0.0'){  
		    	obj.value=obj.value.replace(obj.value, '0.00');
		    }
		   // if(obj.value.length > 3 && len4 != '0.00'){  
		   // 	obj.value=obj.value.replace(obj.value, '0.00');
		  //  }
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
		
		function addItem(){
			var addNumberTemp = $($("tbody.payBak")[0]);
			$("tbody.payBak").clone(true).appendTo($("#mtb"));
			// 处理从复制源复制出来的新模板
			var newAddNumber = $($("tbody.payBak")[$("tbody.payBak").length-1]);
			newAddNumber.removeClass("hide").removeClass("payBak").addClass("pay");
			var dateTime = new Date().getTime();
			newAddNumber.attr("id",dateTime);
			//newAddNumber.find("tr[id='routeTr']").attr("id","routeTr" + dateTime);
			//newAddNumber.find("tr[id='limitTr']").attr("id","limitTr" + dateTime);
			$(newAddNumber.find("input[type='text']")).each(function (i){
				var tid = $(this).attr("id");
				$(this).attr("id",tid+"_"+dateTime);
			});
			$(newAddNumber.find("select")).each(function (i){
				var tid = $(this).attr("id");
				$(this).attr("id",tid+"_"+dateTime);
			//	$(this).select2();
				
			});
			$(newAddNumber.find("select")).each(function (i){
				var tid = $(this).attr("id");
				
			});
			
		}
		
		function addItem1(){
			var addNumberTemp = $($("tbody.payBak")[0]);
			var dateTime = new Date().getTime();
			var str = "<tbody class=\"pay\" id=\""+dateTime+"\">";
			str+="<tr><td class=\"info-table-label\"><div class=\"text-right\">交易方式:</div></td><td width=\"30%\">";
			str+="<select id=\"txnMethod_"+dateTime+"\" name=\"txnMethod\" class=\"input-medium\">";		
			$("#txnMethod option").each(function() {
				str+="<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
			});
			str+="</select></td>";
			str+="<td class=\"info-table-label\"><div class=\"text-right\">交易类型:</div></td><td width=\"30%\">";
			str+="<select id=\"txnType_"+dateTime+"\" name=\"txnType\" class=\"input-medium\">";
			$("#txnType option").each(function() {
				str+="<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
			});
			str+="</select></td></tr>";
			str+="<tr><td class=\"info-table-label\"><div class=\"text-right\">D0交易费率:</div></td><td width=\"30%\">";
			str+="<input id=\"t0TradeRate_"+dateTime+"\" name=\"t0TradeRate\" class=\"input-medium\" type=\"text\" value=\"\" maxlength=\"9\" onkeyup=\"formatMoneyWith4digts(this);\"></td>";
			str+="<td class=\"info-table-label\"><div class=\"text-right\">T1交易费率:</div></td><td width=\"30%\">";
			str+="<input id=\"t1TradeRate_"+dateTime+"\" name=\"t1TradeRate\" class=\"input-medium\" type=\"text\" value=\"\" maxlength=\"9\" onkeyup=\"formatMoneyWith4digts(this);\"></td>";
			str+="</tr>";
			
			str+="<tr><td class=\"info-table-label\"><div class=\"text-right\">D0提现手续费:</div></td><td width=\"30%\">";
			str+="<input id=\"t0DrawFee_"+dateTime+"\" name=\"t0DrawFee\" class=\"input-medium\" type=\"text\" value=\"\" maxlength=\"9\" onkeyup=\"formatMoneyWith2digts(this);\">元</td>";
			str+="<td class=\"info-table-label\"><div class=\"text-right\">T1提现手续费:</div></td><td width=\"30%\">";
			str+="<input id=\"t1DrawFee_"+dateTime+"\" name=\"t1DrawFee\" class=\"input-medium\" type=\"text\" value=\"\" maxlength=\"9\" onkeyup=\"formatMoneyWith2digts(this);\">元&nbsp;&nbsp;<input id=\"btndel_"+dateTime+"\" class=\"btn\" type=\"button\" value=\"删 除\" onclick=\"deleteItem('"+dateTime+"')\"/></td>";
			str+="</tr></tbody>";
			$("#mtb").append(str);
			$("#txnMethod_"+dateTime).select2();
			$("#txnType_"+dateTime).select2();
			
		}
		
		function deleteItem(oid){
			$("#"+oid).remove();
		}
		
		function contain(list,object){  
		    var i = 0;  
		    for (; i < list.length; i++) {  
		        if (list[i] === object) {  
		            break;  
		        }  
		    }  
		    if (i >= list.length) {  
		        return false;  
		    }  
		    return true;  
		} 
		
		function saveMem(){
			var list = new Array();
			var name = $.trim($("#name").val());
			if(name==""){
				alert("请输入商户名称");
				$("#name").focus();
				return;
			}
			var mobilePhone = $.trim($("#mobilePhone").val());
			if(mobilePhone==""){
				alert("请输入手机号码");
				$("#mobilePhone").focus();
				return;
			}
			var office = $.trim($("#officeId").val());
			if(office==""){
				alert("请选择机构");
				$("#office").focus();
				return;
			}
			var str = "";
			var flag = true;
			$("tbody.pay").each(function() {
				var methodObj =  $(this).find("select[name='txnMethod']");
				var txnMethod = $.trim(methodObj.val());
				if(txnMethod == ""){
					flag = false;
					alert("请选择交易方式");
					methodObj.focus();
					return false;
				}
				var typeObj =  $(this).find("select[name='txnType']");
				var txnType = $.trim(typeObj.val());
				if(txnType == ""){
					flag = false;
					alert("请选择交易类型");
					typeObj.focus();
					return false;
				}
				var t0Obj =  $(this).find("input[name='t0TradeRate']");
				var t0TradeRate = $.trim(t0Obj.val());
				if(t0TradeRate == ""){
					flag = false;
					alert("请输入D0交易费率");
					t0Obj.focus();
					return false;
				}
				var t1Obj =  $(this).find("input[name='t1TradeRate']");
				var t1TradeRate = $.trim(t1Obj.val());
				if(t1TradeRate == ""){
					flag = false;
					alert("请输入T1交易费率");
					t1Obj.focus();
					return false;
				}
				var t0DrawFee = $.trim($(this).find("input[name='t0DrawFee']").val());
				if(t0DrawFee==""){
					t0DrawFee=" ";
				}
				var t1DrawFee = $.trim($(this).find("input[name='t1DrawFee']").val());
				if(t1DrawFee==""){
					t1DrawFee=" ";
				}
				
				if(contain(list,txnMethod +"#"+txnType)){
					flag = false;
					alert("交易方式和类型重复选择");
					return false;
				}
				
				list[list.length] = txnMethod +"#"+txnType;
				str = str + txnMethod +"#"+txnType+"#"+t0TradeRate+"#"+t1TradeRate+"#"+t0DrawFee+"#"+t1DrawFee+";";
			});
			if(!flag){
				return;
			}
			if(str == ""){
				alert("请配置交易方式");
				return;
			}
			
			$.ajax({
				url:"${ctx }/mem/member/saveMem",
				data:{name:name,shortName:$.trim($("#shortName").val()),contact:$.trim($("#contact").val()),mobilePhone:mobilePhone,
				certNbr:$.trim($("#certNbr").val()),busLicenceNbr:$.trim($("#busLicenceNbr").val()),email:$.trim($("#email").val()),
				officeId:$.trim($("#officeId").val()),remarks:$.trim($("#remarks").val()),tradeInfo:str},
				type:'post',
				cache:false,
				async:false,
				dataType:'json',
				success:function(data) {
					if(data.returnCode=="0000"){//请求成功
						alert("商户进件成功");
						window.location.href="${ctx}/mem/member/";
					}else{
						alert(data.returnMsg);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			        alert("请求出错");
			    }
			});

		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mem/member/">商户信息列表</a></li>
		<shiro:hasPermission name="mem:member:edit"><li class="active"><a href="${ctx}/mem/member/toRegist">商户信息添加</a></li></shiro:hasPermission>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered info-table" align="center" id="mtb">
			<tr>
	        	<td colspan="4" class="info-table-group"><div class="text-center">商户基本信息</div></td>
	        </tr>
        
	        <tr>
				<td class="info-table-label"><div class="text-right">商户名称:</div></td>
				<td width="30%">
					<form:input path="name" id="name" htmlEscape="false" maxlength="50"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td class="info-table-label"><div class="text-right">商户简称:</div></td>
				<td width="30%">
					<form:input path="shortName" id="shortName" htmlEscape="false" maxlength="50"/>
			
				</td>
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">联系人:</div></td>
				<td width="30%">
					<form:input path="contact" id="contact" htmlEscape="contact" maxlength="50"/>
				</td>
				<td class="info-table-label"><div class="text-right">手机号码:</div></td>
				<td width="30%">
					<form:input path="mobilePhone" id="mobilePhone" htmlEscape="false" maxlength="11"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">身份证号码:</div></td>
				<td width="30%">
					<form:input path="certNbr" id="certNbr" htmlEscape="false" maxlength="20"/>
				</td>
				<td class="info-table-label"><div class="text-right">营业执照号码:</div></td>
				<td width="30%">
					<form:input path="busLicenceNbr" id="busLicenceNbr" htmlEscape="false" maxlength="30"/>
				</td>
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">邮箱:</div></td>
				<td width="30%">
					<form:input path="email" id="email" htmlEscape="false" maxlength="100" class="email"/>
				</td>
				<td class="info-table-label"><div class="text-right">归属机构:</div></td>
				<td width="30%">
					 <sys:treeselect id="office" name="office.id" value="${member.office.id}" labelName="office.name" labelValue="${member.office.name}"
					title="机构" url="/sys/office/treeData"  />
			
				</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">备注:</div></td>
				<td width="30%">
					<form:textarea path="remarks" id="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
				</td>
				<td class="info-table-label"><div class="text-right"></div></td>
				<td width="30%">
					
				</td>
			</tr>
			
			<tr>
				<td colspan="4" class="info-table-group"><div class="text-center">交易配置信息
					<input id="btnAdd" class="btn" type="button" value="添 加" onclick="addItem1()"/>
					<span style="color:#FF0000"><b>&nbsp&nbsp交易费率输入格式：例如0.40%请输入0.004</b> </span>
				</div>
				</td>
	        </tr>
	        <tbody class="payBak hide">
	        <tr>
				<td class="info-table-label"><div class="text-right">交易方式:</div></td>
				<td width="30%">
					<select id="txnMethod" name="txnMethod" class="input-medium">
						<c:forEach items="${fns:getDictList('txn_method')}" var="mlist">
							<option value="${mlist.value }">${mlist.label }</option>
						</c:forEach>
					</select>
				</td>
				<td class="info-table-label"><div class="text-right">交易类型:</div></td>
				<td width="30%">
					<select id="txnType" name="txnType" class="input-medium">
						<c:forEach items="${fns:getDictList('txn_type')}" var="mlist">
							<option value="${mlist.value }">${mlist.label }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
	        <tr>
				<td class="info-table-label"><div class="text-right">D0交易费率:</div></td>
				<td width="30%">
					<input id="t0TradeRate" name="t0TradeRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);">
				</td>
				<td class="info-table-label"><div class="text-right">T1交易费率:</div></td>
				<td width="30%">
					<input id="t1TradeRate" name="t1TradeRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);">
				</td>
			</tr>
			<tr>
				<td class="info-table-label"><div class="text-right">D0提现手续费:</div></td>
				<td width="30%">
					<input id="t0DrawFee" name="t0DrawFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);">
				</td>
				<td class="info-table-label"><div class="text-right">T1提现手续费:</div></td>
				<td width="30%">
					<input id="t1DrawFee" name="t1DrawFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);">
				</td>
			</tr>
			
			<!-- <tr>
				<td class="info-table-label"><div class="text-right">子商户池轮询:</div></td>
				<td width="30%">
					<select id="payDefault" name="payDefault" class="input-medium" onchange="showRoute(this.value,this)">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
				<td colspan="2">
					
				</td>
			</tr>
			<tr id="routeTr" style="display: none">
				<td class="info-table-label"><div class="text-right">通道:</div></td>
				<td width="30%">
					<select id="routeCode" name="routeCode" class="input-medium">
						<c:forEach items="${fns:getDictList('route_code')}" var="mlist">
							<option value="${mlist.value }">${mlist.label }</option>
						</c:forEach>
					</select>
				</td>
				<td class="info-table-label"><div class="text-right">通道商户编码:</div></td>
				<td width="30%">
					<input id="merchantCode" name="merchantCode"  type="text" value="" maxlength="50">
				</td>
			
			</tr>
			
			<tr>
				<td class="info-table-label"><div class="text-right">商户IP访问限制:</div></td>
				<td width="30%">
					<select id="memberIp" name="memberIp" class="input-medium" onchange="showLimit(this.value)">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
				<td colspan="2">
					
				</td>
			</tr>
			
			<tr id="limitTr" style="display: none">
				<td class="info-table-label"><div class="text-right">时长:</div></td>
				<td width="30%">
					<input id="seconds" name="seconds" class="input-medium" type="text" value="" maxlength="9">秒
				</td>
				<td class="info-table-label"><div class="text-right">访问次数:</div></td>
				<td width="30%">
					<input id="limitTimes" name="limitTimes" class="input-medium" type="text" value="" maxlength="9">
				</td>
			</tr> -->
			</tbody>
		</table>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="mem:member:edit"><input id="btnSubmit" class="btn btn-primary" type="button" onclick="saveMem()" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>