/*
 * CursusDB
 * Java Native Client Package
 * ******************************************************************
 * Copyright (C) 2023 CursusDB
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cursusdb.java;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

// CursusDB class
class CursusDB {
    // CursusDB client connection class
    static class Client {
        private String host; // Cluster host
        private int port; // Cluster port
        private String username; // Cluster database user username
        private String password; // Cluster database user password
        private boolean tls; // TLS enabled?
        private DataInputStream reader; // Reader for connection
        private DataOutputStream writer; // Writer for connection
        private Socket socket; // Socket
        private SSLSocket secureSocket; // Secured socket


        public class InvalidAuthenticationException
                extends RuntimeException {
            public InvalidAuthenticationException(String errorMessage) {
                super(errorMessage);
            }
        }

        // Constructor for CursusDB Client
        Client(String hostIn, int portIn, String usernameIn, String passwordIn, boolean tlsIn) {
            host = hostIn;
            port = portIn;
            username = usernameIn;
            password = passwordIn;
            tls = tlsIn;
        }

        void Connect() throws IOException {

            if (tls) {
                SocketFactory factory = SSLSocketFactory.getDefault();
                secureSocket = (SSLSocket) factory.createSocket(host, port);

                secureSocket.setEnabledCipherSuites(new String[] { "TLS_AES_128_GCM_SHA256" });
                secureSocket.setEnabledProtocols(new String[] { "TLSv1.3" });


                // Setup writer and reader
                reader = new DataInputStream(secureSocket.getInputStream());
                writer = new DataOutputStream(secureSocket.getOutputStream());
            } else {
                // Create new socket
                socket = new Socket();

                // Connect to cluster
                socket.connect(new InetSocketAddress("0.0.0.0", port), 1000);

                // Setup writer and reader
                reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());
            }

            Base64.Encoder base64Encoder = Base64.getEncoder();
            String userPassEncoded = base64Encoder.encodeToString((username + "\\0" + password).getBytes());

            writer.writeBytes("Authentication: " + userPassEncoded + "\r\n");

            String clusterResponse = reader.readLine();

            if (clusterResponse.startsWith("0")) {
                System.out.println("Connected to cluster.");
            } else {
                throw new InvalidAuthenticationException("Could not authenticate to cluster");
            }

        }

        void Close() throws IOException {
            reader.close();
            writer.close();

            if (tls) {
                secureSocket.close();
            } else {
                socket.close();
            }
            System.out.println("Cluster connection closed.");
        }

        String Query(String query) throws IOException {
            writer.write((query + "\r\n").getBytes());

            String clusterResponse = reader.readLine();
            return clusterResponse;

        }
    }

}