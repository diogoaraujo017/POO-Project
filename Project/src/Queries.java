 import java.time.LocalDate;
 import java.util.*;
 import java.util.AbstractMap.SimpleEntry;

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

     public double getLucroUtilizador(Vintage vin, String email, LocalDate antes, LocalDate depois) {
         double r = 0;
         String codigo = vin.getContaByEmail(email).getCodigo();
         Utilizador verificar = vin.getUtilizadorByCodigo(codigo);
         List<Fatura> faturas = verificar.getFaturas();
         for(Fatura f : faturas){

         }
         return r;
     }

     public void topCompradores(Vintage vin, LocalDate depoisde, LocalDate antesde){
         List<Utilizador> users = new ArrayList<>();
         Map<Integer, Utilizador> t = vin.getUtilizadores();
         double soma=0;
         for (Map.Entry<Integer, Utilizador> value : t.entrySet()){
            users.add(value.getValue());
         }
         List<SimpleEntry<Double, Utilizador>> pares= new ArrayList<>();
         for(Utilizador user : users){
             List<Fatura> faturas = user.getFaturas();
             for(Fatura fat : faturas){
                 if(intervalaDatas(depoisde,antesde,fat.getData())) {
                     if (fat.eComprador(fat, user)) {
                        soma+=vin.valFatura(fat);
                     }
                 }
             }
             pares.add(new SimpleEntry<>(soma,user));
         }
         pares.sort((o1, o2) -> Double.compare(o2.getKey(), o1.getKey()));
         System.out.println("Top Compradores entre " + depoisde + " e " + antesde);
         int contador=1;
         for (SimpleEntry<Double, Utilizador> entry : pares) {
             Utilizador user = entry.getValue();
             String nome = user.getNome();
             System.out.print(contador + "°- ");
             System.out.println( nome + " com " +entry.getKey() + " €");
         }
     }

     public void topVendedores(Vintage vin, LocalDate depoisde, LocalDate antesde){
         List<Utilizador> users = new ArrayList<>();
         Map<Integer, Utilizador> t = vin.getUtilizadores();
         double soma=0;
         for (Map.Entry<Integer, Utilizador> value : t.entrySet()){
             users.add(value.getValue());
         }
         List<SimpleEntry<Double, Utilizador>> pares= new ArrayList<>();
         for(Utilizador user : users){
             List<Fatura> faturas = user.getFaturas();
             for(Fatura fat : faturas){
                 if(intervalaDatas(depoisde,antesde,fat.getData())) {
                     if (!fat.eComprador(fat, user)) {
                         soma+=vin.valFatura(fat);
                     }
                 }
             }
             pares.add(new SimpleEntry<>(soma,user));
         }
         pares.sort((o1, o2) -> Double.compare(o2.getKey(), o1.getKey()));
         System.out.println("Top Vendedores entre " + depoisde + " e " + antesde);
         int contador=1;
         for (SimpleEntry<Double, Utilizador> entry : pares) {
             Utilizador user = entry.getValue();
             String nome = user.getNome();
             System.out.print(contador + "°- ");
             System.out.println( nome + " com " +entry.getKey() + " €");
         }
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
