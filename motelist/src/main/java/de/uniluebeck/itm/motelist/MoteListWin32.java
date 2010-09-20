package de.uniluebeck.itm.motelist;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import de.uniluebeck.itm.tr.util.Logging;
import de.uniluebeck.itm.tr.util.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class MoteListWin32 extends AbstractMoteList {

	private static final Logger log = LoggerFactory.getLogger(MoteListWin32.class);

	private ProcessBuilder pb;

	private File tmpFile;

	public MoteListWin32() throws IOException {
		if (SystemUtils.IS_OS_WINDOWS){
			copyScriptToTmpFile("motelist-win32.exe");
			pb = new ProcessBuilder(tmpFile.getAbsolutePath(), "-c");
		}
		else
			log.warn("This motelist version supports windows only");
	}

	public static void main(String[] args) throws IOException {
		Logging.setLoggingDefaults();

		if (args.length > 1) {
			log.info("Searching for {} device with MAC address: {}", args[0], args[1]);
			log.info("Found: {}", new MoteListWin32().getMotePort(args[0], StringUtils.parseHexOrDecLong(args[1])));
		}
		else {
			log.info("Displaying all connected devices: \n{}", new MoteListWin32().getMoteList());
		}

	}

	private void copyScriptToTmpFile(String motelistResource) throws IOException {

		InputStream from = getClass().getClassLoader().getResourceAsStream(motelistResource);
		FileOutputStream to = null;

		try {

			tmpFile = File.createTempFile("motelist", "");
			to = new FileOutputStream(tmpFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			} // write

		} finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
					log.debug("" + e, e);
				}
			}
			if (to != null) {
				try {
					to.close();
				} catch (IOException e) {
					log.debug("" + e, e);
				}
			}
		}

		tmpFile.setExecutable(true);

	}

	@Override
	public Multimap<String, String> getMoteList() {
		BufferedReader in;
		try {
			Process p = pb.start();
			// Eingabestream holen
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			// parsing
			return parseMoteList(in);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Multimap<String, String> parseMoteList(final BufferedReader in) {

		Multimap<String, String> motes = LinkedListMultimap.create(3);
		String text = "";

		try {

			//in.readLine(); // Information about how we had got this information.

			String reference, port, type;

			while ((text = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(text, ",");
				

				if (tokenizer.countTokens() != 4) {
					log.warn("Unexpected token count of {} in line \"{}\"", tokenizer.countTokens(), text);
				} else {

					reference = tokenizer.nextToken();
					port = tokenizer.nextToken();
					tokenizer.nextToken(); // Skip reference count 
					type = tokenizer.nextToken();

					//lin: XBOW Crossbow Telos Rev.B
					//win32: Crossbow Telos Rev.B
					if (type.contains("Telos")) {
						motes.put("telosb", port.replaceAll("[_[^\\w\\d������\\+\\- ]]", ""));
					}
					
					// ITM Pacemate
					if (type.contains("Pacemate")) {
						motes.put("pacemate", port.replaceAll("[_[^\\w\\d������\\+\\- ]]", ""));
					}
					
					// Coalesenses iSense
					if (type.contains("iSense")) {
						motes.put("isense", port.replaceAll("[_[^\\w\\d������\\+\\- ]]", ""));
					}

				}
			}
		} catch (IOException e) {
			log.error("" + e, e);
		}

		return motes;
	}
}
