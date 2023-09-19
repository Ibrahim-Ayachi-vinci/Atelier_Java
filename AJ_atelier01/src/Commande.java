import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande> {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> ligneDeCommandes;

    public Commande(Client client) {
        if (client.getCommandeEnCours() != null) {
            throw new IllegalArgumentException("« impossible de créer " +
                    "une commande pour un client ayant encore une commande en cours");
        }
        ligneDeCommandes = new ArrayList<LigneDeCommande>();
        this.numero = numeroSuivant;
        this.client = client;
        client.enregistrer(this);
        date = LocalDateTime.now();
        numeroSuivant++;
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite) {
        if (client.getCommandeEnCours() != this) {
            return false;
        }
        for (LigneDeCommande ligne : ligneDeCommandes) {
            if (ligne.getPizza().equals(pizza)) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return true;
            }
        }
        LigneDeCommande nvLigne = new LigneDeCommande(pizza, quantite);
        ligneDeCommandes.add(nvLigne);
        return true;
    }

    public boolean ajouter(Pizza pizza) {
        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal() {
        double montantTotal = 0;
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            montantTotal = montantTotal + ligneDeCommande.calculerPrixTotal();
        }
        return montantTotal;
    }

    public String detailler() {
        String text = "";
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            text = text + ligneDeCommande.toString() + "\n";
        }
        return text;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }

    @Override
    public Iterator<LigneDeCommande> iterator() {
        return ligneDeCommandes.iterator();
    }
}
