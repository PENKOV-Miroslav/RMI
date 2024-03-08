package org.example.morpion;

public class MorpionGrille {

    private final int hauteur;
    private final int largeur;
    private final char[][] grille;

    public MorpionGrille(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.grille = new char[hauteur][largeur];
        initialiserGrille();
    }

    public void initialiserGrille() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public String visualiserGrille() {
        StringBuilder gridString = new StringBuilder();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                gridString.append(grille[i][j]);
                if (j < largeur - 1) {
                    gridString.append("|"); // Ajouter des barres verticales pour séparer les cases
                }
            }
            gridString.append("\n"); // Sauter une ligne pour la prochaine ligne de la grille
            if (i < hauteur - 1) {
                // Afficher des lignes horizontales pour séparer les lignes de la grille
                for (int k = 0; k < largeur; k++) {
                    gridString.append("-");
                    if (k < largeur - 1) {
                        gridString.append("+"); // Ajouter des croix pour former la grille
                    }
                }
                gridString.append("\n"); // Sauter une ligne pour la prochaine ligne de la grille
            }
        }
        return gridString.toString();
    }

    public char[][] getGrille() {
        return grille;
    }
}
