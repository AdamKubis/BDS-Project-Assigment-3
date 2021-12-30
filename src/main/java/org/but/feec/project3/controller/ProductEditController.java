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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.api.ProductEditView;
import org.but.feec.project3.data.PersonRepository;
import org.but.feec.project3.data.ProductRepository;
import org.but.feec.project3.service.ProductService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProductEditController {
    private static final Logger logger = LoggerFactory.getLogger(ProductEditController.class);

    @FXML
    public Button editProductButton;
    @FXML
    public TextField idTextField;
    @FXML
    private TextField productTypeTextField;
    @FXML
    private TextField brandNameTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField nicknameTextField;

    private ProductService productService;
    private ProductRepository productRepository;
    private ValidationSupport validation;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);

        validation = new ValidationSupport();
        validation.registerValidator(idTextField, Validator.createEmptyValidator("The id must not be empty."));
        idTextField.setEditable(false);
        validation.registerValidator(productTypeTextField, Validator.createEmptyValidator("The product type must not be empty."));
        productTypeTextField.setEditable(true);
        validation.registerValidator(brandNameTextField, Validator.createEmptyValidator("The brand name must not be empty."));
        brandNameTextField.setEditable(true);
        validation.registerValidator(modelTextField, Validator.createEmptyValidator("The model must not be empty."));
        modelTextField.setEditable(true);
        validation.registerValidator(versionTextField, Validator.createEmptyValidator("The version must not be empty."));
        versionTextField.setEditable(true);
        validation.registerValidator(priceTextField, Validator.createEmptyValidator("The price must not be empty."));
        priceTextField.setEditable(true);

        editProductButton.disableProperty().bind(validation.invalidProperty());

        loadProductData();

        logger.info("ProductEditController initialized");
    }

    /**
     * Load passed data from Persons controller. Check this tutorial explaining how to pass the data between controllers: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
     */
    private void loadProductData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof ProductBasicView) {
            ProductBasicView productBasicView = (ProductBasicView) stage.getUserData();
            idTextField.setText(String.valueOf(productBasicView.getId()));
            productTypeTextField.setText(productBasicView.getProduct_type());
            brandNameTextField.setText(productBasicView.getBrand_name());
            modelTextField.setText(productBasicView.getModel());
            versionTextField.setText(productBasicView.getVersion());
            priceTextField.setText(productBasicView.getPrice());
        }
    }

    @FXML
    public void handleEditProductButton(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        Long id_product = Long.valueOf(idTextField.getText());
        String product_type = productTypeTextField.getText();
        String product_brand_name = brandNameTextField.getText();
        String product_model = modelTextField.getText();
        String product_version = versionTextField.getText();
        String product_price = priceTextField.getText();

        ProductEditView productEditView = new ProductEditView();
        productEditView.setId(id_product);
        productEditView.setProduct_type(product_type);
        productEditView.setBrand_name(product_brand_name);
        productEditView.setModel(product_model);
        productEditView.setVersion(product_version);
        productEditView.setPrice(product_price);

        productService.editProduct(productEditView);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Product Edit Confirmation");
        alert.setHeaderText("Product was successfully edited.");

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
