package org.example.exercices.exo3;

public class Account {

        private static long nbComptes = 0;
        protected double solde;
        protected long code;

        public Account(double solde) {
            this.solde = solde;
            this.code = ++nbComptes;
        }

        public long getCode() {
            return code;
        }

        public static long getNbComptes() {
            return nbComptes;
        }


        public double getSolde() {
            return solde;
        }

        public void setSolde(double solde) {
            this.solde = solde;
        }

        public double depot(double montant) {
            solde=solde+montant;
            return solde;}

        public double retrait(double montant){
            if (montant> solde) System.out.println("Pas assez d'argent sur le compte");
            else solde=solde-montant;
            return solde;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "solde=" + solde +
                    ", code=" + code +
                    '}';
        }
    }

