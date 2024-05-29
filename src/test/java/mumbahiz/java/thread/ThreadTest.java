package mumbahiz.java.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread(){
        var name = Thread.currentThread().getName();
        System.out.println(name);
    }

    @Test
    void createThread() {
        Runnable runnable = () -> {
            System.out.println("Hello from thread : " + Thread.currentThread().getName());
        };

        // runnable.run(); -> it will be run in main thread
        var thread = new Thread(runnable);
        thread.start();

        // First output is 'Program Finish', because thread runs asynchronous
        System.out.println("Program Finish");
    }

    @Test
    void threadSleep() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Program Finish");

        try {
            Thread.sleep(3_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Hello from thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Waiting");
        thread.join();
        System.out.println("Program Finish");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    // e.printStackTrace(); -> wrong implementation, interrupt() will be not works
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();
        System.out.println("Program Finish");
    }

    @Test
    void threadInterruptManualApproach() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable : " + i);
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(10L);
        thread.interrupt();
        System.out.println("Program Finish");
    }

    @Test
    void threadName() {
        var thread = new Thread(()-> {
            System.out.println("Run in thread : " + Thread.currentThread().getName());
        });

        thread.setName("Mumbahiz");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(()-> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Run in thread state : " + Thread.currentThread().getName());
        });
        thread.setName("Mumbahiz");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
