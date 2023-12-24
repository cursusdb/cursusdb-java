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

package cursusdbjava;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;

class CursusDB {

    static class Client {
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean tls;

        private DataInputStream reader;

        private DataOutputStream writer;


        private Socket socket;

        // Constructor for CursusDB Client
        Client(String hostIn, int portIn, String usernameIn, String passwordIn, boolean tlsIn) {
            host = hostIn;
            port = portIn;
            username = usernameIn;
            password = passwordIn;
            tls = tlsIn;
        }

        void Connect() throws IOException {
            // Create new socket
            socket = new Socket();

            // Connect to cluster
            socket.connect(new InetSocketAddress("0.0.0.0", port), 1000);

// Setup writer and reader
            reader = new DataInputStream(socket.getInputStream());
            writer = new DataOutputStream(socket.getOutputStream());

            Base64.Encoder base64Encoder = Base64.getEncoder();
            String userPassEncoded = base64Encoder.encodeToString((username + "\\0" + password).getBytes());

            writer.writeBytes("Authentication: " + userPassEncoded + "\r\n");

           String clusterResponse = reader.readLine();
           System.out.println(clusterResponse);



            System.out.println("Connected to cluster.");
        }

        void Close() throws IOException {
            reader.close();
            writer.close();
            socket.close();
            System.out.println("Cluster connection closed.");
        }
    }

}