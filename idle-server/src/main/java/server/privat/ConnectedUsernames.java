package server.privat;

import java.util.Set;

public interface ConnectedUsernames {


    String getUsernameFor(String token);


    Set<String> getAllConnectedUsernames();

}
