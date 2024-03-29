import java.io.Serializable;
import java.time.LocalDate;

public class SapatilhaPremium extends Sapatilha implements Premium , Serializable {

    public SapatilhaPremium() {
        super();
    }

    public SapatilhaPremium(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora,String vendedor, double n_tamanho, boolean tem_atacadores, String cor, LocalDate data_lancamento, double desconto) {
        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora,vendedor, n_tamanho, tem_atacadores, cor, data_lancamento, desconto);
    }

    public SapatilhaPremium(Sapatilha sapatilha) {
        super(sapatilha);
    }


    public void calculaValorPremium(Sapatilha sp) {
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        int idade = idade_sapatilhas(sp.getDataLancamento());


        switch (getEstado()) {
            case 'a':
                preco_final = (preco_base + (preco_base * 0.1 * idade));
                break;
            case 'b':
                preco_final = (preco_base + (preco_base * 0.075 * idade));
                break;
            case 'c':
                preco_final = (preco_base + (preco_base * 0.03 * idade));
                break;
        }

        if(preco_final <= 10) {
            preco_final = 10;
        }
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        sp.setPrecoFinal(preco_final);
    }
    public Artigo clone() {
        return new SapatilhaPremium(this);
    }
}
