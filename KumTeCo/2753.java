import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int year = scn.nextInt();
        int f = year % 4;
        int h = year % 100;
        int fh = year % 400;

        int result = 0;
        if(f == 0){
            if(h != 0){
                result = 1;
            }
            else if(fh == 0){
                result = 1;
            }
        }

        System.out.println(result);
    }
}
