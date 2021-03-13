package model;

import model.ia.Expectiminimax;
import model.ia.Node;
import model.ia.State;
import model.ia.Strategy;
import model.pieces.cases.Castle;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;

public class Robot extends Player{

    private Kingdomino game;
    private int depth;

    public Robot(int id, Kingdomino game, int depth) {
        super(id);
        this.game=game;
        this.depth = depth;
    }

    public IPut playAi(Strategy strategy){
        return strategy.resolution();
    }


    public static void main(String[] args){

        Kingdomino game=new Kingdomino();
        Robot a = new Robot(4,game,2);
        Robot b = new Robot(6,game,1);
        game.addPlayer(a);
        //game.addPlayer(b);
        Castle castle = new Castle();
        PutCastle c = new PutCastle(a.getPlateau(), castle,new int[]{2,2});
        c.put();
        /*
        Castle castle2 = new Castle();
        PutCastle c2 = new PutCastle(b.getPlateau(), castle,new int[]{2,2});
        c2.put();*/

        int tour=10;
        for(int i=0; i<tour;i++){
            System.out.println();
            System.out.println("===============tour "+(i+1)+"===================");
            game.pick();
            System.out.println("la pioche : "+game.getPick());
            IPut action = a.playAi(new Expectiminimax(a,1,game));
            if(!a.getCanPlay()){
                break;
            }
            a.play(action);
            a.getPlateau().afficheGrille();
            System.out.println("score a = "+a.getScore());
            /*
            IPut action2 = b.playAi(new Expectiminimax(b,1,game));
            if(!b.getCanPlay()){
                break;
            }
            b.play(action2);
            System.out.println("la pioche : "+game.getPick());
            b.getPlateau().afficheGrille();
            System.out.println("score b = "+b.getScore());*/
        }

        State state = new State(game);
        Node root = new Node(a,state);
        //a.buildGraph(root,3);


    }

}
