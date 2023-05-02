import java.time.LocalDate;
import java.util.*;
import java.lang.Thread;

public class Menu{
    private Loja shop;

    public static void abreMenuInicial(){
        System.out.println("\nMENU\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n5-\n6-\n7-\n8-\n9-\n0-Sair\n");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        input.close();
        if(entrada.equals("1")){
            clearTerminal();
            abreMenuLogin();
        }
        if(entrada.equals("2")){
            clearTerminal();
            abreMenuRegister();
        }
        else if(entrada.equals("3")){
            clearTerminal();
            abreMenuData();
        }
        else if(entrada.equals("4")){
            clearTerminal();
            abreMenuQueries();
        }
        else if(entrada.equals("0")){
            System.exit(0);
        }

        else{
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

    public static void abreMenuLogin(){
        System.out.println("Menu de Login\n");
        System.out.print("Email de Utilizador:");
        Scanner mail = new Scanner(System.in);
        String email = mail.nextLine();
        mail.close();
        System.out.print("Insira a palavra passe:");
        Scanner password = new Scanner(System.in);
        String pass= password.nextLine();
        password.close();
        if(ContaMap.loginCorreto(email,pass)){
            clearTerminal();
            abreMenuIntermedio();
        }
        else{
            clearTerminal();
            abreMenuLogin();
        }
    }
    public static void abreMenuRegister(){
        System.out.println("Menu de Registo\n");
        System.out.print("Nome de Utilizador:");
        Scanner s1 = new Scanner(System.in);
        String nome = s1.nextLine();
        s1.close();
        System.out.print("Email:");
        Scanner s2 = new Scanner(System.in);
        String email = s2.nextLine();
        s2.close();
        System.out.print("Password:");
        Scanner s3 = new Scanner(System.in);
        String pass = s3.nextLine();
        s3.close();
        System.out.print("Morada:");
        Scanner s4 = new Scanner(System.in);
        String morada = s4.nextLine();
        s4.close();
        System.out.print("NIF:");
        Scanner s5 = new Scanner(System.in);
        String nif = s5.nextLine();
        s5.close();
        List<Artigo> vazio=new ArrayList<Artigo>();
        List<Fatura> vazia=new ArrayList<Fatura>();
        String code= geraCodigo(8);
        new Utilizadores(code,nome,email,morada,nif,vazio,vazio,vazio,vazia);
        Conta nova = new Conta(code,email,pass);
        ContaMap.addConta(nova);
        clearTerminal();
        abreMenuIntermedio();
    }
    public static void abreMenuData(){
        System.out.println("Menu de Data");
        System.out.print("Dia:");
        Scanner s1 = new Scanner(System.in);
        String dia = s1.nextLine();
        s1.close();
        System.out.print("Mês:");
        Scanner s2 = new Scanner(System.in);
        String mes = s2.nextLine();
        s2.close();
        System.out.print("Ano:");
        Scanner s3 = new Scanner(System.in);
        String ano = s3.nextLine();
        s3.close();
        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anoo = Integer.parseInt(ano);
        LocalDate mudada=LocalDate.of(anoo,mess,diaa);
    }
    public static void abreMenuQueries(){
        System.out.println("Coming soon");
        abreMenuInicial();
    }
    public static void abreMenuIntermedio(){
        System.out.println("Deseja:\n1-Comprar\n2-Vender\n9-Menu Inicial\n0-Sair");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        s1.close();
        if(entrada.equals("1")){
            clearTerminal();
            abreMenuCompras();
        }
        else if(entrada.equals("2")){
            clearTerminal();
            abreMenuVendas();
        }
        else if(entrada.equals("0")){
            System.exit(0);
        }
        else if(entrada.equals("9")){
            clearTerminal();
            abreMenuInicial();
        }
        else{
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
    public static void abreMenuCompras(){
        System.out.println("Menu de Compras");
    }
    public static void abreMenuVendas(){
        System.out.println("Menu de Vendas");
        System.out.println("Dejesa vender:\n1-Sapatilhas\n2-Malas\n3-TShirts\n\nSe quiser voltar atrás, prima 0");
        System.out.print("->");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
        s1.close();
        if(entrada.equals("1")){
            clearTerminal();
            abreMenuVendaSapatilhas();
        }
        else if(entrada.equals("2")){
            clearTerminal();
            abreMenuVendasMalas();
        }
        else if(entrada.equals("3")){
            clearTerminal();
            abreMenuVendasTShirts();
        }
        else if(entrada.equals("0")){
            clearTerminal();
            abreMenuIntermedio();
        }
        else{
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
    public static void abreMenuVendasTShirts(){
        System.out.println("Menu de venda de TShirts\n\n");
        System.out.println("Descreva a sua tshirt");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        s1.close();
        System.out.println("Qual a marca da TShirt?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        s2.close();
        System.out.println("A qual preço deseja listar a sua TShirt?");
        Scanner s3 = new Scanner(System.in);
        String pre = s3.nextLine();
        s3.close();
        double preco = Double.parseDouble(pre);

        System.out.println("A que estado corresponde a sua TShirt?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        Scanner s4 = new Scanner(System.in);
        String est = s4.nextLine();
        s4.close();
        char estado='a';
        if(est.equals("a")) estado = 'a';
        else if(est.equals("b")) estado = 'b';
        else if(est.equals("c")) estado = 'c';
        else if(est.equals("n")) estado = 'n';
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }

        System.out.println("Quantos donos já teve a TShirt?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        s5.close();
        int donos = Integer.parseInt(dono);

        System.out.println("Em qual transportadora será feito o envio da TShirt?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        s6.close();
        if((trans.equals("1")))trans="CTT";
        else if((trans.equals("2")))trans="USPS";
        else if((trans.equals("3")))trans="UPS";
        else if((trans.equals("4")))trans="DHL";
        else if((trans.equals("5")))trans="FedEx";
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }

        System.out.println("Qual o tamanho da sua TShirt?\ns-S\nm-M\nl-L\nx-XL");
        Scanner s7 = new Scanner(System.in);
        String tam = s7.nextLine();
        s7.close();
        char tamanho = 's';
        if(tam.equals("s")){
            tamanho = 's';
        }
        else if(tam.equals("m")){
            tamanho = 'm';
        }
        else if(tam.equals("l")){
            tamanho = 'l';
        }
        else if(tam.equals("x")){
            tamanho = 'x';
        }
        else{
            System.out.println("O tamanho não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }

        System.out.println("Qual o padrão da sua TShirt?\np-Palmeiras\nr-Riscas\nl-Liso");
        Scanner s8 = new Scanner(System.in);
        String pad = s8.nextLine();
        s8.close();
        char padrao='p';
        if(pad.equals("p")){
            padrao = 'p';
        }
        else if(pad.equals("r")){
            padrao = 'r';
        }
        else if(pad.equals("l")){
            padrao = 'l';
        }
        else{
            System.out.println("O padrão não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
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

    }
    public static void abreMenuVendasMalas(){
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
        char estado='a';
        if(est.equals("a")){
            estado = 'a';
        }
        else if(est.equals("b")){
            estado = 'b';
        }
        else if(est.equals("c")){
            estado = 'c';
        }
        else if(est.equals("n")){
            estado = 'n';
        }
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }


        System.out.println("Quantos donos já teve a Mala?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        int donos = Integer.parseInt(dono);

        System.out.println("Em qual transportadora será feito o envio da Mala?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        if((trans.equals("1")))trans="CTT";
        else if((trans.equals("2")))trans="USPS";
        else if((trans.equals("3")))trans="UPS";
        else if((trans.equals("4")))trans="DHL";
        else if((trans.equals("5")))trans="FedEx";
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasMalas();
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
        else if(prem.equals("2")){
            premium=false;
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

    }
    public static void abreMenuVendaSapatilhas(){
        System.out.println("Menu de venda de Sapatilhas\n\n");
        System.out.println("Descreva as suas Sapatilhas");
        Scanner s1 = new Scanner(System.in);
        String desc = s1.nextLine();
        s1.close();
        System.out.println("Qual a marca das Sapatilhas?");
        Scanner s2 = new Scanner(System.in);
        String marca = s2.nextLine();
        s2.close();
        System.out.println("A qual preço deseja listar as suas Sapatilhas?");
        Scanner s3 = new Scanner(System.in);
        String pre = s3.nextLine();
        double preco = Double.parseDouble(pre);
        s3.close();
        System.out.println("A que estado correspondem as suas Sapatilhas?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        Scanner s4 = new Scanner(System.in);
        String est = s4.nextLine();
        s4.close();
        char estado='a';
        if(est.equals("a")){
            estado = 'a';
        }
        else if(est.equals("b")){
            estado = 'b';
        }
        else if(est.equals("c")){
            estado = 'c';
        }
        else if(est.equals("n")){
            estado = 'n';
        }
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }


        System.out.println("Quantos donos já tiveram as Sapatilhas?");
        Scanner s5 = new Scanner(System.in);
        String dono = s5.nextLine();
        int donos = Integer.parseInt(dono);
        s5.close();
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?\n1-CTT\n2-USPS\n3-UPS\n4-DHL\n5-FedEx");
        Scanner s6 = new Scanner(System.in);
        String trans = s6.nextLine();
        s6.close();
        if((trans.equals("1")))trans="CTT";
        else if((trans.equals("2")))trans="USPS";
        else if((trans.equals("3")))trans="UPS";
        else if((trans.equals("4")))trans="DHL";
        else if((trans.equals("5")))trans="FedEx";
        else{
            System.out.println("O estado não corresponde a nenhuma das opções, tente novamente.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendasTShirts();
        }
        System.out.println("Qual o número (tamanho) das sapatilhas?");
        Scanner s7 = new Scanner(System.in);
        String tam = s7.nextLine();
        double tamanho = Double.parseDouble(tam);
        s7.close();
        System.out.println("A suas sapatilhas têm atacadores?\n1-Sim\n2-Não");
        Scanner s8 = new Scanner(System.in);
        String ata = s8.nextLine();
        s8.close();
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
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuVendaSapatilhas();
        }

        System.out.println("Qual a cor das suas sapatilhas?");
        Scanner s9 = new Scanner(System.in);
        String cor = s9.nextLine();
        s9.close();
        System.out.println("Qual a data de lançamento das suas sapatilhas? (Por favor, coloque a data em números)");
        System.out.print("Dia:");
        Scanner s10 = new Scanner(System.in);
        String dia = s10.nextLine();
        s10.close();
        System.out.print("Mês:");
        Scanner s11 = new Scanner(System.in);
        String mes = s11.nextLine();
        s11.close();
        System.out.print("Ano:");
        Scanner s12 = new Scanner(System.in);
        String ano = s12.nextLine();
        s12.close();
        int diaa = Integer.parseInt(dia);
        int mess = Integer.parseInt(mes);
        int anoo = Integer.parseInt(ano);
        LocalDate lanc=LocalDate.of(anoo,mess,diaa);

        System.out.println("As suas sapatilhas são Premium?\n1-Sim\n2-Não");
        Scanner s13 = new Scanner(System.in);
        String prem = s13.nextLine();
        s13.close();
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
    }

    public Menu(Menu me){
        this.shop=me.shop;
    }

    public Menu(){
        this.shop=null;
    }

    public Menu(Loja shop) {
        this.shop = shop;
    }

    public Loja getShop() {
        return shop;
    }

    public void setShop(Loja shop) {
        this.shop = shop;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(getShop(), menu.getShop());
    }

    public static void clearTerminal() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    public static String geraCodigo(int codigoLength){
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
