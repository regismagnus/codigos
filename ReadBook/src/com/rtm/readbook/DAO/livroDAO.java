package com.rtm.readbook.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.rtm.readbook.Entity.livroEntity;
import com.rtm.readbook.util.createDB;

public class livroDAO {
	
	ContentValues valores;
	
	public livroDAO() {
			valores = new ContentValues();
	}
	
	public boolean salvar(livroEntity livro, SQLiteDatabase db) {
		
		valores.put("titulo", livro.getTitulo());
		if(livro.getSubtitulo() != null && !livro.getSubtitulo().equalsIgnoreCase(""))
			valores.put("subtitulo", livro.getSubtitulo());
		if(livro.getAutor() != null && !livro.getAutor().equalsIgnoreCase(""))
			valores.put("autor", livro.getAutor());
		if(livro.getEditora() != null && !livro.getEditora().equalsIgnoreCase(""))
			valores.put("editora", livro.getEditora());
		if(livro.getEdicao() != null && !livro.getEdicao().equalsIgnoreCase(""))
			valores.put("edicao", livro.getEdicao());
		if(livro.getAno() != null && livro.getAno()>=0)
			valores.put("ano", livro.getAno());
		if(livro.getNumeroPaginas() != null && livro.getNumeroPaginas()>=0)
			valores.put("numeroPaginas", livro.getNumeroPaginas());
		if(livro.getISBN() != null && !livro.getISBN().equalsIgnoreCase(""))
			valores.put("isbn", livro.getISBN());
		if(livro.getDescricao() != null && !livro.getDescricao().equalsIgnoreCase(""))
			valores.put("descricao", livro.getDescricao());
		try{
			
			Long h = db.insert("livro", null, valores);
			
			if(h==-1){
				createDB create = new createDB();
				create.createTableLivro(db);
				create.createTableLendoLivro(db);
				
				h = db.insert("livro", null, valores);
				
				if(h==-1){
					db.close();
					return false;
				}
			}
			
			db.close();
			
			return true;
		}catch (SQLiteException ex) {
			Log.i("Erro DAO", "Erro ocorrido " + ex.toString());
			createDB create = new createDB();
			create.createTableLivro(db);
			create.createTableLendoLivro(db);
			db.insert("livro", null, valores);
			db.close();
			return true;
		}catch (Exception ex){
			Log.i("Erro DAO", "Erro ocorrido " + ex.toString());
			return false;
		}
	}

	public boolean update(livroEntity livro, SQLiteDatabase db) {
		
		valores.put("titulo", livro.getTitulo());
		if(livro.getSubtitulo() != null && !livro.getSubtitulo().equalsIgnoreCase(""))
			valores.put("subtitulo", livro.getSubtitulo());
		if(livro.getAutor() != null && !livro.getAutor().equalsIgnoreCase(""))
			valores.put("autor", livro.getAutor());
		if(livro.getEditora() != null && !livro.getEditora().equalsIgnoreCase(""))
			valores.put("editora", livro.getEditora());
		if(livro.getEdicao() != null && !livro.getEdicao().equalsIgnoreCase(""))
			valores.put("edicao", livro.getEdicao());
		if(livro.getAno() != null && livro.getAno()>=0)
			valores.put("ano", livro.getAno());
		if(livro.getNumeroPaginas() != null && livro.getNumeroPaginas()>=0)
			valores.put("numeroPaginas", livro.getNumeroPaginas());
		if(livro.getISBN() != null && !livro.getISBN().equalsIgnoreCase(""))
			valores.put("isbn", livro.getISBN());
		if(livro.getDescricao() != null && !livro.getDescricao().equalsIgnoreCase(""))
			valores.put("descricao", livro.getDescricao());
		try{
			
			db.update("livro", valores, "id =" + livro.getId(), null);
			
			db.close();
			return true;
		}catch (SQLiteException ex) {
			Log.i("Erro DAO", "Erro ocorrido " + ex.toString());
			return false;
		}catch (Exception ex){
			Log.i("Erro DAO", "Erro ocorrido " + ex.toString());
			return false;
		}
	}


}
