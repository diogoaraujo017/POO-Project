public class Artigo {
    
    private String descricao;
    private String marca;
    private String codigo; // 9 digitos sapatilhas, 10 digitos tshirts, 11 digitos mala,
    private double preco_base;
    private double preco_final;
    private char estado; // n = artigo novo
                         // a = artigo usado (como novo)
                         // b = artigo usado (bom estado)
                         // c = artigo usado (estado médio/mau)
                         // d = devolvido
    private int n_donos;

    private String transportadora;

    public Artigo (){
        this.descricao = "";
        this.marca = "";
        this.codigo = "";
        this.preco_base = 0.00;
        this.preco_final = 0.00;
        this.estado = ' ';
        this.n_donos = 0;
        this.transportadora = null;
    }

    public Artigo (String descricao, String marca, String codigo, double preco_base,
                   char estado, int n_donos, String transportadora){
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.preco_base = preco_base;
        this.preco_final = preco_base;
        this.estado = estado;
        if(estado == 'n') this.n_donos = 0;
        else this.n_donos = n_donos;
        this.transportadora = transportadora;
    }

    public Artigo (Artigo artigo){
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.codigo = artigo.getCodigo();
        this.preco_base = artigo.getPrecoBase();
        this.preco_final = artigo.getPrecoFinal();
        this.estado = artigo.getEstado();
        this.n_donos = artigo.getNDonos();
        this.transportadora = artigo.getTransportadora();
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getMarca(){
        return this.marca;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public double getPrecoBase(){
        return this.preco_base;
    }

    public double getPrecoFinal(){
        return this.preco_final;
    }

    public char getEstado(){
        return this.estado;
    }

    public int getNDonos(){
        return this.n_donos;
    }

    public String getTransportadora() {
        return this.transportadora;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public void setPrecoBase(double preco_base){
        this.preco_base = preco_base;
    }

    public void setPrecoFinal(double preco_final){
        this.preco_final = preco_final;
    }

    public void setEstado(char estado){
        this.estado = estado;
    }

    public void setNDonos(int n_donos){
        this.n_donos = n_donos;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public Artigo clone(){
        return new Artigo(this);
    }


    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Sapatilhas e = (Sapatilhas) obj;
        return  e.getDescricao().equals(this.descricao) &&
                e.getMarca().equals(this.marca) &&
                e.getCodigo().equals(this.codigo) &&
                e.getPrecoBase()==(this.preco_base) &&
                e.getPrecoFinal()==(this.preco_final) &&
                e.getEstado()==(this.estado) &&
                e.getNDonos()==(this.n_donos) &&
                e.getTransportadora()==(this.transportadora);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(this.getDescricao());
        sb.append("; Marca: ").append(this.getMarca());
        sb.append("; Código: ").append(this.getCodigo());
        sb.append("; Preço Base: ").append(this.getPrecoBase());
        sb.append("; Preço Final: ").append(this.getPrecoFinal());
        sb.append("; Estado: ").append(this.getEstado());
        sb.append("; Número de donos: ").append(this.getNDonos());
        sb.append("; Transportadora: ").append(this.getTransportadora());
        sb.append("}");

        return sb.toString();
    }

}