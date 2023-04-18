import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda {

    private List <Artigo> artigos; // coleção do artigo
    private int dimensao; // 1 artigo - encomenda pequena // 2 a 5 artigos - encomenda média // > 5 artigos - encomenda grande
    private char estado; // p - pendente // f - finalizada // e - expedida
    private LocalDate data; // data de criação

    public Encomenda(){
        this.artigos=new ArrayList<>();
        this.dimensao=0;
        this.estado='p';
        this.data=LocalDate.now();
    }

    public Encomenda(List <Artigo> lista_artigos, int dimensao, char estado, LocalDate data){
        this.artigos=artigos.stream().map(Artigo::clone).collect(Collectors.toList());
        this.dimensao=dimensao;
        this.estado=estado;
        this.data=data;
    }

    public Encomenda(Encomenda enc){
        this.artigos=enc.getArtigos();
        this.dimensao=enc.getDimensao();
        this.estado=enc.getEstado();
        this.data=enc.getData();
    }

    public void adicionarArtigoEncomenda(Artigo art){
        artigos.add(art.clone());
    }

    public void removerArtigoEncomenda(Artigo art){
        artigos.remove(art);
    }

    public void devolverEncomenda(Encomenda enc){
        long dias_diferenca = ChronoUnit.DAYS.between(enc.getData(), LocalDate.now());

        if(enc.estado=='e' && dias_diferenca>=2){ // pode devolver a encomenda
            setArtigos(artigos);
        }
    }

    public double valorFinalEncomenda(Encomenda enc){
        double total = 0;

        for(Artigo a : artigos){
            total+=a.getPrecoFinal();
            if(a.getEstado()=='n') total+=0.5;
            else total+=0.25;
        }
        
        return total;
    }

    public List<Artigo> getArtigos() {
        return artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public int getDimensao() {
        return dimensao;
    }

    public char getEstado() {
        return estado;
    }

    public LocalDate getData() {
        return data;
    }


    public void setArtigos(List<Artigo> lista_artigos) {
        artigos.clear();
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Encomenda clone(){
        return new Encomenda(this);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Encomenda e = (Encomenda) obj;
        return e.getArtigos().equals(this.artigos) &&
               e.getDimensao() == this.dimensao &&
               e.getEstado() == this.estado &&
               e.getData().equals(this.data);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        char aux = getEstado();
        sb.append("Encomenda: {");
        sb.append("Artigos: ").append(this.artigos);
        sb.append("; Número de Artigos: ").append(this.dimensao);
        sb.append("; Estado: ");
        if(aux=='p') sb.append("Pendente");
        else if(aux=='f') sb.append("Finalizado");
        else sb.append("Expedido");
        sb.append("}");

        return sb.toString();
    }

    



}