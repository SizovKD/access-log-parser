import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

enum HTTPMethod {
    Unknown,
    GET,
    POST
}

public class LogEntry {
    final String IPAddr;
    final LocalDateTime time;
    final HTTPMethod method;
    final String path;
    final UserAgent agent;
    final String responseCode;
    final int responseSize;
    final String referer;

    public LogEntry(String string) {
        int startIndex, endIndex;


        endIndex = string.indexOf('-');
        IPAddr = string.substring(0, endIndex).trim();
        System.out.println("IP: " + IPAddr);

        startIndex = string.indexOf('[') + 1;
        endIndex = string.indexOf(']');
        time = LocalDateTime.parse(string.substring(startIndex, endIndex), DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.US));
        System.out.println("Дата: " + time);

        startIndex = string.indexOf('"');
        endIndex = string.indexOf('"', startIndex + 1);
        int savedIndex = endIndex;
        String request = string.substring(startIndex + 1, endIndex);
        System.out.println(request);

        endIndex = request.indexOf(' ');
        String method = request.substring(0, endIndex);
        if (method.equalsIgnoreCase("GET")) {
            this.method = HTTPMethod.GET;
        } else if (method.equalsIgnoreCase("POST")) {
            this.method = HTTPMethod.POST;
        } else {
            this.method = HTTPMethod.Unknown;
        }
        System.out.println("Метод: " + method);
        startIndex = endIndex;
        endIndex = request.indexOf(' ', endIndex + 1);
        path = request.substring(startIndex, endIndex);
        System.out.println("path: " + path);

        startIndex = string.indexOf(' ', savedIndex + 1);
        endIndex = string.indexOf(' ', startIndex + 1);
        responseCode = string.substring(startIndex, endIndex).trim();
        System.out.println("answerCode: " + responseCode);

        startIndex = endIndex + 1;
        endIndex = string.indexOf(' ', startIndex + 1);
        responseSize = Integer.parseInt(string.substring(startIndex, endIndex));
        System.out.println("answerSize: " + responseSize);

        startIndex = string.indexOf('"', endIndex + 1) + 1;
        endIndex = string.indexOf('"', startIndex + 1);
        String referer = string.substring(startIndex, endIndex);
        if (referer.equals("-") || referer.equals("")) {
            this.referer = "";
        } else {
            this.referer = referer;
        }
        System.out.println("referer: " + this.referer);

        startIndex = string.indexOf('"', endIndex + 1);
        endIndex = string.indexOf('"', startIndex + 1);
        agent = new UserAgent(string.substring(startIndex + 1, endIndex));
    }
}