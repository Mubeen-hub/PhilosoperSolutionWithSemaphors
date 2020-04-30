/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiningProblemSolution;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author mubeen
 */

class Philosopher implements Runnable {

    // Genertes random time for each philosopher to thnik and eat
    private Random randomTime = new Random();

    // Philosopher's unique id to identify uniquely
    private int id;

    
    // Available chopsticks for Philosophers
    private Semaphore leftChopstick;
    private Semaphore rightChopstick;

   
    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    // Continouly think, pickup left and right chopstick eat then put down
    public void run() {
        try {
            while (true) {
                think();
                pickUpLeftChopstick();
                pickUpRightChopstick();
                eat();
                putDownChopsticks();
                
                
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher : " + id + " was interrupted.\n");
        }
    }

    
    private void think() throws InterruptedException {
        System.out.println("Philosopher : " + id + " is thinking.\n");
        System.out.flush();
        Thread.sleep(randomTime.nextInt(10));
    }

    
    private void pickUpLeftChopstick() throws InterruptedException{
         if(leftChopstick.availablePermits() ==0){
            System.out.println("Philosopher : " +id +" is WAITING for left  chopstick");
        }
        
        
        leftChopstick.acquire();
        System.out.println("Philosopher : " + id + " is HOLDING  left  chopstick.\n");
        System.out.flush();
       
        
    }


    private void pickUpRightChopstick()  throws InterruptedException{
        if(rightChopstick.availablePermits() ==0){
            System.out.println("Philosopher : " +id +" is  for right chopstick");
        }
        
        rightChopstick.acquire();
        System.out.println("Philosopher : " + id + " is HOLDING  right   chopstick.\n");
        System.out.flush();
        
        
    }


    private void eat() throws InterruptedException {
        System.out.println("Philosopher : " + id + " is eating.\n");
        System.out.flush();
        Thread.sleep(randomTime.nextInt(10));
    }

  
    private void putDownChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
    }
}