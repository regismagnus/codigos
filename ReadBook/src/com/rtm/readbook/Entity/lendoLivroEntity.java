package com.rtm.readbook.Entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class lendoLivroEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private livroEntity bookEntity;
	private Integer paginaAtual;
	private Date ultimaLeitura;
	
	public lendoLivroEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public livroEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(livroEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public Integer getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public Date getUltimaLeitura() {
		return ultimaLeitura;
	}

	public void setUltimaLeitura(Date ultimaLeitura) {
		this.ultimaLeitura = ultimaLeitura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		lendoLivroEntity other = (lendoLivroEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bookEntity.getTitulo() + " Atualmente lendo pag: "
				+ paginaAtual + " Ultima leitura: " + new SimpleDateFormat("dd/MM/yyyy").format(ultimaLeitura);
	}
	
	
}
