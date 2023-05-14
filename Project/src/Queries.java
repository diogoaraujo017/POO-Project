 import java.time.LocalDate;
 import java.util.*;
 import java.util.AbstractMap.SimpleEntry;

 public class Queries {
     public boolean intervalaDatas(LocalDate depois, LocalDate antes, LocalDate compara){
         return compara.isAfter(depois) && compara.isBefore(antes);
     }
     public String transMaiorLucro(Vintage vin){
         double somatorio=0;
         double maior=0;
         String resposta="ERRO";
         List <Transportadora> todasTrans = vin.getTransportadoras();
         for(Transportadora t :  todasTrans){
             List<Encomenda> todasEncs = vin.getEncomendas();
             for(Encomenda enc : todasEncs){
                 for(Artigo art : enc.getArtigos()) {
                     if(art.getTransportadora().equals(t.getNome())) {
                         somatorio += art.getPrecoFinal();
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
         resposta+=" com um lucro de " + Math.round(maior * 100.0) / 100.0 + "€";
         return resposta;
     }

     public void listaEncomendas(Vintage vin, String email) {
         Conta conta = vin.getContaByEmail(email);
         String code = conta.getCodigo();
         Utilizador user = vin.getUtilizadorByCodigo(code);
         String nome = user.getNome();
         List<Artigo> comprado = user.getProdutosComprou();
         List<Encomenda> todasDoSistema = vin.getEncomendas();
         List<Encomenda> encComprada = new ArrayList<>();
         for (Artigo art : comprado) {
             for (Encomenda enc : todasDoSistema) {
                 List<Artigo> encomendado = enc.getArtigos();
                 for (Artigo artDaEnc : encomendado) {
                     if (art.equals(artDaEnc)) {
                         encComprada.add(enc);
                     }
                 }
             }
         }
         Set<Encomenda> encomendasCompradas = new HashSet<>();
         for (Encomenda encomenda : encComprada) {
             if (!encomendasCompradas.contains(encomenda)) encomendasCompradas.add(encomenda);
         }
         System.out.println("Encomendas feitas por "+nome+" como comprador:\n\n");
         for(Encomenda encomenda : encomendasCompradas){
             System.out.println(encomenda+"\n\n\n");
         }
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
         if(depoisde.equals(LocalDate.of(0,1, 1))) System.out.println("Top Compradores desde o ínicio de funcionamento da Vintage:");
         else System.out.println("Top Compradores entre " + depoisde + " e " + antesde + " :");
         int contador=1;
         for (SimpleEntry<Double, Utilizador> entry : pares) {
             Utilizador user = entry.getValue();
             String nome = user.getNome();
             System.out.print(contador + "°- ");
             System.out.println( nome + " com " +Math.round(entry.getKey() * 100.0) / 100.0 + " €");
             contador++;
         }
     }
     public void topVendedor(Vintage vin, LocalDate depoisde, LocalDate antesde){
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
         for (SimpleEntry<Double, Utilizador> entry : pares){
             Utilizador user = entry.getValue();
             String nome = user.getNome();
             if(depoisde.equals(LocalDate.of(0,1, 1))) System.out.println( "Melhor vendedor desde o inicio da Vintage é " + nome + " com " +Math.round(entry.getKey() * 100.0) / 100.0 + " €");
             else System.out.println( "Melhor vendedor entre " + depoisde + " e " + antesde + " é " + nome + " com " +Math.round(entry.getKey() * 100.0) / 100.0 + " €");
             break;
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
         if(depoisde.equals(LocalDate.of(0,1, 1))) System.out.println("Top Vendedores desde o ínicio de funcionamento da Vintage:");
         else System.out.println("Top Vendedores entre " + depoisde + " e " + antesde+" :");
         int contador=1;
         for (SimpleEntry<Double, Utilizador> entry : pares) {
             Utilizador user = entry.getValue();
             String nome = user.getNome();
             System.out.print(contador + "°- ");
             System.out.println( nome + " com " + Math.round(entry.getKey() * 100.0) / 100.0 + " €");
             contador++;
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
