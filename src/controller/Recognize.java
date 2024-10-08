package controller;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class Recognize {
    public static void main(String[] args) {
        // Nạp thư viện OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Mở camera (ID 0)
        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Không thể mở camera.");
            return;
        }

        Mat frame = new Mat();
        Recognize faceRecognizer = new Recognize(); // Giả sử đây là lớp nhận diện khuôn mặt

//        while (true) {
//            if (camera.read(frame)) {
//                // Nhận diện khuôn mặt
//                String studentId = faceRecognizer.recognize(frame);
//
//                if (studentId != null) {
//                    // Lưu trạng thái điểm danh thành công cho học sinh
//                	Recognize.markAttendance(studentId);
//                    System.out.println("Đã điểm danh học sinh: " + studentId);
//                }
//
//                HighGui.imshow("Camera", frame);
//
//                if (HighGui.waitKey(1) == 'q') {
//                    break;
//                }
//            } else {
//                System.out.println("Không thể đọc khung hình từ camera.");
//                break;
//            }
//        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}
