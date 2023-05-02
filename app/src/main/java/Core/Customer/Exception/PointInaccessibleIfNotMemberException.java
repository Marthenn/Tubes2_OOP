package Core.Customer.Exception;

public class PointInaccessibleIfNotMemberException extends Exception{

    public PointInaccessibleIfNotMemberException() {
        super("Your membership is deactivated, you cannot use any point!");
    }
}
