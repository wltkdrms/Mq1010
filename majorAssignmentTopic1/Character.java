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

    /*  
    * Constructs a Character with a name, race, max health, special move, and team name.
    * Attack and defense are initialized based on the character's race.
    *  Health is set to maxHealth at the start.
    */
    public Character(String name, Race race, int maxHealth, SpecialMove specialMove, String teamName) {
        this.name = name;
        this.race = race;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = race.getBaseAttack(); // Set attack based on race's base value
        this.defense = race.getBaseDefense(); // Set defense based on race's base value
        this.specialUsed = false;  // Special move is initially unused
        this.specialMove = specialMove;
        this.teamName = teamName;
    }
    // Core Actions
    /*
    *Attacks another character.
    *Damage is calculated as (attacker's attack - target's defense).
    *Damage can't go below zero.
    */
    public void attack(Character target) {
        int totalAttack = this.attack;
        int damage = totalAttack - target.defense;
        if (damage < 0) damage = 0;
        target.takeDamage(damage);
        Game.addToBattleLog(name + " attacked " + target.name + " for " + damage + " damage.");
    }

    /*
     Reduces this character's health by a specified amount.
     Health cannot go below 0.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /*
    Uses the character's special move if it hasn't been used already.
    Special moves can only be used once.
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

    /*
     Equips an item that is compatible with the character's race.
     Updates attack and defense based on the item's boost values.
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
    
    //Increases the character's attack stat by a given amount
    public void increaseAttack (int amount){
        this.attack += amount;
    }
    //Increases the character's defense stat by a given amount
    public void increaseDefense (int amount){
        this.defense += amount;
    }

    // Getters

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public int getHealth() {
        return health;
    }
    //checks if character is still alive
    public boolean isAlive() {
        return this.health > 0;
    }
    //checks if character has used his special move.
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
