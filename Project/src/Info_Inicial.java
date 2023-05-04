import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Info_Inicial {
    public static void info() throws Exception{

    // SAPATILHAS
    Artigo art1, art2, art3, art4, art5, art6, art7, art8, art9, art10, art11, art12, art13, art14, art15, art16, art17, art18, art19, art20;

    art1 = new Sapatilhas("Sapatilhas Desportivas","Nike", "cd5xo1fp6", 154.34, 'a', 3, "UPS", 43.0, false, "Amarelo", LocalDate.of(2023,6,12), false);
    art2 = new Sapatilhas("Sapatilhas Casuais","Adidas", "wgvupmumg", 94.23, 'a', 1, "DHL", 42.5, true, "Azul", LocalDate.of(2021,4,14), true);
    art3 = new Sapatilhas("Sapatilhas Festivas","Puma", "9g7repv37", 44.45, 'n', 2, "FedEx", 39.0, false, "Preto", LocalDate.of(2021,1,17), true);
    art4 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "ufp5ylqyu", 321.3, 'c', 0, "USPS", 22.0, true, "Azul", LocalDate.of(2023,8,1), false);
    art5 = new Sapatilhas("Sapatilhas Festivas","Adidas", "l2wrqsrcv", 45.32, 'b', 3, "DHL", 34.0, false, "Amarelo", LocalDate.of(2021,4,29), true);
    art6 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "nfr950f07", 123.34, 'b', 2, "UPS", 54.0, true, "Azul", LocalDate.of(2023,3,12), false);
    art7 = new Sapatilhas("Sapatilhas Casuais","Puma", "z21za7v4q", 431.36, 'n', 1, "DHL", 42.0, true, "Vermelho", LocalDate.of(2021,2,1), true);
    art8 = new Sapatilhas("Sapatilhas Casuais","Nike", "qwrrjgv6v", 55.99, 'c', 1, "FedEx", 33.0, false, "Roxo", LocalDate.of(2021,4,8), true);
    art9 = new Sapatilhas("Sapatilhas Festivas","Adidas", "4mss7ngdf", 34.64, 'b', 3, "DHL", 43.0, true, "Azul", LocalDate.of(2023,4,19), true);
    art10 = new Sapatilhas("Sapatilhas Festivas","Puma", "l3zmopumj", 58.43, 'c', 1, "FedEx", 46.0, true, "Preto", LocalDate.of(2022,4,20), true);
    art11 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "u6upqoiei", 63.32, 'n', 2, "UPS", 33.0, false, "Azul", LocalDate.of(2022,10,12), false);
    art12 = new Sapatilhas("Sapatilhas Casuais","Adidas", "5ym074315", 42.11, 'a', 3, "CTT", 17.0, true, "Preto", LocalDate.of(2020,11,11), false);
    art13 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "pok2jtbnz", 12.24, 'a', 2, "FedEx", 14.5, true, "Azul", LocalDate.of(2020,4,14), true);
    art14 = new Sapatilhas("Sapatilhas Desportivas","Nike", "e86o549ty", 113.34, 'n', 0, "UPS", 55.5, false, "Verde", LocalDate.of(2023,1,9), false);
    art15 = new Sapatilhas("Sapatilhas Casuais","Adidas", "f9pe5mg8g", 54.64, 'c', 0, "USPS", 23.5, true, "Amarelo", LocalDate.of(2020,12,7), true);
    art16 = new Sapatilhas("Sapatilhas Festivas","NewBalance", "e2unr4d7s", 13.34, 'a', 2, "UPS", 22.5, false, "Azul", LocalDate.of(2023,6,2), true);
    art17 = new Sapatilhas("Sapatilhas Festivas","Puma", "e544js8of", 154.04, 'n', 2, "FedEx", 11.5, true, "Verde", LocalDate.of(2020,4,17), false);
    art18 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "cw9nn67a1", 184.00, 'b', 1, "DHL", 28.5, false, "Azul", LocalDate.of(2023,8,30), false);
    art19 = new Sapatilhas("Sapatilhas Desportivas","Adidas", "dg7v5w65k", 18.34, 'a', 4, "CTT", 31.0, true, "Amarelo", LocalDate.of(2022,9,30), false);
    art20 = new Sapatilhas("Sapatilhas Casuais","Puma", "jaxh72hoe", 15.99, 'b', 6, "FedEx", 23.0, false, "Verde", LocalDate.of(2021,4,1), false);


    // TSHIRTS
    Artigo t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
    t1 = new Tshirts("T-shirt preta", "Reebok", "corkhvcko8", 72.05, 'n', 4, "FedEx", 'X', 'R');
    t2 = new Tshirts("T-shirt branca", "Fila", "xw4d1cdm0l", 99.66, 'a', 0, "DHL", 'L', 'P');
    t3 = new Tshirts("T-shirt rosa", "Adidas", "gm0rccojyy", 57.50, 'b', 9, "FedEx", 'X', 'L');
    t4 = new Tshirts("T-shirt branca", "New Balance", "dvj7mtk4fe", 78.99, 'c', 4, "CTT", 'S', 'L');
    t5 = new Tshirts("T-shirt verde", "Fila", "z40ewicx54", 7.82, 'c', 9, "CTT", 'X', 'R');
    t6 = new Tshirts("T-shirt azul", "Converse", "8owzklu2ya", 15.36, 'a', 9, "DHL", 'X', 'P');
    t7 = new Tshirts("T-shirt branca", "Converse", "mdwvlwr2wv", 78.97, 'n', 0, "UPS", 'L', 'L');
    t8 = new Tshirts("T-shirt amarela", "Converse", "h8xu05b6u8", 62.97, 'n', 3, "FedEx", 'M', 'P');
    t9 = new Tshirts("T-shirt roxa", "New Balance", "8gontks1mx", 68.67, 'n', 6, "FedEx", 'X', 'L');
    t10 = new Tshirts("T-shirt roxa", "Under Armour", "uuug14cioo", 92.52, 'n', 3, "FedEx", 'S', 'P');
    t11 = new Tshirts("T-shirt amarela", "Reebok", "9qjuxkz8zk", 69.95, 'a', 0, "CTT", 'S', 'P');
    t12 = new Tshirts("T-shirt preta", "Converse", "70jfsf0cnh", 76.95, 'a', 4, "USPS", 'M', 'L');
    t13 = new Tshirts("T-shirt amarela", "Nike", "dwq5pygvl8", 5.94, 'b', 1, "DHL", 'S', 'L');
    t14 = new Tshirts("T-shirt laranja", "Reebok", "nspqjdmoxe", 93.27, 'b', 6, "UPS", 'S', 'P');
    t15 = new Tshirts("T-shirt amarela", "Converse", "594pohir93", 41.76, 'b', 6, "DHL", 'S', 'R');
    t16 = new Tshirts("T-shirt preta", "New Balance", "0ay74og76l", 96.43, 'c', 4, "CTT", 'M', 'P');
    t17 = new Tshirts("T-shirt vermelha", "New Balance", "ybsh1gwj5d", 70.28, 'c', 9, "USPS", 'X', 'R');
    t18 = new Tshirts("T-shirt laranja", "Asics", "18nq9h95xk", 34.44, 'b', 1, "DHL", 'S', 'L');
    t19 = new Tshirts("T-shirt preta", "Asics", "s19l5rc3t3", 35.75, 'n', 1, "USPS", 'X', 'R');
    t20 = new Tshirts("T-shirt azul", "Nike", "8vk2vfcrgb", 99.19, 'a', 8, "USPS", 'L', 'P');





    // MALAS
    Artigo m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20;
    m1 = new Malas("Mala grande", "Rimowa", "das3das3das", 554.08, 'b', 8, "FedEx", 103, 38, 27, "Nylon", 2010, false);
    m2 = new Malas("Mala grande", "Samsonite", "gafdas3das312", 983.98, 'a', 4, "UPS", 43, 29, 16, "Couro", 2020, false);
    m3 = new Malas("Mala grande", "Delsey", "o8jl4lziyrj", 1010.10, 'n', 8, "DHL", 66, 29, 21, "Couro", 1963, false);
    m4 = new Malas("Mala de mão", "Rimowa", "jm2xe5dvn1y", 487.46, 'c', 3, "CTT", 83, 38, 55, "PVC", 2015, true);
    m5 = new Malas("Mala de mão", "American Tourister", "8080fuwtvgk", 258.82, 'c', 4, "FedEx", 40, 41, 13, "ABS", 1977, true);
    m6 = new Malas("Mala de viagem", "Samsonite", "ehj4dvtia1i", 471.24, 'c', 7, "USPS", 80, 39, 23, "PVC", 1963, true);
    m7 = new Malas("Mala executiva", "Samsonite", "wxrozjuwqkr", 464.77, 'b', 9, "DHL", 64, 37, 15, "Nylon", 2022, true);
    m8 = new Malas("Mala executiva", "Tumi", "x6ielo02wnk", 513.25, 'n', 3, "CTT", 82, 45, 10, "Couro", 1963, true);
    m9 = new Malas("Mala de rodinhas", "Rimowa", "ni39w92416r", 734.43, 'a', 5, "DHL", 95, 12, 42, "PVC", 1977, true);
    m10 = new Malas("Mala de rodinhas", "American Tourister", "40jxcbdy2gj", 511.93, 'a', 0, "UPS", 118, 36, 12, "Nylon", 2022, true);
    m11 = new Malas("Mala de mão", "Rimowa", "vq473dk0sxt", 633.61, 'n', 2, "FedEx", 101, 38, 57, "Couro", 1988, true);
    m12 = new Malas("Mala executiva", "Tumi", "qq0gqh99gde", 833.13, 'c', 6, "CTT", 48, 44, 54, "Couro", 2010, true);
    m13 = new Malas("Mala executiva", "American Tourister", "a1znelm5x3z", 788.35, 'c', 5, "Fedex", 69, 46, 28, "PVC", 2022, false);
    m14 = new Malas("Mala grande", "Delsey", "de8xr9z405s", 1048.01, 'c', 0, "USPS", 25, 43, 52, "Nylon", 2010, false);
    m15 = new Malas("Mala grande", "Delsey", "q8917kwpkut", 273.01, 'c', 4, "CTT", 107, 55, 36, "Poliéster", 2022, true);
    m16 = new Malas("Mala grande", "Samsonite", "zg2fpgl1m3w", 110.75, 'a', 8, "FedEx", 74, 24, 47, "Couro", 2010, false);
    m17 = new Malas("Mala executiva", "American Tourister", "78qyama7mhj", 795.88, 'a', 1, "USPS", 39, 10, 29, "ABS", 2022, true);
    m18 = new Malas("Mala de mão", "Delsey", "8pmu0qm3knp", 876.98, 'a', 5, "UPS", 111, 29, 20, "PVC", 1999, false);
    m19 = new Malas("Mala de viagem", "Tumi", "oa5ejl8t5hy", 70.73, 'c', 4, "DHL", 79, 59, 33, "Nylon", 2020, true);
    m20 = new Malas("Mala grande", "Tumi", "972gpqkshxj", 103.96, 'b', 6, "UPS", 62, 57, 54, "PVC", 2020, true);





    // ENCOMENDAS
    Encomenda enc1, enc2, enc3;
    List<Artigo> conj_artigos1 = new ArrayList<>();
    conj_artigos1.add(art1);
    conj_artigos1.add(art2);

    List<Artigo> conj_artigos2 = new ArrayList<>();
    conj_artigos2.add(art3);
    conj_artigos2.add(art4);
    conj_artigos2.add(art5);
    conj_artigos2.add(art6);
    conj_artigos2.add(art7);

    List<Artigo> conj_artigos3 = new ArrayList<>();
    conj_artigos3.add(art8);

    enc1 = new Encomenda(conj_artigos1,2,'e',LocalDate.of(2022,4,2));
    enc2 = new Encomenda(conj_artigos2,5,'f',LocalDate.of(2023,5,22));
    enc3 = new Encomenda(conj_artigos3,1,'e',LocalDate.of(2022,12,1));
    

    // UTILIZADORES
    Utilizadores user1, user2, user3;

    user1 = new Utilizadores("pwzjef5j","João Marcelo", "joao@gmail.com", "Rua das Oliveiras n32", "728842772",conj_artigos1,conj_artigos2,conj_artigos3, new ArrayList<>());
    user2 = new Utilizadores("osyxeg0x","Rui António", "rui_antonio@outlook.com", "Rua da Albufeira n420", "112314213",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    user3 = new Utilizadores("ldlnzv8v","Ana Santos", "anaSantos@gmail.com", "Rua da Beira n1", "429881734",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), new ArrayList<>());

    // System.out.println();
    // System.out.println(art1.toString());
    
    // enc3.devolverEncomenda(enc3);
    // System.out.println();
    // System.out.println(enc1.toString());

    //     System.out.println();
    // System.out.println(art1.toString());

    //enc1.devolverEncomenda(enc1);
    System.out.println();
    System.out.println(user1.toString());

    }
}
