package bug.mockito;

public interface BookService<K extends BookKey> extends Service<K, Book> {

    @Override
    public Book lookup(K bookKey);

}
