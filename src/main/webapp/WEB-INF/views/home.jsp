<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/home.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<!-- ================= HERO SECTION ================= -->

<section class="hero">

    <div class="container hero-container">

        <div class="hero-content">

            <h1>Discover Your Perfect Style</h1>

            <p>
                Explore trendy fashion for Men, Women,
                Kids and Accessories at the best prices.
            </p>

            <a href="${pageContext.request.contextPath}/products"
               class="btn">
               Shop Now
            </a>

        </div>

        <div class="hero-image">

            <img src="${pageContext.request.contextPath}/assets/images/fashion-banner.jpg"
                 alt="Fashion Banner">

        </div>

    </div>

</section>

<!-- ================= CATEGORY SECTION ================= -->

<section class="section">

    <div class="container">

        <div class="section-title">

            <h2>Shop By Category</h2>

            <p>Browse our popular collections</p>

        </div>

        <div class="category-grid">

            <c:forEach var="category" items="${categories}">

                <div class="category-card">

                    <img
                    src="${pageContext.request.contextPath}/assets/images/categories/${category.categoryName.toLowerCase()}.jpg"
                    alt="${category.categoryName}">

                    <h3>${category.categoryName}</h3>

                    <a href="${pageContext.request.contextPath}/products?category=${category.categoryId}"
                       class="btn">
                       Explore
                    </a>

                </div>

            </c:forEach>

        </div>

    </div>

</section>

<!-- ================= LATEST PRODUCTS ================= -->

<section class="section">

    <div class="container">

        <div class="section-title">

            <h2>Latest Products</h2>

            <p>Fresh arrivals just for you</p>

        </div>

        <div class="product-grid">

            <c:forEach var="product" items="${latestProducts}">

                <div class="product-card">

                    <img
                    src="${pageContext.request.contextPath}/${product.imageUrl}"
                    alt="${product.productName}">

                    <div class="product-info">

                        <h3>${product.productName}</h3>

                        <p class="product-brand">
                            ${product.brand}
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

        </div>

    </div>

</section>

<!-- ================= FEATURES ================= -->

<section class="features section">

    <div class="container">

        <div class="features-grid">

            <div class="feature-card">

                <i class="fa-solid fa-truck-fast fa-2x"></i>

                <h3>Free Delivery</h3>

                <p>Free shipping on eligible orders.</p>

            </div>

            <div class="feature-card">

                <i class="fa-solid fa-credit-card fa-2x"></i>

                <h3>Secure Payment</h3>

                <p>100% secure payment gateway.</p>

            </div>

            <div class="feature-card">

                <i class="fa-solid fa-arrow-rotate-left fa-2x"></i>

                <h3>Easy Returns</h3>

                <p>Simple return and exchange policy.</p>

            </div>

            <div class="feature-card">

                <i class="fa-solid fa-headset fa-2x"></i>

                <h3>24 × 7 Support</h3>

                <p>Always ready to help you.</p>

            </div>

        </div>

    </div>

</section>

<!-- Footer -->
<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>