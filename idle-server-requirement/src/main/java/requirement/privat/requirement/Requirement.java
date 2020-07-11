package requirement.privat.requirement;

public interface Requirement<T> {


    boolean satisfied(T check);

    String description();
}
