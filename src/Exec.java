import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exec {

	public static final String USAGE = "Usage: Exec --cmd=send_message --user_id=<id> --message=<message>";
	public static final String pat = "--(.+)=(.+)$";
	static Pattern p = Pattern.compile(pat);

	public static Map<String, String> parsingArgs(String[] args) {
		Map<String, String> map = new LinkedHashMap<>();

		if (args.length > 0) {

			for (String arg : args) {
				if (checkWithRegExp(p, arg)) {
					map.put(retFindsVal(p, arg, 1), retFindsVal(p, arg, 2));
				}
			}
		}

		if (map.containsKey("cmd") && map.containsValue("send_message")) {
			if (!map.containsKey("user_id") || !map.containsKey("message")){
				map.clear();
			map.put("HELP", USAGE);}
		}

		else if (!map.containsKey("cmd") || map.containsKey("help")) {
			map.clear();
			map.put("HELP", USAGE);
		}
		return map;
	}

	public static boolean checkWithRegExp(Pattern p, String arg) {
		Matcher m = p.matcher(arg);
		return m.matches();
	}

	public static String retFindsVal(Pattern p, String arg, int numGroup) {
		Matcher m = p.matcher(arg);
		if (m.find())
			return m.group(numGroup).toLowerCase();
		else
			return "";
	}

	public static void main(String args[]) throws Exception {
		String sendLine = "";
		for (String arg : args) {
			sendLine = sendLine + " " + arg;
		}
		 Send.sendMessage(sendLine);
		//System.out.println(sendLine);
		Map<String, String> map = parsingArgs(args);

		for (Map.Entry<String, String> entry : map.entrySet()) {

			System.out.println(entry.getValue());
		}

	}
}
