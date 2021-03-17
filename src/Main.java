import model.Kingdomino;
import model.ia.Expectiminimax;
import model.ia.Node;
import model.ia.Random;
import model.ia.State;
import model.pieces.cases.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.Grille;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;
import model.player.Human;
import model.player.Robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main{
  public static void main(String[] args) {


      Kingdomino game = new Kingdomino();
      Robot robot = new Robot(game);
      robot.setStrategy(new Random(game));
      Human human = new Human(game);
      game.addPlayer(human);
      game.addPlayer(robot);
      //parcours de tout les dominos présent dans le deck et création de tout les noeuds possibles.


      game.start();

/*

      Kingdomino game=new Kingdomino();

      Robot a = new Robot(game);
      game.addPlayer(a);
      a.setStrategy(new Expectiminimax(a,3,game));

      Castle castle = new Castle();
      PutCastle c = new PutCastle(a.getPlateau(), castle,new int[]{2,2});
      c.put();

      int tour=5;
      for(int i=0; i<tour;i++){
          System.out.println();
          System.out.println("===============tour "+(i+1)+"===================");
          game.pick();
          System.out.println(game.getPick());
          a.play();
          a.getPlateau().afficheGrille();
          System.out.println("score = "+a.getScore());
      }
*/
  }
}