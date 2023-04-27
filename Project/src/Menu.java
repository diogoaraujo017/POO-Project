import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Thread;

public class Menu {
    private Loja shop;

    public static void abreMenuInicial(){
        System.out.println("\nMENU\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n5-\n6-\n7-\n8-\n9-\n0-Sair\n");
        System.out.println("->");
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();
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
        System.out.print("Nome de Utilizador:");
        Scanner username = new Scanner(System.in);
        String nome = username.nextLine();
        System.out.print("Insira a palavra passe:");
        Scanner password = new Scanner(System.in);
        String pass= password.nextLine();
        abreMenuIntermedio();
    }
    public static void abreMenuRegister(){
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
        new Utilizadores(nome,email,morada,nif,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        abreMenuIntermedio();
    }
    public static void abreMenuData(){
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
    }
    public static void abreMenuQueries(){
        System.out.println("Coming soon");
        abreMenuInicial();
    }
    public static void abreMenuIntermedio(){
        System.out.println("Deseja:\n1-Comprar\n2-Vender\n9-Menu Inicial\n0-Sair");
        Scanner s1 = new Scanner(System.in);
        String entrada = s1.nextLine();
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
    }
    public static void abreMenuCompras(){
        System.out.println("Menu de Compras");
    }
    public static void abreMenuVendas(){
        System.out.println("Menu de Vendas");
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
}
