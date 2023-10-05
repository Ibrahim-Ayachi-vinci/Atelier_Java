package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <T> List<T> allMatches(List<T> list, Predicate<T> match) {
        //TODO
        List <T> list1 = new ArrayList<T>();
        for (T integer : list){
            if (match.test(integer)){
                list1.add(integer);
            }
        }
        return list1;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static <T,R> List<R> transformAll(List<T> list, Function<T, R> transform) {
        //TODO
        List<R> list1 = new ArrayList<R>();

        for (T integer : list){
            list1.add(transform.apply(integer));
        }
        return list1;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> match) {
        //TODO
        List <T> list1 = list
                .stream()
                .filter(match)
                .collect(Collectors.toList());

        return list1;
    }


    public static <T,R> List<R> map(List<T> list, Function<T, R> transform) {
        //TODO
        List<R> list1 = list.stream()
                .map(transform)
                .collect(Collectors.toList());
        return list1;
    }


}
