<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户默认费率配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
		    if(obj.value.length > 3 && len4 != '0.00'){  
		    	obj.value=obj.value.replace(obj.value, '0.00');
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<!--  
		<li><a href="${ctx}/agent/agentRate/">代理商成本配置列表</a></li>
		-->
		<li class="active"><a href="${ctx}/agent/agentRate/memberRateform">商户默认费率配置</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="agentRate" action="${ctx}/agent/agentRate/saveMemberRate" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<h5 style="color:#FF0000">配置说明：</h5>
		<div>
			<ul style="color:#FF0000">
				<li>
					<span><b>通过代理商推广码注册的商户，获取此商户默认费率。</b> </span>
				</li>
				<li>
					<span><b>扫码类交易配置D0和T1相关配置，快捷类交易配置快捷有积分和无积分相关配置。</b> </span>
				</li>
				<li>
					<span><b>所有提现费设置格式为金额格式，小数点保留两位。</b> </span>
					<span><b>所有费率设置格式设置为数字格式。例如要设置0.38%请填写0.0038</b> </span>
				</li> 
			</ul>
		</div>
		<!--  		
		<div class="control-group">
			<label class="control-label">代理商：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${agentRate.office.id}" labelName="office.name" labelValue="${agentRate.office.name}"
					title="代理商" url="/agent/agentRate/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		-->
		<div class="control-group">
			<label class="control-label">D0提现费：</label>
			<div class="controls">
				<form:input path="mT0DrawFee" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith2digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">D0交易费率：</label>
			<div class="controls">
				<form:input path="mT0TradeRate" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith4digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">T1提现费：</label>
			<div class="controls">
				<form:input path="mT1DrawFee" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith2digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">T1交易费率：</label>
			<div class="controls">
				<form:input path="mT1TradeRate" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith4digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<!-- 快捷 -->
		<div class="control-group">
			<label class="control-label">快捷(有积分)提现费：</label>
			<div class="controls">
				<form:input path="mBonusQuickFee" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith2digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(有积分)费率：</label>
			<div class="controls">
				<form:input path="mBonusQuickRate" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith4digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(无积分)提现费：</label>
			<div class="controls">
				<form:input path="mQuickFee" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith2digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快捷(无积分)费率：</label>
			<div class="controls">
				<form:input path="mQuickRate" htmlEscape="false" class="input-xlarge required" onkeyup="formatMoneyWith4digts(this);"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="agent:agentRate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>