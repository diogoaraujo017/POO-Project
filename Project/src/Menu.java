import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.nio.file.*;
import java.lang.Thread;


public class Menu{

    private LocalDate data;
    private Conta conta;
    private boolean flag;

    
    public Menu(){
        this.flag=false;
    }
    public boolean getFlag (){
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }



    public void inicio (LocalDate data_atual){
        setData(data_atual);
        Vintage vin = new Vintage();
        List<Artigo> todos = vin.getListaArtigos();
        for(Artigo art : todos){
            if(art instanceof Mala){
                if(!(art instanceof Premium))recalculaValorFinalMala((Mala)art,data_atual);
                //else art.calculaValorMalaPremium(art);
            }
            else if(art instanceof Sapatilha && !(art instanceof Premium)){
                recalculaValorFinalSapatilhas((Sapatilha)art,data_atual);
            }
        }
        abreMenuInicial(vin);
    }

    public void abreMenuInicial(Vintage vin){

        System.out.println("\nMenu Inicial\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n5-Carregar Estado\n6-Guardar Estado\n\n\n\n0-Sair\n");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        switch (entrada) {
            case "1" -> {
                if(this.flag==true){
                    clearTerminal();
                    abreMenuLogin(vin);
                }
                else{
                    System.out.println("Carregue um estado primeiro!");
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
            case "2" -> {
                if(this.flag==true){
                    clearTerminal();
                    abreMenuRegister(vin);
                }
                else{
                    System.out.println("Carregue um estado primeiro!");
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
            case "3" -> {
                if(this.flag==true){
                    clearTerminal();
                    abreMenuData(vin);
                }
                else{
                    System.out.println("Carregue um estado primeiro!");
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
            case "4" -> {
                if(this.flag==true){
                    clearTerminal();
                    abreMenuQueries(vin);
                }
                else{
                    System.out.println("Carregue um estado primeiro!");
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
            case "5" -> {
                this.setFlag(true);
                clearTerminal();
                abreMenuCarregarEstado(vin);
            }
            case "6" -> {
                if(this.flag==true){
                    clearTerminal();
                    //abreguardarEstado();
                }
                else{
                    System.out.println("Carregue um estado primeiro!");
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
            System.out.print("->");
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
        if(!(vin.getUtilizadorByEmail(email)==null)){
            System.out.print("O email introduzido já se encontra utilizado no sistema, tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                input.close();
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenuRegister(vin);
        }
        System.out.print("Password:");
        String pass = input.nextLine();
        System.out.print("Morada:");
        String morada = input.nextLine();
        System.out.print("NIF:");
        String nif = input.nextLine();
        String code= geraCodigo(8);
        new Utilizador(code,nome,email,morada,nif);
        Conta nova = new Conta(code,email,pass);
        vin.addConta(nova);
        clearTerminal();
        abreMenuIntermedio(vin);
        input.close();
    }
    public void abreMenuData(Vintage vin){
        System.out.println("Menu de Data");
        System.out.print("Ano:");
        Scanner input = new Scanner(System.in);
        int anoo = 0;
        boolean check2 = false;

        while (!check2) {
            String ano = input.nextLine();
            try {
                anoo = Integer.parseInt(ano);
                if(anoo>=1){
                    check2= true;
                }
                else{
                    System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        System.out.print("Mês:");
        int mess = 0;
        boolean check1 = false;

        while (!check1) {
            String mes = input.nextLine();
            try {
                mess = Integer.parseInt(mes);
                if(mess>=1&&mess<=12){
                    check1 = true;
                }
                else{
                    System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }

        System.out.print("Dia:");
        int diaa = 0;
        boolean check = false;

        while (!check) {
            String dia = input.nextLine();
            try {
                diaa = Integer.parseInt(dia);
                if (diaa >= 1 && diaa <= getDiasMes(mess, anoo)) {
                    check = true;
                }
                else{
                    System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        LocalDate mudada=LocalDate.of(anoo,mess,diaa);
        if(mudada.isAfter(getData())) {
            setData(mudada);
            System.out.println("Data alterada com sucesso");
            List<Artigo> todos = vin.getListaArtigos();
            for(Artigo art : todos){
                if(art instanceof Mala && !(art instanceof Premium)){
                    recalculaValorFinalMala((Mala)art,mudada);
                }
                if(art instanceof Sapatilha && !(art instanceof Premium)){
                    recalculaValorFinalSapatilhas((Sapatilha) art,mudada);
                }
            }
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
        System.out.println("Bem vindo ao menu das Queries\nQual Querie deseja executar?\n\n\n1-Maior vendedor num intervalo de tempo\n2-Transportadora com maior volume de faturação\n3-Ver encomenda emitidas por um utilizador\n4-Ver o ranking de vendedores num intervalo de tempo\n5-Ver o ranking de compradores num intervalo de tempo\n6-Ver quanto ganhou a Vintage\n\n\n\n0-Voltar para o Menu Inicial");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
        switch (entrada) {
            case "1" -> {
                System.out.println("Qual a data de inicio do intervalo de tempo?");
                System.out.print("Ano:");
                int anoo = 0;
                boolean check2 = false;

                while (!check2) {
                    String ano = input.nextLine();
                    try {
                        anoo = Integer.parseInt(ano);
                        if(anoo>=1){
                            check2= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int mess = 0;
                boolean check1 = false;

                while (!check1) {
                    String mes = input.nextLine();
                    try {
                        mess = Integer.parseInt(mes);
                        if(mess>=1&&mess<=12){
                            check1 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaa = 0;
                boolean check = false;

                while (!check) {
                    String dia = input.nextLine();
                    try {
                        diaa = Integer.parseInt(dia);
                        if (diaa >= 1 && diaa <= getDiasMes(mess, anoo)) {
                            check = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate depois=LocalDate.of(anoo,mess,diaa);
                System.out.println("Qual a data de fim do intervalo de tempo?");
                System.out.print("Ano:");
                int anooo = 0;
                boolean check20 = false;

                while (!check20) {
                    String ano = input.nextLine();
                    try {
                        anooo = Integer.parseInt(ano);
                        if(anooo>=1){
                            check20= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int messs = 0;
                boolean check10 = false;

                while (!check10) {
                    String mes = input.nextLine();
                    try {
                        messs = Integer.parseInt(mes);
                        if(messs>=1&&messs<=12){
                            check10 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaaa = 0;
                boolean check0 = false;

                while (!check0) {
                    String dia = input.nextLine();
                    try {
                        diaaa = Integer.parseInt(dia);
                        if (diaaa >= 1 && diaa <= getDiasMes(messs, anooo)) {
                            check0 = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate antes=LocalDate.of(anooo,messs,diaaa);
                Queries querie = new Queries();
                querie.topVendedor(vin,depois,antes);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "2" -> {
                Queries querie = new Queries();
                String mais = querie.transMaiorLucro(vin);
                System.out.println(mais + " é a transportadora que mais facturou com a Vintage");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "3" -> {
                System.out.print("Digite o email que deseja verificar.\n->");
                String email = input.nextLine();
                if(vin.getContaByEmail(email)==null){
                    System.out.println("O email que está a utilizar não corresponde a nenhum email da nossa base de dados, tente novamente!");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        input.close();
                        throw new RuntimeException(e);
                    }
                    clearTerminal();
                    abreMenuQueries(vin);
                }
                Queries querie = new Queries();
                querie.listaEncomendas(vin,email);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "4" -> {
                System.out.println("Qual a data de inicio do intervalo de tempo?");
                System.out.print("Ano:");
                int anoo = 0;
                boolean check2 = false;

                while (!check2) {
                    String ano = input.nextLine();
                    try {
                        anoo = Integer.parseInt(ano);
                        if(anoo>=1){
                            check2= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int mess = 0;
                boolean check1 = false;

                while (!check1) {
                    String mes = input.nextLine();
                    try {
                        mess = Integer.parseInt(mes);
                        if(mess>=1&&mess<=12){
                            check1 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaa = 0;
                boolean check = false;

                while (!check) {
                    String dia = input.nextLine();
                    try {
                        diaa = Integer.parseInt(dia);
                        if (diaa >= 1 && diaa <= getDiasMes(mess, anoo)) {
                            check = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate depois=LocalDate.of(anoo,mess,diaa);
                System.out.println("Qual a data de fim do intervalo de tempo?");
                System.out.print("Ano:");
                int anooo = 0;
                boolean check20 = false;

                while (!check20) {
                    String ano = input.nextLine();
                    try {
                        anooo = Integer.parseInt(ano);
                        if(anooo>=1){
                            check20= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int messs = 0;
                boolean check10 = false;

                while (!check10) {
                    String mes = input.nextLine();
                    try {
                        messs = Integer.parseInt(mes);
                        if(messs>=1&&messs<=12){
                            check10 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaaa = 0;
                boolean check0 = false;

                while (!check0) {
                    String dia = input.nextLine();
                    try {
                        diaaa = Integer.parseInt(dia);
                        if (diaaa >= 1 && diaa <= getDiasMes(messs, anooo)) {
                            check0 = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate antes=LocalDate.of(anooo,messs,diaaa);
                Queries querie = new Queries();
                querie.topVendedores(vin,depois,antes);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "5" -> {
                System.out.println("Qual a data de inicio do intervalo de tempo?");
                System.out.print("Ano:");
                int anoo = 0;
                boolean check2 = false;

                while (!check2) {
                    String ano = input.nextLine();
                    try {
                        anoo = Integer.parseInt(ano);
                        if(anoo>=1){
                            check2= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int mess = 0;
                boolean check1 = false;

                while (!check1) {
                    String mes = input.nextLine();
                    try {
                        mess = Integer.parseInt(mes);
                        if(mess>=1&&mess<=12){
                            check1 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaa = 0;
                boolean check = false;

                while (!check) {
                    String dia = input.nextLine();
                    try {
                        diaa = Integer.parseInt(dia);
                        if (diaa >= 1 && diaa <= getDiasMes(mess, anoo)) {
                            check = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate depois=LocalDate.of(anoo,mess,diaa);
                System.out.println("Qual a data de fim do intervalo de tempo?");
                System.out.print("Ano:");
                int anooo = 0;
                boolean check20 = false;

                while (!check20) {
                    String ano = input.nextLine();
                    try {
                        anooo = Integer.parseInt(ano);
                        if(anooo>=1){
                            check20= true;
                        }
                        else{
                            System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + ano + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                System.out.print("Mês:");
                int messs = 0;
                boolean check10 = false;

                while (!check10) {
                    String mes = input.nextLine();
                    try {
                        messs = Integer.parseInt(mes);
                        if(messs>=1&&messs<=12){
                            check10 = true;
                        }
                        else{
                            System.out.println("Erro: " + mes + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + mes + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }

                System.out.print("Dia:");
                int diaaa = 0;
                boolean check0 = false;

                while (!check0) {
                    String dia = input.nextLine();
                    try {
                        diaaa = Integer.parseInt(dia);
                        if (diaaa >= 1 && diaa <= getDiasMes(messs, anooo)) {
                            check0 = true;
                        }
                        else{
                            System.out.println("Erro: " + dia + " não é válido. Tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: " + dia + " não pode ser convertido para um inteiro. Tente novamente.");
                    }
                }
                LocalDate antes=LocalDate.of(anooo,messs,diaaa);
                Queries querie = new Queries();
                querie.topCompradores(vin,depois,antes);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "6" -> {
                Queries querie = new Queries();
                double toPrint = querie.lucroVintage(vin);
                System.out.println("A Vintage já ganhou " + toPrint + "€ desde o começo da sua atividade");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    input.close();
                    throw new RuntimeException(e);
                }
                clearTerminal();
                abreMenuQueries(vin);
            }
            case "0" -> {
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
                abreMenuQueries(vin);
            }
        }
        input.close();
    }

    public void abreMenuCarregarEstado(Vintage vin){
        boolean flag = false;
        Scanner input = new Scanner(System.in);;

        while(!flag){
            System.out.print("Indique o path do ficheiro que pretende carrregar: ");
            String file_path = input.nextLine();
            Path path = Paths.get(file_path);
            if(Files.exists(path) && !Files.isDirectory(path)){
                flag=true;
                vin.carregaEstadoCSV(file_path);
                abreMenuInicial(vin);
            } 
            else {
                System.out.println("\n\nO path é inválido. Por favor insira novamente:");
            }
    }
    input.close();
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
                System.out.println(art);
                System.out.println();
                contador++;
            }
        }
        int num=0;
        System.out.print("\nSe desejar voltar para o menu anterior escreva 0\nIntroduza o número dos produtos que pretende comprar: ");
        Scanner input = new Scanner(System.in);
        String linha = input.nextLine();
        if(linha.equals("0")){
            System.out.println("\nVoltando para o menu anterior...");
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
        String[] partes = linha.split(",");

        for (String parte : partes) {
            try {
                num = Integer.parseInt(parte) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + parte + " não pode ser convertido para um inteiro. Tente novamente.\n\n\n");
                abreMenuCompras(vin);
            }
            if (num >= 0 && num <= (contador - 2)) {
                String codigo = artigos_disponiveis.get(num).getCodigo();
                if (vin.existeArtigo(codigo)) {
                    flag = true;
                    Artigo art = vin.getArtigo(codigo);
                    encomenda.add(art);
                    System.out.println("Adicionado ao carrinho o produto: " + art.getCodigo());
                }
            } else {
                System.out.println("Erro: " + parte + " não existe");
            }
        }

        if(flag){ // Se existir pelo menor um produto na encomenda procede para o checkout
            System.out.print("\n\n");
            Encomenda enc = new Encomenda(encomenda,encomenda.size(),'p',LocalDate.now(),conta.getCodigo());
            vin.addEncomenda(enc);
            System.out.println("Total da sua compra = "+ enc.valorFinalEncomenda(enc,vin) + "€");
            System.out.println("Confirmar a encomenda: Sim | Não");
            System.out.print("->");
            boolean aux = false;
            String conf = "";
            while (!aux) {
                conf = input.nextLine();
                try {
                    if(conf.equals("Sim") || conf.equals("Não")) aux = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: " + conf + " é uma opção inválida. Tente novamente.");
                }
            }
            if(conf.equals("Sim")){
                System.out.println("\nCompra efetuada com sucesso!");
                enc.setEstado('f');
                int i;
                for(i=0;i<encomenda.size();i++){
                    Fatura fatura = new Fatura();
                    Artigo art = encomenda.get(i);
                    vin.emiteFatura(data,art,vin.getUtilizadorByCodigo(art.getVendedor()),vin.getUtilizadorByCodigo(conta.getCodigo()));
                    vin.removeArtigo(art);
                }
            }
            else if(conf.equals("Não")){
                System.out.println("\nEncomenda cancelada");
                enc.clearArtigos();
                enc.setComprador("");
                enc.setDimensao(0);
                enc.setEstado('x');
            }
        }


        try {
            Thread.sleep(3000);
            clearTerminal();
        } catch (InterruptedException e) {
            input.close();
            throw new RuntimeException(e);
        }


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
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca da TShirt?");
        System.out.print("->");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar a sua TShirt?");
        System.out.print("->");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                if(preco>=5) precoValido = true;
                else System.out.println("Valor inválido. Preço mínimo é de 5€");
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua TShirt?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        System.out.print("->");
        String est ;
        char estado='o';
        boolean scheck=false;
        while (!scheck) {
            est = input.nextLine();
            switch (est) {
                case "a" -> {
                    estado = 'a';
                    scheck=true;
                }
                case "b" -> {
                    estado = 'b';
                    scheck=true;
                }
                case "c" -> {
                    estado = 'c';
                    scheck=true;
                }
                case "n" -> {
                    estado = 'n';
                    scheck=true;
                }
                default -> {
                    System.out.println("Opção inválida. Por favor, insira a, b, c ou n, de acordo com o estado da suas sapatilhas");
                    System.out.print("->");
                    scheck=false;
                }
            }
        }
        System.out.println("Quantos donos já teve a TShirt?");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                if(donos>=0) check = true;
                else{
                    System.out.println("Opção inválida. O número de donos tem de ser um valor positivo");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Sapatilhas?");
        System.out.print("->");
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
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + trans + " não segue as normas de seleção de transportadora. Tente novamente.");
                System.out.print("->");
            }
        }
        Transportadora transp = tran.get(v-1);
        String trans = transp.getNome();

        char tamanho = 'o';

        while (tamanho == 'o') {
            System.out.println("Qual o tamanho da sua TShirt?\ns-S\nm-M\nl-L\nx-XL");
            System.out.print("->");
            String tam = input.nextLine();

            switch (tam) {
                case "s" -> tamanho = 's';
                case "m" -> tamanho = 'm';
                case "l" -> tamanho = 'l';
                case "x" -> tamanho = 'x';
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        char padrao = 'o';

        while (padrao == 'o') {
            System.out.println("Qual o padrão da sua TShirt?\np-Palmeiras\nr-Riscas\nl-Liso");
            System.out.print("->");
            String entrada = input.nextLine();

            switch (entrada) {
                case "p" -> padrao = 'p';
                case "r" -> padrao = 'r';
                case "l" -> padrao = 'l';
                default -> System.out.println("Opção inválida. Tente novamente.");
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
        Tshirt t = new Tshirt(desc,marca,codigoTShirt,preco,estado,donos,trans,conta.getCodigo(),tamanho,padrao);

        Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
        vin.addArtigo(t);
        atual.getProdutosLoja().add(t);
        clearTerminal();
        abreMenuIntermedio(vin);
        input.close();
    }
    public void abreMenuVendasMalas(Vintage vin){
        System.out.println("Menu de venda de Malas\n\n");
        System.out.println("Descreva a sua mala");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca da mala");
        System.out.print("->");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar a sua mala?");
        System.out.print("->");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                if(preco>=5) precoValido = true;
                else {
                    System.out.println("Valor inválido. Preço mínimo é de 5€");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado corresponde a sua Mala?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        System.out.print("->");
        String est;
        char estado='o';
        boolean scheck=false;
        while (!scheck) {
            est = input.nextLine();
            switch (est) {
                case "a" -> {
                    estado = 'a';
                    scheck=true;
                }
                case "b" -> {
                    estado = 'b';
                    scheck=true;
                }
                case "c" -> {
                    estado = 'c';
                    scheck=true;
                }
                case "n" -> {
                    estado = 'n';
                    scheck=true;
                }
                default -> {
                    System.out.println("Opção inválida. Por favor, insira a, b, c ou n, de acordo com o estado da suas sapatilhas");
                    System.out.print("->");
                    scheck=false;
                }
            }
        }
        System.out.println("Quantos donos já teve a Mala?");
        System.out.print("->");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                if(donos>=0) check = true;
                else{
                    System.out.println("Opção inválida. O número de donos tem de ser um valor positivo");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
            }
        }
        System.out.println("Em qual transportadora será feito o envio das Malas?");
        List<Transportadora> tran = vin.getTransportadoras();
        int contador = 1;
        for(Transportadora t : tran){
            String nome = t.getNome();
            System.out.println(contador +"-"+ nome);
            contador++;
        }
        System.out.print("->");
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
        System.out.print("->");
        int comprimento = 0;
        boolean check1 = false;

        while (!check1) {
            String comp = input.nextLine();
            try {
                comprimento = Integer.parseInt(comp);
                if(comprimento > 0)check1 = true;
                else{
                    System.out.println("O comprimento tem de ser maior que 0, tente novamente.");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + comp + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
            }
        }
        System.out.println("Qual a largura da sua Mala?");
        System.out.print("->");
        int largura = 0;
        boolean check2 = false;

        while (!check2) {
            String larg = input.nextLine();
            try {
                largura = Integer.parseInt(larg);
                if(largura > 0) check2 = true;
                else{
                    System.out.println("A largura tem de ser maior que 0, tente novamente.");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + larg + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
            }
        }
        System.out.println("Qual a altura da sua Mala?");
        System.out.print("->");
        int altura = 0;
        boolean check3 = false;

        while (!check3) {
            String alt = input.nextLine();
            try {
                altura = Integer.parseInt(alt);
                if(altura > 0)check3 = true;
                else{
                    System.out.println("A altura tem de ser maior que 0, tente novamente.");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + alt + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
            }
        }
        System.out.println("Qual o material da sua Mala?");
        System.out.print("->");
        String material = input.nextLine();
        System.out.println("Qual o ano de lançamento da sua Mala?");
        System.out.print("->");
        int ano = 0;
        boolean check4 = false;

        while (!check4) {
            String anoo = input.nextLine();
            try {
                ano = Integer.parseInt(anoo);
                if(ano>=1 && ano<=getData().getYear()){
                    check4 = true;
                }
                else{
                    System.out.println("Erro: " + ano + " não é válido. Tente novamente.");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + anoo + " não pode ser convertido para um inteiro. Tente novamente.");
            }
        }
        boolean premium = false;

        while (true) {
            System.out.println("A sua mala é Premium?\n1-Sim\n2-Não");
            System.out.print("->");
            String prem = input.nextLine();

            if (prem.equals("1")) {
                premium = true;
                break; // sai do loop while
            } else if (prem.equals("2")) {
                premium = false;
                break; // sai do loop while
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
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
            MalaPremium t = new MalaPremium(desc, marca, codigoMala, preco, estado, donos, trans,conta.getCodigo(), comprimento, largura, altura, material, ano);

            Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        else{
            Mala t = new Mala(desc, marca, codigoMala, preco, estado, donos, trans, conta.getCodigo(), comprimento, largura, altura, material, ano);

            Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
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
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a marca das Sapatilhas?");
        System.out.print("->");
        String marca = input.nextLine();
        System.out.println("A qual preço deseja listar as suas Sapatilhas?");
        System.out.print("->");
        double preco = 0.0;
        boolean precoValido = false;

        while (!precoValido) {
            String pre = input.nextLine();
            try {
                preco = Double.parseDouble(pre);
                if(preco>=5) precoValido = true;
                else{
                    System.out.println("Valor inválido. Preço mínimo é de 5€");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
            }
        }
        System.out.println("A que estado correspondem as suas Sapatilhas?\nn-Nova\na-Como Nova\nb-Bom Estado\nc-Mau/Médio");
        System.out.print("->");
        String est ;
        char estado='o';
        boolean scheck=false;
        while (!scheck) {
            est = input.nextLine();
            switch (est) {
                case "a" -> {
                    estado = 'a';
                    scheck=true;
                }
                case "b" -> {
                    estado = 'b';
                    scheck=true;
                }
                case "c" -> {
                    estado = 'c';
                    scheck=true;
                }
                case "n" -> {
                    estado = 'n';
                    scheck=true;
                }
                default -> {
                    System.out.println("Opção inválida. Por favor, insira a, b, c ou n, de acordo com o estado da suas sapatilhas");
                    System.out.print("->");
                    scheck=false;
                }
            }
        }
        System.out.println("Quantos donos já tiveram as Sapatilhas?");
        System.out.print("->");
        int donos = 0;
        boolean check = false;

        while (!check) {
            String dono = input.nextLine();
            try {
                donos = Integer.parseInt(dono);
                if(donos>=0) check = true;
                else{
                    System.out.println("Opção inválida. O número de donos tem de ser um valor positivo");
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + dono + " não pode ser convertido para um inteiro. Tente novamente.");
                System.out.print("->");
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
        System.out.print("->");
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
                    System.out.print("->");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + trans + " não segue as normas de seleção de transportadora. Tente novamente.");
                System.out.print("->");
            }
        }
        Transportadora transp = tran.get(v-1);
        String trans = transp.getNome();
        System.out.println("Qual o número (tamanho) das sapatilhas?");
        System.out.print("->");
        double tamanho = 0.0;
        boolean tamValido = false;

        while (!tamValido) {
            String tam = input.nextLine();
            try {
                tamanho = Double.parseDouble(tam);
                tamValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + tam + " não pode ser convertido para um double. Tente novamente.");
                System.out.print("->");
            }
        }

        boolean atacadores = false;

        while (true) {
            System.out.println("A suas sapatilhas têm atacadores?\n1-Sim\n2-Não");
            System.out.print("->");
            String entrada = input.nextLine();

            if (entrada.equals("1")) {
                atacadores = true;
                break;
            } else if (entrada.equals("2")) {
                atacadores = false;
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Qual a cor das suas sapatilhas?");
        System.out.print("->");
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
                if(anoo>=1&&anoo<=getData().getYear()){
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

        boolean premium = false;

        while (true) {
            System.out.println("As suas sapatilhas são Premium?\n1-Sim\n2-Não");
            System.out.print("->");
            String prem = input.nextLine();

            if (prem.equals("1")) {
                premium = true;
                break; // sai do loop while
            } else if (prem.equals("2")) {
                premium = false;
                break; // sai do loop while
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
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
            abreMenuVendaSapatilhas(vin);
        }
        if((tamanho>45 || donos>0) && !premium){
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
                SapatilhaPremium t = new SapatilhaPremium(desc, marca, codigoSapatilhas, preco, estado, donos, trans,conta.getCodigo(), tamanho, atacadores, cor, lanc, desconto);

                Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
                vin.addArtigo(t);
                atual.getProdutosLoja().add(t);
            }
            else {
                Sapatilha t = new Sapatilha(desc, marca, codigoSapatilhas, preco, estado, donos, trans,conta.getCodigo(), tamanho, atacadores, cor, lanc, desconto);

                Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
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
            SapatilhaPremium t = new SapatilhaPremium(desc, marca, codigoSapatilhas, preco, estado, donos, trans,conta.getCodigo(), tamanho, atacadores, cor, lanc, 1.00);

            Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
            vin.addArtigo(t);
            atual.getProdutosLoja().add(t);
        }
        else {
            Sapatilha t = new Sapatilha(desc, marca, codigoSapatilhas, preco, estado, donos, trans,conta.getCodigo(), tamanho, atacadores, cor, lanc, 1.00);

            Utilizador atual = vin.getUtilizadorByCodigo(conta.getCodigo());
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

        switch (entrada) {
            case "1" -> {
                clearTerminal();
                abreMenuCreateTrans(vin);
            }
            case "2" -> {
                clearTerminal();
                abreMenuGerirTrans(vin);
            }
            case "0" -> {
                clearTerminal();
                abreMenuInicial(vin);
            }
            default -> {
                System.out.println("======O que digitou não corresponde a nenhuma opção, tente novamente======");
                abreMenuVisaoAdmin(vin);
            }
        }
        input.close();
    }

    public void abreMenuCreateTrans(Vintage vin){
        System.out.println("Menu de criação de transportadoras\n\n");
        System.out.println("Qual o nome da transportadora a adicionar?");
        System.out.print("->");
        Scanner input = new Scanner(System.in);
        String desc = input.nextLine();
        System.out.println("Qual a margem de lucro da transportadora a adicionar?");
        System.out.print("->");
        double lucro = 0.0;
        boolean lucroValido = false;

        while (!lucroValido){
            String pre = input.nextLine();
            try {
                lucro = Double.parseDouble(pre);
                if(lucro>1) lucroValido = true;
                else {
                    lucroValido = false;
                    System.out.println("O lucro inserido tem de ser maior do que 1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + pre + " não pode ser convertido para um double. Tente novamente.");
                System.out.print("->");
            }
        }
        boolean premium = false;

        while (true) {
            System.out.println("A transportadora é Premium?\n1-Sim\n2-Não");
            System.out.print("->");
            String prem = input.nextLine();

            if (prem.equals("1")) {
                premium = true;
                break; // sai do loop while
            } else if (prem.equals("2")) {
                premium = false;
                break; // sai do loop while
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        if(premium){

            TransportadoraPremium t = new TransportadoraPremium(desc,lucro);
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

            Transportadora t = new Transportadora(desc,lucro);
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
        System.out.print("->");
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
        System.out.print("->");
        String opc = input.nextLine();

        switch (opc) {
            case "1" -> {
                System.out.println("Para que nome deseja alterar a transportadora?");
                System.out.print("->");
                String novo = input.nextLine();
                mudar.setNome(novo);
                System.out.println("Nome alterada para " + novo);
                abreMenuVisaoAdmin(vin);
                input.close();
            }
            case "2" -> {
                System.out.println("Para que taxa de lucro deseja alterar a transportadora");
                System.out.print("->");
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
            case "0" -> {
                clearTerminal();
                abreMenuVisaoAdmin(vin);
            }
            default -> {
                System.out.println("======O que digitou não corresponde a nenhuma opção, tente novamente======");
                abreMenuGerirTrans(vin);
            }
        }
    }

    public void abreMenuDevolucao(Vintage vin){
        LocalDate agora = getData();
        agora.plusDays(2);

        System.out.println();

        Utilizador novo = vin.getUtilizadorByCodigo(conta.getCodigo());
        System.out.println(novo.toString());
    }



    // public Menu(Menu me){
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

        for(int j = 0; j < codigoLength; j++) {
            codigo += charSet.charAt(rand.nextInt(charSet.length())); // adiciona um caractere aleatório da string charSet
        }

        return codigo;
    }

    public boolean verificaEmail(String email){
        if (email == null) return false;
        if (email.equals("admin")) return true;
        boolean arrobaEncontrado = false;
        boolean pontoEncontrado = false;
        for (int i = 0; i < email.length(); i++) {
            char comp = email.charAt(i);
            if(comp=='@') arrobaEncontrado=true;
            if(comp=='.'){
                if(arrobaEncontrado){
                    pontoEncontrado=true;
                }
            }
        }
        return (arrobaEncontrado && pontoEncontrado);
    }

    public boolean verificaAnoBissexto(int year){
        // Um ano é bissexto se for divisível por 4, exceto se for divisível por 100, a menos que seja divisível por 400.
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) return true;
                else return false;
            } else return true;
        } else return false;
    }

    public int getDiasMes(int month, int year){
        if (month == 2) {
            if (verificaAnoBissexto(year)) return 29;
            else return 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
        return 31;
    }
    public int idade_sapatilhas(LocalDate idade, LocalDate date){
        Period diferenca = Period.between(idade, date);

        return diferenca.getYears();
    }
    public void recalculaValorFinalSapatilhas(Sapatilha sp, LocalDate date){
        double preco_base = sp.getPrecoBase();
        double preco_final = preco_base;
        double desconto = sp.getDesconto();
        char estado = sp.getEstado();
        int idade =idade_sapatilhas(sp.getDataLancamento(), date);
        int n_donos = sp.getNDonos();

        if(n_donos > 4) {
            n_donos = 4;
        }


        if(estado == 'n' && sp.getNTamanho() > 45) {
            preco_final = (preco_base - sp.getNTamanho() * 0.1)*(1-desconto);
        }

        if(estado != 'n'){
            switch(estado){
                case 'a':
                    preco_final = (preco_base - (preco_base * n_donos) * 0.1 - idade);
                    break;
                case 'b':
                    preco_final = preco_base - (preco_base * n_donos) * 0.13 - idade;
                    break;
                case 'c':
                    preco_final = preco_base - (preco_base * n_donos) * 0.16 - idade;
                    break;
            }
            if(desconto>0 && desconto<1) preco_final=preco_final*(1-desconto);
        }


        if(preco_final <= 10) {
            preco_final = 10;
        }
        preco_final = Math.round(preco_final * 100.0) / 100.0;
        sp.setPrecoFinal(preco_final);
    }

    public void recalculaValorFinalMala(Mala mala, LocalDate date){
        double preco_base = mala.getPrecoBase();
        double preco_final = preco_base;

        int idade = date.getYear() - mala.getAnoLancamento();
        int volume = mala.getComprimento() * mala.getLargura() * mala.getAltura();

        preco_final = preco_base - preco_base * 0.02 * idade - (double) 12000000 / volume;

        if(preco_final <= 15) preco_final = 15;

        preco_final = Math.round(preco_final * 100.0) / 100.0;
        mala.setPrecoFinal(preco_final);
    }

}