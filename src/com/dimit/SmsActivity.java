package com.dimit;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SmsActivity extends Activity {
	private TextView numberText;
	private TextView contentText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		numberText = (TextView) findViewById(R.id.number);
		contentText = (TextView) findViewById(R.id.content);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new ClickOnlistener());
	}

	private final class ClickOnlistener implements OnClickListener {
		public void onClick(View v) {
			String number = numberText.getText().toString();
			String content = contentText.getText().toString();
			if(number == null || content == null) {
				return;
			}
			if(number.length() == 0 || content.length() == 0) {
				return;
			}
			SmsManager manager = SmsManager.getDefault();
			List<String> contents = manager.divideMessage(content);
			for(String con : contents) {
				manager.sendTextMessage(number, null, con, null, null);
			}
			Toast.makeText(SmsActivity.this, R.string.success, Toast.LENGTH_LONG).show();
			
		}
	}
}