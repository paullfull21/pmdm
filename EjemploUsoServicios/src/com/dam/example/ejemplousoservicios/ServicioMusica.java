package com.dam.example.ejemplousoservicios;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	@Override
	public void onCreate() {
		reproductor = MediaPlayer.create(this, R.raw.one_direction);
	}

	@Override
	public void onDestroy() {
		reproductor.stop();
	}

	/*
	 * El m�todo onStart es utilizado si trabajamos con
	 * versiones inferiores a la 2.0
	 * 
	@Override
	public void onStart(Intent intent, int startId) {
		reproductor.start();
	}
	*/
	
	/*
	 * El m�todo onStartCommand se defini� a partir de la API 5
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	
	@Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
          Toast.makeText(this,"Servicio arrancado "+ idArranque,Toast.LENGTH_SHORT).show();
          reproductor.start();
          return START_STICKY;
    }
	
	
	
	
	
}