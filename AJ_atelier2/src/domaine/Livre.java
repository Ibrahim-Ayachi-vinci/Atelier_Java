package domaine;

import util.Util;

import java.util.*;

public class Livre {
    private Map<Plat.Type, SortedSet<Plat>> livrePlat = new HashMap<Plat.Type, SortedSet<Plat>>();


    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);
        SortedSet<Plat> plats = this.livrePlat.get(plat.getType());
        // Si c'est le premier plat de ce type, on crée le SortedSet pour ce type dans la Map.
        if (plats == null) {
            plats = new TreeSet<Plat>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int comp = o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
                    if (comp == 0) return o1.getNom().compareTo(o2.getNom());
                    return comp;
                }
            });
            this.livrePlat.put(plat.getType(), plats);
        }
        // On ajoute dans le SortedSet
        return plats.add(plat);
    }

    public boolean supprimerPlat (Plat plat){
        Plat.Type typePlat = plat.getType();
        if (livrePlat.get(typePlat).contains(plat)){
            livrePlat.get(typePlat).remove(plat);
            if(livrePlat.get(typePlat).isEmpty()){
                livrePlat.remove(typePlat);
            }
            return true;
        }
        return false;
    }

    public SortedSet<Plat> getPlatParType(Plat.Type type){
        SortedSet<Plat> platRenvoye = new TreeSet<Plat>();

        for (Plat plat : livrePlat.get(type)){
            platRenvoye.add(plat);
        }
        return Collections.unmodifiableSortedSet(platRenvoye);
    }

    public boolean contientPlat (Plat plat) {
        if(livrePlat.containsKey(plat.getType())){
            if(livrePlat.get(plat.getType()).contains(plat)){
                return true;
            }
        }
        return false;
    }

    public Set<Plat> tousLesPlats() {
        HashSet<Plat> tousLesPlats = new HashSet<>();
        for (Plat.Type type : livrePlat.keySet()){
            for (Plat plat : livrePlat.get(type)){
                tousLesPlats.add((plat));
            }
        }
        return tousLesPlats;
    }
    @Override
    public String toString() {
        String text = "";

        for (Plat.Type type : livrePlat.keySet()) {
            SortedSet<Plat> platss = livrePlat.get(type);
            for (Plat plats : platss){
                text = text + type.getNom() + "\n=======\n" +
                        plats.getNom() + "\n\n";
            }
        }
        return text;
    }
}
