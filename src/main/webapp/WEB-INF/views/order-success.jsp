<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Order Placed | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/order-success.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<section class="success-section">

<div class="container">

<div class="success-card">

<div class="success-icon">

✅

</div>

<h1>

Order Placed Successfully!

</h1>

<p>

Thank you for shopping with Fashion Store.

</p>

<hr>

<div class="order-details">

<p>

<strong>Order ID :</strong>

#${order.orderId}

</p>

<p>

<strong>Order Date :</strong>

${order.orderDate}

</p>

<p>

<strong>Payment Method :</strong>

${order.paymentMethod}

</p>

<p>

<strong>Status :</strong>

${order.orderStatus}

</p>

<p>

<strong>Total Amount :</strong>

₹ ${order.totalAmount}

</p>

</div>

<hr>

<h3>

Delivery Address

</h3>

<p>

${order.deliveryName}

</p>

<p>

${order.deliveryPhone}

</p>

<p>

${order.deliveryAddressLine1}

</p>

<c:if test="${not empty order.deliveryAddressLine2}">

<p>

${order.deliveryAddressLine2}

</p>

</c:if>

<p>

${order.deliveryCity},
${order.deliveryState}

</p>

<p>

${order.deliveryPinCode}

</p>

<p>

${order.deliveryCountry}

</p>

<div class="success-buttons">

<a href="${pageContext.request.contextPath}/products"
class="btn">

Continue Shopping

</a>

<a href="${pageContext.request.contextPath}/orders"
class="btn">

My Orders

</a>


</div>

</div>

</div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>