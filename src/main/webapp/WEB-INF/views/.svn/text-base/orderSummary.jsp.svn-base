<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Order Summery"/>
<%@ include file="header.jsp" %>

<h2>Your Orders</h2>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Order Id</th>
            <th>Price</th>
            <th>Status</th>
            <th>Option</th>
        </tr>
    </thead>
    <tbody>

        <c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
                <tr>

                    <td><c:out value="${itemEntry.value.name}"/></td>

                    <td><c:out value="${itemEntry.value.price}"/></td>

                    <td><c:out value="${itemEntry.value.description}"/></td>

                    <td><c:out value="${itemEntry.value.type}"/></td>

                </tr>
        </c:forEach>
        <tr>
            <td>001</td>
            <td>59.04</td>
            <td>not payed</td>
            <td><a id="toPaymentByCreditCard" href="/pay/creditCard">Credit Card Pay</a></td>
        </tr>
    </tbody>
</table>


<%@ include file="footer.jsp" %>