import java.util.Scanner;

public class main {

    public static void converter(String og) {
         int fin = 0;
        for(int i = 0; i<og.length(); i++){
           
            char c = og.charAt(i);
            int asciiValue = (int) c;

            fin= fin+asciiValue;
        }

        fin = fin + 100;
        fin = fin * 139;

        System.out.println(fin);
    }
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

            System.out.println("Insert the word you want you pasword to be based on:");
            String og = sc.next();
            converter(og);


    }
}
