public class Admin extends User {
    private String adminId;

    public Admin(String username, String password, String adminId) {
        super(username, password);
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void addQuestion() {
        System.out.println("Admin added a question.");
    }

    public void updateQuestion() {
        System.out.println("Admin updated a question.");
    }

    public void viewQuestions() {
        System.out.println("Admin viewed questions.");
    }

    public void deleteQuestion() {
        System.out.println("Admin deleted a question.");
    }
}
