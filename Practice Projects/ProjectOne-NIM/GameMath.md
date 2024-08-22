# Solving Nim with Math
One of my options for the computer was to find a mathematical way to guarantee victory.
This document is my place for tracking patterns and simulating scenarios


## Overall Patterns
* Giving a single pile always loses
* Giving n piles of 1 will win if n is odd, lose if n is even
* Possible next move outcomes is the sum of current sticks
* For any 2 piles of number n where n > 1
    * If you give to an opponent
        * They take n, you take n-1 from the other side and win (they get 0, 1)
        * They take n-1, you take n from the other side and win (they get 1, 0)
        * They take any other number you match theirs on the other side and reduce to another set with equal numbers and repeat
* For any 3 piles of number n where n > 1
    * If you give to an opponent
        * Opponent wins by giving you back 2 piles of n
* for any 4 piles of number n where n > 1
#### Winning Scenarios
What happens if both players play perfectly

* If you're given 2, 3
    * You win by taking 1 from the 3 to make it a 2, 2
* If you're given 2, 2, 2
    * You win by taking 2 and giving your opponent a 2, 2
* If you're given a 1, 2, 2
  * You win by taking the 1 and giving them a 2, 2


Giving 2 piles of n:
* If you give a 2, 2
    * Your opponent takes 2, you take one and you win
    * Your opponent takes 1, you take the other 2 and you win
* If you give a 3, 3
    * Your opponent takes 3, you take 2 from the remaining and win
    * Your opponent takes 2, you take 3 from the other side and win
    * Your opponent takes 1, you take the other one to give them 2, 2 and you win
* If you give a 4, 4
    * Your opponent takes 4, you take 3 from the other side and win
    * Your opponent takes 3, you take the 4 from the other side and win
    * Your opponent takes 2, you give them 2, 2 and you win
    * Your opponent takes 1, you give them 3, 3 and you win
* If you give a 5, 5
    * Your opponent takes 5, you take 4 from the other side and win
    * Your opponent takes 4, you take 5 from the other side and win
    * Your opponent takes 3, you give them a 2, 2 and win
    * Your opponent takes 2, you give them a 3, 3 and win
    * Your opponent takes 1, you give them a 4, 4 and win

Giving 3 piles of n:
* If you give 2, 2, 2
    * Your opponent wins by taking 2, and giving you 2, 2
* If you give 3, 3, 3
    * Your opponent wins by taking 3, and giving you 3, 3

Giving 4 piles of n:
* If you give 2, 2, 2, 2
  * You win
  * If they take 2 and give you 2, 2, 2, you win by taking another 2
  * If they take 1, giving you 1, 2, 2, 2
      * You take 1 from a 2, giving them 1, 1, 2, 2
        * They take 1 from a 2, giving you 1, 1, 1, 2
          * You take the 2, giving them 1, 1, 1 (3 piles odd) you win
* If you give a 3, 3, 3, 3

At this point, simulating them becomes too unpredictable.
