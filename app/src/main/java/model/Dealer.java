package model;

import java.util.ArrayList;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;
import model.rules.WinStrategy;

/**
* Represents a dealer player that handles the deck of cards and runs the game using rules.
*/
public class Dealer extends Player {
  
  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinStrategy winRule;
  private ArrayList<CardObserver> observers;
  
  /**
  * Initializing constructor.
  *
  * @param rulesFactory A factory that creates the rules to use.
  */
  public Dealer(RulesFactory rulesFactory) {
    
    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.newHit();
    winRule = rulesFactory.getWinStrategy();
    observers = new ArrayList<>();

  }
  
  /**
  * Starts a new game if the game is not currently under way.
  *
  * @param player The player to play agains.
  * @return True if the game could be started.
  */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(deck, this, player);
    }
    return false;
  }
  
  /**
  * Gives the player one more card if possible. I.e. the player hits.
  *
  * @param player The player to give a card to.
  * @return true if the player could get a new card, false otherwise.
  */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      dealCard(player, true);      
      return true;
    }
    return false;
  }
  
  /**
  * Checks if the dealer is the winner compared to a player.
  *
  * @param player The player to check agains.
  * @return True if the dealer is the winner, false if the player is the winner.
  */
  public boolean isDealerWinner(Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (calcScore() > maxScore) {
      return false;
    } else if (winRule.tieCheck(player, this) == true) {
      return true;
    }
    return calcScore() >= player.calcScore();
  }
  
  /**
  * Checks if the game is over, i.e. the dealer can take no more cards.
  *
  * @return True if the game is over.
  */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }
  
  /**
  * The player has choosen to take no more cards, it is the dealers turn.
  */
  public boolean stand() {
    if (deck != null) {
      showHand();
      while (hitRule.doHit(this) == true) {
        dealCard(this, true);
      }
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * card dealing. 
   *
   * @param player being delt a card.
   * @param show if card is visiable or not.
   */
  public void dealCard(Player player, boolean show) {
    Card.Mutable c = deck.getCard();
    c.show(show);
    player.dealCard(c);
    notifyObservers(player);
  }

  /**
   * Remove an observer. 
   *
   * @param c being obersvered.
   */
  public void removeObserver(CardObserver c) {
    observers.remove(c);
  }

  /**
   * Adds Observer. 
   *
   * @param c bing observerd.
   */
  public void addObserver(CardObserver c) {
    observers.add(c);
  }

  /**
   * Updates the obervers that a card has been drawn and who drew it. 
   *
   * @param player the object that is drawing the card.
   */
  void notifyObservers(Player player) {
    for (CardObserver c : observers) {
      c.cardDrawing(player);
    }
  }


  
  
  
}