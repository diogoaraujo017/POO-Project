
import java.time.Year;

public class Malas extends Artigo{

    private int comprimento;
    private int largura;
    private int altura;
    private String material;
    private int ano_lancamento;
    private boolean e_premium; // true = Premium // false = not Premium


    public Malas(){
        super();
        this.comprimento = 0;
        this.largura = 0;
        this.altura = 0;
        this.material="";
        this.ano_lancamento=0;
        this.e_premium=true;
    }

    public Malas(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora,
                 int comprimento, int largura, int altura, String material, int ano_lancamento, boolean e_premium){

        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora);
        this.comprimento = comprimento;
        this.largura = largura;
        this.altura = altura;
        this.material=material;
        this.ano_lancamento=ano_lancamento;
        this.e_premium=e_premium;
        calculaValorFinalMala(this);
    }

    public Malas(Malas malas){
        super(malas);
        this.comprimento = malas.getComprimento();
        this.largura = malas.getLargura();
        this.altura = malas.getAltura();
        this.material = malas.getMaterial();
        this.ano_lancamento = malas.getAnoLancamento();
        this.e_premium = malas.getEPremium();
        calculaValorFinalMala(malas);
    }

    public void calculaValorFinalMala(Malas ml){
        double preco_base = ml.getPrecoBase();
        double preco_final = preco_base;

        int idade = Year.now().getValue() - ml.getAnoLancamento();
        int volume = ml.getComprimento() * ml.getLargura() * ml.getAltura();

        if(ml.getEPremium()){
            preco_final = preco_base + preco_base * 0.05 * idade;
        } else {
            preco_final = preco_base - preco_base * 0.02 * idade - (double) 12000000 / volume;
        }

        if(preco_final <= 15) preco_final = 15;

        preco_final = Math.round(preco_final * 100.0) / 100.0;
        setPrecoFinal(preco_final);
    }

    public int getComprimento() {
        return comprimento;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public String getMaterial(){
        return this.material;
    }

    public int getAnoLancamento(){
        return this.ano_lancamento;
    }

    public boolean getEPremium(){
        return this.e_premium;
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

    public void setEPremium(boolean e_premium){
        this.e_premium=e_premium;
    }
    

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Malas e = (Malas) obj;
        return  e.getComprimento()==(this.comprimento) &&
                e.getLargura()==(this.largura) &&
                e.getAltura()==(this.altura) &&
                e.getMaterial().equals(this.material) &&
                e.getAnoLancamento()==(this.ano_lancamento) &&
                e.getEPremium()==(this.e_premium);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mala: {");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço Final: ").append(this.getPrecoFinal());
        sb.append("; Estado: ").append(this.getEstado());
        sb.append("; Número de donos: ").append(this.getNDonos());
        sb.append("; Transportadora: ").append(this.getTransportadora());
        sb.append(" Comprimento: ").append(this.getComprimento());
        sb.append("; Largura: ").append(this.getLargura());
        sb.append("; Altura: ").append(this.getAltura());
        sb.append("; Material: ").append(this.getMaterial());
        sb.append("; Ano de Lançamento: ").append(this.getAnoLancamento());
        sb.append("; Premium: ");
        if(this.getEPremium())sb.append("Sim");
        else sb.append("Não");
        sb.append(" }");

        return sb.toString();
    }
}
