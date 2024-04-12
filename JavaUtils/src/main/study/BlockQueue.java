package main.study;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author duancf
 * @version 1.0
 * @date created in 2024年04月12日 10:49
 * @since 1.0
 */
public class BlockQueue {

    final Lock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();
    final Condition notFull = lock.newCondition();

    final Object[] arr = new Object[100];

    private Integer count = 0;

    private Integer putIndex = 0;

    private Integer getIndex = 0;

    public void put(Object value){
        lock.lock();
        try{
            if(count == arr.length - 1){
                System.out.println("队列满");
                notFull.await();
            }
            arr[putIndex++] = value;
            if(putIndex == arr.length - 1){
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public Object get(){
        lock.lock();
        try{
            if(count == 0){
                System.out.println("队列空");
                notEmpty.await();
            }
            Object value = arr[getIndex++];
            if(getIndex == arr.length - 1){
                getIndex = 0;
            }
            count--;
            notFull.signal();
            return value;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
