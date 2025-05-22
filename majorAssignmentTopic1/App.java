import java.io.IOException;
public class App {
    public static void main(String[] args) {
        Game game = new Game(); // Create the game instance
        game.startGame(); // Start game (sets up teams, picks player, runs loop)
        
        // This is the method to save the game into a .csv file
        try {
            game.saveBattleLogToCSV("battle_log.csv");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
