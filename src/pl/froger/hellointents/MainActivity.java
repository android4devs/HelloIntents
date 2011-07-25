package pl.froger.hellointents;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int THIRD_ACTIVITY_REQUEST_CODE = 1;
	
	private Button btnOpenActivity;
	private Button btnOpenActivityForResponse;
	private Button btnOpenUrl;
	private Button btnOpenGeo;
	private TextView tvResult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
		btnOpenActivityForResponse = (Button) findViewById(R.id.btnOpenActivityForResponse);
		btnOpenGeo = (Button) findViewById(R.id.btnOpenGeo);
		btnOpenUrl = (Button) findViewById(R.id.btnOpenUrl);
		tvResult = (TextView) findViewById(R.id.tvResult);
		initButtonsOnClickListeners();
	}

	private void initButtonsOnClickListeners() {
		btnOpenActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openSecondActivity();
			}
		});
		btnOpenActivityForResponse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openActivityForResult();
			}
		});
		btnOpenGeo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openGeoUriAddress();
			}
		});
		btnOpenUrl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openUrlAddress();
			}
		});
	}

	private void openSecondActivity() {
		Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
		startActivity(intent);
	}

	private void openActivityForResult() {
		Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
		startActivityForResult(intent, THIRD_ACTIVITY_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (THIRD_ACTIVITY_REQUEST_CODE == requestCode) {
			String response = data.getStringExtra(ThirdActivity.RESPONSE);
			insertResponse(response);
		}
	}

	private void insertResponse(String response) {
		tvResult.setText(response);
	}
	
	private void openUrlAddress() {
		Uri url = Uri.parse("http://www.google.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, url);
		startActivity(intent);
	}
	
	private void openGeoUriAddress() {
		Uri geoUri = Uri.parse("geo:50.07,19.97");
		Intent intent = new Intent(Intent.ACTION_VIEW, geoUri);
		if(isIntentAvailable(intent)) {
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), 
					"Your system hasn't necessary application for this intent", 
					Toast.LENGTH_LONG)
					.show();
		}
	}

	private boolean isIntentAvailable(Intent intent) {
		PackageManager packageManager = getApplicationContext().getPackageManager();
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return resolveInfo.size() > 0;
	}	
}