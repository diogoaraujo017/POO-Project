
public class Fatura {

    private Utilizadores comprador;
    private Utilizadores vendedor;
    private Encomenda encomenda;
    private double custo;

    public void emiteFatura(Encomenda enc, Utilizadores vendedor, Utilizadores comprador){
        Fatura nova = new Fatura(comprador,vendedor,enc);
        double passado = vendedor.getValorTotalVendas();
        double custo = valorCusto(enc);
        for(Artigo art : enc.getArtigos()){
            vendedor.adicionaArtigoVendido(art);
            comprador.adicionaArtigoComprado(art);
            vendedor.removeArtigoVendido(art);
        }
        vendedor.setValorTotalVendas(passado+custo);
        vendedor.adicionaFatura(nova);
        comprador.adicionaFatura(nova);
    }

    public double valorCusto(Encomenda enc){
        double val = enc.valorFinalEncomenda(enc);
        setCusto(val);
        return val;
    }

    public Fatura(){
        this.comprador=null;
        this.vendedor=null;
        this.encomenda=null;
        this.custo=0;
    }

    public Fatura(Fatura fat){
        this.comprador=fat.comprador;
        this.vendedor=fat.vendedor;
        this.encomenda=fat.encomenda;
        this.custo=fat.custo;
    }

    public Fatura(Utilizadores comprador, Utilizadores vendedor, Encomenda compras) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.encomenda = compras;
        this.custo = compras.valorFinalEncomenda(compras);
    }

    public Utilizadores getComprador() {
        return this.comprador;
    }

    public void setComprador(Utilizadores comprador) {
        this.comprador = comprador;
    }

    public Utilizadores getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Utilizadores vendedor) {
        this.vendedor = vendedor;
    }

    public Encomenda getEncomenda() {
        return this.encomenda;
    }

    public void setEncomenda(Encomenda compras) {
        this.encomenda = compras;
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
        sb.append("; Encomenda: ").append(this.getEncomenda());
        sb.append("; Custo: ").append(this.getCusto());
        sb.append("}");

        return sb.toString();
    }
}
