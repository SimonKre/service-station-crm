<%--
  Created by IntelliJ IDEA.
  User: szymon
  Date: 13.07.18
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Warsztat samochodowy</title>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
</head>
<body>
<div class="container-fluid">
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
    <div><h4>Edycja klienta</h4></div>
        <form class="form" action="<c:url value="/customer/editor" />" method="post">
            <div class="input-group sm-3">
                <div class="input-group-prepend">
                    <button type="submit" class="btn btn-dark">Zatwierdź</button>
                </div>
                <input type="text" class="form-control form-control-sm" placeholder="Imię" value="${customer.name}" name="name">
                <input type="text" class="form-control form-control-sm" placeholder="Nazwisko" value="${customer.surname}" name="surname">
                <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Data urodzenia" value="${customer.birthdate}" name="birthdate">
                <input type="hidden" name="id" value="${customer.id}">
            </div>
        </form>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>