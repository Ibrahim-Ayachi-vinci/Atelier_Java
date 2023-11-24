package main;


import blacklist.BlacklistService;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {

		BlacklistService blacklistService = new BlacklistService();
		System.out.println(blacklistService);
		QueryFactory queryFactory = new QueryFactoryImpl();
		ProxyServer proxyServer = new ProxyServer(queryFactory, blacklistService);

		proxyServer.startServer();



	}

}