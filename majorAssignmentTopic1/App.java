public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame(); // This is the method that starts off the whole game
        // This is the method to save the game in to .csv file
        try {
            game.saveBattleLogToCSV("battle_log.csv");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
