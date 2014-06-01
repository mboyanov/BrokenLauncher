package barcode.scanner;

import java.io.BufferedReader;

import android.net.Uri;
import android.os.Vibrator;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.BarcodeTest.IntentIntegrator;
import com.example.BarcodeTest.IntentResult;

public class BarcoderActivity extends Activity {
	WebView webview;

	MediaPlayer mp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		IntentIntegrator.initiateScan(this);
		
	}

	@Override
	public void onResume(){
		super.onResume();
		IntentIntegrator.initiateScan(this);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE: {
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(
						requestCode, resultCode, data);
				if (scanResult != null) {
					String number = "tel:"+scanResult.getContents();
					Intent intent = new Intent(Intent.ACTION_CALL);
					 intent.setData(Uri.parse(number));
					 startActivity(intent);
				}
			}
			break;
		}
		}
	}

	private String getEvent() {
		SharedPreferences prefs = this.getSharedPreferences("myPrefsKey",
				Context.MODE_PRIVATE);
		return Integer.toString(prefs.getInt("eventid", -1));
	}

	public void show(int i) {

	}

	public void destroy(View v) {

		finish();
	}

	
	public void mainmenu(View v) {
		onCreate(null);
	}

	

}
