package model.rules;

/**
 * Creates concrete rules.
 */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.

   * @return The rule to use
   */
  public NewHitStrategy newHit() {
    return new NewHitStrategy();
  }

  public BasicHitStrategy basicHit() {
    return new BasicHitStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.

   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }

  public WinStrategy getWinStrategy() {
    return new DealerWinsTie();
  }
}