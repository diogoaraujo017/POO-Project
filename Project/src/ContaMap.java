import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.hash;

public class ContaMap {
    private static Map<Integer,Conta> contas;


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

    public static void addConta(Conta c){
        contas.put(hash(c.getEmail()), c.clone());
    }

    public void removeConta(Conta c){
        this.contas.remove(hash(c.getEmail()));
    }

    public static boolean loginCorreto(String email,String password){
        int key = hash(email);

        if(contas.containsKey(key)){
            Conta conta = contas.get(key);
            if(conta.getEmail().equals(email) && conta.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }


    public String getCodigoUtilizadores(String email){
        int key = hash(email);
        String codigo = null;

        if(contas.containsKey(key)){
            Conta conta = contas.get(key);
            codigo = conta.geraCodigo();
        }

        return codigo;
    }
}

