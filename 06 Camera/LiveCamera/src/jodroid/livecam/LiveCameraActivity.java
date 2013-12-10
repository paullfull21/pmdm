package jodroid.livecam;

import android.app.Activity;
import android.os.Bundle;

public class LiveCameraActivity extends Activity {

	private Preview mPreview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);

		mPreview = new Preview(this);
		setContentView(mPreview);

//		LinearLayout ll = (LinearLayout)findViewById(R.id.linlay);
//		ll.addView(mPreview);
	}
}