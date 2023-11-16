package serveur;

import domaine.Query;

import java.util.Scanner;

public class ProxyServer {

    public  void startServeur() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();

            Query query = new Query(url, Query.QueryMethod.GET);
            QueryHandler queryHandler = new QueryHandler(query);
            queryHandler.start();
        }


    }

}
