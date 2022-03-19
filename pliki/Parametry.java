package pliki;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parametry {
    private int ile_tur;
    private int pocz_ile_robów;
    private Program pocz_progr;
    private int pocz_energia;
    private int ile_daje_jedzenie;
    private int ile_rośnie_jedzenie;
    private int koszt_tury;
    private float pr_powielania;
    private float ułamek_energii_rodzica;
    private int limit_powielania;
    private float pr_usunięcia_instr;
    private float pr_dodania_instr;
    private float pr_zmiany_instr;
    private String[] spis_instr;
    private int co_ile_wypisz;

    private int x_planszy;
    private int y_planszy;
    private int[][] plansza;

    public int getCo_ile_wypisz() {
        return co_ile_wypisz;
    }

    public int getIle_tur() {
        return ile_tur;
    }

    public int getPocz_ile_robów() {
        return pocz_ile_robów;
    }

    public Program getPocz_progr() {
        return pocz_progr;
    }

    public int getPocz_energia() {
        return pocz_energia;
    }

    public int getIle_daje_jedzenie() {
        return ile_daje_jedzenie;
    }

    public int getIle_rośnie_jedzenie() {
        return ile_rośnie_jedzenie;
    }

    public int getKoszt_tury() {
        return koszt_tury;
    }

    public float getPr_powielania() {
        return pr_powielania;
    }

    public float getUłamek_energii_rodzica() {
        return ułamek_energii_rodzica;
    }

    public int getLimit_powielania() {
        return limit_powielania;
    }

    public float getPr_usunięcia_instr() {
        return pr_usunięcia_instr;
    }

    public float getPr_dodania_instr() {
        return pr_dodania_instr;
    }

    public float getPr_zmiany_instr() {
        return pr_zmiany_instr;
    }

    public String[] getSpis_instr() {
        return spis_instr;
    }

    public int getX_planszy() {
        return x_planszy;
    }

    public int getY_planszy() {
        return y_planszy;
    }

    public int[][] getPlansza() {
        return plansza;
    }

    public Parametry(String parametry, String świat) throws FileNotFoundException { //aktualizuję parametry do symulacji na podstawie danych
        Scanner odczyt = new Scanner(new File(parametry));
        int ile_parametrów = 0;
        while (odczyt.hasNextLine()) {
            String x = odczyt.nextLine();
            String[] oddzielone = x.split(" ");
            if(oddzielone.length != 2){
                System.out.println("Błędnie podany parametr - być może nie każdy parametr ma wpisaną wartość!");
                System.exit(1);
            }
            switch (oddzielone[0]){
                case "ile_tur"  :
                    ile_tur = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pocz_ile_robów"  :
                    pocz_ile_robów = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pocz_progr"  :
                    List<String> lista = new ArrayList<String>();
                    String[] pom = oddzielone[1].split("");
                    for (int i = 0; i < pom.length; i++) {
                        lista.add(pom[i]);
                    }
                    pocz_progr = new Program(lista);
                    ile_parametrów++;
                    break;
                case "pocz_energia"  :
                    pocz_energia = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "ile_daje_jedzenie"  :
                    ile_daje_jedzenie = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "ile_rośnie_jedzenie"  :
                    ile_rośnie_jedzenie = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "koszt_tury"  :
                    koszt_tury = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pr_powielania"  :
                    pr_powielania = Float.parseFloat(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "ułamek_energii_rodzica"  :
                    ułamek_energii_rodzica = Float.parseFloat(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "limit_powielania"  :
                    limit_powielania = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pr_usunięcia_instr"  :
                    pr_usunięcia_instr = Float.parseFloat(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pr_dodania_instr"  :
                    pr_dodania_instr = Float.parseFloat(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "pr_zmiany_instr"  :
                    pr_zmiany_instr = Float.parseFloat(oddzielone[1]);
                    ile_parametrów++;
                    break;
                case "spis_instr"  :
                    spis_instr = oddzielone[1].split("");
                    ile_parametrów++;
                    break;
                case "co_ile_wypisz"  :
                    co_ile_wypisz = Integer.parseInt(oddzielone[1]);
                    ile_parametrów++;
                    break;
                default:
                    System.out.println("Błędny parametr!");
                    System.exit(1);
            }
        }
        if(ile_parametrów != 15){
            System.out.println("Zła liczba parametrów!");
            System.exit(1);
        }
        odczyt.close();

        Scanner odczyt2 = new Scanner(new File(świat));
        int liczba_wierszy = 0;
        int liczba_kolumn = 0;
        int pom = 0;
        int wymiar_poprzedniej_linijki = 0;
        while (odczyt2.hasNextLine()){
            String x = odczyt2.nextLine();
            if(pom == 1){
                if(wymiar_poprzedniej_linijki != x.length()){
                    System.out.println("Niepoprawne długości wierszy!");
                    System.exit(1);
                }
            }
            liczba_kolumn = x.length();
            liczba_wierszy++;
            wymiar_poprzedniej_linijki = x.length();
            pom = 1;
        }
        x_planszy = liczba_kolumn;
        y_planszy = liczba_wierszy;
        if (liczba_kolumn <= 0 || liczba_wierszy <= 0){
            System.out.println("Zła liczba kolumn lub wierszy!");
            System.exit(1);
        }
        odczyt2.close();

        plansza = new int[liczba_kolumn][liczba_wierszy];
        Scanner odczyt3 = new Scanner(new File(świat));
        int y = 0;
        while (odczyt3.hasNextLine()) {

            String linijka = odczyt3.nextLine();
            String[] linijka_podzielona = linijka.split("");
            for(int x = 0; x < linijka.length(); x++){
                if (linijka_podzielona[x].equals("x")){
                    plansza[x][y] = 1; //1 - pole jest jedzeniowe
                }
                else{
                    plansza[x][y] = 0;
                }
            }
            y++;
        }
    }

}
