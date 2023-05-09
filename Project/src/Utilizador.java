import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utilizador {
    
    private String codigo; // código identificador de cada user de 8 digitos
    private String email; // usado para login
    private String nome;
    private String morada;
    private String nif;
    private List <Artigo> produtosVendidos;
    private List <Artigo> produtosLoja;
    private List <Artigo> produtosComprou;
    private double valorTotalVendas;
    private List<Fatura> faturas;

    Utilizador(){
        this.codigo=null;
        this.email=null;
        this.nome=null;
        this.morada=null;
        this.nif=null;
        this.produtosVendidos=new ArrayList<>();
        this.produtosLoja=new ArrayList<>();
        this.produtosComprou=new ArrayList<>();
        this.valorTotalVendas=0;
        this.faturas=new ArrayList<>();
    }

    Utilizador(String codigo,String nome, String email, String morada, String nif, List <Artigo> produtosVendidos,
                 List <Artigo> produtosLoja, List <Artigo> produtosComprou, List<Fatura> fat){
        this.codigo=codigo;
        this.email=email;
        this.nome=nome;
        this.morada=morada;
        this.nif=nif;
        this.produtosVendidos = produtosVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
        this.produtosLoja = produtosLoja.stream().map(Artigo::clone).collect(Collectors.toList());
        this.produtosComprou = produtosComprou.stream().map(Artigo::clone).collect(Collectors.toList());
        valorTotalVendas(produtosVendidos,0);
        this.faturas=fat;
    }

    Utilizador(Utilizador ut){

        this.codigo = ut.getCodigo();
        this.email = ut.getEmail();
        this.nome = ut.getNome();
        this.morada = ut.getMorada();
        this.nif = ut.getNif();
        this.produtosVendidos = ut.getProdutosVendidos();
        this.produtosLoja = ut.getProdutosLoja();
        this.produtosComprou = ut.getProdutosComprou();
        valorTotalVendas(getProdutosVendidos(),0);
        this.faturas=ut.faturas;
    }

    public void valorTotalVendas(List<Artigo> list, double vendidoPassado){
        double taxa_vintage=1.03;
        double valortotal=vendidoPassado;
        for(Artigo art : list){
            valortotal+=art.getPrecoFinal();
        }
        valortotal=valortotal/taxa_vintage;
        valortotal=Math.round(valortotal * 100.0) / 100.0;;
        setValorTotalVendas(valortotal);
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public String getNif() {
        return this.nif;
    }

    public double getValorTotalVendas() {
        return this.valorTotalVendas;
    }

    public List<Artigo> getProdutosVendidos() {
        return this.produtosVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Artigo> getProdutosLoja() {
        return this.produtosLoja.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Artigo> getProdutosComprou() {
        return this.produtosComprou.stream().map(Artigo::clone).collect(Collectors.toList());
    }
    public List<Fatura> getFaturas() {
        return faturas.stream().map(Fatura::clone).collect(Collectors.toList());
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setListaVendeu(List<Artigo> lvendeu) {
        this.produtosVendidos.clear();
        this.produtosVendidos=new ArrayList<>(lvendeu);
        valorTotalVendas(lvendeu,0);
    }

    public void setListaVenda(List<Artigo> lvenda) {
        this.produtosLoja.clear();
        this.produtosLoja=new ArrayList<>(lvenda);
    }

    public void setListaCompra(List<Artigo> lcompra) {
        this.produtosComprou.clear();
        this.produtosComprou=new ArrayList<>(lcompra);
    }

    public void setValorTotalVendas(double total) {
        this.valorTotalVendas = total;
    }

    public void setFatura(List<Fatura> faturas) {
        this.faturas.clear();
        this.faturas=new ArrayList<>(faturas);
    }

    public void adicionaArtigoVendido(Artigo art){
        this.produtosVendidos.add(art);
    }

    public void adicionaArtigoComprado(Artigo art){
        this.produtosComprou.add(art);
    }

    public void removeArtigoVendido(Artigo art){
        this.produtosLoja.remove(art);
    }
    public void adicionaFatura(Fatura fat){
        this.faturas.add(fat);
    }
    public void removeFatura(Fatura fat){
        this.faturas.remove(fat);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Utilizador e = (Utilizador) obj;
        return e.getCodigo().equals(this.codigo) &&
               e.getEmail().equals(this.email) &&
               e.getNome().equals(this.nome) &&
               e.getMorada().equals(this.morada) &&
               e.getNif().equals(this.nif) &&
               e.getProdutosVendidos().equals(this.produtosVendidos) &&
               e.getProdutosLoja().equals(this.produtosLoja) &&
               e.getProdutosComprou().equals(this.produtosComprou) &&
               e.getFaturas().equals(this.faturas);
    }

    public String toString() {
        int cont = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador: {");
        sb.append("Nome: ").append(this.getNome());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Email: ").append(this.getEmail());
        sb.append("; Morada: ").append(this.getMorada());
        sb.append("; NIF: ").append(this.getNif());
        sb.append("}");

        sb.append("\n\nLista de produtos vendidos: ");
        sb.append("\n");
        for (Artigo art : this.getProdutosVendidos()){
            sb.append("\n->Artigo" + cont+ ": ");
            if(art.getEstado()=='d') sb.append("*DEVOLVIDO* ");
            sb.append(art);
            sb.append("\n");
            cont++;
        }
        cont=1;
        sb.append("\n");

        sb.append("\n\nLista de produtos à venda: ");
        sb.append("\n");
        for (Artigo art : this.getProdutosLoja()){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        cont=1;
        sb.append("\n");

        sb.append("\n\nLista de produtos adquiridos: ");
        sb.append("\n");
        for (Artigo art : this.getProdutosComprou()){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            if(art.getEstado()=='d') sb.append("*DEVOLVIDO* ");
            sb.append("\n");
            cont++;
        }
        cont=1;
        sb.append("\n");

        sb.append("\n\nLista de Faturas: ");
        sb.append("\n");
        for (Fatura fat : this.getFaturas()){
            sb.append("\n->Fatura" + cont+ ": ").append(fat);
            sb.append("\n");
            cont++;
        }
        sb.append("\n\n");
        

        sb.append("Valor total das vendas: ").append(this.getValorTotalVendas());
        sb.append("\n");
        return sb.toString();
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

}
