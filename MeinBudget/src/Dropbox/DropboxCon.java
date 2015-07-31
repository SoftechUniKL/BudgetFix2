package Dropbox;

import com.dropbox.core.*;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.util.Locale;
import java.util.Scanner;

public class DropboxCon {
	public static void main(String[] args) {
		/*try {
			DbxAppInfo appInfo = new DbxAppInfo("rhgubiix1gpndk4",
					"s43r8gxhp7bnjyg");
			DbxRequestConfig config = new DbxRequestConfig("BudgetFix", Locale
					.getDefault().toString());
			DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config,
					appInfo);

			Desktop.getDesktop().browse(new URI(webAuth.start()));

			System.out.println("Bitte geben sie ihren Code nun unten ein:");

			Scanner scanner = new scanner(system.in);

			String code = scanner.next();

			System.out.println();
			System.out.println("Dies ist ihr Acces-Token:");

			System.out.println(webAuth.finish(code).accessToken);

			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}


 * throws IOException, DbxException { // Get your app key and secret from the
 * Dropbox developers website. final String APP_KEY = "INSERT_APP_KEY"; final
 * String APP_SECRET = "INSERT_APP_SECRET";
 * 
 * DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
 * 
 * DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
 * Locale.getDefault().toString()); DbxWebAuthNoRedirect webAuth = new
 * DbxWebAuthNoRedirect(config, appInfo);
 * 
 * // Have the user sign in and authorize your app. String authorizeUrl =
 * webAuth.start(); System.out.println("1. Go to: " + authorizeUrl);
 * System.out.println("2. Click \"Allow\" (you might have to log in first)");
 * System.out.println("3. Copy the authorization code."); String code = new
 * BufferedReader(new InputStreamReader(System.in)).readLine().trim();
 * 
 * // This will fail if the user enters an invalid authorization code.
 * DbxAuthFinish authFinish = webAuth.finish(code); String accessToken =
 * authFinish.accessToken;
 * 
 * DbxClient client = new DbxClient(config, accessToken);
 * 
 * System.out.println("Linked account: " + client.getAccountInfo().displayName);
 * 
 * File inputFile = new File("working-draft.txt"); FileInputStream inputStream =
 * new FileInputStream(inputFile); try { DbxEntry.File uploadedFile =
 * client.uploadFile("/magnum-opus.txt", DbxWriteMode.add(), inputFile.length(),
 * inputStream); System.out.println("Uploaded: " + uploadedFile.toString()); }
 * finally { inputStream.close(); }
 * 
 * DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
 * System.out.println("Files in the root path:"); for (DbxEntry child :
 * listing.children) { System.out.println("	" + child.name + ": " +
 * child.toString()); }
 * 
 * FileOutputStream outputStream = new FileOutputStream("magnum-opus.txt"); try
 * { DbxEntry.File downloadedFile = client.getFile("/magnum-opus.txt", null,
 * outputStream); System.out.println("Metadata: " + downloadedFile.toString());
 */
	}
}