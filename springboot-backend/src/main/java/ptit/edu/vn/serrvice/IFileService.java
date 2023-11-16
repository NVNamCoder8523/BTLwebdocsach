package ptit.edu.vn.serrvice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.stereotype.Service;

@Service
public interface IFileService {
    public final String ROOT_DIR 
        = System.getProperty("user.dir") + "/src/main/resources/static/";
    public final String BOOK_DIR 
        = ROOT_DIR + "chapters";
    public final String COVERIMAGE_DIR 
        = ROOT_DIR + "coverimages";
    
    public void uploadCoverImage(MultipartFile file);

    public void uploadChapterBook(List<MultipartFile> files, Integer bookId, Integer chapterId);

    public FileInformation streamCoverimageBook(String filename);

    public List<FileInformation> streamChapterBook(Integer bookId, Integer chapterId);
}
