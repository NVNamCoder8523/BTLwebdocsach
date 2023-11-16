package ptit.edu.vn.serrvice.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ptit.edu.vn.exception.AppException;
import ptit.edu.vn.serrvice.FileInformation;
import ptit.edu.vn.serrvice.IFileService;

@Service
public class FileService implements IFileService {
    @Override
    public void uploadCoverImage(MultipartFile file){
        if (FileValidatorService.ValidFileImage(file)) {
            try {
            File dir = new File(COVERIMAGE_DIR);
            if (List.of(dir.listFiles())
                .contains(new File(file.getOriginalFilename()))) {
                throw new AppException(HttpStatus.BAD_REQUEST, 
                    "Tên file đã tồn tại",
                    "Hãy thử lại với tên file khác");
            }
            file.transferTo(dir);
            } catch (Exception e) {
                throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, 
                    "Lỗi khi lưu file");
            }
        }
    } 

    public void uploadChapterBook(List<MultipartFile> files, Integer bookId, Integer chapterId) {
        try {
            File dir = new File(BOOK_DIR + "/" + bookId + "/" + chapterId);
            int maxId = dir.listFiles().length;
            if (!dir.exists()) {
                // tạo folder mới nếu chưa tồn tại
                dir.mkdirs();
                maxId = 1;
            }
            for (MultipartFile fileUpload : files) {
                // Lưu lại những file hợp lệ. nếu file ko hợp lệ thì skip
                MediaType validResult = FileValidatorService.ValidChapterFile(fileUpload);
                if (validResult != null) {
                    String newFileName = String.format("%03d", maxId)
                         + "." + validResult.getSubtype();
                    File targetFile = new File(dir.getPath() + "/" + newFileName);
                    fileUpload.transferTo(targetFile);
                }
            }
        } catch (Exception e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Lỗi khi lưu file");
        }
    } 

    // Luôn chắc kèo file ảnh tồn tại
    public FileInformation streamCoverimageBook(String filename){
        File file = new File(COVERIMAGE_DIR + "/" + filename);
        Resource resource = new FileSystemResource(file.getPath());
        MediaType type = filename.endsWith(".jpg")
            ? MediaType.IMAGE_JPEG 
            : MediaType.IMAGE_PNG;
        return new FileInformation(filename, resource, type);
    }

    public List<FileInformation> streamChapterBook(Integer bookId, Integer chapterId){
        File dir = new File(BOOK_DIR + "/" + bookId + "/" + chapterId);
        List<FileInformation> result = new ArrayList<>();
        
        // order all file in 'dir' by name
        Arrays.sort(dir.listFiles());

        for (File file : dir.listFiles()) {
            Resource resource = new FileSystemResource(file.getPath());
            MediaType type = file.getName().endsWith(".jpg")
                ? MediaType.IMAGE_JPEG 
                : MediaType.IMAGE_PNG;
            result.add(new FileInformation(file.getName(), resource, type));
        }
        return result;
    }
}
