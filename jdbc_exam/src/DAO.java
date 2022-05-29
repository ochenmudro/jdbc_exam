public interface DAO<T> {
    String DB_URL = "jdbc:postgresql://localhost:5455/postgresDB";
    String USER = "user";
    String PASS = "admin";

    void getAll();

    void get(T t);

    void add(T t);

    void update(T t);

    void delete(int id);
}
