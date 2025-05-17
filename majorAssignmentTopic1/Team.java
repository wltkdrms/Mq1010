import java.util.ArrayList;
import java.util.Random;

public class Team {
    public String name;
    public ArrayList<Character> members;

    /*
    Constructor to initialize a team with a name and an empty character list.
    @param name The name of the team
    */
    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

     /*
     * Adds a character to the team, if there are fewer than 3 members.
     * Prevents teams from having more than 3 characters.
     * @param character The character to be added to the team
     */
    public void addMember(Character character) {
        if (members.size() < 3) {
            members.add(character);
        } else {
            System.out.println("Team " + name + " already has 3 members.");
        }
    }

    /**
     * Returns a random character from the team who is still alive.
     * Returns null if no alive characters remain.
     */
    public Character getRandomAliveCharacter() {
        ArrayList<Character> alive = getAliveCharacters();
        if (alive.isEmpty()) return null;

        Random rand = new Random();
        return alive.get(rand.nextInt(alive.size()));
    }

     //Returns a list of characters who are still alive.
    public ArrayList<Character> getAliveCharacters() {
        ArrayList<Character> alive = new ArrayList<>();
        for (Character c : members) {
            if (c.isAlive()) {
                alive.add(c);
            }
        }
        return alive;
    }

    //Returns true if all characters are dead.
    public boolean isDefeated() {
        for (Character c : members) {
            if (c.isAlive()) return false;
        }
        return true;
    }

    public ArrayList<Character> getAllMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public void printTeamStatus() {
        System.out.println("Status of " + name + ":");
        for (Character c : members) {
            System.out.println(" - " + c.getStatus());
        }
    }

    //Recursively calculates the total health of all alive characters on the team.
    public int getTotalHealthRecursive() {
        return getTotalHealthRecursiveHelper(members, 0);
    }

     /*
     * Helper method for the recursive total health calculation.
     * @param list  The list of characters to sum health from
     * @param index The current index in recursion
     * return Cumulative health of alive characters from current index to end of list
     */
    private int getTotalHealthRecursiveHelper(ArrayList<Character> list, int index) {
        if (index >= list.size()) {
            return 0; // Base case: reached end of list
        }

        Character c = list.get(index);
        int health = c.isAlive() ? c.getHealth() : 0;
        // Recursive case: current health + rest of list
        return health + getTotalHealthRecursiveHelper(list, index + 1);
    }
}
