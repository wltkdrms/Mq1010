public class SpecialMove {
    public String name;
    public int attackBoost;
    public int defenseBoost;

    /**
     * Constructor for a simple stat-boosting special move.
     */
    public SpecialMove(String name, int attackBoost, int defenseBoost) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
    }

    /**
     * Applies the special move directly to the character’s stats.
     */
    public void apply(Character character) {
        character.increaseAttack(attackBoost);
        character.increaseDefense(defenseBoost);
        System.out.println(character.getName() + " gains +" + attackBoost + " ATK and +" + defenseBoost + " DEF from " + name + "!");
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return name + " (+ATK: " + attackBoost + ", +DEF: " + defenseBoost + ")";
    }
}