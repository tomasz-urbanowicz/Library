import java.util.ArrayList;

public class Librarian extends Person {

    private ArrayList<Book> books;
    private Status status;

    public Librarian(String name) {
        super(name);
    }

    int lend(String readerName) {
        if (status == Status.FREE) {
            this.name = readerName;
            status = Status.LEND;
            return 0;
        }
        if (status == Status.RESERVED && this.name.equals(readerName)) {
            status = Status.LEND;
            return 0;
        }
        return -2;
    }

    int acceptReturn() {
        if (status != Status.WITHDRAW) {
            status = Status.FREE;
            this.name = "";
            return 0; //OK - done
        }
        return -2;
    }

    void withdrownBook() {
        status = Status.WITHDRAW;
    }

    int deleteBook(int index) {
        if (this.books == null) return -3;
        if (books.size() == 0) return -1;
        if (index == -1) return -2; // no books in the collection
        if (index < 0 || index > books.size() - 1) return -3;
        Book book = books.get(index);
        if (book.getReader().equals("")) {
            books.remove(index);
            return 0;
        } else return -4; //the reader has a book
    }

}
