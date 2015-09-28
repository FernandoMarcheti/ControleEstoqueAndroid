package br.com.turmaformacaocast.controleestoque.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Product implements Parcelable {

    @JsonIgnore
    private Integer id;
    @JsonProperty("image")
    private String imagem;
    @JsonProperty("name")
    private String nome;
    @JsonProperty("description")
    private String descricao;
    @JsonProperty("stock")
    private Integer quantidade;
    @JsonProperty("minimunStock")
    private Integer quantidadeMinEstoque;
    @JsonProperty("unitaryValue")
    private Double valorUnitario;
    @JsonProperty("id")
    private Long webId;
    @JsonProperty("date")
    private Long date;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
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

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
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
        if (valorUnitario != null ? !valorUnitario.equals(product.valorUnitario) : product.valorUnitario != null)
            return false;
        if (webId != null ? !webId.equals(product.webId) : product.webId != null) return false;
        return !(date != null ? !date.equals(product.date) : product.date != null);

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
        result = 31 * result + (webId != null ? webId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", imagem='" + imagem + '\'' +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", quantidadeMinEstoque=" + quantidadeMinEstoque +
                ", valorUnitario=" + valorUnitario +
                ", webId=" + webId +
                ", date=" + date +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.imagem);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
        dest.writeValue(this.quantidade);
        dest.writeValue(this.quantidadeMinEstoque);
        dest.writeValue(this.valorUnitario);
        dest.writeValue(this.webId);
        dest.writeValue(this.date);
    }

    protected Product(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imagem = in.readString();
        this.nome = in.readString();
        this.descricao = in.readString();
        this.quantidade = (Integer) in.readValue(Integer.class.getClassLoader());
        this.quantidadeMinEstoque = (Integer) in.readValue(Integer.class.getClassLoader());
        this.valorUnitario = (Double) in.readValue(Double.class.getClassLoader());
        this.webId = (Long) in.readValue(Long.class.getClassLoader());
        this.date = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
