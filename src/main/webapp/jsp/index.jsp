<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>Index</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/admin/style.css"></link>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/hudong.css"></link>
    <link href="<%=basePath%>js/umeditor/themes/default/css/umeditor.min.css" rel="stylesheet"></link>
</head>
<body onload="overtrigger()">

    <div class="white-module">

        <div class="hudong_container">
            <div class="keshi_list" currentKeshiid="">
                <ul>
                    <c:forEach var="room" items="${rooms}">
                        <li><a href="javascript:void(0);" keshiid="${room.bianma}" class="">${room.mingcheng}&nbsp;<span class="noread"></span></a></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="chat-left">
                <div class="chat-content">
                    <div class="am-g am-g-fixed chat-content-container">
                        <div class="am-u-sm-12">
                            <ul id="message-list" class="am-comments-list am-comments-list-flip"></ul>
                        </div>
                    </div>
                </div>

                <div class="message-input">
                    <div class="am-form-group">
                        <script type="text/plain" id="myEditor" style="width: 100%;height:100px;"></script>
                    </div>
                </div>
                <div class="send-div">
                    <span class="shotcuttext">(快捷键：Ctrl+Enter)</span>
                    <button id="send" type="button">发 送</button>
                    <input id="nickname" style="display: none;" value="${user.userId}" yishengxm="${user.userName}" type="text" />
                </div>
            </div>
        </div>
    </div>

<script src="<%=basePath%>js/jquery/jquery.min.js"></script>
<script charset="utf-8" src="<%=basePath%>js/umeditor/umeditor.config.js"></script>
<script charset="utf-8" src="<%=basePath%>js/umeditor/umeditor.min.js"></script>
<script src="<%=basePath%>js/umeditor/lang/zh-cn/zh-cn.js"></script>



<script type="text/javascript">
    var um = UM.getEditor('myEditor', {
        toolbar: [
            'emotion | undo redo | bold italic underline strikethrough |  forecolor backcolor | removeformat |', 'paragraph | fontfamily fontsize',
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | image video |  horizontal fullscreen'
        ]
    });

    //socket协议
    var socket = null;
    if ('WebSocket' in window) {
        socket = new WebSocket('ws://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/hudongsocket');
    } else {
        alert("对不起！你的浏览器不支持webSocket,请下载chrome或firefox或ie10+等浏览器 ")
    }


    //连接成功
    socket.onopen = function (event) {
        console.log("连接服务器成功！");
    };

    //发生错误
    socket.onerror = function () {
        alert("连接聊天服务器发生错误,请稍后重试！");
    };

    //接收到消息
    socket.onmessage = function (event) {
        addMessage(event.data);
    };

    //断开连接
    socket.onclose = function () {
        console.log("断开连接");
    };

    //点击发送消息
    $('#send').on('click', function () {
        sendMessage();
    });

    //快捷键发生消息
    document.onkeydown = function (e) {
        if (e.ctrlKey && e.keyCode == 13) {
            sendMessage();
        }
    };

    //发生消息方法
    function sendMessage() {
        if (!um.hasContents()) {
            um.focus();
        } else {
            socket.send(JSON.stringify({
                content: um.getContent(),
                yishengid: $('#nickname').val(),
                yishengxm: $('#nickname').attr("yishengxm"),
                keshiid: $(".keshi_list").attr("currentKeshiid")
            }));
            um.setContent('');
            um.focus();
        }
    }

    //添加socket返回的数据加入到对应的科室里
    function addMessage(message) {
        message = JSON.parse(message);
        if ($(".keshi_list").attr("currentKeshiid") == message.keshiid) {
            var messageItem = '<li class="am-comment ' + (message.isSelf ? 'mes-self' : 'mes-other') + '">' +
                    '<div class="am-comment-main">' +
                    '<div class="am-comment-meta">' +
                    '<span  class="comment-author">' + message.yishengxm + '</span><time class="comment-date">' + message.date + '</time>' +
                    '</div>' +
                    '<div onclick="largerImg(this)" class="am-comment-bd">' + message.content + '</div>' +
                    '</div></li>';
            $(messageItem).appendTo('#message-list');
        } else {
            $(".keshi_list ul li a[keshiid=" + message.keshiid + "]").children("span").html("【有新消息】");
        }
        // 把滚动条滚动到底部
        $(".chat-content-container").scrollTop($(".chat-content-container")[0].scrollHeight);
    }


    function overtrigger() {
        $(".keshi_list ul li a:first").trigger("click");
    }


    var currentYishengid = "${user.userId}";
    //点击科室加载聊天信息，并将当前科室未读数初始化
    $(".keshi_list ul li a").click(function () {
        $(this).parents("ul").find("a").attr("class", "");
        $(this).attr("class", "active");
        $(this).children("span").html("");

        var keshiid = $(this).attr("keshiid");
        $(".keshi_list").attr("currentKeshiid", keshiid);
        $('#message-list').html("");
        $.get("history?roomId=" + keshiid, function (data) {
            if (!$.isEmptyObject(data)) {
                $.each(data, function (i, message) {
                    var isself = false;
                    if (currentYishengid == message.userId) {
                        isself = true;
                    }
                    var messageItem = '<li class="am-comment ' + (isself ? 'mes-self' : 'mes-other') + '">' +
                            '<div class="am-comment-main">' +
                            '<div class="am-comment-hd">' +
                            '<span  class="comment-author">' + message.userName  + '</span><time class="comment-date">' + formateDate(message.sendDate) + '</time>' +
                            '</div>' +
                            '<div onclick="largerImg(this)" class="am-comment-bd">' + message.content + '</div>' +
                            '</div></li>';
                    $(messageItem).appendTo('#message-list');
                    $(".chat-content-container").scrollTop($(".chat-content-container")[0].scrollHeight);
                });
            }
        });
    });

    function largerImg(thiz) {
        $(thiz).find("img").each(function (e) {
            var src = $(this).attr("src");
            var html = '<div class="round_shade_box" id="zoom">' +
                    '<div class="round_shade_center">' +
                    '<img onclick="closeImg()" src="' + src + '"/>' +
                    '</div>' +
                    '<a href="javascript:closeImg();" class="round_box_close" id="zoom_close">关闭</a>' +
                    '</div>';

            $("body").append(html);
            $("#zoom").show();

            $("#mask").css("height", $(document).height());
            $("#mask").css("width", $(document).width());
            $("#mask").show();

            var top = ($(window).height() - $("#zoom").height()) / 2;
            var left = ($(window).width() - $("#zoom").width()) / 2;
            var scrollTop = $(document).scrollTop();
            var scrollLeft = $(document).scrollLeft();
            $("#zoom").css({position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft}).show();

        })
    }

    function closeImg() {
        $("#zoom").remove();
        $("#mask").hide();
    }


    //格式化时间
    function formateDate(xiaoxisj) {
        var date = new Date(xiaoxisj);
        var year = date.getFullYear();
        var month = (date.getMonth() + 1) <= 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
        var day = date.getDate() <= 9 ? "0" + date.getDate() : date.getDate();
        var sec = date.getSeconds() <= 9 ? "0" + date.getSeconds() : date.getSeconds();
        var time = year + "-" + month + "-" + day + " " + date.getHours() + ":" + date.getMinutes() + ":" + sec;
        return time;
    }

    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: true,
        autoplay:2500
    });
</script>
<div class="mask" id="mask">

</div>
</body>
</html>
