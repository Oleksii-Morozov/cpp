package alerix.dev;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Reader extends Thread {
    private final Library library;
    private boolean haveBook;
    private boolean chill;

    synchronized public boolean isHaveBook() {
        return haveBook;
    }

    private void simulateWork() {
        var delay = 1000000000;
        for (int i = 0; i < delay; i++) {
            char work = "abcdef".charAt(i % 6);
        }
    }

    private void checkChill() {
        try {
            synchronized (this) {
                while (chill) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "thread can`t wait");
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            library.borrowBook();
            haveBook = true;
            simulateWork();
            checkChill();
            library.returnBook();
            haveBook = false;
            simulateWork();
            checkChill();
        }
    }

    synchronized public void suspendReader() {
        chill = true;
        System.out.println("suspend " + Thread.currentThread().getName());
    }

    synchronized public void resumeReader() {
        chill = false;
        System.out.println("resume " + Thread.currentThread().getName());
        notify();
    }
}
