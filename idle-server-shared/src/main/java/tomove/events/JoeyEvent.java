package tomove.events;


public interface JoeyEvent {


    //At this time it seems OK to assume that all events will happen
    //at a given time, tick, for a given player.
    long getTick();

    String getUsername();

}
