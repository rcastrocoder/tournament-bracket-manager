package main.java;

//historylist.java
//manages a list of match history for the tournament
//allows adding match results, displaying all matches, and retrieving matches by round

import java.util.ArrayList;
import java.util.List;

public class HistoryList {
	
    //list to store match history
    private List<MatchNode> history;

    //constructor to initialize list
    public HistoryList() {
    	//using ArrayList for easy access
        history = new ArrayList<>();
    }

    //method to add a match result to history
    public void addMatch(MatchNode match) {
        if (match.getWinner() != null) {
            history.add(match);
        }
    }

    //method to display all matches in history
    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No matches have been played yet.");
        } else {
            System.out.println("Match History:");
            for (MatchNode match : history) {
                System.out.println(match);
            }
        }
    }

    //method to retrieve a specific match by round
    public MatchNode getMatchByRound(int round) {
        for (MatchNode match : history) {
            if (match.getRound() == round) {
                return match;
            }
        }
        //no match found for the given round
        return null;
    }
}
