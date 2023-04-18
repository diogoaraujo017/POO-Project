import java.time.Year;

public class Malas extends Artigo{

    private int dimensao;
    private String material;
    private int ano_lancamento; // dd/mm/aaaa
    private boolean e_premium; // true - é Premium // false - não é Premium


    public Malas(){
        super();
        this.dimensao=0;
        this.material="";
        this.ano_lancamento=0;
        this.e_premium=true;
    }

    public Malas(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, int dimensao, String material, int ano_lancamento, boolean e_premium){
        super(descricao, marca, codigo, preco_base, estado, n_donos);
        this.dimensao=dimensao;
        this.material=material;
        this.ano_lancamento=ano_lancamento;
        this.e_premium=e_premium;
    }

    public Malas(Malas malas){
        super(malas);
        this.dimensao=malas.getDimensao();
        this.material=malas.getMaterial();
        this.ano_lancamento=malas.getAnoLancamento();
        this.e_premium=malas.getEPremium();
    }

    public void calcula_valor_final_sapatilhas(Malas ml){
        double preco_base = ml.getPrecoBase();
        double preco_final = preco_base;
        int idade = Year.now().getValue()-ml.getAnoLancamento();
        int dimensao = ml.getDimensao();

        
        if(ml.getEPremium()){
            preco_final=preco_base+preco_base*0.05*idade+dimensao/10;
        }

        else{
            preco_final=preco_base-preco_base*0.02*idade-4/dimensao;
        }
        if(preco_final<=7.50) preco_final=7.50;
        setPrecoFinal(preco_final);
    }

    public int getDimensao(){
        return this.dimensao;
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


    public void setDimensao(int dimensao){
        this.dimensao=dimensao;
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
        return e.getDimensao()==(this.dimensao) &&
               e.getMaterial().equals(this.material) &&
               e.getAnoLancamento()==(this.ano_lancamento) &&
               e.getEPremium()==(this.e_premium);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mala: {");
        sb.append("Dimensao: ").append(this.dimensao);
        sb.append("; Material: ").append(this.material);
        sb.append("; Ano de Lançamento: ").append(this.ano_lancamento);
        sb.append("; Premium: ");
        if(this.e_premium)sb.append("Sim");
        else sb.append("Não");
        sb.append("}");

        return sb.toString();
    }
}
