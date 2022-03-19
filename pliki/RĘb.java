package pliki;

import java.util.Random;

public class Rób {
    private int x;
    private int y;
    private int kierunek; //1 - góra, 2 - prawo, 3 - dół, 4 - lewo
    private int ile_energii;
    private Program kod;
    private final Parametry parametry;
    private int wiek;
    private Pole[][] plansza_jedzeniowa;
    private Pola_z_jedzeniem ile_pól_z_jedzeniem;

    public Rób(int x, int y, int kierunek, int ile_energii, Program kod, Parametry parametry, Pole[][] plansza_jedzeniowa, Pola_z_jedzeniem ile_pól_z_jedzeniem) {
        this.x = x;
        this.y = y;
        this.kierunek = kierunek;
        this.ile_energii = ile_energii;
        this.kod = kod.mutuj(parametry);
        this.parametry = parametry;
        this.wiek = 0;
        this.plansza_jedzeniowa = plansza_jedzeniowa;
        this.ile_pól_z_jedzeniem = ile_pól_z_jedzeniem;
    }


    public int getWiek() {
        return wiek;
    }

    public int getIle_energii() {
        return ile_energii;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKierunek() {
        return kierunek;
    }

    public Program getKod() {
        return kod;
    }

    public void setIle_energii(int ile_energii) {
        this.ile_energii = ile_energii;
    }

    public void jedz(){
        ile_energii = ile_energii + parametry.getIle_daje_jedzenie();
    }


    public boolean rusz_się(){ //metoda rusza/zmienia kierunek roba na podstawie pierwszej dostępnej w jego kodzie instrukcji
        if (ile_energii < parametry.getKoszt_tury()){
            return false; //rob do usunięcia - umarł
        }
        if(kod.getKod().size() != 0){
            ile_energii--;
            String ruch = kod.getKod().get(0);
            switch (ruch){
                case "l":
                    if(kierunek == 1){
                        kierunek = 4;
                    }
                    else{
                        kierunek--;
                    }
                    break;

                case "p":
                    if(kierunek == 4){
                        kierunek = 1;
                    }
                    else{
                        kierunek++;
                    }
                    break;

                case "i":
                    if(kierunek == 1){
                        if(y == 0){ //góra
                            y = parametry.getY_planszy() - 1;
                        }
                        else{
                            y--;
                        }
                    }
                    else if(kierunek == 2){ //prawo
                        if(x == parametry.getX_planszy() - 1){
                            x = 0;
                        }
                        else{
                            x++;
                        }
                    }
                    else if(kierunek == 3){ //dół
                        if(y == parametry.getY_planszy() - 1){
                            y = 0;
                        }
                        else{
                            y++;
                        }

                    }
                    else if(kierunek == 4){ //lewo
                        if(x == 0){
                            x = parametry.getX_planszy() - 1;
                        }
                        else{
                            x--;
                        }
                    }
                    if(plansza_jedzeniowa[x][y].getKiedy_będzie_jedzenie() == 0){
                        jedz();
                        plansza_jedzeniowa[x][y].zjadanie_jedzenia();
                        int pom = ile_pól_z_jedzeniem.getIle_pól_z_jedzeniem();
                        ile_pól_z_jedzeniem.setIle_pól_z_jedzeniem(pom - 1);

                    }
                    break;

                case "w":
                    if (y == 0 && x== 0){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(y == 0 && x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(y == parametry.getY_planszy()-1 && x == 0){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(y == parametry.getY_planszy()-1 && x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(y == 0){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(y == parametry.getY_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(x == 0){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else if(x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }

                    else{
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 2;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            kierunek = 3;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            kierunek = 4;
                    }
                    break;

                case "j":

                    if (y == 0 && x== 0){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            y = parametry.getY_planszy()-1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            x = parametry.getX_planszy()-1;

                        else if(plansza_jedzeniowa[1][1].getKiedy_będzie_jedzenie() == 0){
                            x = 1;
                            y = 1;
                        }
                        else if(plansza_jedzeniowa[1][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = 1;
                            y = parametry.getY_planszy()-1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-1;
                            y = 1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-1;
                            y = parametry.getY_planszy()-1;
                        }
                    }

                    else if(y == 0 && x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            y = parametry.getY_planszy()-1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            x = 0;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[0][1].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = 1;
                        }
                        else if(plansza_jedzeniowa[0][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = parametry.getY_planszy()-1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-2][1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-2;
                            y = 1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-2][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-2;
                            y = parametry.getY_planszy()-1;
                        }
                    }

                    else if(y == parametry.getY_planszy()-1 && x == 0){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            y = 0;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            x = parametry.getX_planszy()-1;

                        else if(plansza_jedzeniowa[1][0].getKiedy_będzie_jedzenie() == 0){
                            x = 1;
                            y = 0;
                        }
                        else if(plansza_jedzeniowa[1][parametry.getY_planszy()-2].getKiedy_będzie_jedzenie() == 0){
                            x = 1;
                            y = parametry.getY_planszy()-2;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][0].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-1;
                            y = 0;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][parametry.getY_planszy()-2].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-1;
                            y = parametry.getY_planszy()-2;
                        }
                    }

                    else if(y == parametry.getY_planszy()-1 && x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            x = 0;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            y = 0;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[0][0].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = 0;
                        }
                        else if(plansza_jedzeniowa[0][parametry.getY_planszy()-2].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = parametry.getY_planszy()-2;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-2][0].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-2;
                            y = 0;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-2][parametry.getY_planszy()-2].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-2;
                            y = parametry.getY_planszy()-2;
                        }
                    }

                    else if(y == 0){
                        if(plansza_jedzeniowa[x][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie()==0)
                            y = parametry.getY_planszy()-1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[x-1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y+1;
                        }
                        else if(plansza_jedzeniowa[x+1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y+1;
                        }
                        else if(plansza_jedzeniowa[x-1][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = parametry.getY_planszy()-1;
                        }
                        else if(plansza_jedzeniowa[x+1][parametry.getY_planszy()-1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = parametry.getY_planszy()-1;
                        }
                    }

                    else if(y == parametry.getY_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][0].getKiedy_będzie_jedzenie()==0)
                            y = 0;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[x-1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x+1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x-1][0].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = 0;
                        }
                        else if(plansza_jedzeniowa[x+1][0].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = 0;
                        }
                    }

                    else if(x == 0){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y].getKiedy_będzie_jedzenie()==0)
                            x = parametry.getX_planszy()-1;

                        else if(plansza_jedzeniowa[x+1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x+1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y+1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getY_planszy()-1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[parametry.getX_planszy()-1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = parametry.getX_planszy()-1;
                            y = y+1;
                        }
                    }

                    else if(x == parametry.getX_planszy()-1){
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[0][y].getKiedy_będzie_jedzenie()==0)
                            x = 0;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[0][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[0][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = 0;
                            y = y+1;
                        }
                        else if(plansza_jedzeniowa[x-1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x-1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y+1;
                        }
                    }

                    else{
                        if(plansza_jedzeniowa[x][y-1].getKiedy_będzie_jedzenie()==0)
                            y = y - 1;
                        else if(plansza_jedzeniowa[x+1][y].getKiedy_będzie_jedzenie()==0)
                            x = x + 1;
                        else if(plansza_jedzeniowa[x][y+1].getKiedy_będzie_jedzenie()==0)
                            y = y + 1;
                        else if(plansza_jedzeniowa[x-1][y].getKiedy_będzie_jedzenie()==0)
                            x = x - 1;

                        else if(plansza_jedzeniowa[x-1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x-1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x-1;
                            y = y+1;
                        }
                        else if(plansza_jedzeniowa[x+1][y-1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y-1;
                        }
                        else if(plansza_jedzeniowa[x+1][y+1].getKiedy_będzie_jedzenie() == 0){
                            x = x+1;
                            y = y+1;
                        }
                    }

                    if(plansza_jedzeniowa[x][y].getKiedy_będzie_jedzenie()==0){ //sprawdzam, czy na polu na które wszedł rób znajduje się jedzenie - jeśli tak, to rób je je
                        jedz();
                        plansza_jedzeniowa[x][y].zjadanie_jedzenia();
                        int pom = ile_pól_z_jedzeniem.getIle_pól_z_jedzeniem();
                        ile_pól_z_jedzeniem.setIle_pól_z_jedzeniem(pom - 1);
                    }
                    break;
            }

            kod.getKod().remove(0);
        }
        ile_energii = ile_energii - parametry.getKoszt_tury();
        if(ile_energii < 0)
            return false;
        wiek++;
        return true;
    }

    public boolean powiel_się(){ //metoda z podanym w parametrach prawdopodobieństwem powiela roba
        if (ile_energii >= parametry.getLimit_powielania()){
            Random rand = new Random();
            float f = rand.nextFloat();
            if(f < parametry.getPr_powielania()){
                return true; //rob do powielenia
            }
        }
        return false;
    }

}
