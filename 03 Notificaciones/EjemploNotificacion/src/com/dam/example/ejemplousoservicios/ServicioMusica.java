package com.dam.example.ejemplousoservicios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class ServicioMusica extends Service {
    private MediaPlayer reproductor;
    private static final int notificacionId = 47576;

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
    public int onStartCommand(Intent intent, int flags, int idArranque) {
         // Comienza la reproducci�n del audio
          reproductor.start();
          
          // Creamos la notificaci�n
          
          // Patr�n de vibraci�n: 0ms sin vibrar, 100ms vibrando
          // Por ejemplo SMS en Morse ser�a: ...--... = 50,50,50,50,50,50,50,100,50,100,50,50,50,50,50,50
          long[] pattern = new long[]{0,50,500,50,500,50,500,100,500,100,500,50,500,50,500,50};
          
          
          NotificationCompat.Builder notificacion =
        	        new NotificationCompat.Builder(this)
          		.setSmallIcon(android.R.drawable.ic_media_play)
        	        .setContentTitle("One direction")
        	        .setContentText("Kiss you!")
        	        // Encender el LED en Rojo con hexadecimal
        	        // 4s = 4000ms encendido y 1000 ms apagado => con esto
        	        // se consigue el parpadeo del LED.
        	        // .setLights(0xFF0099CC, 4000, 1000)
        	        	.setLights(Color.BLUE, 4000, 1000)
        	        	.setVibrate(pattern);
          
        	// Asociamos la actividad que se lanzar� en caso de que hagamos clic en la 
        // notificaci�n. Se hace mediante un intent expl�cito.
        	Intent resultIntent = new Intent(this, ControlServicioActivity.class);

        	// The stack builder object will contain an artificial back stack for the
        	// started Activity.
        	// This ensures that navigating backward from the Activity leads out of
        	// your application to the Home screen.
        	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        	// Adds the back stack for the Intent (but not the Intent itself)
        	stackBuilder.addParentStack(ControlServicioActivity.class);
        	// Adds the Intent that starts the Activity to the top of the stack
        	stackBuilder.addNextIntent(resultIntent);
        	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        	notificacion.setContentIntent(resultPendingIntent);
        	
        	NotificationManager mNotificationManager =
        	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        	
        
        	
        	// notificacionId nos permite identificar de forma �nica esta notificaci�n, para que de ser
        	// necesario pueda ser actualizada.
        	mNotificationManager.notify(notificacionId, notificacion.build());
          
        return START_STICKY;
    }
	
	
	
	
	
}