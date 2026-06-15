package portfolio.oop;

public class Dog extends Animal {
    private final String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String speak() {
        return "woof";
    }

    @Override
    public String describe() {
        return super.describe() + " (breed: " + breed + ")";
    }
}
