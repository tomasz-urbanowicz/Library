import java.util.ArrayList;

public class BookCollection {

    /**
     * Collection for storing books
     */
    private ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Add a new book to the collection
     *
     * @param author author's name
     * @param title  book's title
     */
    void buyBook(String author, String title) {
        books.add(new Book(author, title));
    }

    /**
     * @param author author's name
     * @return the index of the searched item
     */
    int searchBookByAuthor(String author) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().equals(author))
                return i;
        }
        return -2; //there is not such position
    }

    int searchBookByReader(String readerName) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getReader().equals(readerName)) {
                return i;
            }
        }
        return -2;
    }

    /**
     * @param title book's title
     * @return the index of the searched item
     */
    int searchBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title))
                return i;
        }
        return -2; //there is not such position
    }

    /**
     * @param author author's name
     * @param title  book's title
     * @return the index of the searched item
     */
    int searchBookByAuthorAndTitle(String author, String title) {
        for (int i = 0; i < books.size(); i++) {
            if ((books.get(i).getAuthor().equals(author))
                    && (books.get(i).getTitle().equals(title)))
                return i;
        }
        return -2; //there is not such position
    }

    int reserve(String author, String title, String readerName) {
        if (books != null) {
            int position = searchBookByAuthorAndTitle(author, title);
            if (position == -2) return -2;
            else {
                if (books.get(position).getStatus().equals(Status.FREE)) {
                    books.get(position).reserve(readerName);
                    return 0;
                } else
                    return -1;
            }
        }
        return -3;
    }


    int cancelReservation(String author, String title, String readerName) {
        if (books != null) {
            int position = searchBookByAuthorAndTitle(author, title);
            if (position == -2) return -2;
            else {
                if (books.get(position).getStatus().equals(Status.RESERVED) && books.get(position).getReader().equals(readerName)) {
                    books.get(position).cancelReservation(readerName);
                    return 0;
                } else
                    return -1;
            }
        }
        return -3;
    }

    int lend(String author, String title, String readerName) {
        int position = searchBookByAuthorAndTitle(author, title);
        if (position == -2) return -2; // there's no such item
        else {
            if (books.get(position).getStatus() == Status.FREE) {
                books.get(position).lend(readerName);
                return 0;
            } else return -1;//book's unavailable
        }
    }

    int acceptReturn(String readerName) {
        if (this.books != null) {
            for (Book book : this.books) {
                if (book.getStatus() != Status.WITHDRAW && book.getReader().equals(readerName)) {
                    book.returnBook();
                    return 0;
                } else return -1;
            }
        }
        return -3;
    }

    void withdrawnBook(int index) {
        Book book = books.get(index);
        if (book.getReader().equals("") && book.getStatus() == Status.FREE) {
            books.get(index).withdraw();
        }
    }

    int deleteBook(int index) {
        if (books == null) return -3;
        if (books.size() == 0) return -2;
        if (index < 0 || index > books.size() - 1) return -3;
        Book book = books.get(index);

        if (book.getReader().equals("")) {
            books.remove(index);
            return 0;
        } else return -4;
    }

    @Override
    public String toString() {
        return "BookCollection{" +
                "books=" + books +
                '}';
    }
}
