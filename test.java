package cursusdbjava;

class Test {
    public static void main(String[] args) {
        CursusDB.Client client = new CursusDB.Client("0.0.0.0",7681, "username", "password", false);

        client.Connect();
    }
}