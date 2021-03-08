package model.plateau;

import model.pieces.Case;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Score {

    private Grille grille;

    public Score(Grille grille) {
        this.grille = grille;
    }


    // On calcule le score via un HashMap des noms en key et du multiplicateur (nombre de couronnes en tout)
    /*
    public static int calculateScore(Grille grille) {
        int score = 0;
        HashMap<String, Integer> crowns = countCrowns(grille);
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                if (crowns.containsKey(grille.getGrille()[i][j].getPaysage().getName())) {
                    score += crowns.get(grille.getGrille()[i][j].getPaysage().getName());
                }
            }
        }
        return score;
    }

    // On calcule les couronnes d'abord pour avoir les noms de paysages et leur multiplicateur.
    public static HashMap<String, Integer> countCrowns(Grille grille) {
        HashMap<String, Integer> scoreCounter = new HashMap<String, Integer>();
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                // Si la key existe déjà, on update sa valeur en l'incrémentant par le nb de couronne.
                if ((scoreCounter.containsKey(grille.getGrille()[i][j].getPaysage().getName())) && grille.getGrille()[i][j].getPaysage().getNbCouronnes() > 0) {
                    scoreCounter.put(grille.getGrille()[i][j].getPaysage().getName(), scoreCounter.get(grille.getGrille()[i][j].getPaysage().getName()) + grille.getGrille()[i][j].getPaysage().getNbCouronnes());
                } // cas où ca n'existe pas pour initialiser le HashMap.
                else if (grille.getGrille()[i][j].getPaysage().getNbCouronnes() > 0) {
                    scoreCounter.put(grille.getGrille()[i][j].getPaysage().getName(), grille.getGrille()[i][j].getPaysage().getNbCouronnes());
                }
            }
        }
        return scoreCounter;
    }

     */

    public static String getTheMostPaysage(Grille grille){
        HashMap<String, Integer> paysageCounter = new HashMap<>();
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                paysageCounter.put(grille.getGrille()[i][j].getPaysage().getName(),0);
            }
        }
        for (int i = 0; i < grille.getNbLigne(); i++) {
            for (int j = 0; j < grille.getNbColonne(); j++) {
                paysageCounter.put(grille.getGrille()[i][j].getPaysage().getName(),paysageCounter.get(grille.getGrille()[i][j].getPaysage().getName())+1);
            }
        }
        String most="null";
        int plus=0;
        for (Map.Entry<String, Integer> entry : paysageCounter.entrySet()) {
            if(!entry.getKey().equals("vide")){
                if(entry.getValue()>plus){
                    plus=entry.getValue();
                    most=entry.getKey();
                }
            }
        }
        return most;
    }


    public int calculateAreaScore(int x, int y, ArrayList<Case> verifiedCases, ArrayList<Case> area)
    {
        Case c = grille.getCaseBis(x,y);
        verifiedCases.add(c);
        area.add(c);

        Case caseAbove  = (x > 0) ? grille.getCaseBis(x-1,y) : null;
        if (caseAbove != null && !verifiedCases.contains(caseAbove)) {
            if (caseAbove.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x - 1, y, verifiedCases, area);
        }

        Case caseUnder = (x < grille.getNbLigne() - 1) ? grille.getCaseBis(x+1,y) : null;
        if (caseUnder != null && !verifiedCases.contains(caseUnder)) {
            if (caseUnder.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x + 1, y, verifiedCases, area);
        }

        Case caseLeft = (y > 0) ? grille.getCaseBis(x,y-1) : null;
        if (caseLeft != null && !verifiedCases.contains(caseLeft)) {
            if (caseLeft.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x, y - 1, verifiedCases, area);
        }

        Case caseRight = (y < grille.getNbColonne()-1) ? grille.getCaseBis(x,y+1) : null;
        if (caseRight != null && !verifiedCases.contains(caseRight)) {
            if (caseRight.getPaysage().getName().equals(c.getPaysage().getName()))
                calculateAreaScore(x, y + 1, verifiedCases, area);
        }

        int nbCrowns= 0;
        for (Case c0 : area)
            nbCrowns += c0.getPaysage().getNbCouronnes();

        return nbCrowns * area.size();
    }

    public int calculateVerifiedCases(ArrayList<Case> verifiedCases)
    {
        int score = 0;

        for (int i = 0; i < grille.getNbLigne(); i++)
            for (int j = 0; j < grille.getNbColonne(); j++) {
                Case c = this.grille.getCaseBis(i,j);

                if (!c.getPaysage().getName().equals("castle") && !verifiedCases.contains(c))  {
                    ArrayList<Case> area = new ArrayList<Case>();
                    score += this.calculateAreaScore(i, j, verifiedCases, area);
                }
            }

        return score;
    }

    public int calculateScore() {
        ArrayList<Case> verifiedCases = new ArrayList<>();
        return calculateVerifiedCases(verifiedCases);
    }


}
