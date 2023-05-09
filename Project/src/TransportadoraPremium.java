import java.util.List;

public class TransportadoraPremium extends Transportadora implements Premium{

    public TransportadoraPremium() {
        super();
    }

    public TransportadoraPremium(Transportadora t) {
        super(t);
    }

    public TransportadoraPremium(String nome, double taxa, boolean prem, List<Artigo> lista_encomendas) {
        super(nome, taxa, prem, lista_encomendas);
    }

}
