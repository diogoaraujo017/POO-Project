// import java.time.LocalDate;
// import java.util.List;

// public class Queries {
//     public boolean intervalaDatas(LocalDate depois, LocalDate antes, Fatura fat){
//         Encomenda en = fat.getEncomenda();
//         LocalDate teste = en.getData();
//         return teste.isAfter(depois) && teste.isBefore(antes);
//     }
//     public double lucroTransportadora(LocalDate depois, LocalDate antes, Transportadora trans){
//         double r = 0, re = 0;
//         for(Artigo art : trans.getArtigos()){
//             double taxa = trans.getLucro();
//             re = art.getPrecoFinal() - art.getPrecoFinal()/taxa;
//             r += re;
//         }
//         r = Math.round(r * 100.0) / 100.0;
//         return r;
//     }

//     public String transMaiorLucro(LocalDate depois, LocalDate antes, List<Transportadora> todas){
//         double max=0, comp=0;
//         String r=null;
//         for(Transportadora trans : todas){
//             comp=lucroTransportadora(depois,antes,trans);
//             if(comp>max){
//                 max = comp;
//                 comp=0;
//                 r=trans.getNome();
//             }
//             else comp=0;
//         }
//         return r;
//     }

//     public double lucroVintage(LocalDate depois, LocalDate antes, List<Fatura> fats) {
//         double r = 0;
//         for (Fatura fat : fats) {
//             if(intervalaDatas(depois,antes,fat)){
//                 Encomenda en = fat.getEncomenda();
//                 List<Artigo> artigos = en.getArtigos();
//                 for (Artigo art : artigos) {
//                     r += (art.getPrecoFinal() - (art.getPrecoFinal() / 1.03));
//                 }
//             }
//         }
//         return r;
//     }
// }
