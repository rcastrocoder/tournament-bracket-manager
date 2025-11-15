package main.java;

//matchnode.java
//represents a match between two players in the tournament
//includes players, winner, and the round of the tournament

public class MatchNode {

    //first player in match
    private Player player1;

    //second player in match
    private Player player2;

    //winner of match
    private Player winner;

    //round of  match (quarterfinal, semifinal, etc.)
    private int round;

     //left child match for player1
     private MatchNode leftMatch;

     //right child match for player2
     private MatchNode rightMatch;

    //constructor to initialize a match with two players and round number
    public MatchNode(Player player1, Player player2, int round) {
        this.player1 = player1;
        this.player2 = player2;
        this.round = round;

        //winner is unknown at the start
        this.winner = null;

        //child matches are initially null
        this.leftMatch = null;
        this.rightMatch = null;
    }

    //getter for leftmatch
    public MatchNode getLeftMatch() {
        return leftMatch;
    }

    //setter for leftmatch
    public void setLeftMatch(MatchNode leftMatch) {
        this.leftMatch = leftMatch;
    }

    //getter for rightmatch
    public MatchNode getRightMatch() {
        return rightMatch;
    }
    
    //setter for rightmatch
    public void setRightMatch(MatchNode rightMatch) {
        this.rightMatch = rightMatch;
    }

    //set player1 for this match
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    //set player2 for this match
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    //get first player
    public Player getPlayer1() {
        return player1;
    }

    //get second player
    public Player getPlayer2() {
        return player2;
    }

    //get winner of match
    public Player getWinner() {
        return winner;
    }

    //set winner of match
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    //get round number
    public int getRound() {
        return round;
    }

    //convert match details to string
    @Override
    public String toString() {
        String player1Name = (player1 != null) ? player1.getName() : "TBD";
        String player2Name = (player2 != null) ? player2.getName() : "TBD";
        String winnerName = (winner != null) ? winner.getName() : "TBD";

        return "Match{Round: " + round + ", Player 1: " + player1Name +
               " vs Player 2: " + player2Name + ", Winner: " + winnerName + "}";
    }
    
}
