import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Info_Inicial {
    public static void info() throws Exception {

    
    Artigo art1, art2, art3, art4, art5, art6, art7, art8, art9, art10, art11, art12, art13, art14, art15, art16, art17, art18, art19, art20;

        art1 = new Sapatilhas("Sapatilhas Desportivas","Nike", "a12fgd", 154.34, 'a', 3, "UPS", 43.0, false, "Amarelo", LocalDate.of(2023,6,12), false);
        art2 = new Sapatilhas("Sapatilhas Casuais","Adidas", "asdb2", 94.23, 'a', 1, "DHL", 42.5, true, "Azul", LocalDate.of(2021,4,14), true);
        art3 = new Sapatilhas("Sapatilhas Festivas","Puma", "asd5r5", 44.45, 'n', 2, "fedex", 39.0, false, "Preto", LocalDate.of(2021,1,17), true);
        art4 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "asd123", 321.3, 'c', 0, "UPS", 22.0, true, "Azul", LocalDate.of(2023,8,1), false);
        art5 = new Sapatilhas("Sapatilhas Festivas","Adidas", "kol21", 45.32, 'b', 3, "DHL", 34.0, false, "Amarelo", LocalDate.of(2021,4,29), true);
        art6 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "afa456", 123.34, 'b', 2, "UPS", 54.0, true, "Azul", LocalDate.of(2023,3,12), false);
        art7 = new Sapatilhas("Sapatilhas Casuais","Puma", "456asd", 431.36, 'n', 1, "DHL", 42.0, true, "Vermelho", LocalDate.of(2021,2,1), true);
        art8 = new Sapatilhas("Sapatilhas Casuais","Nike", "lop3123", 55.99, 'c', 1, "fedex", 33.0, false, "Roxo", LocalDate.of(2021,4,8), true);
        art9 = new Sapatilhas("Sapatilhas Festivas","Adidas", "feesa456", 34.64, 'b', 3, "DHL", 43.0, true, "Azul", LocalDate.of(2023,4,19), true);
        art10 = new Sapatilhas("Sapatilhas Festivas","Puma", "gasdfsa5", 58.43, 'c', 1, "fedex", 46.0, true, "Preto", LocalDate.of(2022,4,20), true);
        art11 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "asd566", 63.32, 'n', 2, "UPS", 33.0, false, "Azul", LocalDate.of(2022,10,12), false);
        art12 = new Sapatilhas("Sapatilhas Casuais","Adidas", "hghtyt5", 42.11, 'a', 3, "UPS", 17.0, true, "Preto", LocalDate.of(2020,11,11), false);
        art13 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "hgfhgf31", 12.24, 'a', 2, "fedex", 14.5, true, "Azul", LocalDate.of(2020,4,14), true);
        art14 = new Sapatilhas("Sapatilhas Desportivas","Nike", "bvdc432", 113.34, 'n', 0, "UPS", 55.5, false, "Verde", LocalDate.of(2023,1,9), false);
        art15 = new Sapatilhas("Sapatilhas Casuais","Adidas", "asadad33", 54.64, 'c', 0, "fedex", 23.5, true, "Amarelo", LocalDate.of(2020,12,7), true);
        art16 = new Sapatilhas("Sapatilhas Festivas","NewBalance", "gsda23", 13.34, 'a', 2, "UPS", 22.5, false, "Azul", LocalDate.of(2023,6,2), true);
        art17 = new Sapatilhas("Sapatilhas Festivas","Puma", "gfsdfg67", 154.04, 'n', 2, "fedex", 11.5, true, "Verde", LocalDate.of(2020,4,17), false);
        art18 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "mdias41a", 184.00, 'b', 1, "DHL", 28.5, false, "Azul", LocalDate.of(2023,8,30), false);
        art19 = new Sapatilhas("Sapatilhas Desportivas","Adidas", "dsa52sd", 18.34, 'a', 4, "fedex", 31.0, true, "Amarelo", LocalDate.of(2022,9,30), false);
        art20 = new Sapatilhas("Sapatilhas Casuais","Puma", "gfasda54", 15.99, 'b', 6, "fedex", 23.0, false, "Verde", LocalDate.of(2021,4,1), false);


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
