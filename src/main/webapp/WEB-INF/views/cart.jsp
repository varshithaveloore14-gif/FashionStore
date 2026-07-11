<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Shopping Cart | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/cart.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<section class="cart-section">

<div class="container">

<h2 class="page-title">Shopping Cart</h2>

<c:choose>

<c:when test="${not empty cartItems}">

<div class="cart-container">

<table class="cart-table">

<thead>

<tr>

<th>Product</th>
<th>Price</th>
<th>Quantity</th>
<th>Subtotal</th>
<th>Action</th>

</tr>

</thead>

<tbody>

<c:forEach var="item" items="${cartItems}">

<tr>

<td>

<div class="cart-product">

<img
src="${pageContext.request.contextPath}/${item.imageUrl}"
alt="${item.productName}">

<div>

<h4>${item.productName}</h4>

<p>${item.brand}</p>

<p>Size : ${item.size}</p>

</div>

</div>

</td>

<td>

₹ ${item.price}

</td>

<td>

<form action="${pageContext.request.contextPath}/cart"
      method="post"
      class="quantity-form">

<input
type="hidden"
name="action"
value="update">

<input
type="hidden"
name="cartItemId"
value="${item.cartItemId}">

<input
type="number"
name="quantity"
value="${item.quantity}"
min="1">

<button
type="submit"
class="btn-small">

Update

</button>

</form>

</td>

<td>

₹ ${item.subtotal}

</td>

<td>

<form action="${pageContext.request.contextPath}/cart"
      method="post">

<input
type="hidden"
name="action"
value="remove">

<input
type="hidden"
name="variantId"
value="${item.variantId}">

<button
type="submit"
class="remove-btn">

Remove

</button>

</form>

</td>

</tr>

</c:forEach>

</tbody>

</table>

<div class="cart-summary">

<h3>

Total :

₹ ${cartTotal}

</h3>

<div class="cart-buttons">

<a
href="${pageContext.request.contextPath}/products"
class="btn">

Continue Shopping

</a>

<a
href="${pageContext.request.contextPath}/checkout"
class="btn">

Proceed to Checkout

</a>

</div>

</div>

</div>

</c:when>

<c:otherwise>

<div class="empty-cart">

<h2>Your Cart is Empty</h2>

<p>

Looks like you haven't added anything yet.

</p>

<a
href="${pageContext.request.contextPath}/products"
class="btn">

Shop Now

</a>

</div>

</c:otherwise>

</c:choose>

</div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>