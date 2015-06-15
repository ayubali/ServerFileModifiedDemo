import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IFModifiedFileDemo.java
 *
 * Date Jun 15, 2015 3:45:10 PM
 * 
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/

public class IFModifiedFileDemo {

	/**
	 * @param args
	 * @throws IOException
	 */

	private Date lastModifed = null;

	public static void isServerModifiedSince(Date lastModified, String fileUrl) {
		Date currentLastModofied = getServerLastModified(fileUrl);
		System.out.println("currentLastModofied: " + currentLastModofied);
		System.out.println("lastModified: " + lastModified);

	}

	private static Date getServerLastModified(String fileUrl) {
		Date date = null;
		try {
			URL url = new URL(fileUrl);
			HttpURLConnection httpCon;
			httpCon = (HttpURLConnection) url.openConnection();
			int resCode = httpCon.getResponseCode();
			if (resCode == HttpURLConnection.HTTP_OK) {
				long dateTime = httpCon.getLastModified();
				if (dateTime == 0) {
					System.out.println("No last-modified information.");
				} else {
					date = new Date(dateTime);
					System.out.println("Last-Modified: " + date);
				}
			}else{
				System.out.println("No file to download. Server replied HTTP code: "
						+ resCode);
			}
			

		} catch (IOException e) {

			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] args) throws IOException {
		isServerModifiedSince(
				getServerLastModified("http://prv1.lcchpes.com:8080/application/version.xml?ip=192.168.20.100"),
				"http://prv1.lcchpes.com:8080/application/version.xml?ip=192.168.20.100");
	}

}
