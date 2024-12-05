package model.rules;

import model.Card;
import model.Dealer;
import model.Deck;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Deck deck, Dealer dealer, Player player) {

    dealer.dealCard(player, true);

    dealer.dealCard(dealer, true);

    dealer.dealCard(player, true);

    return true;
  }
}