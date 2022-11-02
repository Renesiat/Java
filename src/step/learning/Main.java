package step.learning;


public class Main {
    public static void main(String[] args) {
        //new DataType().Run();
        //new Complex().Run();
        //new Dictionary().Run();
        //new Library().Run();
        //new SerializationDemo().Run();
        //new FilesDemo().Run();
        //new FileNavigation().Run();
        //new AnnotationsDemo().Run();
        Injector injector = Guice.createInjector( new ConfigModule() ) ;
         app = injector.getInstance( App.class ) ;  // Resolving
        app.run() ;
    }
}