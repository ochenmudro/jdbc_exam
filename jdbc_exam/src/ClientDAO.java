import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ClientDAO() {
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5455/postgresDB", "user", "admin");
    }

    public void getAll() {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM client");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Budget " + this.resultSet.getInt("budget"));
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
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
            } catch (Exception var9) {
                var9.printStackTrace();
            }

        }

    }

    public void get(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM client WHERE id=?");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Budget " + this.resultSet.getInt("budget"));
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

    public void add(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("INSERT INTO client (name, budget) VALUES (?,?)");
            this.ptmt.setString(1, client.getName());
            this.ptmt.setInt(2, client.getBudget());
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

    public void update(Client client) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("UPDATE client SET budget=? WHERE name=?");
            this.ptmt.setInt(1, client.getBudget());
            this.ptmt.setString(2, client.getName());
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
            this.ptmt = this.connection.prepareStatement("DELETE FROM client WHERE id=?");
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

    public void spending(int id) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT name FROM client WHERE id=?");
            this.ptmt.setInt(1, id);
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                System.out.println("Name " + this.resultSet.getString("name"));
            }

            this.ptmt = this.connection.prepareStatement("SELECT price FROM product AS p JOIN \"order\" AS o ON p.order_id=o.id AND o.client_id=?");
            this.ptmt.setInt(1, id);
            this.resultSet = this.ptmt.executeQuery();

            int spent = 0;
            while (this.resultSet.next())
                spent += this.resultSet.getInt("price");
            System.out.println("Spent money " + spent);
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
}

