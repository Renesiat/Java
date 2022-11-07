package step.learning;


import step.learning.annotations.DemoClass;
import step.learning.annotations.EntryPoint;
import step.learning.threading.ThreadingDemo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //new DataType().run();
        //new Complex().run();
        //new Dictionary().run();
        //new Library().run();
        //new SerializationDemo().run();
        //new FilesDemo().run();
        //new FileNavigation().run();
        //new AnnotationsDemo().run();

        String packageName = Main.class.getPackage().getName();

        List<String> className = getClassNames(packageName);
        if (className == null) {
            System.out.println("Scanning error. Program terminated");
        }
        for (String classN : className) {
            System.out.println(className);
        }
        List<Class<?>> demoClasses = new ArrayList<>();
        for (String classN : className) {
            Class<?> theClass;
            try {
                theClass = Class.forName(classN);
            } catch (Exception ignored) {
                continue;
            }
            if (theClass.isAnnotationPresent(DemoClass.class)) {
                demoClasses.add(theClass);
            }
        }
        //Menu(demoClasses);

        new ThreadingDemo().demo();
    }

    private static void Menu(List<Class<?>> demoClasses) {

        System.out.println("Demo classes: ");
        int i = 1;
        for (Class<?> demoClass : demoClasses) {
            System.out.printf("%d. %s %n", i++, demoClass.getName());
        }
        System.out.println("0.Exit program\n" +
                "Make a choice:");
        Scanner kbScanner = new Scanner(System.in);
        int userChoice = -1;

        try {
            userChoice = kbScanner.nextInt();
        } catch (InputMismatchException ignored) {
            System.out.println("Incorrect input");
            return;
        }
        if (userChoice == 0) {
            return;
        }
        Class<?> exeClass = demoClasses.get(userChoice - 1);
        Method[] methods = exeClass.getDeclaredMethods();
        for (Method method : exeClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EntryPoint.class)) {
                try {
                    method.invoke(exeClass.getDeclaredConstructor().newInstance());
                } catch (Exception ex) {
                    System.out.println("Entry point error: " + ex.getMessage());
                }
            }

        }
        Menu(demoClasses);
    }


    private static List<String> getClassNames(String packageName) {
        //ClassLoader classLoader = Main.class.getClassLoader();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resourceUrl = classLoader.getResource(packageName.replace('.', '/'));


        if (resourceUrl == null) {
            System.out.println("Resource location error");
            return null;
        }
        //System.out.println(resourceUrl.getPath());
        File packageBase = new File(resourceUrl.getPath());
        File[] baseList = packageBase.listFiles();
        if (baseList == null) {
            System.out.println("Resource scanning error" + packageBase.getName());
            return null;
        }
        List<String> className = new ArrayList<>();
        for (File file : baseList) {
            if (file.isFile()) {
                String filename = file.getName();

                if (filename.endsWith(".class")) {
                    className.add(packageName + "." +
                            filename.substring(0, filename.lastIndexOf('.')));
                }
            } else if (file.isDirectory()) {
                File[] subList = file.listFiles();
                if (subList != null) {
                    for (File sub : subList) {
                        if (sub.isFile()) {
                            String filename = sub.getName();
                            if (filename.endsWith(".class")) {
                                System.out.println(packageName + "." + file.getName() + "." +
                                        filename.substring(0, filename.lastIndexOf('.')));

                            }
                        }

                    }
                }
            }

        }
        return className;
    }

}