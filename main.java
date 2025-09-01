import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class main {

    public static BigInteger convertToDecimal(String baseStr, String valueStr) {
        int base = Integer.parseInt(baseStr);
        return new BigInteger(valueStr, base);
    }

    public static void main(String[] args) {
        String filename = "testcase2.json"; // JSON input file

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject keys = (JSONObject) jsonObject.get("keys");
            long n = (long) keys.get("n");
            long k = (long) keys.get("k");

            List<BigInteger> roots = new ArrayList<>();

            // Read roots from JSON except "keys"
            for (Object keyObj : jsonObject.keySet()) {
                String key = (String) keyObj;
                if (key.equals("keys")) continue;

                JSONObject rootObj = (JSONObject) jsonObject.get(key);
                String baseStr = (String) rootObj.get("base");
                String valueStr = (String) rootObj.get("value");

                BigInteger decimalVal = convertToDecimal(baseStr, valueStr);
                roots.add(decimalVal);
            }

            // Calculate constant term c = r1 * r2 (quadratic roots)
            if (roots.size() >= 2) {
                BigInteger r1 = roots.get(0);
                BigInteger r2 = roots.get(1);

                BigInteger c = r1.multiply(r2);
                System.out.println(c.toString());
            } else {
                System.out.println("Not enough roots to find quadratic polynomial.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
