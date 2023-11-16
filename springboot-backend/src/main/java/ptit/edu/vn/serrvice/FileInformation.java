package ptit.edu.vn.serrvice;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileInformation {
    private String name;
    private Resource resource;
    private MediaType type;
}
