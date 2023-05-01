import java.time.LocalDate;
import java.util.List;

public class Queries {
    private List<Fatura> faturas;
    public boolean intervalaDatas(LocalDate depois, LocalDate antes, Fatura fat){
        Encomenda en = fat.getEncomenda();
        LocalDate teste = en.getData();
        return teste.isAfter(depois) && teste.isBefore(antes);
    }
    public double lucroTransportadora(LocalDate depois, LocalDate antes, List<Fatura> fats, Transportadora trans){
        double r=0;
        for(Fatura fat : fats){
            if(intervalaDatas(depois,antes,fat)){
                Encomenda en = fat.getEncomenda();
                List<Artigo> artigos = en.getArtigos();
                for (Artigo art : artigos) {
                    if ((art.getTransportadora()).equals(trans)) {
                        r += (art.getPrecoFinal() - (art.getPrecoFinal() / trans.getLucro()));
                    }
                }
            }
        }
        return r;
    }
    public double lucroVintage(LocalDate depois, LocalDate antes, List<Fatura> fats) {
        double r = 0;
        for (Fatura fat : fats) {
            if(intervalaDatas(depois,antes,fat)){
                Encomenda en = fat.getEncomenda();
                List<Artigo> artigos = en.getArtigos();
                for (Artigo art : artigos) {
                    r += (art.getPrecoFinal() - (art.getPrecoFinal() / 1.03));
                }
            }
        }
        return r;
    }
}
