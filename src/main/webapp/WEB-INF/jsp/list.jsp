<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-05-11
  Time: 오후 5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<a href="boardWriteForm.kosc">write</a>
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
            <td><c:out value="${search.totRow-((search.page - 1)*search.displayRowCount+status.index)}"/></td>
            <td><a href="${link}"><c:out value="${listView.brdTitle}"/></a></td>
            <td><c:out value="${listView.brdWriter}"/></td>
            <td><c:out value="${listView.brdDate}"/></td>
            <td><c:out value="${listView.brdHit}"/></td>
            <td><c:out value="${listView.fileCnt}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form id="form1" name="form1" method="post">
    <c:if test="${search.totPage > 1}">
        <div class="paging">
            <c:if test="${search.page > 1}">
                <a href="javascript:fnSubmitForm(1);">[first]</a>
                <a href="javascript:fnSubmitForm(${search.page -1});">[before]</a>
            </c:if>
            <c:forEach var="i" begin="${search.pageStart}" end="${search.pageEnd}" step="1">
                <c:url var="pageLink" value="/board/boardList.kosc">
                    <c:param name="page" value="${i}"/>
                </c:url>
                <c:choose>
                    <c:when test="${i eq search.page}">
                        <c:out value="${i}"/>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:fnSubmitForm(${i})"><c:out value="${i}"/></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${search.totPage > search.page}">
                <a href="javascript:fnSubmitForm(${search.page + 1}">[next]</a>
                <a href="javascript:fnSubmitForm(${search.totPage}">[last]</a>
            </c:if>
        </div>
        <input type="hidden" name="page" id="page" value=""/>

    </c:if>
    <div>
        <input type="radio" name="searchType" value="BRDTITLE"
               <c:if test="${fn:indexOf(search.searchType, 'BRDTITLE') != -1}">checked="checked"</c:if>/>title
        <input type="radio" name="searchType" value="BRDMEMO"
               <c:if test="${fn:indexOf(search.searchType, 'BRDMEMO') != -1}">checked="checked"</c:if>/>
        <label class="chkSelect">content</label>
        <input type="text" name="searchKeyword" style="width: 150px;" maxlength="50"
               value="<c:out value="${search.searchKeyword}"/>" onkeydown="if(event.keyCode == 13){fn_formSubmit();}"/>
        <input type="button" name="btn_search" class="btn_sch" value="search" onclick="fn_formSubmit()">
    </div>
</form>
<script>
    function fnSubmitForm(page) {
        document.form1.page.value = page;
        document.form1.submit();
    }

    function fn_formSubmit() {
        document.form1.submit();
    }
</script>
</body>
</html>
