# PigSolver

Pig is a dice game for two players. The players take turns rolling a six-sided die. On each turn, the current player rolls, adding up the total of the rolls. The player can stop rolling at any time; when the current player ends the turn voluntarily then the current turn total is added to the player's score. However, if the current player rolls a 1 then the turn is over with no points gained. The first player to a predetermined target (often 100) wins the game.

Implemented a dynamic programming algorithm that computes the optimal strategy for two-player Pig. The optimal strategy specifies, for any given scores, the optimal turn total to roll until for Player 1.
