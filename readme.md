## CursusDB Java Native Client Package
### Quick Start

```
package com.cursusdb.java;

import java.io.IOException;

class Test {
    public static void main(String[] args) {

        CursusDB.Client client = new CursusDB.Client("0.0.0.0",7681, "db-user-username", "db-user-password", false);


        try {

            client.Connect();

        } catch (IOException | CursusDB.Client.InvalidAuthenticationException e) {
            throw new RuntimeException(e);
        }

        try {

            String response = client.Query("ping;");

            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            client.Close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
```