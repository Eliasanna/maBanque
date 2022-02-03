package com.company.maBanque;

public class Compte {
    public int numCompte;
    public float soldeCompte;

    public Compte() {

    }

    public Compte(int numCompte, float soldeCompte) {
        this.numCompte = numCompte;
        this.soldeCompte = soldeCompte;
    }

    /* pour faire un depot sur le compte. */
    void depot(float valeur){
        soldeCompte=soldeCompte+valeur;
    }

    /* pour faire un retrait sur le compte. */
     void retrait(float valeur){
         soldeCompte=soldeCompte-valeur;
     }

    /* pour obtenir la valeur du solde */
    public float getSoldeCompte() {
        return soldeCompte;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "soldeCompte=" + soldeCompte +
                '}';
    }

    //faire un virement
    void virer (float valeur, Compte compteDestinataire){
        compteDestinataire.soldeCompte = compteDestinataire.soldeCompte + valeur;
    }


}
