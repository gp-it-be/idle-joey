package server.privat;

import java.util.Set;

public interface SessionRetriever {


    String getUsernameFor(String token);


    Set<String> getAllConnectedUsernames();

}
