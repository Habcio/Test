package app_zaliczenie;


import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class mat implements Callable<String> {

    private String plik;

    public mat(String plik) {
        this.plik = plik;
    }

    @Override
    public String call() throws Exception {
        try {
            File file = new File("x/" + plik + ".txt");
            Scanner in = new Scanner(file);
            int suma = 0;
            int liczba = 0;
            while (in.hasNextLine()) {
                 String linia = in.nextLine();
                 liczba = Integer.parseInt(linia);
                 suma += liczba;
            }
           // return (Thread.currentThread().getName() + "# " + "Wynik pliku: " + plik + ": = " + suma );
            return Integer.toString(suma);
           
        } catch (Exception e) {
            e.printStackTrace();
            return (Thread.currentThread().getName() + "# " + "blad pliku" + plik);
        }
    }
}