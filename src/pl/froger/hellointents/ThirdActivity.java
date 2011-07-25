package pl.froger.hellointents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends Activity {
	public static final String RESPONSE = "Response";
	private EditText etResponse;
	private Button btnSendResponse;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third);
		etResponse = (EditText) findViewById(R.id.etResponse);
		btnSendResponse = (Button) findViewById(R.id.btnSendResponse);
		btnSendResponse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				prepareResponse();
				finish();
			}
		});
	}
	
	private void prepareResponse() {
		String response = etResponse.getText().toString();
		Intent resultIntent = new Intent();
		resultIntent.putExtra(RESPONSE, response);
		setResult(RESULT_OK, resultIntent);
	}
}