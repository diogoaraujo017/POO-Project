 import jdk.jshell.execution.Util;

 import java.time.LocalDate;
 import java.util.*;

 public class Queries {
     public boolean intervalaDatas(LocalDate depois, LocalDate antes, LocalDate compara){
         return compara.isAfter(depois) && compara.isBefore(antes);
     }
     public double lucroTransportadora(LocalDate depois, LocalDate antes, Transportadora trans, Vintage vin){
         double r = 0, re = 0;
         for(Artigo art : trans.getArtigos()){
             double taxa = trans.getLucro();
             re = art.getPrecoFinal() - art.getPrecoFinal()/taxa;
             r += re;
         }
         r = Math.round(r * 100.0) / 100.0;
         return r;
     }

     public String transMaiorLucro(Vintage vin){
         double somatorio=0;
         double maior=0;
         String resposta="ERRO";
         List <Transportadora> todasTrans = vin.getTransportadoras();
         for(Transportadora t :  todasTrans){
             List<Encomenda> todasEncs = vin.getEncomendas();
             for(Encomenda enc : todasEncs){
                 char como = enc.getEstado();
                 if (como == 'f' || como == 'e') {
                     List<Artigo> arts = enc.getArtigos();
                     for (Artigo art : arts) {
                         String nome = art.getTransportadora();
                         if (t.getNome().equals(nome)) {
                             double oquesomar = 0;
                             if (art.getEstado() == 'n') {
                                 oquesomar = art.getPrecoFinal() - 0.5;
                             } else oquesomar = art.getPrecoFinal() - 0.25;
                             somatorio+=oquesomar/t.getLucro();
                         }
                     }
                 }
             }
             if(somatorio>maior){
                 maior = somatorio;
                 somatorio=0;
                 resposta=t.getNome();
             }
             else{
                 somatorio=0;
             }
         }
         return resposta;
     }

     public double getLucroUtilizador(Vintage vin, String email) {
         double r = 0;
         String codigo = vin.getContaByEmail(email).getCodigo();
         Utilizador verificar = vin.getUtilizadorByCodigo(codigo);
         List<Artigo> vendidos = verificar.getProdutosVendidos();
         for (Artigo art : vendidos) {
            r+=art.getPrecoFinal();
         }
         return r;
     }

     public void topVendedores(Vintage vin){
         List<Utilizador> users = new ArrayList<>();
         Map<Integer, Utilizador> t = vin.getUtilizadores();
         for (Map.Entry<Integer, Utilizador> value : t.entrySet()){
            users.add(value.getValue());
         }
         users.sort(Comparator.comparingDouble(Utilizador::getValorTotalVendas).reversed());

     }

     public double lucroVintage(Vintage vin) {
         double r = 0;
         List<Encomenda> todasEncs = vin.getEncomendas();
         for(Encomenda enc : todasEncs) {
             char como = enc.getEstado();
             if (como == 'f' || como == 'e') {
                 List<Artigo> arts = enc.getArtigos();
                 for (Artigo art : arts) {
                     if (art.getEstado() == 'n') r += 0.5;
                     else r += 0.25;
                 }
             }
         }
         return r;
     }
 }
