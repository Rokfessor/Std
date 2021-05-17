package Task6;

import java.util.*;

public class Reader extends Thread {
    private final String name;
    private final Library library;

    public Reader(String name, Library library) {
        this.name = name;
        this.library = library;
    }

    @Override
    public void run() {
        List<BookHandle> desiredBooks = randomBooks();
        List<BookHandle> takenBooks = new ArrayList<>();

        for (BookHandle desiredBook : desiredBooks) {
            Book book = library.books.get(desiredBook.book.name);
            if (book != null && (!(!book.mayTakeHome && desiredBook.state == BookState.READHOME))) {
                takenBooks.add(new BookHandle(book, desiredBook.state));
                library.books.remove(desiredBook.book.name);
            }
        }

        StringBuilder message1 = new StringBuilder(Timer.getCurrentTime() + " \n");
        message1.append(name).append(" пришел в библиотеку и захотел взять книги:\n")
                .append(desiredBooks.toString())
                .append("\nКниги, которые он смог взять:\n")
                .append(takenBooks.toString()).append("\n");

        if (takenBooks.size() == 0)
            message1.append(name).append(" не взял книг и расстроенный ушел домой\n");

        System.out.println(message1.toString());

        if (takenBooks.size() != 0) {
            try {
                int libRead = 0;
                long homeRead = 0;

                for (BookHandle book : takenBooks) {
                    if (book.state == BookState.READLIB) {
                        libRead += (Math.random() * 60) + 20;
                    } else
                        homeRead += (Math.random() * 48) + 12;
                }

                if (libRead != 0) {
                    long sleep = Timer.calcOtnMinutes(libRead);
                    if (sleep > library.timeUntilClose())
                        sleep = library.timeUntilClose();

                    System.out.println(Timer.getCurrentTime() + " " + name + " будет читать в библиотеке " + (libRead) + " минут\n");
                    Thread.sleep(sleep);

                    List<Book> returnedBooks = new ArrayList<>();
                    for (Iterator<BookHandle> it = takenBooks.iterator(); it.hasNext(); ) {
                        BookHandle bookHand = it.next();
                        if (bookHand.state == BookState.READLIB) {
                            library.books.put(bookHand.book.name, bookHand.book);
                            returnedBooks.add(bookHand.book);
                            it.remove();
                        }
                    }

                    System.err.println(Timer.getCurrentTime() + " " + name + " почитал в библиотеке и вернул обратно книги:\n" + returnedBooks + "\n");
                }

                if (takenBooks.size() != 0) {
                    System.out.println(Timer.getCurrentTime() + " " + name + " пошел домой, прихватив с собой книги на " + homeRead + " часов\n" + takenBooks + "\n");

                    Thread.sleep(Timer.calcOtnHours(homeRead));

                    while (!Library.isOpen) {
                        long t = library.timeUntilOpen();
                        if (t <= 0) {
                            t = Timer.calcOtnHours(1);
                        }
                        Thread.sleep(t);
                    }

                    System.err.println(Timer.getCurrentTime() + " " + name + " вернул книги:\n" + takenBooks + "\n");
                    for (Iterator<BookHandle> it = takenBooks.iterator(); it.hasNext(); ) {
                        BookHandle bookHand = it.next();
                        library.books.put(bookHand.book.name, bookHand.book);
                        it.remove();
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public List<BookHandle> randomBooks() {
        List<BookHandle> res = new ArrayList<>();
        int i = (int) (Math.random() * library.booksList.size());
        int j = 0;

        for (Map.Entry<String, Book> book : library.booksList.entrySet()) {
            j++;
            if (i == j) {
                res.add(new BookHandle(book.getValue(), BookHandle.randState()));
            } else if (Math.random() < 0.005D) {
                res.add(new BookHandle(book.getValue(), BookHandle.randState()));
            }
        }

        return res;
    }
}
