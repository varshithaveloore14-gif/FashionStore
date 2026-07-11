package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER_SQL = """
            INSERT INTO orders
            (user_id, total_amount, payment_method, order_status,
             delivery_name, delivery_phone,
             delivery_address_line1, delivery_address_line2,
             delivery_city, delivery_state,
             delivery_pin_code, delivery_country)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_ORDER_BY_ID_SQL = """
            SELECT *
            FROM orders
            WHERE order_id = ?
            """;

    private static final String GET_ORDERS_BY_USER_ID_SQL = """
            SELECT *
            FROM orders
            WHERE user_id = ?
            ORDER BY order_date DESC
            """;

    private static final String GET_ALL_ORDERS_SQL = """
            SELECT *
            FROM orders
            ORDER BY order_date DESC
            """;

    private static final String UPDATE_ORDER_STATUS_SQL = """
            UPDATE orders
            SET order_status = ?
            WHERE order_id = ?
            """;

    private static final String DELETE_ORDER_SQL = """
            DELETE FROM orders
            WHERE order_id = ?
            """;
    @Override
    public int placeOrder(Order order) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            INSERT_ORDER_SQL,
                            PreparedStatement.RETURN_GENERATED_KEYS)
        ) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setBigDecimal(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getPaymentMethod());
            preparedStatement.setString(4, order.getOrderStatus());
            preparedStatement.setString(5, order.getDeliveryName());
            preparedStatement.setString(6, order.getDeliveryPhone());
            preparedStatement.setString(7, order.getDeliveryAddressLine1());
            preparedStatement.setString(8, order.getDeliveryAddressLine2());
            preparedStatement.setString(9, order.getDeliveryCity());
            preparedStatement.setString(10, order.getDeliveryState());
            preparedStatement.setString(11, order.getDeliveryPinCode());
            preparedStatement.setString(12, order.getDeliveryCountry());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {

                    return rs.getInt(1);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }


    @Override
    public Order getOrderById(int orderId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ORDER_BY_ID_SQL)
        ) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return mapResultSetToOrder(resultSet);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ORDERS_BY_USER_ID_SQL)
        ) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    orders.add(mapResultSetToOrder(resultSet));

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }


    @Override
    public List<Order> getAllOrders() {

        List<Order> orders = new ArrayList<>();

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_ALL_ORDERS_SQL);

            ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                orders.add(mapResultSetToOrder(resultSet));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }


    @Override
    public boolean updateOrderStatus(int orderId, String orderStatus) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_ORDER_STATUS_SQL)
        ) {

            preparedStatement.setString(1, orderStatus);
            preparedStatement.setInt(2, orderId);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean deleteOrder(int orderId) {

        try (
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(DELETE_ORDER_SQL)
        ) {

            preparedStatement.setInt(1, orderId);

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private Order mapResultSetToOrder(ResultSet resultSet) throws Exception {

        Order order = new Order();

        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setOrderDate(resultSet.getTimestamp("order_date"));
        order.setTotalAmount(resultSet.getBigDecimal("total_amount"));
        order.setPaymentMethod(resultSet.getString("payment_method"));
        order.setOrderStatus(resultSet.getString("order_status"));
        order.setDeliveryName(resultSet.getString("delivery_name"));
        order.setDeliveryPhone(resultSet.getString("delivery_phone"));
        order.setDeliveryAddressLine1(resultSet.getString("delivery_address_line1"));
        order.setDeliveryAddressLine2(resultSet.getString("delivery_address_line2"));
        order.setDeliveryCity(resultSet.getString("delivery_city"));
        order.setDeliveryState(resultSet.getString("delivery_state"));
        order.setDeliveryPinCode(resultSet.getString("delivery_pin_code"));
        order.setDeliveryCountry(resultSet.getString("delivery_country"));

        return order;
    }

}