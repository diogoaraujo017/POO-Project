import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Loja {
    private List<Utilizador> users;

    public Loja(Loja shop){
        this.users=shop.getUtilizadores();
    }
    public Loja(List <Utilizador> lista_artigos){
        this.users=lista_artigos.stream().map(Utilizador::clone).collect(Collectors.toList());
    }
    public Loja(){
        this.users=new ArrayList<>();
    }
    public List<Utilizador> getUtilizadores() {
        return users.stream().map(Utilizador::clone).collect(Collectors.toList());
    }
    public void setUtilizadores(List<Utilizador> lista_users) {
        users.clear();
        users=new ArrayList<>(lista_users);
    }

    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Loja e = (Loja) obj;
        return e.getUtilizadores().equals(this.users);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int cont=1;
        sb.append("Artigos disponíveis: ");
        for (Utilizador art : this.users){
            sb.append("\n->Artigo" + cont+ ": ").append(art);
            sb.append("\n");
            cont++;
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        return sb.toString();
    }

    public List<Artigo> getTodosArtigosAVenda(List<Utilizador> pessoas){
        List<Artigo> venda = new ArrayList<>();
        for(Utilizador util : pessoas){
            for(Artigo art : util.getProdutosLoja()){
                venda.add(art);
            }
        }
        return venda;
    }
}

