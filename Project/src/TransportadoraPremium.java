import java.io.Serializable;
import java.util.List;

public class TransportadoraPremium extends Transportadora implements Premium, Serializable {

    public TransportadoraPremium() {
        super();
    }

    public TransportadoraPremium(Transportadora t) {
        super(t);
    }

    public TransportadoraPremium(String codigo,String nome, double taxa, List<Artigo> lista_encomendas) {
        super(codigo,nome, taxa, lista_encomendas);
    }

    public TransportadoraPremium(String codigo,String nome, double taxa) {
        super(codigo,nome, taxa);
    }

    public TransportadoraPremium(String nome, double taxa) {
        super(nome, taxa);
    }

}
