
public class Fatura {

    private Utilizadores comprador;
    private Utilizadores vendedor;
    private Encomenda encomenda;
    private static double custo;

    public static void emiteFatura(Encomenda enc, Utilizadores vendedor, Utilizadores comprador){
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

    public static double valorCusto(Encomenda enc){
        double val = Encomenda.valorFinalEncomenda(enc);
        setCusto(val);
        return val;
    }

    public Fatura(){
        this.comprador=null;
        this.vendedor=null;
        this.encomenda=null;
        custo=0;
    }

    public Fatura(Fatura fat){
        this.comprador=fat.comprador;
        this.vendedor=fat.vendedor;
        this.encomenda=fat.encomenda;
        custo=fat.custo;
    }

    public Fatura(Utilizadores comprador, Utilizadores vendedor, Encomenda compras) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.encomenda = compras;
        custo = Encomenda.valorFinalEncomenda(compras);
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
        return custo;
    }

    public static void setCusto(double cust) {
        custo = cust;
    }

    public Fatura clone(){
        return new Fatura(this);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura: {");
        //sb.append("Comprador: ").append(this.getComprador());
        //sb.append("; Vendedor: ").append(this.getVendedor());
        sb.append("; Encomenda: ").append(this.getEncomenda());
        sb.append("; Custo: ").append(this.getCusto());
        sb.append("}");

        return sb.toString();
    }
}
