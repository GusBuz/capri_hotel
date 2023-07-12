<%@ page import="util.JspUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="${path}/styles/success.css" rel="stylesheet" type="text/css">
        <title>Sucesso - Capri Hotel</title>
    </head>
    <header>
        <a href="${home}">Home</a><p>&#9679;</p>
        <a href="${register}">Registro</a><p>&#9679;</p>
        <a href="${search}">Buscar</a><p>&#9679;</p>
        <a href="${logout}">Logout</a>
    </header>
    <body>
        <div class="pageContent">
            <div class="leftBackground">
                <form action="${index}" method="post">
                    <h2>Registro ${requestScope.title} com sucesso!</h2>

                    <div class="inputContainer">
                        <label for="fullName">Nome completo</label>
                        <input type="text" id="fullName" class="fullInput" readonly value="${requestScope.guest.name}">
                    </div>

                    <div class="doubleColumnContainer">
                        <div class="doubleColumnInput">
                            <label for="birthDate">Data de nascimento</label>
                            <input type="date" id="birthDate" class="halfInput" readonly
                                   value="${requestScope.guest.birthDate}">
                        </div>
                        <div class="doubleColumnInput">
                            <label for="cpf">CPF</label>
                            <input type="text" id="cpf" class="halfInput" readonly value="${JspUtils.cpfFormatter(requestScope.guest.cpf)}">
                        </div>
                    </div>

                    <div class="doubleColumnContainer">
                        <div class="doubleColumnInput">
                            <label for="phone">Telefone</label>
                            <input type="text" id="phone" class="halfInput" readonly value="${JspUtils.phoneFormatter(requestScope.guest.phone)}">
                        </div>
                        <div class="doubleColumnInput">
                            <label for="checkinDate">Data de check in</label>
                            <input type="date" id="checkinDate" class="halfInput" readonly
                                   value="${requestScope.reservation.checkinDate}">
                        </div>
                    </div>

                    <div class="doubleColumnContainer">
                        <div class="doubleColumnInput">
                            <label for="reservationCost">Valor da reserva</label>
                            <input type="text" id="reservationCost" class="halfInput" readonly value="${JspUtils.costFormatter(requestScope.reservation.cost)}">
                        </div>
                        <div class="doubleColumnInput">
                            <label for="checkoutDate">Data de check out</label>
                            <input type="date" id="checkoutDate" class="halfInput" readonly
                                   value="${requestScope.reservation.checkoutDate}">
                        </div>
                    </div>

                    <div class="doubleColumnContainer">
                        <div class="doubleColumnInput">
                            <label for="reservationNumber">Número da reserva</label>
                            <input type="text" id="reservationNumber" class="halfInput" readonly
                                   value="${requestScope.reservation.id}">
                        </div>
                        <div class="doubleColumnInput">
                            <label for="paymentMethod">Forma de pagamento</label>
                            <input type="text" id="paymentMethod" class="halfInput" readonly value="${JspUtils.paymentFormatter(requestScope.reservation.paymentMethod)}">
                        </div>
                    </div>

                    <input type="submit" value="Voltar" class="buttons">
                    <input type="hidden" name="action" value="Menu">
                </form>
            </div>
            <div class="rightBackground">
                <img src="${path}/images/logo.svg" class="logoCapri" alt="Logotipo da Capri Hotel">
                <img src="${path}/images/success.svg" class="halfIllustration" alt="Ilustração de um foguete">
            </div>
        </div>
    </body>
    <footer>
        <p>Desenvolvido por Gus Buzana 2023</p>
        <a href="${linkedin}" target="_blank"><img src="${path}/images/linkedin-icon.svg" alt="Logotipo do Linkedin" class="footerLogo"></a>
        <a href="${github}" target="_blank"><img src="${path}/images/github-icon.svg" alt="Logotipo do Github" class="footerLogo"></a>
    </footer>
</html>