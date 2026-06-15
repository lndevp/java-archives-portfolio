package portfolio.games;

public class Villain extends GameCharacter {
    private final String plan;

    public Villain(String name, int powerLevel, String plan) {
        super(name, powerLevel);
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    @Override
    public String getRole() {
        return "Villain";
    }
}
