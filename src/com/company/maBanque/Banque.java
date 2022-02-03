package com.company.maBanque;

public class Banque {

    public String nomBanque;
    public Client[] sesClients;
    public int nbCLient;


    public Banque(String nom) {
        this.nomBanque = nom;
        this.nbCLient=0;
        this.sesClients = new Client[100];

    }

    public Banque(String nomBanque, Client[] sesClient) {
        this.nomBanque = nomBanque;
        this.sesClients = sesClient;
    }


    public void  ajouterClient(Client newClient){
        this.sesClients[nbCLient++]=newClient;
    }

    public Client trouveCLient ( String nomAtrouver){
        for (Client client : this.sesClients) {
            if ( client == null ) {
                return null;
            }

            if ( client.getNom().compareTo(nomAtrouver) == 0 ) {
                return client;
            }
        }
        return null;
    }

    public void  bilanCLient(){

    }

    public void afficcherBilan(){

    }

    public void renflouer(Client[] mesClients){
        for (int i=0;i< mesClients.length;i++){
        Client monClient = mesClients[i];
        //renfoue le compte courrant (le 1er)
        monClient.renfouer();
        }

    }
}
