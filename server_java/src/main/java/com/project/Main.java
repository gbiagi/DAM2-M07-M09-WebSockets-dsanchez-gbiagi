package com.project;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

// Tutorials: http://tootallnate.github.io/Java-WebSocket/

/*
    WebSocket messages:

    Client to server:
        - Connection posibilities:
            ~ Create game       { "type": "createGame" }
            ~ Join game         { "type": "joinGame", "gameID": "gameID"}            
        - Focus card            { "type": "markCard", "gameID": "gameID", "index": [row, col] }            
        - Select card           { "type": "flipCard", "gameID": "gameID", "index": [row, col] }
    
    Server to client:
        + Error missage         { "type": "error", "value": "Show error list" }
        + Connection            { "type": "conn", "usrID": "usrID" }
        + Create game           { "type": "gameCreated", "gameID": "gameID" }
        + Send status           { "type": "gameSatus", "enemiID": "ID", "turn": "playerTurn", "playerPoints": "num", "enemiPoints": "num" }
        - Focus card            { "type": "markCard", "card": "cardName", "index": [row, col] }
        - Select card           { "type": "flipCard", "card": "cardName", "index": [row, col] }
        - Cards worng           { "type": "wrongCards", "card0": "cardName", "index0": [row, col], "card1": "cardName", "index1": [row, col] }    

    Error list:
        - Game id wrong         201
*/

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        int port = 8888;
        String localIp = getLocalIPAddress();
        System.out.println("Local server IP: " + localIp);

        // Deshabilitar SSLv3 per clients Android
        java.lang.System.setProperty("jdk.tls.client.protocols", "TLSv1,TLSv1.1,TLSv1.2");

        Memory server = new Memory(port);
        server.runServerBucle();
    }

    public static String getLocalIPAddress() throws SocketException, UnknownHostException {
        String localIp = "";
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface ni = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress ia = inetAddresses.nextElement();
                if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia.isSiteLocalAddress()) {
                    System.out.println(ni.getDisplayName() + ": " + ia.getHostAddress());
                    localIp = ia.getHostAddress();
                    // Si hi ha múltiples direccions IP, es queda amb la última
                }
            }
        }

        // Si no troba cap direcció IP torna la loopback
        if (localIp.compareToIgnoreCase("") == 0) {
            localIp = InetAddress.getLocalHost().getHostAddress();
        }
        return localIp;
    }
}