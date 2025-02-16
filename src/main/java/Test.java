import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Test {

	public static String encode(String key, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		sha256_HMAC.init(secret_key);
		return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}

	public static void main(String[] args) throws Exception {
		String encrypted = encode("nUmPkHfO5qnxgZ9a",
				"path=/v1/briva/j104408/77777/1&verb=GET&token=Bearer OVk4G80sgJFVo4mdVP4m6ccDO2tZ&timestamp=2023-04-06T00:42:52.153Z&body=");
		System.out.println(encrypted);
	}

}
