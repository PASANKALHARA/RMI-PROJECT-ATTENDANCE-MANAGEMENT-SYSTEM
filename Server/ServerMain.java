package Server;

import Interface.Attendance;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Attendance attendance = new AttendanceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AttendanceService", attendance);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
