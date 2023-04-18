import java.time.LocalDate;
import java.time.Period;

public class Sapatilhas extends Artigo{
    
    private double n_tamanho;
    private boolean tem_atacadores; // true - tem atacadores // false - não tem atacadores
    private String cor;
    private String data_lancamento; // dd/mm/aaaa
    private boolean e_premium; // true - é Premium // false - não é Premium

    public Sapatilhas(){
        super();
        this.n_tamanho=0.00;
        this.tem_atacadores=true;
        this.cor="";
        this.data_lancamento="";
        this.e_premium=true;
    }

    public Sapatilhas(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos, double n_tamanho, boolean tem_atacadores, String cor, String data_lancamento, boolean e_premium){
        super(descricao, marca, codigo, preco_base, estado, n_donos);
        this.n_tamanho=n_tamanho;
        this.tem_atacadores=tem_atacadores;
        this.cor=cor;
        this.data_lancamento=data_lancamento;
        this.e_premium=e_premium;
    }

    public Sapatilhas(Sapatilhas sapatilhas){
        super(sapatilhas);
        this.n_tamanho=sapatilhas.getNTamanho();
        this.tem_atacadores=sapatilhas.getTemAtacadores();
        this.cor=sapatilhas.getCor();
        this.data_lancamento=sapatilhas.getDataLancamento();
        this.e_premium=sapatilhas.getEPremium();
    }

    public int idade_sapatilhas(String idade){ // calcula a idade da coleção das sapatilhas em anos
        String dia = idade.substring(0,2);
        String mes = idade.substring(3,5);
        String ano = idade.substring(6);
        LocalDate idade_inicial = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
        LocalDate atual = LocalDate.now();

        Period diferenca = Period.between(idade_inicial, atual);

        return diferenca.getYears();
    }
//
    public void calcula_valor_final_sapatilhas(Sapatilhas sp){
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        char estado = sp.getEstado();
        int idade = idade_sapatilhas(sp.getDataLancamento());
        int n_donos = sp.getNDonos();

        if(n_donos>4) n_donos=4;
        
        if(sp.getEPremium()){
            switch(estado){
                case 'a':
                    preco_final=preco_base+(preco_base*0.1*idade);
                    break;
                case 'b':
                    preco_final=preco_base+(preco_base*0.075*idade);
                    break;
                case 'c':
                    preco_final=preco_base+(preco_base*0.03*idade);
                    break;
            }
        }

        else{
            if(estado == 'n' && sp.getNTamanho()>45) preco_final = preco_base - sp.getNTamanho()*0.1; 
            if(idade>=1){
                switch(estado){
                    case 'a':
                        preco_final=preco_base-preco_base*n_donos*1.2*0.65;
                        break;
                    case 'b':
                        preco_final=preco_base-(preco_base/n_donos)*0.75;
                        break;
                    case 'c':
                        preco_final=preco_base-(preco_base/n_donos)*0.9;
                        break;
                }
            }
        }
        if(preco_final<=10) preco_final=10;
        setPrecoFinal(preco_final);
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

    public String getDataLancamento(){
        return this.data_lancamento;
    }

    public boolean getEPremium(){
        return this.e_premium;
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

    public void setDataLancamento(String data_lancamento){
        this.data_lancamento=data_lancamento;
    }

    public void setEPremium(boolean e_premium){
        this.e_premium=e_premium;
    }


    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Sapatilhas e = (Sapatilhas) obj;
        return e.getNTamanho()==(this.n_tamanho) &&
               e.getTemAtacadores()==(this.tem_atacadores) && 
               e.getCor().equals(this.cor) && 
               e.getDataLancamento().equals(this.data_lancamento) && 
               e.getEPremium()==(this.e_premium);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sapatilhas: {");
        sb.append("Tamanho: ").append(this.n_tamanho);
        sb.append("; Atacadores: ");
        if(this.tem_atacadores)sb.append("Sim");
        else sb.append("Não");
        sb.append("; Cor: ").append(this.cor);
        sb.append("; Data de Lançamento: ").append(this.data_lancamento);
        sb.append("; Premium: ");
        if(this.e_premium)sb.append("Sim");
        else sb.append("Não");
        sb.append("}");

        return sb.toString();
    }

}
