import java.util.regex.Pattern;

public class FabriqueResistor {

    public static final String PATRON_RESISTANCE = "^[BROJVbMGL][NBROJVbMGL]{1,2}[NBROJVbMoA][NBROJVbMoA]$";

    public static Pattern patronCode = Pattern.compile(PATRON_RESISTANCE);

    public static Resistor fabriquerResistor(String code) {
        if (code == null)
            return null;
        if (!patronCode.matcher(code).matches())
            return null;

        double valeur = 0;
        int ind = 0;
        int mult = code.length() == 5 ? 100 : 10;

        valeur += CodeCouleur.valueOf(code.substring(ind, ++ind)).valeur * mult;
        valeur += CodeCouleur.valueOf(code.substring(ind, ++ind)).valeur * (mult / 10.0);
        valeur += code.length() == 5 ? CodeCouleur.valueOf(code.substring(ind, ++ind)).valeur : 0;
        valeur *= CodeCouleur.valueOf(code.substring(ind, ++ind)).multiplicateur;

        return new Resistor(valeur, CodeCouleur.valueOf(code.substring(ind, ++ind)).tolerance);
    }

    public enum CodeCouleur {
        N(0, 1,               0.2),     // Noir
        B(1, 10,              0.01),    // Brun
        R(2, 100,             0.02),    // Rouge
        O(3, 1_000,           0.03),    // Orange
        J(4, 10_000,          1),       // Jaune
        V(5, 100_000,         0.00_5),  // Vert
        b(6, 1_000_000,       0.00_25), // Bleu
        M(7, 10_000_000,      0.00_1),  // Mauve
        G(8, 100_000_000,     0.00_05), // Gris
        L(9, 1_000_000_000,   0.1),     // Blanc
        o(0, 0.1,             0.05),    // Or
        A(0, 0.01,            0.1);     // Argent

        private final int valeur;
        private final double multiplicateur;
        private final double tolerance;

        CodeCouleur(int valeur, double multiplicateur, double tolerance) {
            this.valeur = valeur;
            this.multiplicateur = multiplicateur;
            this.tolerance = tolerance;
        }
    }
}
