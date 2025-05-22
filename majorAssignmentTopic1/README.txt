1. Problem Statement
This program simulates a turn-based RPG battle between two teams of characters. 
The game models a combat system that includes:
- Races(such as Elf and Goblin) with base stats (attack & defense)
- Race-specific equipment that boosts those stats
- One-time special moves unique to each character
- Random events that occur with a 10% chance on any turn to boost or damage all characters, adding unpredictability

Each team has three characters, and the game progresses in alternating turns until one team is completely defeated.
At the start, one random character is selected as the first player.
Then, after each turn, control switches to a random alive character from the opposing team.
This continues until all characters on one team are defeated.
In the end, all major events (such as character attacks and the random events triggered) is saved to a battle_log.csv file.

Characters take one of the following actions on their turn:
-Perform a basic attack
-Use their special move (only once per game)
-If the special move has already been used, the character will automatically attack

2. Structure of the Program
This program is organized into classes that interact as follows:
 1.`Client.java`-Launches the game and calls `Game.startGame()` and `saveBattleLogToCSV()`
 2. 'Game.java`  
    → Orchestrates the entire game:
    - Sets up characters and teams
    - Randomly selects a starting player
    - Alternates turns
    - Applies attacks, special moves, and random events
    - Uses File I/O to save the battle log after the game ends  
    - Uses the `Team` and `Character` classes directly  
    - Maintains a linked list battle log using `BattleLogEntry` (used recursively)
 3. `Team.java`- Manages team members, alive checks, and includes a recursive method `getTotalHealthRecursive()`
     which is used at the end of the game to calculate and display each team's remaining health.
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
  → Recursively prints battle messages
  → Used by `Game` to store turn-by-turn logs  
  → Method: `printLog()` is implemented recursively.
This program has the following Test Classes:
(i)CharacterTest 
- Checks that character is alive immediately after creation
- Confirms character is dead after taking full damage
- Confirms character is still alive after partial damage
(ii)EquipmentTest
- Equipping compatible race items increases attack/defense
- Equipping incompatible items is ignored
(iii)GameTest
- Teams are initialized properly on creation
- setupTeams() adds members to each team
- Battle logs are added using linked list structure
- Game ends when a team is fully defeated
- Switching turns changes the current player
(iv)BattleLogEntryTest
- Log message is stored correctly in constructor
- Next entry can be set and retrieved
- Linked log entries can be chained correctly
(v)RandomEffectTest
- generateRandomEffect() produces valid effect types
- 'damageAll' reduces HP of living characters
- 'boostAttack' increases character attack
- Dead characters are not affected by random effects
(vi)TestAll
- Executes all test classes together in a unified suite*

3. How to Run the Program
- Open the project in a Java IDE (VS Code)
- Ensure all `.java` files are in the same `src/` folder or appropriate package
- Compile the program
- Run `Client.java`

During gameplay:
- Enter `1` to perform an attack
- Enter `2` to use a character’s special move (can be used once only)

After the game ends:
- The result is printed to the console
- The battle log is saved to `battle_log.txt`

4. Task Allocation (Group Contribution)
      All members contributed to reviewing and commenting on their assigned code sections. Code was divided evenly, and every
member ensured clarity and documentation of their parts. We held weekly group meetings to ensure all work was completed on time and met 
the project deadlines. The work is separated by: 
25% - Sangkeun Ji 48791180 		
 • Developed methods such as startGame(), chooseStartingPlayer(), checkGameOver(), and addToBattleLog(), etc.		
 • Integrated turn control and random event triggers.		
 • Conducted unit tests simulating turn sequences and verifying timing and battle log accuracy.
 • Responsible for the Unit testing
25% - Rishika Beedassy 48474762
 • Developed the Character class, including combat methods like attack(), useSpecialMove(), and takeDamage().		
 • Implemented core game logic in the Game classes.
 • Linked characters with Equipment, Race, and StatusEffect.		
 • Co-authored the README.txt.		
 • Tested character behaviors including healing, buffing, equipping, and special move use.
25% - Hemisa Pinweha 48479217
 • Implemented Equipment and Race classes and core game logic in the Game classes.
 • Designed attack/defense bonuses and power levels.		
 • Created mock items and races for testing.		
 • Co-authored the README.txt.		
 • Responsible for File I/O (read/write) and assisted in testing integration.
25% - Syeda Ruhana Masud 48613584
 • Developed StatusEffect with expiry logic and applyEffect() methods.		
 • Implemented the RandomEvent class to apply effects across characters.		
 • Responsible for the UML diagram.
 • Designed the game flow and how the game is going to look like. 

5. UML Diagram
LINK: https://yuml.me/dcee28fd.png

// Teams and Characters
[Team{bg:pink}]1-* [Character{bg:aquamarine}]

// Character relationships
[Character]1-1 [Race{bg:royalblue}]
[Character]1-1 [SpecialMove{bg:hotpink}]
[Character]0..1-1 [Equipment{bg:maroon}]

// Game relationships
[Game{bg:green}]1-1 [Team]
[Game]1-1 [Team]
[Game]1-1 [BattleLogEntry{bg:mediumorchid}]

// Battle log chaining
[BattleLogEntry]0..1-1 [BattleLogEntry]

// Random effects
[RandomEffect{bg:plum}]1-* [Character]

// Equipment compatibility
[Equipment]1-1 [Race]

//BattleLogEntry Class
[BattleLogEntry|message: String; nextEntry: BattleLogEntry|BattleLogEntry(): string;getMessage(): String;setNextEntry(nextEntry:BattleLogEntry):void;getNextEntry(): BattleLogEntry;printLog(): void]

//Character Class
[Character|name: String;race: Race;health: int;maxHealth: int;attack: int;defense: int;equippedItem: Equipment;specialUsed: boolean;specialMove: SpecialMove;teamName: String|Character(name: String, race: Race, maxHealth: int, specialMove: SpecialMove, teamName: String);attack(target: Character): void;takeDamage(damage: int): void;useSpecialMove(): void;equipItem(item: Equipment): void;increaseAttack(amount: int): void;increaseDefense(amount: int): void;getName(): String;getRace(): Race;getHealth(): int;isAlive(): boolean;isSpecialUsed(): boolean;getStatus(): String;getTeamName(): String]

//Equipment Class
[Equipment|name: String; attackBoost: int;defenseBoost: int;compatibleRace: Race| Equipment(name: String, attackBoost: int,defenseBoost: int, compatibleRace: Race); getName(): String; getAttackBoost(): int;getDefenseBoost():int;getCompatibleRace(): Race;isCompatibleWith(race: Race): boolean;toString(): String]

//Game Class
[Game| teamA: Team; teamB: Team;currentPlayer:Character;battleLog:ArrayList;isGameOver: boolean; randomEventChance: double; Scanner:scanner;battleLogHead:BattleLogEntry;currentLog: BattleLogEntry | Game();startGame(): void; setupTeams(): void; chooseStartingPlayer(): void; gameLoop(): void;switchTurn(): void;triggerRandomEffectMaybe(): void;checkGameOver(): void;addToBattleLog(message: String): void;printBattleLog(): void;saveBattleLogToCSV(filename: String): void]

//Race Class
[Race|name: String; baseAttack: int;baseDefense: int|Race(name: String, baseAttack: int, baseDefense: int);getName(): String;getBaseAttack(): int;getBaseDefense(): int;toString(): String]

//RandomEffect class
 [RandomEffect|name: String;type: String;value: int| RandomEffect(name: String, type: String, value: int); applyToAll(allCharacters: ArrayList);getName(): String; getType(): String;getValue(): int; generateRandomEffect(): RandomEffect]

//SpecialMove class
[SpecialMove|name: String;attackBoost: int; defenseBoost: int|SpecialMove(name: String, attackBoost: int; defenseBoost: int); apply(character: Character); getName(): String; toString(): String]

//Team class
[Team| name: String; members: ArrayList| Team(name: String); addMember(character: Character): void; getRandomAliveCharacter(): Character; getAliveCharacters(): ArrayList;isDefeated(): boolean; getAllMembers(): ArrayList; getName(): String; printTeamStatus(): void;getTotalHealthRecursive(): int]

6. Method Comparison Analysis
      One of the key methods in our project is the saveBattleLogToCSV(String filename) in the Game class, which handles writing the battle
log to a CSV file. In our project, we use a recursive data structure called BattleLogEntry to store combat messages, and we traverse it
recursively to write each turn’s message to the file. This method is not only effective but also satisfies the recursive structure
requirement of the assignment. An alternative design could use an ArrayList<String> to store the log entries and iterate through it with a
standard loop. While that approach is slightly more efficient for large datasets, our recursive solution is more elegant and allows us to
demonstrate our understanding of recursive programming meaningfully. Overall, our choices balance clarity, modularity, and
assignment requirements effectively.
      Another important method is the attack(Character target) method in the Character class. In our current implementation, this method
directly calculates the damage by subtracting the target's defense from the attacker's attack value, ensuring the damage is never negative.
While this works fine, an alternative and more modular approach would be to move the damage calculation into a separate helper method, such
as calculateDamage(). This would make the code easier to read, test, and reuse in other combat-related methods, such as special moves or
random event handling. Delegating responsibilities to helper methods keeps the main logic cleaner and better organized.
