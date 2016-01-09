package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;
import br.com.singularideas.labs.knowhub.client.aboriginal.OperationalSystem;

@Service
public class RuntimeService {

	public String getOperationalSystem() {
		return getOperationalSystem(null, false);
	}
	
	public String getOperationalSystem(final String name) {
		return getOperationalSystem(name, false);
	}
		
	public String getOperationalSystemName() {
		return getOperationalSystem(null, true);
	}
	
	public String getOperationalSystem(final String name, final boolean returnName) {
		String osName = (name == null) ? System.getProperty("os.name") : name;
		String os = osName.toLowerCase().substring(0,
				(osName.indexOf(' ') == -1) ? osName.length() - 1 : osName.indexOf(' '));

		if (! isOperationalSystemSupported(os)) {
			throw new AboriginalException(String.format("Operational System [%s] is not supported", os));
		}

		return (returnName) ? osName : os;
	}

	private boolean isOperationalSystemSupported(String os) {
		boolean supported = false;
		
		switch (os) {
		case OperationalSystem.MAC:
		case OperationalSystem.WINDOWS:
			supported = true;
			break;

		default:
			break;
		}

		return supported;
	}

	public String perform(final String command) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(command), "command to perform must be valid");

		String[] cmds = command.split(" ");

		String app = cmds[0];
		String[] args = (cmds.length > 1) ? Arrays.copyOfRange(cmds, 1, cmds.length) : null;

		return perform(app, args);
	}

	public String perform(final String command, final String[] args) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(command), "command to perform must be valid");

		List<String> cmd = new ArrayList<String>();
		cmd.add(command);

		if (args != null && args.length > 0) {
			cmd.addAll(Arrays.asList(args));
		}

		String output = null;

		try {
			Runtime rt = Runtime.getRuntime();
			String[] commands = cmd.toArray(new String[cmd.size()]);
			Process p = rt.exec(commands);

			Thread.sleep(2000);

			if (p.exitValue() != 0) {
				throw new AboriginalException(String.format("Command '%s %s' exit with errors [%s]", command, args,
						readStream(p.getErrorStream())));
			}

			output = readStream(p.getInputStream());
		} catch (Exception e) {
			throw new AboriginalException(e.getMessage(), e);
		}

		return output;
	}

	private String readStream(final InputStream stream) throws IOException {
		StringBuilder sb = new StringBuilder();
		String buffer = null;

		BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
		while ((buffer = inputReader.readLine()) != null) {
			sb.append(buffer);
		}

		return sb.toString();
	}

}
