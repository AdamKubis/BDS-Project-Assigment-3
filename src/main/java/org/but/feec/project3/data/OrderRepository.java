package org.but.feec.project3.data;

import org.but.feec.project3.api.OrderBasicView;
import org.but.feec.project3.api.OrderDetailView;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.config.DataSourceConfig;
import org.but.feec.project3.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public List<OrderBasicView> getOrderBasicView(String email) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT uo.id_user, p.id_user, uo.id_order, o.id_order, email" +
                             " FROM bpc_bds_project.users_has_order uo" +
                             " LEFT JOIN bpc_bds_project.persons p ON uo.id_user = p.id_user" +
                             " LEFT JOIN bpc_bds_project.order o ON uo.id_order = o.id_order" +
                             " WHERE email = ?")
        ) {
             preparedStatement.setString(1, email);
             ResultSet resultSet = preparedStatement.executeQuery();
             List<OrderBasicView> orderBasicViews = new ArrayList<>();
             while (resultSet.next()) {
                 orderBasicViews.add(mapToOrderBasicView(resultSet));
             }
             return orderBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Order basic view could not be loaded.", e);
        }
    }

    private OrderBasicView mapToOrderBasicView(ResultSet rs) throws SQLException {
        OrderBasicView orderBasicView = new OrderBasicView();
        orderBasicView.setId(rs.getLong("id_order"));
        return orderBasicView;
    }

    public OrderDetailView findOrderDetailedView(Long orderId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_order, order_status_code, o.id_payment, p.id_payment, status_code, payment_method, status_description, total, pt.payment_type, p.payment_type,id_payment_status, payment_status_type" +
                             " FROM bpc_bds_project.order o" +
                             " LEFT JOIN bpc_bds_project.order_status os ON o.order_status_code = os.status_code" +
                             " LEFT JOIN bpc_bds_project.payment p ON o.id_payment = p.id_payment" +
                             " LEFT JOIN bpc_bds_project.payment_status ps ON p.id_payment_status = ps.payment_status" +
                             " LEFT JOIN bpc_bds_project.payment_type pt ON p.payment_type = pt.payment_type" +
                             " WHERE id_order = ?")
        ) {
            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToOrderDetailView(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find order by ID failed.", e);
        }
        return null;
    }

    private OrderDetailView mapToOrderDetailView(ResultSet rs) throws SQLException {
        OrderDetailView orderDetailView = new OrderDetailView();
        orderDetailView.setId(rs.getLong("id_order"));
        orderDetailView.setStatusDesription(rs.getString("status_description"));
        orderDetailView.setPaymentStatusType(rs.getString("payment_status_type"));
        orderDetailView.setPaymentMethod(rs.getString("payment_method"));
        orderDetailView.setTotal(rs.getString("total"));
        return orderDetailView;
    }
}
