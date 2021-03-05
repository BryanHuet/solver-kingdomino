import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

public class Main{
  public static void main(String[] args) {
      Grille grille = new Grille(5, 5);
      grille.afficheGrille();
      System.out.println("-------------------------------");
      Castle castle = new Castle(new int[] {2,1});
      PutCastle putCastle = new PutCastle(grille, castle);
      putCastle.put();
      grille.afficheGrille();
      System.out.println(grille.getCastle());

  }
}