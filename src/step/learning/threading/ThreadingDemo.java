package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

@DemoClass()
public class ThreadingDemo {
    @EntryPoint
    public void demo() {
        new PrintThread().start();
        new ArgThread("Arg 1").start();
        ArgThread argThread =new ArgThread();
        argThread.setArg("Arg 2");
        argThread.start();
        new Thread(){
            @Override
            public void run(){
                System.out.println("Anon thread");
            }
        }.start();
        System.out.println(ConsoleColors.YELLOW_BRIGHT + "Threading demo" + ConsoleColors.RESET);

    }

    class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from thread");
        }
    }

    static class ArgThread extends Thread {
        private String arg;

        public ArgThread(String arg) {
            this.arg = arg;
        }

        public ArgThread() {
            this.arg = "";
        }
        public void setArg (String arg) {
            this.arg = arg;
        }
        @Override
        public void run(){
            System.out.println("Arg thread: "+this.arg);
        }
    }
}
