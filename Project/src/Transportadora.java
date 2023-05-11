import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transportadora {
    private String nome; // CTT, USPS, UPS, DHL, FedEx
    private double lucro;
    private List<Artigo> artigos;

    public Transportadora(){
        this.nome="";
        this.lucro=0.00;
        this.artigos= new ArrayList<>();
    }

    public Transportadora(String nome, Double lucro){
        this.nome = nome;
        this.lucro = lucro;
        this.artigos = new ArrayList<>();
    }

    public Transportadora(String nome, Double lucro, List<Artigo> artigos){
        this.nome = nome;
        this.lucro = lucro;
        this.artigos = new ArrayList<>(artigos);
    }

    public Transportadora(Transportadora t){
        this.nome=t.getNome();
        this.lucro=t.getLucro();
        this.artigos=t.getArtigos();
    }

    public Transportadora(String nome, double taxa, List<Artigo> lista_encomendas) {
        this.nome = nome;
        this.lucro=taxa;
        this.artigos= lista_encomendas.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public String getNome() {
        return this.nome;
    }

    public double getLucro() {
        return this.lucro;
    }

    public List<Artigo> getArtigos() {
        return this.artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public void setArtigos(List<Artigo> lista_encomendas) {
        this.artigos.clear();
        this.artigos=new ArrayList<>(lista_encomendas);
    }

    public void addArtigo(Artigo a){
        this.artigos.add(a.clone());
    }

    /*public double precoExpedido(Transportadora transporte){
        List <Artigo> lartigos = transporte.getArtigos();
        double valor_bruto=0.00;
        double valor_final=0.00;
        double imposto=0.23;
        double base=0.00;
        // int tam=encomenda.getDimensao();
        // if(tam==1) base=1.00;
        // if((tam>=2)&&(tam<=5)) base=1.10;
        // if(tam>=6) base=1.20;
        for(Artigo artigo : lartigos){
            valor_bruto+=artigo.valorFinalEncomenda(artigos);
        }
        if(transporte.getEPremium()) {
            valor_final=(valor_bruto * this.lucro * (1.0 + imposto) * 0.9)*1.5*base;

        }
        else {
            valor_final=valor_bruto * this.lucro * (1.0 + imposto) * 0.9*base;
        }
        valor_final = Math.round(valor_final * 100.0) / 100.0;
        return valor_final;
    }*/

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora: {");
        sb.append("Nome: ").append(this.nome);
        sb.append("; Margem de Lucro: ").append(this.lucro);
        sb.append("; Lista de produtos transportados: ").append(this.artigos);
        sb.append("}");

        return sb.toString();
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }
}


