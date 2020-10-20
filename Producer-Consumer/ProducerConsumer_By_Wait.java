import java.util.LinkedList;
import java.util.Queue;


class Item{
    Item getItem(){
        return this;
    } 
}

public class ProducerConsumer_By_Wait<T> {
  
    private Queue<Item> queue=new LinkedList<>();
    private int max=3;
     private Object notFull=this;
     private Object notEmpty=this;    

    synchronized void  insert(){ 

        try{
            Thread.sleep(1000);

            while(queue.size()==max){
                notFull.wait();
              //  System.out.println(Thread.currentThread());
            }
            queue.add(new Item().getItem());
            notEmpty.notifyAll();


        }catch(Exception e){
                System.out.println(e);
        }
        System.out.println("Producer Added Item Successfully");
        
    }

     synchronized void take(){

        try {
            Thread.sleep(1000);
            while(queue.size()==0){
               notEmpty.wait();
             //   System.out.println(Thread.currentThread());
            }
            queue.remove();
           notFull.notifyAll();            
        } catch (Exception e) {
            System.out.println(e);
        }
       
        System.out.println("Consumer Received Item Successfully");
        
    }


     void  startThreads(){
        
               new Thread(()->{
                   while(true){
                    insert();  
                }                       // Producer 1 
               }).start();

               new Thread(()->{
                while(true)   
                    take();                 // Consumer 1
              }).start();

               new Thread(()->{
                while(true)   
                    insert();               // Producer 2
               }).start();

              

              new Thread(()->{
                while(true)
                    take();                 // Consumer 2
              }).start();

    }

    public static void main(String[] args) {
        ProducerConsumer_By_Wait<Item> test3=new ProducerConsumer_By_Wait<>();
        test3.startThreads();
    }

    

}