import model.Kingdomino;
import model.ia.Expectiminimax;
import model.ia.RandomStrategy;
import model.pieces.cases.Castle;
import model.plateau.actions.PutCastle;
import model.player.Human;
import model.player.Robot;

public class Main{
  public static void main(String[] args) {


      Kingdomino game = new Kingdomino();
      Human human = new Human(game);
      Robot robot = new Robot(game);
      robot.setStrategy(new RandomStrategy(game));
      //Robot robot2 = new Robot(game);
      //robot2.setStrategy(new RandomStrategy(game));
      Robot expecti = new Robot(game);
      expecti.setStrategy(new Expectiminimax(expecti,2,game));

      game.addPlayer(robot);
      game.addPlayer(expecti);
      //game.addPlayer(robot2);
      game.addPlayer(human);


      game.start();

/*

      Kingdomino game=new Kingdomino();

      Robot a = new Robot(game);
      game.addPlayer(a);
      a.setStrategy(new Expectiminimax(a,3,game));

      Castle castle = new Castle();
      PutCastle c = new PutCastle(a.getPlateau(), castle,new int[]{2,2});
      c.put();

      int tour=2;
      for(int i=0; i<tour;i++){
          System.out.println();
          System.out.println("===============tour "+(i+1)+"===================");
          //game.setPick(game.getDeck().pick(game.getPlayers().size()));
          System.out.println("deck:"+game.getDeck().getDominos().size()+"|"+game.getDeck().getDominos());
          System.out.println("pick:"+game.getPick());
          a.play();
          a.getPlateau().afficheGrille();
          System.out.println("score = "+a.getScore());
      }

      */

  }
}