public class Equipment {
    public String name;
    public String rarity; //”common/rare/epic”
    public int bonusAttack; //some equipment gives character more attack power
    public int bonusDefense; //some equipment gives character more defense power
    public StatusEffect statusEffect;

    public Equipment(String name, String rarity, int bonusAttack, int bonusDefense, StatusEffect statusEffect) {
        this.name = name;
        this.rarity = rarity;
        this.bonusAttack = bonusAttack;
        this.bonusDefense = bonusDefense;
        this.statusEffect = statusEffect;
    }

    //Returns the total attack boost provided by the equipment.
    public int getTotalAttackBoost(){
        return bonusAttack; 
    }

    //Returns the total defense boost provided by the equipment.
    public int getTotalDefenseBoost(){
        return bonusDefense; 
    }

    //Checks whether the equipment has a status effect.
    public boolean hasStatusEffect(){
        if (statusEffect != null) {
            return true; 
        }
        return false;
    }
} 


