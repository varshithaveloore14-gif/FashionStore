package com.fashionstore.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private static final String INSERT_PRODUCT_SQL = """
            INSERT INTO products
            (category_id, product_name, brand, description,
             price, image_url, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE_PRODUCT_SQL = """
            UPDATE products SET
            category_id = ?,
            product_name = ?,
            brand = ?,
            description = ?,
            price = ?,
            image_url = ?,
            is_active = ?
            WHERE product_id = ?
            """;

    private static final String DELETE_PRODUCT_SQL = """
            DELETE FROM products
            WHERE product_id = ?
            """;

    private static final String GET_PRODUCT_BY_ID_SQL = """
            SELECT * FROM products
            WHERE product_id = ?
            """;

    private static final String GET_ALL_PRODUCTS_SQL = """
            SELECT * FROM products
            ORDER BY product_id DESC
            """;

    private static final String GET_ALL_ACTIVE_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE is_active = true
            ORDER BY product_id DESC
            """;

    private static final String GET_PRODUCTS_BY_CATEGORY_SQL = """
            SELECT * FROM products
            WHERE category_id = ?
            AND is_active = true
            ORDER BY product_name
            """;

    private static final String SEARCH_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE product_name LIKE ?
            OR brand LIKE ?
            ORDER BY product_name
            """;

    private static final String GET_PRODUCTS_BY_PRICE_RANGE_SQL = """
            SELECT * FROM products
            WHERE price BETWEEN ? AND ?
            ORDER BY price
            """;

    private static final String GET_PRODUCTS_BY_PRICE_ASC_SQL = """
            SELECT * FROM products
            ORDER BY price ASC
            """;

    private static final String GET_PRODUCTS_BY_PRICE_DESC_SQL = """
            SELECT * FROM products
            ORDER BY price DESC
            """;

    private static final String GET_LATEST_PRODUCTS_SQL = """
            SELECT * FROM products
            ORDER BY created_at DESC
            LIMIT ?
            """;

    private static final String GET_FILTERED_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE category_id = ?
            AND product_name LIKE ?
            AND price BETWEEN ? AND ?
            AND is_active = true
            ORDER BY product_name
            """;

    private static final String GET_RELATED_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE category_id = ?
            AND product_id <> ?
            AND is_active = true
            LIMIT ?
            """;
    @Override
    public boolean addProduct(Product product) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_PRODUCT_SQL)
        ) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setBigDecimal(5, product.getPrice());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.setBoolean(7, product.isActive());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean updateProduct(Product product) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_PRODUCT_SQL)
        ) {

            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getBrand());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setBigDecimal(5, product.getPrice());
            preparedStatement.setString(6, product.getImageUrl());
            preparedStatement.setBoolean(7, product.isActive());
            preparedStatement.setInt(8, product.getProductId());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteProduct(int productId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_PRODUCT_SQL)
        ) {

            preparedStatement.setInt(1, productId);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public Product getProductById(int productId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCT_BY_ID_SQL)
        ) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToProduct(resultSet);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ALL_PRODUCTS_SQL);

            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getAllActiveProducts() {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ALL_ACTIVE_PRODUCTS_SQL);

            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_SQL)
        ) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    products.add(mapResultSetToProduct(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(SEARCH_PRODUCTS_SQL)
        ) {

            String searchKeyword = "%" + keyword + "%";

            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    products.add(mapResultSetToProduct(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getProductsByPriceRange(BigDecimal minPrice,
                                                 BigDecimal maxPrice) {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCTS_BY_PRICE_RANGE_SQL)
        ) {

            preparedStatement.setBigDecimal(1, minPrice);
            preparedStatement.setBigDecimal(2, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    products.add(mapResultSetToProduct(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getProductsSortedByPriceAsc() {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCTS_BY_PRICE_ASC_SQL);

            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    @Override
    public List<Product> getProductsSortedByPriceDesc() {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_PRODUCTS_BY_PRICE_DESC_SQL);

            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    @Override
    public List<Product> getLatestProducts(int limit) {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_LATEST_PRODUCTS_SQL)
        ) {

            preparedStatement.setInt(1, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    products.add(mapResultSetToProduct(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getFilteredProducts(Integer categoryId,
            String keyword,
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        List<Product> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM products WHERE is_active = true "
        );

        List<Object> parameters = new ArrayList<>();


        if (categoryId != null) {
            sql.append(" AND category_id = ?");
            parameters.add(categoryId);
        }


        if (keyword != null && !keyword.isBlank()) {
            sql.append(" AND (product_name LIKE ? OR brand LIKE ?)");
            
            String search = "%" + keyword + "%";
            parameters.add(search);
            parameters.add(search);
        }


        if (minPrice != null && maxPrice != null) {
            sql.append(" AND price BETWEEN ? AND ?");
            parameters.add(minPrice);
            parameters.add(maxPrice);
        }
        else if (minPrice != null) {
            sql.append(" AND price >= ?");
            parameters.add(minPrice);
        }
        else if (maxPrice != null) {
            sql.append(" AND price <= ?");
            parameters.add(maxPrice);
        }


        sql.append(" ORDER BY product_name");


        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql.toString())
        ) {


            for (int i = 0; i < parameters.size(); i++) {

                Object value = parameters.get(i);

                if (value instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer)value);
                }

                else if (value instanceof BigDecimal) {
                    preparedStatement.setBigDecimal(i + 1, (BigDecimal)value);
                }

                else if (value instanceof String) {
                    preparedStatement.setString(i + 1, (String)value);
                }

            }


            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {

                products.add(mapResultSetToProduct(resultSet));

            }


        } catch(Exception e) {

            e.printStackTrace();

        }


        return products;
    }

    @Override
    public List<Product> getRelatedProducts(int categoryId,
                                            int excludeProductId,
                                            int limit) {

        List<Product> products = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_RELATED_PRODUCTS_SQL)
        ) {

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, excludeProductId);
            preparedStatement.setInt(3, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    products.add(mapResultSetToProduct(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    private Product mapResultSetToProduct(ResultSet resultSet) throws Exception {

        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setCategoryId(resultSet.getInt("category_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setBrand(resultSet.getString("brand"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setActive(resultSet.getBoolean("is_active"));
        product.setCreatedAt(resultSet.getTimestamp("created_at"));

        return product;
    }

}