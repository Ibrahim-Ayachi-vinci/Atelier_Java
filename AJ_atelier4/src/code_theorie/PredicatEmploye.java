package code_theorie;

import domaine.Employe;

import java.util.function.Consumer;

public class PredicatEmploye implements Consumer<Employe> {
    @Override
    public void accept(Employe employe) {
        System.out.println(employe);
    }
}
