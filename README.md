Tournament Bracket Manager - README

Project Overview

The Tournament Bracket Manager is a Java-based application designed to manage, simulate, and display a tournament bracket for a series of matches. The project utilizes core data structures and object-oriented programming principles to provide a robust and interactive system. Users can add players, remove players, simulate matches, view the current bracket, and display match history.

Features

Add Players: Users can add players to the tournament by entering their name and skill level (1–10).

Remove Players: Players can be removed from the tournament dynamically, with the bracket updating accordingly.

Simulate Matches: Matches are simulated round by round, with users selecting winners manually.

Display Bracket: The current tournament bracket can be displayed at any point.

Match History: A history of all completed matches is available for review.

Input Validation: Robust input validation ensures the application handles invalid or unexpected inputs gracefully.

Main File Structure

TournamentBracketManager
├── src
│   ├── main
│   │   └── java
│   │       ├── Main.java                # Entry point for the application
│   │       ├── Player.java              # Represents a player with name, skill level, and record
│   │       ├── MatchNode.java           # Represents a match between two players
│   │       ├── TournamentTree.java      # Manages the binary tree structure of the bracket
│   │       ├── TournamentManager.java   # Handles tournament processes and user interaction
│   │       └── HistoryList.java         # Tracks and displays match history
├── README.md                            # Documentation for the project
└── .gitignore                           # Specifies files to exclude from version control

How to Run

Prerequisites:

Java Development Kit (JDK) 17 or higher.

Running the Application:

Navigate to the project directory.

Open your terminal and move into the root directory of the project (the folder that contains the src folder)

Compile and run the program using these commands:

javac src/main/java/*.java -d bin
java -cp bin main.java.Main

Using the Application:

Follow the interactive console menu to add players, simulate matches, and view the bracket or match history.

Data Structures Used

Binary Tree (TournamentTree):

Represents the tournament bracket as a binary tree.

Each node is a match, with winners advancing to the next round.

ArrayList (HistoryList):

Tracks the history of matches played, allowing for quick retrieval and display.

Custom Objects:

Player: Stores player information and stats.

MatchNode: Represents individual matches and their relationships in the bracket.

Project Details

Classes:

1. Player

Purpose: Represents a player in the tournament.

Key Methods:

recordWin(): Increments the player's win count.

recordLoss(): Increments the player's loss count.

2. MatchNode

Purpose: Represents a single match in the tournament.

Key Attributes:

Player player1 and Player player2.

Player winner.

3. TournamentTree

Purpose: Manages the structure of the tournament bracket.

Key Features:

Recursive methods for simulating matches and displaying the bracket.

User interaction to select winners.

4. TournamentManager

Purpose: Oversees the entire tournament process, including adding/removing players and coordinating the tree.

Key Features:

Ensures a dynamic tournament structure that updates with user actions.

5. HistoryList

Purpose: Tracks and stores completed matches for review.

Key Methods:

addMatch(MatchNode match): Adds a completed match to the history.

displayHistory(): Displays all matches in the history.

Interfaces and Inheritance

Interfaces and inheritance are used to ensure modularity and scalability.

Contribution

This project was completed entirely on my own. I handled all aspects, including planning, design, implementation, and testing. I used Eclipse IDE for development and GitHub for version control and as a backup for the project files.

Future Enhancements

Graphical User Interface (GUI): Enhance usability with a visual representation of the tournament bracket.

Dynamic Bracket Sizes: Support brackets with non-power-of-two player counts using bye rounds.

Known Issues

Currently requires even player counts for proper tournament initialization.

Limited support for dynamically altering the bracket after initialization.