package doganyigit.kerem.library.controller;

import doganyigit.kerem.library.model.Author;
import doganyigit.kerem.library.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Author getAuthor(@PathVariable String id) {
        return authorRepository.getOne(id);
    }

    @PostMapping
    public Author create(@RequestBody final Author author) {
        return authorRepository.saveAndFlush(author);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String name) {
        authorRepository.deleteById(name);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    public void update(@PathVariable String name, @RequestBody Author author) {
        Author existingAuthor = authorRepository.getOne(name);
        BeanUtils.copyProperties(author, existingAuthor, "name");
        authorRepository.saveAndFlush(existingAuthor);
    }
}
