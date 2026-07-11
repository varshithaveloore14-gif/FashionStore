<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Products | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/products.css">

</head>

<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<!-- ================= PAGE HEADER ================= -->

<section class="page-header">

    <div class="container">

        <h1>Our Products</h1>

        <p>Discover the latest fashion collections.</p>

    </div>

</section>

<!-- ================= FILTER SECTION ================= -->

<section class="filter-section">

<div class="container">

<form action="${pageContext.request.contextPath}/products"
      method="get"
      class="filter-form">

<input type="text"
       name="keyword"
       placeholder="Search products..."
       value="${keyword}">

<select name="category">

<option value="">All Categories</option>

<c:forEach var="category" items="${categories}">

<option value="${category.categoryId}"
<c:if test="${selectedCategory == category.categoryId}">
selected
</c:if>>

${category.categoryName}

</option>

</c:forEach>

</select>

<input type="number"
       name="minPrice"
       placeholder="Min Price"
       value="${minPrice}">

<input type="number"
       name="maxPrice"
       placeholder="Max Price"
       value="${maxPrice}">

<button type="submit" class="btn">

Search

</button>

</form>

</div>

</section>

<!-- ================= PRODUCT GRID ================= -->

<section class="products-section">

<div class="container">

<div class="product-grid">

<c:choose>

<c:when test="${not empty products}">

<c:forEach var="product" items="${products}">

<div class="product-card">

<img class="product-image"
     src="${pageContext.request.contextPath}/${product.imageUrl}"
     alt="${product.productName}">

<div class="product-details">

<h3>${product.productName}</h3>

<p class="product-brand">${product.brand}</p>

<p class="product-description">

${product.description}

</p>

<h4 class="product-price">

₹ ${product.price}

</h4>

<a href="${pageContext.request.contextPath}/product?id=${product.productId}"
   class="btn">

View Details

</a>

</div>

</div>

</c:forEach>

</c:when>

<c:otherwise>

<div class="no-products">

<h2>No Products Found</h2>

<p>Please try a different search or filter.</p>

</div>

</c:otherwise>

</c:choose>

</div>

</div>

</section>

<!-- Footer -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>