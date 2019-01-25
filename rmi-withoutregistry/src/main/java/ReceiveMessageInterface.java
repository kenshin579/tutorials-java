import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReceiveMessageInterface extends Remote {
    void receiveMessage(String x) throws RemoteException;
}
