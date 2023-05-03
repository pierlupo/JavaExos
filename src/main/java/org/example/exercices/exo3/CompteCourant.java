package org.example.exercices.exo3;

public class CompteCourant extends Account{

        int valeurDecouvert;

        public CompteCourant(double solde, int valeurDecouvert)
        {
            super(solde);
            this.valeurDecouvert = valeurDecouvert;
        }

        public int getValeurDecouvert() {
            return valeurDecouvert;
        }

        public void setValeurDecouvert(int valeurDecouvert) {
            this.valeurDecouvert = valeurDecouvert;
        }

        @Override
        public double retrait(double montant) {
            if(solde + valeurDecouvert -montant < 0) System.out.println("Solde insuffisant pour effectuer le retrait!");
            else solde = solde - montant; return solde;
        }

        @Override
        public String toString() {
            return "CompteCourant{" +
                    "code=" + code +
                    ", valeurDecouvert=" + valeurDecouvert +
                    ", solde=" + solde +
                    '}';
        }
    }
