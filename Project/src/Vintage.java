import java.util.*;
import java.util.stream.Collectors;
import static java.util.Objects.hash;

public class Vintage {

    private String currentUserEmail;
    private Map<Integer,Conta> contas;
    private Map<Integer,Utilizadores> utilizadores;
    private List<Transportadora> transportadoras;
    private List<Encomenda> encomendas;
    private List<Artigo> artigos;


    // CONTRUCTORS /////////////////////////////////////////////////////////////////////////////////////
    public Vintage(){
        this.currentUserEmail = "";
        contas = new HashMap<>();
        utilizadores = new HashMap<>();
        this.transportadoras = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.artigos = new ArrayList<>();
    }

    public Vintage(String user, Map<Integer,Conta> contas, Map<Integer,Utilizadores> users,
                   List<Transportadora> transportadoras, List<Encomenda> encomendas,List<Artigo> artigos){
        this.currentUserEmail=user;
        this.contas=contas;
        this.utilizadores=users;
        this.transportadoras=transportadoras;
        this.encomendas=encomendas;
        this.artigos=artigos;
    }

    public Vintage(Vintage v){
        this.currentUserEmail = v.getCurrentUserEmail();
        this.contas = v.getContas();
        this.utilizadores = v.getUtilizadores();
        this.transportadoras = v.getTransportadoras();
        this.encomendas = v.getEncomendas();
        this.artigos= v.getArtigos();
    }

    // GETTERS ///////////////////////////////////////////////////////////////////////////////////////
    public String getCurrentUserEmail() {
        return this.currentUserEmail;
    }

    public Vintage getLoja(Vintage vin){
        return new Vintage(vin);
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

    public List<Artigo> getArtigos() {
        return this.artigos.stream()
                .map(Artigo::clone).collect(Collectors.toCollection(ArrayList::new));
    }

    // SETTERS //////////////////////////////////////////////////////////////////////////////////////////

    public void setCurrentUserEmail(String currentUser) {
        this.currentUserEmail = currentUser;
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

    public void setMalas(List<Artigo> artigos) {
        this.artigos = new ArrayList<>(artigos);
    }

    public Vintage clone(){
        return new Vintage(this);
    }


    // METODOS DA CLASSE ////////////////////////////////////////////////////////////////////////////

    public void addConta(Conta c){
        contas.put(hash(c.getEmail()), c.clone());
    }

    public void removeConta(Conta c){
        contas.remove(hash(c.getEmail()));
    }

    public void addUser(Utilizadores u){
        utilizadores.put(hash(u.getEmail()), u.clone());
    }

    public void removeUser(Utilizadores u){
        utilizadores.remove(hash(u.getEmail()));
    }

    public void addTransportadora(Transportadora t){
        this.transportadoras.add(t.clone());
    }

    public void removeTrasnportadora(Transportadora t){
        this.transportadoras.remove(t);
    }

    public void addEncomenda(Encomenda e){
        this.encomendas.add(e.clone());
    }

    public void removeEncomenda(Encomenda e){
        this.encomendas.remove(e);
    }

    public void removeArtigo(Artigo art){
        this.artigos.remove(art);
    }

    public void addArtigo(Artigo art){
        this.artigos.add((Artigo) art.clone());
    }
    public boolean loginCorreto(String email, String password){
        int key = hash(email);

        if(contas.containsKey(key)){
            Conta conta = contas.get(key);
            return conta.getEmail().equals(email) && conta.getPassword().equals(password);
        }

        return false;
    }


    public Utilizadores getUtilizadorByEmail(String email) {
        int key = hash(email);

        if(contas.containsKey(key)){
            return utilizadores.get(key);
        }
        return null;
    }

    public Transportadora getTransportdoraByName(String nome) {

        for(Transportadora value : transportadoras) {
            if(value.getNome().equals(nome)){
                return value;
            }
        }

        return null;
    }

    public Conta getContaByEmail(String email) {

        int key = hash(email);

        if(contas.containsKey(key)){
            return contas.get(key);
        }
        return null;
    }


    /// MISC ////////////////////////////////////////////////////////////////////////////////

    /*public static Utilizadores getUsersByCode(String code) {
        int key = hash(code);

        Utilizadores u = null;

        try {
            u = utilizadores.get(key);
        } catch (Exception e) {
            System.out.println("User does not exist!");
        }

        return u;
    }*/

    /*public static String getCodigoUtilizadores(String email){
        int key = hash(email);
        String codigo = null;

        if(contas.containsKey(key)){
            Conta conta = contas.get(key);
            codigo = conta.geraCodigo();
        }

        return codigo;
    }*/
}
