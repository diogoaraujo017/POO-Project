import java.time.LocalDate;

public class Fatura {
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
        this.data=fat.data;
        this.comprador=fat.comprador;
        this.vendedor=fat.vendedor;
        this.artigo=fat.artigo;
        this.custo=fat.custo;
    }

    public Fatura(LocalDate dataD, Utilizador comprador, Utilizador vendedor, Artigo artigo) {
        this.data = dataD;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.artigo = artigo;
        this.custo = valorCusto(artigo);
    }

    public double valorCusto(Artigo art){
        double val = art.getPrecoFinal();
        setCusto(art.getPrecoFinal());
        return val;
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



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: {");
        sb.append("Data de emissão:").append(this.getData());
        sb.append("Comprador: ").append(this.getComprador().getCodigo());
        sb.append("; Vendedor: ").append(this.getVendedor().getCodigo());
        sb.append("; Artigo: ").append(this.getArtigo());
        sb.append("; Custo: ").append(this.getCusto());
        sb.append("}");

        return sb.toString();
    }
}
