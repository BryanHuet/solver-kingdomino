import model.Kingdomino;
import model.Player;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

public class Main{
  public static void main(String[] args) {

      Kingdomino game = new Kingdomino();
      Player one = new Player(1);
      Player two = new Player(2);
      game.addPlayer(one);
      game.addPlayer(two);
      game.start();

  }
}