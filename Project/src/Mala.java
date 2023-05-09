
import java.time.Year;

public class Mala extends Artigo{

    private int comprimento;

    private int largura;

    private int altura;

    private String material;

    private int ano_lancamento;

    public Mala(){
        super();
        this.comprimento = 0;
        this.largura = 0;
        this.altura = 0;
        this.material="";
        this.ano_lancamento=0;
    }

    public Mala(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora,
                 int comprimento, int largura, int altura, String material, int ano_lancamento, String vendedor){

        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora, vendedor);
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.material=material;
        this.ano_lancamento=ano_lancamento;
        if(this instanceof Premium) ((MalaPremium) this).calculaValorMalaPremium(this);
        else calculaValorFinalMala(this);
    }

    public Mala(Mala mala){
        super(mala);
        this.comprimento = mala.getComprimento();
        this.largura = mala.getLargura();
        this.altura = mala.getAltura();
        this.material = mala.getMaterial();
        this.ano_lancamento = mala.getAnoLancamento();
        if(mala instanceof Premium) ((MalaPremium) mala).calculaValorMalaPremium(mala);
        else calculaValorFinalMala(mala);
    }

    public void calculaValorFinalMala(Mala mala){
        double taxa_vintage=1.03;
        double preco_base = mala.getPrecoBase();
        double preco_final = preco_base;

        int idade = Year.now().getValue() - mala.getAnoLancamento();
        int volume = mala.getComprimento() * mala.getLargura() * mala.getAltura();

        preco_final = preco_base - preco_base * 0.02 * idade - (double) 12000000 / volume;

        if(preco_final <= 15) preco_final = 15;

        preco_final=preco_final*taxa_vintage;
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        mala.setPrecoFinal(preco_final);
    }

    public int getComprimento() {
        return this.comprimento;
    }

    public int getLargura() {
        return this.largura;
    }

    public int getAltura() {
        return this.altura;
    }

    public String getMaterial(){
        return this.material;
    }

    public int getAnoLancamento(){
        return this.ano_lancamento;
    }

    public void setComprimento(int comprimento) {
        this.comprimento = comprimento;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setMaterial(String material){
        this.material=material;
    }

    public void setAnoLancamento(int ano_lancamento){
        this.ano_lancamento=ano_lancamento;
    }

    public Artigo clone() {
        return new Mala(this);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Mala e = (Mala) obj;
        return  e.getComprimento()==(this.comprimento) &&
                e.getLargura()==(this.largura) &&
                e.getAltura()==(this.altura) &&
                e.getMaterial().equals(this.material) &&
                e.getAnoLancamento()==(this.ano_lancamento);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mala: {");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço Final: ").append(this.getPrecoFinal());
        if(this instanceof Premium) sb.append("; Premium: Sim");
        else sb.append("; Premium: Não");
        sb.append("; Estado: ").append(this.getEstado());
        sb.append("; Número de donos: ").append(this.getNDonos());
        sb.append("; Transportadora: ").append(this.getTransportadora());
        sb.append(" Comprimento: ").append(this.getComprimento());
        sb.append("; Largura: ").append(this.getLargura());
        sb.append("; Altura: ").append(this.getAltura());
        sb.append("; Material: ").append(this.getMaterial());
        sb.append("; Ano de Lançamento: ").append(this.getAnoLancamento());
        sb.append(" }");

        return sb.toString();
    }
}
