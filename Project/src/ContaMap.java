import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContaMap {
    private Map<Integer,Conta> contas;


    public ContaMap(){
        this.contas = new HashMap<>();
    }

    public ContaMap(Map<Integer, Conta> c){
        this.setContas(c);
    }

    public ContaMap(ContaMap c){
         this.contas = c.getContas();
    }

    public Map<Integer, Conta> getContas() {
        return this.contas.entrySet().stream()
                                     .collect(Collectors
                                              .toMap(Map.Entry::getKey, Map.Entry::getValue,(a,b)->a, HashMap::new));
    }

    public void setContas(Map<Integer, Conta> contas) {
        this.contas = contas.entrySet().stream()
                                       .collect(Collectors
                                               .toMap(Map.Entry::getKey, v -> v.getValue().clone(),(a,b)->a, HashMap::new));
    }


    public ContaMap clone(){
        return new ContaMap(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contas: ");
        sb.append(contas.values().stream()
                .map(Conta::toString)
                .collect(Collectors.joining(",\n")));
        sb.append('\n');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaMap contaMap = (ContaMap) o;
        return Objects.equals(contas, contaMap.contas);
    }

    public void addConta(Conta c){
        this.contas.put(c.hashCode(), c.clone());
    }

    public void loginCorreto(String email,String password){
        int key = c.ashCode();
    }

}

