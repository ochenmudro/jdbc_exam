package com.example.DAO;

import com.example.ConnectionFactory;
import com.example.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.example.Constants.*;

public class OrderDAO implements DAO<Order> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private static Logger log = Logger.getLogger(ProductDAO.class.getName());

    public void getAll() {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ORDER);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", com.example.entities.Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
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

    @Override
    public void getByID(int id) {

    }

    public void get(Order order) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ORDER_BY_ID);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", com.example.entities.Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.resultSet != null) {
                    this.resultSet.close();
                }
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void add(Order order) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(INSERT_ORDER);
            this.ptmt.setString(1, order.getName());
            this.ptmt.setInt(2, order.getClientId());
            this.ptmt.executeUpdate();
            log.info("Data Added Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void update(Order order) {
        try {
            this.connection =ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(UPDATE_ORDER_CLIENT_ID);
            this.ptmt.setInt(1, order.getClientId());
            this.ptmt.setString(2, order.getName());
            this.ptmt.executeUpdate();
            log.info("Table Updated Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
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
            this.connection =ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(DELETE_ORDER);
            this.ptmt.setInt(1, id);
            this.ptmt.executeUpdate();
            log.info("Data deleted Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void getOrders(int clientId) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ORDER_BY_CLIENT_ID);
            this.ptmt.setInt(1, clientId);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", com.example.entities.Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        }
    }

    public void getExpensive(int clientId) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(GET_EXPENSIVE_ORDER);
            this.resultSet = this.ptmt.executeQuery();
            if (this.resultSet.next()) {
                log.info("Most expensive order: ID " + this.resultSet.getInt("order_id") +
                        " total price " + this.resultSet.getInt("final_price"));
            }
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s",e.getMessage()));
            }
        }
    }

    public void deleteByClient(int clientId, String orderName) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.ptmt = this.connection.prepareStatement(DELETE_PRODUCT_BY_ORDER_ID);
            this.ptmt.setString(1, orderName);
            this.ptmt.executeUpdate();
            this.ptmt.close();
            this.ptmt = this.connection.prepareStatement(DELETE_ORDER_BY_CLIENT_ID_AND_NAME);
            this.ptmt.setInt(1, clientId);
            this.ptmt.setString(2, orderName);
            this.ptmt.executeUpdate();
            log.info("Data deleted Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s",e.getMessage()));
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
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
