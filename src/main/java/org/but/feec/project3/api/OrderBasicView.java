package org.but.feec.project3.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderBasicView {
    private LongProperty id = new SimpleLongProperty();
    //private StringProperty product_type = new SimpleStringProperty();
    //private StringProperty brand_name = new SimpleStringProperty();
    //private StringProperty model = new SimpleStringProperty();
    //private StringProperty version = new SimpleStringProperty();
    //private StringProperty price = new SimpleStringProperty();
    //private StringProperty nickname = new SimpleStringProperty();

    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long id) {
        this.idProperty().setValue(id);
    }

    /*public String getProduct_type() {
        return product_typeProperty().get();
    }

    public void setProduct_type(String product_type) {
        this.product_typeProperty().setValue(product_type);
    }

    public String getBrand_name() {
        return brand_nameProperty().get();
    }

    public void setBrand_name(String brand_name) {
        this.brand_nameProperty().setValue(brand_name);
    }

    public String getModel() {
        return modelProperty().get();
    }

    public void setModel(String model) {
        this.modelProperty().setValue(model);
    }

    public String getVersion() {
        return versionProperty().get();
    }

    public void setVersion(String version) {
        this.versionProperty().setValue(version);
    }

    public String getPrice() {
        return priceProperty().get();
    }

    public void setPrice(String price) {
        this.priceProperty().setValue(price);
    }

    /* public String getNickname() {
         return nicknameProperty().get();
     }

     public void setNickname(String nickname) {
         this.nicknameProperty().set(nickname);
     }
 */
    public LongProperty idProperty() {
        return id;
    }

    /*public StringProperty product_typeProperty() {
        return product_type;
    }

    public StringProperty brand_nameProperty() {
        return brand_name;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public StringProperty versionProperty() {
        return version;
    }

    public StringProperty priceProperty() {
        return price;
    }

    /*public StringProperty nicknameProperty() {
        return nickname;
    }*/

}
