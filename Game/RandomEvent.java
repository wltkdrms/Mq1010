import java.util.List;

public class RandomEvent {
     String name;
     int damageDone;

    // Constructor
    public RandomEvent(String name, int damageDone) {
        this.name = name;
        this.damageDone = damageDone;
    }

    // Apply this event's damage to all characters using a for loop
    public void applyToAllCharacters(List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            if (character.isAlive()) {
                character.takeDamage(damageDone);
            }
        }
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for damageDone
    public int getDamageDone() {
        return damageDone;
    }
}

