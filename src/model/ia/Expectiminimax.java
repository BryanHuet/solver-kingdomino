package model.ia;
import model.Kingdomino;
import model.Player;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.lang.*;
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
        float a;
        if(root.getChild().isEmpty() || depth == 0){
            return root.getHeuristic();
        }
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
        }else{
            a = 0;
            for(Node child: root.getChild()) {
                a += proba(child)* calcul(child,depth-1);
            }
        }
        return a;
    }

    //Moyenne -> pourcentage de chance que ce noeud apparaisse faire la moyenne de ça;
    // -->
    public float proba(Node node) {

        switch (Score.getTheMostPaysage(node.getPlayer().getPlateau())) {
            case "wheat":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("wheat") / node.getState().getGame().getDeck().getSize();
            case "forest":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("forest") / node.getState().getGame().getDeck().getSize();
            case "water":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("water") / node.getState().getGame().getDeck().getSize();
            case "grass":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("grass") / node.getState().getGame().getDeck().getSize();
            case "swamp":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("swamp") / node.getState().getGame().getDeck().getSize();
            case "mine":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("mine") / node.getState().getGame().getDeck().getSize();
            default:
                return (float) 0.0;
        }

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

    @Override
    public IPut resolution() {
        PutDomino actionChosen = null;
        float maximum = Float.NEGATIVE_INFINITY;
        HashSet<Node> roots = new HashSet<>();
        for (PutDomino action : this.player.actionsPossible(this.game.getPick())){
            State state = new State(this.game);
            state.setActualPayer(this.player);
            action.setGrille(state.getSavesGrid().get(this.player));
            this.game.move(action);
            Node root = new Node(this.player,state);
            roots.add(root);
            buildGraph(root, this.depth);

            float expecti = (new Expectiminimax(this.player,this.depth,this.game).calcul(root,this.depth));

            if( expecti > maximum){
                maximum=expecti;
                actionChosen=action;
            }
        }

        if (actionChosen!=null){
            actionChosen.setGrille(this.player.getPlateau());
        }
        System.out.println("choix : "+actionChosen);
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