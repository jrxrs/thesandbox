package bug.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceProviderTestCase {

    /* Common Mock Objects */
    @Mock private BookKey mockBookKey;
    @Mock private Book mockBook;

    /* Test 1 - PASS
     * Initialise ServiceProvider with a mock Service<BookKey, Book>.
     * org.mockito.internal.invocation.InvocationMatcher.hasSameMethod resolves
     * both invocation and candidate to:
     * public abstract java.lang.Object bug.mockito.Service.lookup(java.lang.Object)
     */
    @Mock private Service<BookKey, Book> mockService;
    @Test
    public void testViaServiceInterface() throws Exception {
        final ServiceProvider serviceProvider = new ServiceProvider(mockService);

        Mockito.when(mockService.lookup(mockBookKey)).thenReturn(mockBook);

        Assert.assertSame(mockBook, serviceProvider.findBook(mockBookKey));
    }

    /* Test 2 - FAIL
     * Initialise ServiceProvider with a mock BookService<BookKey>.
     * org.mockito.internal.invocation.InvocationMatcher.hasSameMethod resolves
     * invocation to public abstract bug.mockito.Book bug.mockito.BookService.lookup(bug.mockito.BookKey)
     * but candidate to public abstract java.lang.Object bug.mockito.Service.lookup(java.lang.Object).
     */
    @Mock private BookService<BookKey> mockBookService;
    @Test
    public void testViaBookServiceInterface() throws Exception {
        final ServiceProvider bookServiceProvider = new ServiceProvider(mockBookService);

        Mockito.when(mockBookService.lookup(mockBookKey)).thenReturn(mockBook);

        Assert.assertSame(mockBook, bookServiceProvider.findBook(mockBookKey));
    }

}
