public class Equipment {
    public String name;
    public int attackBoost;
    public int defenseBoost;
    public Race compatibleRace;

    /**
     * Constructor to create an equipment item.
     * @param name            name of the equipment (e.g., "Steel Sword", "Mystic Shield")
     * @param attackBoost     attack boost provided by the equipment
     * @param defenseBoost    defense boost provided by the equipment
     * @param compatibleRace  the race that can use this equipment
     */
    public Equipment(String name, int attackBoost, int defenseBoost, Race compatibleRace) {
        this.name = name;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
        this.compatibleRace = compatibleRace;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public int getDefenseBoost() {
        return defenseBoost;
    }

    public Race getCompatibleRace() {
        return compatibleRace;
    }

    /**
     * Checks if a given race can use this equipment.
     * @param race the race to check compatibility with
     * @return true if compatible, false otherwise
     */
    public boolean isCompatibleWith(Race race) {
        return compatibleRace.getName().equalsIgnoreCase(race.getName());
    }

    @Override
    public String toString() {
        return name + " (+" + attackBoost + " ATK, +" + defenseBoost + " DEF, Race: " + compatibleRace.getName() + ")";
    }
}