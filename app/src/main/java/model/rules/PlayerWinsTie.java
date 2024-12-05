package model.rules;

import model.Player;

/**
 * Rule for when the player wins ties.
 */

public class PlayerWinsTie implements WinStrategy {
  
  @Override
  public boolean tieCheck(Player player, Player dealer) {
    if (player.calcScore() == dealer.calcScore()) {
      return true;
    } else { 
      return false; 
    }
  }
  
}
