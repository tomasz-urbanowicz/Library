public class Book {

    private String author;
    private String title;
    private String reader;
    private Status status = Status.FREE;

    private void setReader(String reader) {
        this.reader = reader;
    }

    String getAuthor() {
        return author;
    }

    String getTitle() {
        return title;
    }

    String getReader() {
        return reader;
    }

    Status getStatus() {
        return status;
    }

    public Book() { }

    Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    int reserve(String readerName) {
        if (this.status == Status.FREE) {
            setReader(readerName);
            this.status = Status.RESERVED;
            return 0; // OK - done
        }
        return -1; //the book is unavailable
    }

    int cancelReservation(String readerName) {
        if (status == Status.RESERVED && this.reader.equals(readerName)) {
            this.reader = "";
            this.status = Status.FREE;
            return 0; //OK - done
        }
        return -1;
    }

    int lend(String readerName) {
        if (this.status == Status.FREE) {
            setReader(readerName);
            status = Status.LEND;
            return 0; // OK - done
        }
        if (status == Status.RESERVED) {
            if (this.reader.equals(readerName)) {
                status = Status.LEND;
                return 0; // OK - done
            } else
                return -1; // the book is unavailable
        }
        return -1; // the book is unavailable
    }

    int returnBook() {
        if (status != Status.WITHDRAW) {
            status = Status.FREE;
            return 0; // OK - done
        } else return -2; // the book is withdrawn
    }

    void withdraw() {
        status = Status.WITHDRAW;
    }

    void changeBookStatus(Status status) {
        this.status = status;
    }

    String showStatus() {
        switch (status) {
            case FREE:
                return "free";
            case LEND:
                return "lend";
            case RESERVED:
                return "reserved";
            case WITHDRAW:
                return "withdraw";
            default:
                return "wrong status";
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", reader='" + reader + '\'' +
                ", status=" + status +
                '}';
    }
}

