package domaine;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Livre {
    Map<Plat.Type, SortedSet<Plat>> livrePlat = new HashMap<Plat.Type, SortedSet<Plat>>();


    public boolean ajouterPlat (Plat plat){
        Plat.Type typeDePlat = plat.getType();
        if(livrePlat.get(typeDePlat) == null){
            livrePlat.put(typeDePlat, new TreeSet<Plat>());
            return true;
        }

        if (livrePlat.get(typeDePlat).contains(plat)){return false;}

        livrePlat.get(typeDePlat).add(plat);
        return true;
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
    @Override
    public String toString() {
        String text = "";

        for (Plat.Type type : livrePlat.keySet()) {
            for (Plat plats : livrePlat.get(type)){
                text = text + type + "\n =======\n" +
                        plats.toString();
            }
        }
        return text;
    }
}
