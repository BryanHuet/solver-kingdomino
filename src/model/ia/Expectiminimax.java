package model.ia;
import model.Kingdomino;
import model.player.Nature;
import model.player.Player;
import model.pieces.domino.Domino;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Expectiminimax implements Strategy{

    private Player player;
    private int depth;
    private Kingdomino game;

    public Expectiminimax(Player player, int depth, Kingdomino game){
        this.player = player;
        this.depth = depth;
        this.game = game;
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

        HashMap<Node,Float> nodes = new HashMap<>();
        //parcours de tout les dominos présent dans le deck et création de tout les noeuds possibles.
        for (int i = 0; i < this.player.getPlateau().getNbLigne(); i++) {
            for (int j = 0; j < this.player.getPlateau().getNbColonne(); j++) {
                for (Domino d : this.game.getDeck().getDominos()) {
                    PutDomino actionh = new PutDomino(this.player.getPlateau(), d, "horizontal", new int[]{i, j});
                    PutDomino actionhr = new PutDomino(this.player.getPlateau(), d, "horizontalReversed", new int[]{i, j});
                    PutDomino actionv = new PutDomino(this.player.getPlateau(), d, "vertical", new int[]{i, j});
                    PutDomino actionvr = new PutDomino(this.player.getPlateau(), d, "verticalReversed", new int[]{i, j});
                    if (actionh.isValid()) {
                        State stateh = new State(game);
                        actionh.setGrille(stateh.getSavesGrid().get(this.player));
                        stateh.getGame().move(actionh);
                        nodes.put(new Node(this.player, stateh),1/(float)this.game.getDeck().getDominos().size());
                    }
                    if (actionv.isValid()) {
                        State statev = new State(game);
                        actionv.setGrille(statev.getSavesGrid().get(this.player));
                        statev.getGame().move(actionv);
                        nodes.put(new Node(this.player, statev),1/(float)this.game.getDeck().getDominos().size());
                    }
                    if (actionhr.isValid()) {
                        State statehr = new State(game);
                        actionhr.setGrille(statehr.getSavesGrid().get(this.player));
                        statehr.getGame().move(actionhr);
                        nodes.put(new Node(this.player, statehr),1/(float)this.game.getDeck().getDominos().size());
                    }
                    if (actionvr.isValid()) {
                        State statevr = new State(game);
                        actionvr.setGrille(statevr.getSavesGrid().get(this.player));
                        statevr.getGame().move(actionvr);
                        nodes.put(new Node(this.player, statevr),1/(float)this.game.getDeck().getDominos().size());
                    }
                }
            }
        }
        return nodes.getOrDefault(node, 0f);
    }

    public void buildGraph(Node root, int depthness) {

        if (depthness > 1) {
            Player parentPlayer = root.getPlayer();
            if (parentPlayer.getId() != 0) {
                for (IPut action : this.player.actionsPossible(root.getState().getGame().getPick())) {
                    State state = new State(game);
                    state.setActualPayer(this.player);
                    action.setGrille(state.getSavesGrid().get(state.getActualPayer()));
                    state.getGame().move((PutDomino) action);
                    state.getGame().nextPlayer();
                    Node childMin = new Node(state.getActualPayer(), state);
                    root.addChild(childMin);
                    this.buildGraph(childMin, depthness - 1);
                }
            } else {
                for (Domino domino : root.getState().getDeck().getDominos()) {
                    for (int i = 0; i < this.player.getPlateau().getNbLigne(); i++) {
                        for (int j = 0; j < this.player.getPlateau().getNbColonne(); j++) {
                            for (Domino d : this.game.getDeck().getDominos()) {
                                PutDomino actionh = new PutDomino(this.player.getPlateau(), d, "horizontal", new int[]{i, j});
                                PutDomino actionhr = new PutDomino(this.player.getPlateau(), d, "horizontalReversed", new int[]{i, j});
                                PutDomino actionv = new PutDomino(this.player.getPlateau(), d, "vertical", new int[]{i, j});
                                PutDomino actionvr = new PutDomino(this.player.getPlateau(), d, "verticalReversed", new int[]{i, j});
                                if (actionh.isValid()) {
                                    State stateh = new State(game);
                                    actionh.setGrille(stateh.getSavesGrid().get(this.player));
                                    stateh.getGame().move(actionh);
                                    stateh.getGame().nextPlayer();
                                    Node nodeh = new Node(this.player, stateh);

                                    System.out.println(d + " "+actionh);
                                    root.addChild(nodeh);
                                }
                                if (actionv.isValid()) {
                                    State statev = new State(game);
                                    actionv.setGrille(statev.getSavesGrid().get(this.player));
                                    statev.getGame().move(actionv);
                                    statev.getGame().nextPlayer();
                                    Node nodev = new Node(this.player, statev);

                                    System.out.println(d + " "+actionv);
                                    root.addChild(nodev);
                                }
                                if (actionhr.isValid()) {
                                    State statehr = new State(game);
                                    actionhr.setGrille(statehr.getSavesGrid().get(this.player));
                                    statehr.getGame().move(actionhr);
                                    statehr.getGame().nextPlayer();
                                    Node nodehr = new Node(this.player,statehr);
                                    System.out.println(d + " "+actionhr);
                                    root.addChild(nodehr);
                                }
                                if (actionvr.isValid()) {
                                    State statevr = new State(game);
                                    actionvr.setGrille(statevr.getSavesGrid().get(this.player));
                                    statevr.getGame().move(actionvr);
                                    statevr.getGame().nextPlayer();
                                    Node nodevr=new Node(this.player, statevr);

                                    System.out.println(d + " "+actionvr);
                                    root.addChild(nodevr);
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    public IPut resolution(ArrayList<IPut> actions) {
        int cpmt=0;
        PutDomino actionChosen = null;
        float maximum = Float.NEGATIVE_INFINITY;
        HashSet<Node> roots = new HashSet<>();
        for (IPut action : this.player.actionsPossible(this.game.getPick())){
            State state = new State(this.game);
            state.setActualPayer(this.player);
            action.setGrille(state.getSavesGrid().get(this.player));
            this.game.move((PutDomino) action);
            this.game.nextPlayer();
            Node root = new Node(this.player,state);
            roots.add(root);
            buildGraph(root, this.depth);

            //System.out.println(root);
            float expecti = (new Expectiminimax(this.player,this.depth,this.game).calcul(root,this.depth));

            if( expecti > maximum){
                maximum=expecti;
                actionChosen= (PutDomino) action;
            }
        }

        if (actionChosen!=null){
            actionChosen.setGrille(this.player.getPlateau());
        }
        return actionChosen;
    }



}

/*function expectiminimax(node, depth)
    if node is a terminal node or depth = 0
        return the heuristic value of node
    if the adversary is to play at node
        // Return value of minimum-valued child node
        let α := +∞
        foreach child of node
            α := min(α, expectiminimax(child, depth-1))
    else if we are to play at node
        // Return value of maximum-valued child node
        let α := -∞
        foreach child of node
            α := max(α, expectiminimax(child, depth-1))
    else if random event at node
        // Return weighted average of all child nodes' values
        let α := 0
        foreach child of node
            α := α + (Probability[child] × expectiminimax(child, depth-1))
    return α

    tree p1 :               p1
                            /\
                       pick 1 pick 2
                         |      |
                  p2 pick 2   p2 pick 1
                        \       /
                         nature
                      ||||||||||||| nb de cartes restante dans le deck

    Square : 96
    -> champs : 26/96 ->27%
    -> forêt : 22/96 -> 23%
    -> eau : 18/96 -> 19%
    -> herbe : 14/96 -> 15%
    -> marais : 10/96 -> 10%
    -> mine : 6/96 -> 6%

 */