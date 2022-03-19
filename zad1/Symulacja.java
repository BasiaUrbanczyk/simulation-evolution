package zad1;

import pliki.Parametry;
import pliki.Plansza;

import java.io.FileNotFoundException;


public class Symulacja {

    public static void main(String[] args) throws FileNotFoundException {
        Parametry p = new Parametry(args[1], args[0]);
        Plansza plansza = new Plansza(p);

        System.out.println("Opis stanu symulacji. Właśnie się ona rozpoczęła, trwa jej " + 0 + " tura. Wymiary planszy to: " + p.getX_planszy() + "/" + p.getY_planszy() + ". Obecnie żywych jest " + plansza.getLista_robów().size() + " robów. Ich stany są następujące: "  );
        for(int k = 0; k < plansza.getLista_robów().size(); k++){
            String pomocniczy = "";
            for(int z = 0; z < plansza.getLista_robów().get(k).getKod().getKod().size(); z++){
                pomocniczy = pomocniczy + plansza.getLista_robów().get(k).getKod().getKod().get(z);
            }
            System.out.println("Rob nr: " + k + ", kod: " + pomocniczy +", x: " + plansza.getLista_robów().get(k).getX() + ", y: " + plansza.getLista_robów().get(k).getY() + ", kierunek: " + plansza.getLista_robów().get(k).getKierunek() + ", energia: " + plansza.getLista_robów().get(k).getIle_energii());
        }


        int licznik = 0;
        for (int i = 0; i < p.getIle_tur(); i++){
            if(licznik == p.getCo_ile_wypisz()){
                System.out.println("Opis stanu symulacji. Skończyła się jej " + i + " tura. Wymiary planszy to: " + p.getX_planszy() + "/" + p.getY_planszy() + ". Obecnie żywych jest " + plansza.getLista_robów().size() + " robów. Ich stany są następujące: "  );
                for(int k = 0; k < plansza.getLista_robów().size(); k++){
                    String pomocniczy = "";
                    for(int z = 0; z < plansza.getLista_robów().get(k).getKod().getKod().size(); z++){
                        pomocniczy = pomocniczy + plansza.getLista_robów().get(k).getKod().getKod().get(z);
                    }
                    System.out.println("Rob nr: " + k + ", kod: " + pomocniczy +", x: " + plansza.getLista_robów().get(k).getX() + ", y: " + plansza.getLista_robów().get(k).getY() + ", kierunek: " + plansza.getLista_robów().get(k).getKierunek() + ", energia: " + plansza.getLista_robów().get(k).getIle_energii());
                }
                licznik = 0;
            }
            int min_wiek, max_wiek, suma_wiek, min_energia, max_energia, suma_energia, min_program, max_program, suma_program;
            if(plansza.getLista_robów().size() > 0){
                plansza.przejdź_roby();

            }
            if(plansza.getLista_robów().size() > 0){
                min_wiek = plansza.getLista_robów().get(0).getWiek();
                max_wiek = plansza.getLista_robów().get(0).getWiek();
                suma_wiek = 0;
                min_energia = plansza.getLista_robów().get(0).getIle_energii();
                max_energia = plansza.getLista_robów().get(0).getIle_energii();
                suma_energia = 0;
                min_program = plansza.getLista_robów().get(0).getKod().getKod().size();
                max_program = plansza.getLista_robów().get(0).getKod().getKod().size();
                suma_program = 0;
            }
            else{
                min_wiek = 0;
                max_wiek = 0;
                suma_wiek = 0;
                min_energia = 0;
                max_energia = 0;
                suma_energia = 0;
                min_program = 0;
                max_program = 0;
                suma_program = 0;
            }
            plansza.przejdź_planszę();

            for (int j = 0; j < plansza.getLista_robów().size(); j++){
                if(plansza.getLista_robów().get(j).getWiek() < min_wiek)
                    min_wiek = plansza.getLista_robów().get(j).getWiek();
                if(plansza.getLista_robów().get(j).getWiek() > max_wiek)
                    max_wiek = plansza.getLista_robów().get(j).getWiek();
                suma_wiek = suma_wiek + plansza.getLista_robów().get(j).getWiek();

                if(plansza.getLista_robów().get(j).getIle_energii() < min_energia)
                    min_energia = plansza.getLista_robów().get(j).getIle_energii();
                if(plansza.getLista_robów().get(j).getIle_energii() > max_energia)
                    max_energia = plansza.getLista_robów().get(j).getIle_energii();
                suma_energia = suma_energia + plansza.getLista_robów().get(j).getIle_energii();

                if(plansza.getLista_robów().get(j).getKod().getKod().size() < min_program)
                    min_program = plansza.getLista_robów().get(j).getKod().getKod().size();
                if(plansza.getLista_robów().get(j).getKod().getKod().size() > max_program)
                    max_program = plansza.getLista_robów().get(j).getKod().getKod().size();
                suma_program = suma_program + plansza.getLista_robów().get(j).getKod().getKod().size();

            }

            float średni_program, średni_wiek, średnia_energia;
            if(plansza.getLista_robów().size() > 0){
                średnia_energia = (float)suma_energia/ (float) plansza.getLista_robów().size();
                średni_wiek = (float)suma_wiek/(float)plansza.getLista_robów().size();
                średni_program = (float)suma_program/(float)plansza.getLista_robów().size();
            }
            else{
                średni_program = 0;
                średnia_energia = 0;
                średni_wiek = 0;
            }

            System.out.println(i+1 + ", rob: " +  plansza.getLista_robów().size()  + ", żyw: " + plansza.getIle_pól_z_jedzeniem().getIle_pól_z_jedzeniem() + ", prg: " + min_program + "/" + średni_program  + "/" + max_program + ", energ: " + min_energia + "/" + średnia_energia  + "/" + max_energia + ", wiek: " + min_wiek + "/" + średni_wiek  + "/" + max_wiek);
            licznik++;
        }
        System.out.println("Opis stanu symulacji. Właśnie skończyła się jej ostatnia - " + p.getIle_tur() + " tura. Wymiary planszy to: " + p.getX_planszy() + "/" + p.getY_planszy() + ". Pozostało żywych " + plansza.getLista_robów().size() + " robów. Ich stany są następujące: "  );
        for(int k = 0; k < plansza.getLista_robów().size(); k++){
            String pomocniczy = "";
            for(int z = 0; z < plansza.getLista_robów().get(k).getKod().getKod().size(); z++){
                pomocniczy = pomocniczy + plansza.getLista_robów().get(k).getKod().getKod().get(z);
            }
            System.out.println("Rob nr: " + k + ", kod: " + pomocniczy +", x: " + plansza.getLista_robów().get(k).getX() + ", y: " + plansza.getLista_robów().get(k).getY() + ", kierunek: " + plansza.getLista_robów().get(k).getKierunek() + ", energia: " + plansza.getLista_robów().get(k).getIle_energii());
        }
    }
}
