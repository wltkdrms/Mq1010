public class BattleLogEntry {
    public String message;
    public BattleLogEntry nextEntry;

    public BattleLogEntry(String message) {
        this.message = message;
        this.nextEntry = null;
    }

    public String getMessage() {
        return message;
    }

    public void setNextEntry(BattleLogEntry nextEntry) {
        this.nextEntry = nextEntry;
    }

    public BattleLogEntry getNextEntry() {
        return nextEntry;
    }

    // Recursively print log
    public void printLog() {
        System.out.println(message);
        if (nextEntry != null) {
            nextEntry.printLog();  // Recursive call!
        }
    }
}
