import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool {

    int corePoolSize;
    int maxNoOfThreads;
    BlockingQueue<Runnable> taskQueue;
    List<WorkerThread> threads;

    ThreadPool(int corePoolSize, int maxNoOfThreads, long keepAliveTime, TimeUnit unit) {
        this.taskQueue = new ArrayBlockingQueue<>(corePoolSize);
        this.corePoolSize = corePoolSize;
        this.maxNoOfThreads = maxNoOfThreads;
        this.threads = new ArrayList<>();
        createThreads(corePoolSize);
    }

    public void createThreads(int corePoolSize){
        for(int i=0;i<corePoolSize;i++){
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    public void submit(Runnable r) {
        synchronized (taskQueue){
            if(taskQueue.size()+1<=corePoolSize){
                taskQueue.add(r);
                taskQueue.notify();
            }else{
                System.out.println("Task is rejected as thread Pool is occupied.");
            }
        }
    }

}




