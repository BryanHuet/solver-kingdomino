package model.ia;

import model.Kingdomino;
import model.player.Player;
import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Expectiminimax implements Strategy {

    private Player player;
    private int depth;
    private Kingdomino game;

    public Expectiminimax(Player player, int depth, Kingdomino game) {
        this.player = player;
        this.depth = depth;
        this.game = game;

    }


    public float calcul(Node root, int depth) {
        float a = 0;
        if (root.getChild().isEmpty() || depth == 0) {
            return root.getHeuristic();
        }
        if (root.getPlayer() != this.player) {
            a = Float.POSITIVE_INFINITY;
            for (Node child : root.getChild()) {
                a = Math.min(a, calcul(child, depth - 1));
            }
        } else if (root.getPlayer() == this.player) {
            a = -Float.NEGATIVE_INFINITY;
            for (Node child : root.getChild()) {
                a = Math.max(a, calcul(child, depth - 1));
            }
        } else if (root.isChance()) {
            // ici la moyenne des distributions de dominos.
            for (Node child : root.getChild()) {
                a += (1f / root.getChild().size()) * calcul(child, depth - 1);
            }
        }
        return a;
    }

    //Moyenne -> pourcentage de chance que ce noeud apparaisse faire la moyenne de ça;
    // -->

    public void buildGraph(Node root, int depthness) {

        Player player = root.getPlayer();

        for (int i=0; i<depthness;i++) {

            for(Player other : this.game.getPlayers()){
                if (other != player){
                    for(IPut action : other.getPlateau().actionsPossible(root.getState().getGame().getPick())) {
                        State otherState = new State(this.game,other);
                        action.setGrille(otherState.getSavesGrid().get(player));
                        //action.put();
                        Node playerNode = new Node(otherState.getGame().getCurrentPlayer(), otherState);
                        root.addChild(playerNode);
                    }

                }
            }
            State pickState = new State(this.game,null);
            Node pickNode = new Node(null,pickState);
            pickNode.setChance();
            root.addChild(pickNode);
            player=null;
            root=pickNode;
        }
    }


    @Override
    public IPut resolution(ArrayList<IPut> actions) {

        IPut actionChosen = null;
        float maximum = Float.NEGATIVE_INFINITY;

        for (IPut action : this.player.getPlateau().actionsPossible(this.game.getPick())) {

            State state = new State(this.game, this.player);
            action.setGrille(state.getSavesGrid().get(this.player));
            action.put();


            Node root = new Node(state.getActualPayer(), state);
            buildGraph(root, this.depth);
            //System.out.println(root);

            float expecti = (this.calcul(root, this.depth));

            if (expecti > maximum) {
                maximum = expecti;
                actionChosen = action;
            }
        }

        if (actionChosen != null) {
            actionChosen.setGrille(this.player.getPlateau());
        }
        return actionChosen;
    }

    @Override
    public String toString() {
        return "Expectiminimax";
    }


}