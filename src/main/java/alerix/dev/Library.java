package alerix.dev;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Semaphore;

@Getter
@RequiredArgsConstructor
public class Library {
    private final Semaphore semaphore;
    private int borrowedBooks = 0;

    public void borrowBook() {
        semaphore.acquireUninterruptibly();
        synchronized (this) {
            borrowedBooks++;
        }
        System.out.println("occupied " + Thread.currentThread().getName());
    }

    public void returnBook() {
        synchronized (this) {
            borrowedBooks--;
        }
        semaphore.release();
        System.out.println("release " + Thread.currentThread().getName());
    }
}
