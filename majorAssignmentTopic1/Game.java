import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Game {
    public Team teamA;
    public Team teamB;
    public Character currentPlayer;
    public ArrayList<String> battleLog;
    public boolean isGameOver;
    public double randomEventChance = 0.1; // 10% chance of random effect each turn
    public Scanner scanner;
    // For linked-list style battle log (used in printBattleLog)
    public BattleLogEntry battleLogHead;
    public BattleLogEntry currentLog;
    
    //Constructor initializes teams, scanner, and game status.
    public Game() {
        teamA = new Team("Team A");
        teamB = new Team("Team B");
        battleLog = new ArrayList<>();
        isGameOver = false;
        scanner = new Scanner(System.in);
    }

    // Initializes the game: creates characters, assigns teams, and starts.   
    public void startGame() {
        setupTeams();
        chooseStartingPlayer();
        System.out.println("Game Start! First to play: " + currentPlayer.getName());
        gameLoop();
    }

   /*
     * Prepares the game by:
     * Creating races
     * Defining equipment and special moves
     * Creating characters and assigning them to teams
   */
    public void setupTeams() {
        // Define races with base stats
        Race elf = new Race("Elf", 15, 8);
        Race orc = new Race("Orc", 12, 12);
        Race human = new Race("Human", 10, 10);

        // Define race-specify equipment
        Equipment elvenBow = new Equipment("Elven Bow", 5, 0, elf);
        Equipment orcShield = new Equipment("Orc Shield", 0, 5, orc);
        Equipment sword = new Equipment("Steel Sword", 3, 2, human);

        // Define special moves
        SpecialMove berserk = new SpecialMove("Berserk", 10, 0);
        SpecialMove fortify = new SpecialMove("Fortify", 0, 10);
        SpecialMove surge = new SpecialMove("Surge", 5, 5);

        // Create Team A characters and equip them
        Character a1 = new Character("Luna", elf, 100, surge, "Team A");
        Character a2 = new Character("Grim", orc, 100, berserk, "Team A");
        Character a3 = new Character("Elric", human, 100, fortify, "Team A");

        a1.equipItem(elvenBow);
        a2.equipItem(orcShield);
        a3.equipItem(sword);
        
        teamA.addMember(a1);
        teamA.addMember(a2);
        teamA.addMember(a3);
        
        // Create Team B characters and equip them
        Character b1 = new Character("Zara", elf, 100, surge, "Team B");
        Character b2 = new Character("Thok", orc, 100, berserk, "Team B");
        Character b3 = new Character("Riven", human, 100, fortify, "Team B");

        b1.equipItem(elvenBow);
        b2.equipItem(orcShield);
        b3.equipItem(sword);

        teamB.addMember(b1);
        teamB.addMember(b2);
        teamB.addMember(b3);
    }

    //Randomly selects a starting player from either team.
    public void chooseStartingPlayer() {
        Random rand = new Random();
        currentPlayer = rand.nextBoolean() ? teamA.getRandomAliveCharacter() : teamB.getRandomAliveCharacter();
    }

   /*
     * Main turn-based loop of the game. Continues until a team is defeated.
     * On each turn:
     *  The current player selects an action (attack or special move)
     *  The action is applied to a random enemy
     *  A random effect may occur
     *  The game checks for victory conditions
     *  If game isn't over, turn switches
    */
    public void gameLoop() {
        while (!isGameOver) {
            System.out.println("\nCurrent turn: " + currentPlayer.getName());
            System.out.println(currentPlayer.getStatus());

            // Identify opposing team
            Team opponentTeam = currentPlayer.getTeamName().equals("Team A") ? teamB : teamA;
            Character target = opponentTeam.getRandomAliveCharacter();

            // If special move not used, give player a choice
            if (!currentPlayer.isSpecialUsed()) {
                System.out.println("Choose action: 1 = Attack, 2 = Use Special Move");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    currentPlayer.useSpecialMove();
                } else {
                    currentPlayer.attack(target);
                }
            } else {
                currentPlayer.attack(target); // If special already used, auto-attack
            }

            // Maybe trigger a random event
            triggerRandomEffectMaybe();

            // Check for game over
            checkGameOver();
            if (!isGameOver) {
                switchTurn();
            }
        }
        //end of game
        System.out.println("\n=== GAME OVER ===");
        if (teamA.isDefeated()) {
            System.out.println("Team B wins!");
        } else {
            System.out.println("Team A wins!");
        }
    }

    //Switches turn to a random character from the opposing team.
    public void switchTurn() {
        Team currentTeam = currentPlayer.getTeamName().equals("Team A") ? teamA : teamB;
        Team opponentTeam = currentTeam == teamA ? teamB : teamA;
        currentPlayer = opponentTeam.getRandomAliveCharacter();
    }

    /*
     * Triggers a random effect that applies to all characters,
     * with a chance based on `randomEventChance`.
     */
    public void triggerRandomEffectMaybe() {
        Random rand = new Random();
        if (rand.nextDouble() < randomEventChance) {
            RandomEffect event = RandomEffect.generateRandomEffect();
            // Combine all characters into one list
            ArrayList<Character> everyone = new ArrayList<>();
            everyone.addAll(teamA.getAllMembers());
            everyone.addAll(teamB.getAllMembers());
            // Apply effect to all characters
            event.applyToAll(everyone);
        }
    }

   //Checks if either team is fully defeated. If so, ends the game.
    public void checkGameOver() {
        if (teamA.isDefeated() || teamB.isDefeated()) {
            isGameOver = true;
        }
    }
    //Adds a message to the battle log using a linked list of `BattleLogEntry` nodes.
    public void addToBattleLog(String message) {
        BattleLogEntry newEntry = new BattleLogEntry(message);
        if (battleLogHead == null) {
            battleLogHead = newEntry;
            currentLog = newEntry;
        } else {
            currentLog.setNextEntry(newEntry);
            currentLog = newEntry;
        }
    }
    //Prints the full battle log from the head node.
    public void printBattleLog() {
        if (battleLogHead != null) {
            battleLogHead.printLog();
        } else {
            System.out.println("No battle log available.");
        }
    }

    // File I/O (write to file)
    public void saveBattleLogToCSV(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.println("Turn,Message");

            BattleLogEntry current = battleLogHead;
            int turn = 1;
            while (current != null) {
                writer.println(turn + "," + current.getMessage());
                current = current.getNextEntry();
                turn++;
            }

            writer.close();
            System.out.println("✅ Battle log saved to " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}
