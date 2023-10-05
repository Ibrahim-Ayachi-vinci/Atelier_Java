package main;

import code_theorie.PredicatEmploye;
import code_theorie.PredicatGenreHomme;
import code_theorie.PredicatNom;
import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ExerciceFunctionalInterface {
    public static List<Employe> employes;
    public static void main(String[] args) {
        employes = new ArrayList<>();

        employes.add(new Employe(Genre.HOMME, 185, "Bob"));
        employes.add(new Employe(Genre.FEMME, 225, "Alice"));
        employes.add(new Employe(Genre.HOMME, 155, "John"));
        employes.add(new Employe(Genre.FEMME, 165, "Carole"));
        employes.add(new Employe(Genre.HOMME, 185, "Alex"));
        employes.add(new Employe(Genre.HOMME, 185, "Bart"));

        exMap();

        exComparator();

        exForEach();

    }

    /**
     * Replacer l'instatiation de la classe EmployeComparator par un lambda
     */
    private static void exComparator() {
        employes.sort((a, e) ->{
            if (a.getTaille() == e.getTaille()){
                return a.getNom().compareTo(e.getNom());
            }
            return e.getTaille() - a.getTaille();
        });
        System.out.println("Employés triés:");
        System.out.println(employes);

    }

    /**
     * Trouver le type du paramètre de la méthode map.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */
    private static void exMap() {
        Stream<String> listeNom = employes.stream()
                .filter(new PredicatGenreHomme())
                .sorted(Comparator.comparingInt(Employe::getTaille)
                        .reversed())
                .map(new PredicatNom());
        listeNom.forEach(System.out::println);

    }


    /**
     * Trouver le type du paramètre de la méthode foreach.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */
    private static void exForEach(){
        employes.forEach(new PredicatEmploye());


    }
}
