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
    <div><h4>Zlecenia:</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Id</th>
                <th>Planowy start</th>
                <th>Start</th>
                <th>Pracownik</th>
                <th>Problem</th>
                <th>Naprawa</th>
                <th>Status</th>
                <th>Pojazd</th>
                <th>Koszt całkowity</th>
                <th>Części</th>
                <th>Godziny</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.plannedStart}</td>
                    <td>${order.start}</td>
                    <td><c:forEach var="employee" items="${employees}">${order.employeeId == employee.id ? employee.name += " " += employee.surname :""}</c:forEach></td>
                    <td>${order.problemDesc}</td>
                    <td>${order.repairDesc}</td>
                    <td><c:forEach var="status" items="${statuses}">${order.statusId == status.id ? status.status :""}</c:forEach></td>
                    <td><c:forEach var="vehicle" items="${vehicles}">${order.vehicleId == vehicle.id ? vehicle.model += " " += vehicle.registrationNumber :""}</c:forEach></td>
                    <td>${order.totalCost}</td>
                    <td>${order.partsCost}</td>
                    <td>${order.hoursSpent}</td>
                    <td><a href="<c:url value="/orders/remove?id=${order.id}" />">Usuń </a>
                        <a href="<c:url value="/orders/editor?id=${order.id}" />">Edytuj</a>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <form class="form" action="<c:url value="/orders/manager" />" method="post">
        <div class="input-group sm-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-dark">Dodaj</button>
            </div>
            <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Planowy start" name="plannedStart">
            <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Start" name="start">
            <select class="form-control" placeholder="Pracownik" name="employeeId">
                <option value="">Wybierz pracownika</option>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.id}">${employee.name} ${employee.surname}</option>
                </c:forEach>
            </select>
            <input type="text" class="form-control form-control-sm" placeholder="Problem" name="problemDesc">
            <input type="text" class="form-control form-control-sm" placeholder="Naprawa" name="repairDesc">
            <select class="form-control" placeholder="Status" name="statusId">
                <option value="">Wybierz status</option>
                <c:forEach var="status" items="${statuses}">
                    <option value="${status.id}">${status.status}</option>
                </c:forEach>
            </select>
            <select class="form-control" placeholder="Pojazd" name="vehicleId">
                <option value="">Wybierz pojazd</option>
                <c:forEach var="vehicle" items="${vehicles}">
                    <option value="${vehicle.id}">${vehicle.model} ${vehicle.registrationNumber}</option>
                </c:forEach>
            </select>
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Koszt całkowity" name="totalCost">
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Części" name="partsCost">
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Godziny" name="hoursSpent">
        </div>
    </form>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>
