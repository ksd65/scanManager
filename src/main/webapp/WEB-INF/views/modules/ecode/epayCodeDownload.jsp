<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>E码付管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#searchForm").validate({
				submitHandler: function(form){
                   	if ($("#amount").val()=="" || $("#amount").val()==0){
                        $("#amount").focus();
                        top.$.jBox.tip('请输入生成数量!','warning');
                    }else if ($("#amount").val() > ${amount}){
                        $("#amount").focus();
                        top.$.jBox.tip('输入的数量超过了可生成数量!','warning');
                    }else if ($("#amount").val() > 100){
                        $("#amount").focus();
                        top.$.jBox.tip('输入的数量超过了最大限制!','warning');
                    }
                    else if ($("#t0TradeRate").val()=="" || $("#t0TradeRate").val()==0){
                        $("#t0TradeRate").focus();
                        top.$.jBox.tip('请输入D0交易费率!','warning');
                    }
                    else if ($("#t0TradeRate").val() < 0.0037){
                        $("#t0TradeRate").focus();
                        top.$.jBox.tip('D0交易费率不能低于0.37%!','warning');
                    }
                    else if ($("#t0DrawFee").val()=="" || $("#t0DrawFee").val()==0){
                        $("#t0DrawFee").focus();
                        top.$.jBox.tip('请输入D0提现手续费!','warning');
                    }
                    else if ($("#t0DrawFee").val() < 1){
                        $("#t0DrawFee").focus();
                        top.$.jBox.tip('D0提现手续费不能低于1元!','warning');
                    }
                    else if ($("#t1TradeRate").val()=="" || $("#t1TradeRate").val()==0){
                        $("#t1TradeRate").focus();
                        top.$.jBox.tip('请输入T1交易费率!','warning');
                    }
                    else if ($("#t1TradeRate").val() < 0.0035){
                        $("#t1TradeRate").focus();
                        top.$.jBox.tip('T1交易费率不能低于0.35%!','warning');
                    }
                    else if ($("#t1DrawFee").val()=="" || $("#t1DrawFee").val()==0){
                        $("#t1DrawFee").focus();
                        top.$.jBox.tip('请输入T1提现手续费!','warning');
                    }
                    else if ($("#t1DrawFee").val() < 1){
                        $("#t1DrawFee").focus();
                        top.$.jBox.tip('T1提现手续费不能低于1元!','warning');
                    }          
                    else if ($("#mlJfRate").val()=="" || $("#mlJfRate").val()==0){
                        $("#mlJfRate").focus();
                        top.$.jBox.tip('请输入快捷支付(有积分)交易费率!','warning');
                    }
                    else if ($("#mlJfRate").val() < 0.0049){
                        $("#mlJfRate").focus();
                        top.$.jBox.tip('快捷支付(有积分)交易费率不能低于0.49%!','warning');
                    }
                    else if ($("#mlJfFee").val()=="" || $("#mlJfFee").val()==0){
                        $("#mlJfFee").focus();
                        top.$.jBox.tip('请输入快捷支付(有积分)提现手续费!','warning');
                    }
                    else if ($("#mlJfFee").val() < 2){
                        $("#mlJfFee").focus();
                        top.$.jBox.tip('快捷支付(有积分)提现手续费不能低于2元!','warning');
                    }
                   	
                    else if ($("#mlWjfRate").val()=="" || $("#mlWjfRate").val()==0){
                        $("#mlWjfRate").focus();
                        top.$.jBox.tip('请输入快捷支付(无积分)交易费率!','warning');
                    }
                    else if ($("#mlWjfRate").val() < 0.0041){
                        $("#mlWjfRate").focus();
                        top.$.jBox.tip('快捷支付(有积分)交易费率不能低于0.41%!','warning');
                    }
                    else if ($("#mlWjfFee").val()=="" || $("#mlWjfFee").val()==0){
                        $("#mlWjfFee").focus();
                        top.$.jBox.tip('请输入快捷支付(无积分)提现手续费!','warning');
                    }
                    else if ($("#mlWjfFee").val() < 2){
                        $("#mlJfFee").focus();
                        top.$.jBox.tip('快捷支付(有积分)提现手续费不能低于2元!','warning');
                    }
                   	
                    else{
                        //loading('正在提交，请稍等...');
                        form.submit();
                    }
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
	<style type="text/css">
		tr{
			height: 40px;
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<form id="searchForm" class="breadcrumb form-search" action="${ctx}/ecode/epayCode/download" method="post">		
			<h3 style="width:100%;text-align:center">生成并下载二维码图片</h3>
			<h5 style="color:#FF0000">功能说明：</h5>
			<div>
			<ul style="color:#FF0000">
				<li>
					<span><b>可生成1~100数量的二维码，提供打包下载。</b> </span>
				</li> 
				<li>
					<span><b>下载完成后，请刷新本页面，以重新计算可生成二维码数量。</b> </span>
				</li>
				<li>
					<span><b>扫码类交易费率和提现手续费设置不能低于(D0:0.37%+1,T1:0.35%+1)</b> </span>
					<span><b>快捷支付类交易费率和提现手续费设置不能低于(有积分:0.49%+2,无积分:0.41%+2)</b> </span>
				</li> 
			</ul>
			</div>
			
			<table>
				<tr>
				<td>可生成数量:</td>
				<td>${amount}</td>
				</tr>
				
				<tr>
				<td style="text-align: right;">生成数量:</td>
				<td><input id="amount" name="amount" class="input-medium" type="number" value="" maxlength="9"/></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">D0交易费率:</td>
				<td><input id="t0TradeRate" name="t0TradeRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);"/></td>
				<td><span style="color:#FF0000"><b>&nbsp&nbsp输入格式：例如0.40%请输入0.004</b> </span></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">D0提现费:</td>
				<td><input id="t0DrawFee" name="t0DrawFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);" /></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">T1交易费率:</td>
				<td><input id="t1TradeRate" name="t1TradeRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);"/></td>
				<td><span style="color:#FF0000"><b>&nbsp&nbsp输入格式：例如0.38%请输入0.0038</b> </span></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">T1提现费:</td>
				<td><input id="t1DrawFee" name="t1DrawFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);"/></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">快捷(有积分)交易费率:</td>
				<td><input id="mlJfRate" name="mlJfRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);"/></td>
				<td><span style="color:#FF0000"><b>&nbsp&nbsp输入格式：例如0.49%请输入0.0049</b> </span></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">快捷(有积分)提现费:</td>
				<td><input id="mlJfFee" name="mlJfFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);"/></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">快捷(无积分)交易费率:</td>
				<td><input id="mlWjfRate" name="mlWjfRate" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith4digts(this);"/></td>
				<td><span style="color:#FF0000"><b>&nbsp&nbsp输入格式：例如0.41%请输入0.0041</b> </span></td>
				</tr>
				
				<tr>
				<td style="text-align: right;">快捷(无积分)提现费:</td>
				<td><input id="mlWjfFee" name="mlWjfFee" class="input-medium" type="text" value="" maxlength="9" onkeyup="formatMoneyWith2digts(this);"/></td>
				</tr>
				
				<tr>
				<td colspan="2" style="text-align: center;"><input id="btnSubmit" class="btn btn-primary" style="width: 80px;" type="submit" value="生成"/></td>
				</tr>
			</table>
	</form>
</body>
</html>