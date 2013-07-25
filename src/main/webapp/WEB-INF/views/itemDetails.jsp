<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Item Details"/>
<!-- Details dialog-->

<div id="itemDetails">
    <div class="media">
        <a class="pull-left" href="#">
             <img class="media-object" src="${item.imagePath}" style="max-width:300px; max-height: 300px;"/></td>
        </a>

        <div class="media-body">
            <h3 data-name="${item.name}" class="media-heading" id="item_name">
                    <c:out value="${item.name}"/>
            </h3>

            <p>
                <small><c:out value="${item.type}"/></small>
            </p>
            <p>
                <c:out value="${item.description}"/>
            </p>
        </div>
    </div>
    <div id="modal_footer">
        <form:form action="shoppingCart" method="post" modelAttribute="item">
            <form:hidden path="itemId" value="${item.itemId}"/>
            <h4>&pound; <c:out value="${item.price}"/></h4>

            <button class="btn btn-success" data-name="${item.name}" type="submit"
                    name="reserve" id="reserve" value="Reserve Item">
                Add to Cart
            </button>
        </form:form>

    </div>
</div>