<%@ page import="util.JspUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="<%=java.time.LocalDate.now()%>" var="now"/>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<c:url value="/index" var="index"/>
<c:url var="home" value="index?action=Menu"/>
<c:url var="register" value="index?action=ReservationPage"/>
<c:url var="search" value="index?action=Search"/>
<c:url var="logout" value="index?action=Logout"/>
<c:url var="linkedin" value="https://www.linkedin.com/in/gusbuzana/"/>
<c:url var="github" value="https://github.com/GusBuz"/>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
        <link href="${path}/styles/reset.css" rel="stylesheet" type="text/css">
        <link href="${path}/styles/global.css" rel="stylesheet" type="text/css">
        <link href="${path}/styles/form.css" rel="stylesheet" type="text/css">
        <title>Atualizar reserva - Capri Hotel</title>
    </head>
    <header>
        <a href="${home}">Home</a>
        <p>&#9679;</p>
        <a href="${register}">Registro</a>
        <p>&#9679;</p>
        <a href="${search}">Buscar</a>
        <p>&#9679;</p>
        <a href="${logout}">Logout</a>
    </header>
    <body>
        <div class="pageContent">
            <div class="leftBackground">
                <img src="${path}/images/logo.svg" class="logoCapri" alt="Logotipo da Capri Hotel">
                <img src="${path}/images/update.svg" class="halfIllustration" alt="Ilustração de uma mulher mexendo num celular">
            </div>
            <div class="rightBackground">
                <form action="${index}" method="post" onsubmit="return dateValidation();">
                    <h1>Alteração de registro</h1>
                    <h2>Reserva</h2>

                    <div class="inputContainer">
                        <label for="checkinDate">Data de check in</label>
                        <input type="date" onchange="updateValue()" value="${requestScope.reservation.checkinDate}" id="checkinDate"
                               name="checkinDate" min="${now}" max="${now.plusYears(3)}" required>
                    </div>

                    <div class="inputContainer">
                        <label for="checkoutDate">Data de check out</label>
                        <input type="date" onchange="updateValue()" value="${requestScope.reservation.checkoutDate}"
                               id="checkoutDate"
                               name="checkoutDate" min="${now.plusDays(1)}" required>
                    </div>

                    <div class="inputContainer">
                        <label for="reservationCost">Valor da reserva</label>
                        <input type="text" id="reservationCost" name="reservationCost"
                               value="${JspUtils.costFormatter(requestScope.reservation.getCost().toString())}" readonly required>
                    </div>

                    <div class="inputContainer">
                        <label for="paymentMethod">Forma de pagamento</label>
                        <select id="paymentMethod" name="paymentMethod" required>
                            <option value="PIX" selected>Pix</option>
                            <option value="CARTAO_CREDITO">Cartão de crédito</option>
                            <option value="CARTAO_DEBITO">Cartão de débito</option>
                        </select>
                    </div>

                    <input type="submit" value="Entrar" class="buttons" onclick="updateValue()">
                    <input type="hidden" name="reservationId" value="${requestScope.reservation.id}">
                    <input type="hidden" name="action" value="UpdateReservation">

                </form>
            </div>
        </div>
    </body>
    <footer>
        <p>Desenvolvido por Gus Buzana 2023</p>
        <a href="${linkedin}" target="_blank"><img src="${path}/images/linkedin-icon.svg" alt="Logotipo do Linkedin" class="footerLogo"></a>
        <a href="${github}" target="_blank"><img src="${path}/images/github-icon.svg" alt="Logotipo do Github" class="footerLogo"></a>
    </footer>
    <script src="${path}/script/inputFunctions.js"></script>
</html>