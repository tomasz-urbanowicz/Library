import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;
    private final int CORRECT_OPERATION_CODE = 0;
    private final int UNAVAILABLE_BOOK_CODE = -1;
    private final int BOOK_DOES_NOT_EXIST_CODE = -2;

    @BeforeEach
    public void setUp() {
        book = new Book("Adam Mickiewicz", "Pan Tadeusz");
    }

    @Test
    void shouldSayThatReaderCanSuccessfullyReservedBookWithCorrectData() {
        int code = book.reserve("Tadeusz Norek");
        assertEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatReaderCanNotSuccessfullyReservedBookWhenBookIsWithdraw() {
        book.withdraw();
        int code = book.reserve("Tadeusz Norek");
        assertNotEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatReaderCanNotReserveBookWhenBookIsAlreadyReserved() {
        book.reserve("Tadeusz Norek");
        int code = book.reserve("Karol Krawczyk");
        assertEquals(UNAVAILABLE_BOOK_CODE, code);
    }

    @Test
    void shouldSayThatBookStatusHasChangeToReserved() {
        book.reserve("Tadeusz Norek");
        assertEquals(Status.RESERVED, book.getStatus());
    }

    @Test
    void shouldSayThatBookStatusHasChangedToFree() {
        book.reserve("Tadeusz Norek");
        book.cancelReservation("Tadeusz Norek");
        assertEquals(Status.FREE, book.getStatus());
    }

    @Test
    void shouldSayThatReservationIsCanceled() {
        book.reserve("Tadeusz Norek");
        int code = book.cancelReservation("Tadeusz Norek");
        assertEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatReaderNameIsEmptyAfterCancelingReservation() {
        book.reserve("Tadeusz Norek");
        book.cancelReservation("Tadeusz Norek");
        String expectedReaderName = "";
        assertEquals(expectedReaderName, book.getReader());
    }

    @Test
    void shouldSayThatReservationCanNotBeCancelingWhenGivenReaderDoesNotExist() {
        book.reserve("Tadeusz Norek");
        int code = book.cancelReservation("Karol Krawczyk");
        assertEquals(UNAVAILABLE_BOOK_CODE, code);
    }

    @Test
    void shouldSayThatReservationCanNotBeCancelingWhenBookIsLent() {
        book.lend("Tadusz Norek");
        int code = book.cancelReservation("Tadusz Norek");
        assertEquals(UNAVAILABLE_BOOK_CODE, code);
    }

    @Test
    void shouldSayThatReservationCanNotBeCancelingWhenReaderNameIsInvalid() {
        book.reserve("Tadeusz Norek");
        int code = book.cancelReservation("Karol Krawczyk");
        assertNotEquals(UNAVAILABLE_BOOK_CODE, code);
    }

    @Test
    void shouldSayThatReservedBookCanBeLent() {
        book.reserve("Tadusz Norek");
        int code = book.lend("Tadusz Norek");
        assertEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatFreeBookCanBeLent() {
        int code = book.lend("Tadeusz Norek");
        assertEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatBookAlreadyLentCanNotBeLentAgain() {
        book.lend("Tadusz Norek");
        int code = book.lend("Karol Krawczyk");
        assertEquals(UNAVAILABLE_BOOK_CODE, code);
    }

    @Test
    void shouldSayThatBookCanBeReturnedAfterLent() {
        book.lend("Tadeusz Norek");
        int code = book.returnBook();
        assertEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatBookCanNotBeReturnedWhenBookIsWithdraw() {
        book.withdraw();
        int code = book.returnBook();
        assertNotEquals(CORRECT_OPERATION_CODE, code);
    }

    @Test
    void shouldSayThatBookIsWithdraw() {
        book.withdraw();
        assertEquals(Status.WITHDRAW, book.getStatus());
    }

    @Test
    void shouldSayThatBookStatusHasChangedToLend() {
        book.changeBookStatus(Status.LEND);
        assertEquals(Status.LEND, book.getStatus());
    }
}