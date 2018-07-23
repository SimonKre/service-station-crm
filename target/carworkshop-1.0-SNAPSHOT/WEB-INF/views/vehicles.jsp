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
    <div><h4>Pojazdy:</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Id</th>
                <th>Model</th>
                <th>Marka</th>
                <th>Rocznik</th>
                <th>Nr rejestracji</th>
                <th>Następny przegląd</th>
                <th>Właściciel</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vehicle" items="${vehicles}">
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.maker}</td>
                    <td>${vehicle.productionYear}</td>
                    <td>${vehicle.registrationNumber}</td>
                    <td>${vehicle.nextOverhaul}</td>
                    <td>${vehicle.customerId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>
