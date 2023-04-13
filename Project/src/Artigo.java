public class Artigo {
    
    private String descricao;
    private String marca;
    private String codigo;
    private double preco_base;
    private double preco_final;
    private char estado; // n - artigo novo // a - artigo usado (como novo) // b - artigo usado (bom estado) // c - artigo usado (estado médio/mau)
    private int n_donos;

    public Artigo (){
        this.descricao = null;
        this.marca = null;
        this.codigo = null;
        this.preco_base = 0.00;
        this.preco_final = 0.00;
        this.estado = ' ';
        this.n_donos=0;
    }

    public Artigo (String descricao, String marca, String codigo, double preco_base, double preco_final, char estado, int n_donos){
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = codigo;
        this.preco_base = preco_base;
        this.preco_final = preco_final;
        this.estado = estado;
        this.n_donos = n_donos;
    }

    public Artigo (Artigo artigo){
        this.descricao = artigo.getDescricao();
        this.marca = artigo.getMarca();
        this.codigo = artigo.getCodigo();
        this.preco_base = artigo.getPrecoBase();
        this.preco_final = artigo.getPrecoFinal();
        this.estado = artigo.getEstado();
        this.n_donos = artigo.getNDonos();
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

    
    public void setDescricao(String descricao){
        this.descricao=descricao;
    }

    public void setMarca(String marca){
        this.marca=marca;
    }

    public void setCodigo(String codigo){
        this.codigo=codigo;
    }

    public void setPrecoBase(double preco_base){
        this.preco_base=preco_base;
    }

    public void setPrecoFinal(double preco_final){
        this.preco_final=preco_final;
    }

    public void setEstado(char estado){
        this.estado=estado;
    }

    public void setNDonos(int n_donos){
        this.n_donos=n_donos;
    }


    public boolean equals(Object obj){

        if(obj==this) return true;

        if(obj==null || obj.getClass()!=this.getClass()) return false;

        Sapatilhas e = (Sapatilhas) obj;
        return e.getDescricao().equals(this.descricao) &&
               e.getMarca().equals(this.marca) &&
               e.getCodigo().equals(this.codigo) &&
               e.getPrecoBase()==(this.preco_base) &&
               e.getPrecoFinal()==(this.preco_final) &&
               e.getEstado()==(this.estado) &&
               e.getNDonos()==(this.n_donos);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo: {");
        sb.append("Descrição: ").append(this.descricao);
        sb.append("; Marca: ").append(this.marca);
        sb.append("; Código: ").append(this.codigo);
        sb.append("; Preço Base: ").append(this.preco_base);
        sb.append("; Preço Final: ").append(this.preco_final);
        sb.append("; Estado: ").append(this.estado);
        sb.append("; Número de donos: ").append(this.n_donos);
        sb.append("}");

        return sb.toString();
    }

}
