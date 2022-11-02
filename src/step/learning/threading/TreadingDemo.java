package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

@DemoClass
public class ThreadingDemo {
    private String str ;

    @EntryPoint
    public void demo() {
        new PrintThread().start() ;  // ! start запускає run у новому потоці, якщо .run(), то запуск синхронний
        new ArgThread( "Arg 1" ).start() ;

        ArgThread argThread = new ArgThread() ;
        argThread.setArg( "Arg 2" ) ;
        argThread.start() ;

        new Thread() {   // розширення класу "на льоту" - анонімний клас
            @Override
            public void run() {
                System.out.println( "Anon thread" ) ;
            }
        }.start() ;

        Runnable runnable1 = new Runnable() {   // іменований об'єкт анонімного класу
            @Override                           // імплементація інтерфейсу "на льоту"
            public void run() {
                System.out.println( "Runnable 1 thread " + str ) ;
            }
        } ;
        new Thread( runnable1 ).start() ;

        str = "str1" ;
        Runnable runnable2 = () -> {    // Стрілкова дефініція імплементації інтерфейсу
            System.out.println( "Runnable 2 thread " + str ) ;
        } ;
        new Thread( runnable2 ).start() ;

        str = "str2" ;
        new Thread( () -> System.out.println( "Runnable anon thread " + str ) ).start() ;

        new Thread( this::printHello ).start() ;  // :: - Runnable from method

        System.out.println(
                ConsoleColors.YELLOW_BOLD_BRIGHT + "Threading demo" + ConsoleColors.RESET ) ;
    }
    // Д.З. Організувати цикл, що запускає N потоків, кожен з яких виводить свій номер
    // N вводить користувач
    private void printHello() {
        String msg = "Print" ;
        System.out.println( "Hello from " + msg ) ;
    }

    static class PrintThread extends Thread {
        @Override
        public void run() {
            System.out.println( "Hello from thread" ) ;
        }
    }

    static class ArgThread extends Thread {
        private String arg ;
        public ArgThread( String arg ) {
            this.arg = arg ;
        }
        public ArgThread() {
            this.arg = "" ;
        }
        public void setArg( String arg ) {
            this.arg = arg ;
        }
        @Override
        public void run() {
            System.out.println( "Arg thread: " + this.arg ) ;
        }
    }
}