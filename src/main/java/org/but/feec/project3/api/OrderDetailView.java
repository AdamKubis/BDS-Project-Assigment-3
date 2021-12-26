package org.but.feec.project3.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderDetailView {
    private LongProperty id_order = new SimpleLongProperty();
    private StringProperty status_description = new SimpleStringProperty();
    private StringProperty payment_status_type = new SimpleStringProperty();
    private StringProperty payment_method = new SimpleStringProperty();
    private StringProperty total = new SimpleStringProperty();
    /*private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty houseNumber = new SimpleStringProperty();*/

    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long id_order) {
        this.idProperty().setValue(id_order);
    }

    public String getStatusDesription() {
        return statusDesriptionProperty().get();
    }

    public void setStatusDesription(String status_description) {
        this.statusDesriptionProperty().setValue(status_description);
    }

    public String getPaymentStatusType() {
        return paymentStatusTypeProperty().get();
    }

    public void setPaymentStatusType(String payment_status_type) {
        this.paymentStatusTypeProperty().setValue(payment_status_type);
    }

    public String getPaymentMethod() {
        return paymentMethodProperty().get();
    }

    public void setPaymentMethod(String payment_type) {
        this.paymentMethodProperty().setValue(payment_type);
    }

    public String getTotal() {
        return totalProperty().get();
    }

    public void setTotal(String total) {
        this.totalProperty().setValue(total);
    }

    public LongProperty idProperty() {
        return id_order;
    }

    public StringProperty statusDesriptionProperty() {
        return status_description;
    }

    public StringProperty paymentStatusTypeProperty() {
        return payment_status_type;
    }

    public StringProperty paymentMethodProperty() {
        return payment_method;
    }

    public StringProperty totalProperty() {
        return total;
    }


}
