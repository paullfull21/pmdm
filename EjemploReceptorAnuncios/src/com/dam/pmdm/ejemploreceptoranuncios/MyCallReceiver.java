package com.dam.pmdm.ejemploreceptoranuncios;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyCallReceiver extends BroadcastReceiver {
	private static final int notificacionId = 1234;
	String numeroTelefono;

	public MyCallReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		Bundle extras = intent.getExtras();
		if (extras != null) {
			String state = extras.getString(TelephonyManager.EXTRA_STATE);
			if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				numeroTelefono = extras
						.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
				
				NotificationCompat.Builder notificacion = new NotificationCompat.Builder(
						context).setSmallIcon(android.R.drawable.ic_menu_call)
						.setContentTitle("Llamada entrante")
						.setContentText("Tel�fono: "+numeroTelefono);

				NotificationManager mNotificationManager = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);

				mNotificationManager.notify(notificacionId, notificacion.build());
			}
		}
	}
}