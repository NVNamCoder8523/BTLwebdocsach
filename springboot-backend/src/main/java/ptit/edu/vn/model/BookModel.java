package ptit.edu.vn.model;

import java.time.LocalDate;
import java.util.Collection;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BookModel {
    private Integer id;
    
    @NonNull
    private String title;

    @NonNull
    private String author;
    
    private LocalDate releaseDate;
    
    private String description;

    private Collection<String> categories;    
}
