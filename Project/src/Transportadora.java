import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transportadora  implements Serializable {
    private String codigo;
    private String nome; // CTT, USPS, UPS, DHL, FedEx
    private double lucro;
    private List<Artigo> artigos;

    public Transportadora(){
        this.codigo="";
        this.nome="";
        this.lucro=0.00;
        this.artigos= new ArrayList<>();
    }

    public Transportadora(String codigo,String nome, Double lucro){
        this.codigo=codigo;
        this.nome = nome;
        this.lucro = lucro;
        this.artigos = new ArrayList<>();
    }

    public Transportadora(String nome, Double lucro){
        this.codigo="";
        this.nome = nome;
        this.lucro = lucro;
        this.artigos = new ArrayList<>();
    }

    public Transportadora(String codigo,String nome, Double lucro, List<Artigo> artigos){
        this.codigo=codigo;
        this.nome = nome;
        this.lucro = lucro;
        this.artigos = new ArrayList<>(artigos);
    }

    public Transportadora(Transportadora t){
        this.codigo=t.getCodigo();
        this.nome=t.getNome();
        this.lucro=t.getLucro();
        this.artigos=t.getArtigos();
    }

    public String getCodigo() {
        return this.codigo;
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

    public double precoExpedido(Encomenda encomenda, Vintage vin){
        List <Artigo> lartigos = encomenda.getArtigos();
        double valor_bruto=0.00;
        double valor_final=0.00;
        double imposto=0.23;
        double base=0.00;
        for(Transportadora trans : vin.getTransportadoras()){
            int contador = 0;
            int soma = 0;
            for(Artigo art: lartigos){
                if(art.getTransportadora().equals(trans.nome)){
                    contador++;
                    soma+=art.getPrecoFinal();
                    if(art.getEstado()=='n'){
                        soma+=0.5;
                    }
                    else soma+=0.25;
                }
            }
            if(contador==1) base=1.20;
            if((contador>=2)&&(contador<=5)) base=1.10;
            if(contador>=6) base=1.05;
            valor_final+=soma * trans.getLucro()*(1.0+imposto)*0.9*base;
        }
        valor_final = Math.round(valor_final * 100.0) / 100.0;
        return valor_final;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora: {");
        sb.append("Nome: ").append(this.nome);
        sb.append("; Margem de Lucro: ").append(this.lucro);
        sb.append("; Lista de produtos transportados: ");
        for (Artigo element : this.artigos) {
            sb.append(element);
        }
        sb.append("}");

        return sb.toString();
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }
}


