package model.ia;
import model.Kingdomino;
import model.player.Nature;
import model.player.Player;
import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Expectiminimax implements Strategy{

    private Player player;
    private int depth;
    private Kingdomino game;
    private HashMap<Domino, ArrayList<Node>> savesDos;

    public Expectiminimax(Player player, int depth, Kingdomino game){
        this.player = player;
        this.depth = depth;
        this.game = game;
        this.savesDos = new HashMap<>();
        this.buildSaves();

    }


    public float calcul(Node root,int depth){
        float a=0;
        if(root.getChild().isEmpty() || depth == 0){
            return root.getHeuristic();
        }
        if(! (root.getPlayer() instanceof Nature))
          {
            if(root.getPlayer()!=this.player){
                a=Float.POSITIVE_INFINITY;
                for(Node child: root.getChild()) {
                    a = Math.min(a, calcul(child,depth-1));
                }
            }else if(root.getPlayer()==this.player){
                a=-Float.NEGATIVE_INFINITY;
                for(Node child: root.getChild()) {
                    a = Math.max(a, calcul(child,depth-1));
                }
            }
        }else{
            for(Node child: root.getChild()) {
                a += /*proba(child)* */ calcul(child,depth-1);
            }
        }
        return a;
    }

    //Moyenne -> pourcentage de chance que ce noeud apparaisse faire la moyenne de ça;
    // -->
    public float proba(Node node) {
        Float average = 0f;
        ArrayList<Integer> values = new ArrayList<>();
        for (Domino d : node.getState().getGame().getDeck().getDominos()){
            for (Node newNode : this.savesDos.get(d)){
                values.add(newNode.getHeuristic());
            }
        }
        int value=0;
        for (Integer i : values){
            value+=i;
        }

        return (float) (value/values.size());
    }

    public void buildGraph(Node root, int depthness) {
        Player player = root.getPlayer();
        if(depthness>1){
            if(player instanceof Nature) {
                State natureGame = root.getState();
                player.play();
                natureGame.nextPlayer();
                Node natureNode = new Node(natureGame.getGame().getCurrentPlayer(),natureGame);
                root.addChild(natureNode);
                buildGraph(natureNode,depthness-1);
            } else {
                for (IPut action : player.actionsPossible(root.getState().getGame().getPick())) {
                    State playerState = new State(this.game,this.player);
                    action.setGrille(playerState.getSavesGrid().get(player));
                    playerState.nextPlayer();
                    Node playerNode = new Node(playerState.getGame().getCurrentPlayer(),playerState);
                    root.addChild(playerNode);
                    buildGraph(playerNode,depthness-1);
                }
            }
        }

    }


    private void buildSaves(){

        for (Domino d : game.getDeck().getDominos()) {
            ArrayList<Node> nodes = new ArrayList<>();
            for (int i = 0; i < this.player.getPlateau().getNbLigne(); i++) {
                for (int j = 0; j < this.player.getPlateau().getNbColonne(); j++) {
                    PutDomino actionh = new PutDomino(this.player.getPlateau(), d, "horizontal", new int[]{i, j});
                    PutDomino actionhr = new PutDomino(this.player.getPlateau(), d, "horizontalReversed", new int[]{i, j});
                    PutDomino actionv = new PutDomino(this.player.getPlateau(), d, "vertical", new int[]{i, j});
                    PutDomino actionvr = new PutDomino(this.player.getPlateau(), d, "verticalReversed", new int[]{i, j});
                    if (actionh.isValid()) {
                        State stateh = new State(game,this.player);
                        actionh.setGrille(stateh.getSavesGrid().get(this.player));
                        stateh.getGame().move(actionh);
                        nodes.add(new Node(this.player, stateh));
                    }
                    if (actionv.isValid()) {
                        State statev = new State(game,this.player);
                        actionv.setGrille(statev.getSavesGrid().get(this.player));
                        statev.getGame().move(actionv);
                        nodes.add(new Node(this.player, statev));
                    }
                    if (actionhr.isValid()) {
                        State statehr = new State(game,this.player);
                        actionhr.setGrille(statehr.getSavesGrid().get(this.player));
                        statehr.getGame().move(actionhr);
                        nodes.add(new Node(this.player, statehr));
                    }
                    if (actionvr.isValid()) {
                        State statevr = new State(game,this.player);
                        actionvr.setGrille(statevr.getSavesGrid().get(this.player));
                        statevr.getGame().move(actionvr);
                        nodes.add(new Node(this.player, statevr));
                    }
                }
            }
            this.savesDos.put(d,nodes);
        }
    }


    @Override
    public IPut resolution(ArrayList<IPut> actions) {

        IPut actionChosen = null;
        float maximum = Float.NEGATIVE_INFINITY;

        for (IPut action : this.player.actionsPossible(this.game.getPick())){

            State state = new State(this.game,this.player);
            state.nextPlayer();
            action.setGrille(state.getSavesGrid().get(this.player));
            this.game.move(action);


            Node root = new Node(state.getActualPayer(),state);
            buildGraph(root, this.depth);
            //System.out.println(root);

            float expecti = (this.calcul(root,this.depth));

            if( expecti > maximum){
                maximum=expecti;
                actionChosen= action;
            }
        }

        if (actionChosen!=null){
            actionChosen.setGrille(this.player.getPlateau());
        }
        return actionChosen;
    }

    @Override
    public String toString(){
        return "Expectiminimax";
    }


}