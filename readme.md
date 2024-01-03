## CursusDB Java Native Client Package
### Quick Start
Get the package and call it from within the class you're wanting to work in.


```
import java.io.IOException;

package com.cursusdb.java;

class Example {
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