public class JDBCExample {
    public JDBCExample() {
    }

    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        new OrderDAO();
        new ProductDAO();
        clientDAO.spending(1);
    }
}