package com.dam.example.ejemplousoservicios;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

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

	@Override
	public void onStart(Intent intent, int startId) {
		reproductor.start();
	}	
	
	
	
}
