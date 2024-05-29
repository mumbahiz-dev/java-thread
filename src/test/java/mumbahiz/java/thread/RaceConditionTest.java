package mumbahiz.java.thread;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {

    @Test
    void raceCondition() throws InterruptedException {
        var counter = new Counter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000L; i++) {
                counter.increment();
            }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}
