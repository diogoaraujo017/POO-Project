import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Utilizadores {
    
    private String codigo; // código identificador de cada user
    private String email; // usado para login
    private String nome;
    private String morada;
    private String nif;
    private List <Artigo> produtosVendidos;
    private List <Artigo> produtosLoja;
    private List <Artigo> produtosComprou;

    Utilizadores(){
        this.codigo=null;
        this.email=null;
        this.nome=null;
        this.morada=null;
        this.nif=null;
        this.produtosVendidos=new ArrayList<>();
        this.produtosLoja=new ArrayList<>();
        this.produtosComprou=new ArrayList<>();
    }

    Utilizadores(String nome, String email, String morada, String nif, List <Artigo> produtosVendidos,
                 List <Artigo> produtosLoja, List <Artigo> produtosComprou){

        this.codigo=UUID.randomUUID().toString(); // gera um código hexadecimal com 36 caracteres único para cada um dos utilizadores
        this.email=email;
        this.nome=nome;
        this.morada=morada;
        this.nif=nif;
        this.produtosVendidos = produtosVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
        this.produtosLoja = produtosLoja.stream().map(Artigo::clone).collect(Collectors.toList());
        this.produtosComprou = produtosComprou.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    Utilizadores(Utilizadores ut){

        this.codigo = ut.getCodigo();
        this.email = ut.getEmail();
        this.nome = ut.getNome();
        this.morada = ut.getMorada();
        this.nif = ut.getNif();
        this.produtosVendidos = ut.getProdutosVendidos();
        this.produtosLoja = ut.getProdutosLoja();
        this.produtosComprou = ut.getProdutosComprou();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNome(){
        return this.nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getNif() {
        return nif;
    }

    public List<Artigo> getProdutosVendidos() {
        return produtosVendidos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Artigo> getProdutosLoja() {
        return produtosLoja.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public List<Artigo> getProdutosComprou() {
        return produtosComprou.stream().map(Artigo::clone).collect(Collectors.toList());
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
        produtosVendidos.clear();
    }

    public void setListaVenda(List<Artigo> lvenda) {
        produtosLoja.clear();
    }

    public void setListaCompra(List<Artigo> lcompra) {
        produtosComprou.clear();
    }


    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Utilizadores e = (Utilizadores) obj;
        return e.getCodigo().equals(this.codigo) &&
               e.getEmail().equals(this.email) &&
               e.getNome().equals(this.nome) &&
               e.getMorada().equals(this.morada) &&
               e.getNif().equals(this.nif) &&
               e.getProdutosVendidos().equals(this.produtosVendidos) &&
               e.getProdutosLoja().equals(this.produtosLoja) &&
               e.getProdutosComprou().equals(this.produtosComprou);
    }

    public String toString() {
        int cont = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador: {");
        sb.append("Nome: ").append(this.nome);
        sb.append("; Código: ").append(this.codigo);
        sb.append("; Email: ").append(this.email);
        sb.append("; Morada: ").append(this.morada);
        sb.append("; NIF: ").append(this.nif);
        sb.append("}");

        sb.append("\n\nLista de produtos vendidos: ");
        sb.append("\n");
        for (Artigo art : this.produtosVendidos){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        cont=1;
        sb.append("\n");

        sb.append("\n\nLista de produtos à venda: ");
        sb.append("\n");
        for (Artigo art : this.produtosLoja){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        cont=1;
        sb.append("\n");

        sb.append("\n\nLista de produtos adquiridos: ");
        sb.append("\n");
        for (Artigo art : this.produtosComprou){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        sb.append("\n");
        return sb.toString();
    }

    public Utilizadores clone(){
        return new Utilizadores(this);
    }
}
