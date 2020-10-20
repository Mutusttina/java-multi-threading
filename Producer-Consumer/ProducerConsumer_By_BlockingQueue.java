import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;




class ProducerConsumer_By_BlockingQueue<T>{
    Item1 i=new Item1();
    private BlockingQueue<Item1> queue=new ArrayBlockingQueue<>(2);    
    
    void startThreads(){
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    new Thread(()->
    {
        while (true) {
            try{
                queue.put(i.insert());
            }catch(Exception e){
                System.out.println(e);
            }                                        // Producer no 1
        }
    }).start();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    new Thread(()->
    {
        while (true) {
            try{
                queue.put(i.insert());                // Producer no 2
            }catch(Exception e){
                System.out.println(e);
            }           
        }
    }).start();  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    new Thread(()->{
        while(true){
            try {
                 queue.take();
                 System.out.println("Consumer Consumed item"); 
            } catch (Exception e) {                   //Cosumer no 1
                System.out.println(e);
            }
                                       
        }
    }).start();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    new Thread(()->{
        while(true){
            try {
                queue.take();
                 System.out.println("Consumer Consumed item"); 
            } catch (Exception e) {System.out.println(e);} //Consumer no 2                                         
        }
    }).start();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
}

public static void main(String[] args) {
    ProducerConsumer_By_BlockingQueue<Item> test=new ProducerConsumer_By_BlockingQueue<>();
    test.startThreads();
}

} 