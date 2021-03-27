# BlackJack
This project was done as a part of Interview process for a Bangalore based company.
The aim of the project is summarized below:
* The player and the dealer receive two cards from a shuffled deck.
* After the first two cards are dealt to dealer and player, the player is asked if they'd like another card (called 'hitting'), or if they are happy with the cards they have already (called 'staying'). The object is to make the sum of your card values as close to 21, without going over. If we make 21 exactly, we have blackjack, which can't be beaten. If we go over 21, we 'bust' and we lose the round. The player is allowed to stop hitting at any point.
* The number cards (2 through 10) are worth the number displayed, face cards are worth 10, and an Ace can be worth either 1 or 11. For example, if our first two cards are a Jack and an Ace, we'd want to count the Ace as 11 since 10 + 11 = 21 and we'd have blackjack, but, if we had already had a hand worth 18, decided to hit, and got an Ace, we'd want to count it as 1, since counting it as 11 would put us at 29 and we'd bust.
* Once our hand is finished, the dealer tries to do the same. The dealer must keep hitting until they get to 17. If they get above 17 without busting, they can stay.

* If the player has blackjack, they win, unless the dealer also has blackjack, in which case the game is a tie.
* If the dealer busts and the player doesn't, the player wins.
* If the player busts, the dealer wins.
* If the player and the dealer both don't bust, whoever is closest to 21 wins.

The assumptions I have taken are:
1. The choice of aither hit/stay by a player is being compromised to hit always.
2. The choice of Ace value is not asked to player instead the best value which is less than or equal to 21 is taken.
3. Due to less time animations were not done.
