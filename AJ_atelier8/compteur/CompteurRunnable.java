package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    private static AtomicInteger ordreArrive = new AtomicInteger(0);
    //TODO: ajouter un attribut de classe qui retient l'ordre d'arrivée.

    @Override
    public void run() {
        //TODO: 1. Compter jusqu'à max
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.

        for (int i = 1; i<= max; i++){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println(this.getNom() + ", " + i);
        }
        System.out.println(this.getNom() + " arrive en : " + ordreArrive.incrementAndGet());
        try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                throw new RuntimeException(e);}
    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}