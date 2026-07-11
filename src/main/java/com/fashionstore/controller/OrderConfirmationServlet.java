package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order-success")
public class OrderConfirmationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final OrderDAO orderDAO = new OrderDAOImpl();

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

        String orderIdParam = request.getParameter("orderId");

        if (orderIdParam == null) {

            response.sendRedirect(
                    request.getContextPath() + "/");

            return;
        }

        int orderId = Integer.parseInt(orderIdParam);

        Order order = orderDAO.getOrderById(orderId);

        if (order == null) {

            response.sendRedirect(
                    request.getContextPath() + "/");

            return;
        }

        if (order.getUserId() != user.getUserId()) {

            response.sendRedirect(
                    request.getContextPath() + "/");

            return;
        }

        request.setAttribute("order", order);

        request.getRequestDispatcher(
                "/WEB-INF/views/order-success.jsp")
                .forward(request, response);
    }
}