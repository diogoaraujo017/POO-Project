import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu novo = new Menu();
        novo.inicio(Info_Inicial.info(),LocalDate.now());
    }
}