package server.tempexported;

public class ServerApplication {

    public static void main(String[] args) {

    }


    public static Controller getController() {
        //main method zal deze moeten gewired hebben en in variabele instellen om hier te returnen
        return new Controller();
    }
}
