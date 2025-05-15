public class Race {
    public String raceName;
    public int level;

    public Race(String raceName, int level) {
        this.raceName = raceName;
        this.level = level;
    }

    public int getPowerLevel(){
        return level; //Returns an integer value based on level
    }
}
