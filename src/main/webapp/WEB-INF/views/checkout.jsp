<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Checkout | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/checkout.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<section class="checkout-section">

<div class="container">

<h2 class="page-title">

Checkout

</h2>

<div class="checkout-container">

<!-- ================= LEFT ================= -->

<div class="checkout-left">

<div class="checkout-card">

<h3>

Shipping Address

</h3>

<p>

<strong>${user.fullName}</strong>

</p>

<p>

${user.addressLine1}

</p>

<c:if test="${not empty user.addressLine2}">

<p>

${user.addressLine2}

</p>

</c:if>

<p>

${user.city},
${user.state}
-
${user.pinCode}

</p>

<p>

${user.country}

</p>

<p>

Phone :
${user.phone}

</p>

<p>

Email :
${user.email}

</p>

</div>

<div class="checkout-card">

<h3>

Payment Method

</h3>

<label>

<input
type="radio"
checked
disabled>

Cash On Delivery (COD)

</label>

</div>

</div>

<!-- ================= RIGHT ================= -->

<div class="checkout-right">

<div class="checkout-card">

<h3>

Order Summary

</h3>

<c:forEach var="item" items="${cartItems}">

<div class="summary-item">

<div class="summary-left">

<img
src="${pageContext.request.contextPath}/${item.imageUrl}"
alt="${item.productName}">

<div>

<h4>

${item.productName}

</h4>

<p>

Brand :
${item.brand}

</p>

<p>

Size :
${item.size}

</p>

<p>

Qty :
${item.quantity}

</p>

</div>

</div>

<div class="summary-right">

₹ ${item.subtotal}

</div>

</div>

<hr>

</c:forEach>

<div class="total-box">

<h3>

Grand Total

</h3>

<h2>

₹ ${cartTotal}

</h2>

</div>

<form action="place-order" method="post">

<button
type="submit"
class="btn place-order-btn">

Place Order

</button>

</form>

</div>

</div>

</div>

</div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>