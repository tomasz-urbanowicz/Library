import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookCollectionTest {
    BookCollection bookCollection;

    @BeforeEach
    public void setUp() {
        bookCollection = new BookCollection();
        bookCollection.buyBook("Andrzej Sapkowski", "The Witcher");
        bookCollection.buyBook("J.R.R. Tolkien", "The Lord of the Rings");
    }

    @Test
    void shouldSayThatSearchingBookByAuthorIsIn1PositionInCollection() {
        int position = bookCollection.searchBookByAuthor("J.R.R. Tolkien");
        assertEquals(1,position);
    }

    @Test
    void searchBookByReader() {
    }

    @Test
    void searchBookByTitle() {
    }

    @Test
    void searchBookByAuthorAndTitle() {
    }

    @Test
    void reserve() {
    }

    @Test
    void cancelReservation() {
    }

    @Test
    void lend() {
    }

    @Test
    void acceptReturn() {
    }

    @Test
    void withdrawnBook() {
    }

    @Test
    void deleteBook() {
    }
}