<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Register | Fashion Store</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/auth.css">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<jsp:include page="/WEB-INF/views/partials/navbar.jsp"/>

<!-- ================= REGISTER SECTION ================= -->

<section class="auth-section">

<div class="container">

<div class="auth-container">

<!-- ================= LEFT SIDE ================= -->

<div class="auth-left">

<h2>Create Your Account</h2>

<p class="subtitle">
Join Fashion Store and discover premium fashion collections.
</p>

<c:if test="${not empty error}">
<div class="alert error">
${error}
</div>
</c:if>

<form action="${pageContext.request.contextPath}/register"
      method="post"
      class="auth-form">

<div class="form-group">

<label>Full Name</label>

<input
type="text"
name="fullName"
placeholder="Enter your full name"
required>

</div>

<div class="form-group">

<label>Email Address</label>

<input
type="email"
name="email"
placeholder="Enter your email"
required>

</div>

<div class="form-group">

<label>Phone Number</label>

<input
type="text"
name="phone"
placeholder="Enter your phone number"
required>

</div>

<div class="form-group">

<label>Password</label>

<input
type="password"
name="password"
placeholder="Create a password"
required>

</div>

<div class="form-group">

<label>Gender</label>

<select name="gender" required>

<option value="">Select Gender</option>

<option value="Male">Male</option>

<option value="Female">Female</option>

<option value="Other">Other</option>

</select>

</div>

<div class="form-group">

<label>Address Line 1</label>

<input
type="text"
name="addressLine1"
placeholder="House No / Street"
required>

</div>

<div class="form-group">

<label>Address Line 2</label>

<input
type="text"
name="addressLine2"
placeholder="Apartment / Landmark (Optional)">

</div>

<div class="form-row">

<div class="form-group">

<label>City</label>

<input
type="text"
name="city"
placeholder="Enter your city"
required>

</div>

<div class="form-group">

<label>State</label>

<input
type="text"
name="state"
placeholder="Enter your state"
required>

</div>

</div>

<div class="form-row">

<div class="form-group">

<label>PIN Code</label>

<input
type="text"
name="pinCode"
placeholder="PIN Code"
required>

</div>

<div class="form-group">

<label>Country</label>

<input
type="text"
name="country"
value="India"
required>

</div>

</div>

<button
type="submit"
class="btn auth-btn">

Create Account

</button>

<div class="auth-footer">

<p>

Already have an account?

<a href="${pageContext.request.contextPath}/login">

Login Here

</a>

</p>

</div>

</form>

</div>

<!-- ================= RIGHT SIDE ================= -->

<div class="auth-right">

<h2>Welcome to Fashion Store</h2>

<p>

Discover the latest collections for Men, Women, Kids and Accessories.

Shop securely with an elegant shopping experience.

</p>

<img
src="${pageContext.request.contextPath}/assets/images/login-banner.jpg"
alt="Fashion Store">

</div>

</div>

</div>

</section>

<!-- ================= FOOTER ================= -->

<jsp:include page="/WEB-INF/views/partials/footer.jsp"/>

</body>

</html>