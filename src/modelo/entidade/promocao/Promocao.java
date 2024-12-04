package modelo.entidade.promocao;

import java.util.List;
import modelo.entidade.produto.Produto;

public class Promocao {
    private int idPromocao;
    private float desconto;
    private String Nome;
    private List<Produto> produto;
    private String Termino;
    private String Inicio;
    private String categoria;

    // Construtor sem parâmetros
    public Promocao() {
        // Inicializa a lista de produtos com uma lista vazia, caso não seja fornecida
        this.produto = null;
    }

    // Construtor com parâmetros, como já estava implementado


    // Getters e Setters
    
    
    
    
    public String getNome() {
        return Nome;
    }

    public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getInicio() {
		return Inicio;
	}

	public void setInicio(String inicio) {
		Inicio = inicio;
	}

	public void setNome(String nome) {
        Nome = nome;
    }

    public String getTermino() {
        return Termino;
    }

    public void setTermino(String termino) {
        Termino = termino;
    }

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Promocao other = (Promocao) obj;
        return Float.floatToIntBits(desconto) == Float.floatToIntBits(other.desconto) && idPromocao == other.idPromocao;
    }

	
	
}
