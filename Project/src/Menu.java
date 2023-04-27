import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Thread;

public class Menu {
    private Loja shop;

    public static void abreMenu(){
        System.out.println("\nMENU\nBem vindo à Vintage!\nAo seu dispor temos várias opções, por favor digite para aceder às diferentes opções\n\n");
        System.out.println("1-Login\n2-Registar\n3-Mudança de Data\n4-Queries\n");
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
            abreMenuHora();
        }
        else if(entrada.equals("4")){
            clearTerminal();
            abreMenuQueries();
        }
        else{
            System.out.println("O seu input não vai de acordo às opções, tente novamente");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clearTerminal();
            abreMenu();
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
        new Utilizadores(nome,email,morada,nif,null,null,null);
    }
    public static void abreMenuHora(){

    }
    public static void abreMenuQueries(){

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
