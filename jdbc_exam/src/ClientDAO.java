import java.sql.*;

public class ClientDAO implements DAO<Client> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    static final String INSERT = "INSERT INTO client (name, budget) VALUES (?,?)";
    static final String GET = "SELECT * FROM client";
    static final String UPDATE = "UPDATE client SET budget=? WHERE name=?";
    static final String DELETE = "DELETE FROM client WHERE id=?";
    static final String GET_ONE = "SELECT * FROM client WHERE id=?";
    static final String GET_NAME = "SELECT name FROM client WHERE id=?";
    static final String SPENT = "SELECT price FROM product AS p JOIN \"order\" AS o ON p.order_id=o.id AND o.client_id=?";

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
                        this.resultSet.getString("name")
                        + ", Budget " + this.resultSet.getInt("budget"));
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

    public void get(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ONE);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Budget " + this.resultSet.getInt("budget"));
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

    public void add(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(INSERT);
            this.ptmt.setString(1, client.getName());
            this.ptmt.setInt(2, client.getBudget());
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

    public void update(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(UPDATE);
            this.ptmt.setInt(1, client.getBudget());
            this.ptmt.setString(2, client.getName());
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

    public void spending(int id) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET_NAME);
            this.ptmt.setInt(1, id);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("Name " + this.resultSet.getString("name"));
            }
            this.ptmt = this.connection.prepareStatement(SPENT);
            this.ptmt.setInt(1, id);
            this.resultSet = this.ptmt.executeQuery();
            int spent = 0;
            while (this.resultSet.next())
                spent += this.resultSet.getInt("price");
            System.out.println("Spent money " + spent);
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
