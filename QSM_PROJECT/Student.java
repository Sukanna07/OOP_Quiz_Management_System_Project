/**
 * Parent class for student profiles.
 */
public class Student extends User {
    private String studentId;
    private String department;
    private String semester;

    public Student(String username, String password, String studentId, String department, String semester) {
        super(username, password);
        this.studentId = studentId;
        this.department = department;
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    public String getSemester() {
        return semester;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void viewProfile() {
        System.out.println("Student Profile");
        System.out.println("Username: " + getUsername());
        System.out.println("Student ID: " + studentId);
        System.out.println("Department: " + department);
        System.out.println("Semester: " + semester);
    }

    public void registerForQuiz() {
    System.out.println(getUsername() + " registered for quiz.");
}
}
