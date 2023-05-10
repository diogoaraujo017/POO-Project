import java.time.LocalDate;
import java.util.*;
import java.lang.Thread;


public class Menu{

    private LocalDate data;
    private Conta conta;


    public void inicio (Vintage vin){
        abreMenuInicial(vin);
    }

    public void abreMenuInicial(Vintage vin){


        // if(!getData().equals(LocalDate.now())){
        //     setData(getData());
        // }
        // else{
        //     setData(LocalDate.now());
        // }
        System.out.println("\nMenu Inicial\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n\n\n\n\n\n0-Sair\n");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuLogin(vin);
            }
            case "2" -> {
                clearTerminal();
                abreMenuRegister(vin);
            }
            case "3" -> {
                clearTerminal();
                abreMenuData(vin);
            }
            case "4" -> {
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "0" -> System.exit(0);
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuInicial(vin);
            }
        }
        input.close();
    }

    public void abreMenuLogin(Vintage vin){
        System.out.println("Menu de Login\n");
        System.out.print("Email de Utilizador:");
        Scanner mail = new Scanner(System.in);
        String email = mail.nextLine();
        if(!verificaEmail(email)){
            System.out.println("O email que escreveu não segue o formato correto, por favor tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                mail.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuLogin(vin);
        }
        System.out.print("Insira a palavra passe:");
        Scanner password = new Scanner(System.in);
        String pass= password.nextLine();
        if(email.equals("admin") && pass.equals("1234")){
            clearTerminal();
            abreMenuVisaoAdmin(vin);
        }
        if(vin.loginCorreto(email,pass)){
            Conta conta = vin.getContaByEmail(email);
            Conta conta_atual = new Conta(conta.getCodigo(),email,pass);
            setConta(conta_atual);
            clearTerminal();
            abreMenuIntermedio(vin);
        }
        else{
            System.out.println("Dados de Login incorretos, tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                mail.close();
                password.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuLogin(vin);
        }
        mail.close();
        password.close();
    }
    public void abreMenuRegister(Vintage vin){
        System.out.println("Menu de Registo\n");
        System.out.print("Nome de Utilizador:");
        Scanner s1 = new Scanner(System.in);
        String nome = s1.nextLine();
        System.out.print("Email:");
        Scanner s2 = new Scanner(System.in);
        String email = s2.nextLine();
        System.out.print("Password:");
        Scanner s3 = new Scanner(System.in);
        String pass = s3.nextLine();
        System.out.print("Morada:");
        Scanner s4 = new Scanner(System.in);
        String morada = s4.nextLine();
        System.out.print("NIF:");
        Scanner s5 = new Scanner(System.in);
        String nif = s5.nextLine();
        List<Artigo> vazio= new ArrayList<>();
        List<Fatura> vazia= new ArrayList<>();
        String code= geraCodigo(8);
        new Utilizador(code,nome,email,morada,nif,vazio,vazio,vazio,vazia);
        Conta nova = new Conta(code,email,pass);
        vin.addConta(nova);
        clearTerminal();
        abreMenuIntermedio(vin);
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
    }
    public void abreMenuData(Vintage vin){
        System.out.println("Menu de Data");
        System.out.print("Dia:");
        Scanner s1 = new Scanner(System.in);
        int diaa = 0;
        boolean check = false;

        while (!check) {
            String dia = s1.nextLine();
            try {
                diaa = Integer.parseInt(dia);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Mês:");
        Scanner s2 = new Scanner(System.in);
        int mess = 0;
        boolean check1 = false;

        while (!check1) {
            String mes = s2.nextLine();
            try {
                mess = Integer.parseInt(mes);
                check1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Ano:");
        Scanner s3 = new Scanner(System.in);
        int anoo = 0;
        boolean check2 = false;

        while (!check2) {
            String ano = s1.nextLine();
            try {
                anoo = Integer.parseInt(ano);
                check2 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        LocalDate mudada=LocalDate.of(anoo,mess,diaa);
        if(mudada.isAfter(getData())) {
            setData(mudada);
            System.out.println("Data alterada com sucesso");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                throw new RuntimeException(e);

            }
            clearTerminal();
            abreMenuInicial(vin);
        }
        else{
            System.out.println("A sua data é anterior à data atual do sistema, por favor tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuData(vin);
        }
        s1.close();
        s2.close();
        s3.close();
    }
    public void abreMenuQueries(Vintage vin){
        System.out.println("Coming soon");
        abreMenuInicial(vin);
    }
    public void abreMenuIntermedio(Vintage vin){
        System.out.println("Deseja:\n1-Comprar\n2-Vender\n3-Devolver Artigo\n9-Menu Inicial\n0-Sair");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuCompras(vin);
            }
            case "2" -> {
                clearTerminal();
                abreMenuVendas(vin);
            }
            case "3" -> {
                clearTerminal();
                abreMenuDevolucao(vin);
            }
            case "0" -> System.exit(0);
            case "9" -> {
                clearTerminal();
                abreMenuInicial(vin);
            }
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuIntermedio(vin);
            }
        }
        s1.close();
    }
    public void abreMenuCompras(Vintage vin){
        System.out.println("Menu de Compras");
        List<Artigo> artvenda = vin.getListaArtigos();
        int contador = 1;
        for(Artigo art : artvenda){
            if(!(art.getVendedor()).equals(vin.getUtilizadorByEmail(conta.getEmail()).getCodigo())){
                System.out.print(contador + " -  ");
                System.out.println(art.toString());
                System.out.println();
                contador++;
            }
        }
        int num=0;
        Scanner scanner = new Scanner(System.in);
        String linha = scanner.nextLine();
        String[] partes = linha.split(",");
        boolean cont=false;
        for(int i=0; i<partes.length;i++){
            try {
                num = Integer.parseInt(partes[i])-1;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + partes[i] + " não pode ser convertido para um inteiro. Tente novamente.");
                abreMenuIntermedio(vin);
            }
            if(num>=0 && num <=(contador-2)){
                String codigo = artvenda.get(num).getCodigo();
                if(vin.existeArtigo(codigo)){
                    cont=true;
                    Artigo art = vin.getArtigo(codigo);
                    Fatura fatura = new Fatura();
                    fatura.emiteFatura(art,vin.getUtilizadorByCodigo(art.getVendedor()),vin.getUtilizadorByEmail(conta.getEmail()));
                    vin.removeArtigo(art);
                    System.out.println(vin.getUtilizadorByCodigo(art.getVendedor()));
                }
            }
            else{
                System.out.println("Erro: "+partes[i]+ " não existe");
            }
        }
        if(cont) System.out.println("Compra efetuada com sucesso");
        try {
            Thread.sleep(3000);
            clearTerminal();
        } catch (InterruptedException e) {
            scanner.close();
            throw new RuntimeException(e);
        }
        abreMenuIntermedio(vin);
        scanner.close();

    
    }
    public void abreMenuVendas(Vintage vin){
        System.out.println("Menu de Vendas");
        System.out.println("Dejesa vender:\n1-Sapatilhas\n2-Malas\n3-TShirts\n\nSe quiser voltar atrás, prima 0");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuVendaSapatilhas(vin);
            }
            case "2" -> {
                clearTerminal();
                abreMenuVendasMalas(vin);
            }
            case "3" -> {
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
            case "0" -> {
                clearTerminal();
                abreMenuIntermedio(vin);
            }
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendas(vin);
            }
        }
        s1.close();
    }
    public void abreMenuVendasTShirts(Vintage vin){
        System.out.println("Menu de venda de TShirts\n\n");
        System.out.println("Descreva a sua tshirt");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca da TShirt?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar a sua TShirt?");
        Scanner s3 = new Scanner(System.in);
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = s3.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua TShirt?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        Scanner s4 = new Scanner(System.in);
        String est = s4.nextLine();
        char estado='o';
        switch (est) {
            case "a" -> estado = 'a';
            case "b" -> estado = 'b';
            case "c" -> estado = 'c';
            case "n" -> estado = 'n';
            default -> {
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("Quantos donos já teve a TShirt?");
        Scanner s5 = new Scanner(System.in);
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = s5.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio da TShirt?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        switch (trans) {
            case "1" -> trans = "CTT";
            case "2" -> trans = "USPS";
            case "3" -> trans = "UPS";
            case "4" -> trans = "DHL";
            case "5" -> trans = "FedEx";
            default -> {
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    s5.close();
                    s6.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("Qual o tamanho da sua TShirt?\ns-S\nm-M\nl-L\nx-XL");
        Scanner s7 = new Scanner(System.in);
        String tam = s7.nextLine();
        char tamanho = 'o';
        switch (tam) {
            case "s" -> tamanho = 's';
            case "m" -> tamanho = 'm';
            case "l" -> tamanho = 'l';
            case "x" -> tamanho = 'x';
            default -> {
                System.out.println("O tamanho não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    s5.close();
                    s6.close();
                    s7.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("Qual o padrão da sua TShirt?\np-Palmeiras\nr-Riscas\nl-Liso");
        Scanner s8 = new Scanner(System.in);
        String pad = s8.nextLine();
        char padrao='o';
        switch (pad) {
            case "p" -> padrao = 'p';
            case "r" -> padrao = 'r';
            case "l" -> padrao = 'l';
            default -> {
                System.out.println("O padrão não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    s5.close();
                    s6.close();
                    s7.close();
                    s8.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("TShirt registada, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            s1.close();
            s2.close();
            s3.close();
            s4.close();
            s5.close();
            s6.close();
            s7.close();
            s8.close();
            throw new RuntimeException(e);
        }
        String codigoTShirt=geraCodigo(10);
        Tshirt t = new Tshirt(desc,marca,codigoTShirt,preco,estado,donos,trans,tamanho,padrao,conta.getCodigo());
        String codigo = conta.getCodigo();
        Utilizador atual = vin.getUtilizadorByCodigo(codigo);
        vin.addArtigo(t);
        atual.getProdutosLoja().add(t);
        clearTerminal();
        abreMenuIntermedio(vin);
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
        s6.close();
        s7.close();
        s8.close();
    }
    public void abreMenuVendasMalas(Vintage vin){
        System.out.println("Menu de venda de Malas\n\n");
        System.out.println("Descreva a sua mala");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca da mala");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar a sua mala?");
        Scanner s3 = new Scanner(System.in);
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = s3.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua Mala?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        Scanner s4 = new Scanner(System.in);
        String est = s4.nextLine();
        char estado='o';
        switch (est) {
            case "a" -> estado = 'a';
            case "b" -> estado = 'b';
            case "c" -> estado = 'c';
            case "n" -> estado = 'n';
            default -> {
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasMalas(vin);
            }
        }
        System.out.println("Quantos donos já teve a Mala?");
        Scanner s5 = new Scanner(System.in);
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = s5.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio da Mala?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        switch (trans) {
            case "1" -> trans = "CTT";
            case "2" -> trans = "USPS";
            case "3" -> trans = "UPS";
            case "4" -> trans = "DHL";
            case "5" -> trans = "FedEx";
            default -> {
                System.out.println("A transportadora não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    s5.close();
                    s6.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasMalas(vin);
            }
        }
        System.out.println("Qual o comprimento da sua Mala?");
        Scanner s7 = new Scanner(System.in);
        int comprimento = 0;
        boolean check1 = false;

        while (!check1) {
            String comp = s7.nextLine();
            try {
                comprimento = Integer.parseInt(comp);
                check1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + comp + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual a largura da sua Mala?");
        Scanner s8 = new Scanner(System.in);
        int largura = 0;
        boolean check2 = false;

        while (!check2) {
            String larg = s8.nextLine();
            try {
                largura = Integer.parseInt(larg);
                check2 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + larg + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual a altura da sua Mala?");
        Scanner s9 = new Scanner(System.in);
        int altura = 0;
        boolean check3 = false;

        while (!check3) {
            String alt = s9.nextLine();
            try {
                altura = Integer.parseInt(alt);
                check3 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + alt + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual o material da sua Mala?");
        Scanner s10 = new Scanner(System.in);
        String material = s10.nextLine();
        System.out.println("Qual o ano de lançamento da sua Mala?");
        Scanner s11 = new Scanner(System.in);
        int ano = 0;
        boolean check4 = false;

        while (!check4) {
            String anoo = s5.nextLine();
            try {
                ano = Integer.parseInt(anoo);
                check4 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + anoo + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("A sua mala é Premium?\n1-Sim\n2-Não");
        Scanner s12 = new Scanner(System.in);
        String prem = s12.nextLine();
        boolean premium=false;
        if(prem.equals("1")){
            premium=true;
        }
        else if(prem.equals("2")){
            premium=false;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                s4.close();
                s5.close();
                s6.close();
                s7.close();
                s8.close();
                s9.close();
                s10.close();
                s11.close();
                s12.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasMalas(vin);
        }
        if(!premium && trans.equals("USPS")){
            System.out.println("A transportadora que selecionou apenas transporta artigos premium e o seu artigo não o é, por favor selecione outra transportadora.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                s4.close();
                s5.close();
                s6.close();
                s7.close();
                s8.close();
                s9.close();
                s10.close();
                s11.close();
                s12.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasMalas(vin);
        }
        System.out.println("Mala registada, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            s1.close();
            s2.close();
            s3.close();
            s4.close();
            s5.close();
            s6.close();
            s7.close();
            s8.close();
            s9.close();
            s10.close();
            s11.close();
            s12.close();
            throw new RuntimeException(e);
        }
        String codigoMala=geraCodigo(11);
        if(premium) {
            MalaPremium t = new MalaPremium(desc, marca, codigoMala, preco, estado, donos, trans, comprimento, largura, altura, material, ano, conta.getCodigo());
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        else{
            Mala t = new Mala(desc, marca, codigoMala, preco, estado, donos, trans, comprimento, largura, altura, material, ano, conta.getCodigo());
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        clearTerminal();
        abreMenuIntermedio(vin);
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
        s6.close();
        s7.close();
        s8.close();
        s9.close();
        s10.close();
        s11.close();
        s12.close();
    }
    public void abreMenuVendaSapatilhas(Vintage vin){
        System.out.println("Menu de venda de Sapatilhas\n\n");
        System.out.println("Descreva as suas Sapatilhas");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca das Sapatilhas?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar as suas Sapatilhas?");
        Scanner s3 = new Scanner(System.in);
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = s3.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado correspondem as suas Sapatilhas?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        Scanner s4 = new Scanner(System.in);
        String est = s4.nextLine();
        char estado='o';
        switch (est) {
            case "a" -> estado = 'a';
            case "b" -> estado = 'b';
            case "c" -> estado = 'c';
            case "n" -> estado = 'n';
            default -> {
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }


        System.out.println("Quantos donos já tiveram as Sapatilhas?");
        Scanner s5 = new Scanner(System.in);
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = s5.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        switch (trans) {
            case "1" -> trans = "CTT";
            case "2" -> trans = "USPS";
            case "3" -> trans = "UPS";
            case "4" -> trans = "DHL";
            case "5" -> trans = "FedEx";
            default -> {
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    s1.close();
                    s2.close();
                    s3.close();
                    s4.close();
                    s5.close();
                    s6.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }
        System.out.println("Qual o número (tamanho) das sapatilhas?");
        Scanner s7 = new Scanner(System.in);
        double tamanho = 0.0;
        boolean tamValido = false;

        while (!tamValido) {
            String tam = s7.nextLine();
            try {
                tamanho = Double.parseDouble(tam);
                tamValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + tam + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A suas sapatilhas têm atacadores?\n1-Sim\n2-Não");
        Scanner s8 = new Scanner(System.in);
        String ata = s8.nextLine();
        boolean atacadores=false;
        if(ata.equals("1")){
            atacadores=true;
        }
        else if(ata.equals("2")){
            atacadores=false;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                s4.close();
                s5.close();
                s6.close();
                s7.close();
                s8.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas(vin);
        }

        System.out.println("Qual a cor das suas sapatilhas?");
        Scanner s9 = new Scanner(System.in);
        String cor = s9.nextLine();
        System.out.println("Qual a data de lançamento das suas sapatilhas? (Por favor, coloque a data em números)");
        System.out.print("Dia:");
        Scanner s10 = new Scanner(System.in);
        int diaa = 0;
        boolean check1 = false;

        while (!check1) {
            String dia = s10.nextLine();
            try {
                diaa = Integer.parseInt(dia);
                check1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Mês:");
        Scanner s11 = new Scanner(System.in);
        int mess = 0;
        boolean check2 = false;

        while (!check2) {
            String mes = s11.nextLine();
            try {
                mess = Integer.parseInt(mes);
                check2 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Ano:");
        Scanner s12 = new Scanner(System.in);
        int anoo = 0;
        boolean check3 = false;

        while (!check3) {
            String ano = s12.nextLine();
            try {
                anoo = Integer.parseInt(ano);
                check3 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        LocalDate lanc=LocalDate.of(anoo,mess,diaa);
        System.out.println("As suas sapatilhas são Premium?\n1-Sim\n2-Não");
        Scanner s13 = new Scanner(System.in);
        String prem = s13.nextLine();
        boolean premium=false;
        if(prem.equals("1")){
            premium=true;
        }
        else if(prem.equals("2")){
            premium=false;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                s4.close();
                s5.close();
                s6.close();
                s7.close();
                s8.close();
                s9.close();
                s10.close();
                s11.close();
                s12.close();
                s13.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas(vin);

        }
        System.out.println("Sapatilhas registadas, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            s1.close();
            s2.close();
            s3.close();
            s4.close();
            s5.close();
            s6.close();
            s7.close();
            s8.close();
            s9.close();
            s10.close();
            s11.close();
            s12.close();
            s13.close();
            throw new RuntimeException(e);
        }
        String codigoSapatilhas=geraCodigo(9);
        if(premium){
            SapatilhaPremium t = new SapatilhaPremium(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo());
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        else {
            Sapatilha t = new Sapatilha(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo());
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        clearTerminal();
        abreMenuIntermedio(vin);
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
        s6.close();
        s7.close();
        s8.close();
        s9.close();
        s10.close();
        s11.close();
        s12.close();
        s13.close();
    }

    public void abreMenuVisaoAdmin(Vintage vin){
        System.out.println("Menu de Administração\n\n");
        System.out.println("Selecione o menu que deseja aceder\n1-Criar Transportadora\n2-Gerir Transportadora\n\n\n\n\n\n\n\n0-Voltar ao Menu Inicial");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        if(entrada.equals("1")){
            clearTerminal();
            abreMenuCreateTrans(vin);
        } else if(entrada.equals("2")){
            clearTerminal();
            abreMenuGerirTrans(vin);
        }
        else if(entrada.equals("0")){
            clearTerminal();
            abreMenuInicial(vin);
        }
        else{
            System.out.println("======O que digitou não corresponde a nenhuma opção, tente novamente======");
            abreMenuVisaoAdmin(vin);
        }
        input.close();
    }

    public void abreMenuCreateTrans(Vintage vin){
        System.out.println("Menu de criação de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a adicionar?");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a margem de lucro da transportadora a adicionar?");
        Scanner s2 = new Scanner(System.in);
        double lucro = 0.0;
        boolean lucroValido = false;

        while (!lucroValido){
            String pre = s2.nextLine();
            try {
                lucro = Double.parseDouble(pre);
                lucroValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A transportadora é premium? (Sim ou Não)");
        Scanner s3 = new Scanner(System.in);
        String premium = s3.nextLine();
        boolean prem= premium.equals("Sim");
        if(prem){
            List<Artigo> vazia = new ArrayList<>();
            TransportadoraPremium t = new TransportadoraPremium(desc,lucro,prem,vazia);
            vin.addTransportadora(t);
            System.out.println("Transportadora " + t.getNome() + " registada com sucesso.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVisaoAdmin(vin);
            s1.close();
            s2.close();
            s3.close();
        }
        else{
            List<Artigo> vazia = new ArrayList<>();
            Transportadora t = new Transportadora(desc,lucro,prem,vazia);
            vin.addTransportadora(t);
            System.out.println("Transportadora " + t.getNome() + " registada com sucesso.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                s1.close();
                s2.close();
                s3.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVisaoAdmin(vin);
            s1.close();
            s2.close();
            s3.close();
        }
    }

    public void abreMenuGerirTrans(Vintage vin){
        System.out.println("Menu de gestão de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a modificar?");
        Scanner s1 = new Scanner(System.in);
        String nome = s1.nextLine();
        Transportadora mudar = vin.getTransportdoraByName(nome);
        if(mudar==null){
            System.out.println("O nome que digitou não corresponde a nenhuma das transportadoras existentes no sistema, eis a lista de tranportadoras existentes:");
            List<Transportadora> nova = vin.getTransportadoras();
            for(Transportadora t : nova){
                System.out.println(t);
            }
            System.out.println("\n\n=======//=======\n");
            abreMenuGerirTrans(vin);
            s1.close();
        }
        System.out.println("O que deseja alterar?\n1-Nome\n2-Margem de Lucro\n\n\n0-Voltar para o Menu de Visão de Administrador");
        Scanner s2 = new Scanner(System.in);
        String opc = s2.nextLine();
        if(opc.equals("1")){
            System.out.println("Para que nome deseja alterar a transportadora?");
            Scanner s3 = new Scanner(System.in);
            String novo = s3.nextLine();
            mudar.setNome(novo);
            System.out.println("Nome alterada para " + novo);
            abreMenuVisaoAdmin(vin);
            s1.close();
            s2.close();
            s3.close();
        }
        else if(opc.equals("2")){
            System.out.println("Para que taxa de lucro deseja alterar a transportadora");
            Scanner s4 = new Scanner(System.in);
            double preco = 0.0;
            boolean precoValido = false;

            while (!precoValido) {
                String pre = s4.nextLine();
                try {
                    preco = Double.parseDouble(pre);
                    precoValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
                }
            }
            mudar.setLucro(preco);
            System.out.println("Taxa de lucro alterada para " + preco);
            abreMenuVisaoAdmin(vin);
            s1.close();
            s2.close();
            s4.close();
        }
        else if(opc.equals("0")){
            clearTerminal();
            abreMenuVisaoAdmin(vin);
        }
        else{
            System.out.println("======O que digitou não corresponde a nenhuma opção, tente novamente======");
            abreMenuGerirTrans(vin);
        }
    }

    public void abreMenuDevolucao(Vintage vin){
        LocalDate agora= getData();
        agora.plusDays(2);
        Conta atual=getConta();
        System.out.println();
        Vintage loja=vin;
        Utilizador novo = loja.getUtilizadorByEmail(atual.getEmail());
        System.out.println(novo.toString());
    }

    // public Menu(Menu me){
    //     this.shop=me.shop;
    //     this.data=me.data;
    //     this.conta=me.conta;
    // }

    // public Menu(){
    //     this.shop=null;
    //     this.data=LocalDate.now();
    //     this.conta=null;
    // }

    // public Menu(Vintage shop, LocalDate dat, Conta cont) {
    //     this.shop = shop;
    //     this.data = dat;
    //     this.conta= cont;
    // }

    // public Vintage getShop() {
    //     return this.shop;
    // }

    // public void setShop(Vintage shop) {
    //     this.shop = shop;
    // }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate dat) {
        data = dat;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta user) {
        this.conta = user;
    }


    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Menu menu = (Menu) o;
    //     return Objects.equals(getShop(), menu.getShop());
    // }

    public void clearTerminal() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    public String geraCodigo(int codigoLength){
        String codigo;
        String charSet = "qwertyuiopasdfghjklzxcvbnm0123456789";
        Random rand = new Random();
        codigo="";
        for (int j = 0; j < codigoLength; j++) {
            codigo += charSet.charAt(rand.nextInt(charSet.length())); // adiciona um caractere aleatório da string charSet
        }
        return codigo;
    }

    public boolean verificaEmail(String email){
        if (email == null) {
            return false;
        }
        if (email.equals("admin")){
            return true;
        }
        boolean arrobaEncontrado = false;
        boolean pontoEncontrado = false;
        for (int i = 0; i < email.length(); i++) {
            char comp = email.charAt(i);
            if(comp=='@'){
                arrobaEncontrado=true;
            }
            if(comp=='.'){
                if(arrobaEncontrado){
                    pontoEncontrado=true;
                }
            }
        }
        return (arrobaEncontrado && pontoEncontrado);
    }
}