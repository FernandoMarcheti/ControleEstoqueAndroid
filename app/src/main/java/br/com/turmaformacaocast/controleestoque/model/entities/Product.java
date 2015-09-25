package br.com.turmaformacaocast.controleestoque.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private Integer id;
    private Image imagem;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer quantidadeMinEstoque;
    private Double valorUnitario;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidadeMinEstoque() {
        return quantidadeMinEstoque;
    }

    public void setQuantidadeMinEstoque(Integer quantidadeMinEstoque) {
        this.quantidadeMinEstoque = quantidadeMinEstoque;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (imagem != null ? !imagem.equals(product.imagem) : product.imagem != null) return false;
        if (nome != null ? !nome.equals(product.nome) : product.nome != null) return false;
        if (descricao != null ? !descricao.equals(product.descricao) : product.descricao != null)
            return false;
        if (quantidade != null ? !quantidade.equals(product.quantidade) : product.quantidade != null)
            return false;
        if (quantidadeMinEstoque != null ? !quantidadeMinEstoque.equals(product.quantidadeMinEstoque) : product.quantidadeMinEstoque != null)
            return false;
        return !(valorUnitario != null ? !valorUnitario.equals(product.valorUnitario) : product.valorUnitario != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imagem != null ? imagem.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (quantidadeMinEstoque != null ? quantidadeMinEstoque.hashCode() : 0);
        result = 31 * result + (valorUnitario != null ? valorUnitario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", imagem=" + imagem +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", quantidadeMinEstoque=" + quantidadeMinEstoque +
                ", valorUnitario=" + valorUnitario +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(this.id == null ? -1 : this.id);
        //dest.writeParcelable(this.imagem,0);
        dest.writeString(this.nome == null ? "" : this.nome);
        dest.writeString(this.descricao == null ? "" : this.descricao);
        dest.writeValue(this.quantidade == null ? -1 : this.quantidade);
        dest.writeValue(this.quantidadeMinEstoque == null ? -1 : this.quantidadeMinEstoque);
        dest.writeValue(this.valorUnitario == null ? -1 : this.valorUnitario);
    }

    protected Product(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        //this.imagem = in.readParcelable(Image.class.getClassLoader());
        this.nome = in.readString();
        this.descricao = in.readString();
        this.quantidade = (Integer) in.readValue(Integer.class.getClassLoader());
        this.quantidadeMinEstoque = (Integer) in.readValue(Integer.class.getClassLoader());
        this.valorUnitario = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
