//Made by Dias Muratbayev
import java.util.*;
import java.io.*;
public class Book {
    public static void main(String[] args){
        printall();
    }

    public static void addNew(String name,long number){
        System.out.println("New contact --> "+ name + " save and his/her number is : "+ number );

        File file = new File ("file.txt");
        try(PrintWriter pw = new PrintWriter(new FileWriter("file.txt",true))){
             pw.println(name+ ":" + number);
    }catch(IOException e){
        System.out.print(e.getMessage());
        }
    }

    public static String [] searchName(String name){
        
        try(Scanner in  = new Scanner(new File("file.txt"))){
            String s[] = new String [0];
            boolean foundPerson = false;
            while(in.hasNextLine()){
                s = in.nextLine().split(":");
                if(s[0].equals(name)){
                System.out.println("Yes you have this contact --> " + name);
                foundPerson = true;
                break;
                }
            }
            if(!foundPerson){
                System.out.println("Sorry, we could find name --> "+ name);
                s = null;
            }
            return s;

        }catch(IOException e ){
            System.out.println(e.getMessage());
        }
        return null;
        
    }

    public  static void printal() throws EOFException {
        try(Scanner sc = new Scanner(new FileInputStream("file.txt"))){
        while (sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }catch(IOException e ){
        System.out.println(e.getMessage());
    }
    }

    public static void EditCon (){

    }
    public static void deleteServer(int number1) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            File file = new File("file.txt");
            String fileToWrite = "file.txt";
            reader = new BufferedReader(new FileReader(file));
            writer = new PrintWriter(new FileWriter(fileToWrite));
            int current = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (current != number1) {
                    writer.println(line);
                }
                current++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void AllDelete(){
        try {
            FileWriter fstream1 = new FileWriter("file.txt");// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
             out1.close(); // закрываем
             } catch (Exception e) 
                {System.err.println("Error in file cleaning: " + e.getMessage());}
    }

    public static String [] searchNumber(String number){
        try(Scanner in  = new Scanner(new File("file.txt"))){
            String s[] = new String [0];
            boolean foundNumber = false;
            while(in.hasNextLine()){
                s = in.nextLine().split(":");
                if(s[1].equals(number)){
                System.out.println("Yes you have this number --> " + number);
                System.out.println("His/her name is " + s[0]);
                foundNumber = true;
                break;
                }
            }
            if(!foundNumber){
                System.out.println("Sorry, we could find number --> "+ number);
                s = null;
            }
            return s;

        }catch(IOException e ){
            System.out.println(e.getMessage());
        }
        return null;
        
    }
        
    

    public static void printall(){
    try(Scanner in = new Scanner(System.in)){

    do{
    System.out.println("####################################################################");
    System.out.println("Hello user! What would you like to do? ");
    System.out.println("1.	Add new contact ");
    System.out.println("2.	Search contact based on name ");
    System.out.println("3.	Search contact based on phone number");
    System.out.println("4.	Edit contact ");
    System.out.println("5.	Delete contact based on name");
    System.out.println("6.	Delete all contacts");
    System.out.println("7.	Print all contack");
    System.out.println("####################################################################");


   // System.out.println("7.	Validate contacts name format ");
   // System.out.println("8.	Validate contacts phone number format ");
   //  System.out.println("9.	Validate contacts e-mail format ");

    int choise = in.nextInt();
    in.nextLine();
    
    switch(choise){
        case 1:

            System.out.println("What contact you want to save");
            String name = in.next();
            System.out.println("His/her number");
            long number = in.nextLong();
            in.nextLine();
            addNew(name,number);
            break;

        case 2:
        System.out.println("What contact you want to find");
        searchName(in.nextLine());
            break;

        case 3:
        System.out.println("What contact you want to find");
        searchNumber(in.nextLine());
            break;
        case 4:

            break;
        case 5:
        System.out.println("What contact you want to delete");
        int number1 = in.nextInt();
        deleteServer(number1);

            break;
        case 6:
        System.out.println("Everythig is clean");
        AllDelete();

            break;
        case 7:
            printal();
            break;
    }
    System.out.println("Do you wish to perform another action (Y/N)");
    if(in.next().toLowerCase().charAt(0) != 'y')
    break;
    }while(true);
}catch(Exception e){
    System.out.println(e.getMessage());
}
    }
}