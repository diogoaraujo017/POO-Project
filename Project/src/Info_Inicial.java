import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Info_Inicial {
    public static Vintage info() throws Exception{
        Vintage vintage = new Vintage();

    // SAPATILHAS
        Sapatilhas s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20;
        s1 = new Sapatilhas("Sapatilhas Desportivas","Nike", "cd5xo1fp6", 154.34, 'a', 3, "UPS", 43.0, false, "Amarelo", LocalDate.of(2023,6,12));
        s2 = new SapatilhasPremium("Sapatilhas Casuais","Adidas", "wgvupmumg", 94.23, 'a', 1, "DHL", 42.5, true, "Azul", LocalDate.of(2021,4,14));
        s3 = new SapatilhasPremium("Sapatilhas Festivas","Puma", "9g7repv37", 44.45, 'n', 2, "FedEx", 39.0, false, "Preto", LocalDate.of(2021,1,17));
        s4 = new SapatilhasPremium("Sapatilhas Desportivas","NewBalance", "ufp5ylqyu", 321.3, 'c', 0, "USPS", 22.0, true, "Azul", LocalDate.of(2023,8,1));
        s5 = new SapatilhasPremium("Sapatilhas Festivas","Adidas", "l2wrqsrcv", 45.32, 'b', 3, "DHL", 34.0, false, "Amarelo", LocalDate.of(2021,4,29));
        s6 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "nfr950f07", 123.34, 'b', 2, "UPS", 54.0, true, "Azul", LocalDate.of(2023,3,12));
        s7 = new SapatilhasPremium("Sapatilhas Casuais","Puma", "z21za7v4q", 431.36, 'n', 1, "DHL", 42.0, true, "Vermelho", LocalDate.of(2021,2,1));
        s8 = new SapatilhasPremium("Sapatilhas Casuais","Nike", "qwrrjgv6v", 55.99, 'c', 1, "FedEx", 33.0, false, "Roxo", LocalDate.of(2021,4,8));
        s9 = new SapatilhasPremium("Sapatilhas Festivas","Adidas", "4mss7ngdf", 34.64, 'b', 3, "DHL", 43.0, true, "Azul", LocalDate.of(2023,4,19));
        s10 = new SapatilhasPremium("Sapatilhas Festivas","Puma", "l3zmopumj", 58.43, 'c', 1, "FedEx", 46.0, true, "Preto", LocalDate.of(2022,4,20));
        s11 = new Sapatilhas("Sapatilhas Desportivas","NewBalance", "u6upqoiei", 63.32, 'n', 2, "UPS", 33.0, false, "Azul", LocalDate.of(2022,10,12));
        s12 = new Sapatilhas("Sapatilhas Casuais","Adidas", "5ym074315", 42.11, 'a', 3, "CTT", 17.0, true, "Preto", LocalDate.of(2020,11,11));
        s13 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "pok2jtbnz", 12.24, 'a', 2, "FedEx", 14.5, true, "Azul", LocalDate.of(2020,4,14));
        s14 = new Sapatilhas("Sapatilhas Desportivas","Nike", "e86o549ty", 113.34, 'n', 0, "UPS", 55.5, false, "Verde", LocalDate.of(2023,1,9));
        s15 = new Sapatilhas("Sapatilhas Casuais","Adidas", "f9pe5mg8g", 54.64, 'c', 0, "USPS", 23.5, true, "Amarelo", LocalDate.of(2020,12,7));
        s16 = new SapatilhasPremium("Sapatilhas Festivas","NewBalance", "e2unr4d7s", 13.34, 'a', 2, "UPS", 22.5, false, "Azul", LocalDate.of(2023,6,2));
        s17 = new Sapatilhas("Sapatilhas Festivas","Puma", "e544js8of", 154.04, 'n', 2, "FedEx", 11.5, true, "Verde", LocalDate.of(2020,4,17));
        s18 = new Sapatilhas("Sapatilhas Casuais","Decathlon", "cw9nn67a1", 184.00, 'b', 1, "DHL", 28.5, false, "Azul", LocalDate.of(2023,8,30));
        s19 = new Sapatilhas("Sapatilhas Desportivas","Adidas", "dg7v5w65k", 18.34, 'a', 4, "CTT", 31.0, true, "Amarelo", LocalDate.of(2022,9,30));
        s20 = new Sapatilhas("Sapatilhas Casuais","Puma", "jaxh72hoe", 15.99, 'b', 6, "FedEx", 23.0, false, "Verde", LocalDate.of(2021,4,1));


        // TSHIRTS
        Tshirts t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20;
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
        Malas m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20;
        m1 = new MalaPremium("Mala grande", "Rimowa", "das3das3das", 554.08, 'b', 8, "FedEx", 103, 38, 27, "Nylon", 2010);
        m2 = new Malas("Mala grande", "Samsonite", "gafdas3das312", 983.98, 'a', 4, "UPS", 43, 29, 16, "Couro", 2020);
        m3 = new Malas("Mala grande", "Delsey", "o8jl4lziyrj", 1010.10, 'n', 8, "DHL", 66, 29, 21, "Couro", 1963);
        m4 = new Malas("Mala de mão", "Rimowa", "jm2xe5dvn1y", 487.46, 'c', 3, "CTT", 83, 38, 55, "PVC", 2015);
        m5 = new Malas("Mala de mão", "American Tourister", "8080fuwtvgk", 258.82, 'c', 4, "FedEx", 40, 41, 13, "ABS", 1977);
        m6 = new MalaPremium("Mala de viagem", "Samsonite", "ehj4dvtia1i", 471.24, 'c', 7, "USPS", 80, 39, 23, "PVC", 1963);
        m7 = new Malas("Mala executiva", "Samsonite", "wxrozjuwqkr", 464.77, 'b', 9, "DHL", 64, 37, 15, "Nylon", 2022);
        m8 = new Malas("Mala executiva", "Tumi", "x6ielo02wnk", 513.25, 'n', 3, "CTT", 82, 45, 10, "Couro", 1963);
        m9 = new MalaPremium("Mala de rodinhas", "Rimowa", "ni39w92416r", 734.43, 'a', 5, "DHL", 95, 12, 42, "PVC", 1977);
        m10 = new MalaPremium("Mala de rodinhas", "American Tourister", "40jxcbdy2gj", 511.93, 'a', 0, "UPS", 118, 36, 12, "Nylon", 2022);
        m11 = new MalaPremium("Mala de mão", "Rimowa", "vq473dk0sxt", 633.61, 'n', 2, "FedEx", 101, 38, 57, "Couro", 1988);
        m12 = new Malas("Mala executiva", "Tumi", "qq0gqh99gde", 833.13, 'c', 6, "CTT", 48, 44, 54, "Couro", 2010);
        m13 = new Malas("Mala executiva", "American Tourister", "a1znelm5x3z", 788.35, 'c', 5, "Fedex", 69, 46, 28, "PVC", 2022);
        m14 = new Malas("Mala grande", "Delsey", "de8xr9z405s", 1048.01, 'c', 0, "USPS", 25, 43, 52, "Nylon", 2010);
        m15 = new MalaPremium("Mala grande", "Delsey", "q8917kwpkut", 273.01, 'c', 4, "CTT", 107, 55, 36, "Poliéster", 2022);
        m16 = new Malas("Mala grande", "Samsonite", "zg2fpgl1m3w", 110.75, 'a', 8, "FedEx", 74, 24, 47, "Couro", 2010);
        m17 = new MalaPremium("Mala executiva", "American Tourister", "78qyama7mhj", 795.88, 'a', 1, "USPS", 39, 10, 29, "ABS", 2022);
        m18 = new Malas("Mala de mão", "Delsey", "8pmu0qm3knp", 876.98, 'a', 5, "UPS", 111, 29, 20, "PVC", 1999);
        m19 = new Malas("Mala de viagem", "Tumi", "oa5ejl8t5hy", 70.73, 'c', 4, "DHL", 79, 59, 33, "Nylon", 2020);
        m20 = new Malas("Mala grande", "Tumi", "972gpqkshxj", 103.96, 'b', 6, "UPS", 62, 57, 54, "PVC", 2020);


        // ENCOMENDAS
        Encomenda enc1, enc2, enc3, enc4, enc5, enc6, enc7, enc8;
        List<Artigo> conj_artigos1 = new ArrayList<>();
        conj_artigos1.add(s1);
        conj_artigos1.add(s2);

        List<Artigo> conj_artigos2 = new ArrayList<>();
        conj_artigos2.add(s3);
        conj_artigos2.add(s4);
        conj_artigos2.add(s5);
        conj_artigos2.add(s6);
        conj_artigos2.add(s7);

        List<Artigo> conj_artigos3 = new ArrayList<>();
        conj_artigos3.add(s8);

        List<Artigo> conj_artigos4 = new ArrayList<>();
        conj_artigos4.add(s9);
        conj_artigos4.add(s10);
        conj_artigos4.add(s11);
        conj_artigos4.add(m11);
        conj_artigos4.add(m10);
        conj_artigos4.add(t2);
        conj_artigos4.add(t3);

        List<Artigo> conj_artigos5 = new ArrayList<>();
        conj_artigos5.add(s12);
        conj_artigos5.add(s13);
        conj_artigos5.add(m13);

        List<Artigo> conj_artigos6 = new ArrayList<>();
        conj_artigos6.add(m14);
        conj_artigos6.add(s14);
        conj_artigos6.add(t20);

        List<Artigo> conj_artigos7 = new ArrayList<>();
        conj_artigos7.add(m17);
        conj_artigos7.add(s15);
        conj_artigos7.add(t16);

        List<Artigo> conj_artigos8 = new ArrayList<>();
        conj_artigos8.add(m1);
        conj_artigos8.add(m2);

        List<Artigo> conj_artigos9 = new ArrayList<>();
        conj_artigos9.add(t9);
        conj_artigos9.add(t10);
        conj_artigos9.add(t11);
        conj_artigos9.add(t12);

        enc1 = new Encomenda(conj_artigos1,2,'e',LocalDate.of(2022,4,2));
        enc2 = new Encomenda(conj_artigos3,1,'e',LocalDate.of(2022,12,1));
        enc3 = new Encomenda(conj_artigos4,7,'e',LocalDate.of(2022,1,23));
        enc4 = new Encomenda(conj_artigos6,3,'f',LocalDate.of(2020,3,15));
        enc5 = new Encomenda(conj_artigos7,3,'f',LocalDate.of(2022,12,16));
        enc6 = new Encomenda(conj_artigos8,2,'e',LocalDate.of(2022,10,3));
        enc7 = new Encomenda(conj_artigos9,4,'f',LocalDate.of(2021,9,1));

        vintage.addEncomenda(enc1);
        vintage.addEncomenda(enc2);
        vintage.addEncomenda(enc3);
        vintage.addEncomenda(enc4);
        vintage.addEncomenda(enc5);
        vintage.addEncomenda(enc6);
        vintage.addEncomenda(enc7);

        List<Artigo> multiplas_encomendas1 = new ArrayList<>();
        multiplas_encomendas1.addAll(conj_artigos7);
        multiplas_encomendas1.addAll(conj_artigos8);

        //Transportadoras
        Transportadora tr1, tr2, tr3, tr4, tr5;

        List<Artigo> ups = new ArrayList<>();
        ups.add(s1);
        ups.add(s6);
        ups.add(s11);
        ups.add(m10);
        ups.add(s14);
        ups.add(m2);

        List<Artigo> usps = new ArrayList<>();
        usps.add(s4);
        usps.add(m14);
        usps.add(t20);
        usps.add(m17);
        usps.add(s15);
        usps.add(t12);

        List<Artigo> dhl = new ArrayList<>();
        dhl.add(s2);
        dhl.add(s5);
        dhl.add(s7);
        dhl.add(s9);
        dhl.add(t2);

        List<Artigo> fedex = new ArrayList<>();
        fedex.add(s3);
        fedex.add(s8);
        fedex.add(s10);
        fedex.add(m11);
        fedex.add(t3);
        fedex.add(s13);
        fedex.add(m13);
        fedex.add(m1);
        fedex.add(t9);
        fedex.add(t10);

        List<Artigo> ctt = new ArrayList<>();
        ctt.add(s12);
        ctt.add(t16);
        ctt.add(t11);

        tr1= new Transportadora("CTT",1.05,false,ctt);
        tr2= new Transportadora("UPS",1.07,false,ups);
        tr3= new Transportadora("USPS",1.09,true,usps);
        tr4= new Transportadora("DHL",1.03,false,dhl);
        tr5= new Transportadora("FedEx",1.10,false,fedex);

        vintage.addTransportadora(tr1);
        vintage.addTransportadora(tr2);
        vintage.addTransportadora(tr3);
        vintage.addTransportadora(tr4);
        vintage.addTransportadora(tr5);

        // UTILIZADORES
        Utilizadores user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;

        user1 = new Utilizadores("pwzjef5j","João Marcelo", "joao@gmail.com", "Rua das Oliveiras n32", "728842772",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user2 = new Utilizadores("osyxeg0x","Rui António", "rui_antonio@outlook.com", "Rua da Albufeira n420", "112314213",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user3 = new Utilizadores("ldlnzv8v","Ana Santos", "anaSantos@gmail.com", "Rua da Beira n1", "429881734",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user4 = new Utilizadores("dsxcsws1","Maria Silva", "msilva@gmail.com", "Rua do Sol n4", "242532556",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user5 = new Utilizadores("fnsakvs3","António Costa", "costaAntonio@gmail.com", "Rua da Beira n63", "242412525",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user6 = new Utilizadores("jfsakm3f","Mariana Antunes", "mant@gmail.com", "Rua da Seara n32", "352533535",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user7 = new Utilizadores("sdadassd","Clara Manuela", "clara@outlook.com", "Rua da Praça n23", "421433134",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user8 = new Utilizadores("dsdas322","Jorge Silva", "jorgesilva@gmail.com", "Rua da Praça n7", "426633134",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user9 = new Utilizadores("mbksalew","Sara Raquel", "raquell@gmail.com", "Rua do Santo n32", "424244321",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        user10 = new Utilizadores("nsd32424","Renato Guedes", "renatoG@gmail.com", "Rua das Amélias n32", "321223332",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        vintage.addUser(user1);
        vintage.addUser(user2);
        vintage.addUser(user3);
        vintage.addUser(user4);
        vintage.addUser(user5);
        vintage.addUser(user6);
        vintage.addUser(user7);
        vintage.addUser(user8);
        vintage.addUser(user9);
        vintage.addUser(user10);

        Conta c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
        c1=new Conta("pwzjef5j","joao@gmail.com","joaozito");
        c2=new Conta("osyxeg0x","rui_antonio@outlook.com","RAnt");
        c3=new Conta("ldlnzv8v","anaSantos@gmail.com","Santorini");
        c4=new Conta("dsxcsws1","msilva@gmail.com","masil");
        c5=new Conta("fnsakvs3","costaAntonio@gmail.com","PrimeiroMinistro");
        c6=new Conta("jfsakm3f","mant@gmail.com","mantissa");
        c7=new Conta("sdadassd","clara@outlook.com","clouca");
        c8=new Conta("dsdas322","jorgesilva@gmail.com","jorje");
        c9=new Conta("mbksalew","raquell@gmail.com","quelsporting");
        c10=new Conta("nsd32424","renatoG@gmail.com","renaGOD");

        vintage.addConta(c1);
        vintage.addConta(c2);
        vintage.addConta(c3);
        vintage.addConta(c4);
        vintage.addConta(c5);
        vintage.addConta(c6);
        vintage.addConta(c7);
        vintage.addConta(c8);
        vintage.addConta(c9);
        vintage.addConta(c10);

        //emiteFatura(Encomenda enc, Utilizadores vendedor, Utilizadores comprador)
        Fatura fatura = new Fatura();
        fatura.emiteFatura(enc1, user1, user3);
    
        fatura.emiteFatura(enc2, user4, user1);

        fatura.emiteFatura(enc3, user3, user6);

        fatura.emiteFatura(enc4, user8, user5);

        fatura.emiteFatura(enc5, user6, user4);

        fatura.emiteFatura(enc6, user6, user4);

        fatura.emiteFatura(enc7, user9, user1);

        user1.setListaVenda(conj_artigos2);

        user2.setListaVenda(conj_artigos5);



        // System.out.println();
        // System.out.println(s1.toString());
    
        // enc3.devolverEncomenda(enc3);
        // System.out.println();
        // System.out.println(enc1.toString());

        // System.out.println();
        // System.out.println(s1.toString());
        //enc1.devolverEncomenda(enc1);

        System.out.println();
        System.out.println(user1.toString());

        return vintage;

    }
}