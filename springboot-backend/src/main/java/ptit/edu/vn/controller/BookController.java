package ptit.edu.vn.controller;

import ptit.edu.vn.entity.Book;
import ptit.edu.vn.exception.AppException;
import ptit.edu.vn.model.BookModel;
import ptit.edu.vn.repository.BookRepository;
import ptit.edu.vn.serrvice.FileInformation;
import ptit.edu.vn.serrvice.IFileService;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	private final IFileService IFileService;
	private final BookRepository bookRepository;
	private final int PAGE_SIZE = 10;

	public BookController(IFileService IFileService, BookRepository bookRepository) {
		this.IFileService = IFileService;
		this.bookRepository = bookRepository;
	}

	@GetMapping("all")
	@Secured("ROLE_ADMIN")
	public List<Book> GetAll(@RequestParam int page){
		return bookRepository.findAll(
			Pageable.ofSize(PAGE_SIZE).withPage(page)).toList();
	}		

	@GetMapping
	public ResponseEntity<List<Book>> getBookByName(
			@RequestParam(required = true) String title){

		// StringUtil check ko null và có kí tự 
		if (!StringUtils.hasLength(title))
			throw new AppException(HttpStatus.BAD_REQUEST, 
				"Tên sách không được để trống");

		List<Book> result = bookRepository.findByTitle(title);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new AppException(
					HttpStatus.NOT_FOUND, 
					"Không có sách nào có mã : " + id));
		return ResponseEntity.ok(book);
	}

	@GetMapping("{id}/image")
	public ResponseEntity<Resource> getImageBook(@PathVariable Integer id){
		String filename = bookRepository.findById(id)
				.orElseThrow(() -> new AppException(
					HttpStatus.NOT_FOUND, 
					"Không có sách nào có mã : " + id))
				.getCoverImage();
		FileInformation fileInfo = IFileService.streamCoverimageBook(filename);
		return ResponseEntity.ok()
			.contentType(fileInfo.getType())
			.body(fileInfo.getResource());
	}

	@PostMapping
	public Integer addBook(@RequestBody BookModel model) {
		if (model.getReleaseDate().isAfter(LocalDate.now()))
			throw new AppException(HttpStatus.BAD_REQUEST, 
				"Ngày phát hành không được lớn hơn ngày hiện tại");
		if (bookRepository.existsByTitle(model.getTitle()))
			throw new AppException(HttpStatus.CONFLICT, 
				"Đã tồn tại sách có tên : " + model.getTitle() + " trong hệ thống");
		Book book = new Book();
		book.setId(0);
		book.setTitle(model.getTitle());
		book.setReleaseDate(model.getReleaseDate());
		book.setAuthor(model.getAuthor());
		book.setUploadAt(LocalDateTime.now());
		book.setDescription(model.getDescription());
		bookRepository.save(book);
		return book.getId();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Boolean> editBookInformation(@PathVariable Integer id, @RequestBody Book bookDetails){
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new AppException(
					HttpStatus.NOT_FOUND, 
					"Không có sách nào có mã : " + id));
		
		book.setTitle(bookDetails.getTitle());
		book.setDescription(bookDetails.getDescription());
		book.setReleaseDate(bookDetails.getReleaseDate());
		
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook != null);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Boolean>> deletebook(@PathVariable Integer id){
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND,
				"Book not exist with id :" + id));
		
		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
