// Game.java
import java.util.*;

public class Game {
    private boolean isGameOver;
    private ArrayList<String> battleLog;
    private int currentTurnIndex;
    private String difficultyLevel;
    private double randomEventChance;
    private Character startingPlayer;
    private ArrayList<Character> characters;
    private Duration duration;
    private List<RandomEvent> events;

    public void startGame() { /* 구현 */ }
    public void outputBattleLog() { /* 구현 */ }
    public void chooseStartingPlayer() { /* 구현 */ }
    public void nextTurn() { /* 구현 */ }
    public boolean checkGameOver() { return isGameOver; }
}
