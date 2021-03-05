import model.ia.Player;
import model.ia.State;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.actions.PutCastle;

public class Main{
  public static void main(String[] args) {

      //TEST TOLGA + MITHIAN = <3
      /*Grille grille = new Grille(5, 5);
      grille.afficheGrille();

      Castle castle = new Castle(new int[] {2,2});
      Domino d = DominoFactory.getDomino( 5,new int[]{3,2});
      System.out.println("Couronnes n : " + d.getExtremiteDroite().getPaysage().getNbCouronnes() +  " |  " + d.getExtremiteGauche().getPaysage().getNbCouronnes());
      System.out.println(castle.getPosition()[0] + "," + castle.getPosition()[1]);
      PutCastle putCastle = new PutCastle(grille, castle);
      putCastle.put();
      grille.afficheGrille();*/

      State state = new State();

      Player p1 = new Player(1);
      Player p2 = new Player(2);

      state.addPlayer(p1);
      state.addPlayer(p2);

      for(Domino d : state.pick()) {
          System.out.println(d);
      }
      System.out.println(state.pick().size());
  }
}