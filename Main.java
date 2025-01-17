import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 4, 5, TimeUnit.SECONDS);
        for(int i=0;i<5;i++){
            threadPool.submit(()->{
                try {
                    System.out.println("Hello World!");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}