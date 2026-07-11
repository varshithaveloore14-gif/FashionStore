<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Login | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/auth.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<section class="auth-section">

<div class="container auth-container">

    <!-- Left Side -->

    <div class="auth-left">

        <h2>Login</h2>

        <p class="auth-subtitle">
            Welcome back! Please login to continue shopping.
        </p>

        <c:if test="${not empty error}">
            <div class="alert alert-error">
                ${error}
            </div>
        </c:if>

        <c:if test="${param.success == 'true'}">
            <div class="alert alert-success">
                Registration successful. Please login.
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login"
              method="post">

            <div class="form-group">

                <label>Email Address</label>

                <input
                    type="email"
                    name="email"
                    placeholder="Enter your email"
                    required>

            </div>

            <div class="form-group">

                <label>Password</label>

                <input
                    type="password"
                    name="password"
                    placeholder="Enter your password"
                    required>

            </div>

            <button
                type="submit"
                class="btn auth-btn">

                Login

            </button>

            <div class="auth-footer">

                <p>

                    Don't have an account?

                    <a href="${pageContext.request.contextPath}/register">

                        Register

                    </a>

                </p>

            </div>

        </form>

    </div>

    <!-- Right Side -->

    <div class="auth-right">

        <h2>Welcome Back!</h2>

        <p>

            Continue your fashion journey with Fashion Store.

            Discover premium collections for Men, Women,
            Kids and Accessories.

            Enjoy secure shopping and fast delivery.

        </p>

        <img
            src="${pageContext.request.contextPath}/assets/images/login-banner.jpg"
            alt="Fashion Store Login">

    </div>

</div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>