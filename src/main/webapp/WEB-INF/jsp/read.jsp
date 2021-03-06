<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-05-11
  Time: 오후 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>read</title>
</head>
<body>
<table border="1" style="width: 600px;">
    <caption>board</caption>
    <colgroup>
        <col width="15%"/>
        <col width="*%"/>
    </colgroup>
    <tbody>
    <tr>
        <td>writer</td>
        <td><c:out value="${boardInfo.brdWriter}"/></td>
    </tr>
    <tr>
        <td>title</td>
        <td><c:out value="${boardInfo.brdTitle}"/></td>
    </tr>
    <tr>
        <td>content</td>
        <td><c:out value="${boardInfo.brdMemo}" escapeXml="false"/></td>
    </tr>
    <tr>
        <td>attach</td>
        <td>
            <c:forEach var="listView" items="${listView}" varStatus="status">
                <a href="/board/fileDownload?fileName=<c:out value="${listView.fileName}"/>&downName=<c:out value="${listView.realName}"/>"><c:out
                        value="${listView.fileName}"/></a>
                <c:out value="${listView.size2String()}"/><br/>
            </c:forEach>
        </td>
    </tr>
    </tbody>
</table>
<a href="/board/boardList.kosc">home</a>
<a href="/board/boardWriteForm.kosc?brdNo=<c:out value="${boardInfo.brdNo}"/>">mod</a>
<a href="/board/boardDelete.kosc?brdNo=<c:out value="${boardInfo.brdNo}"/>">del</a>
<div style="border: 1px solid; width: 600px; padding: 5px;">
    <form name="replyForm">
        <input type="hidden" name="brdNo" value="<c:out value="${boardInfo.brdNo}"/>"/>
        writer : <input type="text" name="reWriter" size="20" maxlength="20" required/>
        <textarea name="reMemo" rows="3" cols="60" maxlength="500" placeholder="insert reply" required></textarea>
        <input type="button" value="save" onclick="javascript:saveReply()"/>
    </form>
</div>
<!-- -->
<div id="commentList"></div>

<div id="replyDiv" style="width: 99%; display: none;">
    <form name="form2">
        <input type="hidden" name="brdNo" value="<c:out value="${boardInfo.brdNo}"/>"/>
        <input type="hidden" name="reNo"/>
        <textarea name="reMemo" rows="3" cols="60" maxlength="500" required></textarea>
        <input type="button" id="updateBtn" value="update">
        <a href="#" onclick="javascript:replyUpdateCancel()">cancel</a>
    </form>
</div>

<div id="replyDialog"></div>


<!-- JQuery CDN Version 3.2.1 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<script>
    function makeReply(data) {
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += '<div style="border: 1px solid gray; width: 600px; padding: 5px; margin-top: 5px; margin-left: ' + data[i].reDepth * 20 + 'px; display: inline-block">';
            html += data[i].reWriter + '&nbsp;' + data[i].reDate;
            html += '<a href="#" onclick="deleteReply(' + data[i].reNo + ')">del</a>&nbsp;';
            html += '<a href="#" onclick="updateReply(' + data[i].reNo + ')">mod</a>&nbsp;';
            html += '<a href="#" onclick="replyReply(' + data[i].reNo + ')">reply</a>';
            html += '<br/>';
            html += '<div id="reply' + data[i].reNo + '">' + data[i].reMemo + '</div>';
            html += '</div>';
            html += '<br/>';
        }
        $("#commentList").html(html);
    }

    function saveReply() {
        if (document.replyForm.reWriter.value.length > 10) {
            alert('이름이 넘 길다');
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/reply/replySaveAjax',
            data: {
                'brdNo': '${boardInfo.brdNo}',
                'reWriter': document.replyForm.reWriter.value,
                'reMemo': document.replyForm.reMemo.value
            },
            dataType: 'json',
            success: function (data) {
                makeReply(data);
            }
        })
    }

    function deleteReply(reNo) {
        if (!confirm('do you want to delete this reply?'))
            return;
        $.ajax({
            type: 'POST',
            url: '/reply/replyDeleteAjax',
            data: {
                'reNo': reNo,
                'brdNo': ${boardInfo.brdNo}
            },
            dataType: 'json',
            success: function (data) {
                makeReply(data);
            }
        })
        console.log(reNo);
    }

    var updateReNo = updateReMemo = null;
    function updateReply(reNo) {
        hideDiv("replyDialog");

        var form = document.form2;
        var reply = document.getElementById("reply" + reNo);
        var replyDiv = document.getElementById("replyDiv");
        replyDiv.style.display = "";

        if (updateReNo) {
            document.body.appendChild(replyDiv);
            var oldReno = document.getElementById("reply" + updateReNo);
            oldReno.innerText = updateReMemo;
        }


        form.reNo.value = reNo;
        form.reMemo.value = reply.innerText;
        reply.innerText = "";
        reply.appendChild(replyDiv);
        updateReNo = reNo;
        updateReMemo = form.reMemo.value;
        form.reMemo.focus();

        $("#updateBtn").unbind("click").bind("click", function () {
            $("#commentList").after(replyDiv);
            replyDiv.style.display = "none";
            $.ajax({
                type: 'POST',
                url: '/reply/replySaveAjax',
                data: {
                    'reNo': reNo,
                    'brdNo': '${boardInfo.brdNo}',
                    'reMemo': form.reMemo.value
                },
                dataType: 'json',
                success: function (data) {
                    makeReply(data);
                }
            })
        })
    }

    function replyUpdateCancel() {
        hideDiv("replyDiv");

        var $oldReNo = $("#reply" + updateReNo);
        $oldReNo.innerText = updateReMemo;
        updateReNo = updateReMemo = null;
    }

    function hideDiv(id) {
        var div = document.getElementById(id);
        div.style.display = "none";
        document.body.appendChild(div);
    }

    function replyReply(reNo) {
        console.log(reNo);
    }

    $(document).ready(function () {
        $.ajax({
            type: 'POST',
            url: '/reply/replyListAjax',
            data: {
                'brdNo': '${boardInfo.brdNo}'
            },
            dataType: 'json',
            success: function (data) {
                makeReply(data);
            }
        })
    });
</script>

</body>
</html>