package portfolio.games;

public class Hero extends GameCharacter {
    private final String signatureMove;

    public Hero(String name, int powerLevel, String signatureMove) {
        super(name, powerLevel);
        this.signatureMove = signatureMove;
    }

    public String getSignatureMove() {
        return signatureMove;
    }

    @Override
    public String getRole() {
        return "Hero";
    }
}
