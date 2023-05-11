import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Estado {

    // No this vai estar guardado uma vintage.
    public void carregaEstado(String fileName) {
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
                    //System.out.println(l.toString());
                    this.addConta(c);
                }
                case "TransportadoraPremium" -> {
                    TransportadoraPremium tp = parseTransportadoraPremium(separatedLines[1]);
                    //System.out.println(v.toString());
                    this.addTransportadora(tp);
                }
                case "Transportadora" -> {
                    Transportadora tr = parseTransportadora(separatedLines[1]);
                    //System.out.println(t.toString());
                    this.addTransportadora(tr);
                }
                case "Sapatilha" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Sapatilha s = parseSapatilha(aux[1]);
                    this.addSapatilha(s, info[0], info[1]);
                }
                case "SapatilhaPremium" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    SapatilhaPremium sp = parseSapatilhaPremium(aux[1]);
                    this.addSapatilha(sp, info[0], info[1]);
                }
                case "Tshirt" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Tshirt t = parseTshirt(aux[1]);
                    this.addTshirt(t, info[0], info[1]);
                }
                case "Mala" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    Mala m = parseMala(aux[1]);
                    this.addMala(m, info[0], info[1]);
                }
                case "MalaPremium" -> {
                    aux = separatedLines[1].split("=", 2);
                    info = aux[0].split(",", 2);
                    MalaPremium mp = parseMalaPremium(aux[1]);
                    this.addMala(mp, info[0], info[1]);
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

    public Transportadora parseTransportadora(String input) {
        String[] info = input.split(",");
        String nome = info[0];
        double lucro = Double.parseDouble(info[1]);
        return new Transportadora(nome,lucro);
    }

    public TransportadoraPremium parseTransportadoraPremium(String input) {
        String[] info = input.split(",");
        String nome = info[0];
        double lucro = Double.parseDouble(info[1]);
        return new TransportadoraPremium(nome,lucro);
    }

    public Sapatilha parseSapatilha(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.getInteger(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        double tamanho = Double.parseDouble(info[8]);
        boolean temAtacadores = Boolean.parseBoolean(info[9]);
        String cor = info[10];
        String[] aux = info[11].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.getInteger(aux[0]),Integer.getInteger(aux[2]),Integer.getInteger(aux[3]));
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
        int numeroDonos = Integer.getInteger(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        double tamanho = Double.parseDouble(info[8]);
        boolean temAtacadores = Boolean.parseBoolean(info[9]);
        String cor = info[10];
        String[] aux = info[11].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.getInteger(aux[0]),Integer.getInteger(aux[2]),Integer.getInteger(aux[3]));
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
        int numeroDonos = Integer.getInteger(info[5]);
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
        int numeroDonos = Integer.getInteger(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        int comprimento = Integer.getInteger(info[8]);
        int largura = Integer.getInteger(info[9]);
        int altura = Integer.getInteger(info[10]);
        String material = info[11];
        int data = Integer.getInteger(info[12]);

        return new Mala(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,comprimento, largura,altura,material,data);
    }

    public MalaPremium parseMalaPremium(String input) {
        String[] info = input.split(",");
        String descricao = info[0];
        String marca = info[1];
        String codigo = info[2];
        double precoBase = Double.parseDouble(info[3]);
        char estado = info[4].charAt(0);
        int numeroDonos = Integer.getInteger(info[5]);
        String transportadora = info[6];
        String codVendedor = info[7];
        int comprimento = Integer.getInteger(info[8]);
        int largura = Integer.getInteger(info[9]);
        int altura = Integer.getInteger(info[10]);
        String material = info[11];
        int data = Integer.getInteger(info[12]);

        return new MalaPremium(descricao,marca,codigo,precoBase,estado,numeroDonos,transportadora,codVendedor,comprimento, largura,altura,material,data);
    }

    public Encomenda parseEncomenda(String input) {

        String[] enc = input.split("@");
        String[] artigos = enc[0].split("]");

        List<Artigo> artigosList = new ArrayList<>();

        for (String line : artigos) {
            String[] separatedLines = line.split("'", 2);

            switch (separatedLines[0]) {
                case "Sapatilha" -> {
                    Sapatilha s = parseSapatilha(separatedLines[1]);
                    artigosList.add(s);
                }
                case "SapatilhaPremium" -> {
                    SapatilhaPremium sp = parseSapatilhaPremium(separatedLines[1]);
                    artigosList.add(sp);
                }
                case "Tshirt" -> {
                    Tshirt t = parseTshirt(separatedLines[1]);
                    artigosList.add(t);
                }
                case "Mala" -> {
                    Mala m = parseMala(separatedLines[1]);
                    artigosList.add(m);
                }
                case "MalaPremium" -> {
                    MalaPremium mp = parseMalaPremium(separatedLines[1]);
                    artigosList.add(mp);
                }
                default -> System.out.println("Linha invalida.");
            }
        }

        String[] infoFinal = enc[1].split(",");
        int dimensao = Integer.getInteger(infoFinal[0]);
        char estado = infoFinal[1].charAt(0);
        String[] auxData = infoFinal[2].split("/");
        LocalDate dataFinal = LocalDate.of(Integer.getInteger(auxData[0]),Integer.getInteger(auxData[2]),Integer.getInteger(auxData[3]));
        String codComprador = infoFinal[3];

        return new Encomenda(artigosList,dimensao,estado,dataFinal,codComprador);
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

}
