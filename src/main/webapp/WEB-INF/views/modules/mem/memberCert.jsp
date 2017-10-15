<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商户认证信息</title>
    <meta name="decorator" content="default"/>
    <%--FIXME 样式地方--%>
    <style>
        .cert-info .block {
            width: 18%;
            margin-left: 2%;
            text-align: center;
            float: left;
        }
        table img{
            width: 50%;
        }
        .result{position: fixed;top:0;left:0;background: rgba(0,0,0,0.5);z-index:1000;width:100%;height:100%;display: none;}
        .imgresult{border:5px solid #fff;}
        .indiv{position: absolute;}
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/mem/member/">商户认证信息</a></li>
    <li class="active"><a href="${ctx}/mem/member/detail?id=${member.id}">商户认证信息</a></li>
</ul>
<br/>

<form:form id="form" modelAttribute="member" action="${ctx}/mem/member/doCert" method="post"
           class="breadcrumb form-search">
    <form:hidden path="id"/>
    <table class="table table-bordered info-table" align="center">
        <tbody>
        <tr>
            <td colspan="4" class="info-table-group">
                <div class="text-center">商户等级</div>
            </td>
        </tr>

        <tr>
            <td class="info-table-label">
                <div class="text-right">商户等级：</div>
            </td>
            <td width="30%">
                <form:select id="level" path="level" class="input-medium">
                    <form:options items="${fns:getDictList('member_level')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false"/>
                </form:select>
            </td>
            <td class="info-table-label"></td>
            <td width="30%"></td>
        </tr>


        <td colspan="4" class="info-table-group">
            <div class="text-center">个人信息认证</div>
        </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">身份证(正面)：</div>
            </td>
            <td width="30%">
                <img src="${member.certPic1}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="certPic1"/>
                <button type="button" id="u-certPic1" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
            <td class="info-table-label">
                <div class="text-right">身份证(反面)：</div>
            </td>
            <td width="30%">
                <img src="${member.certPic2}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="certPic2"/>
                <button type="button" id="u-certPic2" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">银行卡(正面)：</div>
            </td>
            <td width="30%">
                <img src="${member.cardPic1}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="cardPic1"/>
                <button type="button" id="u-cardPic1" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
            <td class="info-table-label">
                <div class="text-right">银行卡(反面)：</div>
            </td>
            <td width="30%">
                <img src="${member.cardPic2}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="cardPic2"/>
                <button type="button" id="u-cardPic2" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">手持身份证、银行卡：</div>
            </td>
            <td width="30%">
                <img src="${member.memcertPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="memcertPic"/>
                <button type="button" id="u-memcertPic" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
            <td class="info-table-label"></td>
            <td width="30%"></td>
        </tr>

        <td colspan="4" class="info-table-group">
            <div class="text-center">企业信息认证</div>
        </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">营业执照：</div>
            </td>
            <td width="30%">
                <img src="${member.busPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="busPic"/>
                <button type="button" class="btn del" style="display: none">删除</button>
                <button type="button" id="u-busPic" class="upload-btn btn">上传</button>
            </td>
            <td class="info-table-label">
                <div class="text-right">门头、门店：</div>
            </td>
            <td width="30%">
                <img src="${member.headPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="headPic"/>
                <button type="button" id="u-headPic" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">收银台：</div>
            </td>
            <td width="30%">
                <img src="${member.deskPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="deskPic"/>
                <button type="button" id="u-deskPic" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
            <td class="info-table-label">
                <div class="text-right">公司、商铺内部：</div>
            </td>
            <td width="30%">
                <img src="${member.insidePic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="insidePic"/>
                <button type="button" id="u-insidePic" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
        </tr>
        <tr>
            <td class="info-table-label">
                <div class="text-right">员工照片：</div>
            </td>
            <td width="30%">
                <img src="${member.staffPic}" alt="" onerror="javascript:this.src='${defaultImg}'"/>
                <form:hidden path="staffPic"/>
                <button type="button" id="u-staffPic" class="upload-btn btn">上传</button>
                <button type="button" class="btn del" style="display: none">删除</button>
            </td>
            <td class="info-table-label"></td>
            <td width="30%"></td>
        </tr>

        </tbody>
    </table>
    <div class="text-center">
        <input class="btn" type="submit" value="保 存"/>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>

<div class="result" id="outdiv">
    <div class="indiv">
        <img class="imgresult" id="bigimg" src="">
    </div>
</div>

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
                    uploader.setOption("multipart_params",{"memberId":"${member.id}"});
                },
                UploadProgress: function (uploader, file) {
//                    file.percent
                },
                FileUploaded: function (up, file, info) {
                    var response = $.parseJSON(info.response);
                    if (response.code == 0) {
                        console.log("文件上传成功");
                        $("#"+id).parent().find("img").attr("src",response.path);
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

</script>
</body>
</html>