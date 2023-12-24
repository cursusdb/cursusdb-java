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

class CursusDB {

    static class Client {
        private String host;
        private int port;
        private String username;
        private String password;
        private boolean tls;

        Client(String hostIn, int portIn, String usernameIn, String passwordIn, boolean tlsIn) {
            host = hostIn;
            port = portIn;
            password = passwordIn;
            tls = tlsIn;
        }

        void Connect() {
            System.out.println("Connected.");
        }
    }

}