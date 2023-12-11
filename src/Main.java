import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Podaj nazwę pliku, który chcesz wczytać: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        if(Objects.equals(fileName, "")){
            //System.out.println("fileName");
            fileName = "wizytowki.csv";
        }

        boolean orEnd = false;
        while(!orEnd){
            System.out.println("1: Wyświetlenie wszystkich wizytówek");
            System.out.println("2: Dodanie nowej wizytówki");
            System.out.println("3: Wyświetlenie wizytówki dla osób o określonym nazwisku");
            System.out.println("4: Zakończenie działania programu");

            String option = reader.readLine();
            switch (option){
                case "1":
                    printBusinessCard(fileName);
                    break;
                case "2":
                    createAndSaveBusinessCard(fileName);
                    break;
                case "3":
                    printBusinessCard(fileName,reader.readLine());
                    break;
                case "4":
                    orEnd=true;
                    break;
                default:
                    System.out.println("Taka opcja nie istnieje.");
            }
        }
    }

    public static void createAndSaveBusinessCard(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Podaj dane.");

        System.out.println("Imię:");
        String firstName = reader.readLine();

        System.out.println("Nazwisko:");
        String lastName = reader.readLine();

        System.out.println("Tel.:");
        String phoneNumber = reader.readLine();

        System.out.println("e-mail:");
        String email = reader.readLine();

        String line = firstName+";"+lastName+";"+phoneNumber+";"+email;
        writeLineToFile(fileName,line);
    }

    public static void writeLineToFile(String fileName, String line){
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(line+"\n");
            fw.close();
        }
        catch (IOException e){
            System.out.println("Kurka! Wystąpił błąd.");
            e.printStackTrace();
        }
    }

    public static void printBusinessCard(String fileName, String lastName){
        String result;
        try {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()){
                result = myScanner.nextLine();
                var tabOfResult = result.split(";");
                if(Objects.equals(lastName,tabOfResult[1]) || lastName==null) {
                    System.out.println("Imię: "+tabOfResult[0]);
                    System.out.println("Nazwisko: "+tabOfResult[1]);
                    System.out.println("Tel.: "+tabOfResult[2]);
                    System.out.println("e-mail: "+tabOfResult[3]);
                    System.out.println("");
                }
            }
            myScanner.close();
        }
        catch (IOException e){
            System.out.println("Kurka! Wystąpił błąd.");
            e.printStackTrace();
        }
    }

    public static void printBusinessCard(String fileName){
        printBusinessCard(fileName,null);
    }
    //}
}