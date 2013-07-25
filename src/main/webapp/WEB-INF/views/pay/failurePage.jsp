<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Payment Failure"/>

<%@ include file="../header.jsp" %>

<div id="resultMessage" class="alert alert-error">
    Your credit card was rejected! Please check if the information is valid, click <a href="/order/summary">here</a> to go back to order summary page.
</div>

<%@ include file="../footer.jsp" %>
