# MANCALA - TERMINAL GAME

A java program that uses abstraction, interface, and polymorphism to deploy a mancala game program on the terminal.

## Description

This assignment has one abstract class, two interfaces, and various classes to create a mancala program. 
This mancala board allows two different rule sets, and also has a graphic user interface for the user to use. 
GameRules is an abstract class that represents the rules of the game, and AyoRules and KalahRules extend GameRules as they initiate the moves based on their own specific rule sets. 
MancalaDataStructure represents the board of the game, which is made up of Pit and Store-- who implement the interface Countable. 
Then, MancalaGame represents the game as a whole and the actions that may be done!

### Dependencies
* Must have java 17.0.7 or later installed
* An IDE or terminal to install and run the program in

### Executing program
* Run gradle build
```
gradle build
```
* Run gradle echo
```
To run the program from jar:
java -jar build/libs/Mancala.jar
To run the program from class files:
java -cp build/classes/java/main ui.TextUI

```
* Copy and paste wanted command!
```
//For TextUI
java -cp build/classes/java/main ui.TextUI
```

* Expected output for TextUI:
```
=== Welcome to Mancala! ===
Choose 1 for Kalah, 2 for Ayoayo:
(User input)
Enter player one's name:
P1
Enter player two's name:
P2

Game Board:
                                                === Mancala Board ===
-------------------------------------------------Top Row (P2 Pits):-------------------------------------------------
                Pit 12:         Pit 11:         Pit 10:         Pit 9:          Pit 8:          Pit 7:
                4               4               4               4               4               4
P1's Store                                                                                            P2's Store
0                                                                                                               0
                Pit 1:          Pit 2:          Pit 3:          Pit 4:          Pit 5:          Pit 6:
                4               4               4               4               4               4
-----------------------------------------------Bottom Row (P1 Pits):-------------------------------------------------

Current Player: P1

Enter the pit number (1-6 for Player 1, 7-12 for Player 2):
```

## Limitations

What isn't done?
* Proper game serialization + Player serialization.

What things cause errors?
* Pit inputs that are out of bounds due to stopping pit calculations

## Author Information

* Name: Lyna Tran

## Acknowledgments
Dr. Judi McCuaig for providing each class javadoc and code in:
* Countable.java
* GameRules.java
* MancalaDataStructure.java
