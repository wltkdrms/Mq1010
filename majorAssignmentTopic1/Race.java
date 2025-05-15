public class Race {
    public String name;
    public int baseAttack;
    public int baseDefense;

    /**
     * Constructor to create a new Race with given attributes.
     * @param name         the name of the race (e.g., Elf, Orc, Human)
     * @param baseAttack   base attack strength
     * @param baseDefense  base defense strength
     */
    public Race(String name, int baseAttack, int baseDefense) {
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }


    public String toString() {
        return name + " (ATK: " + baseAttack + ", DEF: " + baseDefense + ")";
    }
}