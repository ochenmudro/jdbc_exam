package com.example;

public interface Constants {
    String INSERT_CLIENT = "INSERT INTO client (name, budget) VALUES (?,?)";
    String GET_CLIENT = "SELECT * FROM client";
    String UPDATE_CLIENT_BUDGET = "UPDATE client SET budget=? WHERE name=?";
    String DELETE_CLIENT = "DELETE FROM client WHERE id=?";
    String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE id=?";
    String GET_CLIENT_NAME = "SELECT name FROM client WHERE id=?";
    String GET_CLIENT_SPENT = "SELECT price FROM product AS p JOIN \"order\" AS o ON p.order_id=o.id AND o.client_id=?";
    String INSERT_ORDER = "INSERT INTO \"order\" (name, client_id) VALUES (?,?)";
    String GET_ORDER = "SELECT * FROM \"order\"";
    String UPDATE_ORDER_CLIENT_ID = "UPDATE \"order\" SET client_id=? WHERE name=?";
    String DELETE_ORDER = "DELETE FROM \"order\" WHERE id=?";
    String GET_ORDER_BY_ID = "SELECT * FROM \"order\" WHERE id=?";
    String GET_ORDER_BY_CLIENT_ID = "SELECT * FROM \"order\" WHERE client_id=?";
    String GET_EXPENSIVE_ORDER = "SELECT order_id, SUM(price) AS final_price FROM product GROUP BY order_id ORDER BY final_price DESC ";
    String DELETE_PRODUCT_BY_ORDER_ID = "DELETE FROM product WHERE order_id = (SELECT id FROM \"order\" WHERE name=?)";
    String DELETE_ORDER_BY_CLIENT_ID_AND_NAME = "DELETE FROM \"order\" WHERE client_id=? AND name=?";
    String INSERT_PRODUCT = "INSERT INTO product (name, price, order_id) VALUES (?,?,?)";
    String GET_PRODUCT = "SELECT * FROM product";
    String UPDATE_PRODUCT_ORDER_ID = "UPDATE product SET order_id=? WHERE name=?";
    String DELETE_PRODUCT = "DELETE FROM product WHERE id=?";
    String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    String UPDATE_CLIENT_BUDGET_WHEN_ADDING_PRODUCT = "UPDATE client SET budget=(budget-?) FROM \"order\" WHERE \"order\".id=? AND \"order\".client_id=client.id";
    String GET_CLIENT_BUDGET_BY_ORDER = "SELECT budget FROM client JOIN \"order\" ON client.id=\"order\".client_id AND \"order\".id=?";
}
