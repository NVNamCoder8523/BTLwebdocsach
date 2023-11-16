package ptit.edu.vn.serrvice.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import ptit.edu.vn.exception.AppException;

public class FileValidatorService {
    
    public static boolean ValidFileImage(MultipartFile file) throws AppException {
        if (file.isEmpty())
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "File ảnh không được để trống");
        String fileName = file.getOriginalFilename(), contentType = file.getContentType();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension.equals("jpg") || extension.equals("png")) {
                return true;
            }
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Lỗi ảnh. Hãy chắc chắn file có đuôi là .jpg hoặc .png");
        }
        if (contentType != null) {
            if (contentType.equals("image/jpeg") || contentType.equals("image/png")) {
                return true;
            }
            throw new AppException(HttpStatus.BAD_REQUEST,
                    "Định dạng file không hợp lệ");
        }
        throw new AppException(HttpStatus.BAD_REQUEST,
                "Thiếu định dạng file");
    }

    public static MediaType ValidChapterFile(MultipartFile file) throws AppException {
        if (file.isEmpty())
            return null;
        String fileName = file.getOriginalFilename(), contentType = file.getContentType();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (extension.equals("jpg"))
                return MediaType.IMAGE_JPEG;
            if (extension.equals("png"))
                return MediaType.IMAGE_PNG;
        }
        if (contentType != null) {
            if (contentType.equals("image/jpeg"))
                return MediaType.IMAGE_JPEG;
            if (contentType.equals("image/png"))
                return MediaType.IMAGE_PNG;
        }
        return null;
    }
}
