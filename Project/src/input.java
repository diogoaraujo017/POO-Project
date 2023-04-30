import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static void recebe(String[] args) throws FileNotFoundException{
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int i;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] partes = linha.split(",");
            for(i=0;i<partes.length;i++){
                String tipo = partes[i];
                switch (tipo) {
                    case "Sapatilhas" -> {
                        Sapatilhas input = meteSapatilhas(linha, i);
                        i += 9;
                    }
                    case "Mala" -> {
                        Malas input = meteMala(linha, i);
                        i += 12;
                    }
                    case "Tshirt" -> {
                        Tshirts input = meteShirt(linha, i);
                        i += 12;
                    }
                    case "Utilizador" -> {
                        Utilizadores input = meteUtilizador(linha, i);
                    }
                    case "Transportadora" -> {
                        Transportadora transportadorainput = meteTransportadora(linha, i);
                    }
                    case "Encomenda" -> {
                        Encomenda encomendainput = meteEncomenda(linha, i);
                    }
                }
            }
        }
    }

    public static Sapatilhas meteSapatilhas(String linha, int i){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora, cor;
        double preco_base, n_tamanho;
        boolean tem_atacadores=false, e_premium=false;
        int n_donos;
        char estado;
        LocalDate data_lancamento;
        descricao=partes[i+1];
        marca=partes[i+2];
        codigo=Menu.geraCodigo(9);
        preco_base=Double.parseDouble(partes[i+3]);
        estado='e';
        estado = switch (partes[i + 4]) {
            case "a" -> 'a';
            case "b" -> 'b';
            case "c" -> 'c';
            case "n" -> 'n';
            default -> estado;
        };
        n_donos = Integer.parseInt(partes[i+5]);
        transportadora=partes[i+6];
        n_tamanho=Double.parseDouble(partes[i+7]);
        if(partes[i+8].equals("Sim")) tem_atacadores = true;
        else if(partes[i+8].equals("Não")) tem_atacadores = false;
        cor=partes[i+9];
        String[] data = partes[i+10].split("/");
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[2]);
        data_lancamento=LocalDate.of(dia,mes,ano);
        if(partes[i+11].equals("Sim")) e_premium = true;
        else if(partes[i+11].equals("Não")) e_premium = false;
        Sapatilhas sapatilhainput= new Sapatilhas(descricao,marca, codigo, preco_base, estado, n_donos, transportadora, n_tamanho, tem_atacadores, cor, data_lancamento, e_premium);
        return sapatilhainput;
    }

    public static Malas meteMala(String linha, int i){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora, material;
        double preco_base;
        boolean e_premium=false;
        int n_donos,comprimento, altura, largura, ano_lancamento;
        char estado;
        descricao=partes[i+1];
        marca=partes[i+2];
        codigo=Menu.geraCodigo(11);
        preco_base=Double.parseDouble(partes[i+3]);
        estado='e';
        estado = switch (partes[i + 4]) {
            case "a" -> 'a';
            case "b" -> 'b';
            case "c" -> 'c';
            case "n" -> 'n';
            default -> estado;
        };
        n_donos = Integer.parseInt(partes[i+5]);
        transportadora=partes[i+6];
        comprimento = Integer.parseInt(partes[i+7]);
        largura = Integer.parseInt(partes[i+8]);
        altura = Integer.parseInt(partes[i+9]);
        material = partes[i+10];
        ano_lancamento = Integer.parseInt(partes[i+11]);
        if(partes[i+12].equals("Sim")) e_premium = true;
        else if(partes[i+12].equals("Não")) e_premium = false;
        Malas malainput = new Malas(descricao, marca,codigo,preco_base,estado,n_donos,transportadora,comprimento,largura,altura,material,ano_lancamento,e_premium);
        return malainput;
    }

    public static Tshirts meteShirt(String linha, int i){
        String[] partes = linha.split(",");
        String descricao, codigo, marca, transportadora;
        double preco_base;
        int n_donos;
        char estado, tamanho, padrao;
        descricao=partes[i+1];
        marca=partes[i+2];
        codigo=Menu.geraCodigo(10);
        preco_base=Double.parseDouble(partes[i+3]);
        estado='e';
        estado = switch (partes[i + 4]) {
            case "a" -> 'a';
            case "b" -> 'b';
            case "c" -> 'c';
            case "n" -> 'n';
            default -> estado;
        };
        n_donos = Integer.parseInt(partes[i+5]);
        transportadora=partes[i+6];
        tamanho='o';
        switch (partes[i + 7]) {
            case "s" -> tamanho = 's';
            case "m" -> tamanho = 'm';
            case "l" -> tamanho = 'l';
            case "xl" -> estado = 'x';
        }
        padrao='o';
        tamanho = switch (partes[i + 8]) {
            case "p" -> 'p';
            case "r" -> 'r';
            case "l" -> 'l';
            default -> tamanho;
        };
        Tshirts tshirtinput = new Tshirts(descricao,marca,codigo,preco_base,estado,n_donos,transportadora,tamanho,padrao);
        return tshirtinput;
    }

    public static Transportadora meteTransportadora(String linha, int i){
        String[] partes = linha.split(",");
        String nome;
        Transportadora transport;
        boolean premium;
        premium = false;
        double lucro;
        List<Encomenda> encomend= new ArrayList<Encomenda>();
        nome=partes[i+1];
        lucro=Double.parseDouble(partes[i+2]);
        if(partes[i+3].equals("Sim")) premium = true;
        else if(partes[i+3].equals("Não")) premium = false;
        int index;
        for(index=i+4;index<=partes.length;index+=4){
            encomend.add(meteEncomenda(linha,index));
        }
        transport=new Transportadora(nome,lucro,premium,encomend);
        return transport;
    }
    public static Utilizadores meteUtilizador(String linha, int i){
        Utilizadores user=null;
        String[] partes = linha.split(",");
        String codigo, email, nome, morada, nif;
        codigo=Menu.geraCodigo(8);
        email=partes[i+1];
        nome=partes[i+2];
        morada= partes[i+3];
        nif= partes[i+4];
        List<Artigo> vendido= new ArrayList<Artigo>();
        List<Artigo> avenda= new ArrayList<Artigo>();
        List<Artigo> comprado= new ArrayList<Artigo>();
        int index;
        for(index=i+5;index<partes.length;index++){
            if(partes[index].equals("Comprado")){
                if(partes[index+1].equals("Tshirt")){
                    Tshirts tinput=meteShirt(linha, index+1);
                    comprado.add(tinput);
                    index+=9;
                }
                if(partes[index+1].equals("Mala")){
                    Malas minput=meteMala(linha,index+1);
                    comprado.add(minput);
                    index+=12;

                }
                if(partes[index+1].equals("Sapatilhas")){
                    Sapatilhas sinput=meteSapatilhas(linha,index+1);
                    comprado.add(sinput);
                    index+=12;
                }
            }
            if(partes[index].equals("À Venda")){
                if(partes[index+1].equals("Tshirt")){
                    Tshirts tinput=meteShirt(linha, index+1);
                    avenda.add(tinput);
                    index+=9;
                }
                if(partes[index+1].equals("Mala")){
                    Malas minput=meteMala(linha,index+1);
                    avenda.add(minput);
                    index+=12;

                }
                if(partes[index+1].equals("Sapatilhas")){
                    Sapatilhas sinput=meteSapatilhas(linha,index+1);
                    avenda.add(sinput);
                    index+=12;
                }
            }
        }
        user=new Utilizadores(codigo,nome,email,morada,nif,vendido,avenda,comprado);
        return user;
    }
    public static Encomenda meteEncomenda(String linha, int i){
        String[] partes = linha.split(",");
        int index;
        Encomenda encomendainput=null;
        List<Artigo> encomendado = new ArrayList<Artigo>();
        for(index=i;;index++) {
            if (partes[index + 1].equals("Tshirt")) {
                Tshirts tinput = meteShirt(linha, index + 1);
                encomendado.add(tinput);
                index += 9;
            }
            if (partes[index + 1].equals("Mala")) {
                Malas minput = meteMala(linha, index + 1);
                encomendado.add(minput);
                index += 12;

            }
            if (partes[index + 1].equals("Sapatilhas")) {
                Sapatilhas sinput = meteSapatilhas(linha, index + 1);
                encomendado.add(sinput);
                index += 12;
            }
            if(partes.equals("FIM")) break;
        }
        String dime=partes[index+1];
        int dimensao = Integer.parseInt(dime);
        String esta = partes[index+2];
        char estado='a';
        if(partes[index+2].equals("p")){
            estado = 'p';
        }
        else if(partes[index+2].equals("f")){
            estado = 'f';
        }
        else if(partes[index+2].equals("e")){
            estado = 'e';
        }
        else if(partes[index+2].equals("d")){
            estado = 'd';
        }
        String[] data = partes[index+3].split("/");
        int diaa = Integer.parseInt(data[0]);
        int mess = Integer.parseInt(data[1]);
        int anoo = Integer.parseInt(data[2]);
        LocalDate datac=LocalDate.of(anoo,mess,diaa);
        encomendainput=new Encomenda(encomendado,dimensao,estado,datac);
        return encomendainput;
    }
}
