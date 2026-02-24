import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Repository repo = new Repository();

            while (true) {
                System.out.println("""
                        \n=========================
                        STUDENT SYSTEM
                        =========================
                        1. Add Student Info
                        2. Clear All Student Info
                        3. Display Detailed Master List
                        4. Exit
                        =========================
                        """);
                System.out.print("Choice: ");
                int myChoice = scanner.hasNextInt() ? scanner.nextInt() : 0;
                scanner.nextLine(); 

                switch (myChoice) {
                    case 1:
                        addStudentFlow(scanner, repo);
                        break;

                    case 2:
                        System.out.print("Are you sure? (Y/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("Y")) {
                            repo.clearAllStudents();
                        }
                        break;

                    case 3:
                        displayFullDetails(repo);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addStudentFlow(Scanner scanner, Repository repo) {
        System.out.print("How many students to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= count; i++) {
            System.out.println("\n--- Data for Student #" + i + " ---");
            System.out.print("First Name: "); String fName = scanner.nextLine();
            System.out.print("Middle Name: "); String mName = scanner.nextLine();
            System.out.print("Last Name: "); String lName = scanner.nextLine();
            System.out.print("Age: "); int age = scanner.nextInt();
            System.out.print("Year Level: "); int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Course: "); String course = scanner.nextLine();
            System.out.print("Section: "); int section = scanner.nextInt();
            System.out.print("Student Number: "); long sNum = scanner.nextLong();
            System.out.print("Contact Number: "); long cNum = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Birthday (YYYY-MM-DD): "); String bday = scanner.nextLine();

            Student s = new Student.StudentBuilder()
                    .setFirstName(fName).setMiddleName(mName).setLastName(lName)
                    .setAge(age).setYearLevel(year).setCourse(course).setSection(section)
                    .setStudentNumber((int) sNum).setContactNumber((int) cNum).setBirthday(bday)
                    .build();

            repo.addStudent(s);
        }
    }

    private static void displayFullDetails(Repository repo) {
        List<Student> list = repo.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("\n[!] No records found.");
            return;
        }

        System.out.println("\n--- DETAILED STUDENT RECORDS ---");
        for (Student s : list) {
            System.out.println("-".repeat(40));
            System.out.println("ID: " + s.getStudentNumber());
            System.out.println("Name: " + s.getLastName() + ", " + s.getFirstName() + " " + s.getMiddleName());
            System.out.println("Course/Yr/Sec: " + s.getCourse() + " " + s.getYearLevel() + "-" + s.getSection());
            System.out.println("Age: " + s.getAge());
            System.out.println("Birthday: " + s.getBirthday());
            System.out.println("Contact: " + s.getContactNumber());
        }
        System.out.println("-".repeat(40));
    }
}

