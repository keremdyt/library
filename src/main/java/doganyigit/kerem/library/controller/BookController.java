package doganyigit.kerem.library.controller;

import doganyigit.kerem.library.model.Book;
import doganyigit.kerem.library.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    @RequestMapping("{isbn}")
    public Book getBook(@PathVariable int isbn) {
        return bookRepository.getOne(isbn);
    }

    @GetMapping
    @RequestMapping("/authorname/{name}")
    public List<Book> allBooksByAuthorName(@PathVariable String name) {
        return bookRepository.findAllByAuthorName(name);
    }

    @GetMapping
    @RequestMapping("/name/{name}")
    public List<Book> allBooksByName(@PathVariable String name) {
        return bookRepository.findAllByName(name);
    }

    @GetMapping
    @RequestMapping("/serialname/{serialname}")
    public List<Book> allBooksBySerialname(@PathVariable String serialname) {
        return bookRepository.findAllBySerialname(serialname);
    }

    @PostMapping
    public Book addBook(@RequestBody final Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int isbn) {
        //Also need to check for children records before deleting
        bookRepository.deleteById(isbn);
    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.PUT)
    public void update(@PathVariable int isbn, @RequestBody Book book) {
        Book existingBook = bookRepository.getOne(isbn);
        BeanUtils.copyProperties(book, existingBook, "isbn");
        bookRepository.saveAndFlush(existingBook);
    }
}
