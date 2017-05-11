<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-05-11
  Time: 오후 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<table border="1" style="width: 600px">
    <caption>board</caption>
    <colgroup>
        <col width="8%"/>
        <col width="*%"/>
        <col width="15%"/>
        <col width="15%"/>
        <col width="10%"/>
        <col width="10%"/>
    </colgroup>
    <thead>
    <tr>
        <th>no</th>
        <th>title</th>
        <th>writer</th>
        <th>date</th>
        <th>hit</th>
        <th>attach</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listView" items="${listView}" varStatus="status">
        <c:url var="link" value="/board/boardRead.kosc">
            <c:param name="brdNo" value="${listView.brdNo}"/>
        </c:url>
        <tr>
            <td><c:out value="${listView.brdNo}"/></td>
            <td><a href="${link}"><c:out value="${listView.brdTitle}"/></a></td>
            <td><c:out value="${listView.brdWriter}"/></td>
            <td><c:out value="${listView.brdDate}"/></td>
            <td><c:out value="${listView.brdHit}"/></td>
            <td><c:out value="${listView.brdHit}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
