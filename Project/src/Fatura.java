
public class Fatura {
    private Utilizadores comprador;
    private Utilizadores vendedor;
    private Encomenda encomenda;
    private double custo;

    public void emiteFatura(Encomenda enc, Utilizadores comprador, Utilizadores vendedor){
        double passado = vendedor.getValorTotalVendas();
        double custo = valorCusto(enc);
        for(Artigo art : enc.getArtigos()){
            vendedor.adicionaArtigoVendido(art);
            comprador.adicionaArtigoComprado(art);
            vendedor.removeArtigoVendido(art);
        }
        vendedor.setValorTotalVendas(passado+custo);
        Fatura nova = new Fatura(comprador,vendedor,enc,custo);
        vendedor.adicionaFatura(nova);
        comprador.adicionaFatura(nova);
    }

    public double valorCusto(Encomenda enc){
        double val = Encomenda.valorFinalEncomenda(enc);
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

    public Fatura(Utilizadores comprador, Utilizadores vendedor, Encomenda compras, double custo) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.encomenda = compras;
        this.custo = custo;
    }

    public Utilizadores getComprador() {
        return comprador;
    }

    public void setComprador(Utilizadores comprador) {
        this.comprador = comprador;
    }

    public Utilizadores getVendedor() {
        return vendedor;
    }

    public void setVendedor(Utilizadores vendedor) {
        this.vendedor = vendedor;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda compras) {
        this.encomenda = compras;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Fatura clone(){
        return new Fatura(this);
    }

}
