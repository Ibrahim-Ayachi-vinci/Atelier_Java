package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixAucun;
    private Prix prixPub;
    private Prix prixSolde;

    private Produit produit;

    private Produit produitEmpty;

    @BeforeEach
    void setUp() {
        prixAucun = new Prix();
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 20);
        prixAucun.definirPrix(1, 20);
        prixAucun.definirPrix(10, 10);
        prixPub.definirPrix(3, 15);
        produit = new Produit("Bouteille","Everyday","Boisson");
        produitEmpty = new Produit("","","");
        produit.ajouterPrix(LocalDate.of(2000,10,20),prixAucun);
        produit.ajouterPrix(LocalDate.of(2000,10,21),prixPub);
        produit.ajouterPrix(LocalDate.of(2000,10,22),prixSolde);


    }

    @Test
    void getMarque() {
    }

    @Test
    void getNom() {
    }

    @Test
    void getRayon() {
    }

    @Test
    void ajouterPrix() {
    }

    @Test
    void getPrix() {
    }

}