public class Student extends User {
    private String studentId;
    private String department;

    public Student(String username, String password, String studentId, String department) {
        super(username, password);
        this.studentId = studentId;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void viewProfile() {
        System.out.println("Student Profile");
        System.out.println("Username: " + getUsername());
        System.out.println("Student ID: " + studentId);
        System.out.println("Department: " + department);
    }

    public void registerForQuiz() {
        System.out.println("Student registered for quiz.");
    }
}