package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {

    private String nom;
    private int nbPersonne;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinute;
    private List<Instruction> recette;
    private Set <IngredientQuantifie> ingredients;
    private Type type;

    public Plat(String nom, int nbPersonne, Difficulte niveauDeDifficulte, Cout cout, Type type){
        Util.checkString(nom);
        Util.checkPositiveOrNul(nbPersonne);
        Util.checkObject(niveauDeDifficulte);
        Util.checkObject(cout);
        this.nom = nom;
        this.nbPersonne = nbPersonne;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinute = Duration.ofMinutes(0);
        this.type = type;

        recette = new ArrayList<Instruction>();
        ingredients = new HashSet<IngredientQuantifie>();
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinute() {
        return dureeEnMinute;
    }

    public Type getType() {
        return type;
    }

    public void insererInstruction(int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        if(position > recette.size()){throw new IllegalArgumentException();}
        Util.checkObject(instruction);
        this.dureeEnMinute = this.dureeEnMinute.plus(instruction.getDureeEnMinute());
        recette.add(position - 1,instruction);
    }

    public void ajouterInstruction (Instruction instruction){
        Util.checkObject(instruction);
        this.dureeEnMinute = this.dureeEnMinute.plus(instruction.getDureeEnMinute());

        recette.add(instruction);
    }

    public Instruction remplacerInstruction (int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        if(position > recette.size()){throw new IllegalArgumentException();}
        Util.checkObject(instruction);

        Instruction instruction1 =  recette.remove(position - 1);
        this.dureeEnMinute = this.dureeEnMinute.minus(instruction1.getDureeEnMinute());
        this.dureeEnMinute = this.dureeEnMinute.plus(instruction.getDureeEnMinute());
        recette.add(position - 1,instruction);

        return instruction1;
    }

    public Instruction supprimerInstruction (int position){
        Util.checkStrictlyPositive(position);
        if(position > recette.size()){throw new IllegalArgumentException();}

        Instruction instruction1 =  recette.remove(position-1);
        this.dureeEnMinute = this.dureeEnMinute.minus(instruction1.getDureeEnMinute());

        return instruction1;
    }

    public Iterator<Instruction> instructions(){
        return Collections.unmodifiableList(recette).iterator();
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        Util.checkObject(unite);

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);

        return ingredients.add(ingredientQuantifie);
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,Unite.NEANT);

        return ingredients.add(ingredientQuantifie);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(ingredient);
        Util.checkStrictlyPositive(quantite);
        Util.checkObject(unite);
        if (!ingredients.contains(ingredient)){return false;}

        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);

        ingredients.remove(ingredient);

        return ingredients.add(ingredientQuantifie);
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        Util.checkObject(ingredient);
        if (!ingredients.contains(ingredient)){return false;}

        return ingredients.remove(ingredient);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        Util.checkObject(ingredient);

        for (IngredientQuantifie ingredientQuantifies : ingredients){
            if (ingredientQuantifies.getIngredient().equals(ingredient)){
                return ingredientQuantifies;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinute.toHours(), dureeEnMinute.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonne + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }




    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;

        @Override
        public String toString() {
            String text = "";
            switch (this){
                case X :
                    text = "*";
                    break;
                case XX :
                    text = "**";
                    break;
                case XXX :
                    text = "***";
                    break;
                case XXXX :
                    text = "****";
                case XXXXX :
                    text = "*****";
                    break;
            }
            return text;
        }
    }
    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;

        public String toString() {
            String text = "";
            switch (this){
                case $ :
                    text = "€";
                    break;
                case $$ :
                    text = "€€";
                    break;
                case $$$ :
                    text = "€€€";
                    break;
                case $$$$ :
                    text = "€€€€";
                case $$$$$ :
                    text = "€€€€€";
                    break;
            }
            return text;
        }
    }
    public enum Type {
        ENTREE, PLAT, DESSERT;

    }
}
