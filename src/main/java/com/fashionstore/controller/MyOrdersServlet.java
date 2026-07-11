package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.dao.impl.OrderDAOImpl;
import com.fashionstore.model.Order;
import com.fashionstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orders")
public class MyOrdersServlet extends HttpServlet {

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


        List<Order> orders =
                orderDAO.getOrdersByUserId(
                        user.getUserId());


        request.setAttribute("orders", orders);


        request.getRequestDispatcher(
                "/WEB-INF/views/orders.jsp")
                .forward(request, response);

    }

}