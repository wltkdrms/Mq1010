public class Equipment {
    public String name;
    public String rarity;
    public int bonusAttack;
    public int bonusDefense;
    public StatusEffect statusEffect;

    public Equipment(String name, String rarity, int bonusAttack, int bonusDefense, StatusEffect statusEffect) {
        this.name = name;
        this.rarity = rarity;
        this.bonusAttack = bonusAttack;
        this.bonusDefense = bonusDefense;
        this.statusEffect = statusEffect;
    }

    public int getTotalAttackBoost(){
        return bonusAttack; //Returns the total attack boost provided by the equipment.
    }

    public int getTotalDefenseBoost(){
        return bonusDefense; //Returns the total defense boost provided by the equipment.
    }
    
    public boolean hasStatusEffect(){ //Checks whether the equipment has a status effect.
        if (statusEffect != null) {
            return true; //TRUE if a status effect is present
        }
        return false; //FLASE if not present
    }
}

