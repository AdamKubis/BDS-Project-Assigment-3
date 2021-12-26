package org.but.feec.project3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.but.feec.project3.App;
import org.but.feec.project3.api.OrderBasicView;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.data.OrderRepository;
import org.but.feec.project3.data.ProductRepository;
import org.but.feec.project3.exceptions.ExceptionHandler;
import org.but.feec.project3.service.OrderService;
import org.but.feec.project3.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @FXML
    private TableColumn<OrderBasicView, Long> orderId;
   /* @FXML
    private TableColumn<OrderBasicView, String> productType;
    @FXML
    private TableColumn<ProductBasicView, String> productBN;
    @FXML
    private TableColumn<ProductBasicView, String> productModel;
    @FXML
    private TableColumn<ProductBasicView, String> productVersion;
    @FXML
    private TableColumn<ProductBasicView, String> productPrice;
    // @FXML
    // private TableColumn<ProductBasicView, String> personsNickname; */
    @FXML
    private TableView<OrderBasicView> systemOrderTableView;
//    @FXML
//    public MenuItem exitMenuItem;

    private OrderService orderService;
    private OrderRepository orderRepository;

    public OrderController() {
    }

    @FXML
    private void initialize() throws NoSuchMethodException {
        orderRepository = new OrderRepository();
        orderService = new OrderService(orderRepository);
//        GlyphsDude.setIcon(exitMenuItem, FontAwesomeIcon.CLOSE, "1em");

        orderId.setCellValueFactory(new PropertyValueFactory<OrderBasicView, Long>("id"));
        /*productType.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("product_type"));
        productBN.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("brand_name"));
        productModel.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("model"));
        productVersion.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("version"));
        productPrice.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("price"));
        //personsNickname.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("nickname"));
        */

        ObservableList<OrderBasicView> observableOrderList = initializeOrderData();
        systemOrderTableView.setItems(observableOrderList);

        systemOrderTableView.getSortOrder().add(orderId);

        initializeTableViewSelection();
        //loadIcons();

        logger.info("ProductController initialized");
    }

    private void initializeTableViewSelection() {
        //MenuItem edit = new MenuItem("Edit person");
        MenuItem detailedView = new MenuItem("Detailed order view");
        /*edit.setOnAction((ActionEvent event) -> {
            OrderBasicView orderView = systemOrderTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("PersonEdit.fxml"));
                Stage stage = new Stage();
                stage.setUserData(orderView);
                stage.setTitle("BDS JavaFX Edit Person");

                PersonEditController controller = new PersonEditController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
        });*/

        detailedView.setOnAction((ActionEvent event) -> {
            OrderBasicView orderView = systemOrderTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("OrderDetailView.fxml"));
                Stage stage = new Stage();

                Long orderId = orderView.getId();
                //OrderDetailView personDetailView = productService.getPersonDetailView(personId);

                //stage.setUserData(personDetailView);
                stage.setTitle("BDS JavaFX Persons Detailed View");

                PersonDetailViewController controller = new PersonDetailViewController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
        });


        ContextMenu menu = new ContextMenu();
        //menu.getItems().add(edit);
        menu.getItems().addAll(detailedView);
        systemOrderTableView.setContextMenu(menu);
    }

    private ObservableList<OrderBasicView> initializeOrderData() {
        List<OrderBasicView> orders = orderService.getOrderBasicView();
        return FXCollections.observableArrayList(orders);
    }

    /*private void loadIcons() {
        Image vutLogoImage = new Image(App.class.getResourceAsStream("logos/vut-logo-eng.png"));
        ImageView vutLogo = new ImageView(vutLogoImage);
        vutLogo.setFitWidth(150);
        vutLogo.setFitHeight(50);
    }*/
}
