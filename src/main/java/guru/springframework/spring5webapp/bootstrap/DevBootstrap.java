package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
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

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        //First author
        Author dostoevsky = new Author("Fedor", "Dostoevsky");
        Book cap = new Book("Crime and Punishment", "1234", "Some Publisher");
        dostoevsky.getBooks().add(cap);
        cap.getAuthors().add(dostoevsky);

        authorRepository.save(dostoevsky);
        bookRepository.save(cap);

        //Second author
        Author tolstoy = new Author("Lev", "Tolstoy");
        Book wap = new Book("War and Peace", "45326", "Some Another Publisher");
        tolstoy.getBooks().add(wap);
        cap.getAuthors().add(tolstoy);

        authorRepository.save(tolstoy);
        bookRepository.save(wap);


    }
}
