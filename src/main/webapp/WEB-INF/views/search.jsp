<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="util.JspUtils" %>
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
        <link href="${path}/styles/search.css" rel="stylesheet" type="text/css">
        <title>Buscar - Capri Hotel</title>
    </head>
    <header>
        <a href="${home}">Home</a><p>&#9679;</p>
        <a href="${register}">Registro</a><p>&#9679;</p>
        <a href="${search}">Buscar</a><p>&#9679;</p>
        <a href="${logout}">Logout</a>
    </header>
    <body>
        <div class="pageContent">
            <img src="${path}/images/logo.svg" class="logoCapri" alt="Logotipo da Capri Hotel">
            <h1>Sistema de busca</h1>
            <div class="fullContent">
                <div class="buttonsAboveTable">
                    <div class="tabsContainer">
                        <input type="button" id="reservationTab" onclick="changeTab(1)" value="Reservas" class="tableTabs">
                        <input type="button" id="guestTab" onclick="changeTab(2)" value="Hóspedes" class="tableTabs">
                    </div>
                    <div class="searchContainer">
                        <form action="${index}" method="post" onsubmit="return numberValidation()">
                            <label for="filterType">Pesquisar por:</label>
                            <select name="filterType" id="filterType" onchange="updateSearchInput()">
                                <option value="name" selected>Nome</option>
                                <option value="cpf">CPF</option>
                                <option value="checkinDate">Data de check in</option>
                            </select>
                            <input type="text" name="searchKey" id="searchInput">
                            <input type="date" name="dateSearchKey" id="dateSearchInput">
                            <input type="submit" name="action" value="Search" class="buttons">
                            <input type="hidden" name="filter" value="doFilter">
                        </form>
                    </div>
                </div>

                <form action="${index}" method="post" onsubmit="return validateRadio(this.id)" id="reservationForm">
                    <div id="content1" class="tableContent">
                        <div class="selectedTable">
                            <table>
                                <thead class="reservationHeader">
                                <tr>
                                    <th>#</th>
                                    <th>Id</th>
                                    <th>Data de entrada</th>
                                    <th>Data de saída</th>
                                    <th>Valor da reserva</th>
                                    <th>Forma de pagamento</th>
                                    <th>Id do Hóspede</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.reservationList}" var="reservation">

                                    <fmt:parseDate value="${reservation.checkinDate}" pattern="yyyy-MM-dd" var="checkinDate"
                                                   type="date"/>
                                    <fmt:formatDate value="${checkinDate}" var="goodCheckinDate"/>
                                    <fmt:parseDate value="${reservation.checkoutDate}" pattern="yyyy-MM-dd" var="checkoutDate"
                                                   type="date"/>
                                    <fmt:formatDate value="${checkoutDate}" var="goodCheckoutDate"/>

                                    <tr>
                                        <th><input type="radio" name="registry" value="reservation:${reservation.id}"></th>
                                        <th>${reservation.id}</th>
                                        <th>${goodCheckinDate}</th>
                                        <th>${goodCheckoutDate}</th>
                                        <th><fmt:formatNumber value="${reservation.cost}" type="currency"
                                                              currencySymbol="R$ "/></th>
                                        <th>${JspUtils.paymentFormatter(reservation.getPaymentMethod().toString())}</th>
                                        <th>${reservation.guest.id}</th>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="buttonsBelowTable">
                            <button type="submit" name="action" value="Update" class="buttons">Editar</button>
                            <button type="submit" name="action" value="Delete" class="buttons">Deletar</button>
                        </div>
                    </div>
                </form>

                <form action="${index}" method="post" onsubmit="return validateRadio(this.id)" id="guestForm">
                    <div id="content2" class="tableContent">
                        <div class="selectedTable">
                            <table>
                                <thead class="guestHeader">
                                <tr>
                                    <th>#</th>
                                    <th>Id</th>
                                    <th>Nome</th>
                                    <th>CPF</th>
                                    <th>Data de nascimento</th>
                                    <th>Telefone</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.guestList}" var="guest">

                                    <fmt:parseDate value="${guest.birthDate}" pattern="yyyy-MM-dd" var="birthDate"
                                                   type="date"/>
                                    <fmt:formatDate value="${birthDate}" var="goodBirthDate"/>

                                    <tr>
                                        <th><input type="radio" name="registry" value="guest:${guest.id}"></th>
                                        <th>${guest.id}</th>
                                        <th>${guest.name}</th>
                                        <th>${JspUtils.cpfFormatter(guest.cpf)}</th>
                                        <th>${goodBirthDate}</th>
                                        <th>${JspUtils.phoneFormatter(guest.phone)}</th>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="buttonsBelowTable">
                            <button type="submit" name="action" value="Update" class="buttons">Editar</button>
                            <button type="submit" name="action" value="Delete" class="buttons">Deletar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <footer>
        <p>Desenvolvido por Gus Buzana 2023</p>
        <a href="${linkedin}" target="_blank"><img src="${path}/images/linkedin-icon.svg" alt="Logotipo do Linkedin" class="footerLogo"></a>
        <a href="${github}" target="_blank"><img src="${path}/images/github-icon.svg" alt="Logotipo do Github" class="footerLogo"></a>
    </footer>
    <script type="text/javascript" src="${path}/script/searchFunctions.js"></script>
    <script type="text/javascript" src="${path}/script/inputFunctions.js"></script>
</html>