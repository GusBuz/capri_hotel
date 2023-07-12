<%@ page import="util.JspUtils" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.JspUtils" %>
<c:set value="<%=java.time.LocalDate.of(1900, 1, 1)%>" var="minBirthDate"/>
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
        <title>Atualizar hóspede - Capri Hotel</title>
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
                <img src="${path}/images/update.svg" class="halfIllustration"
                     alt="Ilustração de uma mulher mexendo num celular">
            </div>
            <div class="rightBackground">
                <form action="${index}" method="post" onsubmit="return numberValidation();">
                    <h1>Alteração de registro</h1>
                    <h2>Hóspede</h2>

                    <div class="inputContainer">
                        <label for="name">Nome</label>
                        <input type="text" id="name" name="name" value="${requestScope.guest.name}"
                               oninput="nameFormatter(this)" required>
                    </div>

                    <div class="inputContainer">
                        <label for="cpf">CPF</label>
                        <input type="text" id="cpf" name="cpf" value="${JspUtils.cpfFormatter(requestScope.guest.cpf)}"
                               oninput="cpfFormatter(this)" maxlength="14" required>
                    </div>

                    <div class="inputContainer">
                        <label for="birthDate">Data de nascimento</label>
                        <input type="date" id="birthDate" name="birthDate" value="${requestScope.guest.birthDate}"
                               min="${minBirthDate}" max="${now.minusYears(18)}" required>
                    </div>

                    <div class="inputContainer">
                        <label for="phone">Telefone</label>
                        <input type="text" id="phone" name="phone" value="${JspUtils.phoneFormatter(requestScope.guest.phone)}"
                               oninput="phoneFormatter(this)" maxlength="15" required>
                    </div>

                    <input type="submit" value="Entrar" class="buttons">
                    <input type="hidden" name="guestId" value="${requestScope.guest.id}">
                    <input type="hidden" name="action" value="UpdateGuest">
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