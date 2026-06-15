package portfolio.games;

import java.util.Random;

/** Small battle simulator demonstrating interfaces, inheritance, and randomness. */
public class MarvelBattleGame {
    private final Random random;

    public MarvelBattleGame(Random random) {
        this.random = random;
    }

    public static void main(String[] args) {
        MarvelBattleGame game = new MarvelBattleGame(new Random());
        Hero hero = new Hero("Photon", 82, "Light Burst");
        Villain villain = new Villain("Shadow Circuit", 78, "Disable the city grid");
        System.out.println(game.fight(hero, villain));
    }

    public String fight(Combatant first, Combatant second) {
        int firstRoll = first.getPowerLevel() + random.nextInt(21);
        int secondRoll = second.getPowerLevel() + random.nextInt(21);
        Combatant winner = firstRoll >= secondRoll ? first : second;
        return "%s defeated %s (%d vs %d)".formatted(
                winner.getName(), winner == first ? second.getName() : first.getName(), firstRoll, secondRoll);
    }
}
