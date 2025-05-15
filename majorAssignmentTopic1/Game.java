import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public Team teamA;
    public Team teamB;
    public Character currentPlayer;
    public ArrayList<String> battleLog;
    public boolean isGameOver;
    public double randomEventChance = 0.3; // 30% chance each turn
    public Scanner scanner;

    public Game() {
        teamA = new Team("Team A");
        teamB = new Team("Team B");
        battleLog = new ArrayList<>();
        isGameOver = false;
        scanner = new Scanner(System.in);
    }

    /**
     * Initializes the game: creates characters, assigns teams, and starts.
     */
    public void startGame() {
        setupTeams();
        chooseStartingPlayer();
        System.out.println("Game Start! First to play: " + currentPlayer.getName());
        gameLoop();
    }

    /**
     * Sets up characters, races, equipment, and assigns to teams.
     */
    public void setupTeams() {
        // Define some races
        Race elf = new Race("Elf", 15, 8);
        Race orc = new Race("Orc", 12, 12);
        Race human = new Race("Human", 10, 10);

        // Define equipment
        Equipment elvenBow = new Equipment("Elven Bow", 5, 0, elf);
        Equipment orcShield = new Equipment("Orc Shield", 0, 5, orc);
        Equipment sword = new Equipment("Steel Sword", 3, 2, human);

        // Define special moves
        SpecialMove berserk = new SpecialMove("Berserk", 10, 0);
        SpecialMove fortify = new SpecialMove("Fortify", 0, 10);
        SpecialMove surge = new SpecialMove("Surge", 5, 5);

        // Create characters
        Character a1 = new Character("Luna", elf, 100, surge, "Team A");
        Character a2 = new Character("Grim", orc, 110, berserk, "Team A");
        Character a3 = new Character("Elric", human, 95, fortify, "Team A");

        a1.equipItem(elvenBow);
        a2.equipItem(orcShield);
        a3.equipItem(sword);

        teamA.addMember(a1);
        teamA.addMember(a2);
        teamA.addMember(a3);

        Character b1 = new Character("Zara", elf, 100, surge, "Team B");
        Character b2 = new Character("Thok", orc, 110, berserk, "Team B");
        Character b3 = new Character("Riven", human, 95, fortify, "Team B");

        b1.equipItem(elvenBow);
        b2.equipItem(orcShield);
        b3.equipItem(sword);

        teamB.addMember(b1);
        teamB.addMember(b2);
        teamB.addMember(b3);
    }

    /**
     * Chooses a random starting player from either team.
     */
    public void chooseStartingPlayer() {
        Random rand = new Random();
        currentPlayer = rand.nextBoolean() ? teamA.getRandomAliveCharacter() : teamB.getRandomAliveCharacter();
    }

    /**
     * Runs the turn-based game loop until one team is defeated.
     */
    public void gameLoop() {
        while (!isGameOver) {
            System.out.println("\nCurrent turn: " + currentPlayer.getName());
            System.out.println(currentPlayer.getStatus());

            // Choose a random enemy
            Team opponentTeam = currentPlayer.getTeamName().equals("Team A") ? teamB : teamA;
            Character target = opponentTeam.getRandomAliveCharacter();

            // Action: Attack or Special?
            if (!currentPlayer.isSpecialUsed()) {
                System.out.println("Choose action: 1 = Attack, 2 = Use Special Move");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    currentPlayer.useSpecialMove();
                } else {
                    currentPlayer.attack(target);
                }
            } else {
                currentPlayer.attack(target);
            }

            // Maybe trigger a random event
            triggerRandomEffectMaybe();

            // Check for game over
            checkGameOver();
            if (!isGameOver) {
                switchTurn();
            }
        }

        System.out.println("\n=== GAME OVER ===");
        if (teamA.isDefeated()) {
            System.out.println("Team B wins!");
        } else {
            System.out.println("Team A wins!");
        }
    }

    /**
     * Switches the current player to a random character on the opposing team.
     */
    public void switchTurn() {
        Team currentTeam = currentPlayer.getTeamName().equals("Team A") ? teamA : teamB;
        Team opponentTeam = currentTeam == teamA ? teamB : teamA;
        currentPlayer = opponentTeam.getRandomAliveCharacter();
    }

    /**
     * Applies a random effect with a certain chance.
     */
    public void triggerRandomEffectMaybe() {
        Random rand = new Random();
        if (rand.nextDouble() < randomEventChance) {
            RandomEffect event = RandomEffect.generateRandomEffect();
            ArrayList<Character> everyone = new ArrayList<>();
            everyone.addAll(teamA.getAllMembers());
            everyone.addAll(teamB.getAllMembers());
            event.applyToAll(everyone);
        }
    }

    /**
     * Ends the game if a team is fully defeated.
     */
    public void checkGameOver() {
        if (teamA.isDefeated() || teamB.isDefeated()) {
            isGameOver = true;
        }
    }
}