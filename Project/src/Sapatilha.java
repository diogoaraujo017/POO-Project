
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Sapatilha extends Artigo implements Serializable {

    private double n_tamanho;

    private boolean tem_atacadores; // true - tem atacadores // false - não tem atacadores

    private String cor;

    private LocalDate data_lancamento; // dd/mm/aaaa

    private double desconto;


    public Sapatilha(){
        super();
        this.n_tamanho=0.00;
        this.tem_atacadores=true;
        this.cor="";
        this.data_lancamento= null;
        this.desconto=0.00;
    }


    public Sapatilha(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, String transportadora,
                     String vendedor,double n_tamanho, boolean tem_atacadores, String cor, LocalDate data_lancamento, double desconto){

        super(descricao, marca, codigo, preco_base, estado, n_donos, transportadora, vendedor);
        this.n_tamanho=n_tamanho;
        this.tem_atacadores=tem_atacadores;
        this.cor=cor;
        this.data_lancamento=data_lancamento;
        if(this instanceof Premium) ((SapatilhaPremium) this).calculaValorPremium(this);
        else calculaValorFinalSapatilhas(this);
        this.desconto=desconto;
    }

    public Sapatilha(Sapatilha sapatilhas){
        super(sapatilhas);
        this.n_tamanho=sapatilhas.getNTamanho();
        this.tem_atacadores=sapatilhas.getTemAtacadores();
        this.cor=sapatilhas.getCor();
        this.data_lancamento=sapatilhas.getDataLancamento();
        if(sapatilhas instanceof Premium) ((SapatilhaPremium) sapatilhas).calculaValorPremium(sapatilhas);
        else calculaValorFinalSapatilhas(sapatilhas);
        this.desconto=sapatilhas.getDesconto();
    }

    // Calcula a idade da coleção das sapatilhas em anos.
    public int idade_sapatilhas(LocalDate idade){

        LocalDate atual = LocalDate.now();
        Period diferenca = Period.between(idade, atual);

        return diferenca.getYears();
    }

    public void calculaValorFinalSapatilhas(Sapatilha sp){
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        double desconto = sp.getDesconto();
        char estado = sp.getEstado();
        int idade = idade_sapatilhas(sp.getDataLancamento());
        int n_donos = sp.getNDonos();

        if(n_donos > 4) {
            n_donos = 4;
        }


        if(estado == 'n' && sp.getNTamanho() > 45) {
            preco_final = (preco_base - sp.getNTamanho() * 0.1)*(1-desconto);
        }

        if(estado != 'n'){
            switch(estado){
                case 'a':
                    preco_final = (preco_base - (preco_base * n_donos) * 0.1 - idade);
                    break;
                case 'b':
                    preco_final = preco_base - (preco_base * n_donos) * 0.13 - idade;
                    break;
                case 'c':
                    preco_final = preco_base - (preco_base * n_donos) * 0.16 - idade;
                    break;
            }
            if(desconto>0 && desconto<1) preco_final=preco_final*(1-desconto);
        }


        if(preco_final <= 10) {
            preco_final = 10;
        }
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        sp.setPrecoFinal(preco_final);
    }

    public double getNTamanho(){
        return this.n_tamanho;
    }

    public double getDesconto() {
        return this.desconto;
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

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setDataLancamento(LocalDate data_lancamento){
        this.data_lancamento=data_lancamento;
    }

    public Artigo clone() {
        return new Sapatilha(this);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Sapatilha e = (Sapatilha) obj;
        return e.getNTamanho()==(this.n_tamanho) &&
                e.getTemAtacadores()==(this.tem_atacadores) &&
                e.getCor().equals(this.cor) &&
                e.getDataLancamento().equals(this.data_lancamento) && 
                e.getDesconto()==this.desconto;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilhas: {");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço s/taxas:: ").append(this.getPrecoFinal());
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
