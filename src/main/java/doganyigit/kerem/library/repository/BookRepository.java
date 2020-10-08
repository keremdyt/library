package doganyigit.kerem.library.repository;

import doganyigit.kerem.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findAllByAuthorName(String author_name);
    public List<Book> findAllByName(String name);
    public List<Book> findAllBySerialname(String serialname);
}
