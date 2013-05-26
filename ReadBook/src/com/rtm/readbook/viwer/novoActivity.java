package com.rtm.readbook.viwer;

import com.rtm.readbook.R;
import com.rtm.readbook.BO.livroBO;
import com.rtm.readbook.Entity.livroEntity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class novoActivity extends Activity implements OnClickListener{

	private EditText etTitulo;
	private EditText etSubtitulo;
	private EditText etAutor;
	private EditText etEditora;
	private EditText etEdicao;
	private EditText etAno;
	private EditText etNumeroPaginas;
	private EditText etISBN;
	private EditText etDescricao;
	private Button bGravar;
	
	private AlertDialog alerta;
	private SQLiteDatabase db;
	
	private livroEntity livro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.novolivro);
		
		etTitulo = (EditText) findViewById(R.id.etTitulo);
		etSubtitulo = (EditText) findViewById(R.id.etSubtitulo);
		etAutor = (EditText) findViewById(R.id.etAutor);
		etEditora = (EditText) findViewById(R.id.etEditora);
		etEdicao = (EditText) findViewById(R.id.etEdicao);
		etAno = (EditText) findViewById(R.id.etAno);
		etNumeroPaginas = (EditText) findViewById(R.id.etNumeroPaginas);
		etISBN = (EditText) findViewById(R.id.etISBN);
		etDescricao = (EditText) findViewById(R.id.etDescricao);
		bGravar = (Button) findViewById(R.id.bGravar);
		
		Intent it = getIntent();
		
		if(it != null){
			
			Bundle param = it.getExtras();	
	
			if(param!=null){
			
				livro = (livroEntity) param.get("livro");
			
				if(livro.getTitulo() != null && !livro.getTitulo().equalsIgnoreCase(""))
					etTitulo.setText(livro.getTitulo());
					
				if(livro.getSubtitulo() != null && !livro.getSubtitulo().equalsIgnoreCase(""))
					etSubtitulo.setText(livro.getSubtitulo());
				
				if(livro.getAutor() != null && !livro.getAutor().equalsIgnoreCase(""))
					etAutor.setText(livro.getAutor());
				
				if(livro.getEditora() != null && !livro.getEditora().equalsIgnoreCase(""))
					etEditora.setText(livro.getEditora());
				
				if(livro.getEdicao() != null && !livro.getEdicao().equalsIgnoreCase(""))
					etEdicao.setText(livro.getEdicao());
				
				if(livro.getAno() != null)
					etAno.setText(livro.getAno().toString());
				
				if(livro.getNumeroPaginas() != null);
					etNumeroPaginas.setText(livro.getNumeroPaginas().toString());
				
				if(livro.getISBN() != null && !livro.getISBN().equalsIgnoreCase(""))
					etISBN.setText(livro.getISBN());
				
				if(livro.getDescricao() != null && !livro.getDescricao().equalsIgnoreCase(""))
					etDescricao.setText(livro.getDescricao());
				
			}
		}
		
		
		bGravar.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.novo, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.salvar:
                salvar();
                return true;
            case R.id.cancelar:
            	cancelar();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void cancelar() {
		this.finish();
	}

	private void salvar() {
		
		onClick(new View(this));
		
	}

	@Override
	public void onClick(View v) {

		livroBO livroBO = new livroBO();

		if(livro == null){
			livro = new livroEntity();
		}
		
		try{
			livro.setTitulo(etTitulo.getText().toString().substring(0,1).toUpperCase().concat(etTitulo.getText().toString().substring(1)));
			livro.setSubtitulo(etSubtitulo.getText().toString());
			livro.setAutor(etAutor.getText().toString());
			livro.setEditora(etEditora.getText().toString());
			livro.setEdicao(etEdicao.getText().toString());
			livro.setAno(Integer.parseInt(etAno.getText().toString()));
			livro.setNumeroPaginas(Integer.parseInt(etNumeroPaginas.getText().toString()));
			livro.setISBN(etISBN.getText().toString());
			livro.setDescricao(etDescricao.getText().toString());
			
			db = openOrCreateDatabase("book", Context.MODE_PRIVATE, null);
			
			if(livroBO.salvar(livro, this, db)){
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				if(livro.getId() == null){
					builder.setTitle("Salvar...");
					builder.setMessage("Livro gravado com sucesso!");
				}else{
					builder.setTitle("Atualizar...");
					builder.setMessage("Livro atualizado com sucesso!");
				}
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface arg0, int arg1) {
				        	db.close();
				        	novoActivity.this.finish();
				        }
				    });

				alerta = builder.create();
				alerta.show();
				db.close();
				
			}else{
				Toast.makeText(this, "Erro ao gravar livro", Toast.LENGTH_LONG).show();
			}
		}catch(NumberFormatException ex){
			Toast.makeText(this, "Digite somente números em Ano e Número de Páginas", Toast.LENGTH_LONG).show();
		}catch (StringIndexOutOfBoundsException e) {
			Toast.makeText(this, "Por favor, digite um titulo", Toast.LENGTH_LONG).show();
		}
		
	}
}
