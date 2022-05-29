public class Client {
    private String name;
    private int id;
    private int budget;

    Client(String name, int budget) {
        this.setName(name);
        this.setBudget(budget);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getBudget() {
        return this.budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
