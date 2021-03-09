package model;

import model.ia.Expectiminimax;
import model.ia.Node;
import model.ia.State;
import model.pieces.Castle;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

import java.util.HashMap;
import java.util.HashSet;

import static java.lang.Math.exp;
import static java.lang.Math.max;

public class Robot extends Player{

    private Expectiminimax strategy;
    private Kingdomino game;
    private int depth;

    public Robot(int id, Kingdomino game, int depth) {
        super(id);
        this.game=game;
        this.strategy=new Expectiminimax(this);
        this.depth = depth;
    }


    public void buildGraph(Node root, int depth) {
        Player actual = null;
        for (Player p : root.getState().getGame().getPlayers()) {
            if (p != root.getPlayer()) {
                actual=p;
            }
        }

        if (depth > 1) {
            //System.out.println(actual.actionsPossible(root.getState().getGame().getPick()));
            //System.out.println("chui la");
            for (PutDomino action : actual.actionsPossible(root.getState().getGame().getPick())) {
                State state = new State(game);
                state.setActualPayer(actual);
                action.setGrille(state.getSavesGrid().get(state.getActualPayer()));
                state.getGame().move(action);
                Node childMin = new Node(state.getActualPayer(), state);
                root.addChild(childMin);
                this.buildGraph(childMin, depth - 1);
            }

        }
    }


    public PutDomino playAi(){
        PutDomino actionChosen = null;
        float maximum = -100000000;
        HashSet<Node> roots = new HashSet<>();
        for (PutDomino action : this.actionsPossible(game.getPick())){
            State state = new State(game);
            state.setActualPayer(this);
            action.setGrille(state.getSavesGrid().get(this));
            state.getGame().move(action);
            Node root = new Node(state.getActualPayer(),state);
            roots.add(root);
            buildGraph(root, this.depth);
            float expecti = (new Expectiminimax(root.getPlayer()).calcul(root,1));
            if( expecti > maximum){
                maximum=expecti;
                actionChosen=action;
            }
        }

        if (actionChosen!=null){
         actionChosen.setGrille(this.getPlateau());
        }
        System.out.println("choix : "+actionChosen);
        return actionChosen;
    }


    public static void main(String[] args){

        Kingdomino game=new Kingdomino();
        Robot a = new Robot(4,game,2);
        Robot b = new Robot(6,game,1);
        game.addPlayer(a);
        game.addPlayer(b);
        Castle castle = new Castle();
        PutCastle c = new PutCastle(a.getPlateau(), castle,new int[]{2,2});
        c.put();
        Castle castle2 = new Castle();
        PutCastle c2 = new PutCastle(b.getPlateau(), castle,new int[]{2,2});
        c2.put();

        int tour=10;
        for(int i=0; i<tour;i++){
            System.out.println();
            System.out.println("===============tour "+(i+1)+"===================");
            game.pick();
            System.out.println("la pioche : "+game.getPick());
            IPut action = a.playAi();
            if(!a.getCanPlay()){
                break;
            }
            a.play(action);
            a.getPlateau().afficheGrille();
            System.out.println("score a = "+a.getScore());
            IPut action2 = b.playAi();
            if(!b.getCanPlay()){
                break;
            }
            b.play(action2);
            b.getPlateau().afficheGrille();
            System.out.println("score b = "+b.getScore());
        }

        State state = new State(game);
        Node root = new Node(a,state);
        //a.buildGraph(root,3);


    }

}
