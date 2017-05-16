<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-05-16
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>writeForm</title>
</head>
<body>
<form name="form1" action="/board/boardSave.kosc" method="post" enctype="multipart/form-data">
    <table border="1" style="width: 600px">
        <caption>board</caption>
        <colgroup>
            <col width="15%"/>
            <col width="*%"/>
        </colgroup>
        <tbody>
        <tr>
            <td>writer</td>
            <td><input type="text" name="brdWriter" size="20" maxlength="20"
                       value="<c:out value="${boardInfo.brdWriter}"/>" required/></td>
        </tr>
        <tr>
            <td>title</td>
            <td><input type="text" name="brdTitle" size="20" maxlength="20"
                       value="<c:out value="${boardInfo.brdTitle}"/>" required/></td>
        </tr>
        <tr>
            <td>content</td>
            <td><input type="text" name="brdMemo" size="20" maxlength="20"
                       value="<c:out value="${boardInfo.brdMemo}"/>" required/></td>
        </tr>
        <tr>
            <td>attach</td>
            <td>
                <c:forEach var="listView" items="${listView}" varStatus="status">
                    <input type="checkbox" name="fileNo" value="<c:out value="${listView.fileNo}"/>">
                    <a href="fileDownload?fileName=<c:out value="${listView.fileName}"/>&downName=<c:out value="${listView.realName}"/>"><c:out value="${listView.fileName}"/></a>
                    <c:out value="${listView.size2String()}"/>
                </c:forEach>
                <input type="file" name="uploadFiles" multiple>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="brdNo" value="<c:out value="${boardInfo.brdNo}"/>">
    <a href="#" onclick="form1.submit()">save</a>
</form>
</body>
</html>
