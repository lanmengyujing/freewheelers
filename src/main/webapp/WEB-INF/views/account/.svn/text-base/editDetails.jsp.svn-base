<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Edit Details"/>

<%@ include file="../header.jsp" %>

<div class="alert alert-info">
    Edit account details
</div>

<c:if test="${not empty errors}">
    <div id="resultsMessage" class="alert alert-error">
        There were errors.
    </div>
</c:if>

<form class="form-horizontal account-form" action="/account/editDetails" method="post">


    <fieldset>
        <legend>Personal Information</legend>

        <div class="control-group">
            <label class="control-label">Phone Number<br>
                <small>(Include country code)</small>
            </label>
            <div class="controls">
                <input type="text" id="fld_phoneNumber" name="phoneNumber" value="${account.phoneNumber}">
                <c:if test="${not empty errors['phoneNumber']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["phoneNumber"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">Email Address <span class="text-error">*</span></label>
            <div class="controls">
                <input type="text" id="fld_emailAddress" name="emailAddress" value="${account.emailAddress}">
                <c:if test="${not empty errors['email']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["email"]}</span>
                </c:if>
            </div>
        </div>
    </fieldset>

    <fieldset>
        <legend>Address</legend>
        <div class="control-group">
            <label class="control-label" for="fld_streetOne">Street 1</label>
            <div class="controls">
                <input type="text" id="fld_streetOne" placeholder="street one name" name="address.streetOne" value="${account.address.streetOne}">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_streetTwo">Street 2</label>
            <div class="controls">
                <input type="text" id="fld_streetTwo" placeholder="street two name" name="address.streetTwo" value="${account.address.streetTwo}">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_city">City</label>
            <div class="controls">
                <input type="text" id="fld_city" placeholder="city" name="address.city" value="${account.address.city}">
                <c:if test="${not empty errors['city']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["city"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_state">State</label>
            <div class="controls">
                <input type="text" id="fld_state" placeholder="state" name="address.state" value="${account.address.state}">
                <c:if test="${not empty errors['state']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["state"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_country">Country</label>
            <div class="controls">
                <select id="fld_country" name="address.country">
                    <option>${account.address.country}</option>
                    <c:forEach var="country" items="${country}" varStatus="row">
                        <c:if test="${country != account.address.country}">
                            <option>${country}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <c:if test="${not empty errors['country']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["country"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_zip">Zip</label>
            <div class="controls">
                <input type="text" id="fld_zip" placeholder="zip" name="address.zip" value="${account.address.zip}">
            </div>
        </div>
    </fieldset>

    <div class="control-group">
        <div class="controls">
            <button type="submit" id="editUserDetails" value="Submit" class="btn btn-success">Save Changes</button>
        </div>
    </div>

</form>

<%@ include file="../footer.jsp" %>
