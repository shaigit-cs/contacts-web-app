<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="/ico/favicon.ico">
        <link rel="stylesheet" href="/css/bootstrap-4.2.1.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <title>Contacts Web App</title>
    </head>
    <body>

    <div class="container">
        <nav class="navbar fixed-top navbar-expand-md navbar-light bg-light container">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <span class="nav-link"><i class="far fa-lg fa-address-book"></i></span>
                </li>
                <li class="nav-item ${activeList}">
                    <a class="nav-link" href="list">${menu_list}</a>
                </li>
                <li class="nav-item ${activeSearch}">
                    <a class="nav-link" href="search">${menu_search}</a>
                </li>
                <li class="nav-item ${activeAdd}">
                    <a class="nav-link" href="add">${menu_add}</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">${menu_login}</a>
                </li>
                <li class="nav-item">
                    <span class="navbar-text" style="padding-left: 10px; padding-right: 10px">|</span>
                </li>
                <li class="nav-item">
                    <#if currentLanguage == "pl">
                        <a class="nav-link disabled" href="language?lang=pl${referrer}">PL</a>
                    <#else>
                        <a class="nav-link" href="language?lang=pl${referrer}">PL</a>
                    </#if>
                </li>
                <li class="nav-item">
                    <#if currentLanguage == "en">
                        <a class="nav-link disabled" href="language?lang=en${referrer}">EN</a>
                    <#else>
                        <a class="nav-link" href="language?lang=en${referrer}">EN</a>
                    </#if>
                </li>
                <li class="nav-item">
                    <#if currentLanguage == "de">
                        <a class="nav-link disabled" href="language?lang=de${referrer}">DE</a>
                    <#else>
                        <a class="nav-link" href="language?lang=de${referrer}">DE</a>
                    </#if>
                </li>
            </ul>
        </nav>