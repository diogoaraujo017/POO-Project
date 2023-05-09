
import java.time.LocalDate;
import java.time.Period;

public class Sapatilhas extends Artigo{
    
    private double n_tamanho;

    private boolean tem_atacadores; // true - tem atacadores // false - não tem atacadores

    private String cor;

    private LocalDate data_lancamento; // dd/mm/aaaa


    public Sapatilhas(){
        super();
        this.n_tamanho=0.00;
        this.tem_atacadores=true;
        this.cor="";
        this.data_lancamento= null;
    }

    public Artigo clone() {
        return new Sapatilhas(this);
    }

    public Sapatilhas(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora,
                      double n_tamanho, boolean tem_atacadores, String cor, LocalDate data_lancamento){

        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora);
        this.n_tamanho=n_tamanho;
        this.tem_atacadores=tem_atacadores;
        this.cor=cor;
        this.data_lancamento=data_lancamento;
        if(this instanceof Premium) ((SapatilhasPremium) this).calculaValorPremium(this);
        else calculaValorFinalSapatilhas(this);
    }

    public Sapatilhas(Sapatilhas sapatilhas){
        super(sapatilhas);
        this.n_tamanho=sapatilhas.getNTamanho();
        this.tem_atacadores=sapatilhas.getTemAtacadores();
        this.cor=sapatilhas.getCor();
        this.data_lancamento=sapatilhas.getDataLancamento();
        if(sapatilhas instanceof Premium) ((SapatilhasPremium) sapatilhas).calculaValorPremium(sapatilhas);
        else calculaValorFinalSapatilhas(sapatilhas);
    }

    // Calcula a idade da coleção das sapatilhas em anos.
    public int idade_sapatilhas(LocalDate idade){

        LocalDate atual = LocalDate.now();
        Period diferenca = Period.between(idade, atual);

        return diferenca.getYears();
    }

    public void calculaValorFinalSapatilhas(Sapatilhas sp){
        double taxa_vintage=1.03;
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        char estado = sp.getEstado();
        int idade = idade_sapatilhas(sp.getDataLancamento());
        int n_donos = sp.getNDonos();

        if(n_donos > 4) {
            n_donos = 4;
        }

        
        if(estado == 'n' && sp.getNTamanho() > 45) {
                preco_final = preco_base - sp.getNTamanho() * 0.1;
        }
        
        if(estado != 'n'){
            switch(estado){
                case 'a':
                    preco_final = preco_base - (preco_base * n_donos) * 0.1 - idade;
                    break; 
                case 'b':
                    preco_final = preco_base - (preco_base * n_donos) * 0.13 - idade;
                    break; 
                case 'c':
                    preco_final = preco_base - (preco_base * n_donos) * 0.16 - idade;
                    break;
            }
        }
       

        if(preco_final <= 10) {
            preco_final = 10;
        }
        preco_final=preco_final*taxa_vintage;
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        sp.setPrecoFinal(preco_final);
    }
    
    public double getNTamanho(){
        return this.n_tamanho;
    }

    public boolean getTemAtacadores(){
        return this.tem_atacadores;
    }

    public String getCor(){
        return this.cor;
    }

    public LocalDate getDataLancamento(){
        return this.data_lancamento;
    }

    public void setNTamanho(double n_tamanho){
        this.n_tamanho=n_tamanho;
    }

    public void setTemAtacadores(boolean tem_atacadores){
        this.tem_atacadores=tem_atacadores;
    }

    public void setCor(String cor){
        this.cor=cor;
    }

    public void setDataLancamento(LocalDate data_lancamento){
        this.data_lancamento=data_lancamento;
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Sapatilhas e = (Sapatilhas) obj;
        return e.getNTamanho()==(this.n_tamanho) &&
               e.getTemAtacadores()==(this.tem_atacadores) && 
               e.getCor().equals(this.cor) && 
               e.getDataLancamento().equals(this.data_lancamento);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilhas: {");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço Final: ").append(this.getPrecoFinal());
        sb.append("; Estado: ").append(this.getEstado());
        sb.append("; Número de donos: ").append(this.getNDonos());
        sb.append("; Transportadora: ").append(this.getTransportadora());
        sb.append("; Tamanho: ").append(this.getNTamanho());
        sb.append("; Atacadores: ");
        if(this.getTemAtacadores())sb.append("Sim");
        else sb.append("Não");
        if(this instanceof Premium) sb.append("; Premium: Sim");
        else sb.append("; Premium: Não");
        sb.append("; Cor: ").append(this.getCor());
        sb.append("; Data de Lançamento: ").append(this.getDataLancamento());
        sb.append("}");

        return sb.toString();
    }

}
