package step.learning;

public class DataType {
    public void Run(){
        System.out.println(ConsoleColors.PURPLE+"Data types demo"+ConsoleColors.RESET);
        byte bx = -120;//всі типи знакові! беззнакових непердбачено
        short sx = 20;
        int ix = 30;
        long lx = 40;
        float fx = 0.1f;//0.1 - double
        float fy = (float) 0.1;
        double dx = 0.1;

        char c='A'; // 2 byte UTF-16
        boolean b = true;
        System.out.println((char)60); //<
        System.out.println((int)'A'); //65

        String s1="He"+"llo";
        String s2 = "Hel"+"lo";
        if(s1==s2) System.out.println("==");
        else System.out.println("!=");

        String s3= new String("Hello");
        String s4 = new String("Hello");
        if(s3==s4) System.out.println("==");
        else System.out.println("!=");

        System.out.println(s3.hashCode()+" "+ s4.hashCode());

        System.out.println("Byte: " + bx);
        System.out.println("Short: " + sx);
        System.out.println("Int: " + ix);
        System.out.println("Long: " + lx);
        System.out.println("Float: " + fx);
        System.out.println("Float: " + fy);
        System.out.println("Double: " + dx);
    }
}
