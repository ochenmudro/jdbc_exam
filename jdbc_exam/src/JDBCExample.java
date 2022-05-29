public class JDBCExample {
    public JDBCExample() {
    }

    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        orderDAO.getOrders(1);
        clientDAO.spending(1);
        orderDAO.getExpensive(1);

        Order order=new Order(1, "complect");
        Order order1=new Order(1, "makeup");
        orderDAO.update(order);
        orderDAO.add(order1);
        orderDAO.deleteByClient(1,"makeup");
    }
}