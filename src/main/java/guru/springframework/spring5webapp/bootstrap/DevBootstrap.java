package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by johnnyGrimes on 11/02/2018.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        //First author
        Author dostoevsky = new Author("Fedor", "Dostoevsky");
        Publisher somePublisher = new Publisher("Some Publisher", "Moscow");
        Book cap = new Book("Crime and Punishment", "1234", somePublisher);
        dostoevsky.getBooks().add(cap);
        cap.getAuthors().add(dostoevsky);

        authorRepository.save(dostoevsky);
        publisherRepository.save(somePublisher);
        bookRepository.save(cap);

        //Second author
        Author tolstoy = new Author("Lev", "Tolstoy");
        Publisher anotherPublisher = new Publisher("Another Publisher", "St Petersburg");
        Book wap = new Book("War and Peace", "45326", anotherPublisher);
        tolstoy.getBooks().add(wap);
        cap.getAuthors().add(tolstoy);

        authorRepository.save(tolstoy);
        publisherRepository.save(anotherPublisher);
        bookRepository.save(wap);


    }
}
