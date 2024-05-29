package mumbahiz.java.thread;

public class MultithreadThing extends Thread{

    private int threadNumber;
    public  MultithreadThing(int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " from thread " + Thread.currentThread().getName());
            if (threadNumber == 3){
                throw new RuntimeException();
            }
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
