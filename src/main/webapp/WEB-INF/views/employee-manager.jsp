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
    <div><h4>Pracownicy</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Id</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Adres</th>
                <th>Telefon</th>
                <th>Notatka</th>
                <th>PLN/h</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.surname}</td>
                    <td>${employee.adress}</td>
                    <td>${employee.phone}</td>
                    <td>${employee.note}</td>
                    <td>${employee.manHour}</td>
                    <td><a data-toggle="confirmation" data-title="Czy na pewno chcesz usunąć pracownika?" href="<c:url value="/employee/remove?id=${employee.id}" />" target="_blank">Usuń </a>
                        <a href="<c:url value="/employee/editor?id=${employee.id}" />">Edytuj</a>
                        <a href="<c:url value="/order/by-employee?id=${employee.id}" />">Zlecenia</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

        <form class="form" action="<c:url value="/employee/manager" />" method="post">
            <div class="input-group sm-3">
                <div class="input-group-prepend">
                    <button type="submit" class="btn btn-dark">Dodaj</button>
                </div>
                <input type="text" class="form-control form-control-sm" placeholder="Imię" name="name">
                <input type="text" class="form-control form-control-sm" placeholder="Nazwisko" name="surname">
                <input type="text" class="form-control form-control-sm" placeholder="Adres" name="adress">
                <input type="text" class="form-control form-control-sm" placeholder="Telefon" name="phone">
                <input type="text" class="form-control form-control-sm" placeholder="Notatka" name="note">
                <input type="number" step="0.01" class="form-control form-control-sm" placeholder="PLN/h" name="man_hour">
            </div>
        </form>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>
