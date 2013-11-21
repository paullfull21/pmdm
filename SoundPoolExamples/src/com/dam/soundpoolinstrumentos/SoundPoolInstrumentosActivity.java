package com.dam.soundpoolinstrumentos;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SoundPoolInstrumentosActivity extends Activity {

	int miAudioA = -1;
	int miAudioG = -1;
	SoundPool sP;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound_pool_instrumentos);

		sP = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);

		Button bA = (Button) findViewById(R.id.buttonA);
		Button bG = (Button) findViewById(R.id.buttonG);

		try {
			AssetManager manager = getAssets();
			AssetFileDescriptor afdA = manager.openFd("building-loop.ogg");
			miAudioA = sP.load(afdA, 1);
			
			AssetFileDescriptor afdB = manager.openFd("slight-crackle.ogg");
			miAudioG = sP.load(afdB, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		bA.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				sP.play(miAudioA, 1.0f, 1.0f, 1, 0, 0.5f);

			}
		});
		
		bG.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				sP.play(miAudioG, 1.0f, 1.0f, 1, 0, 1);

			}
		});
		*/
		

	}
	

	@Override
	protected void onResume() {
		super.onResume();
		sP.play(miAudioA, 1.0f, 1.0f, 1, 0, 0.5f);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater()
				.inflate(R.menu.activity_sound_pool_instrumentos, menu);
		return true;
	}
}
