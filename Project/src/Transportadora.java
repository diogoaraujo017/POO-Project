import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transportadora {
    private String nome; // CTT, USPS, UPS, DHL, FedEx
    private double lucro;
    private boolean e_premium;
    private List<Encomenda> encomendas;

    public Transportadora(){
        this.nome="";
        this.lucro=0.00;
        this.e_premium=false;
        this.encomendas= new ArrayList<>();
    }
    public Transportadora(Transportadora t){
        this.nome=t.getNome();
        this.lucro=t.getLucro();
        this.e_premium=t.getEPremium();
        this.encomendas=t.getEncomendas();
    }

    public Transportadora(String nome, double taxa, boolean prem, List<Encomenda> lista_encomendas) {
        this.nome = nome;
        this.lucro=taxa;
        this.e_premium=prem;
        this.encomendas= lista_encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public double getLucro() {
        return lucro;
    }

    public boolean getEPremium() {
        return e_premium;
    }

    public List<Encomenda> getEncomendas() {
        return encomendas.stream().map(Encomenda::clone).collect(Collectors.toList());
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public void setEPremium(boolean e_premium) {
        this.e_premium = e_premium;
    }

    public void setEncomendas(List<Encomenda> lista_encomendas) {
        encomendas.clear();
        encomendas=new ArrayList<>(lista_encomendas);
    }


    public double precoExpedido(Transportadora transporte){
        List <Encomenda> encomenda = transporte.getEncomendas();
        double valor_bruto=0.00;
        double valor_final=0.00;
        double imposto=0.23;
        double base=0.00;
        // int tam=encomenda.getDimensao();
        // if(tam==1) base=1.00;
        // if((tam>=2)&&(tam<=5)) base=1.10;
        // if(tam>=6) base=1.20;
        for(Encomenda enc : encomenda){
            valor_bruto+=enc.valorFinalEncomenda(enc);
        }
        if(transporte.getEPremium()) {
            valor_final=(valor_bruto * this.lucro * (1.0 + imposto) * 0.9)*1.5*base;

        }
        else {
            valor_final=valor_bruto * this.lucro * (1.0 + imposto) * 0.9*base;
        }
        valor_final = Math.round(valor_final * 100.0) / 100.0;;
        return valor_final;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora: {");
        sb.append("Nome: ").append(this.nome);
        sb.append("; Margem de Lucro: ").append(this.lucro);
        sb.append("; Premium: ").append(this.e_premium);
        sb.append("; Lista de produtos transportados: ").append(this.encomendas);
        sb.append("}");

        return sb.toString();
    }

    public Transportadora clone(){
        return new Transportadora(this);
    }
}


