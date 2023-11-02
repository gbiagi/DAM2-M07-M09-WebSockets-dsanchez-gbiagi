package com.project;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

// Tutorials: http://tootallnate.github.io/Java-WebSocket/

/*
    WebSockets server, example of messages:

    From client to server:
        - List of clients       { "type": "list" }
        - Private message       { "type": "private", "value": "Hello 002", "destination": "002" }
        - Broadcast message     { "type": "broadcast", "value": "Hello everyone" }

    From server to client:
        - Welcome message       { "type": "private", "from": "server", "value": "Welcome to the chat server" }
        - Client Id             { "type": "id", "from": "server", "value": "002" }
        - List of clients       { "type": "list", "from": "server", "list": ["001", "002", "003"] }
        - Private message       { "type": "private", "from": "001", "value": "Hello 002" }
        - Broadcast message     { "type": "broadcast", "from": "001", "value": "Hello everyone" }
        - Client connected      { "type": "connected", "from": "server", "id": "001" }
        - Client disconnected   { "type": "disconnected", "from": "server", "id": "001" }
 */

public class Main {


    public static void main (String[] args) throws InterruptedException, IOException {

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