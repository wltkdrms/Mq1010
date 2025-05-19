public class App {
    public static void main(String[] args) {
        Game game = new Game(); // Create the game instance
        game.startGame(); // This is the method that starts off the whole game
        // This is the method to save the game into .csv file
        try {
            game.saveBattleLogToCSV("battle_log.csv");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
