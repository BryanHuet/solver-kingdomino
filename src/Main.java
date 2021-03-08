import model.Kingdomino;
import model.Player;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.Score;
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
      PutDomino p = new PutDomino(grille, d, "verticalReversed",new int[]{1,2});
      p.put();

      Domino d2 = DominoFactory.getDomino(30 ,new int[]{1,3});

      PutDomino p2 = new PutDomino(grille, d2, "vertical",new int[]{4,2});
      grille.afficheGrille();

      Domino d3 = DominoFactory.getDomino(30 ,new int[]{1,4});
      PutDomino p3 = new PutDomino(grille, d3, "horizontal",new int[]{1,4});
      grille.afficheGrille();

      Domino d4 = DominoFactory.getDomino(30 ,new int[]{1,4});
      PutDomino p4 = new PutDomino(grille, d4, "verticalReversed",new int[]{1,1});
      grille.afficheGrille();

      Domino d5 = DominoFactory.getDomino(30 ,new int[]{1,4});
      PutDomino p5 = new PutDomino(grille, d5, "horizontalReversed",new int[]{2,1});
      grille.afficheGrille();

      Domino d6 = DominoFactory.getDomino(18 ,new int[]{1,4});
      PutDomino p6 = new PutDomino(grille, d6, "vertical",new int[]{3,4});
      grille.afficheGrille();


      System.out.println("--------------------------");
      p2.put();
      grille.afficheGrille();

      System.out.println("--------------------------");
      p3.put();
      grille.afficheGrille();
      System.out.println("--------------------------");
      p4.put();
      grille.afficheGrille();
      System.out.println("--------------------------");
      p5.put();
      p6.put();
      grille.afficheGrille();

      Score s = new Score(grille);
      System.out.println(s.calculateScore());
  }
}