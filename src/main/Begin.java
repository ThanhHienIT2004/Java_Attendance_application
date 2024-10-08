package main;

import controller.FaceDetect;  
import repository.ConnectDatabase;
import model.student; // Thêm import cho lớp student

public class Begin {                     // Đổi tên lớp thành Main
    public static void main(String[] args) {  // Phương thức main
        // Khởi tạo đối tượng ConnectDatabase
        ConnectDatabase connect = new ConnectDatabase();  
        FaceDetect faceDetect = new FaceDetect();      // Khởi tạo đối tượng FaceDetect
        student newStudent = new student(); // Tạo đối tượng student

        
        connect.connect_db();     // Gọi phương thức connect_db để kết nối đến cơ sở dữ liệu
        
        // Nhập thông tin học sinh
        newStudent = newStudent.enterStudentInfo(); // Nhập thông tin học sinh
        
        // Lưu thông tin học sinh vào cơ sở dữ liệu
        ConnectDatabase.saveStudentData(newStudent); // Gọi phương thức lưu dữ liệu
        
        // Gọi phương thức detectFace để bắt đầu phát hiện và lưu khuôn mặt
        faceDetect.detectFace(); // Gọi phương thức chụp ảnh
    }
}
