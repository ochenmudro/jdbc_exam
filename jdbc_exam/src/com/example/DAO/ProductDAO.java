package com.example.DAO;

import com.example.ConnectionFactory;
import com.example.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.example.Constants.*;

public class ProductDAO implements DAO<Product> {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private static Logger log = Logger.getLogger(ProductDAO.class.getName());

    public void getAll() {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(GET_PRODUCT);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price")
                        + ", com.example.entities.Order id" + this.resultSet.getInt("order_id"));
            }
        } catch (SQLException e) {
           log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.resultSet != null) {
                    this.resultSet.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void get(Product product) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(GET_PRODUCT_BY_ID);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price")
                        + ", com.example.entities.Order id" + this.resultSet.getInt("order_id"));
            }
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.resultSet != null) {
                    this.resultSet.close();
                }
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    @Override
    public void getByID(int id) {

    }

    public void add(Product product) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(INSERT_PRODUCT);
            this.preparedStatement.setString(1, product.getName());
            this.preparedStatement.setInt(2, product.getPrice());
            this.preparedStatement.setInt(3, product.getOrderId());
            this.preparedStatement.executeUpdate();
            this.preparedStatement = this.connection.prepareStatement(GET_CLIENT_BUDGET_BY_ORDER);
            this.preparedStatement.setInt(1, product.getOrderId());
            this.resultSet = this.preparedStatement.executeQuery();
            if (this.resultSet.next()) {
                int check = this.resultSet.getInt(1) - product.getPrice();
                if (check < 0) {
                    throw new Exception();
                }
            }
            this.preparedStatement = this.connection.prepareStatement(UPDATE_CLIENT_BUDGET_WHEN_ADDING_PRODUCT);
            this.preparedStatement.setInt(1, product.getPrice());
            this.preparedStatement.setInt(2, product.getOrderId());
            this.preparedStatement.executeUpdate();
            log.info("Data Added Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } catch (Exception e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void update(Product product) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(UPDATE_PRODUCT_ORDER_ID);
            this.preparedStatement.setInt(1, product.getOrderId());
            this.preparedStatement.setString(2, product.getName());
            this.preparedStatement.executeUpdate();
            log.info("Table Updated Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void delete(int id) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(DELETE_PRODUCT);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeUpdate();
            log.info("Data deleted Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }
}