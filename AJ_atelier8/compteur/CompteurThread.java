package compteur;

import com.sun.source.tree.SynchronizedTree;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;

    //Cette variable de classe permet de retenir quel CompteurThread
    //a fini de compter le premier.
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {

        //TODO: 1. Compter jusqu'à max
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter
        for (int i = 1; i<= max; i++) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.getNom() + ", " + i);
        }
            synchronized (CompteurThread.class) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        if (gagnant == null){
            gagnant = this;
        }
    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
