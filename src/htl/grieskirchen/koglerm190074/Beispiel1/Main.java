package htl.grieskirchen.koglerm190074.Beispiel1;

import com.sun.source.util.TaskEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static List<String[]> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        List<Integer> integerList= readCSV("numbers.csv");
       /* System.out.println("chunks:");
        int chunks = Integer.parseInt(s.nextLine());
        System.out.println("divider");
        int divider = Integer.parseInt(s.nextLine());

        int laenge = integerList.size() / chunks;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

        System.out.println(integerList.size());
        for (int i = 0; i < laenge; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                   int laengeRun = integerList.size()/laenge;
                    for (int j = 0; j < laengeRun; j++) {
                        if(integerList.get(j)%divider == 0){
                            //System.out.println(integerList.get(j));
                        }
                    }
                }
            });
        }*/
    }

    public static List<Integer> readCSV(String name) {
        List<Integer> intList = new ArrayList<>();
        File file = new File(name);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(name));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                list.add(zeile.split(":"));
               // System.out.println(zeile);
                for (String[] sArr : list) {
                    for (String s: sArr) {
                        boolean isInt = true;
                        int count = 0;
                        for (int i = 0; i < s.length(); i++) {
                            if(!Character.isDigit(s.charAt(i))){
                                isInt = false;
                                count++;
                            }if(i == s.length()-1){
                                if(count == 0){
                                    intList.add(Integer.parseInt(s));
                                }
                            }
                        }

                    }
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return intList;
    }


}
