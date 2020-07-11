package activity.privat.requirements;

public interface Requirement<T> {


    boolean satisfied(T check);

    String description();
}
