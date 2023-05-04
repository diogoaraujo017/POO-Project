import jdk.jshell.execution.Util;

import java.time.LocalDate;
import java.util.*;
import java.lang.Thread;

public class Menu{
    private Vintage shop;
    private LocalDate data;
    private Conta conta;

    public void abreMenuInicial(){
        Vintage atual=getShop();
        if(!getData().equals(LocalDate.now())){
            setData(getData());
        }
        else{
            setData(LocalDate.now());
        }
        System.out.println("\nMenu Inicial\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n\n\n\n\n\n0-Sair\n");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuLogin();
            }
            case "2" -> {
                clearTerminal();
                abreMenuRegister();
            }
            case "3" -> {
                clearTerminal();
                abreMenuData();
            }
            case "4" -> {
                clearTerminal();
                abreMenuQueries();
            }
            case "0" -> System.exit(0);
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuInicial();
            }
        }
        input.close();
    }

    public void abreMenuLogin(){
        System.out.println("Menu de Login\n");
        System.out.print("Email de Utilizador:");
        Scanner mail = new Scanner(System.in);
        String email = mail.nextLine();
        System.out.print("Insira a palavra passe:");
        Scanner password = new Scanner(System.in);
        String pass= password.nextLine();
        if(email.equals("admin") && pass.equals("1234")){
            clearTerminal();
            abreMenuVisaoAdmin();
        }
        if(Vintage.loginCorreto(email,pass)){
            Conta atual = new Conta(Vintage.getCodigoUtilizadores(email),email,pass);
            setConta(atual);
            clearTerminal();
            abreMenuIntermedio();
        }
        else{
            System.out.println("Dados de Login incorretos, tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuLogin();
        }
        mail.close();
        password.close();
    }
    public void abreMenuRegister(){
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
        new Utilizadores(code,nome,email,morada,nif,vazio,vazio,vazio,vazia);
        Conta nova = new Conta(code,email,pass);
        Vintage.addConta(nova);
        clearTerminal();
        abreMenuIntermedio();
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
    }
    public void abreMenuData(){
        System.out.println("Menu de Data");
        System.out.print("Dia:");
        Scanner s1 = new Scanner(System.in);
        String dia = s1.nextLine();
        System.out.print("Mês:");
        Scanner s2 = new Scanner(System.in);
        String mes = s2.nextLine();
        System.out.print("Ano:");
        Scanner s3 = new Scanner(System.in);
        String ano = s3.nextLine();
        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anoo = Integer.parseInt(ano);
        LocalDate mudada=LocalDate.of(anoo,mess,diaa);
        if(mudada.isAfter(getData())) {
            setData(mudada);
            System.out.println("Data alterada com sucesso");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuInicial();
        }
        else{
            System.out.println("A sua data é anterior à data atual do sistema, por favor tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuData();
        }
        s1.close();
        s2.close();
        s3.close();
    }
    public void abreMenuQueries(){
        System.out.println("Coming soon");
        abreMenuInicial();
    }
    public void abreMenuIntermedio(){
        System.out.println("Deseja:\n1-Comprar\n2-Vender\n3- Devolver Artigo\n9-Menu Inicial\n0-Sair");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuCompras();
            }
            case "2" -> {
                clearTerminal();
                abreMenuVendas();
            }
            case "3" -> {
                clearTerminal();
                abreMenuDevolucao();
            }
            case "0" -> System.exit(0);
            case "9" -> {
                clearTerminal();
                abreMenuInicial();
            }
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuIntermedio();
            }
        }
        s1.close();
    }
    public void abreMenuCompras(){
        System.out.println("Menu de Compras");
    }
    public void abreMenuVendas(){
        System.out.println("Menu de Vendas");
        System.out.println("Dejesa vender:\n1-Sapatilhas\n2-Malas\n3-TShirts\n\nSe quiser voltar atrás, prima 0");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuVendaSapatilhas();
            }
            case "2" -> {
                clearTerminal();
                abreMenuVendasMalas();
            }
            case "3" -> {
                clearTerminal();
                abreMenuVendasTShirts();
            }
            case "0" -> {
                clearTerminal();
                abreMenuIntermedio();
            }
            default -> {
                System.out.println("O seu input não vai de acordo às opções, tente novamente");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendas();
            }
        }
        s1.close();
    }
    public void abreMenuVendasTShirts(){
        System.out.println("Menu de venda de TShirts\n\n");
        System.out.println("Descreva a sua tshirt");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca da TShirt?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar a sua TShirt?");
        Scanner s3 = new Scanner(System.in);
        String pre = s3.nextLine();
        double preco = Double.parseDouble(pre);
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
            }
        }

        System.out.println("Quantos donos já teve a TShirt?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        int donos = Integer.parseInt(dono);

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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
            }
        }

        System.out.println("TShirt registada, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String codigoTShirt=geraCodigo(10);
        new Tshirts(desc,marca,codigoTShirt,preco,estado,donos,trans,tamanho,padrao);
        clearTerminal();
        abreMenuIntermedio();
        s1.close();
        s2.close();
        s3.close();
        s4.close();
        s5.close();
        s6.close();
        s7.close();
        s8.close();
    }
    public void abreMenuVendasMalas(){
        System.out.println("Menu de venda de Malas\n\n");
        System.out.println("Descreva a sua mala");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca da mala");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar a sua mala?");
        Scanner s3 = new Scanner(System.in);
        String pre = s3.nextLine();
        double preco = Double.parseDouble(pre);
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
            }
        }
        System.out.println("Quantos donos já teve a Mala?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        int donos = Integer.parseInt(dono);
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
                System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasMalas();
            }
        }

        System.out.println("Qual o comprimento da sua Mala?");
        Scanner s7 = new Scanner(System.in);
        String compri = s7.nextLine();
        int comprimento= Integer.parseInt(compri);
        System.out.println("Qual a largura da sua Mala?");
        Scanner s8 = new Scanner(System.in);
        String lar = s8.nextLine();
        int largura= Integer.parseInt(lar);
        System.out.println("Qual a altura da sua Mala?");
        Scanner s9 = new Scanner(System.in);
        String alt = s9.nextLine();
        int altura= Integer.parseInt(alt);
        System.out.println("Qual o material da sua Mala?");
        Scanner s10 = new Scanner(System.in);
        String material = s10.nextLine();
        System.out.println("Qual o ano de lançamento da sua Mala?");
        Scanner s11 = new Scanner(System.in);
        String an = s11.nextLine();
        int ano= Integer.parseInt(an);
        System.out.println("A sua mala é Premium?\n1-Sim\n2-Não");
        Scanner s12 = new Scanner(System.in);
        String prem = s12.nextLine();
        boolean premium=false;
        if(prem.equals("1")){
            premium=true;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasMalas();
        }

        System.out.println("Mala registada, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String codigoMala=geraCodigo(11);
        new Malas(desc,marca,codigoMala,preco,estado,donos,trans,comprimento,largura,altura,material,ano,premium);
        clearTerminal();
        abreMenuIntermedio();
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
    public void abreMenuVendaSapatilhas(){
        System.out.println("Menu de venda de Sapatilhas\n\n");
        System.out.println("Descreva as suas Sapatilhas");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a marca das Sapatilhas?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        System.out.println("A qual preço deseja listar as suas Sapatilhas?");
        Scanner s3 = new Scanner(System.in);
        String pre = s3.nextLine();
        double preco = Double.parseDouble(pre);
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
            }
        }


        System.out.println("Quantos donos já tiveram as Sapatilhas?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        int donos = Integer.parseInt(dono);
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
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuVendasTShirts();
            }
        }
        System.out.println("Qual o número (tamanho) das sapatilhas?");
        Scanner s7 = new Scanner(System.in);
        String tam = s7.nextLine();
        double tamanho = Double.parseDouble(tam);
        System.out.println("A suas sapatilhas têm atacadores?\n1-Sim\n2-Não");
        Scanner s8 = new Scanner(System.in);
        String ata = s8.nextLine();
        boolean atacadores=false;
        if(ata.equals("1")){
            atacadores=true;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas();
        }

        System.out.println("Qual a cor das suas sapatilhas?");
        Scanner s9 = new Scanner(System.in);
        String cor = s9.nextLine();
        System.out.println("Qual a data de lançamento das suas sapatilhas? (Por favor, coloque a data em números)");
        System.out.print("Dia:");
        Scanner s10 = new Scanner(System.in);
        String dia = s10.nextLine();
        System.out.print("Mês:");
        Scanner s11 = new Scanner(System.in);
        String mes = s11.nextLine();
        System.out.print("Ano:");
        Scanner s12 = new Scanner(System.in);
        String ano = s12.nextLine();
        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anoo = Integer.parseInt(ano);
        LocalDate lanc=LocalDate.of(anoo,mess,diaa);
        System.out.println("As suas sapatilhas são Premium?\n1-Sim\n2-Não");
        Scanner s13 = new Scanner(System.in);
        String prem = s13.nextLine();
        boolean premium=false;
        if(prem.equals("1")){
            premium=true;
        }
        else{
            System.out.println("A resposta não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas();

        }
        System.out.println("Sapatilhas registadas, obrigado pela preferência!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String codigoSapatilhas=geraCodigo(9);
        new Sapatilhas(desc,marca,codigoSapatilhas,preco,estado,donos,trans,tamanho,atacadores,cor,lanc,premium);
        clearTerminal();
        abreMenuIntermedio();
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

    public void abreMenuVisaoAdmin(){
        System.out.println("Menu de Administração\n\n");
        System.out.println("Selecione o menu que deseja aceder\n1-Criar Transportadora\n2-Gerir Transportadora\n\n\n\n\n\n\n\n0-Voltar ao Menu Inicial");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        if(entrada.equals("1")){
            clearTerminal();
            abreMenuCreateTrans();
        } else if(entrada.equals("2")){
            clearTerminal();
            abreMenuGerirTrans();
        }
        else{
            clearTerminal();
            abreMenuInicial();
        }
        input.close();
    }

    public void abreMenuCreateTrans(){
        System.out.println("Menu de criação de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a adicionar?");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        System.out.println("Qual a margem de lucro da transportadora a adicionar?");
        Scanner s2 = new Scanner(System.in);
        String pre = s2.nextLine();
        double lucro = Double.parseDouble(pre);
        System.out.println("A transportadora é premium? (Sim ou Não)");
        Scanner s3 = new Scanner(System.in);
        String premium = s3.nextLine();
        boolean prem= premium.equals("Sim");
        List<Encomenda> vazia = new ArrayList<>();
        new Transportadora(desc,lucro,prem,vazia);
        s1.close();
        s2.close();
        s3.close();
    }

    public void abreMenuGerirTrans(){
        System.out.println("Menu de gestão de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a modificar?");
        Scanner s1 = new Scanner(System.in);
        String nome = s1.nextLine();
    }

    public void abreMenuDevolucao(){
        LocalDate agora= getData();
        agora.plusDays(2);
        Conta atual=getConta();
        System.out.println();
        Vintage loja=getShop();
        Utilizadores novo = loja.getUtilizadorByEmail(atual.getEmail());
        System.out.println(novo.toString());
    }

    public Menu(Menu me){
        this.shop=me.shop;
        this.data=me.data;
        this.conta=me.conta;
    }

    public Menu(){
        this.shop=null;
        this.data=LocalDate.now();
        this.conta=null;
    }

    public Menu(Vintage shop, LocalDate dat, Conta cont) {
        this.shop = shop;
        this.data = dat;
        this.conta= cont;
    }

    public Vintage getShop() {
        return this.shop;
    }

    public void setShop(Vintage shop) {
        this.shop = shop;
    }

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


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(getShop(), menu.getShop());
    }

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
}