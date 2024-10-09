import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.utils.Converters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FaceTrainer {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private static final String datasetPath = "dataset"; // Đường dẫn đến tập dữ liệu
    private static final String cascadePath = "open_cv/haarcascade_frontalface_default.xml"; // Đường dẫn đến file Haar Cascade

    private CascadeClassifier detector;
    private LBPHFaceRecognizer recognizer;

    public FaceTrainer() {
        detector = new CascadeClassifier(cascadePath);
        recognizer = LBPHFaceRecognizer.create();
    }

    // Hàm lấy hình ảnh và nhãn từ thư mục
    private List<Mat> getImagesAndLabels(List<Integer> ids) {
        File datasetDir = new File(datasetPath);
        File[] imageFiles = datasetDir.listFiles();
        List<Mat> faceSamples = new ArrayList<>();

        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                // Đọc hình ảnh
                Mat img = Imgcodecs.imread(imageFile.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
                if (img.empty()) { // Kiểm tra xem hình ảnh có được đọc thành công không
                    System.out.println("Không thể đọc hình ảnh: " + imageFile.getName());
                    continue; // Bỏ qua hình ảnh này nếu không đọc được
                } 

                // Lấy ID từ tên tệp hình ảnh
                String fileName = imageFile.getName();
                String[] parts = fileName.split("\\.");
                if (parts.length < 2) {
                    System.out.println("Tên tệp không hợp lệ: " + fileName);
                    continue; // Bỏ qua nếu tên tệp không hợp lệ
                }
                int id = Integer.parseInt(parts[1]); // Giả sử định dạng là "name.id.jpg"

                // Nhận diện khuôn mặt trong hình ảnh
                Rect[] facesArray = detector.detectMultiScale(img).toArray();

                for (Rect face : facesArray) {
                    Mat faceMat = new Mat(img, face);
                    faceSamples.add(faceMat);
                    ids.add(id);
                }
            }
        }
        return faceSamples;
    }

    public void trainFaces() {
        List<Integer> ids = new ArrayList<>();
        List<Mat> faces = getImagesAndLabels(ids);
        if (!faces.isEmpty()) {
            recognizer.train(Converters.vector_Mat_to_Mat(faces), Converters.vector_int_to_Mat(ids));
            recognizer.save("trainer/trainer.yml");
            System.out.println(ids.size() + " khuôn mặt đã được train và lưu vào trainer.yml.");
        } else {
            System.out.println("Không tìm thấy hình ảnh hợp lệ.");
        }
    }

    public static void main(String[] args) {
        FaceTrainer faceTrainer = new FaceTrainer();
        faceTrainer.trainFaces(); // Gọi hàm trainFaces để đào tạo khuôn mặt
    }
}
