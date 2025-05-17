public class Equipment {
    public String name;
    public int attackBoost;
    public int defenseBoost;
    public Race compatibleRace;

    /*
     * Constructor to create an equipment item.
     * @param name Name of the equipment 
     * @param attack boost Boost provided by the equipment
     * @param defenseBoost Boost provided by the equipment
     * @param compatibleRace  The race that can use this equipment
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

    /*
     * Checks if a given race can use this equipment.
     * @return true if compatible, false otherwise
     */
    public boolean isCompatibleWith(Race race) {
        return compatibleRace.getName().equalsIgnoreCase(race.getName());
    }
    public String toString() {
        return name + " (+" + attackBoost + " ATK, +" + defenseBoost + " DEF, Race: " + compatibleRace.getName() + ")";
    }
}
