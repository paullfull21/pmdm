package com.dam.pmdm.broadcastauriculares;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AuricularesReceiver extends BroadcastReceiver {
	public AuricularesReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		Toast.makeText(context, "Auriculares contectados/desconectados", Toast.LENGTH_LONG).show();
	}
}
