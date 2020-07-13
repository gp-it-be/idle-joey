package ticking;

public class JoeyClock {


    private static long tickTime = 0;

    public static long ticksPassedSince(long tick) {
        return tickTime - tick;
    }

    public static void tick() {
        tickTime++;
    }

    public static long currentTick() {
        return tickTime;
    }
}
