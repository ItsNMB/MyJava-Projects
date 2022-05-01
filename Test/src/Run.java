import java.net.*;
import java.io.*;


class Run extends Template {

	public Run() {
		try {
			String stringToReverse = URLEncoder.encode("TEST", "UTF-8");

			URL url = new URL("https://dashboard.ngrok.com/cloud-edge/status");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);

			OutputStreamWriter out = new OutputStreamWriter(
			    connection.getOutputStream());
			out.write("string=" + stringToReverse);
			out.close();

			BufferedReader in = new BufferedReader(
			    new InputStreamReader(
			        connection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				System.out.println(decodedString);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
