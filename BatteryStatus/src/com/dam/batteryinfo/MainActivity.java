package com.dam.batteryinfo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView percent_level;
	private TextView charger_type;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);
        
        // Se est� cargando el telefono?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                             status == BatteryManager.BATTERY_STATUS_FULL;

        // Como se est� realizando la carga: por AC o por USB?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        
        // Actualizo el tipo de cargador que se esta utilizando
        charger_type = (TextView) findViewById(R.id.charger_type);
        if(usbCharge) {
        	charger_type.setText("USB");
        } else if(acCharge) {
        	charger_type.setText("AC");
        } else if(!isCharging) {
        	charger_type.setText("Ninguno");
        }
        
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = (level / (float)scale) * 100;
        
        // Actualizo el porcentaje de nivel de bater�a actual
        // 1. Formateo el string "percent_level_battery", definido en /res/values/strings.xml
        String percent_string = getResources().getString(R.string.percent_level_battery);
        String valor_percent = String.format(percent_string, batteryPct);
        
        // 2. Actualizo el valor del TextView que muestra el porcentaje de carga de la bater�a
        percent_level = (TextView) findViewById(R.id.percent_level);
        percent_level.setText(valor_percent);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
