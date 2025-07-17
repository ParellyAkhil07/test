import java.util.*;
import java.util.regex.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FindMatches {
    private String matchingType;
    private String inputFile;
    private int rownum = 0;
    private List<String> header = null;
    private Integer emailCol2 = null;
    private Integer emailCol = null;
    private Integer phoneCol2 = null;
    private Integer phoneCol = null;
    private Map<String, Integer> ids = new HashMap<>();
    private int id = 1;

    public FindMatches(String inputFile, String matchingType) throws IOException {
        this.matchingType = matchingType.toLowerCase();
        if (!Arrays.asList("email", "phone", "email_phone").contains(this.matchingType)) {
            System.out.println("Please use a valid matching type: 'email', 'phone', or 'email_phone'.");
            return;
        }

        this.inputFile = inputFile;
        List<String> lines = Files.readAllLines(Paths.get(this.inputFile), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(new StringReader(String.join("\n", lines)));

        // Read header
        String line = reader.readLine();
        if (line != null) {
            this.header = Arrays.asList(line.split(","));
            this.rownum++;
        }

        // Get column numbers for email
        for (int col = 0; col < this.header.size(); col++) {
            if (this.header.get(col).toLowerCase().contains("email2")) {
                this.emailCol2 = col;
            }
            if (this.header.get(col).toLowerCase().equals("email1") || this.header.get(col).toLowerCase().equals("email")) {
                this.emailCol = col;
            }
        }

        // Get column numbers for phone
        for (int col = 0; col < this.header.size(); col++) {
            if (this.header.get(col).toLowerCase().contains("phone2")) {
                this.phoneCol2 = col;
            }
            if (this.header.get(col).toLowerCase().equals("phone1") || this.header.get(col).toLowerCase().equals("phone")) {
                this.phoneCol = col;
            }
        }

        // Write to CSV after all ids are assigned
        writeCsv(reader);
    }

    private int emailMatch(String[] row, Integer minId) {
        Integer rowId = null;
        int iden = (minId != null) ? minId : this.id;

        if (this.emailCol2 != null && !row[this.emailCol2].isEmpty()) {
            String email2 = row[this.emailCol2];
            addKeyToDict(email2, iden);
            rowId = this.ids.get(email2);
        }

        if (this.emailCol != null && !row[this.emailCol].isEmpty()) {
            String email1 = row[this.emailCol];
            addKeyToDict(email1, iden);
            rowId = this.ids.getOrDefault(email1, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private String formatPhone(String[] row, int column) {
        String formattedPhone = row[column].replaceAll("\\D+", "");
        if (formattedPhone.length() > 10) {
            formattedPhone = formattedPhone.substring(1);
        }
        return formattedPhone;
    }

    private int phoneMatch(String[] row, Integer minId) {
        Integer rowId = null;
        int iden = (minId != null) ? minId : this.id;

        if (this.phoneCol2 != null && !row[this.phoneCol2].isEmpty()) {
            String idsKey = formatPhone(row, this.phoneCol2);
            addKeyToDict(idsKey, iden);
            rowId = this.ids.get(idsKey);
        }

        if (this.phoneCol != null && !row[this.phoneCol].isEmpty()) {
            String idsKey = formatPhone(row, this.phoneCol);
            addKeyToDict(idsKey, iden);
            rowId = this.ids.getOrDefault(idsKey, this.id);
        }

        if (rowId == null) {
            rowId = this.id;
        }

        return rowId;
    }

    private void addKeyToDict(String idsKey, int iden) {
        this.ids.putIfAbsent(idsKey, iden);
    }

    private void writeCsv(BufferedReader reader) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("output_file.csv"), StandardCharsets.UTF_8);
        writer.write("id," + String.join(",", this.header) + "\n");

        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            Integer rowId = null;
            Integer emailRowId = null;
            Integer phoneRowId = null;

            if (this.matchingType.equals("email")) {
                Integer minId = null;
                if (this.emailCol2 != null && this.ids.containsKey(row[this.emailCol2])) {
                    minId = this.ids.get(row[this.emailCol2]);
                }
                if (this.emailCol != null && this.ids.containsKey(row[this.emailCol])) {
                    minId = (minId != null) ? Math.min(minId, this.ids.get(row[this.emailCol])) : this.ids.get(row[this.emailCol]);
                }
                rowId = emailMatch(row, minId);
            } else if (this.matchingType.equals("phone")) {
                Integer minId = null;
                if (this.phoneCol2 != null && this.ids.containsKey(formatPhone(row, this.phoneCol2))) {
                    minId = this.ids.get(formatPhone(row, this.phoneCol2));
                }
                if (this.phoneCol != null && this.ids.containsKey(formatPhone(row, this.phoneCol))) {
                    minId = (minId != null) ? Math.min(minId, this.ids.get(formatPhone(row, this.phoneCol))) : this.ids.get(formatPhone(row, this.phoneCol));
                }
                rowId = phoneMatch(row, minId);
            } else if (this.matchingType.equals("email_phone")) {
                Integer minId = null;
                if (this.emailCol2 != null && this.ids.containsKey(row[this.emailCol2])) {
                    minId = this.ids.get(row[this.emailCol2]);
                }
                if (this.emailCol != null && this.ids.containsKey(row[this.emailCol])) {
                    minId = (minId != null) ? Math.min(minId, this.ids.get(row[this.emailCol])) : this.ids.get(row[this.emailCol]);
                }
                if (this.phoneCol2 != null && this.ids.containsKey(formatPhone(row, this.phoneCol2))) {
                    minId = (minId != null) ? Math.min(minId, this.ids.get(formatPhone(row, this.phoneCol2))) : this.ids.get(formatPhone(row, this.phoneCol2));
                }
                if (this.phoneCol != null && this.ids.containsKey(formatPhone(row, this.phoneCol))) {
                    minId = (minId != null) ? Math.min(minId, this.ids.get(formatPhone(row, this.phoneCol))) : this.ids.get(formatPhone(row, this.phoneCol));
                }
                emailRowId = emailMatch(row, minId);
                phoneRowId = phoneMatch(row, minId);
                rowId = (emailRowId < phoneRowId) ? emailRowId : phoneRowId;
            }

            writer.write(rowId + "," + String.join(",", row) + "\n");
            this.id++;
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new FindMatches(args[0], args[1]);
    }