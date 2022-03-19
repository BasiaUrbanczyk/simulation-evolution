package pliki;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Program {
    private List<String> kod;

    public Program(List<String> kod) {
        this.kod = kod;
    }

    public List<String> getKod() {
        return kod;
    }

    public Program mutuj(Parametry parametry){ //metoda mutuje kod na podstawie prawdopodobiueństw zawartych w parametrach programu
        List<String> kodzik = new ArrayList<String>();
        for(int i = 0; i < kod.size(); i++){
            kodzik.add(kod.get(i));
        }
        List<String> kod_zmut = kodzik;
        Random rand = new Random();
        float f = rand.nextFloat();
        if (f < parametry.getPr_usunięcia_instr() && kod_zmut.size() > 0){
            kod_zmut.remove(kod_zmut.size()-1);
        }
        f = rand.nextFloat();
        if (f < parametry.getPr_dodania_instr()){
            int a = rand.nextInt(parametry.getSpis_instr().length);
            kod_zmut.add(parametry.getSpis_instr()[a]);

        }
        f = rand.nextFloat();
        if (f < parametry.getPr_zmiany_instr()){
            if(kod_zmut.size() != 0){
                int b = rand.nextInt(kod_zmut.size());
                int a = rand.nextInt(parametry.getSpis_instr().length);
                kod_zmut.set(b, parametry.getSpis_instr()[a]);
            }
        }
        return new Program(kod_zmut);
    }
}
