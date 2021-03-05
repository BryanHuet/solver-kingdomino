package model.plateau;

import model.pieces.Case;

import java.util.ArrayList;
import java.util.HashMap;

public class Score {

    // On calcule le score via un HashMap des noms en key et du multiplicateur (nombre de couronnes en tout)
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
}
