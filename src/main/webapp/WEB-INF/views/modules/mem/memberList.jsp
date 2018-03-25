<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mem/member/">商户信息列表</a></li>
		<shiro:hasPermission name="mem:member:edit"><li><a href="${ctx}/mem/member/toRegist">商户信息添加</a></li></shiro:hasPermission>
		
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/mem/member/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="mobilePhone" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>联系人：</label>
				<form:input path="contact" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>E码付编号：</label>
				<form:input path="payCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
		<!--  	<li><label>微信状态：</label>
				<form:select id="wxStatus" path="wxStatus" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>支付宝状态：</label>
				<form:select id="zfbStatus" path="zfbStatus" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('pay_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>-->
			<li><label>所属机构：</label><sys:treeselect id="officeId" name="officeId" value="${member.office.id}" labelName="office.name" labelValue="${member.office.name}" 
				title="机构" url="/sys/office/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>注册时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			
			<li><label>商户状态：</label>
				<form:select id="status" path="status" class="input-medium">
					<form:option value="" label="所有"/>
					<form:options items="${fns:getDictList('member_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>所属机构</th>
				<%--<th>代理商类型</th>--%>
				<th>手机号码</th>
				<!--<th>商户等级</th>
				<th>提现状态</th>
				
				<th>所属一级机构</th>
				-->
				<th>商户状态</th>
			<!-- 	<th>微信状态</th>
				<th>支付宝状态</th>-->
				<th>联系人</th> 
				<!--  
				<th>结算方式</th>
				-->
				<th>E码付编号</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
				<td>
					${member.code}
				</td>
				<td>
					${member.name}
				</td>
				<td>
					${member.office.name}
				</td>
				<%--<td>--%>
					<%--${fns:getDictLabel(member.office.agtType,'sys_agt_type','')}--%>
				<%--</td>--%>
				<td>
					${member.mobilePhone}
				</td>
				<!--<td>
						${fns:getDictLabel(member.level,'member_level',member.level)}
				</td>
				<td>
						${fns:getDictLabel(member.drawStatus,'draw_status',member.drawStatus)}
				</td>
				  
				<td>
					${member.agentNameLevel1 }
				</td>
				-->
				<td>
					${fns:getDictLabel(member.status,'member_status',member.status)}
				</td>
			<!-- 	<td>				
					${fns:getDictLabel(member.wxStatus,'pay_status',member.wxStatus)}
				</td>
				<td>
					${fns:getDictLabel(member.zfbStatus,'pay_status',member.zfbStatus)}
				</td> -->
				<td>
					${member.contact}
				</td>
				<!--  
				<td>
					${fns:getDictLabel(member.memberBank.settleType,'settle_type',member.memberBank.settleType)}
				</td>
				-->
				<td>
					${member.payCode}
				</td>
				<td>
					<fmt:formatDate value="${member.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<i class="icon-edit"></i></i><a href="${ctx}/mem/member/toDetail?id=${member.id}">详情</a>
				<shiro:hasPermission name="mem:member:edit">
					<i class="icon-edit"></i><a href="${ctx}/mem/member/toEdit?id=${member.id}">修改</a>
					
				<!-- 	<i class="icon-edit"></i><a class="permission" href="javascript:void(0);"
												data-id="${member.id}"
												data-status="${member.status}"
												data-draw-status="${member.drawStatus}"
												data-sing-limit="${member.singleLimit}"
												data-day-limit="${member.dayLimit}">权限</a>
					<i class="icon-edit"></i><a href="${ctx}/mem/member/cert?id=${member.id}">认证</a> -->
				</shiro:hasPermission>
				<shiro:hasPermission name="mem:member:audit">
					<c:if test="${member.status == 0}">
						<i class="icon-edit"></i><a href="${ctx}/mem/member/disable?id=${member.id}" onclick="return confirmx('确认要禁用该商户吗？', this.href)">禁用</a>
					</c:if>
					<c:if test="${member.status == 1}">
						<i class="icon-edit"></i><a href="${ctx}/mem/member/enable?id=${member.id}" onclick="return confirmx('确认要启用该商户吗？', this.href)">启用</a>
					</c:if>
					<c:if test="${member.status == 3}">
						<i class="icon-edit"></i><a href="${ctx}/mem/member/audit?id=${member.id}" onclick="return confirmx('确认要审核通过该商户吗？', this.href)">审核通过</a>
					</c:if>
				</shiro:hasPermission>
				<i class="icon-edit"></i><a href="javascript:keyinfo('${member.id}')">秘钥</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="dataModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
					</button>
					<h4 class="modal-title" id="myModalLabel">权限管理</h4>
				</div>
				<div class="modal-body">
					<div style='text-align: center;'>
						<form action="${ctx}/mem/member/update" id="m-form" method="post">
							<input type="hidden" id="m-id" name="id" value="">
							<table class="table table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th style="text-align: center;">商户状态</th>
									<th style="text-align: center;">提现状态</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td style="text-align: center;">
										<select id="m-status" name="status" style="width: 200px;">
											<c:forEach items="${fns:getDictList('member_status')}" var="status">
												<option value="${status.value}">${status.label}</option>
											</c:forEach>
										</select>
									</td>
									<td style="text-align: center;">
										<select id="m-draw-status" name="drawStatus" style="width: 200px;">
											<c:forEach items="${fns:getDictList('draw_status')}" var="status">
												<option value="${status.value}">${status.label}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								</tbody>
							</table>

							<table class="table table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th style="text-align: center;">单笔限额</th>
									<th style="text-align: center;">单日限额</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td style="text-align: center;">
										<input id="m-sing-limit" type="number" name="singleLimit" style="text-align: center;width: 180px;">元
									</td>
									<td style="text-align: center;">
										<input id="m-day-limit" type="number" name="dayLimit"style="text-align: center;width: 180px;">元
									</td>
								</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="save btn btn-primary">保存</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
	<div class="modal fade" id="dataModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
					</button>
					<h4 class="modal-title" id="myModalLabel">查看秘钥</h4>
				</div>
				<div class="modal-body">
					<div style='text-align: center;'>
						<form action="${ctx}/mem/member/update" id="m-form" method="post">
							<input type="hidden" id="m-id" name="id" value="">
							<table class="table table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th style="text-align: center;">公钥【<a href="javascript:copyStr('publicStr')">复制</a>】</th>
									<th style="text-align: center;">私钥【<a href="javascript:copyStr('privateStr')">复制</a>】</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td style="text-align: center;">
										<textarea rows="10" cols="30" id="publicStr"></textarea>
									</td>
									<td style="text-align: center;">
										<textarea rows="10" cols="30" id="privateStr"></textarea>
									</td>
								</tr>
								</tbody>
							</table>

							
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" >关闭</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	
	<script type="text/javascript">
		$(function(){
		    $(".permission").click(function(){
		        var status = $(this).data("status");
		        var drawStatus = $(this).data("draw-status");
		        var singLimit = $(this).data("sing-limit");
		        var dayLimit = $(this).data("day-limit");
		        $("#m-id").val($(this).data("id"));
		        $("#m-status").val(status);
		        $("#m-draw-status").val(drawStatus);
		        $("#m-sing-limit").val(singLimit);
		        $("#m-day-limit").val(dayLimit);
				$("#m-status").parent().find(".select2-chosen").html($("#m-status").find("option:selected").text());
				$("#m-draw-status").parent().find(".select2-chosen").html($("#m-draw-status").find("option:selected").text());


                $('#dataModal').modal('toggle').modal('show');
			})

			$(".save").click(function(){
			    var id = $("#m-id").val();
			    var status = $("#m-status").val();
			    var drawStatus = $("#m-draw-status").val();
			    var singLimit = $("#m-sing-limit").val();
			    var dayLimit = $("#m-day-limit").val();

			    $("#m-form").submit();
			})
		})
		
		function keyinfo(id){
			$("#privateStr").html("");
			$("#publicStr").html("");	
			$.ajax({
				url:"${ctx }/mem/member/keyinfo",
				data:{id:id},
				type:'post',
				cache:false,
				async:false,
				dataType:'json',
				success:function(data) {
					if(data.returnCode=="0000"){//请求成功
						$("#privateStr").html(data.privateKey);
						$("#publicStr").html(data.publicKey);	
						$('#dataModal1').modal('toggle').modal('show');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {    
			        alert("请求出错");
			    }
			});

		}
		
		function copyStr(id){
			var url=document.getElementById(""+id);
            url.select();
            document.execCommand("Copy");
            alert("复制成功");
		}
		
	</script>
</body>
</html>