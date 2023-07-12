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
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Alata&display=swap" rel="stylesheet">
        <link href="${path}/styles/reset.css" rel="stylesheet" type="text/css">
        <link href="${path}/styles/global.css" rel="stylesheet" type="text/css">
        <link href="${path}/styles/menu.css" rel="stylesheet" type="text/css">
        <title>Menu - Capri Hotel</title>
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
                <img src="${path}/images/logo.svg" class="logoCapri" alt="Logotipo da Capri Hotel">
                <div class="menuContents">
                    <form action="${index}" method="post">
                        <button class="registerButton" type="submit">
                            <img src="${path}/images/calendar-icon.svg" class="buttonIcons" alt="Ícone de calendário">
                            <h1>Registro de reservas</h1>
                            <input type="hidden" name="action" value="ReservationPage">
                        </button>
                    </form>
                    <form action="${index}" method="post">
                        <button class="searchButton" type="submit">
                            <img src="${path}/images/search-icon.svg" class="buttonIcons" alt="Ícone de lupa">
                            <h1>Buscar</h1>
                            <input type="hidden" name="action" value="Search">
                        </button>
                    </form>
                </div>
            </div>
            <div class="rightBackground">
                <img src="${path}/images/menu.svg" class="entireIllustration" alt="Ilustração de uma garota usando um computador">
            </div>
        </div>
    </body>
    <footer>
        <p>Desenvolvido por Gus Buzana 2023</p>
        <a href="${linkedin}" target="_blank"><img src="${path}/images/linkedin-icon.svg" alt="Logotipo do Linkedin" class="footerLogo"></a>
        <a href="${github}" target="_blank"><img src="${path}/images/github-icon.svg" alt="Logotipo do Github" class="footerLogo"></a>
    </footer>
</html>