package doganyigit.kerem.library.controller;

import doganyigit.kerem.library.model.Author;
import doganyigit.kerem.library.model.Publisher;
import doganyigit.kerem.library.repository.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Publisher getPublisher(@PathVariable String id) {
        return publisherRepository.getOne(id);
    }

    @PostMapping
    public Publisher create(@RequestBody final Publisher publisher) {
        return publisherRepository.saveAndFlush(publisher);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String name) {
        //Also need to check for children records before deleting
        publisherRepository.deleteById(name);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    public void update(@PathVariable String name, @RequestBody Publisher publisher) {
        Publisher existingPublisher = publisherRepository.getOne(name);
        BeanUtils.copyProperties(publisher, existingPublisher, "name");
        publisherRepository.saveAndFlush(existingPublisher);
    }
}
