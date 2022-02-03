package com.company.maBanque;

public class Client {

    public String nom;
    public int codeCLient;
    public Compte[] compteClient;
    public int nbCompte;



    public Client(String nom) {
        this.nom = nom;
        this.nbCompte = 0;
        this.compteClient= new Compte[100];
        ajouterCompte(0);
    }

    public Client() {
    }

    //ajouter un compte plein
    public void ajouterCompte(float solde){
        this.compteClient[nbCompte++] = new Compte(nbCompte + 1, solde);

    }

    //retourne le solde total des comptes du client;
    public float getSolde() {
        float solde=0;
        for (int i=0;i< this.compteClient.length;i++) {
            solde = this.compteClient[i].soldeCompte;
        }
        return solde;
    }

    public void afficherSolde(){
        float soldeTotal = getSolde();
        System.out.println(soldeTotal);
      }

    public void afficherLesComptes(){
        for (int i=0;i< this.compteClient.length;i++) {
            System.out.println("le compte "+compteClient[i].numCompte+

                    " a un solde de " + compteClient[i].soldeCompte);
        }
    }

    public void renfouer(){
        Compte compteCourrant = compteClient[0];
        float renflue=0;
        float sommeRenflue=0;
        for (Compte compte : compteClient)
              {
                  float solde = 0;
                  try {
                      solde = compte.getSoldeCompte();
                  } catch (Exception e) {
                      solde =0;
                  }
                  if(solde>=10) {
                      renflue= (float) ( solde *0.1);
                      sommeRenflue= renflue +(float) ( solde *0.1);
                      compte.retrait(renflue);
                      compteCourrant.depot(renflue);
                  }
        }
        System.out.println("le compte courrant à été approvisionné de " + sommeRenflue);
    }

    public String getNom() {

        return this.nom;
    }
}
