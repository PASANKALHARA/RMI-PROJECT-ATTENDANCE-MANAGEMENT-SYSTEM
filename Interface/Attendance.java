package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Attendance extends Remote {
    void addAttendance(String studentId, String studentName, String date, String status) throws RemoteException;
    List<String> getAttendanceRecords() throws RemoteException;
    void updateAttendance(int id, String status) throws RemoteException;
    void deleteAttendance(int id) throws RemoteException;
}
