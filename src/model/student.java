package model;

import java.util.Scanner;

public class student {
    private int id;
    private String name;
    private String studentClass;
    private String faceImgPath;

    // Hàm khởi tạo có tham số
    public student(int id, String name, String studentClass, String faceImgPath) {
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.faceImgPath = faceImgPath;
    }

    // Hàm khởi tạo không tham số
    public student() { 
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() { // Cập nhật getter
        return studentClass;
    }

    public void setStudentClass(String studentClass) { // Cập nhật setter
        this.studentClass = studentClass;
    }

    public String getFaceImgPath() {
        return faceImgPath;
    }

    public void setFaceImgPath(String faceImgPath) {
        this.faceImgPath = faceImgPath;
    }
    
    // Phương thức hiển thị thông tin học sinh
    public void displayStudentInfo(student student) {
    	System.out.println("Thông tin học sinh:");
    	
    	System.out.println("Mã số học sinh: " + student.getId()); 
        System.out.println("Tên: " + student.getName());
        System.out.println("Lớp: " + student.getStudentClass());
        
    }
    // Phương thức nhập thông tin sinh viên
    public student enterStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        student student = new student();

        System.out.print("Nhập mã số học sinh: ");
        int studentId = scanner.nextInt();
        student.setId(studentId);
        scanner.nextLine(); // Đọc dòng còn lại để tránh vấn đề với nextLine()

        System.out.print("Nhập tên học sinh: ");
        String name = scanner.nextLine();
        student.setName(name);

        System.out.print("Nhập lớp học sinh: ");
        String studentClass = scanner.nextLine();
        student.setStudentClass(studentClass);

        // Sau khi nhập xong, trả về đối tượng Student
        return student;
    }
    
}
