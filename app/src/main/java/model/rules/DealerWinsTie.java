package model.rules;

import model.Player;


/**
 * Rule for when the dealer wins ties.
 */
public class DealerWinsTie implements WinStrategy {
  
  @Override
  public boolean tieCheck(Player player, Player dealer) {
    if (dealer.calcScore() == player.calcScore()) {
      return true;
    } else { 
      return false; 
    }
  }
  
}
