package htl.grieskirchen.koglerm190074.Beispiel2;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Zahl eingeben");
        int number = Integer.parseInt(s.nextLine());
        int ergebnis = 0;

        Future<Integer> future = new CompletableFuture<>();
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(8);

        int threads = 0;

        if (number % 100 == 0) {
            threads = number / 100;
        } else {
            threads = (number / 100) + 1;
        }


        int max = 0;
        int min = -99;

        String stringNumber = String.valueOf(number);
        String[] digits2 = stringNumber.split("(?<=.)");

        for (int i = 0; i <threads; i++) {

            if(i==threads-1){
                int ten = Integer.parseInt(digits2[1])*10;
                int one = Integer.parseInt(digits2[2]);
                min = min+100;
                max = max+ten+one;
            }else{
                min = min+100;
                max = max+100;
            }



            final int max2 = max;
            final int min2 = min;
            future = executor.submit((() -> {
                int numb = 0;
                for (int j = min2; j <= max2; j++) {
                    numb+=j;
                }
                System.out.println(numb);
                return numb;
            }));
        /*future = executor.submit((() -> {

            int count = 0;

            for (int i = 100; i < number; i+=100) {
                if(number>i){
                    count++;
                }
            }
            int[] arr = new int[count+1];


            String stringNumber = String.valueOf(number);
            String[] digits2 = stringNumber.split("(?<=.)");

            int num = Integer.parseInt(digits2[digits2.length-2]);
            num*=10;
            num+=Integer.parseInt(digits2[digits2.length-1]);

            int numberCount = 0;
            int x = 100;
            for (int i = 0; i < arr.length; i++) {
                if(i == arr.length-1){
                    x-=100;
                    for (int j = x+1; j <= x+num; j++) {
                        arr[i] += j;

                    }
                }else{
                    if(i >0){
                        for (int j = 1; j<=i; j++) {
                            arr[i]-=arr[j-1];
                        }

                    }

                    for (int j = 1; j <= x; j++) {
                        arr[i] += j;

                    }
                    x+=100;
                }

            }
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum+= arr[i];
            }
            /*System.out.println("Sum: "+sum);

            return sum;
        }));*/
            try {
                ergebnis += future.get().intValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        System.out.println(ergebnis);

        executor.shutdown();
    }


}
