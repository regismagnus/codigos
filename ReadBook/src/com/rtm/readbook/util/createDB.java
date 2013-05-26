package com.rtm.readbook.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
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
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class createDB {
	
	//Nome tabelas
	private static final String TABLE_LIVRO_NOME = "livro";
	private static final String TABLE_LENDO_LIVRO_NOME = "lendoLivro";
	
	//Base de dados do livro
	private static final String ID_LIVRO = "id";
	private static final String TITULO = "titulo";
	private static final String SUBTITULO = "subtitulo";
	private static final String AUTOR = "autor";
	private static final String EDITORA = "editora";
	private static final String EDICAO = "edicao";
	private static final String ANO = "ano";
	private static final String NUMEROPAGINAS = "numeropaginas";
	private static final String ISBN = "isbn";
	private static final String DESCRICAO = "descricao";
	
	//Base de dados Lendo livro
	private static final String ID_LENDO_LIVRO = "id";
	private static final String PAGINAATUAL = "paginaAtual";
	private static final String DATA = "ultimaLeitura";
	
	//CREATE TABLE LIVRO
	private static final String TABLE_LIVRO = "CREATE TABLE " + TABLE_LIVRO_NOME + "( "
											  + ID_LIVRO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
											  + TITULO + " TEXT NOT NULL, "
											  + SUBTITULO + " TEXT NULL, "
											  + AUTOR + " TEXT NULL, "
											  + EDITORA + " TEXT NULL, "
											  + EDICAO + " TEXT NULL, "
											  + ANO + " INTEGER NULL, "
											  + NUMEROPAGINAS + " INTEGER NOT NULL, "
											  + ISBN + " TEXT NULL, "
											  + DESCRICAO + " TEXT NULL )";

	//CREATE TABLE LENDO LIVRO
	private static final String TABLE_LENDO_LIVRO = "CREATE TABLE " + TABLE_LENDO_LIVRO_NOME + "( "
											  	    + ID_LENDO_LIVRO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
											  	    + "IdLivro INTEGER NOT NULL, "
											  	    + PAGINAATUAL + " INTEGER NOT NULL, "
											  	    + DATA + " DATE NOT NULL, "
											  	    + "FOREIGN KEY (idLivro) REFERENCES livro (id) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	public createDB() {
		
	}
	
	public boolean createTableLivro(SQLiteDatabase db){
		
		try{
		
		db.execSQL(TABLE_LIVRO);
		
		return true;
		
		}catch (SQLException e) {
			return false;
		}
		
	}
	
	public boolean createTableLendoLivro(SQLiteDatabase db){
		
		try{
		
		db.execSQL(TABLE_LENDO_LIVRO);
		
		return true;
		
		}catch (SQLException e) {
			return false;
		}
		
	}
	
}
