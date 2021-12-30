package org.but.feec.project3.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.but.feec.project3.api.OrderBasicView;
import org.but.feec.project3.api.OrderDetailView;
import org.but.feec.project3.api.PersonAuthView;
import org.but.feec.project3.api.ProductBasicView;
import org.but.feec.project3.controller.LoginController;
import org.but.feec.project3.data.OrderRepository;
import org.but.feec.project3.data.PersonRepository;
import org.but.feec.project3.data.ProductRepository;

import java.lang.reflect.Method;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;
    LoginController login = new LoginController();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDetailView getOrderDetailView(Long id) {
        return orderRepository.findOrderDetailedView(id);
    }



    public List<OrderBasicView> getOrderBasicView() {
        return orderRepository.getOrderBasicView("gnowlan3@virginia.edu");
    }


}
