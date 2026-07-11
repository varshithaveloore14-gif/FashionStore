package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private static final String CREATE_CART_SQL = """
            INSERT INTO cart(user_id)
            VALUES (?)
            """;

    private static final String GET_CART_BY_ID_SQL = """
            SELECT * FROM cart
            WHERE cart_id = ?
            """;

    private static final String GET_CART_BY_USER_ID_SQL = """
            SELECT * FROM cart
            WHERE user_id = ?
            """;

    private static final String DELETE_CART_SQL = """
            DELETE FROM cart
            WHERE cart_id = ?
            """;

    private static final String CART_EXISTS_SQL = """
            SELECT 1 FROM cart
            WHERE user_id = ?
            """;


    @Override
    public boolean createCart(Cart cart) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(CREATE_CART_SQL)
        ) {

            preparedStatement.setInt(1, cart.getUserId());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public Cart getCartById(int cartId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_CART_BY_ID_SQL)
        ) {

            preparedStatement.setInt(1, cartId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToCart(resultSet);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Cart getCartByUserId(int userId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_CART_BY_USER_ID_SQL)
        ) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return mapResultSetToCart(resultSet);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Cart getOrCreateCartByUserId(int userId) {

        Cart cart = getCartByUserId(userId);

        if (cart != null) {
            return cart;
        }

        Cart newCart = new Cart();
        newCart.setUserId(userId);

        createCart(newCart);

        return getCartByUserId(userId);
    }


    @Override
    public boolean deleteCart(int cartId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_CART_SQL)
        ) {

            preparedStatement.setInt(1, cartId);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean cartExistsByUserId(int userId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(CART_EXISTS_SQL)
        ) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private Cart mapResultSetToCart(ResultSet resultSet) throws Exception {

        Cart cart = new Cart();

        cart.setCartId(resultSet.getInt("cart_id"));
        cart.setUserId(resultSet.getInt("user_id"));
        cart.setCreatedAt(resultSet.getTimestamp("created_at"));

        return cart;
    }

}