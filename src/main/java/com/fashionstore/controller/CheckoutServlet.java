package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CartDAO cartDAO = new CartDAOImpl();
    private final CartItemDAO cartItemDAO = new CartItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession()
                .getAttribute("loggedInUser");

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        Cart cart = cartDAO.getOrCreateCartByUserId(
                user.getUserId());

        List<CartItem> cartItems =
                cartItemDAO.getCartItemsByCartId(
                        cart.getCartId());

        if (cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        request.setAttribute("user", user);

        request.setAttribute("cartItems",
                cartItems);

        request.setAttribute("cartTotal",
                cartItemDAO.getCartTotal(
                        cart.getCartId()));

        request.getRequestDispatcher(
                "/WEB-INF/views/checkout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * Place Order
         * (We'll implement this next.)
         */

    }

}