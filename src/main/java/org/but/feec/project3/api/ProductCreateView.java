package org.but.feec.project3.api;

import java.util.Arrays;

public class ProductCreateView {

    private String id_product_type;
    private String brandName;
    private String model;
    private String version;
    //private String product_color;
    private String price;
    private String in_stock;

    /*public String getId_product_type() {
        return id_product_type;
    }

    public void setId_product_type(String id_product_type) {
        this.id_product_type = id_product_type;
    } */

    public String getBrand_name() {
        return brandName;
    }

    public void setBrand_name(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /*public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }*/

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(String in_stock) {
        this.in_stock = in_stock;
    }

    @Override
    public String toString() {
        return "ProductCreateView{" +
               // "id_product_type='" + id_product_type + '\'' +
                "product_brand_name='" + brandName + '\'' +
                ", product_model='" + model + '\'' +
                ", product_version='" + version + '\'' +
              //  ", product_color='" + product_color + '\'' +
                ", product_price='" + price + '\'' +
                ", in_stock='" + in_stock + '\'' +
                '}';
    }

}
