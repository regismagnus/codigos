package com.rtm.readbook.BO;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.rtm.readbook.DAO.livroDAO;
import com.rtm.readbook.Entity.livroEntity;
import com.rtm.readbook.viwer.novoActivity;

public class livroBO {
	
	private livroDAO livroDAO;
	
	public livroBO() {
		
		livroDAO = new livroDAO();
		
	}
	
	public boolean salvar(livroEntity livro, novoActivity novoActivity, SQLiteDatabase db){
		if(validar(livro, novoActivity)){
			
			if(livro.getId()==null){
			    return livroDAO.salvar(livro, db);
			}else{
				return livroDAO.update(livro, db);
			}
		}else{
			return false;
		}
	
		
	}

	private boolean validar(livroEntity livro, novoActivity novoActivity) {

		if(livro.getTitulo()== null || livro.getTitulo().equalsIgnoreCase("")){
			Toast.makeText(novoActivity, "Livro não pode estar vazio", Toast.LENGTH_LONG).show();
			return false;
		}
		if(livro.getNumeroPaginas() == null || livro.getNumeroPaginas() < 0){
			Toast.makeText(novoActivity, "Número de páginas esta incorreto. Somente digite numeros maiores que 0", Toast.LENGTH_LONG).show();
			return false;
		}
		if(livro.getAno() == null || livro.getAno() < 1000){
			Toast.makeText(novoActivity, "Ano não pode ser menor do que 1000", Toast.LENGTH_LONG).show();
			return false;
		}
		
		return true;
	}

}
