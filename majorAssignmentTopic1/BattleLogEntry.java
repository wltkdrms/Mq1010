public class BattleLogEntry {
    public String message; // The battle message for this log entry
    public BattleLogEntry nextEntry; // Reference to the next log entry
    
    public BattleLogEntry(String message) {
        this.message = message;
        this.nextEntry = null; //// Initially, there is no next entry
    }

    public String getMessage() {
        return message;
    }
    //Links this entry to the next one in the log
    public void setNextEntry(BattleLogEntry nextEntry) {
        this.nextEntry = nextEntry;
    }

    public BattleLogEntry getNextEntry() {
        return nextEntry;
    }

    /*
     * Recursively prints all log messages starting from this entry.
     * This method traverses the log using the linked `nextEntry` references,
     * printing each message until it reaches the end.
     */
    public void printLog() {
        System.out.println(message);
        if (nextEntry != null) {
            nextEntry.printLog();  // Recursive call to print the rest of the log
        }
    }
}
