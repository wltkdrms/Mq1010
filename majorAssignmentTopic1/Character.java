public class Character {
    public String name;
    public Race race;
    public int health;
    public int maxHealth;
    public int attack;
    public int defense;
    public Equipment equippedItem;
    public boolean specialUsed;
    public SpecialMove specialMove;
    public String teamName; // e.g., "Team A" or "Team B"

    /**
     * Constructs a character with a race and optional equipment.
     */
    public Character(String name, Race race, int maxHealth, SpecialMove specialMove, String teamName) {
        this.name = name;
        this.race = race;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = race.getBaseAttack();
        this.defense = race.getBaseDefense();
        this.specialUsed = false;
        this.specialMove = specialMove;
        this.teamName = teamName;
    }

    // ====================== Core Actions =======================

    /**
     * Attacks a target character and deals damage based on attack vs defense.
     */
    public void attack(Character target) {
        int totalAttack = this.attack;
        int damage = totalAttack - target.defense;
        if (damage < 0) damage = 0;
        target.takeDamage(damage);
        System.out.println(name + " attacked " + target.name + " for " + damage + " damage.");
    }

    /**
     * Reduces this character's health by the given amount.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Uses the character's one-time special move.
     */
    public void useSpecialMove() {
        if (!specialUsed && specialMove != null) {
            specialMove.apply(this);
            specialUsed = true;
            System.out.println(name + " used their special move: " + specialMove.getName() + "!");
        } else {
            System.out.println (name + " has already used Special Move.");
        }
    }

    /**
     * Equips an item if it's compatible with the character's race.
     */
    public void equipItem(Equipment item) {
        if (item.isCompatibleWith(this.race)) {
            this.equippedItem = item;
            this.attack += item.getAttackBoost();
            this.defense += item.getDefenseBoost();
            System.out.println(name + " equipped " + item.getName() + ".");
        } else {
            System.out.println(item.getName() + " is not compatible with " + race.getName() + ".");
        }
    }

    public void increaseAttack (int amount){
        this.attack += amount;
    }

    public void increaseDefense (int amount){
        this.defense += amount;
    }

    // ====================== Getters =======================

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public boolean isSpecialUsed() {
        return specialUsed;
    }

    public String getStatus() {
    return name + " [" + teamName + "] " + race.getName() + " - HP: " + health + "/" + maxHealth
           + " | ATK: " + attack + ", DEF: " + defense;
    }

    public String getTeamName() {
        return teamName;
    }
}
