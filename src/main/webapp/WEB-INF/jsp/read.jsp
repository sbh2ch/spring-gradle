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
    <form name="replyForm" action="board/replySave" method="post">
        <input type="hidden" name="brdNo" value="<c:out value="${boardInfo.brdNo}"/>"/>
        writer : <input type="text" name="reWriter" size="20" maxlength="20" required/>
        <textarea name="reMemo" rows="3" cols="60" maxlength="500" placeholder="insert reply" required></textarea>
        <input type="submit" value="save"/>
    </form>
</div>

<c:forEach var="replyList" items="${replyList}" varStatus="status">
    <div style="border: 1px solid gray; width: 600px; padding: 5px; margin-top: 5px; margin-left: <c:out
            value="${20*replyList.reDepth}"/>px; display: inline-block">
        <c:out value="${replyList.reWriter}"/>&nbsp;<c:out value="${replyList.reDate}"/>
        <a href="#" onclick="fn_replyDelete('<c:out value="${replyList.reNo}"/>')">del</a>
        <a href="#" onclick="fn_replyUpdate('<c:out value="${replyList.reNo}"/>')">mod</a>
        <a href="#" onclick="fn_replyReply('<c:out value="${replyList.reNo}"/>')">reply</a>
        <br/>
        <div id="reply<c:out value="${replyList.reNo}"/>"><c:out value="${replyList.reMemo}"/></div>
    </div>
    <br/>
</c:forEach>



</body>
</html>