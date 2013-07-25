<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Admin Profile"/>
<%@ include file="header.jsp" %>

<h2>All orders</h2>

<table class="table table-striped">
    <tbody>
    <c:forEach var="order" items="${paidOrders}" varStatus="row">
        <tr>
            <form:form action="admin" method="post">
                <td>
                    <center><c:out value="INV-${order.pay_id}"/></center>
                </td>
                <td>
                    <a class="btn" href="/userProfile/${order.orders.get(0).account.account_name}">
                        <c:out value="${order.orders.get(0).account.account_name}"/>
                    </a>
                </td>
                <td><c:out value="${order.orders.get(0).reservation_timestamp}"/></td>
                <td class="transaction-id"><c:out value="${order.orders.get(0).transactionId}"/></td>

                <td class="item-name">
                    <ul item-data="${order.orders.get(0).order_id}" name="item" style="margin-left: 0px;">
                        <c:forEach var="reseveOrder" items="${order.orders}" varStatus="row">
                            <li style="list-style:none; margin-bottom: 5px; margin-left: 0;">${reseveOrder.item.name}</li>
                        </c:forEach>
                    </ul>
                </td>

                <td>
                    <select item-data="${order.orders.get(0).item.name}" name="status">
                        <c:forEach var="statusoption" items="${order.orders.get(0).statusOptions}" varStatus="row">
                            <option ${order.orders.get(0).status == statusoption ? 'selected="selected"' : ""}>${statusoption}</option>
                        </c:forEach>
                    </select>
                </td>

                <td><textarea rows="3" name="note">${order.orders.get(0).note}</textarea></td>

                <td>
                    <input type="hidden" value="${order.orders.get(0).order_id}" name="orderId" />
                    <button type="submit" item-data="${order.orders.get(0).item.name}" class="btn btn-warning" name="save" value="Save Changes">
                        Save Changes
                    </button>
                </td>
            </form:form>
        </tr>
    </c:forEach>
    </tbody>
    <thead>
    <tr>
        <th>Order ID</th>
        <th>User</th>
        <th>Order Time</th>
        <th>Transaction ID</th>
        <th>Items</th>
        <th>Status</th>
        <th>Note</th>
        <th></th>
    </tr>
    </thead>

</table>


<%@ include file="footer.jsp" %>