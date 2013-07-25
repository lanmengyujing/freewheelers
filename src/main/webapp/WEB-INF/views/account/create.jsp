<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>



    <div id="inputsContainer" >
    <div class="alert alert-info">
        Create a new account <span class="text-error">(*) Fields are Mandatory</span>
    </div>

    <c:if test="${not empty errors}">
        <div id="resultsMessage" class="alert alert-error">
            There were errors.
        </div>
    </c:if>

	<form class="form-horizontal account-form" action="/account/create" method="post">
        <fieldset>
        <legend>Personal Details</legend>
        <div class="control-group">
            <label class="control-label" for="fld_email">Email<span class="text-error">*</span></label>
            <div class="controls">
                <input type="text" id="fld_email" placeholder="somebody@something.com" name="emailAddress" value="${account.emailAddress}">
                <c:if test="${not empty errors['email']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["email"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_password">Password <span class="text-error">*</span></label>
            <div class="controls">
                <input type="password" id="fld_password" placeholder="secret password" name="password">
                <c:if test="${not empty errors['password']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["password"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_name">Name <span class="text-error">*</span></label>
            <div class="controls">
                <input type="text" id="fld_name" placeholder="Your Name" name="account_name" value="${account.account_name}">
                <c:if test="${not empty errors['name']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["name"]}</span>
                </c:if>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_phoneNumber">Phone Number
                <br/>
                <small>(Include country code)</small>
            </label>
            <div class="controls">
                <input type="text" id="fld_phoneNumber" placeholder="555-123456" name="phoneNumber" value="${account.phoneNumber}">
                <c:if test="${not empty errors['phoneNumber']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["phoneNumber"]}</span>
                </c:if>
            </div>
        </div>
        </fieldset>

        <fieldset>
        <legend>Address</legend>
        <div class="control-group">
            <label class="control-label" for="fld_streetOne" >Street 1</label>
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


            <label class="control-label" for="fld_country">Country <span class="text-error">*</span>
                <br/>
                <small>(Countries we ship to)</small>
            </label>

            <div class="controls">
                <select id="fld_country" name="address.country">
                    <option></option>
                    <c:forEach var="country" items="${country}" varStatus="row">
                        <option <c:if test="${country == account.address.country}">selected="selected"</c:if> >${country}</option>
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

        <div class="control-group">
            <div class="controls">
                <label class="checkbox inline" for="fld_termsConditions">
                    <input type="checkbox" id="fld_termsConditions" name="acceptedTerms">
                    I agree with <a href="/account/terms" target="_blank" id="terms_and_conditions_link">terms and conditions</a>

                </label>
                <c:if test="${not empty errors['acceptedTerms']}">
                    <span class="text-error" style="margin-left: 25px;">${errors["acceptedTerms"]}</span>
                </c:if>
            </div>
        </div>
        </fieldset>

        <div class="control-group">
            <div class="controls">
                <button type="submit" id="createAccount" value="Submit" class="btn btn-success">Create Account</button>
            </div>
        </div>

	</form>

    </div>




<%@ include file="../footer.jsp" %>
