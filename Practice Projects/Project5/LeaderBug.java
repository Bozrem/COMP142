package Project5;

/**
 * An interface is like an abstract class, but it only contains
 * abstract methods.  It may not contain any instance variables
 * or concrete methods.  Interfaces are useful when you need to
 * write a derived class that inherits from a base class, but the
 * derived class needs to have additional behaviors as well.  These
 * additional behaviors can be defined through interfaces.
 */

public interface LeaderBug {
    /** A class that implements LeaderBug must define this
     * getLastStep() method.  This method, when written,
     * should return the last step the bug in question took.
     * That is, the Bug that implements LeaderBug should write
     * getLastStep() to return the last dance step the Bug took.
     */
    public abstract DanceStep getLastStep();
}
