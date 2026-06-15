package portfolio.oop;

public abstract class Animal {
    private final String name;
    private final int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String speak();

    public String describe() {
        return "%s is %d years old and says %s".formatted(name, age, speak());
    }
}
