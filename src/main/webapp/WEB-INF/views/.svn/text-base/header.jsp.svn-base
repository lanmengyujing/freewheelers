<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="<c:url value='/scripts/lib/bootstrap/css/bootstrap.css' />" type="text/css" />
    <link rel="stylesheet" href="<c:url value='/scripts/lib/freewhelers.css' />" type="text/css" />
    <script type="text/javascript" src="<c:url value='/scripts/lib/jquery-1.10.1.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/scripts/lib/bootstrap/js/bootstrap.min.js'/>"> </script>
    <script type="text/javascript" src="<c:url value='/scripts/lib/vendor/jquery.ui.widget.js' />"></script>

</head>
<body>

        <div class="navbar">
            <div class="navbar-inner">
                <a class="brand" href="<c:url value='/' />" class="nav_link">
                    <img width="20px;"src="<c:url value='/images/logo-20x20.png' />">
                    Freewheelers
                </a>
                <ul class="nav pull-left">
                    <li><a href="<c:url value='/' />" class="nav_link"><i class="icon-home"></i> Home</a></li>
                </ul>
                <ul class="nav pull-right">


                    <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <li><a href="<c:url value='/admin' />" class="nav_link"><i class="icon-list-alt"></i> Manage Orders</a></li>
                        <li><a href="item" id="manageItems" class="nav_link"><i class="icon-briefcase"></i> Manage items</a></li>
                    </security:authorize>

                    <security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
                        <li><a href="<c:url value='/userProfile' />" class="nav_link"><i class="icon-user"></i> Login</a></li>
                    </security:authorize>

                    <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                        <li><a href="<c:url value='/userProfile' />" class="nav_link"><i class="icon-user"></i> User Profile</a></li>
                    </security:authorize>


                    <security:authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
                        <li><a href="<c:url value='/account/create' />" class="nav_link"><i class="icon-plus"></i> Create Account</a></li>
                    </security:authorize>

                    <security:authorize ifAnyGranted="ROLE_USER">
                        <li><a href="<c:url value="/shoppingCart" />" class="nav_link"><i class="icon-shopping-cart"></i> Cart (${cartCount == null ? 0 : cartCount})</a></li>
                    </security:authorize>

                   <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                        <li><a href="<c:url value="j_spring_security_logout" />" class="nav_link"><i class="icon-eject"></i> Logout</a></li>
                    </security:authorize>
                </ul>

                <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                    <p id="welcome" class="navbar-text">Welcome <security:authentication property="principal.username"/>!</p>
                </security:authorize>
            </div>
        </div>

        <!--[if lt IE 9]>
        <div class="alert alert-warning">
            You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.
        </div>
        <![endif]-->
