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
                    <td><c:forEach var="customer" items="${customers}">${vehicle.customerId==customer.id ? customer.name += " " += customer.surname : ""}</c:forEach></td>
                    <td><a href="<c:url value="/vehicles/remove?id=${vehicle.id}" />">Usuń </a>
                        <a href="<c:url value="/vehicles/editor?id=${vehicle.id}" />">Edytuj</a>
                        <a href="<c:url value="/vehicles/details?id=${vehicle.id}" />">Szczegóły</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form class="form" action="<c:url value="/vehicles/manager" />" method="post">
        <div class="input-group sm-3">
            <div class="input-group-prepend">
                <button type="submit" class="btn btn-dark">Dodaj</button>
            </div>
            <input type="text" class="form-control form-control-sm" placeholder="Model" name="model">
            <input type="text" class="form-control form-control-sm" placeholder="Marka" name="maker">
            <input type="number" class="form-control form-control-sm" placeholder="Rocznik" name="productionYear">
            <input type="text" class="form-control form-control-sm" placeholder="Nr rejestracji"
                   name="registrationNumber">
            <input type="date" class="form-control form-control-sm" required aria-required="true"
                   data-placeholder="Następny przegląd" name="nextOverhaul">
            <select class="form-control" placeholder="Klient" name="customerId">
                <option value="">Wybierz klienta</option>
                <c:forEach var="customer" items="${customers}">
                    <option value="${customer.id}">${customer.name} ${customer.surname}</option>
                </c:forEach>
            </select>
        </div>
    </form>

    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>