package demo.bookinglocker.utilities;

//import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomPasswordGenerator {
        public static String generateRandomPassword() {
        // // Generate UUID
        // UUID uuid = UUID.randomUUID();

        // // Convert UUID to string
        // String uuidString = uuid.toString();

        // // Remove dashes from UUID string
        // uuidString = uuidString.replaceAll("-", "");

        // // Limit the length of the UUID string
        // uuidString = uuidString.substring(0, 8);

        // // Convert string to lowercase
        // uuidString = uuidString.toLowerCase();

        // // Replace some characters to make it more complex
        // uuidString = uuidString.replace('a', '@');
        // uuidString = uuidString.replace('b', '!');
        // uuidString = uuidString.replace('c', '#');
        // // Add more character replacements as needed

        // return uuidString;

        int length = 8;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }
}
