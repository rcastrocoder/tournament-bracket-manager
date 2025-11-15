package main.java;

//tournamenttree.java
//manages the binary tree structure for the tournament bracket
//handles inserting matches, simulating them, and displaying the entire bracket

import java.util.Scanner;

public class TournamentTree {

    //root match node of tournament bracket
    private MatchNode root;

    //scanner for user input
    private Scanner scanner;

    //constructor to initialize tournament tree with a root match
    public TournamentTree(MatchNode root) {
        this.root = root;

        //initialize scanner for user input
        this.scanner = new Scanner(System.in);
    }

    //get root match node
    public MatchNode getRoot() {
        return root;
    }

    //insert a new match node into binary tree
    public void insertMatch(MatchNode parent, MatchNode match, boolean isLeft) {
        if (isLeft) {
            parent.setLeftMatch(match);
        } else {
            parent.setRightMatch(match);
        }
    }
    
    //method to check if match is final match of tournament
    private boolean isFinalMatch(MatchNode match) {
        return match.getLeftMatch() == null && match.getRightMatch() == null;
    }

    //method to generate next round
    private void generateNextRound(MatchNode match) {
        if (match == null) {
            return;
        }

        //if both child matches exist and have winners, create the parent match
        if (match.getLeftMatch() != null && match.getRightMatch() != null &&
            match.getLeftMatch().getWinner() != null && match.getRightMatch().getWinner() != null) {
            Player leftWinner = match.getLeftMatch().getWinner();
            Player rightWinner = match.getRightMatch().getWinner();
            MatchNode nextRoundMatch = new MatchNode(leftWinner, rightWinner, match.getRound() + 1);
            
            //reset winner for this match until simulation
            match.setWinner(null);
            match.setPlayer1(leftWinner);
            match.setPlayer2(rightWinner);
        }

        //recursively generate matches for the left and right children
        generateNextRound(match.getLeftMatch());
        generateNextRound(match.getRightMatch());
    }


    //simulate matches in tournament and set winners
    public void simulateMatches(int round, HistoryList historyList) {
        System.out.println("Simulating matches for Round: " + round);
        simulateRecForRound(root, round, historyList);
    }
    
    //simulate matches for a specific round only
    private void simulateRecForRound(MatchNode match, int round, HistoryList historyList) {
        if (match == null) {
            return;
        }

        //simulate left and right child matches for the given round
        simulateRecForRound(match.getLeftMatch(), round, historyList);
        simulateRecForRound(match.getRightMatch(), round, historyList);

        //simulate current match if it is part of current round
        if (match.getRound() == round && match.getPlayer1() != null && match.getPlayer2() != null) {
            Player winner = simulateMatch(match);
            match.setWinner(winner);

            //add match to the history after determining winner
            historyList.addMatch(match);
        }

        //propagate winners to parent match
        if (match.getLeftMatch() != null && match.getLeftMatch().getWinner() != null) {
            match.setPlayer1(match.getLeftMatch().getWinner());
        }
        if (match.getRightMatch() != null && match.getRightMatch().getWinner() != null) {
            match.setPlayer2(match.getRightMatch().getWinner());
        }
    }



    //recursive method to simulate matches and determine winners
    private void simulateRec(MatchNode match) {
        //base case: if match is null, stop recursion
        if (match == null) {
            return;
        }

        //simulate left and right child matches first
        simulateRec(match.getLeftMatch());
        simulateRec(match.getRightMatch());

        //assign winners from child matches to current match
        if (match.getLeftMatch() != null && match.getLeftMatch().getWinner() != null) {
            match.setPlayer1(match.getLeftMatch().getWinner());
        }
        if (match.getRightMatch() != null && match.getRightMatch().getWinner() != null) {
            match.setPlayer2(match.getRightMatch().getWinner());
        }

        //simulate current match if both players are available
        if (match.getPlayer1() != null && match.getPlayer2() != null) {
            Player winner = simulateMatch(match);
            match.setWinner(winner);
        }
    }

    //simulate an individual match between two players
    private Player simulateMatch(MatchNode match) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Match between " + match.getPlayer1().getName() + " and " + match.getPlayer2().getName());
        System.out.println("Choose the winner: ");
        System.out.println("1. " + match.getPlayer1().getName());
        System.out.println("2. " + match.getPlayer2().getName());

        //loop until valid input
        while (true) { 
            try {
                int choice = scanner.nextInt();
                
                //newline
                scanner.nextLine(); 

                if (choice == 1) {
                    return match.getPlayer1();
                } else if (choice == 2) {
                    return match.getPlayer2();
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                
                //clear invalid input
                scanner.nextLine(); 
            }
        }
    }


    //display entire tournament bracket in a readable format
    public void displayBracket() {
        if (root == null) {
            System.out.println("No tournament bracket to display.");
            return;
        }
        displayRec(root, 0);
    }
    
    //recursive method to display matches in a tree-like structure
    private void displayRec(MatchNode match, int level) {
        if (match == null) {
            return;
        }

        //display left and right child matches first
        displayRec(match.getLeftMatch(), level + 1);
        displayRec(match.getRightMatch(), level + 1);

        //get names or display "TBD" for null players
        String player1Name = (match.getPlayer1() != null) ? match.getPlayer1().getName() : "TBD";
        String player2Name = (match.getPlayer2() != null) ? match.getPlayer2().getName() : "TBD";
        String winnerName = (match.getWinner() != null) ? match.getWinner().getName() : "TBD";

        //display match details
        System.out.println("  Match{Round: " + match.getRound() + ", " +
                           player1Name + " vs " + player2Name + ", Winner: " + winnerName + "}");
    }


}
