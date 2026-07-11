package com.fashionstore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.CategoryDAOImpl;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Category;
import com.fashionstore.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String categoryParam = request.getParameter("category");
        String minPriceParam = request.getParameter("minPrice");
        String maxPriceParam = request.getParameter("maxPrice");

        Integer categoryId = null;
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        try {
            if (categoryParam != null && !categoryParam.isBlank()) {
                categoryId = Integer.parseInt(categoryParam);
            }

            if (minPriceParam != null && !minPriceParam.isBlank()) {
                minPrice = new BigDecimal(minPriceParam);
            }

            if (maxPriceParam != null && !maxPriceParam.isBlank()) {
                maxPrice = new BigDecimal(maxPriceParam);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Product> products;

        if ((categoryId == null)
                && (keyword == null || keyword.isBlank())
                && minPrice == null
                && maxPrice == null) {

            products = productDAO.getAllActiveProducts();

        } else {

            products = productDAO.getFilteredProducts(
                    categoryId,
                    keyword,
                    minPrice,
                    maxPrice);
        }

        List<Category> categories =
                categoryDAO.getAllCategories();

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);

        request.setAttribute("selectedCategory", categoryId);
        request.setAttribute("keyword", keyword);
        request.setAttribute("minPrice", minPriceParam);
        request.setAttribute("maxPrice", maxPriceParam);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}