package zpi.mobile.gamepad;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GamepadActivity extends Activity {

	Button kolko, krzyzyk, kwadrat, trojkat;
	ImageView p1, p2, p3, p4, bateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamepad);

		kolko = (Button) findViewById(R.id.kolko);
		krzyzyk = (Button) findViewById(R.id.krzyzyk);
		kwadrat = (Button) findViewById(R.id.kwadrat);
		trojkat = (Button) findViewById(R.id.trojkat);

		kolko.setEnabled(false);
		krzyzyk.setEnabled(false);
		kwadrat.setEnabled(false);
		trojkat.setEnabled(false);

		bateria = (ImageView) findViewById(R.id.bateria);
		poziomBaterii();

		p1 = (ImageView) findViewById(R.id.p1);
		p2 = (ImageView) findViewById(R.id.p2);
		p3 = (ImageView) findViewById(R.id.p3);
		p4 = (ImageView) findViewById(R.id.p4);

		kolko.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				wyslijKlawisz("kolko");
				p1.setImageResource(R.drawable.player_on);
				p2.setImageResource(R.drawable.player_off);
				p3.setImageResource(R.drawable.player_off);
				p4.setImageResource(R.drawable.player_off);
			}
		});
		krzyzyk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				wyslijKlawisz("krzyzyk");
				p2.setImageResource(R.drawable.player_on);
				p1.setImageResource(R.drawable.player_off);
				p3.setImageResource(R.drawable.player_off);
				p4.setImageResource(R.drawable.player_off);
			}
		});
		kwadrat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				wyslijKlawisz("kwadrat");
				p3.setImageResource(R.drawable.player_on);
				p2.setImageResource(R.drawable.player_off);
				p1.setImageResource(R.drawable.player_off);
				p4.setImageResource(R.drawable.player_off);
			}
		});
		trojkat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				wyslijKlawisz("trojkat");
				p4.setImageResource(R.drawable.player_on);
				p2.setImageResource(R.drawable.player_off);
				p3.setImageResource(R.drawable.player_off);
				p1.setImageResource(R.drawable.player_off);
			}
		});
	}

	protected void wyslijKlawisz(String przycisk) {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gamepad, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.polacz_z_serwerem:
			kolko.setEnabled(true);
			krzyzyk.setEnabled(true);
			kwadrat.setEnabled(true);
			trojkat.setEnabled(true);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void poziomBaterii() {
		BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				context.unregisterReceiver(this);
				int rawlevel = intent.getIntExtra("level", -1);
				int scale = intent.getIntExtra("scale", -1);
				int level = -1;
				if (rawlevel >= 0 && scale > 0) {
					level = (rawlevel * 100) / scale;
				}
				if (level >= 80)
					bateria.setImageResource(R.drawable.b5);
				else if (level >= 60 && level < 80)
					bateria.setImageResource(R.drawable.b4);
				else if (level >= 40 && level < 60)
					bateria.setImageResource(R.drawable.b3);
				else if (level >= 20 && level < 40)
					bateria.setImageResource(R.drawable.b2);
				else if (level < 20)
					bateria.setImageResource(R.drawable.b1);
			}
		};
		IntentFilter batteryLevelFilter = new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryLevelReceiver, batteryLevelFilter);
	}
}