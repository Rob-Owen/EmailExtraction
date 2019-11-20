import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {

    private final static Pattern EMAIL = Pattern.compile("(?<=^|\\s)(?:[\\w.'_%+-]+)@((?:[a-zA-Z0-9-]+\\.)+[a-zA-Z0-9-]+)(?=$|\\s)");
    private static HashMap<String, Integer> domainCount = new HashMap<>();

    /**
     * Command line arguments:
     * 1 - Occurrence threshold to print
     */
    public static void main(String[] args) throws IOException {
        var file = new File("sample.txt");
        var reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));

        reader.lines().forEach(line -> {
            var m = EMAIL.matcher(line);
            while (m.find()) {
                registerEmailDomain(m.group(1));
            }
        });

        domainCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .filter(e -> e.getValue() > Integer.parseInt(args[0]))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    private static void registerEmailDomain(String domain) {
        if (domainCount.containsKey(domain)) {
            domainCount.put(domain, domainCount.get(domain) + 1);
        } else {
            domainCount.put(domain, 1);
        }
    }
}
