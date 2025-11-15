package main.java;

//main.java
//entry point for the tournament bracket manager application
//provides a console menu for managing players, simulating matches, and viewing the bracket

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //welcome message
        System.out.println("Welcome to the Tournament Bracket Manager!");

        //initialize tournament manager to handle players and brackets
        TournamentManager tournamentManager = new TournamentManager();
        
        //initialize history list to track matches
        HistoryList historyList = new HistoryList();

        //boolean to keep the program running
        boolean running = true;

        while (running) {
        	
            // display main menu
            System.out.println("\nTournament Bracket Manager");
            System.out.println("--------------------------");
            System.out.println("1. Insert a player");
            System.out.println("2. Remove a player");
            System.out.println("3. Input Match Results");
            System.out.println("4. Display current bracket");
            System.out.println("5. Display match history");
            System.out.println("6. Exit");
            System.out.print("\nPlease choose an option: ");

            try {
            	
                //get user input for menu choice
                int choice = scanner.nextInt();
                
                //newline
                scanner.nextLine();

                switch (choice) {
                	case 1 -> {
                    System.out.print("Enter the player's name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter the player's skill level (1-10): ");
                    try {
                        int skillLevel = scanner.nextInt();
                        
                        //newline
                        scanner.nextLine(); 

                        //validate and add player
                        Player newPlayer = new Player(name, skillLevel);
                        tournamentManager.addPlayer(newPlayer);
                        System.out.println(name + " added to the player list.");

                        //check if tournament can be initialized
                        if (tournamentManager.getPlayers().size() >= 2 && tournamentManager.getPlayers().size() % 2 == 0) {
                            tournamentManager.initializeTournamentTree();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid skill level: " + e.getMessage());
                        
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 10 for skill level.");
                        
                        //clear invalid input
                        scanner.nextLine();
                    	}
                	}

                    case 2 -> {
                    	
                        //remove a player
                        System.out.print("Enter the name of the player to remove: ");
                        String nameToRemove = scanner.nextLine();

                        Player playerToRemove = tournamentManager.findPlayerByName(nameToRemove);

                        if (playerToRemove != null) {
                            tournamentManager.removePlayer(playerToRemove);
                            System.out.println(nameToRemove + " removed from the tournament.");
                        } else {
                            System.out.println("Player not found in the player list.");
                        }
                    }
                    case 3 -> {
                    	
                        //input match results
                        if (tournamentManager.getTournamentTree() == null) {
                            System.out.println("No tournament tree exists to simulate.");
                            continue;
                        }

                        //input match results and update history
                        tournamentManager.simulateTournament(historyList);

                        //check tournament's root match for winner
                        MatchNode rootMatch = tournamentManager.getTournamentTree().getRoot();
                        if (rootMatch.getWinner() != null) {
                            System.out.println("The champion is: " + rootMatch.getWinner().getName());
                        } else {
                            System.out.println("No winner determined.");
                        }
                    }

                    case 4 -> {
                        //display current tournament bracket
                        if (tournamentManager.getTournamentTree() != null) {
                            System.out.println("Displaying the tournament bracket:");
                            tournamentManager.displayBracket();
                        } else {
                            System.out.println("No tournament bracket to display. Add more players to initialize the tournament.");
                        }
                    }
                    case 5 -> {
                    	
                        //display match history
                        System.out.println("Match history:");
                        historyList.displayHistory();
                    }
                    case 6 -> {
                    	
                        //exit program
                        running = false;
                        System.out.println("Exiting Tournament Bracket Manager. Goodbye!");
                    }
                    default -> {
                        //handle invalid menu choices
                        System.out.println("Invalid input");
                    }
                }
            } catch (java.util.InputMismatchException e) {
                //handle invalid input (non-integer)
                System.out.println("Invalid input");
                
                //clear  invalid input
                scanner.nextLine(); 
            }
        }

        //close scanner to free resources
        scanner.close();
    }
}
