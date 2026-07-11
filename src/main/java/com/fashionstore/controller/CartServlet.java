package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.dao.impl.ProductVariantDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CartDAO cartDAO = new CartDAOImpl();
    private final CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private final ProductVariantDAO productVariantDAO = new ProductVariantDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession()
                .getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDAO.getOrCreateCartByUserId(user.getUserId());

        List<CartItem> cartItems =
                cartItemDAO.getCartItemsByCartId(cart.getCartId());

        request.setAttribute("cart", cart);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute(
                "cartTotal",
                cartItemDAO.getCartTotal(cart.getCartId()));

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession()
                .getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDAO.getOrCreateCartByUserId(user.getUserId());

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        switch (action) {

        case "add":
            addToCart(request, cart);
            break;

        case "update":
            updateCart(request);
            break;

        case "remove":
            removeFromCart(request, cart);
            break;

        case "clear":
            cartItemDAO.clearCart(cart.getCartId());
            break;

        default:
            break;
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void addToCart(HttpServletRequest request,
            Cart cart) {

        int variantId =
                Integer.parseInt(request.getParameter("variantId"));

        int quantity =
                Integer.parseInt(request.getParameter("quantity"));

        if (quantity < 1) {
            quantity = 1;
        }

        ProductVariant variant =
                productVariantDAO.getVariantById(variantId);

        if (variant == null) {
            return;
        }

        if (!productVariantDAO.isStockAvailable(variantId, quantity)) {
            return;
        }

        CartItem existingItem =
                cartItemDAO.getCartItemByCartIdAndVariantId(
                        cart.getCartId(),
                        variantId);

        if (existingItem != null) {

            int newQuantity =
                    existingItem.getQuantity() + quantity;

            if (!productVariantDAO.isStockAvailable(
                    variantId,
                    newQuantity)) {
                return;
            }

            cartItemDAO.updateCartItemQuantity(
                    existingItem.getCartItemId(),
                    newQuantity);

        } else {

            CartItem cartItem = new CartItem();

            cartItem.setCartId(cart.getCartId());
            cartItem.setVariantId(variantId);
            cartItem.setQuantity(quantity);

            cartItemDAO.addCartItem(cartItem);
        }
    }

    private void updateCart(HttpServletRequest request) {

        int cartItemId =
                Integer.parseInt(request.getParameter("cartItemId"));

        int quantity =
                Integer.parseInt(request.getParameter("quantity"));

        if (quantity < 1) {
            quantity = 1;
        }

        cartItemDAO.updateCartItemQuantity(
                cartItemId,
                quantity);
    }

    private void removeFromCart(HttpServletRequest request,
            Cart cart) {

        int variantId =
                Integer.parseInt(request.getParameter("variantId"));

        cartItemDAO.removeCartItemByCartAndVariant(
                cart.getCartId(),
                variantId);
    }
}