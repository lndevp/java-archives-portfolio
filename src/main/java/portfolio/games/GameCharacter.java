package portfolio.games;

public abstract class GameCharacter implements Combatant {
    private final String name;
    private final int powerLevel;

    protected GameCharacter(String name, int powerLevel) {
        if (powerLevel < 0) {
            throw new IllegalArgumentException("power level cannot be negative");
        }
        this.name = name;
        this.powerLevel = powerLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPowerLevel() {
        return powerLevel;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return "%s %s (power %d)".formatted(getRole(), name, powerLevel);
    }
}
