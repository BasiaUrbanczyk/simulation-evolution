package pliki;

public class Pole_żywnieniowe extends Pole{
    private int kiedy_będzie_jedzenie;
    private int ile_rośnie_jedzenie;

    public Pole_żywnieniowe(int ile_rośnie_jedzenie) {
        this.ile_rośnie_jedzenie = ile_rośnie_jedzenie;
        this.kiedy_będzie_jedzenie = 0;
    }

    @Override
    public int getKiedy_będzie_jedzenie() {
        return kiedy_będzie_jedzenie;
    }

    @Override
    public void zjadanie_jedzenia(){ //metoda sprawia, że na podanym polu jedzenie zostaje zjedzone
        kiedy_będzie_jedzenie = ile_rośnie_jedzenie + 1; //nie liczę tej tury, w której rob zjadł jedzenie
    }

    @Override
    public int minięcie_tury(){ //metoda sprawia, że na polu nastąpiło minięcie tury - ewentualny czas oczekiwania na jedzenie się zmiejsza, zwraca 1 kiedy nastąpiło zregenerowanie pola (pojawiło się na nim dostępne jedzenie)
        if (kiedy_będzie_jedzenie > 0){
            kiedy_będzie_jedzenie--;
            if(kiedy_będzie_jedzenie == 0){
                return 1;
            }
        }
        return 0;
    }
}
