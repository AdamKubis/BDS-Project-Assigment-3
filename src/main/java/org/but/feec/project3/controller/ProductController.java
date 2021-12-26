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
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.data.ProductRepository;
import org.but.feec.project3.exceptions.ExceptionHandler;
import org.but.feec.project3.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @FXML
    public Button addPersonButton;
    @FXML
    public Button refreshButton;
    @FXML
    private TableColumn<ProductBasicView, Long> productId;
    @FXML
    private TableColumn<ProductBasicView, String> productType;
    @FXML
    private TableColumn<ProductBasicView, String> productBN;
    @FXML
    private TableColumn<ProductBasicView, String> productModel;
    @FXML
    private TableColumn<ProductBasicView, String> productVersion;
    @FXML
    private TableColumn<ProductBasicView, String> productPrice;
   // @FXML
   // private TableColumn<ProductBasicView, String> personsNickname;
    @FXML
    private TableView<ProductBasicView> systemProductTableView;
//    @FXML
//    public MenuItem exitMenuItem;

    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController() {
    }

    @FXML
    private void initialize() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);
//        GlyphsDude.setIcon(exitMenuItem, FontAwesomeIcon.CLOSE, "1em");

        productId.setCellValueFactory(new PropertyValueFactory<ProductBasicView, Long>("id"));
        productType.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("product_type"));
        productBN.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("brand_name"));
        productModel.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("model"));
        productVersion.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("version"));
        productPrice.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("price"));
        //personsNickname.setCellValueFactory(new PropertyValueFactory<ProductBasicView, String>("nickname"));


        ObservableList<ProductBasicView> observableProductList = initializeProductData();
        systemProductTableView.setItems(observableProductList);

        systemProductTableView.getSortOrder().add(productId);

        initializeTableViewSelection();
        //loadIcons();

        logger.info("ProductController initialized");
    }

    private void initializeTableViewSelection() {
        MenuItem edit = new MenuItem("Edit person");
        MenuItem detailedView = new MenuItem("Detailed person view");
        edit.setOnAction((ActionEvent event) -> {
            ProductBasicView productView = systemProductTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("PersonEdit.fxml"));
                Stage stage = new Stage();
                stage.setUserData(productView);
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
        });

        detailedView.setOnAction((ActionEvent event) -> {
            ProductBasicView personView = systemProductTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("PersonsDetailView.fxml"));
                Stage stage = new Stage();

                Long personId = personView.getId();
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
        menu.getItems().add(edit);
        menu.getItems().addAll(detailedView);
        systemProductTableView.setContextMenu(menu);
    }

    private ObservableList<ProductBasicView> initializeProductData() {
        List<ProductBasicView> persons = productService.getProductBasicView();
        return FXCollections.observableArrayList(persons);
    }

    /*private void loadIcons() {
        Image vutLogoImage = new Image(App.class.getResourceAsStream("logos/vut-logo-eng.png"));
        ImageView vutLogo = new ImageView(vutLogoImage);
        vutLogo.setFitWidth(150);
        vutLogo.setFitHeight(50);
    }*/

    public void handleExitMenuItem(ActionEvent event) {
        System.exit(0);
    }

    public void handleAddPersonButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/PersonsCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("BDS JavaFX Create Person");
            stage.setScene(scene);

//            Stage stageOld = (Stage) signInButton.getScene().getWindow();
//            stageOld.close();
//
//            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/vut.jpg")));
//            authConfirmDialog();

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) {
        ObservableList<ProductBasicView> observablePersonsList = initializeProductData();
        systemProductTableView.setItems(observablePersonsList);
        systemProductTableView.refresh();
        systemProductTableView.sort();
    }
}
