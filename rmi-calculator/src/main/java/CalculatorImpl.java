public class CalculatorImpl
        extends
        java.rmi.server.UnicastRemoteObject
        implements Calculator {

    // Implementations must have an 
    //explicit constructor 
    // in order to declare the 
    //RemoteException exception 
    public CalculatorImpl()
            throws java.rmi.RemoteException {
        super();
    }

    public long add(long a, long b)
            throws java.rmi.RemoteException {
        return a + b;
    }

    public long sub(long a, long b)
            throws java.rmi.RemoteException {
        return a - b;
    }

    public long mul(long a, long b)
            throws java.rmi.RemoteException {
        return a * b;
    }

    public long div(long a, long b)
            throws java.rmi.RemoteException {
        return a / b;
    }
}
