package server.privat.eventpushing;

public interface ClientEventEmitter {


    void sendData(String data);

    void onConnectionBroke(Runnable r);
}
