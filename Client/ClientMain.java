package Client;

import Interface.Attendance;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Attendance attendance = (Attendance) registry.lookup("AttendanceService");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. Add Attendance");
                System.out.println("2. View Attendance");
                System.out.println("3. Update Attendance");
                System.out.println("4. Delete Attendance");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Enter Date (yyyy-mm-dd): ");
                        String date = scanner.nextLine();
                        System.out.print("Enter Status (Present/Absent): ");
                        String status = scanner.nextLine();
                        attendance.addAttendance(studentId, studentName, date, status);
                        break;
                    case 2:
                        System.out.println("Attendance Records:");
                        for (String record : attendance.getAttendanceRecords()) {
                            System.out.println(record);
                        }
                        break;
                    case 3:
                        System.out.print("Enter Record ID to Update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Clear the buffer
                        System.out.print("Enter New Status: ");
                        String newStatus = scanner.nextLine();
                        attendance.updateAttendance(updateId, newStatus);
                        break;
                    case 4:
                        System.out.print("Enter Record ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        attendance.deleteAttendance(deleteId);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
