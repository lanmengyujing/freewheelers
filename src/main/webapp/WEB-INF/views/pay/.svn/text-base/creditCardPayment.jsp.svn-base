<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Credit Card Payment"/>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="<c:url value='/scripts/lib/bootstrap/css/bootstrap.css' />" type="text/css" />
    <script type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">

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

<style type="text/css">
    .control-group.price-summary {
        margin-bottom: 0px;
    }
    .price-summary .control-label{
        padding-top: 0px;
    }
</style>

<form:form id="paymentForm" class="form-horizontal" name="pay" action="" method="post" modelAttribute="creditCardPayment">

    <c:if test="${not empty errors}">
    <div id="resultsMessage" class="alert alert-error">
            ${errors}
    </div>
    </c:if>

    <div class="control-group price-summary">
        <label class="control-label" for="fld_holderName"><strong>Net:</strong></label>
        <div class="controls">
            &pound; ${paymenInfo.netTotal}
        </div>
    </div>
    <div class="control-group price-summary">
        <label class="control-label" for="fld_holderName"><strong>Duty:</strong></label>
        <div class="controls">
            <c:if test="${paymentInfo.dutyTax > 0}">
                &pound; ${paymentInfo.dutyTax}
            </c:if>
            <c:if test="${paymentInfo.dutyTax <= 0}">
                (Exempt)
            </c:if>
        </div>
    </div>


    <div class="control-group price-summary">
        <label class="control-label " for="fld_holderName"><strong>VAT:</strong></label>
        <div class="controls">
            <c:if test="${paymentInfo.vatTax > 0}">
                &pound; ${paymentInfo.vatTax}
            </c:if>
            <c:if test="${paymentInfo.vatTax <= 0}">
                (Exempt)
            </c:if>
        </div>
    </div>


    <div class="control-group price-summary">
        <label class="control-label " for="fld_holderName"><h3>Total:</h3></label>

        <div class="controls">
            <h3>&pound; ${paymentInfo.grossTotal}</h3>
        </div>
    </div>

    <div class="alert alert-info">
        Enter your payment details
    </div>

    <div class="control-group">
        <label class="control-label" for="fld_holderName">Card Holder's Name</label>

        <div class="controls">
            <input type="text" id="fld_holderName" name="holderName" pattern="\w+.*"
                   title="Fill your first and last name" value="${creditCardDetails.holderName}" required>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="fld_creditCardNumber">Card Number</label>

        <div class="controls">
            <input id="fld_creditCardNumber" name="creditCardNumber" type="text" value="${creditCardDetails.creditCardNumber}"
                   autocomplete="off" maxlength="16" pattern="\d{15,16}" title="First four digits" required>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="fld_expiryDate">Card Expiration Date</label>

        <div class="controls">
            <input type="text" id="fld_expiryDate" name="expiryDate" value="${creditCardDetails.expiryDate}"
                   placeholder="01-2020" autocomplete="off" required maxlength="7" pattern="\d{2}\-\d{4}">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="fld_securityCode">CVV/Security Code</label>

        <div class="controls">
            <div class="row-fluid">
                <div class="span3">
                    <input type="text" id="fld_securityCode" name="securityCode" autocomplete="off"
                           maxlength="4" pattern="\d{3,4}" title="Three digits at back of your card" required>
                </div>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <button id="payByCreditCard" type="submit" class="btn btn-primary">Pay</button>
        <a type="button" class="btn cancel" href="/shoppingCart">Cancel</a>
    </div>
</form:form>


<%@ include file="../footer.jsp" %>