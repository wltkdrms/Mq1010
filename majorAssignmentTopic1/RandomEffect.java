import java.util.ArrayList;
import java.util.Random;

public class RandomEffect {
    public String name;
    public String type; // e.g., "damageAll", "boostAttack"
    public int value;

    /**
     * Constructor for a random effect.
     * @param name  effect name
     * @param type  type of effect ("damageAll", "boostAttack", etc.)
     * @param value how much to apply
     */
    public RandomEffect(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * Applies the effect to all characters in the game.
     */
    public void applyToAll(ArrayList<Character> allCharacters) {
        System.out.println("Random Event Triggered: " + name + "!");
        for (Character c : allCharacters) {
            if (!c.isAlive()) continue;

            switch (type) {
                case "damageAll":
                    c.takeDamage(value);
                    System.out.println(c.getName() + " takes " + value + " damage.");
                    break;
                case "boostAttack":
                    c.increaseAttack(value);
                    System.out.println(c.getName() + "'s attack increased by " + value + ".");
                    break;
                // Add more types here if needed
                default:
                    System.out.println("Unknown effect type: " + type);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    /**
     * Randomly generates one of a few possible effects.
     */
    public static RandomEffect generateRandomEffect() {
        Random rand = new Random();
        int roll = rand.nextInt(2); // Add more cases as you add more effects

        switch (roll) {
            case 0:
                return new RandomEffect("Meteor Shower", "damageAll", 10);
            case 1:
                return new RandomEffect("Battle Cry", "boostAttack", 5);
            default:
                return new RandomEffect("Minor Shake", "damageAll", 5);
        }
    }
}