package org.but.feec.project3.api;

public class ProductEditView {
    private Long id;
    private String id_product_type;
    private String product_brand_name;
    private String product_model;
    private String product_version;
    private String product_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_type() {
        return id_product_type;
    }

    public void setProduct_type(String id_product_type) {
        this.id_product_type = id_product_type;
    }

    public String getBrand_name() {
        return product_brand_name;
    }

    public void setBrand_name(String brandName) {
        this.product_brand_name = product_brand_name;
    }

    public String getModel() {
        return product_model;
    }

    public void setModel(String model) {
        this.product_model = product_model;
    }

    public String getVersion() {
        return product_version;
    }

    public void setVersion(String version) {
        this.product_version = product_version;
    }

    public String getPrice() {
        return product_price;
    }

    public void setPrice(String price) {
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return "ProductEditView{" +
                "id_product_type='" + id_product_type + '\'' +
                ", product_brand_name='" + product_brand_name + '\'' +
                ", product_model='" + product_model + '\'' +
                ", product_version='" + product_version + '\'' +
                ", product_price='" + product_price + '\'' +
                '}';
    }
}
