package model.rules;

import model.Card;
import model.Player;


/**
 * When 17 has been reached, check for Ace's. If ace's exist,
 * reduce their cost by 10.
 */
public class NewHitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  /**
   * The remake of doHit.
   */

  public boolean doHit(Player dealer) {
    int score = dealer.calcScore();
    if (score == hitLimit) {
      for (Card c : dealer.getHand()) {
        if (c.getValue() == Card.Value.Ace) {
          score -= 10;
        }
      }
    }
    return score < hitLimit;
  }
}
