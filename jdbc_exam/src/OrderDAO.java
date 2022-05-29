import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO implements DAO<Order> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    static final String INSERT = "INSERT INTO order (name, client_id) VALUES (?,?)";
    static final String GET = "SELECT * FROM order";
    static final String UPDATE = "UPDATE order SET client_id=? WHERE name=?";
    static final String DELETE = "DELETE FROM order WHERE id=?";
    static final String GET_ONE = "SELECT * FROM order WHERE id=?";
    static final String GET_ORDERS = "SELECT * FROM \"order\" WHERE client_id=?";

    public OrderDAO() {
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5455/postgresDB", "user", "admin");
    }

    public void getAll() {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM order");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Client id " + this.resultSet.getInt("client_id"));
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.resultSet != null) {
                    this.resultSet.close();
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }

        }

    }

    public void get(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM order WHERE id=?");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Client id " + this.resultSet.getInt("client_id"));
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
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
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }

    }

    public void add(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("INSERT INTO order (name, client_id) VALUES (?,?)");
            this.ptmt.setString(1, order.getName());
            this.ptmt.setInt(2, order.getClientId());
            this.ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }

    }

    public void update(Order order) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("UPDATE order SET client_id=? WHERE name=?");
            this.ptmt.setInt(1, order.getClientId());
            this.ptmt.setString(2, order.getName());
            this.ptmt.executeUpdate();
            System.out.println("Table Updated Successfully");
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }

    }

    public void delete(int id) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("DELETE FROM order WHERE id=?");
            this.ptmt.setInt(1, id);
            this.ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }

    }

    public void getOrders(int id) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM \"order\" WHERE client_id=?");
            this.ptmt.setInt(1, id);
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Client id " + this.resultSet.getInt("client_id"));
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void getExpensive(int clientId) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT order_id, SUM(price) AS final_price FROM product GROUP BY order_id ORDER BY final_price DESC ");
            this.resultSet = this.ptmt.executeQuery();
            if (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("order_id");
                var10000.println("Most expensive order: ID " + var10001 + " total price " + this.resultSet.getInt("final_price"));
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }

    }

    public void deleteByClient(int clientId, String orderName) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("DELETE FROM product WHERE order_id = (SELECT id FROM \"order\" WHERE name=?)");
            this.ptmt.setString(1, orderName);
            this.ptmt.executeUpdate();
            this.ptmt.close();
            this.ptmt = this.connection.prepareStatement("DELETE FROM \"order\" WHERE client_id=? AND name=?");
            this.ptmt.setInt(1, clientId);
            this.ptmt.setString(2, orderName);
            this.ptmt.executeUpdate();
            System.out.println("Data deleted Successfully");
        } catch (SQLException var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var11) {
                var11.printStackTrace();
            }

        }

    }
}

