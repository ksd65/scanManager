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
		<li class="active"><a href="${ctx}/mem/member/detail?id=${member.id}">商户信息详情</a></li>
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

		<tr>
			<td class="info-table-label"><div class="text-right">代理商类型：</div></td>
			<td width="30%">${fns:getDictLabel(member.office.agtType,'sys_agt_type','')}</td>
			<td class="info-table-label"><div class="text-right"></div></td>
			<td width="30%"></td>
		</tr>

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
			<td class="info-table-label"><div class="text-right">所属区域：</div></td>
			<td width="30%">${member.provinceName} ${member.cityName} ${member.	countyName }</td>
			<td class="info-table-label"><div class="text-right">E码付编号：</div></td>
			<td width="30%">${member.payCode}</td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">微信商户编号：</div></td>
			<td width="30%">${member.wxMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道微信商户编号：</div></td>
			<td width="30%">${member.wxMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">支付宝商户编号：</div></td>
			<td width="30%">${member.zfbMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道支付宝商户编号：</div></td>
			<td width="30%">${member.zfbMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">QQ钱包商户编号：</div></td>
			<td width="30%">${member.qqMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道QQ钱包商户编号：</div></td>
			<td width="30%">${member.qqMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">百度钱包商户编号：</div></td>
			<td width="30%">${member.bdMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道百度钱包商户编号：</div></td>
			<td width="30%">${member.bdMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">京东钱包商户编号：</div></td>
			<td width="30%">${member.jdMemberCode}</td>
			<td class="info-table-label"><div class="text-right">通道京东钱包商户编号：</div></td>
			<td width="30%">${member.jdMerchantCode}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">D0提现费：</div></td>
			<td width="30%">${member.t0DrawFee}</td>
			<td class="info-table-label"><div class="text-right">D0交易费率：</div></td>
			<td width="30%">${member.t0TradeRate}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">T1提现费：</div></td>
			<td width="30%">${member.t1DrawFee}</td>
			<td class="info-table-label"><div class="text-right">T1交易费率：</div></td>
			<td width="30%">${member.t1TradeRate}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">快捷支付提现费 (有积分)：</div></td>
			<td width="30%">${member.mlJfFee}</td>
			<td class="info-table-label"><div class="text-right">快捷支付交易费率(有积分)：</div></td>
			<td width="30%">${member.mlJfRate}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">快捷支付提现费(无积分)：</div></td>
			<td width="30%">${member.mlWjfFee}</td>
			<td class="info-table-label"><div class="text-right">快捷支付交易费率(无积分)：</div></td>
			<td width="30%">${member.mlWjfRate}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">支付宝状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.zfbStatus,'pay_status',member.zfbStatus)}</td>
			<td class="info-table-label"><div class="text-right">微信状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.wxStatus,'pay_status',member.wxStatus)}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">QQ钱包状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.qqStatus,'pay_status',member.qqStatus)}</td>
			<td class="info-table-label"><div class="text-right">百度钱包状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.bdStatus,'pay_status',member.bdStatus)}</td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">京东钱包状态：</div></td>
			<td width="30%">${fns:getDictLabel(member.jdStatus,'pay_status',member.jdStatus)}</td>
			<td class="info-table-label"><div class="text-right"></div></td>
			<td width="30%"></td>
		</tr>
		
		<tr>
			<td class="info-table-label"><div class="text-right">快捷支付状态(有积分)：</div></td>
			<td width="30%">${fns:getDictLabel(member.mlJfStatus,'pay_status',member.mlJfStatus)}</td>
			<td class="info-table-label"><div class="text-right">快捷支付状态(无积分)：</div></td>
			<td width="30%">${fns:getDictLabel(member.mlWjfStatus,'pay_status',member.mlWjfStatus)}</td>
		</tr>
		<tr>

        <td colspan="4" class="info-table-group"><div class="text-center">银行账号信息</div></td>
        </tr>
		<tr>
			<td class="info-table-label"><div class="text-right">结算方式：</div></td>
			<td width="30%">${fns:getDictLabel(member.memberBank.settleType,'settle_type','结算方式')}</td>
			<td class="info-table-label"><div class="text-right">开户行：</div></td>
			<td width="30%">${member.memberBank.bankOpen}</td>
		</tr>
		<tr>
			<td class="info-table-label"><div class="text-right">账号名称：</div></td>
			<td width="30%">${member.memberBank.accountName}</td>
			<td class="info-table-label"><div class="text-right">银行账号：</div></td>
			<td width="30%">${member.memberBank.accountNumber}</td>
		</tr>

		<tr>
        	<td colspan="4" class="info-table-group"><div class="text-center">认证信息</div></td>
        </tr>


		<tr>
			<td colspan="4" class="cert-info">
				<div class="block">
					<div>身份证(正面)</div>
					<img src="${member.certPic1}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>身份证(反面)</div>
					<img src="${member.certPic2}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>银行卡(正面)</div>
					<img src="${member.cardPic1}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>银行卡(反面)</div>
					<img src="${member.cardPic2}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>手持身份证、银行卡</div>
					<img src="${member.memcertPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="cert-info">
				<div class="block">
					<div>营业执照</div>
					<img src="${member.busPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>门头、门店</div>
					<img src="${member.headPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>收银台</div>
					<img src="${member.deskPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>公司、商铺内部</div>
					<img src="${member.insidePic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
				<div class="block">
					<div>员工照片</div>
					<img src="${member.staffPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
				</div>
			</td>
		</tr>
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