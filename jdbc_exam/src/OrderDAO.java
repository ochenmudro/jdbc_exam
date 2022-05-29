import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO implements DAO<Order> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    static final String INSERT = "INSERT INTO \"order\" (name, client_id) VALUES (?,?)";
    static final String GET = "SELECT * FROM \"order\"";
    static final String UPDATE = "UPDATE \"order\" SET client_id=? WHERE name=?";
    static final String DELETE = "DELETE FROM \"order\" WHERE id=?";
    static final String GET_ONE = "SELECT * FROM \"order\" WHERE id=?";
    static final String GET_ORDERS = "SELECT * FROM \"order\" WHERE client_id=?";
    static final String EXPENSIVE = "SELECT order_id, SUM(price) AS final_price FROM product GROUP BY order_id ORDER BY final_price DESC ";
    static final String DEL_PROD = "DELETE FROM product WHERE order_id = (SELECT id FROM \"order\" WHERE name=?)";
    static final String DEL_ORDER = "DELETE FROM \"order\" WHERE client_id=? AND name=?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void getAll() {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    public void get(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ONE);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    public void add(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(INSERT);
            this.ptmt.setString(1, order.getName());
            this.ptmt.setInt(2, order.getClientId());
            this.ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(UPDATE);
            this.ptmt.setInt(1, order.getClientId());
            this.ptmt.setString(2, order.getName());
            this.ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(DELETE);
            this.ptmt.setInt(1, id);
            this.ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getOrders(int clientId) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ORDERS);
            this.ptmt.setInt(1, clientId);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Client id " +
                        this.resultSet.getInt("client_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getExpensive(int clientId) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(EXPENSIVE);
            this.resultSet = this.ptmt.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Most expensive order: ID " + this.resultSet.getInt("order_id") +
                        " total price " + this.resultSet.getInt("final_price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteByClient(int clientId, String orderName) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(DEL_PROD);
            this.ptmt.setString(1, orderName);
            this.ptmt.executeUpdate();
            this.ptmt.close();
            this.ptmt = this.connection.prepareStatement(DEL_ORDER);
            this.ptmt.setInt(1, clientId);
            this.ptmt.setString(2, orderName);
            this.ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

