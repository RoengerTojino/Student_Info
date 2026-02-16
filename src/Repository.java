import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    // Standardizing the connection string
    private static final String DB_URL = "jdbc:sqlite:School.db";

    public Repository() {
        // We no longer need to create the table since it already exists.
        // But we can test the connection here to ensure the file is found.
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Connected to School.db successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    /**
     * Inserts a new student record into the StudentInfo table.
     */
    public void addStudent(Student student) {
        String sql = "INSERT INTO StudentInfo(FIRSTNAME, MIDDLENAME, LASTNAME, AGE, YEARLEVEL, " +
                "COURSE, SECTION, STUDENTNUMBER, CONTACTNUMBER, BIRTHDAY) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getMiddleName());
            pstmt.setString(3, student.getLastName());
            pstmt.setInt(4, student.getAge());
            pstmt.setInt(5, student.getYearLevel());
            pstmt.setString(6, student.getCourse());
            pstmt.setInt(7, student.getSection());
            // Using setLong in case Student/Contact numbers are large
            pstmt.setLong(8, student.getStudentNumber());
            pstmt.setLong(9, student.getContactNumber());
            pstmt.setString(10, student.getBirthday());

            pstmt.executeUpdate();
            System.out.println("Student " + student.getFirstName() + " added successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
    }

    /**
     * Retrieves all student records and maps them to Student objects.
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM StudentInfo";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student.StudentBuilder()
                        .setFirstName(rs.getString("FIRSTNAME"))
                        .setMiddleName(rs.getString("MIDDLENAME"))
                        .setLastName(rs.getString("LASTNAME"))
                        .setAge(rs.getInt("AGE"))
                        .setYearLevel(rs.getInt("YEARLEVEL"))
                        .setCourse(rs.getString("COURSE"))
                        .setSection(rs.getInt("SECTION"))
                        .setStudentNumber(rs.getInt("STUDENTNUMBER"))
                        .setContactNumber(rs.getInt("CONTACTNUMBER"))
                        .setBirthday(rs.getString("BIRTHDAY"))
                        .build();

                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }

        return students;
    }
    public void clearAllStudents() {
        String sql = "DELETE FROM StudentInfo";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            int rowsDeleted = stmt.executeUpdate(sql);
            // Resets the auto-increment ID back to 1 (Optional)
            stmt.execute("DELETE FROM sqlite_sequence WHERE name='StudentInfo'");

            System.out.println("Success: " + rowsDeleted + " records cleared.");
        } catch (SQLException e) {
            System.err.println("Error clearing database: " + e.getMessage());
        }
    }
}