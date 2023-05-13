import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Objects.hash;

public class Vintage implements Serializable,Decoy{

    private String currentUserEmail;
    private Map<Integer,Conta> contas;
    private Map<Integer,Utilizador> utilizadores;
    private List<Transportadora> transportadoras;
    private List<Encomenda> encomendas;
    private Map<Integer,Artigo> artigos;


    // CONTRUCTORS /////////////////////////////////////////////////////////////////////////////////////
    public Vintage(){
        this.currentUserEmail = "";
        this.contas = new HashMap<>();
        this.utilizadores = new HashMap<>();
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
        utilizadores.put(hash(u.getCodigo()), u.clone());
    }

    public void removeUser(Utilizador u){
        utilizadores.remove(hash(u.getCodigo()));
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
        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            Conta conta = entry.getValue().clone();
            if(conta.getEmail().equals(email) && conta.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean getContaByCodigo(String codigo){
        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            if(Objects.equals(entry.getValue().getCodigo(), codigo)){
                return true;
            }
        }
        return false;
    }

    public Conta getContaByCCodigo(String codigo){
        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            if(Objects.equals(entry.getValue().getCodigo(), codigo)){
                return entry.getValue();
            }
        }
        return null;
    }

    public Utilizador getUtilizadorByCodigo(String codigo) {
        int key = hash(codigo);

        if(getContaByCodigo(codigo)){
            return utilizadores.get(key);
        }
        return null;
    }

    public Transportadora getTransportdoraByName(String nome) {

        for(Transportadora value : this.transportadoras) {
            if(value.getNome().equals(nome)){
                return value;
            }
        }

        return null;
    }

    public Transportadora getTransportdoraByCodigo(String codigo) {
        for(Transportadora value : this.transportadoras) {
            if(value.getCodigo().equals(codigo)){
                return value;
            }
        }
        return null;
    }

    public Conta getContaByEmail(String email) {
        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            if (entry instanceof ContaTransportadora) {
                ContaTransportadora conta = (ContaTransportadora) entry.getValue().clone();
                if (conta.getEmail().equals(email)) {
                    return conta;
                }
            } else {
                    Conta conta2 = entry.getValue().clone();
                    if (conta2.getEmail().equals(email)) {
                        return conta2;
                    }
                }
            }
        return null;
    }

    public Utilizador getUtilizadorByEmail(String email) {
        for (Map.Entry<Integer, Utilizador> entry : utilizadores.entrySet()) {
            Utilizador u = entry.getValue().clone();
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    public void emiteFatura(LocalDate dataE,Artigo art, Utilizador vendedor, Utilizador comprador){
        Fatura nova = new Fatura(dataE,comprador,vendedor,art);
        double passado = vendedor.getValorTotalVendas() + art.getPrecoFinal();
        double custo = 0;
        if(art.getEstado()=='n'){
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo=art.getPrecoFinal()*t.getLucro()+0.5;
            custo = Math.round(custo * 100.0) / 100.0;
            nova.setCusto(custo);
        }
        else{
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo=art.getPrecoFinal()*t.getLucro()+0.25;
            custo = Math.round(custo * 100.0) / 100.0;
            nova.setCusto(custo);
        }
        vendedor.adicionaArtigoVendido(art);
        comprador.adicionaArtigoComprado(art);
        vendedor.removeArtigoAVenda(art);
        vendedor.setValorTotalVendas(passado);
        vendedor.adicionaFatura(nova);
        comprador.adicionaFatura(nova);
        nova.setCusto(custo);
    }

    public double valFatura(Fatura fat){
        double custo = 0;
        Artigo art = fat.getArtigo();
        if(art.getEstado()=='n'){
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo=art.getPrecoFinal()*t.getLucro()+0.5;
            custo = Math.round(custo * 100.0) / 100.0;
        }
        else{
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo=art.getPrecoFinal()*t.getLucro()+0.25;
            custo = Math.round(custo * 100.0) / 100.0;
        }
        return custo;
    }

    public double emiteFaturaParse(LocalDate dataE,Artigo art, String vendedor, String comprador){
        Fatura nova = new Fatura(dataE,this.getUtilizadorByCodigo(comprador),this.getUtilizadorByCodigo(vendedor),art);
        double passado = this.getUtilizadorByCodigo(vendedor).getValorTotalVendas() + art.getPrecoFinal();
        double custo = 0;
        if(art.getEstado()=='n'){
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo = art.getPrecoFinal()*t.getLucro()+0.5;
            custo = Math.round(custo * 100.0) / 100.0;
            nova.setCusto(custo);
        }
        else{
            String nome = art.getTransportadora();
            Transportadora t = this.getTransportdoraByName(nome);
            custo = art.getPrecoFinal()*t.getLucro()+0.25;
            custo = Math.round(custo * 100.0) / 100.0;
            nova.setCusto(custo);
        }
        this.getUtilizadorByCodigo(vendedor).setValorTotalVendas(passado);
        this.getUtilizadorByCodigo(vendedor).adicionaFatura(nova);
        this.getUtilizadorByCodigo(comprador).adicionaFatura(nova);
        return custo;
    }
    ////// Metodos para carregar e guardar estados

    // No this vai estar guardado uma vintage.
    public void carregaEstadoCSV(String name) {
        String fileName = name;
        String path = getPathToDecoy();
        File directory = new File(path);
        File[] contents = directory.listFiles();
        if(contents!=null) {
            for (File f : contents) {
                if(Objects.equals(fileName, f.getName())){
                    fileName = path + fileName;
                }
            }
        }

        String[] aux;
        String[] info;

        List<String> lines = lerFicheiro(fileName);

        for (String line : lines) {
            aux = null;
            info = null;
            String[] separatedLines = line.split("=", 2);

            switch (separatedLines[0]) {
                case "Utilizador" -> {
                    Utilizador u = parseUtilizador(separatedLines[1]);
                    //System.out.println(u.toString());
                    this.addUser(u);
                }
                case "Conta" -> {
                    Conta c = parseConta(separatedLines[1]);
                    //System.out.println(c.toString());
                    this.addConta(c);
                }
                case "ContaTransportadora" -> {
                    ContaTransportadora cp = parseContaTransportadora(separatedLines[1]);
                    //System.out.println(c.toString());
                    this.addConta(cp);
                }
                case "TransportadoraPremium" -> {
                    TransportadoraPremium tp = parseTransportadoraPremium(separatedLines[1]);
                    //System.out.println(tp.toString());
                    this.addTransportadora(tp);
                }
                case "Transportadora" -> {
                    Transportadora tr = parseTransportadora(separatedLines[1]);
                    //System.out.println(tr.toString());
                    this.addTransportadora(tr);
                }
                case "Sapatilha" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Sapatilha s = parseSapatilha(aux[1]);
                    this.addSapatilha(s, info[0], info[1].charAt(0));
                }
                case "SapatilhaPremium" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    SapatilhaPremium sp = parseSapatilhaPremium(aux[1]);
                    this.addSapatilha(sp, info[0], info[1].charAt(0));
                }
                case "Tshirt" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Tshirt t = parseTshirt(aux[1]);
                    this.addTshirt(t, info[0], info[1].charAt(0));
                }
                case "Mala" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Mala m = parseMala(aux[1]);
                    this.addMala(m, info[0], info[1].charAt(0));
                }
                case "MalaPremium" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    MalaPremium mp = parseMalaPremium(aux[1]);
                    this.addMala(mp, info[0], info[1].charAt(0));
                }
                case "Encomenda" -> {
                    Encomenda e = parseEncomenda(separatedLines[1]);
                    this.addEncomenda(e);
                }
                default -> System.out.println("Linha invalida.");
            }
        }
    }

    public Utilizador parseUtilizador(String input) {
        String[] info = input.split(",");
        String codigoUser = info[0];
        String nome = info[1];
        String email = info[2];
        String morada = info[3];
        String nif = info[4];
        return new Utilizador(codigoUser,nome,email,morada,nif);
    }

    public Conta parseConta(String input) {
        String[] info = input.split(",");
        String codigoUser = info[0];
        String email = info[1];
        String password = info[2];
        return new Conta(codigoUser,email,password);
    }

    public ContaTransportadora parseContaTransportadora(String input) {
        String[] info = input.split(",");
        String codigoUser = info[0];
        String email = info[1];
        String password = info[2];
        return new ContaTransportadora(codigoUser,email,password);
    }

    public Transportadora parseTransportadora(String input) {
        String[] info = input.split(",");
        String codigo = info[0];
        String nome = info[1];
        double lucro = Double.parseDouble(info[2]);
        return new Transportadora(codigo,nome,lucro);
    }

    public TransportadoraPremium parseTransportadoraPremium(String input) {
        String[] info = input.split(",");
        String codigo = info[0];
        String nome = info[1];
        double lucro = Double.parseDouble(info[2]);
        return new TransportadoraPremium(codigo,nome,lucro);
    }

    public Sapatilha parseSapatilha(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.parseInt(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        double tamanho = Double.parseDouble(info[8]);
        boolean temAtacadores = Boolean.parseBoolean(info[9]);
        String cor = info[10];
        String[] aux = info[11].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
        double desconto = Double.parseDouble(info[12]);

        return new Sapatilha(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,tamanho,temAtacadores,cor,dataFinal,desconto);
    }

    public SapatilhaPremium parseSapatilhaPremium(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.parseInt(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        double tamanho = Double.parseDouble(info[8]);
        boolean temAtacadores = Boolean.parseBoolean(info[9]);
        String cor = info[10];
        String[] aux = info[11].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
        double desconto = Double.parseDouble(info[12]);

        return new SapatilhaPremium(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,tamanho,temAtacadores,cor,dataFinal,desconto);
    }

    public Tshirt parseTshirt(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.parseInt(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        char tamanho = info[8].charAt(0);
        char padrao = info[9].charAt(0);

        return new Tshirt(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,tamanho,padrao);
    }

    public Mala parseMala(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.parseInt(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        int comprimento = Integer.parseInt(info[8]);
        int largura = Integer.parseInt(info[9]);
        int altura = Integer.parseInt(info[10]);
        String material = info[11];
        int data = Integer.parseInt(info[12]);

        return new Mala(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,comprimento, largura,altura,material,data);
    }

    public MalaPremium parseMalaPremium(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.parseInt(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        int comprimento = Integer.parseInt(info[8]);
        int largura = Integer.parseInt(info[9]);
        int altura = Integer.parseInt(info[10]);
        String material = info[11];
        int data = Integer.parseInt(info[12]);

        return new MalaPremium(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,comprimento, largura,altura,material,data);
    }

    public Encomenda parseEncomenda(String input) {

        String[] enc = input.split("@");
        String[] artigos = enc[0].split("]");

        String[] infoFinal = enc[1].split(",");
        int dimensao = Integer.parseInt(infoFinal[0]);
        char estado = infoFinal[1].charAt(0);
        String[] auxData = infoFinal[2].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.parseInt(auxData[0]),Integer.parseInt(auxData[1]),Integer.parseInt(auxData[2]));
        String codComprador = infoFinal[3];

        List<Artigo> artigosList = new ArrayList<>();

        for (String line : artigos) {
            String[] separatedLines = line.split("'", 2);

            switch (separatedLines[0]) {
                case "Sapatilha" -> {
                    Sapatilha s = parseSapatilha(separatedLines[1]);
                    artigosList.add(s);
                    this.emiteFaturaParse(dataFinal,s,s.getVendedor(),codComprador);
                }
                case "SapatilhaPremium" -> {
                    SapatilhaPremium sp = parseSapatilhaPremium(separatedLines[1]);
                    artigosList.add(sp);
                    this.emiteFaturaParse(dataFinal,sp,sp.getVendedor(),codComprador);
                }
                case "Tshirt" -> {
                    Tshirt t = parseTshirt(separatedLines[1]);
                    artigosList.add(t);
                    this.emiteFaturaParse(dataFinal,t,t.getVendedor(),codComprador);
                }
                case "Mala" -> {
                    Mala m = parseMala(separatedLines[1]);
                    artigosList.add(m);
                    this.emiteFaturaParse(dataFinal,m,m.getVendedor(),codComprador);
                }
                case "MalaPremium" -> {
                    MalaPremium mp = parseMalaPremium(separatedLines[1]);
                    artigosList.add(mp);
                    this.emiteFaturaParse(dataFinal,mp,mp.getVendedor(),codComprador);
                }
                default -> System.out.println("Linha invalida.");
            }
        }

        return new Encomenda(artigosList,dimensao,estado,dataFinal,codComprador);
    }

    public void addSapatilha(Sapatilha s, String codUser, char flag){
        switch (flag){

            case 'l' -> {
               this.addArtigo(s);
               this.getUtilizadorByCodigo(codUser).adicionaArtigoLoja(s);
               this.getTransportdoraByName(s.getTransportadora()).addArtigo(s);
            }

            case 'c' -> {
                this.getUtilizadorByCodigo(codUser).adicionaArtigoComprado(s);
                this.getUtilizadorByCodigo(s.getVendedor()).adicionaArtigoVendido(s);
                this.getTransportdoraByName(s.getTransportadora()).addArtigo(s);
            }
        }
    }

    public void addTshirt(Tshirt t, String codUser, char flag){
        switch (flag){

            case 'l' -> {
                this.addArtigo(t);
                this.getUtilizadorByCodigo(codUser).adicionaArtigoLoja(t);
                this.getTransportdoraByName(t.getTransportadora()).addArtigo(t);
            }

            case 'c' -> {
                this.getUtilizadorByCodigo(codUser).adicionaArtigoComprado(t);
                this.getUtilizadorByCodigo(t.getVendedor()).adicionaArtigoVendido(t);
                this.getTransportdoraByName(t.getTransportadora()).addArtigo(t);
            }
        }
    }

    public void addMala(Mala m, String codUser, char flag){
        switch (flag){

            case 'l' -> {
                this.addArtigo(m);
                this.getUtilizadorByCodigo(codUser).adicionaArtigoLoja(m);
                this.getTransportdoraByName(m.getTransportadora()).addArtigo(m);
            }

            case 'c' -> {
                this.getUtilizadorByCodigo(codUser).adicionaArtigoComprado(m);
                this.getUtilizadorByCodigo(m.getVendedor()).adicionaArtigoVendido(m);
                this.getTransportdoraByName(m.getTransportadora()).addArtigo(m);
            }
        }
    }

    public List<String> lerFicheiro(String nomeFile) {
        List<String> linhas = new ArrayList<>();

        try {
            linhas = Files.readAllLines(Paths.get(nomeFile), StandardCharsets.UTF_8);
        } catch (IOException exc) {
            System.out.println("Nao conseguiu ler o estado!");
        }
        return linhas;
    }

    public void handleEstado(String file) throws IOException{

        if(file.charAt((file.length()-1))=='j'){
            this.carregaEstadoObj(file);
        }
        else{
            this.carregaEstadoCSV(file);
        }
    }

    public void salvaEstado(String fileName) throws IOException {
        String path = getPathToDecoy();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path+fileName));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }


    //carregar de ficheiro objeto
    public void carregaEstadoObj(String fileName) throws IOException{
        String file = fileName;
        String path = getPathToDecoy();
        File directory = new File(path);
        File[] contents = directory.listFiles();
        if(contents!=null) {
            for (File f : contents) {
                if(Objects.equals(file, f.getName())){
                    file = path + file;
                }
            }
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Vintage v = null;
        try {
            v = (Vintage) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ois.close();

        this.currentUserEmail = v.getCurrentUserEmail();
        this.contas = v.getContas();
        this.utilizadores = v.getUtilizadores();
        this.transportadoras = v.getTransportadoras();
        this.encomendas = v.getEncomendas();
        this.artigos = v.getArtigos();
    }

    public String toString() {

        for (Map.Entry<Integer, Conta> entry : contas.entrySet()) {
            System.out.println(entry.getValue());
        }
        for (Map.Entry<Integer, Utilizador> entry : utilizadores.entrySet()) {
            System.out.println(entry.getValue());
        }
        for (Transportadora element1 : transportadoras) {
            System.out.println(element1);
        }
        for (Encomenda element : encomendas) {
            System.out.println(element);
        }
        for (Map.Entry<Integer, Artigo> entry : artigos.entrySet()) {
            System.out.println(entry.getValue());
        }

        return null;
    }

}
