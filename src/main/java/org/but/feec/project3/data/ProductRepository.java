package org.but.feec.project3.data;

import org.but.feec.project3.api.PersonAuthView;
import org.but.feec.project3.api.ProductCreateView;
import org.but.feec.project3.api.ProductEditView;
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

    public void editProduct(ProductEditView productEditView) {
        String insertProductSQL = "UPDATE bpc_bds_project.product p " +
                "SET id_product_type = ?, product_brand_name = ?, product_model = ?, product_version = ?, product_price = ? " +
                "WHERE p.id_product = ?";
        String checkIfExists = "SELECT id_product FROM bpc_bds_project.product WHERE id_product = ?";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setString(1, productEditView.getProduct_type());
            preparedStatement.setString(2, productEditView.getBrand_name());
            preparedStatement.setString(3, productEditView.getModel());
            preparedStatement.setString(4, productEditView.getVersion());
            preparedStatement.setString(5, productEditView.getPrice());
            preparedStatement.setLong(6, productEditView.getId());

            try {
                // TODO set connection autocommit to false
                /* HERE */
                try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, productEditView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This person for edit do not exists.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating person failed, no rows affected.");
                }
                // TODO commit the transaction (both queries were performed)
                /* HERE */
            } catch (SQLException e) {
                // TODO rollback the transaction if something wrong occurs
                /* HERE */
            } finally {
                // TODO set connection autocommit back to true
                /* HERE */
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }

    public void createProduct(ProductCreateView productCreateView) {
        String insertPersonSQL = "INSERT INTO bpc_bds_project.product (product_brand_name, product_model, product_version, product_price, in_stock) VALUES (?,?,?,?,?)";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
           // preparedStatement.setString(1, productCreateView.getId_product_type());
            preparedStatement.setString(1, productCreateView.getBrand_name());
            preparedStatement.setString(2, productCreateView.getModel());
            preparedStatement.setString(3, productCreateView.getVersion());
            //preparedStatement.setString(5, productCreateView.getProduct_color());
            preparedStatement.setString(4, productCreateView.getPrice());
            preparedStatement.setString(5, productCreateView.getIn_stock());


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new DataAccessException("Creating person failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }


    public ProductBasicView mapToProductBasicView(ResultSet rs) throws SQLException {
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
