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
    <div><h4>Edycja pojazdu</h4></div>
    <form class="form" action="<c:url value="/orders/editor" />" method="post">
        <div class="input-group sm-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-dark">Zatwierdź</button>
            </div>
            <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Planowy start" value="${order.plannedStart}" name="plannedStart">
            <input type="date" class="form-control form-control-sm" aria-required="true" data-placeholder="Start" value="${order.start}" name="start">
            <select class="form-control" placeholder="Pracownik" name="employeeId">
                <option value="">Wybierz pracownika</option>
                <c:forEach var="employee" items="${employees}">
                    <option ${order.employeeId==employee.id ? "selected=selected" : ""} value="${employee.id}">${employee.name} ${employee.surname}</option>
                </c:forEach>
            </select>
            <input type="text" class="form-control form-control-sm" placeholder="Problem" value="${order.problemDesc}" name="problemDesc">
            <input type="text" class="form-control form-control-sm" placeholder="Naprawa" value="${order.repairDesc}" name="repairDesc">
            <select class="form-control" placeholder="Status" name="statusId">
                <option value="">Wybierz status</option>
                <c:forEach var="status" items="${statuses}">
                    <option ${order.statusId==status.id ? "selected=selected" : ""} value="${status.id}">${status.status}</option>
                </c:forEach>
            </select>
            <select class="form-control" placeholder="Pojazd" name="vehicleId">
                <option value="">Wybierz pojazd</option>
                <c:forEach var="vehicle" items="${vehicles}">
                    <option ${order.vehicleId==vehicle.id ? "selected=selected" : ""} value="${vehicle.id}">${vehicle.model} ${vehicle.registrationNumber}</option>
                </c:forEach>
            </select>
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Koszt całkowity" name="totalCost">
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Części" name="partsCost">
            <input type="number" step="0.01" class="form-control form-control-sm" placeholder="Godziny" name="hoursSpent">
                <input type="hidden" name="id" value="${order.id}">
            </div>
        </form>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>