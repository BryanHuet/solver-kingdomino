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

      /* Test horizontal
      // Grille et chateau
      Grille grille = new Grille(5,5);
      Castle castle = new Castle(new int[]{2,2});
      PutCastle c = new PutCastle(grille, castle);
      c.put();
      grille.afficheGrille();
      System.out.println("-------------------------");

      // Domino
      Domino d = DominoFactory.getDomino(2,new int[]{1,2});
      Domino d2 = DominoFactory.getDomino(7,new int[]{2,4});
      Domino d3 = DominoFactory.getDomino(3,new int[]{0,1});
      // Actions
      PutDomino p = new PutDomino(grille, d, "horizontal");
      PutDomino p2 = new PutDomino(grille, d2, "horizontal");
      PutDomino p3 = new PutDomino(grille, d3, "horizontal");

      // On met et affiche au fur et à mesure
      p.put();
      grille.afficheGrille();
      System.out.println("Avec le domino n2 ------------------");
      p2.put();
      System.out.println("Avec le domino n3 (même paysage en haut) ------------------");
      p3.put();
      grille.afficheGrille();

       */

      // Grille et chateau
      Grille grille = new Grille(5,5);
      Castle castle = new Castle(new int[]{2,2});
      PutCastle c = new PutCastle(grille, castle);
      c.put();
      grille.afficheGrille();
      System.out.println("-------------------------");

      Domino d = DominoFactory.getDomino(30,new int[]{1,2});
      PutDomino p = new PutDomino(grille, d, "verticalReversed");
      p.put();

      /*
      Domino d2 = DominoFactory.getDomino(30 ,new int[]{1,3});
      PutDomino p2 = new PutDomino(grille, d2, "verticalReversed");

       */

      Domino d2 = DominoFactory.getDomino(20 ,new int[]{4,2});
      PutDomino p2 = new PutDomino(grille, d2, "vertical");
      grille.afficheGrille();

      Domino d3 = DominoFactory.getDomino(30 ,new int[]{1,4});
      PutDomino p3 = new PutDomino(grille, d3, "horizontal");
      grille.afficheGrille();

      System.out.println("--------------------------");
      p2.put();
      grille.afficheGrille();

      System.out.println("--------------------------");
      p3.put();
      grille.afficheGrille();

  }
}