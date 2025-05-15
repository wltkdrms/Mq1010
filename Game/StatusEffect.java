public class StatusEffect {
     String name;
     int damagePerTurn;
     int duration;

    public StatusEffect(String name, int damagePerTurn, int duration) {
        this.name = name;
        this.damagePerTurn = damagePerTurn;
        this.duration = duration;
    }

    public void applyEffect(Character target) {
        if (duration > 0 && target.isAlive()) {
            target.takeDamage(damagePerTurn);
            reduceDuration();
        }
    }

    public boolean isExpired() {
        return duration <= 0;
    }

    public void reduceDuration() {
        duration--;
    }

    public String getName() {
        return name;
    }
}
