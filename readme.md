## CursusDB Java Native Client Package
### Quick Start
Add the package
``` 
package cursusdbjava;
```

```
        // Construct client connection to cluster
        // host, port, database user, database user password, tls enabled
        CursusDB.Client client = new CursusDB.Client("0.0.0.0",7681, "u", "p", false);


        try {
            // Connect
            client.Connect();

        } catch (IOException | CursusDB.Client.InvalidAuthenticationException e) {
            throw new RuntimeException(e);
        }

        try {
            // Query database
            String response = client.Query("select * from users;");

            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Close client connection
            client.Close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```