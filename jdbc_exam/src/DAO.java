public interface DAO<T> {
    String DB_URL = "jdbc:postgresql://localhost:5455/postgresDB";
    String USER = "user";
    String PASS = "admin";

    void getAll();

    void get(T var1);

    void add(T var1);

    void update(T var1);

    void delete(int var1);
}
