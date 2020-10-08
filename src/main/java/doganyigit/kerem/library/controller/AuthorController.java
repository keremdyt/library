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
        //Also need to check for children records before deleting
        authorRepository.deleteById(name);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    public void update(@PathVariable String name, @RequestBody Author author) {
        Author existingAuthor = authorRepository.getOne(name);
        BeanUtils.copyProperties(author, existingAuthor, "name");
        authorRepository.saveAndFlush(existingAuthor);
    }




    //Uygulama özellikleri:
    //        * Yazar Tanımı yapılabilmeli ( Yazar Adı, Açıklama )
    //        * Yayın Evi Tanımı yapılabilmeli ( Yayın Evi Adı, Açıklama )
    //        * Kitap tanıtımı yapılabilmeli ( kitap adı, kitap alt adı, kitap seri
    // adı, yazar, yayın evi, isbn numarası, açıklama )
    //        * Bir Yazar için n tane kitap tanımlanabilmeli
    //        * Bir Yayın evi için n tane kitap tanımlanabilmeli
    //        * Kitap adı, Seri adı, Yazar ya da ISBN ile arama yapılabilmeli
    //        * Var olan bir kayıt üzerinde değişiklik yapılabilmeli
    //        * Var olan kayıtlar incelebilmeli
    //        * Var olan bir kayıt silinebilmeli


}
