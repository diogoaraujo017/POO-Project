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
        setData(LocalDate.now());
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
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();
        if(!verificaEmail(email)){
            System.out.println("O email que escreveu não segue o formato correto, por favor tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuLogin(vin);
        }
        System.out.print("Insira a palavra passe:");
        String pass= input.nextLine();
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
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuLogin(vin);
        }
        input.close();
    }
    public void abreMenuRegister(Vintage vin){
        System.out.println("Menu de Registo\n");
        System.out.print("Nome de Utilizador:");
        Scanner input = new Scanner(System.in);
        String nome = input.nextLine();
        System.out.print("Email:");
        String email = input.nextLine();
        while(!verificaEmail(email)){
            System.out.print("O email introduzido não é válido.\n\nPor favor introduza novamente:");
            email = input.nextLine();
        }
        System.out.print("Password:");
        String pass = input.nextLine();
        System.out.print("Morada:");
        String morada = input.nextLine();
        System.out.print("NIF:");
        String nif = input.nextLine();
        List<Artigo> vazio= new ArrayList<>();
        List<Fatura> vazia= new ArrayList<>();
        String code= geraCodigo(8);
        new Utilizador(code,nome,email,morada,nif,vazio,vazio,vazio,vazia);
        Conta nova = new Conta(code,email,pass);
        vin.addConta(nova);
        clearTerminal();
        abreMenuIntermedio(vin);
        input.close();
    }
    public void abreMenuData(Vintage vin){
        System.out.println("Menu de Data");
        System.out.print("Dia:");
        int diaa = 0;
        boolean check = false;
        Scanner input = new Scanner(System.in);

        while (!check) {
            String dia = input.nextLine();
            try {
                diaa = Integer.parseInt(dia);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Mês:");
        int mess = 0;
        boolean check1 = false;

        while (!check1) {
            String mes = input.nextLine();
            try {
                mess = Integer.parseInt(mes);
                check1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Ano:");
        int anoo = 0;
        boolean check2 = false;

        while (!check2) {
            String ano = input.nextLine();
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
                input.close();
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
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuData(vin);
        }
        input.close();
    }
    public void abreMenuQueries(Vintage vin){
        System.out.println("Coming soon");
        abreMenuInicial(vin);
    }
    public void abreMenuIntermedio(Vintage vin){
        System.out.println("Deseja:\n1-Comprar\n2-Vender\n3-Devolver Artigo\n9-Menu Inicial\n0-Sair");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuIntermedio(vin);
            }
        }
        input.close();
    }
    public void abreMenuCompras(Vintage vin){
        System.out.println("Menu de Compras");
        List<Artigo> artvenda = vin.getListaArtigos();
        List<Artigo> artigos_disponiveis = new ArrayList<>();
        List<Artigo> encomenda = new ArrayList<>();
        boolean flag = false;
        int contador = 1;
        for(Artigo art : artvenda){
            if(!((art.getVendedor()).equals(conta.getCodigo()))){
                artigos_disponiveis.add(art);
                System.out.print(contador + " -  ");
                System.out.println(art.toString());
                System.out.println();
                contador++;
            }
        }
        int num=0;
        System.out.print("-> Introduza o número dos produtos que pretende comprar: ");
        Scanner input = new Scanner(System.in);
        String linha = input.nextLine();
        String[] partes = linha.split(",");
        for(int i=0; i<partes.length;i++){
            try {
                num = Integer.parseInt(partes[i])-1;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + partes[i] + " não pode ser convertido para um inteiro. Tente novamente.");
                abreMenuIntermedio(vin);
            }
            if(num>=0 && num <=(contador-2)){
                String codigo = artigos_disponiveis.get(num).getCodigo();
                if(vin.existeArtigo(codigo)){
                    flag=true;
                    Artigo art = vin.getArtigo(codigo);
                    encomenda.add(art);
                    Fatura fatura = new Fatura();
                    fatura.emiteFatura(art,vin.getUtilizadorByCodigo(art.getVendedor()),vin.getUtilizadorByEmail(conta.getEmail()));
                    vin.removeArtigo(art);
                    System.out.println("Adicionado ao carrinho o produto: "+ art.getCodigo());
                }
            }
            else{
                System.out.println("Erro: "+partes[i]+ " não existe");
            }

        }

        if(flag){
            System.out.print("\n\n");
            Encomenda enc = new Encomenda(encomenda,encomenda.size(),'p',LocalDate.now(),conta.getCodigo());
            vin.addEncomenda(enc);
            System.out.println("Total da sua compra = "+ enc.valorFinalEncomenda(enc,vin) + "€");
            System.out.println("Confirmar a encomenda: Sim | Não");
            String conf = input.nextLine();
            if(conf.equals("Sim")){
                enc.setEstado('e');
            }
            else if(conf.equals("Não")){
                System.out.println("Encomenda cancelada");
            }
        }


        try {
            Thread.sleep(3000);
            clearTerminal();
        } catch (InterruptedException e) {
            input.close();
            throw new RuntimeException(e);
        }


        clearTerminal();
        abreMenuIntermedio(vin);
        input.close();

    
    }
    public void abreMenuVendas(Vintage vin){
        System.out.println("Menu de Vendas");
        System.out.println("Dejesa vender:\n1-Sapatilhas\n2-Malas\n3-TShirts\n\nSe quiser voltar atrás, prima 0");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendas(vin);
            }
        }
        input.close();
    }
    public void abreMenuVendasTShirts(Vintage vin){
        System.out.println("Menu de venda de TShirts\n\n");
        System.out.println("Descreva a sua tshirt");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca da TShirt?");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar a sua TShirt?");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua TShirt?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        String est = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("Quantos donos já teve a TShirt?");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?");
        List<Transportadora> tran = vin.getTransportadoras();
        int contador = 1;
        for(Transportadora t : tran){
            String nome = t.getNome();
            System.out.println(contador +"-"+ nome);
            contador++;
        }
        int v=1;
        boolean checkk1 = false;

        while (!checkk1) {
            String trans = input.nextLine();
            try {
                v = Integer.parseInt(trans);
                if(v>=1 && v<= tran.size()) {
                    checkk1 = true;
                }
                else{
                    System.out.println("Erro: " + trans + " não corresponde a nenhuma das transportadoras. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + trans + " não segue as normas de seleção de transportadora. Tente novamente.");
            }
        }
        Transportadora transp = tran.get(v-1);
        String trans = transp.getNome();

        System.out.println("Qual o tamanho da sua TShirt?\ns-S\nm-M\nl-L\nx-XL");
        String tam = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }

        System.out.println("Qual o padrão da sua TShirt?\np-Palmeiras\nr-Riscas\nl-Liso");
        String pad = input.nextLine();
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
                    input.close();
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
            input.close();
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
        input.close();
    }
    public void abreMenuVendasMalas(Vintage vin){
        System.out.println("Menu de venda de Malas\n\n");
        System.out.println("Descreva a sua mala");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca da mala");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar a sua mala?");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua Mala?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        String est = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasMalas(vin);
            }
        }
        System.out.println("Quantos donos já teve a Mala?");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?");
        List<Transportadora> tran = vin.getTransportadoras();
        int contador = 1;
        for(Transportadora t : tran){
            String nome = t.getNome();
            System.out.println(contador +"-"+ nome);
            contador++;
        }
        int v=1;
        boolean checkk1 = false;

        while (!checkk1) {
            String trans = input.nextLine();
            try {
                v = Integer.parseInt(trans);
                if(v>=1 && v<= tran.size()) {
                    checkk1 = true;
                }
                else{
                    System.out.println("Erro: " + trans + " não corresponde a nenhuma das transportadoras. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + trans + " não segue as normas de seleção de transportadora. Tente novamente.");
            }
        }
        Transportadora transp = tran.get(v-1);
        String trans = transp.getNome();

        System.out.println("Qual o comprimento da sua Mala?");
        int comprimento = 0;
        boolean check1 = false;

        while (!check1) {
            String comp = input.nextLine();
            try {
                comprimento = Integer.parseInt(comp);
                check1 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + comp + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual a largura da sua Mala?");
        int largura = 0;
        boolean check2 = false;

        while (!check2) {
            String larg = input.nextLine();
            try {
                largura = Integer.parseInt(larg);
                check2 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + larg + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual a altura da sua Mala?");
        int altura = 0;
        boolean check3 = false;

        while (!check3) {
            String alt = input.nextLine();
            try {
                altura = Integer.parseInt(alt);
                check3 = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + alt + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Qual o material da sua Mala?");
        String material = input.nextLine();
        System.out.println("Qual o ano de lançamento da sua Mala?");
        int ano = 0;
        boolean check4 = false;

        while (!check4) {
            String anoo = input.nextLine();
            try {
                ano = Integer.parseInt(anoo);
                if(ano>=1&&ano<=2023){
                    check4 = true;
                }
                else{
                    System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + anoo + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("A sua mala é Premium?\n1-Sim\n2-Não");
        String prem = input.nextLine();
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
                input.close();
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
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasMalas(vin);
        }
        System.out.println("Mala registada, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            input.close();
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
        input.close();
    }
    public void abreMenuVendaSapatilhas(Vintage vin){
        System.out.println("Menu de venda de Sapatilhas\n\n");
        System.out.println("Descreva as suas Sapatilhas");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca das Sapatilhas?");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar as suas Sapatilhas?");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado correspondem as suas Sapatilhas?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        String est = input.nextLine();
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
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts(vin);
            }
        }


        System.out.println("Quantos donos já tiveram as Sapatilhas?");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                check = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?");
        List<Transportadora> tran = vin.getTransportadoras();
        int contador = 1;
        for(Transportadora t : tran){
            String nome = t.getNome();
            System.out.println(contador +"-"+ nome);
            contador++;
        }
        int v=1;
        boolean checkk1 = false;

        while (!checkk1) {
            String trans = input.nextLine();
            try {
                v = Integer.parseInt(trans);
                if(v>=1 && v<= tran.size()) {
                    checkk1 = true;
                }
                else{
                    System.out.println("Erro: " + trans + " não corresponde a nenhuma das transportadoras. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + trans + " não segue as normas de seleção de transportadora. Tente novamente.");
            }
        }
        Transportadora transp = tran.get(v-1);
        String trans = transp.getNome();
        System.out.println("Qual o número (tamanho) das sapatilhas?");
        double tamanho = 0.0;
        boolean tamValido = false;

        while (!tamValido) {
            String tam = input.nextLine();
            try {
                tamanho = Double.parseDouble(tam);
                tamValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + tam + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A suas sapatilhas têm atacadores?\n1-Sim\n2-Não");
        String ata = input.nextLine();
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
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas(vin);
        }

        System.out.println("Qual a cor das suas sapatilhas?");
        String cor = input.nextLine();
        System.out.println("Qual a data de lançamento das suas sapatilhas? (Por favor, coloque a data em números)");
        System.out.print("Dia:");
        int diaa = 0;
        boolean check1 = false;

        while (!check1) {
            String dia = input.nextLine();
            try {
                diaa = Integer.parseInt(dia);
                if(diaa>=1&&diaa<=31){
                    check1 = true;
                }
                else{
                    System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Mês:");
        int mess = 0;
        boolean check2 = false;

        while (!check2) {
            String mes = input.nextLine();
            try {
                mess = Integer.parseInt(mes);
                if(mess>=1&&mess<=12){
                    check2 = true;
                }
                else{
                    System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Ano:");
        int anoo = 0;
        boolean check3 = false;

        while (!check3) {
            String ano = input.nextLine();
            try {
                anoo = Integer.parseInt(ano);
                if(anoo>=1&&anoo<=2023){
                    check3 = true;
                }
                else{
                    System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        LocalDate lanc=LocalDate.of(anoo,mess,diaa);
        System.out.println("As suas sapatilhas são Premium?\n1-Sim\n2-Não");
        String prem = input.nextLine();
        boolean premium = false;
        if(prem.equals("1")) premium = true;
        int anoAtual = data.getYear();
        if((tamanho>45 || anoAtual>anoo) && premium){
            System.out.println("Qual o desconto que quer aplicar nas sapatilhas (ex: se for 25%, a resposta deve ser 0.25) ?");
            double desconto = 0.0;
            boolean dValido = false;

            while (!dValido) {
                String des = input.nextLine();
                try {
                    desconto = Double.parseDouble(des);
                    dValido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: " + des + " não pode ser convertido para o formato do desconto Tente novamente.");
                }
            }
            System.out.println("Sapatilhas registadas, obrigado pela preferência!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                input.close();
                throw new RuntimeException(e);
            }
            String codigoSapatilhas=geraCodigo(9);
            if(premium){
                SapatilhaPremium t = new SapatilhaPremium(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo(), desconto);
                String codigo = conta.getCodigo();
                Utilizador atual = vin.getUtilizadorByCodigo(codigo);
                vin.addArtigo(t);
                atual.getProdutosLoja().add(t);
            }
            else {
                Sapatilha t = new Sapatilha(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo(), desconto);
                String codigo = conta.getCodigo();
                Utilizador atual = vin.getUtilizadorByCodigo(codigo);
                vin.addArtigo(t);
                atual.getProdutosLoja().add(t);
            }
            clearTerminal();
            abreMenuIntermedio(vin);
            input.close();
        }
        System.out.println("Sapatilhas registadas, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            input.close();
            throw new RuntimeException(e);
        }
        String codigoSapatilhas=geraCodigo(9);
        if(premium){
            SapatilhaPremium t = new SapatilhaPremium(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo(),1.00);
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        else {
            Sapatilha t = new Sapatilha(desc, marca, codigoSapatilhas, preco, estado, donos, trans, tamanho, atacadores, cor, lanc, conta.getCodigo(),1.00);
            String codigo = conta.getCodigo();
            Utilizador atual = vin.getUtilizadorByCodigo(codigo);
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        clearTerminal();
        abreMenuIntermedio(vin);
        input.close();
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
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a margem de lucro da transportadora a adicionar?");
        double lucro = 0.0;
        boolean lucroValido = false;

        while (!lucroValido){
            String pre = input.nextLine();
            try {
                lucro = Double.parseDouble(pre);
                lucroValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A transportadora é premium? (Sim ou Não)");
        String premium = input.nextLine();
        boolean prem= premium.equals("Sim");
        if(prem){
            List<Artigo> vazia = new ArrayList<>();
            TransportadoraPremium t = new TransportadoraPremium(desc,lucro,prem,vazia);
            vin.addTransportadora(t);
            System.out.println("Transportadora " + t.getNome() + " registada com sucesso.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVisaoAdmin(vin);
            input.close();
        }
        else{
            List<Artigo> vazia = new ArrayList<>();
            Transportadora t = new Transportadora(desc,lucro,prem,vazia);
            vin.addTransportadora(t);
            System.out.println("Transportadora " + t.getNome() + " registada com sucesso.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVisaoAdmin(vin);
            input.close();
        }
    }

    public void abreMenuGerirTrans(Vintage vin){
        System.out.println("Menu de gestão de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a modificar?");
        Scanner input = new Scanner(System.in);
        String nome = input.nextLine();
        Transportadora mudar = vin.getTransportdoraByName(nome);
        if(mudar==null){
            System.out.println("O nome que digitou não corresponde a nenhuma das transportadoras existentes no sistema, eis a lista de tranportadoras existentes:");
            List<Transportadora> nova = vin.getTransportadoras();
            for(Transportadora t : nova){
                System.out.println(t.getNome());
            }
            System.out.println("\n\n=======//=======\n");
            abreMenuGerirTrans(vin);
            input.close();
        }
        System.out.println("O que deseja alterar?\n1-Nome\n2-Margem de Lucro\n\n\n0-Voltar para o Menu de Visão de Administrador");
        String opc = input.nextLine();
        if(opc.equals("1")){
            System.out.println("Para que nome deseja alterar a transportadora?");
            String novo = input.nextLine();
            mudar.setNome(novo);
            System.out.println("Nome alterada para " + novo);
            abreMenuVisaoAdmin(vin);
            input.close();
        }
        else if(opc.equals("2")){
            System.out.println("Para que taxa de lucro deseja alterar a transportadora");
            double preco = 0.0;
            boolean precoValido = false;

            while (!precoValido) {
                String pre = input.nextLine();
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
            input.close();
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