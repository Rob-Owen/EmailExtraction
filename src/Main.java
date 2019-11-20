import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        var file = new File("sample.txt");
        var br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        var email = Pattern.compile("(\\s|^)([\\w.'_%+-]+)@(([\\w-]+\\.)+[\\w-]+)(\\s|$)");

        String line;
        int startIndex;
        var lineNum = 1;

        while ((line = br.readLine()) != null) {
            var m = email.matcher(line);
            startIndex = 0;
            while (m.find(startIndex)) {
                System.out.print(lineNum + " ");
                System.out.println(m.group(0));
                startIndex = m.end(5);
            }
            lineNum += 1;
        }
    }
}
