package bug.mockito;

public class ServiceProvider {

    private final Service<BookKey, Book> bookService;

    public ServiceProvider(final Service<BookKey, Book> bookService) {
        this.bookService = bookService;
    }

    public Book findBook(BookKey bookKey) {
        return bookService.lookup(bookKey);
    }

}
