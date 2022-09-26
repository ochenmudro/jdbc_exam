package com.example;

import com.example.DAO.ClientDAO;
import com.example.DAO.OrderDAO;
import com.example.DAO.ProductDAO;
import com.example.entities.Product;

import java.sql.SQLException;

public class JDBCExample {
    public JDBCExample() {
    }

    public static void main(String[] args) throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        Product product =new Product(5,1,"lipstick");
//        orderDAO.getOrders(1);
//        clientDAO.spending(1);
//        orderDAO.getExpensive(1);
//
//        Order order = new Order(1, "complect");
//        Order order1 = new Order(1, "makeup");
//        orderDAO.update(order);
//        orderDAO.add(order1);
//        orderDAO.deleteByClient(1, "makeup");
        productDAO.getAll();
        clientDAO.addProduct(product);
        clientDAO.getByID(1);
    }
}
