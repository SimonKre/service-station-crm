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
        <form class="form" action="<c:url value="/vehicles/editor" />" method="post">
            <div class="input-group sm-3">
                <div class="input-group-prepend">
                    <button type="submit" class="btn btn-dark">Zatwierdź</button>
                </div>
                <input type="text" class="form-control form-control-sm" placeholder="Model" value="${vehicle.model}" name="model">
                <input type="text" class="form-control form-control-sm" placeholder="Marka" value="${vehicle.maker}" name="maker">
                <input type="number" class="form-control form-control-sm" placeholder="Rocznik" value="${vehicle.productionYear}" name="productionYear">
                <input type="text" class="form-control form-control-sm" placeholder="Nr rejestracji" value="${vehicle.registrationNumber}" name="registrationNumber">
                <input type="date" class="form-control form-control-sm" required aria-required="true" data-placeholder="Następny przegląd" value="${vehicle.nextOverhaul}" name="nextOverhaul">
                <select name="customerId">
                    <option value="">Wybierz urzytkownika:</option>
                    <c:forEach var="customer" items="${customers}">
                        <option ${vehicle.customerId==customer.id ? "selected=selected" : ""} value="${vehicle.customerId}">${customer.name} ${customer.surname}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="id" value="${vehicle.id}">
            </div>
        </form>
    <%@ include file="/WEB-INF/fragments/footer.jspf" %>
</div>
</body>
</html>