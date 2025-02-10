package modelo.entidade.venda;

import modelo.entidade.pessoa.funcionario.Funcionario;
import modelo.entidade.item.Item;
import java.util.List;
import java.util.Date;

public class Venda {
	private String funcionarioCpf;
    private String ClienteCpf;
    private float precoTotal;
    private List<Item> itens;
    private Date dataVenda; // add data da venda para histórico
    private int codigoVenda; // add código da venda para consulta
    private String dataVendaFormatada;
 

    

	

	public String getFuncionarioCpf() {
		return funcionarioCpf;
	}

	public void setFuncionarioCpf(String funcionarioCpf) {
		this.funcionarioCpf = funcionarioCpf;
	}

	public String getDataVendaFormatada() {
		return dataVendaFormatada;
	}

	public void setDataVendaFormatada(String dataVendaFormatada) {
		this.dataVendaFormatada = dataVendaFormatada;
	}

	public String getClienteCpf() {
		return ClienteCpf;
	}

	public void setClienteCpf(String clienteCpf) {
		ClienteCpf = clienteCpf;
	}

	public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }
}
