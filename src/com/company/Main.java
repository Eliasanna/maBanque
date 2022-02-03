package com.company;

import com.company.maBanque.Banque;
import com.company.maBanque.BanqueInteractive;

public class Main {

    public static void main(String[] args) {
        Banque maBanque = new Banque("MaBanqueAuTop");
        BanqueInteractive monConseiller = new BanqueInteractive();
        monConseiller.menuPrincipale(maBanque);

    }

}
