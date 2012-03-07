package com.convert.marceloh;

/**
 * Create By Marcelo H
 * marcelohd02@gmail.com
 * */


import com.convert.marceloh.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConvertLinks extends Activity {
	/** Called when the activity is first created. */
    protected int op=0;
	protected EditText link;
	protected Button check,convert;
	protected WebView webV;
	protected RadioGroup opcoes;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		boot_init();
	}
	
	@Override
	public void onStart(){
		super.onStart();
		check.setOnClickListener(onCheckLinks);
		convert.setOnClickListener(onConvertLinks);
		defaultUrlView();	
		
	}
		
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    new MenuInflater(this).inflate(R.menu.menu_opcoes, menu);
	    
	    return(super.onCreateOptionsMenu(menu));
	 }
	  
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	  
		  switch (item.getItemId()) {
		  	case R.id.dev:
		  		defaultMessage("Marcelo H - www.marceloh.com ");
			break;
			
		  	case R.id.sobre:
		  		defaultMessage("Versao 1.0 - ");
		  	break;

		  	default:
			break;
		}
	     	    
	    return(super.onOptionsItemSelected(item));
	}
	  
	protected View.OnClickListener onCheckLinks = new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(link.getText().toString() == null){		
				defaultMessage("Erro - Campo Vazio ");
				
			}else {
				
				webV.loadUrl(link.getText().toString());
				Log.d("","Check Links");
			}
		}
	};

	protected View.OnClickListener onConvertLinks = new View.OnClickListener() {
		public void onClick(View v) {
		
			defaultMessage("Erro - Campo Vazio ");
			op = opcoes.getCheckedRadioButtonId();
			switch (op) {
			case R.convert_rb.rb01:
				loadUrlView(reverse(link.getText().toString()));
				break;
			case R.convert_rb.rb02:
				loadUrlView(convertBase64toString(link.getText().toString()));				
				break;
			case R.convert_rb.rb03:
				loadUrlView(convertBase64toString(link.getText().toString()));	/* alterar metodo para caracters escapados*/
				break;
			case R.convert_rb.rb04:
				defaultMessage("Erro - Ainda Não Disponivel");
					break;
			case R.convert_rb.rb05:
					loadUrlView(convertHexadecimalToString(link.getText().toString()));
				break;

			default:
				defaultMessage("Erro - Escolha uma opção");
				break;
			}
		
		}
	};
	
	protected void boot_init(){
		
		link    = (EditText)findViewById(R.convert_editext.link);
		
		check   = (Button)findViewById(R.convert_buttons.check);
		convert = (Button) findViewById(R.convert_buttons.inverter);
		
		webV 	= (WebView)findViewById(R.convert_webv.webv);
		
		opcoes 	= (RadioGroup)findViewById(R.convert_rgroup.radiogroup);
				
	}
	
	protected void defaultUrlView(){
		
		
		webV.getSettings().setJavaScriptEnabled(true);
		webV.loadUrl("http://www.google.com");
	}
	
	protected String reverse(String url){
		
		StringBuffer rev = new StringBuffer(url);
		rev.reverse();
		
		return rev.toString();
		
	}
	
	protected String convertBase64toString(String url){
		byte[] caracters = Base64.decode(url, 1);
		String realUrl = new String(caracters);
				
		return realUrl;
		
	}
	
	protected String convertHexadecimalToString(String url){
		StringBuilder sbuilder = new StringBuilder();
		StringBuilder sbuilder2 = new StringBuilder();
				
		for (int i = 0;i<url.length()-1;i+=2){
			String saida = url.substring(i,(i+2));
			int decimal  = Integer.parseInt(saida,16);
			sbuilder.append((char)decimal);
			
			sbuilder2.append(decimal);
		}
		return sbuilder.toString();
		

	}
	
	protected void defaultMessage(String msg){
		Toast toast= Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT);
		toast.setGravity(50, 500, 500);
		toast.show();
		Log.v(this.getClass().toString(),msg);
	}
	
	protected void loadUrlView(String url){
		webV.getSettings().setJavaScriptEnabled(true);
		webV.loadUrl(url);
		link.setText(url);
	}

	protected boolean checkNull(String url){
		if(url == null){		
			defaultMessage("Erro - Campo Vazio ");
			return true;
		}else 
			return false; 
	}
	
}