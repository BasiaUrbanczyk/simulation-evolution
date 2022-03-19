package pliki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final int x;
    private final int y;
    private Pole[][] plansza_jedzeniowa;
    private List<Rób> lista_robów;
    private Parametry parametry;
    private Pola_z_jedzeniem ile_pól_z_jedzeniem;

    public Plansza(Parametry parametry) {
        this.x = parametry.getX_planszy();
        this.y = parametry.getY_planszy();
        this.ile_pól_z_jedzeniem = new Pola_z_jedzeniem(0);
        this.plansza_jedzeniowa = zamiana_planszy(parametry.getPlansza(), parametry.getX_planszy(), parametry.getY_planszy(), parametry.getIle_rośnie_jedzenie());
        this.lista_robów = stwórz_roby(parametry);
        this.parametry = parametry;
    }


    public List<Rób> getLista_robów() {
        return lista_robów;
    }

    public Pola_z_jedzeniem getIle_pól_z_jedzeniem() {
        return ile_pól_z_jedzeniem;
    }

    public void przejdź_roby(){ //metoda przechodzi po liście robów, rusza każdego o jedną instrukcję oraz ewentualnie rozmnaża go lub zabija
        int i = lista_robów.size()-1;

        while(i >= 0){
            if(lista_robów.get(i).rusz_się() == false){
                lista_robów.remove(i);
            }
            else if (lista_robów.get(i).powiel_się() == true){
                int pom = lista_robów.get(i).getKierunek();
                int kierunek;
                if (pom == 1)
                    kierunek = 3;
                else if(pom == 2)
                    kierunek = 4;
                else if(pom == 3)
                    kierunek = 1;
                else
                    kierunek = 2;
                int energia_pocz = lista_robów.get(i).getIle_energii();
                int energia = (int) (lista_robów.get(i).getIle_energii() * parametry.getUłamek_energii_rodzica());
                lista_robów.get(i).setIle_energii(energia_pocz - energia);
                dodaj_roba(lista_robów.get(i).getX(), lista_robów.get(i).getY(), kierunek, energia, lista_robów.get(i).getKod());
            }
            i--;
        }
    }

    public void przejdź_planszę(){ //metoda przechodzi po wszyzstkich polach planszy, w każdym aktualizując jego ewentualny czas oczekiwania na jedzenie po minięciu tury
        for(int wiersze = 0; wiersze < y; wiersze++){
            for(int kolumny = 0; kolumny < x; kolumny++){
                int czy_zregenerowalo = plansza_jedzeniowa[kolumny][wiersze].minięcie_tury();
                if(czy_zregenerowalo == 1){ //aktualizuje liczbę pól z jedzeniem (sprawdza, czy podczass tury któreś się zregenerowało)
                    int pom = ile_pól_z_jedzeniem.getIle_pól_z_jedzeniem();
                    ile_pól_z_jedzeniem.setIle_pól_z_jedzeniem(pom + 1);
                }
            }
        }
    }

    public void dodaj_roba(int x, int y, int kierunek, int energia, Program kod){ //metoda dodaje roba do listy
        lista_robów.add( new Rób(x, y, kierunek, energia, kod.mutuj(parametry), parametry, plansza_jedzeniowa, ile_pól_z_jedzeniem));
    }



    private List<Rób> stwórz_roby(Parametry parametry){ //metoda tworzy listę robów (początkową), losując ich początkowe położenia oraz kierunki
        List<Rób> lista_robów = new ArrayList<Rób>();
        for(int i = 0; i < parametry.getPocz_ile_robów(); i++){
            Random rand = new Random();
            int a = rand.nextInt(parametry.getX_planszy());
            int b = rand.nextInt(parametry.getY_planszy());
            int c = rand.nextInt(4) + 1;
            lista_robów.add(new Rób(a, b, c, parametry.getPocz_energia(), parametry.getPocz_progr(), parametry, plansza_jedzeniowa, ile_pól_z_jedzeniem));
        }
        return lista_robów;
    }


    private Pole[][] zamiana_planszy(int[][] pocz_plansza, int x, int y, int ile_rośnie_jedzenie){//metoda tworzy planszę złożoną z pól żywieniowych lub pustych na podstawie początkowej planszy wygenerowanej na podstawie parametrów
        Pole[][] plansza = new Pole[x][y];
        for(int a = 0; a < y; a++){
            for(int b = 0; b < x; b++){
                if(pocz_plansza[b][a] == 1){
                    plansza[b][a] = new Pole_żywnieniowe(ile_rośnie_jedzenie);
                    int pom = ile_pól_z_jedzeniem.getIle_pól_z_jedzeniem();
                    ile_pól_z_jedzeniem.setIle_pól_z_jedzeniem(pom + 1);
                }
                else{
                    plansza[b][a] = new Pole_zwykłe();
                }
            }
        }
        return plansza;
    }
}
