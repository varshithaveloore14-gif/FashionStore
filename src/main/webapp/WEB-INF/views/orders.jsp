<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

<title>My Orders | Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/my-orders.css">

</head>


<body>


<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>



<section class="orders-section">

<div class="container">


<h2 class="page-title">
My Orders
</h2>



<c:choose>


<c:when test="${empty orders}">
<div class="orders-card">

<h3>
No Orders Found
</h3>

<p>
You have not placed any orders yet.
</p>

</div>


</c:when>



<c:otherwise>


<c:forEach var="order" items="${orders}">


<div class="checkout-card">


<h3>
Order ID : #${order.orderId}
</h3>


<p>
<strong>Date:</strong>
${order.orderDate}
</p>


<p>
<strong>Total Amount:</strong>
₹ ${order.totalAmount}
</p>


<p>
<strong>Payment:</strong>
${order.paymentMethod}
</p>


<p>
<strong>Status:</strong>
${order.orderStatus}
</p>


<a class="btn view-order-btn"
href="${pageContext.request.contextPath}/order-success?orderId=${order.orderId}">
View Order
</a>


</div>


</c:forEach>


</c:otherwise>


</c:choose>


</div>

</section>



<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>


</body>

</html>