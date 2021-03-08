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

    public PutDomino playAi(){
        PutDomino actionChosen = null;
        float maximum = -100000000;
        HashSet<Node> roots = new HashSet<>();
        for (PutDomino action : this.actionsPossible(game.getPick())){
            State state = new State(game);
            state.setActualPayer(this);
            action.setGrille(state.getSavesGrid().get(this));
            action.put();
            Node root = new Node(state.getActualPayer(),state);
            roots.add(root);
            float expecti = (new Expectiminimax(root.getPlayer()).calcul(root,1));
            //System.out.println("expecti - "+expecti);
            //System.out.println(expecti);
            //state.getActualPayer().getPlateau().afficheGrille();
            if( expecti > maximum){
                maximum=expecti;
                actionChosen=action;
            }
        }
        assert actionChosen != null;
        actionChosen.setGrille(this.getPlateau());
        System.out.println(actionChosen);
        maximum=-1000f;
        return actionChosen;
    }


    public static void main(String[] args){

        Kingdomino game=new Kingdomino();
        Robot a = new Robot(4,game,1);
        game.addPlayer(a);
        Castle castle = new Castle();
        PutCastle c = new PutCastle(a.getPlateau(), castle,new int[]{2,2});
        c.put();
        game.pick();
        System.out.println(game.getPick());
        IPut action = a.playAi();
        a.play(action);
        a.getPlateau().afficheGrille();
        game.pick();
        System.out.println(game.getPick());
        IPut action2 = a.playAi();
        a.play(action2);
        a.getPlateau().afficheGrille();
        game.pick();
        System.out.println(game.getPick());
        IPut action3 = a.playAi();
        a.play(action3);
        a.getPlateau().afficheGrille();
        game.pick();
        System.out.println(game.getPick());
        IPut action4 = a.playAi();
        a.play(action4);
        a.getPlateau().afficheGrille();
        game.pick();
        System.out.println(game.getPick());
        IPut action5 = a.playAi();
        a.play(action5);
        a.getPlateau().afficheGrille();


    }

}
