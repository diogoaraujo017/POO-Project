
public class Tshirt extends Artigo{
    
    private char tamanho; // s=S // m=M // l=L // x=XL
    private char padrao; // l=liso // r=riscas // p=palmeiras

    public Tshirt(){
        super();
        this.tamanho=' ';
        this.padrao=' ';
    }

    @Override
    public Artigo clone() {
        return new Tshirt(this);
    }

    public Tshirt(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos,
                   String transportadora, char tamanho, char padrao){
                    
        super(descricao, marca, codigo, preco_base, estado, n_donos,transportadora);
        this.tamanho=tamanho;
        this.padrao=padrao;
        calculaValorFinalShirts(this);
    }

    public Tshirt(Tshirt tshirts){
        super(tshirts);
        this.tamanho=tshirts.getTamanho();
        this.padrao=tshirts.getPadrao();
        calculaValorFinalShirts(tshirts);
    }

    public void calculaValorFinalShirts(Tshirt ts){
        double taxa_vintage=1.03;
        char padrao = ts.getPadrao();
        double preco_final = ts.getPrecoBase();

        if(ts.getEstado() != 'n' && (padrao == 'r' || padrao == 'p')){
            preco_final = preco_final * 0.50;
        }
        preco_final=preco_final*taxa_vintage;
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        setPrecoFinal(preco_final);
    }

    public char getTamanho(){
        return this.tamanho;
    }

    public char getPadrao(){
        return this.padrao;
    }


    public void setTamanho(char tamanho){
        this.tamanho=tamanho;
    }

    public void setPadrao(char padrao){
        this.padrao=padrao;
    }


    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;
        if (!super.equals(obj)) return false;

        Tshirt e = (Tshirt) obj;
        return e.getTamanho()==(this.tamanho) &&
               e.getPadrao()==(this.padrao);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tshirt: {");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço Final: ").append(this.getPrecoFinal());
        sb.append("; Estado: ").append(this.getEstado());
        sb.append("; Número de donos: ").append(this.getNDonos());
        sb.append("; Transportadora: ").append(this.getTransportadora());
        sb.append("Tamanho: ").append(this.getTamanho());
        sb.append("; Padrão: ").append(this.getPadrao());
        sb.append("}");

        return sb.toString();
    }
}
