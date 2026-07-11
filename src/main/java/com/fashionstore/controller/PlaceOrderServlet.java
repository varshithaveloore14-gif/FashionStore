package com.fashionstore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.OrderItemDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.ProductVariantDAO;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.dao.impl.OrderItemDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.dao.impl.ProductVariantDAOImpl;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItem;
import com.fashionstore.model.Order;
import com.fashionstore.model.OrderItem;
import com.fashionstore.model.Product;
import com.fashionstore.model.ProductVariant;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CartDAO cartDAO = new CartDAOImpl();
    private final CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private final ProductVariantDAO variantDAO = new ProductVariantDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("===== PLACE ORDER SERVLET HIT =====");
    	

        User user = (User) request.getSession()
                .getAttribute("loggedInUser");

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        Cart cart = cartDAO.getCartByUserId(user.getUserId());

        if (cart == null) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        List<CartItem> cartItems =
                cartItemDAO.getCartItemsByCartId(cart.getCartId());

        if (cartItems.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/cart");

            return;
        }

        String deliveryName = user.getFullName();

        String deliveryPhone = user.getPhone();

        String addressLine1 = user.getAddressLine1();

        String addressLine2 = user.getAddressLine2();

        String city = user.getCity();

        String state = user.getState();

        String pinCode = user.getPinCode();

        String country = user.getCountry();

        String paymentMethod = "Cash on Delivery";

        BigDecimal total =
                cartItemDAO.getCartTotal(cart.getCartId());

        Order order = new Order();

        order.setUserId(user.getUserId());
        order.setTotalAmount(total);
        order.setPaymentMethod(paymentMethod);
        order.setOrderStatus("Placed");

        order.setDeliveryName(deliveryName);
        order.setDeliveryPhone(deliveryPhone);
        order.setDeliveryAddressLine1(addressLine1);
        order.setDeliveryAddressLine2(addressLine2);
        order.setDeliveryCity(city);
        order.setDeliveryState(state);
        order.setDeliveryPinCode(pinCode);
        order.setDeliveryCountry(country);
        System.out.println("Name = " + deliveryName);
        System.out.println("Phone = " + deliveryPhone);
        System.out.println("Address = " + addressLine1);
        System.out.println("Payment = " + paymentMethod);
        int orderId = orderDAO.placeOrder(order);
        System.out.println("Generated Order ID = " + orderId);

        if (orderId == -1) {

            response.sendRedirect(
                    request.getContextPath() + "/checkout");

            return;
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {

            ProductVariant variant =
                    variantDAO.getVariantById(cartItem.getVariantId());

            if (variant == null) {
                continue;
            }

            Product product =
                    productDAO.getProductById(variant.getProductId());

            if (product == null) {
                continue;
            }

            OrderItem orderItem = new OrderItem();

            orderItem.setOrderId(orderId);
            orderItem.setVariantId(cartItem.getVariantId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);

            int updatedStock =
                    variant.getStockQuantity() - cartItem.getQuantity();

            variantDAO.updateStock(
                    variant.getVariantId(),
                    updatedStock);
        }

        orderItemDAO.addOrderItems(orderItems);

        cartItemDAO.clearCart(cart.getCartId());

        response.sendRedirect(
                request.getContextPath()
                + "/order-success?orderId="
                + orderId);
    }
}