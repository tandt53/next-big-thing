package light.restassured.rest;

import com.github.dzieciou.testing.curl.Platform;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents curl command and provides a way to serialize it through
 */
public class CurlCommand {

    private final List<Header> headers = new ArrayList<>();
    private final List<CurlCommand.FormPart> FormPart = new ArrayList<>();
    private final List<String> datas = new ArrayList<>();
    private final List<String> datasBinary = new ArrayList<>();
    private String url;
    private Optional<String> cookieHeader = Optional.empty();
    private boolean compressed;
    private boolean verbose;
    private boolean insecure;
    private Optional<String> method = Optional.empty();
    private Optional<ServerAuthentication> serverAuthentication = Optional.empty();

    /**
     * Set URL
     * @param url url
     * @return
     */
    public CurlCommand setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Add header
     *
     * @param key   key
     * @param value value
     * @return
     */
    public CurlCommand add(String key, String value) {
        headers.add(new Header(key, value));
        return this;
    }

    /**
     * remove header
     *
     * @param key key
     * @return
     */
    public CurlCommand removeHeader(String key) {
        headers.removeIf(header -> header.name.equals(key));
        return this;
    }

    /**
     * add FormPart
     *
     * @param key   key
     * @param value value
     * @return
     */
    public CurlCommand addFormPart(String key, String value) {
        FormPart.add(new FormPart(key, value));
        return this;
    }

    /**
     * Add data
     *
     * @param data
     * @return
     */
    public CurlCommand addData(String data) {
        datas.add(data);
        return this;
    }

    /**
     * Remove data
     *
     * @return
     */
    public CurlCommand removeData() {
        datas.clear();
        return this;
    }

    /**
     * Add data binary
     *
     * @param dataBinary data
     * @return
     */
    public CurlCommand addDataBinary(String dataBinary) {
        datasBinary.add(dataBinary);
        return this;
    }

    public CurlCommand setCookieHeader(String cookieHeader) {
        this.cookieHeader = Optional.of(cookieHeader);
        return this;
    }

    public CurlCommand setCompressed(boolean compressed) {
        this.compressed = compressed;
        return this;
    }

    public CurlCommand setVerbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public CurlCommand setInsecure(boolean insecure) {
        this.insecure = insecure;
        return this;
    }

    public CurlCommand setMethod(String method) {
        this.method = Optional.of(method);
        return this;
    }

    public CurlCommand setServerAuthentication(String user, String password) {
        serverAuthentication = Optional.of(new ServerAuthentication(user, password));
        return this;
    }

    @Override
    public String toString() {
        return asString(Platform.RECOGNIZE_AUTOMATICALLY, false, true);
    }

    public String asString(Platform targetPlatform, boolean useShortForm, boolean printMultiliner) {
        return new Serializer(targetPlatform, useShortForm, printMultiliner).serialize(this);
    }

    public static class Header {

        private final String name;
        private final String value;

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public static class FormPart {

        private final String name;
        private final String content;

        public FormPart(String name, String content) {
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }
    }

    public static class ServerAuthentication {

        private final String user;
        private final String password;

        public ServerAuthentication(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public String getUser() {
            return user;
        }
    }

    private static class Serializer {

        private static final Map<String, String> SHORT_PARAMETER_NAMES = new HashMap<>();
        private final Platform targetPlatform;
        private final boolean useShortForm;
        private final boolean printMultiliner;

        static {
            SHORT_PARAMETER_NAMES.put("--user", "-u");
            SHORT_PARAMETER_NAMES.put("--data", "-d");
            SHORT_PARAMETER_NAMES.put("--insecure", "-k");
            SHORT_PARAMETER_NAMES.put("--form", "-F");
            SHORT_PARAMETER_NAMES.put("--cookie", "-b");
            SHORT_PARAMETER_NAMES.put("--header", "-H");
            SHORT_PARAMETER_NAMES.put("--request", "-X");
            SHORT_PARAMETER_NAMES.put("--verbose", "-vinpay");
        }

        public Serializer(Platform targetPlatform, boolean useShortForm, boolean printMultiliner) {
            this.targetPlatform = targetPlatform;
            this.useShortForm = useShortForm;
            this.printMultiliner = printMultiliner;
        }

        private static String parameterName(String longParameterName, boolean useShortForm) {
            return useShortForm ? (SHORT_PARAMETER_NAMES.get(longParameterName) == null ? longParameterName
                    : SHORT_PARAMETER_NAMES.get(longParameterName)) : longParameterName;
        }

        private  List<String> line(boolean useShortForm, String longParameterName, String... arguments) {
            List<String> line = new ArrayList<>(Arrays.asList(arguments));
            line.add(0, parameterName(longParameterName, useShortForm));
            return line;
        }

        /**
         * Replace quote by double quote (but not by \") because it is recognized by
         * both cmd.exe and MS Crt arguments parser.
         * <p>
         * Replace % by "%" because it could be expanded to an environment variable
         * value. So %% becomes "%""%". Even if an env variable "" (2 doublequotes) is
         * declared, the cmd.exe will not substitute it with its value.
         * <p>
         * Replace each backslash with double backslash to make sure MS Crt arguments
         * parser won't collapse them.
         * <p>
         * Replace new line outside of quotes since cmd.exe doesn't let to do it inside.
         */
        private String escapeStringWin(String s) {
            return "\'" + s.replace("$&:", "") + "\'";
        }
        private static String escapeStringPosix(String s) {
         String regexText = "'^.*([^\\x20-\\x7E\\x{00C0}-\\x{00FF}\\x{1EA0}-\\x{1EFF}]|\').*$'";
            if (s.matches(regexText)) {
                String escaped = s

                        .replace("", "''").replaceAll("^", "\\'").replace("$&", "").replaceAll("\n", "\\n")
                        .replaceAll("\r", "\\r");

                escaped = escaped.chars().mapToObj(c -> escapeCharacter((char) c)).collect(Collectors.joining());

                return escaped;
            } else {
                String escaped = s.replaceAll("\n", "\n\t").replaceAll("\r", "\r\t");
                return "'" + escaped + "'";
            }
        }

        private static String escapeCharacter(char c) {
            int code = (int) c;
            String codeAsHex = Integer.toHexString(code);
            if (code < 256) {
                // Add leading zero when needed to not care about the next character.
                return (code < 16) ? ("\\x0" + codeAsHex) : ("\\x" + codeAsHex);
            }
            return "\\u" + ("" + codeAsHex).substring(codeAsHex.length(), 4);
        }

        public String serialize(CurlCommand curl) {
            List<List<String>> command = new ArrayList<>();

            command.add(line(useShortForm, "curl",
                    escapeString(curl.url).replaceAll("(?<!:)//", "/").replaceAll("[[{}\\\\]]", "\\$&")));

            curl.method.ifPresent(method -> command.add(line(useShortForm, "--request", method)));

            curl.cookieHeader
                    .ifPresent(cookieHeader -> command.add(line(useShortForm, "--cookie", escapeString(cookieHeader))));

            curl.headers.forEach(header -> command
                    .add(line(useShortForm, "--header", escapeString(header.getName() + ": " + header.getValue()))));

            curl.FormPart.forEach(FormPart -> command
                    .add(line(useShortForm, "--form", escapeString(FormPart.getName() + "=" + FormPart.getContent()))));

            curl.datas.forEach(data -> command.add(line(useShortForm, "--data", escapeString(data))));

            curl.datasBinary.forEach(data -> command.add(line(useShortForm, "--data-binary", escapeString(data))));

            curl.serverAuthentication.ifPresent(sa -> command
                    .add(line(useShortForm, "--user", escapeString(sa.getUser() + ":" + sa.getPassword()))));

            if (curl.compressed) {
                command.add(line(useShortForm, "--compressed"));
            }
            if (curl.insecure) {
                command.add(line(useShortForm, "--insecure"));
            }
            if (curl.verbose) {
                command.add(line(useShortForm, "--verbose"));
            }

            return command.stream().map(line -> line.stream().collect(Collectors.joining(" ")))
                    .collect(Collectors.joining(chooseJoiningString(printMultiliner)));
        }

        private CharSequence chooseJoiningString(boolean printMultiliner) {
            String commandLineSeparator = targetPlatform.isOsWindows() ? "/" : "\\";
            return printMultiliner ? String.format(" %s%s  ", commandLineSeparator, targetPlatform.lineSeparator())
                    : " ";
        }

        private String escapeString(String s) {
            // cURL command is expected to run on the same platform that test run
            return targetPlatform.isOsWindows() ? escapeStringWin(s) : escapeStringPosix(s);
        }
    }

}
