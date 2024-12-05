package model.rules;

import model.Player;

/**
 * New win strategy.
 */

public interface WinStrategy {

  /**
   * Checks if there's a tie.
   */

  boolean tieCheck(Player player, Player dealer);
  
}
