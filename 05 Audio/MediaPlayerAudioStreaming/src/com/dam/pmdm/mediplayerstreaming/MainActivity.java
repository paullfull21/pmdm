package com.dam.pmdm.mediplayerstreaming;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;

public class MainActivity extends Activity implements OnPreparedListener,
		OnCompletionListener {

	private MediaPlayer mediaPlayer;
	private ProgressDialog progreso;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// M�todo que se llama al pulsar el bot�n play
	public void buttonPlay(View view) throws IllegalArgumentException,
			SecurityException, IllegalStateException, IOException {

		String url = "http://users.telenet.be/dj-crew/tiger.mp3";
		mediaPlayer = new MediaPlayer();
		
		progreso = new ProgressDialog(MainActivity.this);
		
		// como tenemos implementado el m�todo onPrepared en esta clase, ponemos this
		// como par�metro. Dicho m�todo se invocar� cuando la canci�n haya sido terminada
		// de cargar
		mediaPlayer.setOnPreparedListener(this);	 
		mediaPlayer.setOnCompletionListener(this);
		
		// Establecemos que el Stream en el que va a sonar en el tel�fono es el Stream de
		// M�sica.
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(url);
		
		progreso.setTitle("Rocky BSO");
		progreso.setMessage("Entrenando...");
		progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progreso.show();

		mediaPlayer.prepareAsync();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// Elimina de la interfaz de usuario el ProgessDialog
		progreso.dismiss();
		// Cuando acaba de cargar inicia la reproducci�n del audio
		mp.start();
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// Cuando se acaba la reproducci�n ponemos a null el MediaPlayer
		// para evitar que se cree una instancia 
		mp = null;
	}

}