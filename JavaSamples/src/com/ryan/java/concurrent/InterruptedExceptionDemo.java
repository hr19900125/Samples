package com.ryan.java.concurrent;


public class InterruptedExceptionDemo {

    public static class RunThread extends Thread{
       
//        long time = 0;
//        
//        private void search(){
//            while(true){
//                Thread.sleep(millis)
//            }
//        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                System.out.println("Thread is catch InterruptedException ..");
                e.printStackTrace();
            }
            System.out.println("Thread is over ..");
        }
        
    }
    
    public static void main(String[] args){
        RunThread t1 = new RunThread();
        t1.start();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        t1.interrupt();
    }
}
