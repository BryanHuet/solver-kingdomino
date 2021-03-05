import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.actions.PutCastle;

public class Main{
  public static void main(String[] args) {
      Grille grille = new Grille(5, 5);
      grille.afficheGrille();

      Castle castle = new Castle(new int[] {2,2});
      Domino d = DominoFactory.getDomino( 5,new int[]{3,2});
      System.out.println("Couronnes n : " + d.getExtremiteDroite().getPaysage().getNbCouronnes() +  " |  " + d.getExtremiteGauche().getPaysage().getNbCouronnes());
      System.out.println(castle.getPosition()[0] + "," + castle.getPosition()[1]);
      PutCastle putCastle = new PutCastle(grille, castle);
      putCastle.put();
      grille.afficheGrille();

  }
}