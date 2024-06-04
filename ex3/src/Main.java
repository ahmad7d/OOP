//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        List<Integer> lst1 = new ArrayList<>();
        List<Integer> lst2 = new ArrayList<>();

        lst1.add(1);
        lst1.add(2);
        lst1.add(3);
        lst1.add(4);


        lst2 = lst1;


        for (int i=0 ; i<4 ; ++i){
            System.out.println(lst2.get(i));
        }
    }


    private static int getNewImageDimension(int dimension){
        while (!isPowerOfTwo(dimension)){
            dimension++;
        }
        return dimension;
    }
    private static boolean isPowerOfTwo(int dimension) {
        if (dimension == 0)
            return false;

        return dimension != 0 && ((dimension & (dimension - 1)) == 0);
    }
}