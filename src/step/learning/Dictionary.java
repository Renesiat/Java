package step.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    Map<String, String> dictionary;

    public void Print() {
        Dictionary_Collection();
        System.out.print("English-Ukrainian dictionary\n" +
                "    1. View all\n" +
                "    2. Translate english word\n" +
                "    3. Translate ukrainian word\n" +
                "    4. Add word\n" +
                "    0. Menu\n");
        System.out.print("Enter choice: ");
        Scanner action = new Scanner(System.in);
        int act = action.nextInt();

        switch (act) {
            case (1):
                OutPut_Dictionary();
            case (2):
                Eng_To_Ukr();
            case (3):
                Ukr_To_Eng();
            case (4):
                Add_Word();
            case (0):
                Print();
        }
    }

    public void Dictionary_Collection() {
        dictionary = new HashMap<>();
        dictionary.put("about", "про");
        dictionary.put("above", "вище");
        dictionary.put("accept", "приймати");
        dictionary.put("account", "рахунок");
        dictionary.put("across", "через");
        dictionary.put("act", "діяти");
        dictionary.put("action", "дія");
        dictionary.put("activity", "діяльність");
        dictionary.put("add", "додавати");
        dictionary.put("address", "адреса");
        dictionary.put("afraid", "наляканий");
        dictionary.put("after", "після");
        dictionary.put("afternoon", "день");
        dictionary.put("again", "знову");
        dictionary.put("against", "проти");
        dictionary.put("age", "вік");
        dictionary.put("ago", "тому");
        dictionary.put("agree", "погоджуватися");
        dictionary.put("ahead", "попереду");
        dictionary.put("air", "повітря");
        dictionary.put("airplane", "літак");
        dictionary.put("all", "все");
        dictionary.put("allow", "дозволяти");
        dictionary.put("almost", "майже");
    }

    public void OutPut_Dictionary() {
        for (String key :
                dictionary.keySet()) {
            System.out.printf("%s-%s%n", key, dictionary.get(key));
        }
    }

    public void Eng_To_Ukr()
    {
        Scanner kbScanner = new Scanner( System.in);
        String userInput = kbScanner.nextLine();
        System.out.printf("%s-%s%n", userInput, dictionary.get(userInput));
    }
    public void Ukr_To_Eng()
    {
        Scanner kbScanner = new Scanner( System.in);
        String value = kbScanner.nextLine();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().equals(value)) {
                System.out.printf("%s-%s%n", value, entry.getKey());
            }
        }

    }
    public void Add_Word(){
        System.out.print("Enter english word: ");
        Scanner key = new Scanner( System.in);
        String eng_word = key.nextLine();
        System.out.print("Enter ukrainian word: ");
        Scanner value = new Scanner( System.in);
        String ukr_word = value.nextLine();
        dictionary.put(eng_word, ukr_word);
    }
    public void Run() {
        Print();
    }

}
