package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BlacklistService {
    private static Set<String> blacklistdDomains = new HashSet<>();

    static {
        Properties propertie = null;
        FileInputStream input = null;
        try {
            propertie = new Properties();
            input = new FileInputStream("blackList.properties");
            propertie.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ignore) {
                }
            }
        }
        String value = propertie.getProperty("blackList");
        System.out.println(value);
        String[] words = value.split(";");
        blacklistdDomains.addAll(Arrays.asList(words));

    }

    public BlacklistService() {

    }

    public boolean check(Query query) {
        return blacklistdDomains.stream().noneMatch(e -> query.getUrl().contains(e));
    }

}