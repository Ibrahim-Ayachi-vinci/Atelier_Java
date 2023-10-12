package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Classe test de PrixTest")
class PrixTest {
    private Prix prixAucun;
    private Prix prixPub;
    private Prix prixSolde;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prixAucun = new Prix();
        prixPub = new Prix(TypePromo.PUB, 10);
        prixSolde = new Prix(TypePromo.SOLDE, 20);
        prixAucun.definirPrix(1, 20);
        prixAucun.definirPrix(10, 10);
        prixPub.definirPrix(3, 15);
    }

    @DisplayName("Get type Promo Nulle constructeur sans paramètre")
    @Test
    void getTypePromoNull() {
        assertNull(prixAucun.getTypePromo());
    }

    @DisplayName("Get type Promo pub")
    @Test
    void getTypePromoPub() {
        assertEquals(TypePromo.PUB, prixPub.getTypePromo());
    }

    @DisplayName("Get type Promo Solde")
    @Test
    void getTypePromoSolde() {
        assertEquals(TypePromo.SOLDE, prixSolde.getTypePromo());
    }


    /////////////////////////////////////////////////////////////

    @DisplayName("GetValeurPromo0")
    @Test
    void getValeurPromo0() {
        assertEquals(0, prixAucun.getValeurPromo());
    }

    @DisplayName("Get Valeur promo correspondante pour pub")
    @Test
    void getValeurPromoParametrePub() {
        assertEquals(10, prixPub.getValeurPromo());
    }

    @DisplayName("Get Valeur promo correspondante pour solde")
    @Test
    void getValeurPromoParametreSolde() {
        assertEquals(20, prixSolde.getValeurPromo());
    }


    /////////////////////////////////////////////////////////

    @DisplayName("definirQunatite à 0")
    @ParameterizedTest
    @ValueSource (ints = {0, -2})
    void definirPrixQuantite(int quantite) {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> prixAucun.definirPrix(quantite,5)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixSolde.definirPrix(quantite,5)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixPub.definirPrix(quantite,5))
        );
    }

    @DisplayName("definirPrix  supérieur à 0")
    @ParameterizedTest
    @ValueSource (doubles = {0, -2})
    void definirPrix(double prix) {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> prixAucun.definirPrix(5,prix)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixSolde.definirPrix(5,prix)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixPub.definirPrix(5,prix))
        );
    }

    @DisplayName("Prix de 6 euro à partir de 10 unité")
    @ParameterizedTest
    @ValueSource(ints = { 10, 15})
    void definirPrix6Euro(int quantite){
        prixAucun.definirPrix(10,6);
        assertEquals(6,prixAucun.getPrix(quantite));
    }


    ///////////////////////////////////////////////////////////




    @DisplayName("test getPrix négatif ou null")
    @ParameterizedTest
    @ValueSource(ints = {-5, 0, -2})
    void getPrixNegatifOrNull(int quantite) {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> prixAucun.getPrix(quantite)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixPub.getPrix(quantite)),
                () -> assertThrows(IllegalArgumentException.class, () -> prixSolde.getPrix(quantite)));
    }

    @DisplayName("test getPrix négatif ou null")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9, 10, 15, 20, 25})
    void getPrix(int quantite) {
        if (quantite >= 10){
            assertEquals(10,prixAucun.getPrix(quantite));
        }else{
            assertEquals(20, prixAucun.getPrix(quantite));
        }
    }

    @DisplayName("test quantite non autorisé")
    @Test
    void getQuantitePub2 (){
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixPub.getPrix(2));
    }

    @DisplayName("test quantite non autorisé")
    @Test
    void getQuantiteSolde2 (){
        assertThrows(QuantiteNonAutoriseeException.class, () -> prixSolde.getPrix(1));
    }


    @DisplayName("test du constructeur promo null")
    @Test
    void testConstructeurPrmo() {
        assertThrows(IllegalArgumentException.class, () -> new Prix(null, 30));
    }

    @DisplayName("test du constructeur valeur < 0")
    @ParameterizedTest
    @ValueSource(doubles = { -4.0, 0})
    void testConstructeurValeur(double prix) {
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.SOLDE, prix));
    }

}