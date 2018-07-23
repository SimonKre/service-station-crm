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
    <div><h4>Klienci</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Id</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Data urodzenia</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.surname}</td>
                    <td>${customer.birthdate}</td>
                    <td><a href="<c:url value="/customer/remove?id=${customer.id}" />">Usuń </a>
                        <a href="<c:url value="/customer/editor?id=${customer.id}" />">Edytuj</a>
                        <a href="<c:url value="/vehicles/by-customer?id=${customer.id}" />">Pojazdy</a>
                        <a href="<c:url value="/orders/by-customer?id=${customer.id}" />">Zlecenia</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

        <form class="form" action="<c:url value="/customer/manager" />" method="post">
            <div class="input-group sm-3">
                <div class="input-group-prepend">
                    <button type="submit" class="btn btn-dark">Dodaj</button>
                </div>
                <input type="text" class="form-control form-control-sm" placeholder="Imię" name="name">
                <input type="text" class="form-control form-control-sm" placeholder="Nazwisko" name="surname">
                <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Data urodzenia" name="birthdate">
            </div>
        </form>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>
