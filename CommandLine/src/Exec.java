import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exec {

	private static final String USAGE = "Usage: Exec --cmd=<os_cmd> -param1=<value> -param2=<value>";

	
	private static Matcher controlMyString(String pat, String forFind) //����� ��� �������
	{
		Pattern patt = Pattern.compile(pat);
		Matcher mat = patt.matcher(forFind);
		return mat;
	}
	
	
	public static Map<String, String> decodeToMap(String args[], int variant) {
		Map<String, String> ourResult = new LinkedHashMap<>();

		String cmdSignature = "--cmd=";
		String cmdHelp = "--help";
		String key = null, value = null;
		String errorkey = "Error";
		String errorvalue = "Error input command!!!";

		if (variant == 1) {

			// ������ ������
			if (args.length > 0) {
				if (args[0].startsWith(cmdSignature)) {
					value = args[0].substring(cmdSignature.length(),
							args[0].length());
					key = "command";
					ourResult.put(key, value);
				}

				else if (args[0].startsWith(cmdHelp)) {
					value = USAGE;
					key = "help";
					ourResult.put(key, value);
					return ourResult;
				} else {
					ourResult.put(errorkey, errorvalue);
					return ourResult;
				}

			}

			// ��� ���������� ���� ������ ����� � ����� �����
			for (int i = 1; i <= args.length - 1; i++) {
				String arg = args[i];

				int posMinus = arg.indexOf("-");
				int posEquals = arg.indexOf("=");

				if (posMinus == -1 || posEquals == -1) {
					ourResult.put(errorkey, errorvalue);
					break;
				}

				key = arg.substring(posMinus + 1, posEquals);
				value = arg.substring(posEquals + 1, arg.length());
				ourResult.put(key, value);
			}
			return ourResult;

		}
		// pattern
		else {
			if (args.length > 0) {
				String myPatternCmd = "--[Cc][Mm][Dd]=.+$";
				String myPatternArg = "=.+$";
				String myPatternHelp = "--[Hh][Ee][Ll][Pp]$";
				Matcher m;
				if (controlMyString(myPatternHelp,args[0]).matches())
				{
					ourResult.put("help", USAGE);
					return ourResult;
				}	
				else if (controlMyString(myPatternCmd,args[0]).matches())

				{
					m = controlMyString(myPatternArg, args[0]);
					if (m.find())
					{
						key = "command";
						ourResult.put(key, m.group().replaceAll("=", ""));
					}
					
				}
				else
					{
					ourResult.put(errorkey, errorvalue);
					ourResult.put("help", USAGE);
					return ourResult;
					}
				
				
				String myPatternCmdVerify = "-[a-zA-Z]+=.+$";
				myPatternCmd = "-[a-zA-Z]+=";				
				myPatternArg = "=.+$";
				
				
				for (int i = 1; i < args.length; i++)
				{
					String arg = args[i];
					
					m = controlMyString(myPatternCmdVerify, arg);
					
					if (m.matches()) {
						
						m = controlMyString(myPatternCmd, arg);
						if (m.find()) key=m.group(); else key=errorkey;
						
						m = controlMyString(myPatternArg, arg);
						if (m.find()) value=m.group(); else value=errorvalue;
						
						key=key.replaceAll("-", "").replaceAll("=", "").toLowerCase();
						value=value.replaceAll("=", "").toLowerCase();
						ourResult.put(key, value);
						
						if (key==errorkey) break; 
					}
				}
			}
			else ourResult.put("help", USAGE);

			return ourResult;
		}
	}

	public static void main(String args[]) {

		Map<String, String> ourResult = decodeToMap(args, 1);

		if (ourResult.size() == 0) {
			System.out.println(USAGE);

		} else if (ourResult.containsKey("Error")) {

			System.out.println(ourResult.get("Error"));
		} else {
			for (Entry<String, String> elem : ourResult.entrySet()) {

				System.out.println(elem.getKey() + ":" + elem.getValue());
			}
		}

	}

	@SuppressWarnings("unused")
	private static void executeCmd(String cmd) throws InterruptedException {
		Runtime runtime = Runtime.getRuntime();

		try {
			Process process = runtime
					.exec(new String[] { "cmd.exe", "/c", cmd });
			process.waitFor();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
			}
			bReader.close();
		} catch (IOException e) {
			System.out.println("Command execution failed");
			e.printStackTrace();
		}
	}
}