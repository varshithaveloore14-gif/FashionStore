<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>${product.productName} | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/product-details.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<section class="product-details-section">

<div class="container">

<div class="product-details">

    <!-- Product Image -->

    <div class="product-image">

        <img
            src="${pageContext.request.contextPath}/${product.imageUrl}"
            alt="${product.productName}">

    </div>

    <!-- Product Details -->

    <div class="product-content">

        <span class="brand">

            ${product.brand}

        </span>

        <h1>

            ${product.productName}

        </h1>

        <h2 class="price">

            ₹ ${product.price}

        </h2>

        <p class="description">

            ${product.description}

        </p>

        <!-- Add To Cart Form -->

        <form action="${pageContext.request.contextPath}/cart"
              method="post">

            <input
                type="hidden"
                name="action"
                value="add">

            <h3>Select Size</h3>

            <select
                name="variantId"
                required>

                <c:forEach var="variant" items="${variants}">

                    <option value="${variant.variantId}">

                        ${variant.size}
                        (${variant.stockQuantity} Available)

                    </option>

                </c:forEach>

            </select>

            <div class="quantity-box">

                <label>

                    Quantity

                </label>

                <input
                    type="number"
                    name="quantity"
                    value="1"
                    min="1"
                    required>

            </div>

            <button
                type="submit"
                class="btn add-cart-btn">

                Add To Cart

            </button>

        </form>

    </div>

</div>

</div>

</section>

<!-- Related Products -->

<section class="related-products">

<div class="container">

<h2>

Related Products

</h2>

<div class="product-grid">

<c:forEach var="related" items="${relatedProducts}">

<div class="product-card">

<img
src="${pageContext.request.contextPath}/${related.imageUrl}"
alt="${related.productName}">

<div class="product-info">

<h3>

${related.productName}

</h3>

<p>

${related.brand}

</p>

<h4>

₹ ${related.price}

</h4>

<a
href="${pageContext.request.contextPath}/product?id=${related.productId}"
class="btn">

View Details

</a>

</div>

</div>

</c:forEach>

</div>

</div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>