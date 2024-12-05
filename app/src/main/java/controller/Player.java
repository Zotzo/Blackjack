package controller;

import model.CardObserver;
import model.Dealer;
import model.Game;
import view.View;
import view.View.Action;


/**
* Scenario controller for playing the game.
*/
public class Player implements CardObserver {
  //i provided an instance of view instead of creating it internally in the constructor
  private View view;
  private Game game;
  

  /**
   * Constructor for this class.
   */
  public Player(View view, Game game) {
    setView(view);
    setGame(game);
    game.addDealSubcribers(this);
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Game getGame() {
    return game;
  }

  private void setView(View view) {
    this.view = view;
  }

  public View getView() {
    return view;
  }
  
  /**
  * Runs the play use case.
  *
  * @param game The game state.
  * @return True as long as the game should continue.
  */
  public boolean play(View view, Game game) {
    view.displayWelcomeMessage();
    
    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
    
    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }
    
    // A get method to retrieve the view.
    Action action = getView().getAction();
    if (action == Action.NEWGAME) {
      game.newGame();
    } else if (action == Action.HIT) {
      game.hit();
    } else if (action == Action.STAND) {
      game.stand();
    } else if (action == Action.QUIT) {
      return false;
    }
    return true;
  }

  @Override
  public void cardDrawing(model.Player player) {
    if (player instanceof Dealer) { // if is the dealer.
      view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    } else { // if its not the deal aka player.
      view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());
    }
    try {
      System.out.println("Loading.....");
      Thread.sleep(2000);
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}