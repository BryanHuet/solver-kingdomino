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

      /*
      Kingdomino game = new Kingdomino();
      Player one = new Player(1);
      Player two = new Player(2);
      game.addPlayer(one);
      game.addPlayer(two);
      game.start();


       */

      Grille grille = new Grille(5,5);
      grille.afficheGrille();
      System.out.println("-------------------------");
      Castle castle = new Castle(new int[]{2,2});
      System.out.println(castle.getPosition()[0] + "," + castle.getPosition()[1]);
      PutCastle c = new PutCastle(grille, castle);
      c.put();
      Domino d = DominoFactory.getDomino(4,new int[]{1,2});
      Domino d2 = DominoFactory.getDomino(12,new int[]{2,4});
      PutDomino p = new PutDomino(grille, d, "horizontal");
      PutDomino p2 = new PutDomino(grille, d2, "horizontal");
      p.put();
      grille.afficheGrille();
      System.out.println("Avec le domino n2 ------------------");
      p2.put();
      grille.afficheGrille();
  }
}