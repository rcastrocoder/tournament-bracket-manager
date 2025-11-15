package main.java;

//player.java
//represents a player in the tournament
//includes name, skill level, and tournament record
//provides methods to update and retrieve player information

public class Player {

    //name of player
    private String name;

    //skill level between 1 and 10
    private int skillLevel;

    //number of wins player has
    private int wins;

    //number of losses player has
    private int losses;

    //constructor to initialize player attributes
    public Player(String name, int skillLevel) {
        this.name = name;
        this.skillLevel = skillLevel;
        
        //initialize with no wins
        this.wins = 0;
        //initialize with no losses
        this.losses = 0;
    }

    //get player name
    public String getName() {
        return name;
    }

    //get player skill level
    public int getSkillLevel() {
        return skillLevel;
    }

    //get number of wins
    public int getWins() {
        return wins;
    }

    //get number of losses
    public int getLosses() {
        return losses;
    }

    //set player name
    public void setName(String name) {
        this.name = name;
    }

    //set player skill level
    public void setSkillLevel(int skillLevel) {
        if (skillLevel < 1 || skillLevel > 10) {
            throw new IllegalArgumentException("skill level must be between 1 and 10");
        }
        this.skillLevel = skillLevel;
    }


    //increment win count
    public void recordWin() {
        this.wins++;
    }

    //increment loss count
    public void recordLoss() {
        this.losses++;
    }

    //convert player details to string
    @Override
    public String toString() {
        return "Player{name='" + name + "', skillLevel=" + skillLevel +
                ", wins=" + wins + ", losses=" + losses + "}";
    }
}

