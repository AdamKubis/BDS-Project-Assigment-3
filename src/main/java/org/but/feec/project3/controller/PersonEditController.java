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
import org.but.feec.project3.api.PersonEditView;
import org.but.feec.project3.data.PersonRepository;
import org.but.feec.project3.data.ProductRepository;
import org.but.feec.project3.service.ProductService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PersonEditController {
    private static final Logger logger = LoggerFactory.getLogger(PersonEditController.class);

    @FXML
    public Button editPersonButton;
    @FXML
    public TextField idTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField nicknameTextField;

    private ProductService productService;
    private PersonRepository personRepository;
    private ProductRepository productRepository;
    private ValidationSupport validation;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        personRepository = new PersonRepository();
        productService = new ProductService(productRepository);

        validation = new ValidationSupport();
        validation.registerValidator(idTextField, Validator.createEmptyValidator("The id must not be empty."));
        idTextField.setEditable(false);
        validation.registerValidator(brandTextField, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(modelTextField, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(versionTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(priceTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validation.registerValidator(nicknameTextField, Validator.createEmptyValidator("The nickname must not be empty."));

        editPersonButton.disableProperty().bind(validation.invalidProperty());

        loadProductData();

        logger.info("PersonsEditController initialized");
    }

    /**
     * Load passed data from Persons controller. Check this tutorial explaining how to pass the data between controllers: https://dev.to/devtony101/javafx-3-ways-of-passing-information-between-scenes-1bm8
     */
    private void loadProductData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof ProductBasicView) {
            ProductBasicView productBasicView = (ProductBasicView) stage.getUserData();
            idTextField.setText(String.valueOf(productBasicView.getId()));
            typeTextField.setText(productBasicView.getProduct_type());
            brandTextField.setText(productBasicView.getBrand_name());
            modelTextField.setText(productBasicView.getModel());
            versionTextField.setText(productBasicView.getVersion());
            priceTextField.setText(productBasicView.getPrice());
        }
    }

    @FXML
    public void handleEditPersonButton(ActionEvent event) {
        // can be written easier, its just for better explanation here on so many lines
        Long id = Long.valueOf(idTextField.getText());
        String brand_name = brandTextField.getText();
        String email = modelTextField.getText();
        String firstName = versionTextField.getText();
        String lastName = priceTextField.getText();
        String nickname = nicknameTextField.getText();

        PersonEditView personEditView = new PersonEditView();
        personEditView.setId(id);
        personEditView.setEmail(brand_name);
        personEditView.setEmail(email);
        personEditView.setFirstName(firstName);
        personEditView.setSurname(lastName);
        personEditView.setNickname(nickname);

        //productService.editPerson(personEditView);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Edited Confirmation");
        alert.setHeaderText("Your person was successfully edited.");

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
