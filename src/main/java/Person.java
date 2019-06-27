import java.util.ArrayList;

public abstract class Person {
    protected String name;
    protected int supportedPosition;

    private ArrayList<Book> books;
    private Status status;

    public Person(String name) {
        this.name = name;
    }

    int search(String title, String author) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(title) && books.get(i).getAuthor().equals(author)) {
                return i;
            }
        }
        return -2;


    }

    int reserve(String readerName) {
        if (status == Status.FREE) {
            this.name = readerName;
            this.status = Status.RESERVED;
            return 0; // OK - done
        }
        return -2;
    }


    int cancelReservation(String readerName) {
        if (status == Status.RESERVED && this.name == readerName) {
            this.status = Status.FREE;
            this.name = "";
            return 0; // OK - done
        }
        return -2;
    }
}

