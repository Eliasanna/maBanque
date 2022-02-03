package com.company.maBanque;

import java.util.Scanner;

public class BanqueInteractive {

    //menu principal
    public void menuPrincipale(Banque maBanque){
        Scanner scanner = new Scanner(System.in);

        int choix = 0;
        do {
            System.out.println("Quelle opération voulez vous effectuer?");
            System.out.println("1) Ajouter un client ");
            System.out.println("2) Effectuer une opération sur un client");
            System.out.println("3) Afficher un bilan général");
            System.out.println("9) Quitter");
            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Vous avez saisi une valeur incorecte");
            }
            switch (choix) {
                case 0:
                    break;
                case 1:
                    menuAjoutClient(maBanque);
                    break;
                case 2:
                    if(maBanque.nbCLient>=1){
                    menuEffectueUneOperation(maBanque);}
                    else {
                        System.out.println("La banque ne contient aucun client");
                        System.out.println("Vous devez ajouter un premier client pour pouvoir réaliser des opérations");
                    }
                    break;
                case 3:
                    if(maBanque.nbCLient>=1){
                        menuAfficheUnBilan(maBanque);
                    }
                    else {
                        System.out.println("La banque ne contient aucun client et aucun compte");
                        System.out.println("Vous devez ajouter un premier client pour pouvoir afficher des bilans");
                    }
                    break;
                case 9:
                    System.out.println("A bientot !");
                    break;
                default:
                    System.out.println("Vous avez saisi une valeure incorecte");
                    break;
            }
        } while(choix!=9);
    }



    //menu  1
    private void menuAjoutClient(Banque maBanque) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer le nom du client : ");
        String nom = scanner.nextLine();
        Client newClient= new Client(nom);
        maBanque.ajouterClient(newClient);
        System.out.println("Le client "+ newClient.nom + " a été créé.");

    }

    // menu 2, choix du client et de l'opération a effectuer sur lui
    private void menuEffectueUneOperation(Banque maBanque) {
        Scanner scanner = new Scanner(System.in);
        Client monClient = null;
        try {
            monClient = rechercher1Client(maBanque);
            int choix=0;
            do {
                System.out.println("Quelle opération voulez-vous effectuer sur le client "+ monClient.nom +" ?");
                System.out.println("1) Ajouter un compte");
                System.out.println("2) Faire un retrait");
                System.out.println("3) Faire un dépot");
                System.out.println("4) Faire un virement");
                System.out.println("5) Afficher un bilan");
                System.out.println("6) Renflouer compte");
                System.out.println("9) Revenir au menu principal");

                try {
                    choix = scanner.nextInt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                switch (choix) {
                    case 1:
                        ajouterCompte(monClient);
                        break;
                    case 2:
                        faireRetrait(monClient);
                        break;
                    case 3:
                        faireDepots(monClient);
                        break;
                    case 4:
                        faireVirement(monClient, maBanque);
                        break;
                    case 5:
                        menuAfficheUnBilan(monClient);
                        break;
                    case 6:
                        renfouerCompteCourrantClient(monClient);
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("Vous avez saisi une valeur incorecte");
                        break;
                }
            } while(choix!=6);
        } catch (Exception e) {
            System.out.println("Client introuvable");
        }
    }



    private Client rechercher1Client(Banque maBanque) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du client");
        String clientRecherche= scanner.nextLine();
        Client monClient = maBanque.trouveCLient(clientRecherche );
        return monClient;
    }

    //menu 2.1 ajouter un nouveau compte au client
    private void ajouterCompte(Client monClient) {
        int choix=0;
        do{
        Scanner scanner = new Scanner(System.in);
        System.out.println("le client a deja " + monClient.nbCompte + " dans la banque");
        System.out.println("Souhaitez vous ajouter un compte vide (1) ou un compte avec dépot immédiat (2), annuler (9)");

        try {
            choix = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (choix) {
            case 1:
                monClient.ajouterCompte(0);
                break;
            case 2:
                System.out.println("Montant à ajouter lors de la création du compte");
                float montant = scanner.nextFloat();
                monClient.ajouterCompte(montant);
                break;
            case 9:
                break;
            default:
                System.out.println("Vous avez saisi une valeur incorecte");
                break;
        }
    } while(choix!=3);

    }


    //menu 2.1 afficher le bilan du client
    private void menuAfficheUnBilan(Client monClient) {
        System.out.println("Le bilan du client "+monClient.nom);
        chargelisteCompte(monClient);

    }

    //menu 2.2 faire un retrait.
    private void faireRetrait(Client monClient) {
        //charger les comptes du client (afficher)
        chargelisteCompte(monClient);

        System.out.println("Veillez choisir un compte à retirer :");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();
        System.out.println("Veillez choisir le montant à retirer :");
        float montantRetrait = scanner.nextFloat();

        System.out.println("1/ confirmer le retrait :");
        System.out.println("9/ annuler le retrait :");

        int retour= scanner.nextInt();

        if (retour ==1) {
            if (montantRetrait > monClient.compteClient[choix].getSoldeCompte()) {
                System.out.println("Le montant est supérieur au solde, retrait impossible !");
            } else {
                monClient.compteClient[choix].retrait(montantRetrait);
                System.out.println("Retrait effectué, le nouveau solde du compte est de :" +
                        monClient.compteClient[choix].getSoldeCompte());
            }
        }
        else {
            System.out.println("Retrait annulé :");
        }
    }

    private void chargelisteCompte(Client monClient) {
        System.out.println("Le client : "+monClient.getNom()+" a "+
                monClient.nbCompte+" compte :");
        for(int i=0;i< monClient.nbCompte ;i++) {
            float soldecompte = monClient.compteClient[i].soldeCompte;
            System.out.println("compte : "+ i + " avec un solde de : "+soldecompte);
        }
    }

    // menu 2.3 faire un depot
    private void faireDepots(Client monClient) {
        //charger les comptes du client (afficher)
        chargelisteCompte(monClient);

        System.out.println("Veillez choisir un compte où réaliser le dépot :");
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        System.out.println("Veillez choisir le montant à déposer :");
        float montantDepot = scanner.nextFloat();

        System.out.println("1/ confirmer le dépot :");
        System.out.println("9/ annuler le dépot :");

        int retour= scanner.nextInt();

        if (retour ==1) {
            monClient.compteClient[choix].depot(montantDepot);
            System.out.println("Dépot effectué, le nouveau solde du compte est de :" +
                    monClient.compteClient[choix].getSoldeCompte());
         }
        else{
            System.out.println("Opération annulée");
        }
    }

    private void faireVirement(Client monClient, Banque maBanque) {
        int choix=0;
        do{
            System.out.println("Désiez-vous faire un virement :");
            System.out.println("1/ entre vos comptes");
            System.out.println("2/ sur le compte courrant d'un autre client");
            System.out.println("9/ retour");
            Scanner scanner = new Scanner(System.in);

            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choix) {
                case 1:
                    // entre ses comptes
                    chargelisteCompte(monClient);
                    System.out.println("Veillez choisir le compte éméteur :");
                    int choixEmeteur = scanner.nextInt();
                    chargelisteCompte(monClient);
                    System.out.println("Veillez choisir le compte destinataire :");
                    int choixDestinataire = scanner.nextInt();
                    System.out.println("1/ confirmation");
                    System.out.println("9/ annulation");
                    choix = scanner.nextInt();
                    if(choix==1) {
                    if(choixDestinataire< monClient.nbCompte){
                        System.out.println("Veillez choisir le montant à transférer :");
                        float montant = scanner.nextFloat();
                        monClient.compteClient[choixEmeteur].retrait(montant);
                        monClient.compteClient[choixDestinataire].depot(montant);
                        System.out.println("Dépot effectué, le nouveau solde du compte "+
                                monClient.compteClient[choixEmeteur].numCompte+" est de :" +
                                monClient.compteClient[choixEmeteur].soldeCompte);
                        System.out.println("Dépot effectué, le nouveau solde du compte "+
                                monClient.compteClient[choixDestinataire].numCompte+"  est de :" +
                                monClient.compteClient[choixDestinataire].soldeCompte);
                    }
                    else{
                        System.out.println("Saisie incorrecte, virement interne annulé");
                    }
                    }
                    else{
                        System.out.println( "Virement interne annulé");
                    }
                    break;
                case 2:
                    // vers un autre client. Uniquement les comptes courrants.
                    System.out.println("Le virement externe est réalisé exclusivement sur des comptes courrants.");
                    try {
                        //recherche le client a crédité. S'il existe pas, catsh.
                        Client clientACrediter = rechercher1Client(maBanque);

                        System.out.println("Montant du virement : ");
                        float montantCrediter = scanner.nextFloat();

                        // possibilité d'annuler le virement
                        System.out.println("1/ confirmation");
                        System.out.println("9/ annulation");
                        choix = scanner.nextInt();
                        if(choix==1) {
                            //transfert de l'argent d'un compte a l'autre

                            //crédite le compte courrant de l'autre client à la position 0
                            clientACrediter.compteClient[0].depot(montantCrediter);

                            //retire l'argent du compte courrant du client à la position 0
                            monClient.compteClient[0].retrait(montantCrediter);
                        }
                        else{
                            System.out.println( "Virement externe annulé");
                        }
                    } catch (Exception e) {
                        System.out.println( "Client inexistant, virement externe annulé");
                    }
                    break;
                case 9:
                    break;
                default:
                    break;
            }
        } while(choix!=9);

    }
    //2.6 renflouer compte courrant du client
    private void renfouerCompteCourrantClient(Client monClient) {
        try {
            monClient.renfouer();
            chargelisteCompte(monClient);
        } catch (Exception e) {
            System.out.println("L'approvisionnement du compte courrant a échoué");
        }
    }

    // Menu 3, le bilan a effectuer
    private void menuAfficheUnBilan(Banque maBanque) {
        //affiche les clients 1 à 1
        for (Client client : maBanque.sesClients )
        {
            if(client==null)
            { break;}
            chargelisteCompte(client);
        }
    }
}
