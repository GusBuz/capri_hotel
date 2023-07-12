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
        <link href="${path}/styles/global.css" rel="stylesheet" type="text/css">
        <link href="${path}/styles/login.css" rel="stylesheet" type="text/css">
        <title>Login - Capri Hotel</title>
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
                    <h1>Login</h1>
                    <div class="inputContainer">
                        <label for="username">Usuário</label>
                        <input type="text" id="username" name="username">
                    </div>
                    <div class="inputContainer">
                        <label for="password">Senha</label>
                        <input type="password" id="password" name="password">
                    </div>
                    <input type="submit" value="Entrar" class="buttons">
                    <input type="hidden" name="action" value="Login">
                    <input type="hidden" name="triedAction" value="${requestScope.triedAction}">
                </form>
            </div>
            <div class="rightBackground">
                <img src="${path}/images/logo.svg" class="logoCapri" alt="Logotipo da Capri Hotel">
                <img src="${path}/images/hotel.svg" class="halfIllustration" alt="Ilustração de um hotel">
            </div>
        </div>
    </body>
    <footer>
        <p>Desenvolvido por Gus Buzana 2023</p>
        <a href="${linkedin}" target="_blank"><img src="${path}/images/linkedin-icon.svg" alt="Logotipo do Linkedin" class="footerLogo"></a>
        <a href="${github}" target="_blank"><img src="${path}/images/github-icon.svg" alt="Logotipo do Github" class="footerLogo"></a>
    </footer>
</html>