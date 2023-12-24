## CursusDB Java Native Client Package
### Quick Start
```
        // host, port, database user, database user password, tls enabled
        CursusDB.Client client = new CursusDB.Client("0.0.0.0",7681, "u", "p", false);


        try {

            client.Connect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            String response = client.Query("select * from users;");

            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            client.Close();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
```