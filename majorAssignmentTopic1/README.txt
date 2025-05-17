1. Problem Statement
This program simulates a turn-based RPG battle between two teams of characters. 
The game models a combat system that includes:
- Races(such as Elf and Goblin) with base stats (attack & defense)
- Race-specific equipment that boosts those stats
- One-time special moves unique to each character
- Random events that occur with a 10% chance on any turn to boost or damage all characters, adding unpredictability

Each team has three characters, and the game progresses in alternating turns until one team is completely defeated.
At the start, one random character is selected as the first player.
Then,after each turn, control switches to a random alive character from the opposing team.
This continues until all characters on one team are defeated.

Characters take one of the following actions on their turn:
-Perform a basic attack
-Use their special move (only once per game)
-If the special move has already been used, the character will automatically attack

2. Structure of the Program
This program is organized into classes that interact as follows:
 1.`Main.java`-Launches the game and calls `Game.startGame()` and `saveBattleLogToFile()`
 2.`Game.java`  
    → Orchestrates the entire game:
    - Sets up characters and teams
    - Randomly selects a starting player
    - Alternates turns
    - Applies attacks, special moves, and random events
    - Uses File I/O to save the battle log after the game ends  
    - Uses the `Team` and `Character` classes directly  
    - Maintains a linked list battle log using `BattleLogEntry` (used recursively)
 3. `Team.java`- Manages team members, alive checks, and includes a recursive method `getTotalHealthRecursive()`which is Used at the end of the game to calculate and display each team's remaining health.

4. `Character.java`  
  → Uses:
    - A `Race` (defines base stats)
    - A `SpecialMove` (boosts stats once)
    - An optional `Equipment` (boosts stats if race-compatible)

- `Race.java` -Provides base `attack` and `defense` values
- `Equipment.java` -Applies stat boosts if compatible with the character’s race
- `SpecialMove.java` -Applies one-time attack and/or defense buffs
- `RandomEffect.java`
     Introduces randomness:
    - Each turn has a 10% chance to trigger an effect
    - Effects like `"Fire Spiral"`, `"Battle Doom"`, and `"snowball"` are applied
    - Effects are applied to all characters via a loop
- `BattleLogEntry.java`  
  → A recursive, linked log structure used to store and print battle messages
  → Used by `Game` to store turn-by-turn logs  
  → Method: `printLog()` is implemented **recursively**

3. How to Run the Program
- Open the project in a Java IDE (VS Code)
- Ensure all `.java` files are in the same `src/` folder or appropriate package
- Compile the program
- Run `Main.java`

During gameplay:
- Enter `1` to perform an attack
- Enter `2` to use a character’s special move (can be used once only)

After the game ends:
- The result is printed to the console
- The battle log is saved to `battle_log.txt`
4. Task Allocation (Group Contribution)
5. UML Diagram
6. Method Comparison Analysis
