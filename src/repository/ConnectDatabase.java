package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.student; // Thêm import cho lớp student

public class ConnectDatabase {
    
    // Phương thức để tạo kết nối với database, trả về Connection
    public static Connection connect_db() {
        // Thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/face_id"; // Thay "face_id" bằng tên database của bạn
        String username = "root"; // Tên tài khoản MySQL
        String password = ""; // Mật khẩu của tài khoản MySQL

        // Kết nối với MySQL
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối database thành công!");
            return connection; // Trả về đối tượng Connection
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Trả về null nếu kết nối thất bại
    }

    // Phương thức để lưu dữ liệu học sinh vào database
    public static void saveStudentData(student student) {
        String query = "INSERT INTO students (id, name, studentClass) VALUES (?, ?, ?)"; // Câu lệnh SQL để chèn dữ liệu

        try (Connection connection = connect_db(); // Kết nối đến database
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            // Thiết lập các tham số cho câu lệnh SQL
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getStudentClass());
//            preparedStatement.setString(4, student.getFaceImgPath());

            // Thực thi câu lệnh
            preparedStatement.executeUpdate();
            System.out.println("Dữ liệu học sinh đã được lưu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
