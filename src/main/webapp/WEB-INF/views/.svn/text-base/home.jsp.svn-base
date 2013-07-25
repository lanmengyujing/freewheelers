<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@ include file="header.jsp" %>

<table class="table table-striped">
<tbody>
<c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
    <table class="table table-striped" style="width: 90%; margin-left: 5%;margin-right: 5%">
        <tr>
            <td style="text-align: center;width: 15%;">
                <a data-target="#details_modal_holder" href="/item/details/${itemEntry.value.itemId}"
                   class="view_details" data-toggle="modal">
                    <img src="${itemEntry.value.imagePath}" style="max-width:120px;max-height: 120px;"/>
                </a>
            </td>
            <td style="text-align: left;width: 70%; max-width:450px;">
                <table>
                    <tr>
                        <a data-target="#details_modal_holder" href="/item/details/${itemEntry.value.itemId}"
                           class="view_details" data-toggle="modal">
                            <h3 data-name="${itemEntry.value.name}" style="line-height: 24px; color: black" id="item_name">
                                <c:out value="${itemEntry.value.name}"/>
                            </h3>
                        </a>
                    <tr>
                        <small><c:out value="${itemEntry.value.type}"/></small>
                         <c:set var="shortDescription" value="${itemEntry.value.description}"/>
                        <p>
                            <c:forTokens items="${shortDescription}" delims=' ' end="19" var="word">
                                <c:out value="${word}"/>
                            </c:forTokens>
                            <c:out value=" ..."/>
                        </p>
                        <a data-target="#details_modal_holder" href="/item/details/${itemEntry.value.itemId}"
                           class="view_details" data-toggle="modal">More details</a>
                    </tr>
                    <br/>
                </table>
            </td>

            <td style="text-align: right;width: 15%;">
                <table>
                    <tr><h4>&pound; <c:out value=" ${itemEntry.value.price}"/></h4></tr>
                    <security:authorize ifAnyGranted="ROLE_ADMIN">
                        <tr><h4>Qty: <c:out value="${itemEntry.value.quantity}"/></h4></tr>
                    </security:authorize>

                    <tr>
                        <form:form action="shoppingCart" method="post" modelAttribute="item">
                            <form:hidden path="itemId" value="${itemEntry.value.itemId}"/>
                            <security:authorize ifNotGranted="ROLE_ADMIN">
                                <button class="btn btn-success" data-name="${itemEntry.value.name}" type="submit"
                                        name="reserve" id="reserve" value="Reserve Item">
                                    Add to Cart
                                </button>
                            </security:authorize>
                        </form:form>

                    </tr>
                </table>
            </td>
        </tr>
    </table>

</c:forEach>
</tbody>
</table>
<div id="details_modal_holder" class="modal hide fade" tabindex="-1" role="dialog"
     style="width: 750px; overflow: auto; margin-right: 10px;">
    <div class="pull-right" id="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&#120;</button>
    </div>
    <div class="modal-body" style="overflow: auto;">

    </div>

</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#details_modal_holder').on('hidden', function () {
            $(this).removeData('modal');
        });

    })
</script>

<%@ include file="footer.jsp" %>
