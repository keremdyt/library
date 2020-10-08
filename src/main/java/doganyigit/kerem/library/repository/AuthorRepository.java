package doganyigit.kerem.library.repository;

import doganyigit.kerem.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
