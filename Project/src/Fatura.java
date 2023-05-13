import java.io.Serializable;
import java.time.LocalDate;

public class Fatura implements Serializable {
    private LocalDate data;
    private Utilizador comprador;
    private Utilizador vendedor;
    private Artigo artigo;
    private double custo;

    public Fatura(){
        this.data=LocalDate.now();
        this.comprador=null;
        this.vendedor=null;
        this.artigo=null;
        this.custo=0;
    }

    public Fatura(Fatura fat){
        this.data=fat.getData();
        this.comprador=fat.getComprador();
        this.vendedor=fat.getVendedor();
        this.artigo=fat.getArtigo();
        this.custo=fat.getCusto();
    }

    public Fatura(LocalDate dataD, Utilizador comprador, Utilizador vendedor, Artigo artigo) {
        this.data = dataD;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.artigo = artigo;
        valorCusto(artigo);
    }

    public void valorCusto(Artigo art){
        double val = art.getPrecoFinal();
        setCusto(art.getPrecoFinal());
    }

    public boolean eComprador(Fatura fat, Utilizador user){
        Utilizador teste = fat.getComprador();
        String nomeComprador = teste.getNome();
        String nomeUser = user.getNome();
        return nomeComprador.equals(nomeUser);
    }

    public Utilizador getComprador() {
        return this.comprador;
    }

    public void setComprador(Utilizador comprador) {
        this.comprador = comprador;
    }

    public Utilizador getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Utilizador vendedor) {
        this.vendedor = vendedor;
    }

    public Artigo getArtigo() {
        return this.artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public double getCusto() {
        return this.custo;
    }

    public void setCusto(double cust) {
        this.custo = cust;
    }

    public LocalDate getData() {
        return this.data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public Fatura clone(){
        return new Fatura(this);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Fatura e = (Fatura) obj;
        return e.getData().equals(this.data) &&
               e.getComprador().equals(this.comprador) &&
               e.getVendedor().equals(this.vendedor) &&
               e.getArtigo().equals(this.artigo);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: {");
        sb.append("Data de emissão:").append(this.getData());
        sb.append("; Comprador: ").append(this.getComprador().getCodigo());
        sb.append("; Vendedor: ").append(this.getVendedor().getCodigo());
        sb.append("; Artigo: ").append(this.getArtigo());
        sb.append("; Custo Final: ").append(this.getCusto());
        sb.append("}");

        return sb.toString();
    }
}
