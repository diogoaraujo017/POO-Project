import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Info_Inicial {
    public static void info() throws Exception {

    
    Artigo art1, art2, art3;

    art1 = new Sapatilhas("Sapatilhas xyz","Nike", "a12fgd", 154.34, 'a', 3, "UPS", 43.0, false, "Amarelo", LocalDate.of(2023,4,12), false);
    art2 = new Tshirts("Tshirt xyz", "Adidas", "cssde2", 34.30, 'a', 1, "CTT", 'm', 'l');
    art3 = new Malas("Mala xyz", "Gucci", "fsd32e25", 200.00, 'n', 1, "DHGATE", 24, 43, 23, "Couro", 2020, true);


    Encomenda enc1, enc2, enc3;
    List<Artigo> conj_artigos1 = new ArrayList<>();
    conj_artigos1.add(art1);
    conj_artigos1.add(art2);

    enc1 = new Encomenda(conj_artigos1,2,'p',LocalDate.of(2023,4,2));
    


    System.out.println();
    System.out.println(art1.toString());
    System.out.println();
    System.out.println(art2.toString());
    System.out.println();
    System.out.println(art3.toString());
    System.out.println();
    System.out.println(enc1.toString());

    // for(Artigo artigo : conj_artigos1){
    //     if(artigo instanceof Sapatilhas){
    //         System.out.println(((Sapatilhas) artigo).toString());
    //     }
    //     if(artigo instanceof Malas){
    //         System.out.println(((Malas) artigo).toString());
    //     }
    //     if(artigo instanceof Tshirts){
    //         System.out.println(((Tshirts) artigo).toString());
    //     }

    // }
    }
}
