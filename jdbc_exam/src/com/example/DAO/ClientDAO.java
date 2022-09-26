package com.example.DAO;

import com.example.ConnectionFactory;
import com.example.Constants;
import com.example.entities.Client;
import com.example.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.example.Constants.*;

public class ClientDAO implements DAO<Client> {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private static Logger log = Logger.getLogger(ClientDAO.class.getName());

    public void getAll() {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(Constants.GET_CLIENT);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name")
                        + ", Budget " + this.resultSet.getInt("budget"));
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
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(Constants.GET_CLIENT_BY_ID);
            preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Budget " + this.resultSet.getInt("budget"));
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

    public void get(Client client) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(Constants.GET_CLIENT_BY_ID);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Budget " + this.resultSet.getInt("budget"));
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

    public void add(Client client) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(INSERT_CLIENT);
            this.preparedStatement.setString(1, client.getName());
            this.preparedStatement.setInt(2, client.getBudget());
            this.preparedStatement.executeUpdate();
            log.info("Data Added Successfully");
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

    public void update(Client client) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(Constants.UPDATE_CLIENT_BUDGET);
            this.preparedStatement.setInt(1, client.getBudget());
            this.preparedStatement.setString(2, client.getName());
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
            this.preparedStatement = this.connection.prepareStatement(Constants.DELETE_CLIENT);
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

    public void spending(int id) {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.preparedStatement = this.connection.prepareStatement(Constants.GET_CLIENT_NAME);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                log.info("Name " + this.resultSet.getString("name"));
            }
            this.preparedStatement = this.connection.prepareStatement(Constants.GET_CLIENT_SPENT);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            int spent = 0;
            while (this.resultSet.next())
                spent += this.resultSet.getInt("price");
            log.info("Spent money " + spent);
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

    public void addProduct(Product product) {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getOrderId());
            preparedStatement.executeUpdate();
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
            this.preparedStatement = this.connection.prepareStatement(GET_CLIENT_BUDGET_BY_ORDER);
            this.preparedStatement.setInt(1, product.getOrderId());
            this.resultSet = this.preparedStatement.executeQuery();
            log.info("Data Added Successfully");
        } catch (SQLException e) {
            log.warning(String.format("exception get all %s", e.getMessage()));
        } catch (Exception e) {
            log.info("no money");
        } finally {
            try {
                if (this.preparedStatement != null) {
                    this.preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                log.warning(String.format("exception get all %s", e.getMessage()));
            }
        }
    }
}
