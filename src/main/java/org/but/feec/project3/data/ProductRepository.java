package org.but.feec.project3.data;

import org.but.feec.project3.api.OrderBasicView;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.config.DataSourceConfig;
import org.but.feec.project3.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<ProductBasicView> getProductBasicView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT id_product, id_product_type, product_type_description, product_type, product_brand_name, product_model, product_version, product_price" +
                             " FROM bpc_bds_project.product p" +
                             " LEFT JOIN bpc_bds_project.product_type a ON p.id_product_type = a.product_type");
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<ProductBasicView> productBasicViews = new ArrayList<>();
            while (resultSet.next()) {
                productBasicViews.add(mapToProductBasicView(resultSet));
            }
            return productBasicViews;
        } catch (SQLException e) {
            throw new DataAccessException("Product basic view could not be loaded.", e);
        }
    }

    private ProductBasicView mapToProductBasicView(ResultSet rs) throws SQLException {
        ProductBasicView productBasicView = new ProductBasicView();
        productBasicView.setId(rs.getLong("id_product"));
        productBasicView.setProduct_type(rs.getString("product_type_description"));
        productBasicView.setBrand_name(rs.getString("product_brand_name"));
        productBasicView.setModel(rs.getString("product_model"));
        productBasicView.setVersion(rs.getString("product_version"));
        productBasicView.setPrice(rs.getString("product_price"));
        return productBasicView;
    }
}
