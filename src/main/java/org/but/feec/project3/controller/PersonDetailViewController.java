package org.but.feec.project3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.project3.api.OrderDetailView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDetailViewController {
    private static final Logger logger = LoggerFactory.getLogger(PersonDetailViewController.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField status_descriptionTextField;

    @FXML
    private TextField payment_status_typeTextField;

    @FXML
    private TextField payment_methodTextField;

    @FXML
    private TextField totalTextField;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        idTextField.setEditable(false);
        status_descriptionTextField.setEditable(false);
        payment_status_typeTextField.setEditable(false);
        payment_methodTextField.setEditable(false);
        totalTextField.setEditable(false);

        loadOrdersData();

        logger.info("OrderDetailViewController initialized");
    }

    private void loadOrdersData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof OrderDetailView) {
            OrderDetailView orderBasicView = (OrderDetailView) stage.getUserData();
            idTextField.setText(String.valueOf(orderBasicView.getId()));
            status_descriptionTextField.setText(orderBasicView.getStatusDesription());
            payment_status_typeTextField.setText(orderBasicView.getPaymentStatusType());
            payment_methodTextField.setText(orderBasicView.getPaymentMethod());
            totalTextField.setText(orderBasicView.getTotal());
        }
    }


}
