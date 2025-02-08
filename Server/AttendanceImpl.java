package Server;

import Interface.Attendance;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AttendanceImpl extends UnicastRemoteObject implements Attendance {
    private final List<AttendanceRecord> records;
    private int currentId;

    public AttendanceImpl() throws RemoteException {
        super();
        records = new ArrayList<>();
        currentId = 1; // Initialize record ID counter
    }

    public void addAttendance(String studentId, String studentName, String date, String status) throws RemoteException {
        records.add(new AttendanceRecord(currentId++, studentId, studentName, date, status));
        System.out.println("Attendance added successfully.");
    }

    public List<String> getAttendanceRecords() throws RemoteException {
        List<String> output = new ArrayList<>();
        for (AttendanceRecord record : records) {
            output.add(record.toString());
        }
        return output;
    }

    public void updateAttendance(int id, String status) throws RemoteException {
        for (AttendanceRecord record : records) {
            if (record.getId() == id) {
                record.setStatus(status);
                System.out.println("Attendance updated successfully.");
                return;
            }
        }
        System.out.println("Attendance record not found.");
    }

    public void deleteAttendance(int id) throws RemoteException {
        if (records.removeIf(record -> record.getId() == id)) {
            System.out.println("Attendance deleted successfully.");
        } else {
            System.out.println("Attendance record not found.");
        }
    }

    private static class AttendanceRecord {
        private final int id;
        private final String studentId;
        private final String studentName;
        private final String date;
        private String status;

        public AttendanceRecord(int id, String studentId, String studentName, String date, String status) {
            this.id = id;
            this.studentId = studentId;
            this.studentName = studentName;
            this.date = date;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String toString() {
            return "ID: " + id + ", Student ID: " + studentId + ", Name: " + studentName +
                    ", Date: " + date + ", Status: " + status;
        }
    }
}
