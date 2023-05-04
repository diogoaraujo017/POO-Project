import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.hash;

public class Vintage {

    private String currentUser;
    private Map<Integer,Conta> contas;
    private Map<Integer,Utilizadores> utilizadores;
    private List<Transportadora> transportadoras;
    private List<Encomenda> encomendas;


    // CONTRUCTORS /////////////////////////////////////////////////////////////////////////////////////
    public Vintage(){
        this.currentUser = null;
        this.contas = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.transportadoras = new ArrayList<>();
        this.encomendas = new ArrayList<>();

    }

    public Vintage(String user, Map<Integer,Conta> contas, Map<Integer,Utilizadores> users,
                   List<Transportadora> transportadoras, List<Encomenda> encomendas){
        this.setCurrentUser(user);
        this.setContas(contas);
        this.setUtilizadores(users);
        this.setTransportadoras(transportadoras);
        this.setEncomendas(encomendas);
    }

    public Vintage(Vintage v){
        this.currentUser = v.getCurrentUser();
        this.contas = v.getContas();
        this.utilizadores = v.getUtilizadores();
        this.transportadoras = v.getTransportadoras();
        this.encomendas = v.getEncomendas();
    }

    // GETTERS ///////////////////////////////////////////////////////////////////////////////////////
    public String getCurrentUser() {
        return this.currentUser;
    }

    public Map<Integer, Conta> getContas() {
        return this.contas.entrySet().stream()
                                     .collect(Collectors
                                     .toMap(Map.Entry::getKey, e -> e.getValue().clone(),(a,b)->a, HashMap::new));
    }

    public Map<Integer, Utilizadores> getUtilizadores() {
        return this.utilizadores.entrySet().stream()
                   .collect(Collectors
                           .toMap(Map.Entry::getKey, e -> e.getValue().clone(),(a,b)->a, HashMap::new));
    }

    public List<Transportadora> getTransportadoras() {
        return this.transportadoras.stream()
                   .map(Transportadora::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Encomenda> getEncomendas() {
        return this.encomendas.stream()
                .map(Encomenda::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    // SETTERS //////////////////////////////////////////////////////////////////////////////////////////

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setContas(Map<Integer, Conta> contas) {
        this.contas = contas.entrySet().stream()
                            .collect(Collectors
                               .toMap(Map.Entry::getKey, v -> v.getValue().clone(),(a,b)->a, HashMap::new));
    }

    public void setUtilizadores(Map<Integer, Utilizadores> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream()
                                  .collect(Collectors
                                          .toMap(Map.Entry::getKey, v -> v.getValue().clone(),(a,b)->a, HashMap::new));
    }

    public void setTransportadoras(List<Transportadora> transportadoras) {
        this.transportadoras = new ArrayList<>(transportadoras);
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = new ArrayList<>(encomendas);
    }

    public Vintage clone(){
        return new Vintage(this);
    }




    public void addConta(Conta c){
        this.contas.put(hash(c.getEmail()), c.clone());
    }

    public void removeConta(Conta c){
        this.contas.remove(hash(c.getEmail()));
    }

    public boolean loginCorreto(String email,String password){
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

        if(this.contas.containsKey(key)){
            Conta conta = this.contas.get(key);
            codigo = conta.geraCodigo();
        }

        return codigo;
    }




}
