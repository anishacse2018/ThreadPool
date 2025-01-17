import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{
    BlockingQueue<Runnable> taskQueue;

    WorkerThread(BlockingQueue<Runnable> taskQueue){
        this.taskQueue = taskQueue;
    }
    public void run(){
        Runnable task;
       synchronized (taskQueue){
           while (taskQueue.isEmpty()){
               try {
                   taskQueue.wait();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
           try {
               taskQueue.take().run();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
    }

}