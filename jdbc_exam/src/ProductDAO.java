import java.sql.*;

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
                        this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price")
                        + ", Order id" + this.resultSet.getInt("order_id"));
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

    public void get(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(GET_ONE);
            this.resultSet = this.ptmt.executeQuery();
            while (this.resultSet.next()) {
                System.out.println("ID " + this.resultSet.getInt("id") + ", Name " +
                        this.resultSet.getString("name") + ", Price " + this.resultSet.getInt("price")
                        + ", Order id" + this.resultSet.getInt("order_id"));
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

    public void add(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(INSERT);
            this.ptmt.setString(1, product.getName());
            this.ptmt.setInt(2, product.getPrice());
            this.ptmt.setInt(3, product.getOrderId());
            this.ptmt.executeUpdate();
            this.ptmt = this.connection.prepareStatement(CHECK);
            this.ptmt.setInt(1, product.getOrderId());
            this.resultSet = this.ptmt.executeQuery();
            if (this.resultSet.next()) {
                int check = this.resultSet.getInt(1) - product.getPrice();
                if (check < 0) {
                    throw new Exception();
                }
            }
            this.ptmt = this.connection.prepareStatement(BUDGET_UPDATE);
            this.ptmt.setInt(1, product.getPrice());
            this.ptmt.setInt(2, product.getOrderId());
            this.ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("No money");
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

    public void update(Product product) {
        try {
            this.connection = this.getConnection();
            this.ptmt = this.connection.prepareStatement(UPDATE);
            this.ptmt.setInt(1, product.getOrderId());
            this.ptmt.setString(2, product.getName());
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
}