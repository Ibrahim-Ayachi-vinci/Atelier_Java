package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExercicesOptional {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        ExercicesOptional main = new ExercicesOptional(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExercicesOptional(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Exécute chaque point de l'énoncé
     */
    public void run() {
        this.optional1();
        this.optional2();

    }








    private void optional1() {
        System.out.println("optional1");
        System.out.println("reduce1");
        // TODO reduce
        Integer max1 = transactions.stream().map(Transaction::getValue).reduce(Integer::max).orElse(-1);
        System.out.println(max1);

    }

    private void optional2() {
        System.out.println("optional2");
        // TODO reduce
        Integer max = Integer.MAX_VALUE;
        Trader trader = new Trader("Ibrahim","Bruxelles");
        Transaction transaction = new Transaction(trader, 2021,Integer.MAX_VALUE);

        Optional <Transaction> min1 = transactions.stream().reduce((a,e) -> {
            if (e.getValue() < a.getValue()){
                return e;
            }
            return a;
        });

        if(min1.isEmpty()){
            System.out.println("message");
        }else {
            System.out.println(min1.get());
        }
    }

}