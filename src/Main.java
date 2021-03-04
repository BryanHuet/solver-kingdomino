import model.plateau.Grille;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;

public class Main{
  public static void main(String[] args) {
      Grille grille = new Grille(5, 5);
      grille.afficheGrille();

      // id de domino de 0 à 47
      Domino d = DominoFactory.getDomino(47);
      System.out.println("N domino " + d.getNumeroDomino() + " Extremité gauche : " + d.getExtremiteGauche().getPaysage()
              + " Couronne : " + d.getExtremiteGauche().getCouronne() + " Extremité droite " + d.getExtremiteDroite().getPaysage() + " Nb Couronne : " + d.getExtremiteDroite().getCouronne());
  }
}