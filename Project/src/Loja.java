import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {
    private List<Utilizadores> users;

    public Loja(Loja shop){
        this.users=shop.getUtilizadores();
    }
    public Loja(List <Utilizadores> lista_artigos){
        this.users=lista_artigos.stream().map(Utilizadores::clone).collect(Collectors.toList());
    }
    public Loja(){
        this.users=new ArrayList<>();
    }
    public List<Utilizadores> getUtilizadores() {
        return users.stream().map(Utilizadores::clone).collect(Collectors.toList());
    }
    public void setUtilizadores(List<Utilizadores> lista_users) {
        users.clear();
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Loja e = (Loja) obj;
        return e.getUtilizadores().equals(this.users);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Artigo> total = new ArrayList<>();
        total=getTodosArtigosAVenda(this.users);
        int cont=1;
        sb.append("Artigos disponíveis: ");
        for (Utilizadores art : this.users){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

    public List<Artigo> getTodosArtigosAVenda(List<Utilizadores> pessoas){
        List<Artigo> venda = new ArrayList<>();
        for(Utilizadores util : pessoas){
            for(Artigo art : util.getProdutosLoja()){
                venda.add(art);
            }
        }
        return venda;
    }
}

