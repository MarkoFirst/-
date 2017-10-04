import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        Thread thread;
        thread = new Thread(new Potok());
        thread.start();

        while (true) {
            thread = new Thread(new SecondPotok(in.nextInt(), in.nextInt()));
            thread.start();
        }

    }

    static class Potok extends Thread implements Runnable {


        public Potok() {
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                try {
                    System.out.println("Я работаю");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            interrupt();
        }
    }
    static class SecondPotok extends Thread implements Runnable {

        public int line1;
        public int line2;

        public SecondPotok(int line1, int line2) {
            this.line1 = line1;
            this.line2 = line2;
        }

        @Override
        public void run() {
                System.out.println(line1+line2);
                interrupt();
        }
    }
}