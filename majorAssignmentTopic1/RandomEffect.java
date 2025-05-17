import java.util.ArrayList;
import java.util.Random;

public class RandomEffect {
    public String name;
    public String type; 
    public int value;

    /**
     * Constructor for a random effect.
     * @param name  the display name of the effect
     * @param type  type of effect 
     * @param value numerical value the effect will apply
     */
    public RandomEffect(String name, String type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

     /*
     * Applies the effect to all alive characters in the game.
     * For "damageAll", reduces each character’s HP by the value.
     * For "boostAttack", increases each character’s attack stat.
     * @param allCharacters List of all characters in both teams.
     */
    public void applyToAll(ArrayList<Character> allCharacters) {
        System.out.println("Random Event Triggered: " + name + "!");
        for (Character c : allCharacters) {
            if (!c.isAlive()) continue; //skip dead characters

            switch (type) {
                case "damageAll":
                    c.takeDamage(value);
                    System.out.println(c.getName() + " takes " + value + " damage.");
                    break;
                case "boostAttack":
                    c.increaseAttack(value);
                    System.out.println(c.getName() + "'s attack increased by " + value + ".");
                    break;
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
                return new RandomEffect("Fire spiral", "damageAll", 10);
            case 1:
                return new RandomEffect("Battle Doom", "boostAttack", 5);
            default:
                return new RandomEffect("Snowball", "damageAll", 5); // Fallback effect in case of unexpected roll value
        }
    }
}
