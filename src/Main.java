import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные в произвольном порядке (разделенные пробелом):");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            Date birthDate = parseDate(data[3]);
            long phoneNumber = Long.parseLong(data[4]);
            char gender = parseGender(data[5]);

            PersonalData personalData = new PersonalData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            // Запись в файл
            writeToTextFile(personalData);

            System.out.println("Данные успешно записаны в файл.");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.parse(dateStr);
    }

    private static char parseGender(String genderStr) {
        char gender = genderStr.toLowerCase().charAt(0);
        if (gender != 'm' && gender != 'f') {
            throw new IllegalArgumentException("Неверный формат пола");
        }
        return gender;
    }

    private static void writeToTextFile(PersonalData personalData) throws IOException {
        String fileName = "PersonalData.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.format("%s %s %s %s %d %c%n",
                    personalData.lastName,
                    personalData.firstName,
                    personalData.middleName,
                    new SimpleDateFormat("dd.MM.yyyy").format(personalData.birthDate),
                    personalData.phoneNumber,
                    personalData.gender));
        }
    }
}
