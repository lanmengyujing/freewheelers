<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<h2 id="cart-title">Your Cart</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reserveOrderEntry" items="${reserveOrderGrid.reserveOrderMap}" varStatus="row">
        <tr class="cart">
            <td class="itemName"><c:out value="${reserveOrderEntry.value.getItem().name}"/></td>
            <td><c:out value="${reserveOrderEntry.value.getItem().price}"/></td>
            <td><c:out value="${reserveOrderEntry.value.getItem().description}"/></td>
            <td class="itemQuantity"><c:out value="${reserveOrderEntry.value.getItem_quantity()}"/></td>
            <td><c:out value="${reserveOrderEntry.value.getItem().type}"/></td>
            <td>
                <form action="/shoppingCart/delete" method="POST">
                    <input type="hidden" name="orderId" value="${reserveOrderEntry.value.order_id}"/>
                    <input type="submit" class="btn btn-danger" data-delete-name="${reserveOrderEntry.value.getItem().name}" value="Delete"/>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="totalPriceOfCart">Net Total: </div>


<c:if test="${not empty reserveOrderGrid.reserveOrderMap}">
    <div class="form-actions" style="text-align: right;">
        <a href="/pay/creditCard" class="btn btn-success" type="submit" id="toPaymentByCreditCard">Pay Now</a>
    </div>
</c:if>
<c:if test="${empty reserveOrderGrid.reserveOrderMap}">
    <div class="form-actions" style="text-align: right;">
        <button  class="btn btn-success disabled" type="submit" id="toPaymentByCreditCardDisabled">Pay Now</button>
    </div>
</c:if>


<%@ include file="footer.jsp" %>