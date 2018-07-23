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
    <h4>Zlecenia</h4>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Planowy start</th>
                <th>Rozpoczęcie</th>
                <th>Pracownik</th>
                <th>Usterka</th>
                <th>Zlecone</th>
                <th>Status</th>
                <th>Pojazd</th>
                <th>Koszt</th>
                <th>Części</th>
                <th>Godziny</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.plannedStart}</td>
                    <td>${order.start}</td>
                    <td>${order.employeeId}</td>
                    <td>${order.problemDesc}</td>
                    <td>${order.repairDesc}</td>
                    <td><c:forEach var="status" items="${statuses}">${order.statusId == status.id ? status.status :""}</c:forEach></td>
                    <td>${order.vehicleId}</td>
                    <td>${order.totalCost}</td>
                    <td>${order.partsCost}</td>
                    <td>${order.hoursSpent}</td>
                        <%--<td><a href="<c:url value="/solution/details?id=${solution.id}" />">Szczegóły rozwiązania</a></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>
