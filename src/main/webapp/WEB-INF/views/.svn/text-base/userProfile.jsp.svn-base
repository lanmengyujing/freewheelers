<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>


<h2>Your details</h2>

        <div id="user-details">
            <h4>Personal Information</h4>
            <table class="table table-striped">
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 80%;">
                </colgroup>

                <tr>
                    <td>Name:</td>
                    <td>${userDetail.account_name} </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${userDetail.emailAddress}</td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td>${userDetail.phoneNumber}</td>
                </tr>

            </table>

            <h4>Address</h4>
            <table class="table table-striped">
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 80%;">
                </colgroup>

                <tr>
                    <td>Street 1:</td>
                    <td>${userDetail.address.streetOne}</td>
                </tr>
                <tr>
                    <td>Street 2:</td>
                    <td>${userDetail.address.streetTwo}</td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>${userDetail.address.city}</td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>${userDetail.address.state}</td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>${userDetail.address.country}</td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td>${userDetail.address.zip}
                </tr>


            </table>

            <a class="btn btn-success" name="editDetails" href="/account/editDetails">Edit Details</a>
        </div>


        <security:authorize ifNotGranted="ROLE_ADMIN">

            <h2>Your Orders</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
                    <tr>

                        <td data-name="${itemEntry.value.name}"><c:out value="${itemEntry.value.name}"/></td>

                        <td><c:out value="${itemEntry.value.price}"/></td>

                        <td><c:out value="${itemEntry.value.description}"/></td>

                        <td><c:out value="${itemEntry.value.type}"/></td>

                    </tr>
                 </c:forEach>

                </tbody>
            </table>
        </security:authorize>

<%@ include file="footer.jsp" %>