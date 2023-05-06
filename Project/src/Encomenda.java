import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encomenda {

    private List <Artigo> artigos; // coleção do artigo
    private int dimensao; // 1 artigo - encomenda pequena // 2 a 5 artigos - encomenda média // > 5 artigos - encomenda grande
    private char estado; // p - pendente // f - finalizada // e - expedida // d - devolvida
    private LocalDate data; // data de criação

    public Encomenda(){
        this.artigos=new ArrayList<>();
        this.dimensao=0;
        this.estado='p';
        this.data=LocalDate.now();
    }

    public Encomenda(List <Artigo> lista_artigos, int dimensao, char estado, LocalDate data){
        this.artigos=lista_artigos.stream().map(Artigo::clone).collect(Collectors.toList());
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
        int dim = getDimensao();
        setDimensao(dim++);
    }

    public void removerArtigoEncomenda(Artigo art){
        artigos.remove(art);
        int dim = getDimensao();
        setDimensao(dim--);
    }

    public void devolverEncomenda(Encomenda enc){
        long dias_diferenca = ChronoUnit.DAYS.between(enc.getData(), LocalDate.now());
        List <Artigo> list = enc.getArtigos();
        if(enc.estado=='e' && dias_diferenca>=2){ // pode devolver a encomenda
            clearArtigos();
            setDimensao(0);
            setEstado('d');

            for(Artigo art : list){
                art.setEstado('d');
            }
        }
    }


    public List<Artigo> getArtigos() {
        return this.artigos.stream().map(Artigo::clone).collect(Collectors.toList());
    }

    public int getDimensao() {
        return this.dimensao;
    }

    public char getEstado() {
        return this.estado;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void clearArtigos() {
        artigos.clear();
    }

    public void setArtigos(List<Artigo> lista_artigos) {
        artigos.clear();
        artigos=new ArrayList<>(lista_artigos);
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
        int cont=1;
        char aux = getEstado();
        sb.append("Encomenda: ");
        for (Artigo art : this.getArtigos()){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        sb.append("\nNúmero de Artigos: ").append(this.getDimensao());
        sb.append("; Estado: ");
        if(aux=='p') sb.append("Pendente");
        else if(aux=='f') sb.append("Finalizado");
        else if(aux=='d') sb.append("Devolvida");
        else sb.append("Expedido");

        return sb.toString();
    }
}