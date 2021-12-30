package org.but.feec.project3.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.but.feec.project3.Main;
import org.but.feec.project3.api.ProductCreateView;
import org.but.feec.project3.data.ProductRepository;
import org.but.feec.project3.service.ProductService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProductCreateController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCreateController.class);

    @FXML
    public Button newProductCreateProduct;
    @FXML
    private TextField newProductIdType;

    @FXML
    private TextField newProductBrandName;

    @FXML
    private TextField newProductModel;

    @FXML
    private TextField newProductVersion;

    @FXML
    private TextField newProductColor;

    @FXML
    private TextField newProductPrice;

    @FXML
    private TextField newProductInStock;

    private ProductService productService;
    private ProductRepository productRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);

        validation = new ValidationSupport();
        //validation.registerValidator(newProductIdType, Validator.createEmptyValidator("Must not be empty."));
        validation.registerValidator(newProductBrandName, Validator.createEmptyValidator("Must not be empty."));
        validation.registerValidator(newProductModel, Validator.createEmptyValidator("Must not be empty."));
        validation.registerValidator(newProductVersion, Validator.createEmptyValidator("Must not be empty."));
        validation.registerValidator(newProductPrice, Validator.createEmptyValidator("Must not be empty."));

        //newProductCreateProduct.disableProperty().bind(validation.invalidProperty());

        logger.info("ProductCreateController initialized");
    }

    @FXML
    void handleCreateNewProduct(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        //String id_product_type = newProductIdType.getText();
        String product_brand_name = newProductBrandName.getText();
        String product_model = newProductModel.getText();
        String product_version = newProductVersion.getText();
        //String product_color = newProductColor.getText();
        String product_price = newProductPrice.getText();
        String in_stock = newProductInStock.getText();

        ProductCreateView productCreateView = new ProductCreateView();
        //productCreateView.setId_product_type(id_product_type);
        productCreateView.setBrand_name(product_brand_name);
        productCreateView.setModel(product_model);
        productCreateView.setVersion(product_version);
        //productCreateView.setProduct_color(product_color);
        productCreateView.setPrice(product_price);
        productCreateView.setIn_stock(in_stock);

        productService.createProduct(productCreateView);

        productCreatedConfirmationDialog();
    }

    private void productCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product Creation Confirmation");
        alert.setHeaderText("Product was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }

}
