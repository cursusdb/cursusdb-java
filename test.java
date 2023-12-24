package cursusdbjava;

import java.io.IOException;

class Test {
    public static void main(String[] args) {

        CursusDB.Client client = new CursusDB.Client("0.0.0.0",7681, "u", "p", false);


        try {

            client.Connect();

        } catch (IOException | CursusDB.Client.InvalidAuthenticationException e) {
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


    }
}