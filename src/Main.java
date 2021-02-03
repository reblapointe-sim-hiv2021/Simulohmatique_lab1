import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String entree;

        do {
            System.out.println("\nEntrez un resistor (Q pour quitter)");

            entree = in.next();
            if (!entree.toUpperCase().startsWith("Q")) {
                Resistor r = FabriqueResistance.fabriquerResistor(entree);
                if (r == null)
                    System.out.println("Ceci n'est pas un résistor.");
                else
                    System.out.println("le résistor a une résistance de " +
                            r.getResistance() + " Ω et une tolérance de " + r.getTolerance());
            }
        } while (!entree.toUpperCase().startsWith("Q"));
    }
}
