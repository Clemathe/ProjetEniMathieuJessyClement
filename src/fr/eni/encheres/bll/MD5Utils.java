package fr.eni.encheres.bll;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	private static final Charset UTF_8 = StandardCharsets.UTF_8;
	private static final String ALGO = "MD5";

	// Utiliser le type binary en base de données
	public static String digest(String data) {
		MessageDigest md;

		try {
			md = MessageDigest.getInstance(ALGO);

			byte[] byteData = md.digest(data.getBytes(UTF_8));
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

	        System.out.println("En format hexa : " + sb.toString());
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
}