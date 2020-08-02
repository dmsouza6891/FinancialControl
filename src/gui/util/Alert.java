package gui.util;

import android.content.Context;
import android.widget.Toast;

public class Alert {
	
	public static void showShortAlert(Context context, String message) {
		Toast alert = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		alert.show();
	}
	
}
