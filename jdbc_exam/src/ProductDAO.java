import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO implements DAO<Product> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    static final String INSERT = "INSERT INTO product (name, price, order_id) VALUES (?,?,?)";
    static final String GET = "SELECT * FROM product";
    static final String UPDATE = "UPDATE product SET order_id=? WHERE name=?";
    static final String DELETE = "DELETE FROM product WHERE id=?";
    static final String GET_ONE = "SELECT * FROM product WHERE id=?";
    static final String BUDGET_UPDATE = "UPDATE client SET budget=(budget-?) FROM \"order\" WHERE \"order\".id=? AND \"order\".client_id=client.id";
    static final String CHECK = "SELECT budget FROM client JOIN \"order\" ON client.id=\"order\".client_id AND \"order\".id=?";

    public ProductDAO() {
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5455/postgresDB", "user", "admin");
    }

    public void getAll() {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM product");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price") + ", Order id" + this.resultSet.getInt("order_id"));
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

    public void get(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("SELECT * FROM product WHERE id=?");
            this.resultSet = this.ptmt.executeQuery();

            while (this.resultSet.next()) {
                PrintStream var10000 = System.out;
                int var10001 = this.resultSet.getInt("id");
                var10000.println("ID " + var10001 + ", Name " + this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price") + ", Order id" + this.resultSet.getInt("order_id"));
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

    public void add(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("INSERT INTO product (name, price, order_id) VALUES (?,?,?)");
            this.ptmt.setString(1, product.getName());
            this.ptmt.setInt(2, product.getPrice());
            this.ptmt.setInt(3, product.getOrderId());
            this.ptmt.executeUpdate();
            this.ptmt.close();
            this.ptmt = this.connection.prepareStatement("SELECT budget FROM client JOIN \"order\" ON client.id=\"order\".client_id AND \"order\".id=?");
            this.ptmt.setInt(1, product.getOrderId());
            this.resultSet = this.ptmt.executeQuery();
            if (this.resultSet.next()) {
                int check = this.resultSet.getInt(1) - product.getPrice();
                if (check < 0) {
                    throw new Exception();
                }
            }

            this.ptmt = this.connection.prepareStatement("UPDATE client SET budget=(budget-?) FROM \"order\" WHERE \"order\".id=? AND \"order\".client_id=client.id");
            this.ptmt.setInt(1, product.getPrice());
            this.ptmt.setInt(2, product.getOrderId());
            this.ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException var13) {
            var13.printStackTrace();
        } catch (Exception var14) {
            System.out.println("No money");
        } finally {
            try {
                if (this.ptmt != null) {
                    this.ptmt.close();
                }

                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

    }

    public void update(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement("UPDATE product SET order_id=? WHERE name=?");
            this.ptmt.setInt(1, product.getOrderId());
            this.ptmt.setString(2, product.getName());
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
            this.ptmt = this.connection.prepareStatement("DELETE FROM product WHERE id=?");
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
}

