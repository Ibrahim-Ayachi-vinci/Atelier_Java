package main;

import domaine.Query;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import serveur.ProxyServer;

import java.io.Closeable;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /**String url;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try {
                Scanner scanner = new Scanner(System.in);
                 url = scanner.nextLine();
            }catch (Exception e){throw new RuntimeException(e);}
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                    .build();

            httpclient.execute(httpGet, response -> {
                System.out.println(response.getCode() + " " + response.getReasonPhrase());
                final HttpEntity entity1 = response.getEntity();
                System.out.println(EntityUtils.toString(entity1));
                EntityUtils.consume(entity1);
                return null;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         **/
        ProxyServer proxyServer = new ProxyServer();
        proxyServer.startServeur();
    }
}