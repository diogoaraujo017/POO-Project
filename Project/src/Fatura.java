
public class Fatura {

    private Utilizador comprador;
    private Utilizador vendedor;
    private Artigo artigo;
    private double custo;

    public Fatura(){
        this.comprador=null;
        this.vendedor=null;
        this.artigo=null;
        this.custo=0;
    }

    public Fatura(Fatura fat){
        this.comprador=fat.comprador;
        this.vendedor=fat.vendedor;
        this.artigo=fat.artigo;
        this.custo=fat.custo;
    }

    public Fatura(Utilizador comprador, Utilizador vendedor, Artigo artigo) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.artigo = artigo;
        this.custo = valorCusto(artigo);
    }



    public void emiteFatura(Artigo art, Utilizador vendedor, Utilizador comprador){
        Fatura nova = new Fatura(comprador,vendedor,art);
        double passado = vendedor.getValorTotalVendas();
        double custo = valorCusto(art);
        vendedor.adicionaArtigoVendido(art);
        comprador.adicionaArtigoComprado(art);
        vendedor.removeArtigoAVenda(art);
        vendedor.setValorTotalVendas(passado+custo);
        vendedor.adicionaFatura(nova);
        comprador.adicionaFatura(nova);
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

    public Fatura clone(){
        return new Fatura(this);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: {");
        sb.append("Comprador: ").append(this.getComprador().getCodigo());
        sb.append("; Vendedor: ").append(this.getVendedor().getCodigo());
        sb.append("; Artigo: ").append(this.getArtigo());
        sb.append("; Custo: ").append(this.getCusto());
        sb.append("}");

        return sb.toString();
    }
}
