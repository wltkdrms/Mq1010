import java.util.ArrayList;

public class Character {
    
        String name;
        int health;
        int maxHealth;
        int speed;
        int strength;
        int attack;
        int defense;
        String team;
        ArrayList <Equipment> accessory;
        Race race;
        boolean specialUsed;
        ArrayList<StatusEffect> statusEffects;

        // Constructor
    public Character(String name, int maxHealth, int speed, int strength, int attack, int defense, String team, Race race) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.speed = speed;
        this.strength = strength;
        this.attack = attack;
        this.defense = defense;
        this.team = team;
        this.race = race;
        this.specialUsed = false;
        this.accessory = new ArrayList<>();
        this.statusEffects = new ArrayList<>();
    }
        
        boolean isAlive(){
                if (health<=0){
                    return false;
                }
                return true;
        }
        void takeDamage(int amount){
            health-=amount;
            if(health<0){
                health=0;
            }
        }
        public String getStatus() {
        return name + " [" + team + "] - HP: " + health + "/" + maxHealth;
        }
        void heal(int amount){
            if(health<maxHealth){
            health+=amount;
            }
            else health=maxHealth;
        }
        public void useSpecialMove() {
        if (!specialUsed) {
            System.out.println(name + " used their special move!");
            this.attack += 10;
            this.speed += 5;
            specialUsed = true;
        } else {
            System.out.println("Special move already used.");
        }
    }
        public void equipItem(Equipment e) {
        accessory.add(e);
        this.attack += e.getTotalAttackBoost();
        this.defense += e.getTotalDefenseBoost();
            if (e.hasStatusEffect()) {
            applyStatusEffect(e.getStatusEffect());
            }
        }
        public void applyStatusEffect(StatusEffect effect) {
        statusEffects.add(effect);
        }

        void equipItem(ArrayList<Equipment> e){
            this.accessory=e;
        }
        public void clearStatusEffects() {
        statusEffects.clear();
        }
        public void attack(Character target) {
        int totalAttack = this.attack + this.strength;
        int damage = totalAttack - target.defense;
        if (damage < 0) damage = 0;
        target.takeDamage(damage);
        System.out.println(name + " attacked " + target.name + " for " + damage + " damage.");
    }

    public ArrayList<Equipment> getAccessory() {
        return accessory;
    }

    public void applyOngoingEffects() {
        ArrayList<StatusEffect> expiredEffects = new ArrayList<>();
        for (StatusEffect effect : statusEffects) {
            effect.applyEffect(this);
            effect.reduceDuration();
            if (effect.isExpired()) {
                expiredEffects.add(effect);
            }
        }
        statusEffects.removeAll(expiredEffects);
    }
 //getter methods 
    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }

    public int getHealth() {
        return health;
    }

    public boolean isSpecialUsed() {
        return specialUsed;
    }

    public ArrayList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }
}

        

