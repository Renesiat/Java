package step.learning.serial;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationDemo {
    private final String OBJ_FILE = "data.ser";
    private final String LIST_FILE = "list.ser";
    public void Run(){
        //serialize();
        //deserialize();
        //serializeList();
        deserializeList();
    }
    public void deserializeList(){
        try(FileInputStream file = new FileInputStream(LIST_FILE)){
            ObjectInputStream ois = new ObjectInputStream(file);
            @SuppressWarnings("unchecked")
            List<DataObject> list = (List<DataObject>) ois.readObject();

            for (DataObject data:list){
                System.out.println(data);
            }

        }catch(Exception ex){
            System.out.println("Deserialize error: " + ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
    public void serializeList(){
        List<DataObject> list = new ArrayList<>();
        list.add(new DataObject(1));
        list.add(new DataObject(2));
        list.add(new DataObject(3));
        list.add(new DataObject(4));
        list.add(new DataObject(5));
        try(FileOutputStream file = new FileOutputStream(LIST_FILE))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(list);
            oos.flush();
        }catch (IOException ex){
            System.out.println("Serialize error:" + ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
    public void deserialize(){
        try(FileInputStream file=new FileInputStream(OBJ_FILE)){
            ObjectInputStream ois = new ObjectInputStream(file);
            while(file.available()>0){
                DataObject data = (DataObject) ois.readObject();
                System.out.println(data);
            }
        }catch(Exception ex){
            System.out.println("Deserialize error: "+ ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
    public void serialize(){
        DataObject data1 = new DataObject();
        DataObject data2 = new DataObject(20);
        DataObject data3 = new DataObject(30,30.0);
        DataObject data4 = new DataObject(40,40.0,"Hello",100);

        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data4);
        try(FileOutputStream file = new FileOutputStream(OBJ_FILE)){
            ObjectOutputStream oss = new ObjectOutputStream(file);
            oss.writeObject(data1);
            oss.writeObject(data4);
            oss.flush();
        }catch (IOException ex){
            System.out.println("Serialize error: "+ex.getMessage());
            return;
        }
        System.out.println("---Done---");
    }
}
