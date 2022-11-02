package step.learning.threading;

import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;

import java.util.concurrent.*;

@DemoClass
public class SyncDemo {
    @EntryPoint
    public void demo() {
        System.out.println( "Synchronization demo" ) ;
        sum = 100 ;
        threads = 12 ;
        for( int i = 0; i < 12; ++i ) {
            new Thread( plus10percentFin ).start() ;
        }
        // Задачі та багатозадачність
        // Thread Pool - засіб керування виконанням задач
        ExecutorService pool =   // контейнер для задач, який підтримує у одночасному
                Executors.newFixedThreadPool(3) ;  // виконанні 3 задачі

        pool.submit(   // додавання задачі до пулу, виконання почнеться за першої нагоди
                () -> System.out.println( "Pool works" )
        ) ;

        Future<String>   // тип задачі, що повертає String ( C# - Task<>, JS - Promise )
                task = pool.submit(   // задача повертається як рез-т додавання у пул
                new Callable<String>() {  // Callable<T> - як Runnable, тільки з поверненням Т
                    @Override
                    public String call() {
                        return "Hello" + " from Callable" ;
                    }
                }
        ) ;

        try {
            String res = task.get() ;  // await res (C#, JS);  res.Result (C#)
            System.out.println( res ) ;

            System.out.println( pool.submit(
                    () -> "Hello" + " Arrow Callable"
            ).get() ) ;   // sout( await ( async () => "Hello" + " Arrow Callable" ) )
        }
        catch( Exception ex ) {
            System.out.println( ex.getMessage() ) ;
        }

        pool.shutdown() ;
    }
    private int threads ;  // лічильник активних потоків
    private double sum ;
    private final Runnable plus10percent = () -> {
        double tmp = sum ;               // Проблема - майже всі виводять 110
        tmp = tmp * 1.10 ;               // Чому? Є відмінність у моментах
        System.out.println(              // читання sum (tmp = sum)  та запису
                "Current sum = " + tmp ) ;   // (sum = tmp). Протягом цього часу
        sum = tmp ;                      // інші потоки звертаються до
    } ;                                  // "незбереженних" даних
    // !! якщо змінити на sum *= 1.10, то !! проблема не зникне, але її
    // імовірність значно зменшиться

    private final Object sumLocker = new Object() ;  // об'єкт синхронізації
    // будь-який ref-type має у собі "критичну секцію" (сигнальний системний
    // ресурс синхронізації). Для задач програми можна замість запиту у системи
    // ресурсу (мьютекса чи кр.секції) використати довільний об'єкт.
    private final Runnable plus10percent1 = () -> {
        synchronized( sumLocker ) {   // аналог lock()  у  C#
            double tmp = sum ;               // Працює як треба, але
            tmp = tmp * 1.10 ;               // все тіло методу знаходиться
            System.out.println(              // у синхроблоці, що нівелює
                    "Current sum = " + tmp ) ;   // усю паралельність. Наслідок -
            sum = tmp ;                      // всі суми виводяться послідовно
        }                                    // ! синхроблок має бути мінімальним.
    } ;

    private final Runnable plus10percent2 = () -> {
        synchronized( sumLocker ) {   // Транзакція організована - sum
            sum = sum * 1.10 ;        // читається та пишеться синхронно
        }
        System.out.println(           // поза транзакцією - можливе
                "Current sum = " + sum ); // змінене іншим потоком значення
    } ;

    private final Runnable plus10percent3 = () -> {
        double tmp ;
        synchronized( sumLocker ) {   // Транзакція організована - sum
            tmp = sum = sum * 1.10 ;  // читається та пишеться синхронно
        }                             // + зберігає локальну копію

        System.out.println(           // поза транзакцією - використання
                "Current sum = " + tmp ); // збереженої локальної  копії
    } ;

    private final Runnable plus10percentFin = () -> {
        double tmp ;
        boolean isLast ;
        synchronized( sumLocker ) {
            tmp = sum = sum * 1.10 ;
            isLast = --threads == 0 ;
        }
        // if(isLast)
        System.out.printf( "Current sum = %.2f %s %n", tmp, (isLast?"final":"") ) ;

        // if( isLast ) System.out.println(  ) ; - інша операція - змішується з
        // else System.out.println() ;              з іншими потоками
    } ;
}