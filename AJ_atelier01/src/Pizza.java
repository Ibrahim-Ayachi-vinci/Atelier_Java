import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient> {

    public final static double PRIX_BASE = 5;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza (String titre, String description){
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<Ingredient>();
    }
    public Pizza (String titre, String description, ArrayList<Ingredient> ingredients){
        this(titre, description);
        this.ingredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            if(this.ingredients.contains(ingredient)){
                throw new IllegalArgumentException();
            }
            this.ingredients.add(ingredient);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter (Ingredient ingredient) {
        if (ingredients.contains(ingredient)){
            return false;
        }
        ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer (Ingredient ingredient) {
        if (ingredients.contains(ingredient)){
            ingredients.remove(ingredient);
            return true;
        }
        return false;
    }

    public double calculerPrix() {
        double prixTotal = 0;
        prixTotal = prixTotal + PRIX_BASE;
        for (Ingredient ingredient : ingredients) {
            prixTotal = prixTotal + ingredient.getPrix();
        }
        return prixTotal;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza that)) return false;
        return Objects.equals(titre, that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
