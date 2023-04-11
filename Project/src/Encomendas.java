import java.time.LocalDate;

public class Encomendas {
    
    private String colecao; // coleção do artigo
    private char dimensao; // p - pequeno // m - médio // g - grande
    private int preco; // preço total
    private char estado; // p - pendente // f - finalizada // e - expedida
    private LocalDate data; // data de criação

    public Encomenda() {
        this.colecao=null;
        
    }

}
