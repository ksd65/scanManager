<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现银行卡管理</title>
	<meta name="decorator" content="default"/>
	<style>
        .cert-info .block {
            width: 18%;
            margin-left: 2%;
            text-align: center;
            float: left;
        }
        div img{
            width: 40%;
        }
        .result{position: fixed;top:0;left:0;background: rgba(0,0,0,0.5);z-index:1000;width:100%;height:100%;display: none;}
        .imgresult{border:5px solid #fff;}
        .indiv{position: absolute;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
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
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/trade/payQrCode/">收款码列表</a></li>
		<li  class="active"><a href="${ctx}/trade/payQrCode/form">收款码添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="payQrCode" action="${ctx}/trade/payQrCode/save" method="post" class="form-horizontal" >
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">二维码:</label>
			<div class="controls">
				<img src="${payQrCode.filePath}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="filePath" class="required"/>
                <button type="button" id="u-filePath" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易金额:</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" maxlength="9" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付类型:</label>
			<div class="controls">
				<form:select id="payType" path="payType" class="input-large required" onchange="getPayeeList();">
					<form:options items="${fns:getDictList('qr_pay_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款人:</label>
			<div class="controls">
				<form:select path="payeeId" class="input-large required" id="payeeId" >
					<form:option value="" label="请选择"/>
					
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收款备注:</label>
			<div class="controls">
				<form:input path="qrCodeRemark" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="trade:payQrCode:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
	
<script src="${ctxStatic}/plupload/plupload.full.min.js"></script>
<script>
    $(document).ready(function () {
        for(var i = 0;i<$(".upload-btn").length;i++){
            var id = $(".upload-btn").eq(i).attr("id");
            pluploadInit(id);
        }

        $(".del").click(function(){
            $(this).parent().find("img").attr("src","/");
            $(this).parent().find("input[type=hidden]").val('');
//            $(this).parent().find(".upload-btn").show();
            $(this).hide();
        })
        getPayeeList();
    });

    function showImg(outdiv,indiv,bigimg,thiselement){
        var winW = $(window).width();
        var winH = $(window).height();
        var src = $(thiselement).attr('src');
        $(bigimg).attr("src",src);
        $("<img/>").attr("src",src).load(function(){
            var imgW = this.width;
            var imgH = this.height;
            var scale= imgW/imgH;
            if( imgW > winW ){
                $(bigimg).css("width","100%").css("height","auto");
                imgH = winW/scale;
                var h=(winH-imgH)/2;
                $(indiv).css({"left":0,"top":h});
            }else{
                $(bigimg).css("width",imgW+'px').css("height",imgH+'px');
                var w=(winW-imgW)/2;
                var h=(winH-imgH)/2;
                $(indiv).css({"left":w,"top":h});
            }

            $(outdiv).fadeIn("fast");
            $(outdiv).click(function(){
                $(this).fadeOut("fast");
            });
        });
    }
    $('table img').click(function(){
        var thiselement=$(this);
        showImg("#outdiv",".indiv","#bigimg",thiselement);
    });

    function pluploadInit(id){
        var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
            browse_button: id,
            runtimes: 'html5,html,flash,silverlight',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html
            flash_swf_url: '${ctx}/static/plupload/Moxie.swf',
            silverlight_xap_url: '${ctx}/static/plupload/Moxie.xap',
            url: '${ctx}/file/uploadFile',//上传文件路径
            max_file_size: '10m',//100b, 10kb, 10mb, 1gb
            chunk_size: '10mb',//分块大小，小于这个大小的不分块
            unique_names: true,//生成唯一文件名
            init: {
                //绑定文件添加进队列事件
                FilesAdded: function (uploader, files) {
                    uploader.start(); //开始上传
                },
                BeforeUpload: function (uploader, file) {
                    uploader.setOption("multipart_params",{"memberId":""});
                },
                UploadProgress: function (uploader, file) {
//                    file.percent
                },
                FileUploaded: function (up, file, info) {
                    var response = $.parseJSON(info.response);
                    if (response.code == 0) {
                        console.log("文件上传成功");
                        $("#"+id).parent().find("img").attr("src","${ctx}/file_upload_img"+response.path);
//                        $("#"+id).hide();
                        var _id = id.split("-")[1];
                        $("#"+_id).val(response.path);
                        $("#"+id).parent().find(".del").show();
                        <%--$("#${input}").val($("#${input}").val() + ($("#${input}").val(response.fileUrl) == "" ? response.fileUrl : "|" + response.fileUrl));--%>
                        <%--$("#progress" + file.id).html("");--%>
                        <%--$("#file-list${input}").html("");--%>
                    } else {
                        console.log("文件上传失败");
                        <%--$("#file-list${input}").html("<font color='red'>" + file.name + "上传失败，请联系系统管理员。</font>");--%>
                    }
                }
            }
        });
        uploader.init();
    }
    
    function getPayeeList(){
		var payType = $("#payType").val();
		var subHtml = "<option value=\"\">请选择</option>";
		if(payType=="" || payType==""){
			$("#payeeId").html(subHtml);
			return;
		}
		$.ajax({
			url:"${ctx }/trade/payee/getPayeeList",
			data:{payType:payType},
			type:'post',
			cache:false, 
			async:false,
			dataType:'json',
			success:function(data) {
				
				var list = data.payeeList;
				for(var i=0;i<list.length;i++){
					subHtml = subHtml+ "<option value=\""+list[i].id+"\">"+list[i].payAccount+"("+list[i].userName+")"+"</option>";	
				}
				
				$("#payeeId").html(subHtml);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {    
		        alert("请求出错");
		    }
		});

	}

</script>
	
	
	
	
</body>
</html>