<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
	<meta name="decorator" content="default"/>
	<%--FIXME 样式地方--%>
	<style>
		.cert-info .block{
			width: 18%;
			margin-left: 2%;
			text-align: center;
			float: left;
		}
	</style>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mem/member/">商户信息列表</a></li>
		<li class="active"><a href="${ctx}/mem/member/toDetail?id=${member.id}">商户信息详情</a></li>
	</ul><br/>

	<table class="table table-bordered info-table" align="center">
	<tbody>
		<tr>
        <td colspan="4" class="info-table-group"><div class="text-center">商户基本信息</div></td>
        </tr>
        
        <tr>
			<td class="info-table-label"><div class="text-right">商户编号：</div></td>
			<td width="30%">${member.code}</td>
			<td class="info-table-label"><div class="text-right">商户名称：</div></td>
			<td width="30%">${member.name}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">商户简称：</div></td>
			<td width="30%">${member.shortName}</td>
			<td class="info-table-label"><div class="text-right">商户状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.status,'member_status',member.status)}</td>
		</tr>

	<!-- 	<tr>
			<td class="info-table-label"><div class="text-right">代理商类型：</div></td>
			<td width="30%">${fns:getDictLabel(member.office.agtType,'sys_agt_type','')}</td>
			<td class="info-table-label"><div class="text-right"></div></td>
			<td width="30%"></td>
		</tr> -->

		<tr>
			<td class="info-table-label"><div class="text-right">身份证号：</div></td>
			<td width="30%">${member.certNbr}</td>
			<td class="info-table-label"><div class="text-right">手机号码：</div></td>
			<td width="30%">${member.mobilePhone}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">联系人：</div></td>
			<td width="30%">${member.contact}</td>
			<td class="info-table-label"><div class="text-right">联系地址：</div></td>
			<td width="30%">${member.addr}</td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">邮箱：</div></td>
			<td width="30%">${member.email}</td>
			<td class="info-table-label"><div class="text-right">营业执照号码：</div></td>
			<td width="30%">${member.busLicenceNbr}</td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">所属区域：</div></td>
			<td width="30%">${member.provinceName} ${member.cityName} ${member.	countyName }</td>
			<td class="info-table-label"><div class="text-right">E码付编号：</div></td>
			<td width="30%">${member.payCode}</td>
		</tr>
		
		
		<tr>

        <td colspan="4" class="info-table-group"><div class="text-center">交易配置信息</div></td>
        </tr>
        <c:forEach items="${paytype }" var="plist" varStatus="status">
        <tr>
			<td class="info-table-label"><div class="text-right">交易方式：</div></td>
			<td width="30%">${fns:getDictLabel(plist.payMethod,'txn_method','')}</td>
			<td class="info-table-label"><div class="text-right">交易类型：</div></td>
			<td width="30%">${fns:getDictLabel(plist.txnType,'txn_type','')}</td>
		</tr>
        
        <tr>
			<td class="info-table-label"><div class="text-right">D0交易费率：</div></td>
			<td width="30%">${plist.t0TradeRate}</td>
			<td class="info-table-label"><div class="text-right">T1交易费率：</div></td>
			<td width="30%">${plist.t1TradeRate}</td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">D0提现手续费：</div></td>
			<td width="30%">${plist.t0DrawFee}</td>
			<td class="info-table-label"><div class="text-right">T1提现手续费：</div></td>
			<td width="30%">${plist.t1DrawFee}</td>
		</tr>
        </c:forEach>
		
		
		
	</tbody>
 	</table>
 	<!--  
 	<div>
	     <a class="example-image-link" href="/scanManager/file_servelt${member.certPic1}" data-lightbox="example-1"><img  src="/scanManager/file_servelt${member.certPic1}" style="width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;"alt="身份证照片" /></a>
	     <a class="example-image-link" href="/scanManager/file_servelt${member.cardPic1}" data-lightbox="example-2" data-title="Optional caption."><img src="/scanManager/file_servelt${member.cardPic1}" style="width:108px;height:108px;_height:108px;margin-bottom:5px;border:1px solid #EEE;padding:3px;" alt="银行卡照片"/></a>
	</div>
	--> 
	<div class="text-center">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</body>
</html>