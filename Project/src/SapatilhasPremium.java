import java.time.LocalDate;

public class SapatilhasPremium extends Sapatilhas implements Premium {

    public SapatilhasPremium() {
        super();
    }

    public SapatilhasPremium(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora, double n_tamanho, boolean tem_atacadores, String cor, LocalDate data_lancamento) {
        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora, n_tamanho, tem_atacadores, cor, data_lancamento);
    }

    public SapatilhasPremium(Sapatilhas sapatilhas) {
        super(sapatilhas);
    }

    public void calculaValorPremium(Sapatilhas sp) {
        double taxa_vintage=1.03;
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        int idade = idade_sapatilhas(sp.getDataLancamento());

        switch (getEstado()) {
            case 'a':
                preco_final = preco_base + (preco_base * 0.1 * idade);
                break;
            case 'b':
                preco_final = preco_base + (preco_base * 0.075 * idade);
                break;
            case 'c':
                preco_final = preco_base + (preco_base * 0.03 * idade);
                break;
        }

        if(preco_final <= 10) {
            preco_final = 10;
        }
        preco_final = preco_final*taxa_vintage;
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        sp.setPrecoFinal(preco_final);
    }
}
