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
</body>
</html>