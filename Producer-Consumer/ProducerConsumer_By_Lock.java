import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



class ProducerConsumer_By_Lock<T> {
    private ReentrantLock lock=new ReentrantLock(true);
    private Queue<Item> queue=new LinkedList<>();
    private int max=2;
    private Condition notFull=lock.newCondition();
    private Condition notEmpty=lock.newCondition();

    void insert(){ 
        
        lock.lock();
        try{
            Thread.sleep(2000);
            while(queue.size()==max){
            notFull.await();
            }
            queue.add(new Item().getItem());
            notEmpty.signalAll();


        }catch(Exception e){
                System.out.println(e);
        }finally{
            lock.unlock();
       }
        System.out.println("Producer Added Item Successfully");
        
    }

     void take(){

        lock.lock();
        try {
            Thread.sleep(2000);
            while(queue.size()==0){
                notEmpty.await();
            }
            queue.remove();
            notFull.signalAll();            
        } catch (Exception e) {
            System.out.println(e);
        }finally{
                 lock.unlock();
            }

        System.out.println("Consumer Received Item Successfully");

    }


    void startThreads(){

               new Thread(()->{
                   while(true){
                    insert();  
                }                       // Producer 1 
               }).start();

               new Thread(()->{
                while(true)   
                    insert();               // Producer 2
               }).start();

               new Thread(()->{
                while(true)   
                    take();                 // Consumer 1
              }).start();

              new Thread(()->{
                while(true)
                    take();                 // Consumer 2
              }).start();

    }


    public static void main(String[] args) {
        ProducerConsumer_By_Lock<Item> test=new ProducerConsumer_By_Lock<>();
        test.startThreads();
    }


}
