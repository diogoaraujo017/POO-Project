import java.util.Objects;

public class Tshirts extends Artigo{
    
    private char tamanho; // s - S // m - M // l - L // x - XL
    private char padrao; // l - liso // r - riscas // p - palmeiras

    public Tshirts(){
        super();
        this.tamanho=' ';
        this.padrao=' ';
    }

    public Tshirts(String descricao, String marca, String codigo, double preco_base, double desconto, char estado, int n_donos, char tamanho, char padrao){
        super(descricao, marca, codigo, preco_base, desconto, estado, n_donos);
        this.tamanho=tamanho;
        this.padrao=padrao;
    }

    public Tshirts(Tshirts tshirts){
        super(tshirts);
        this.tamanho=tshirts.getTamanho();
        this.padrao=tshirts.getPadrao();
    }

    public double calcula_valor_final_tshirts(Tshirts ts){
        char padrao = ts.getPadrao();
        double preco_final = ts.getPrecoBase();

        if(ts.getEstado()!='n' && (padrao == 'r' || padrao == 'p')){
            preco_final=preco_final*0.50;
        }

        return preco_final;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tshirts tshirts = (Tshirts) o;
        return tamanho == tshirts.tamanho && padrao == tshirts.padrao;
    }

    @Override
    public String toString() {
        return "Tshirts{" +
                "tamanho=" + tamanho +
                ", padrao=" + padrao +
                '}';
    }
}
