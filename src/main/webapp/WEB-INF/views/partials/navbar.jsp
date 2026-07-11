<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="navbar">

    <div class="container navbar-container">

        <!-- Logo -->
        <div class="logo">
            <a href="<c:url value='/home' />">
                FashionStore
            </a>
        </div>


        <!-- Search Bar -->
        <form class="search-bar"
              action="<c:url value='/products' />"
              method="get">

            <input
                type="text"
                name="keyword"
                placeholder="Search for fashion products">

            <button type="submit">
                Search
            </button>

        </form>


        <!-- Navigation -->
        <nav class="nav-links">

            <a href="<c:url value='/home' />">
                Home
            </a>


            <a href="<c:url value='/products' />">
                Products
            </a>


            <a href="<c:url value='/cart' />">
                Cart
            </a>


            <!-- If user is NOT logged in -->
            <c:if test="${empty sessionScope.loggedInUser}">

                <a href="<c:url value='/login' />">
                    Login
                </a>

                <a href="<c:url value='/register' />">
                    Register
                </a>

            </c:if>


            <!-- If user is logged in -->
            <c:if test="${not empty sessionScope.loggedInUser}">

                <span>
                    Welcome,
                    ${sessionScope.loggedInUser.fullName}
                </span>

<a href="<c:url value='/orders' />">
    My Orders
</a>

                <a href="<c:url value='/logout' />">
                    Logout
                </a>

            </c:if>


        </nav>

    </div>

</header>