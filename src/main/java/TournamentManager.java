package main.java;

//tournamentmanager.java
//handles the overall tournament process, user interaction, and bracket management
//manages adding/removing players, initializing the bracket, and simulating the tournament

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentManager {

    //tournament tree for bracket
    private TournamentTree tournamentTree;

    //list to keep track of players
    private List<Player> players;
    
    //highest round simulated so far
    private int highestRoundSimulated = 0;

    //constructor to initialize tournament manager with an empty list of players
    public TournamentManager() {
        this.players = new ArrayList<>();
        this.tournamentTree = null;  // tournament tree initialized later
    }

    //add player to tournament
    public void addPlayer(Player player) {
        if (players.size() < 16) {  // assume a maximum of 16 players
            players.add(player);
        } else {
            System.out.println("Tournament is full, cannot add more players.");
        }
    }

    //remove player from tournament
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
            System.out.println(player.getName() + " has been removed from the tournament.");
            
            // Reset the tournament tree if it exists
            if (players.size() >= 2 && players.size() % 2 == 0) {
                initializeTournamentTree();
                System.out.println("Tournament tree reinitialized after player removal.");
            } else {
                tournamentTree = null; // Clear the tree if an odd number of players remain
                System.out.println("Tournament tree cleared. Add more players to reinitialize.");
            }
        } else {
            System.out.println("Player not found in the tournament.");
        }
    }


    //recursively update tournament tree
    private void updateTournamentTree(MatchNode match, Player player) {
        if (match == null) {
            return;
        }

        //remove player from match if present
        if (match.getPlayer1() == player) {
            match.setPlayer1(null);
        }
        if (match.getPlayer2() == player) {
            match.setPlayer2(null);
        }

        //recursively update left and right matches
        updateTournamentTree(match.getLeftMatch(), player);
        updateTournamentTree(match.getRightMatch(), player);
    }


    //initialize tournament tree with current players
    public void initializeTournamentTree() {
        if (players.size() < 2 || players.size() % 2 != 0) {
            System.out.println("Cannot initialize tournament. Ensure there are an even number of players (minimum 2).");
            return;
        }

        //list to hold initial matches for round 1
        List<MatchNode> matches = new ArrayList<>();
        for (int i = 0; i < players.size(); i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);
            
         // round 1 matches
            matches.add(new MatchNode(player1, player2, 1));
        }

        //build parent matches for subsequent rounds
        while (matches.size() > 1) {
            List<MatchNode> nextRound = new ArrayList<>();
            for (int i = 0; i < matches.size(); i += 2) {
                MatchNode left = matches.get(i);
                MatchNode right = matches.get(i + 1);
                MatchNode parent = new MatchNode(null, null, left.getRound() + 1);
                parent.setLeftMatch(left);
                parent.setRightMatch(right);
                nextRound.add(parent);
            }
            matches = nextRound;
        }

        //set root of tournament tree
        this.tournamentTree = new TournamentTree(matches.get(0));
        System.out.println("Tournament tree initialized!");
    }

    //find a player by name
    public Player findPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        
        //return null if player not found
        return null;
    }

    //get tournament tree
    public TournamentTree getTournamentTree() {
        return this.tournamentTree;
    }

    //get list of players
    public List<Player> getPlayers() {
        return this.players;
    }

    //simulate tournament round by round
    public void simulateTournament(HistoryList historyList) {
        if (tournamentTree == null) {
            System.out.println("No tournament tree exists to simulate.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int currentRound = 1;

        //simulate round by round
        while (true) {
        	
            //simulate matches for current round
            System.out.println("Simulating matches for Round: " + currentRound);
            tournamentTree.simulateMatches(currentRound, historyList);

            //display current bracket after round
            System.out.println("Current bracket after Round " + currentRound + ":");
            tournamentTree.displayBracket();

            //check if there is a next round
            if (!hasNextRound(tournamentTree.getRoot(), currentRound)) {
                MatchNode root = tournamentTree.getRoot();
                System.out.println("Tournament complete! Final winner is: " +
                                   (root.getWinner() != null ? root.getWinner().getName() : "TBD"));
                
                //exit tournament simulation loop
                return; 
            }

            //ask user if they want to simulate next round
            System.out.print("Do you want to simulate the next round? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("yes")) {
                System.out.println("Exiting simulation. You can resume later.");
                
                //exit tournament simulation loop
                return; 
            }

            //increment round to simulate next one
            currentRound++;
        }
    }



    //check if there are matches to be simulated in next round
    private boolean hasNextRound(MatchNode root, int currentRound) {
        if (root == null) {
            return false;
        }

        //check if there are any matches in next round
        if (root.getRound() > currentRound) {
            return true;
        }

        //check left and right subtrees
        return hasNextRound(root.getLeftMatch(), currentRound) || hasNextRound(root.getRightMatch(), currentRound);
    }

    //display current tournament bracket
    public void displayBracket() {
        if (tournamentTree != null) {
            tournamentTree.displayBracket();
        } else {
            System.out.println("Tournament tree is not initialized.");
        }
    }
}
