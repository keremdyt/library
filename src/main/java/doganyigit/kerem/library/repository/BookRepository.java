package doganyigit.kerem.library.repository;

import doganyigit.kerem.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthorName(String author_name);
    List<Book> findAllByName(String name);
    List<Book> findAllBySerialname(String serialname);
}
