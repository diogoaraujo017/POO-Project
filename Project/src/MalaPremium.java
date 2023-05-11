import java.time.Year;

public class MalaPremium extends Mala implements Premium{

    public MalaPremium() {
        super();
    }

    public MalaPremium(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora, String vendedor, int comprimento, int largura, int altura, String material, int ano_lancamento) {
        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora, vendedor, comprimento, largura, altura, material, ano_lancamento);
    }

    public MalaPremium(Mala mala) {
        super(mala);
    }

    public void calculaValorMalaPremium(Mala mala){
        double preco_base = mala.getPrecoBase();
        double preco_final = preco_base;

        int idade = Year.now().getValue() - mala.getAnoLancamento();
        int volume = mala.getComprimento() * mala.getLargura() * mala.getAltura();

        preco_final = preco_base + preco_base * 0.05 * idade + (double) 100/volume;

        if(preco_final <= 15) preco_final = 15;

        preco_final = Math.round(preco_final * 100.0) / 100.0;
        mala.setPrecoFinal(preco_final);
    }

    public Artigo clone() {
        return new MalaPremium(this);
    }
}
