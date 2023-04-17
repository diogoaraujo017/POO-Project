import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utilizadores {
    
    private String codigo; // código identificador de cada user
    private String email; // usado para login
    private String nome;
    private String morada;
    private String nif;
    private List <Artigo> lista_vendeu;
    private List <Artigo> lista_venda;
    private List <Artigo> lista_compra;

    Utilizadores(){
        this.codigo=null;
        this.email=null;
        this.nome=null;
        this.morada=null;
        this.nif=null;
        this.lista_vendeu=new ArrayList<>();
        this.lista_venda=new ArrayList<>();
        this.lista_compra=new ArrayList<>();
    }

    Utilizadores(String email, String nome, String morada, String nif, List <Artigo> lista_vendeu, List <Artigo> lista_venda, List <Artigo> lista_compra){
        this.codigo=UUID.randomUUID().toString(); // gera um código hexadecimal com 36 caracteres único para cada um dos utilizadores
        this.email=email;
        this.nome=nome;
        this.morada=morada;
        this.nif=nif;
        this.lista_vendeu=new ArrayList<>();
        this.lista_venda=new ArrayList<>();
        this.lista_compra=new ArrayList<>();

        for(Artigo i : lista_vendeu){
            this.lista_vendeu.add(i.clone());
        }

        for(Artigo i : lista_venda){
            this.lista_venda.add(i.clone());
        }

        for(Artigo i : lista_compra){
            this.lista_compra.add(i.clone());
        }
    }

    Utilizadores(Utilizadores ut){
        this.codigo=ut.getCodigo();
        this.email=ut.getEmail();
        this.nome=ut.getNome();
        this.morada=ut.getMorada();
        this.nif=ut.getNif();
        this.lista_vendeu=ut.getListaVendeu();
        this.lista_venda=ut.getListaVenda();
        this.lista_compra=ut.getListaCompra();
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

    public List<Artigo> getListaVendeu() {

        List <Artigo> lst_vendeu = new ArrayList<>();
        
        for(Artigo a : lista_vendeu){
            lst_vendeu.add(a.clone());
        }

        return lst_vendeu;
    }

    public List<Artigo> getListaVenda() {

        List <Artigo> lst_venda = new ArrayList<>();
        
        for(Artigo a : lista_venda){
            lst_venda.add(a.clone());
        }

        return lst_venda;
    }

    public List<Artigo> getListaCompra() {

        List <Artigo> lst_compra = new ArrayList<>();
        
        for(Artigo a : lista_compra){
            lst_compra.add(a.clone());
        }

        return lst_compra;
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
        lista_vendeu.clear();
    }

    public void setListaVenda(List<Artigo> lvenda) {
        lista_venda.clear();
    }

    public void setListaCompra(List<Artigo> lcompra) {
        lista_compra.clear();
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
               e.getListaVendeu().equals(this.lista_vendeu) &&
               e.getListaVenda().equals(this.lista_venda) &&
               e.getListaCompra().equals(this.lista_compra);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador: {");
        sb.append("Nome: ").append(this.nome);
        sb.append("; Código: ").append(this.codigo);
        sb.append("; Email: ").append(this.email);
        sb.append("; Morada: ").append(this.morada);
        sb.append("; NIF: ").append(this.nif);
        sb.append("; Lista de produtos vendidos: ").append(this.lista_vendeu);
        sb.append("; Lista de produtos à venda: ").append(this.lista_venda);
        sb.append("; Lista de produtos adquiridos: ").append(this.lista_compra);
        sb.append("}");
        return sb.toString();
    }  
}
