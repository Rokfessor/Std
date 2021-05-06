package Task6;

public class BookHandle {
    public Book book;
    public BookState state;

    public BookHandle(Book book, BookState state) {
        this.book = book;
        this.state = state;
    }

    static BookState randState() {
        if (Math.random() < 0.5)
            return BookState.READHOME;
        return BookState.READLIB;
    }

    @Override
    public String toString() {
        return state + " | " + book.name + " | mayTakeHome:" + book.mayTakeHome;
    }
}
