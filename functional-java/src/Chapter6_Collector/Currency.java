package Chapter6_Collector;

public class Currency {
    private String name;

    public String getName() {
        return name;
    }

    public Currency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                '}';
    }
}
