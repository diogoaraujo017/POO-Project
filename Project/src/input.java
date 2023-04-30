import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class input {
    public static void recebe(String[] args) throws FileNotFoundException {
        List<Artigo> artigos = new ArrayList<>();
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] partes = linha.split(",");
            String tipo = partes[0];
            if(tipo.equals("Sapatilhas")){
                Sapatilhas input = meteSapatilhas(linha);
            }
            else if(tipo.equals("Mala")) {
                Malas input = meteMala(linha);
            }
            else if(tipo.equals("Tshirt")) {
                Tshirts input = meteShirt(linha);
            }
            else if(tipo.equals("Utilizador")) {
                 Utilizadores utilizadoresinput=meteUtilizador(linha);
            }
            else if(tipo.equals("Transportadora")) {
                Transportadora transportadorainput=meteTransportadora(linha);
            }
            else if(tipo.equals("Encomenda")) {
                Encomenda encomendainput=meteEncomenda(linha);
            }

        }
    }

    public static Sapatilhas meteSapatilhas(String linha){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora, cor;
        double preco_base, n_tamanho;
        boolean tem_atacadores=false, e_premium=false;
        int n_donos;
        char estado;
        LocalDate data_lancamento;
        descricao=partes[1];
        marca=partes[2];
        codigo=Menu.geraCodigo(8);
        preco_base=Double.parseDouble(partes[3]);
        estado='e';
        if(partes[4].equals("a")){
            estado = 'a';
        }
        else if(partes[4].equals("b")){
            estado = 'b';
        }
        else if(partes[4].equals("c")){
            estado = 'c';
        }
        else if(partes[4].equals("n")){
            estado = 'n';
        }
        n_donos = Integer.parseInt(partes[5]);
        transportadora=partes[6];
        n_tamanho=Double.parseDouble(partes[7]);
        if(partes[8].equals("Sim")){
            tem_atacadores=true;
        }
        else if(partes[8].equals("Não")){
            tem_atacadores=false;
        }
        cor=partes[9];
        String[] data = partes[10].split("/");
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[2]);
        data_lancamento=LocalDate.of(dia,mes,ano);
        if(partes[11].equals("Sim")){
            e_premium=true;
        }
        else if(partes[11].equals("Não")){
            e_premium=false;
        }
        Sapatilhas sapatilhainput= new Sapatilhas(descricao,marca, codigo, preco_base, estado, n_donos, transportadora, n_tamanho, tem_atacadores, cor, data_lancamento, e_premium);
        return sapatilhainput;
    }

    public static Malas meteMala(String linha){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora, material;
        double preco_base;
        boolean e_premium=false;
        int n_donos,comprimento, altura, largura, ano_lancamento;
        char estado;
        descricao=partes[1];
        marca=partes[2];
        codigo=Menu.geraCodigo(10);
        preco_base=Double.parseDouble(partes[3]);
        estado='e';
        if(partes[4].equals("a")){
            estado = 'a';
        }
        else if(partes[4].equals("b")){
            estado = 'b';
        }
        else if(partes[4].equals("c")){
            estado = 'c';
        }
        else if(partes[4].equals("n")){
            estado = 'n';
        }
        n_donos = Integer.parseInt(partes[5]);
        transportadora=partes[6];
        comprimento = Integer.parseInt(partes[7]);
        largura = Integer.parseInt(partes[8]);
        altura = Integer.parseInt(partes[9]);
        material = partes[10];
        ano_lancamento = Integer.parseInt(partes[11]);
        if(partes[12].equals("Sim")){
            e_premium=true;
        }
        else if(partes[12].equals("Não")){
            e_premium=false;
        }
        Malas malainput = new Malas(descricao, marca,codigo,preco_base,estado,n_donos,transportadora,comprimento,largura,altura,material,ano_lancamento,e_premium);
        return malainput;
    }

    public static Tshirts meteShirt(String linha){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora;
        double preco_base;
        int n_donos;
        char estado, tamanho, padrao;
        descricao=partes[1];
        marca=partes[2];
        codigo=Menu.geraCodigo(9);
        preco_base=Double.parseDouble(partes[3]);
        estado='e';
        if(partes[4].equals("a")){
            estado = 'a';
        }
        else if(partes[4].equals("b")){
            estado = 'b';
        }
        else if(partes[4].equals("c")){
            estado = 'c';
        }
        else if(partes[4].equals("n")){
            estado = 'n';
        }
        n_donos = Integer.parseInt(partes[5]);
        transportadora=partes[6];
        tamanho='o';
        if(partes[7].equals("s")){
            tamanho = 's';
        }
        else if(partes[7].equals("m")){
            tamanho = 'm';
        }
        else if(partes[7].equals("l")){
            tamanho = 'l';
        }
        else if(partes[7].equals("xl")){
            estado = 'x';
        }
        padrao='o';
        if(partes[8].equals("p")){
            tamanho = 'p';
        }
        else if(partes[8].equals("r")){
            tamanho = 'r';
        }
        else if(partes[8].equals("l")){
            tamanho = 'l';
        }
        Tshirts tshirtinput = new Tshirts(descricao,marca,codigo,preco_base,estado,n_donos,transportadora,tamanho,padrao);
        return tshirtinput;
    }

    public static Transportadora meteTransportadora(String linha){
        Transportadora transportadora=null;
        String[] partes = linha.split(",");
        String nome;
        boolean premium;
        double lucro;
        List<Encomenda> encomendas = new ArrayList<Encomenda>();
        nome=partes[1];
        lucro=Double.parseDouble(partes[2]);
        if(partes[3].equals("Sim")){
            premium=true;
        }
        else if(partes[3].equals("Não")){
            premium=false;
        }
        return transportadora;
    }
    public static Utilizadores meteUtilizador(String linha){
        Utilizadores user=null;
        return user;
    }
    public static Encomenda meteEncomenda(String linha){
        Encomenda encomendainput=null;
        return encomendainput;
    }

}
