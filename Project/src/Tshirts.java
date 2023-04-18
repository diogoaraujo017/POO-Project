public class Tshirts extends Artigo{
    
    private char tamanho; // s=S // m=M // l=L // x=XL
    private char padrao; // l=liso // r=riscas // p=palmeiras

    public Tshirts(){
        super();
        this.tamanho=' ';
        this.padrao=' ';
    }

    public Tshirts(String descricao, String marca, String codigo, double preco_base, char estado, int n_donos,
                   String transportadora, char tamanho, char padrao){
                    
        super(descricao, marca, codigo, preco_base, estado, n_donos,transportadora);
        this.tamanho=tamanho;
        this.padrao=padrao;
    }

    public Tshirts(Tshirts tshirts){
        super(tshirts);
        this.tamanho=tshirts.getTamanho();
        this.padrao=tshirts.getPadrao();
    }

    public void calculaValorFinalShirts(Tshirts ts){
        char padrao = ts.getPadrao();
        double preco_final = ts.getPrecoBase();

        if(ts.getEstado()!='n' && (padrao == 'r' || padrao == 'p')){
            preco_final=preco_final*0.50;
        }

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

        Tshirts e = (Tshirts) obj;
        return e.getTamanho()==(this.tamanho) &&
               e.getPadrao()==(this.padrao);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tshirt: {");
        sb.append("Tamanho: ").append(this.tamanho);
        sb.append("; Padrão: ").append(this.padrao);
        sb.append("}");

        return sb.toString();
    }
}
