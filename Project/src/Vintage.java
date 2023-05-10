import java.util.*;
import java.util.stream.Collectors;
import static java.util.Objects.hash;

public class Vintage {

    private String currentUserEmail;
    private Map<Integer,Conta> contas;
    private Map<Integer,Utilizador> utilizadores;
    private List<Transportadora> transportadoras;
    private List<Encomenda> encomendas;
    private Map<Integer,Artigo> artigos;


    // CONTRUCTORS /////////////////////////////////////////////////////////////////////////////////////
    public Vintage(){
        this.currentUserEmail = "";
        contas = new HashMap<>();
        utilizadores = new HashMap<>();
        this.transportadoras = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.artigos = new HashMap<>();
    }

    public Vintage(String user, Map<Integer,Conta> contas, Map<Integer,Utilizador> users,
                   List<Transportadora> transportadoras, List<Encomenda> encomendas,Map<Integer,Artigo> artigos){
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

    public Map<Integer, Utilizador> getUtilizadores() {
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

    public Map<Integer,Artigo> getArtigos() {
        return this.artigos.entrySet().stream()
                   .collect(Collectors
                           .toMap(Map.Entry::getKey, e -> e.getValue().clone(),(a,b)->a, HashMap::new));
    }

    public List<Artigo> getListaArtigos() {
        return this.artigos.values().stream()
                   .map(Artigo :: clone)
                   .collect(Collectors.toList());
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

    public void setUtilizadores(Map<Integer, Utilizador> utilizadores) {
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

    public void setArtigos(Map<Integer,Artigo> artigos) {
        this.artigos = artigos.entrySet().stream()
                            .collect(Collectors
                               .toMap(Map.Entry::getKey, v -> v.getValue().clone(),(a,b)->a, HashMap::new));
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

    public void addUser(Utilizador u){
        utilizadores.put(hash(u.getEmail()), u.clone());
    }

    public void removeUser(Utilizador u){
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
        artigos.remove(hash(art.getCodigo()));
    }

    public void addArtigo(Artigo art){
        artigos.put(hash(art.getCodigo()), art.clone());
    }

    public boolean existeArtigo(String codigo){
        int hashCode = hash(codigo);
        Artigo art = artigos.get(hashCode);
        return art!= null && art.getCodigo().equals(codigo);
    }

    public Artigo getArtigo(String codigo){
        int hashCode = hash(codigo);
        Artigo art = artigos.get(hashCode);
        return art;
    }

    public boolean loginCorreto(String email, String password){
        int key = hash(email);

        if(contas.containsKey(key)){
            Conta conta = contas.get(key);
            return conta.getEmail().equals(email) && conta.getPassword().equals(password);
        }

        return false;
    }



    public Utilizador getUtilizadorByEmail(String email) {
        int key = hash(email);

        if(contas.containsKey(key)){
            return utilizadores.get(key);
        }
        return null;
    }

    public Utilizador getUtilizadorByCodigo(String codigo) {
        for (Map.Entry<Integer, Utilizador> entry : utilizadores.entrySet()) {
            Utilizador value = entry.getValue();
            if(value.getCodigo().equals(codigo)){
                return value.clone();
            }
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
