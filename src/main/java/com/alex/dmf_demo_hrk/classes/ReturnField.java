package com.alex.dmf_demo_hrk.classes;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import org.json.JSONObject;


public class ReturnField {

    /**
     * Converts the output of the table originally in the format of Seq<Seq<String>> to the format
     * of a valid Simplified CSV file.
     *
     * @param table is a valid table that is rectangular and has at least one column
     */
    private static void toSimplifiedCSV(Seq<Seq<String>> table) {
        for (Seq<String> sequence : table) {
            String line = "";
            String tailData = sequence.get(sequence.size() - 1);
            for (String str : sequence) {
                if (str.equals(tailData)) {
                    line += str;
                } else {
                    line += str + ",";
                }
            }
            System.out.println(line);
        }
    }

    /**
     * Assert that the table satisfies the preconditions that the table is a rectangular table with
     * at least 1 column.
     */
    private static void assertTableInvariants(Seq<Seq<String>> table) throws AssertionError {
        assert table.size() > 0; // Table must have at least 1 column

        int lineSize = table.get(0).size();

        for (Seq<String> line : table) {
            assert (line.size() == lineSize); // Table must be rectangular
            if (line.size() != lineSize) {
                throw new AssertionError();
            }
        }
    }

    /***
     * Load a table from a Simplified CSV file and return a row-major list-of-lists representation.
     * The CSV file is assumed to be in the platform's default encoding. Throws an IOException if
     * there is a problem reading the file.
     *
     * @param file is a String representation of the file name
     * @throws IOException
     */
    public static Seq<Seq<String>> csvToList(String file) throws IOException {

        Seq<Seq<String>> csvList = new LinkedSeq<>();

        try (Reader in = new FileReader(file)) {
            Scanner lines = new Scanner(in);
            while (lines.hasNextLine()) {
                Seq<String> words = new LinkedSeq<>();
                String line = lines.nextLine();
                String[] arrLine = line.split(",", -1);
                for (String str : arrLine) {
                    String word = str;
                    words.append(word);
                }

                csvList.append(words);
            }
        }

        return csvList;
    }


    /**
     * Return the Seq<String> object given an individual's SSN from the csv file. Requires
     * String file (csv file name) and string ssn.
     * */
    public static Seq<String> ssnInfo (String file, String ssn) throws IOException {
        Seq<Seq<String>> csvList = csvToList(file);

        ssn = "\"" + ssn + "\"";
        Seq<String> indvInfo = new LinkedSeq<>();

        for (Seq<String> csvListElem : csvList) {
            if (csvListElem.get(1).equals(ssn)) {
                indvInfo = csvListElem;
            }
        }

        if (indvInfo.size() == 0) {
            return null;
        }

        return indvInfo;
    }

    /**
     * Return JSON information of Seq<Seq<String>> object. Requires Seq<String> object.
     * */
    public static String toJson (Seq<String> indvInfo) {
//        "Description","SSN","LastName","Suffix","FirstName","MiddleName","VCode","DoD","DoB"
        String json = null;
        String description = indvInfo.get(0).replaceAll("\\"+"\"", "");
        String ssn = indvInfo.get(1).replaceAll("\\"+"\"", "");
        String lastName = indvInfo.get(2).replaceAll("\\"+"\"", "");
        String suffix = indvInfo.get(3).replaceAll("\\"+"\"", "");
        String firstName = indvInfo.get(4).replaceAll("\\"+"\"", "");
        String middleName = indvInfo.get(5).replaceAll("\\"+"\"", "");
        String vCode = indvInfo.get(6).replaceAll("\\"+"\"", "");
        String deathDate = indvInfo.get(7).replaceAll("\\"+"\"", "");
        String birthDate = indvInfo.get(8).replaceAll("\\"+"\"", "");

        if (indvInfo.size() > 0) {
            json = "{\n";
            json += "\"description\": " + JSONObject.quote(description) + ",\n";
            json += "\"ssn\": " + JSONObject.quote(ssn) + ",\n";
            json += "\"lastName\": " + JSONObject.quote(lastName) + ",\n";
            json += "\"suffix\": " + JSONObject.quote(suffix) + ",\n";
            json += "\"firstName\": " + JSONObject.quote(firstName) + ",\n";
            json += "\"middleName\": " + JSONObject.quote(middleName) + ",\n";
            json += "\"vCode\": " + JSONObject.quote(vCode) + ",\n";
            json += "\"dateOfDeath\": " + JSONObject.quote(deathDate) + ",\n";
            json += "\"dateOfBirth\": " + JSONObject.quote(birthDate) + "\n";
            json += "}";

        }

        return json;
    }

    /**
     * Return Person object from Seq<String> object. Requires Seq<String> object.
     * */
    public static Person toPerson (Seq<String> indvInfo) {
        String description = getDescription(indvInfo);
        String ssn = getSSN(indvInfo);
        String lastName = getLastName(indvInfo);
        String suffix = getSuffix(indvInfo);
        String firstName = getFirstName(indvInfo);
        String middleName = getMiddleName(indvInfo);
        String vCode = getVCode(indvInfo);
        String deathDate = getDeathDate(indvInfo);
        String birthDate = getBirthDate(indvInfo);

        Person individual = new Person(description, ssn, lastName, suffix, firstName,
            middleName, vCode, deathDate, birthDate);
        return individual;
    }

    /**
     * Return Person object from Seq<String> object. Requires Seq<String> object.
     * */
    public static void restorePerson (Person person, Seq<String> indvInfo) {
        person.setDescription(getDescription(indvInfo));
        person.setSsn(getSSN(indvInfo));
        person.setLastName(getLastName(indvInfo));
        person.setSuffix(getSuffix(indvInfo));
        person.setFirstName(getFirstName(indvInfo));
        person.setMiddleName(getMiddleName(indvInfo));
        person.setvCode(getVCode(indvInfo));
        person.setDeathDate(getDeathDate(indvInfo));
        person.setBirthDate(getBirthDate(indvInfo));
    }

    /**
     * Get description information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getDescription (Seq<String> indvInfo) {
        String description = indvInfo.get(0).replaceAll("\\"+"\"", "");
        if (description.length() == 0) {
            description = "n/a";
        }
        return description;
    }

    /**
     * Get ssn information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getSSN (Seq<String> indvInfo) {
        String ssn = indvInfo.get(1).replaceAll("\\"+"\"", "");
        if (ssn.length() == 0) {
            ssn = "n/a";
        }
        return ssn;
    }

    /**
     * Get last name information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getLastName (Seq<String> indvInfo) {
        String lastName = indvInfo.get(2).replaceAll("\\"+"\"", "");
        if (lastName.length() == 0) {
            lastName = "n/a";
        }
        return lastName;
    }

    /**
     * Get suffix information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getSuffix (Seq<String> indvInfo) {
        String suffix = indvInfo.get(3).replaceAll("\\"+"\"", "");
        if (suffix.length() == 0) {
            suffix = "n/a";
        }
        return suffix;
    }
    /**
     * Get first name information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getFirstName (Seq<String> indvInfo) {
        String firstName = indvInfo.get(4).replaceAll("\\"+"\"", "");
        if (firstName.length() == 0) {
            firstName = "n/a";
        }
        return firstName;
    }
    /**
     * Get middle name information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getMiddleName (Seq<String> indvInfo) {
        String middleName = indvInfo.get(5).replaceAll("\\"+"\"", "");
        if (middleName.length() == 0) {
            middleName = "n/a";
        }
        return middleName;
    }
    /**
     * Get vCode information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getVCode (Seq<String> indvInfo) {
        String vCode = indvInfo.get(6).replaceAll("\\"+"\"", "");
        if (vCode.length() == 0) {
            vCode = "n/a";
        }
        return vCode;
    }
    /**
     * Get death date information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getDeathDate (Seq<String> indvInfo) {
        String deathDate = indvInfo.get(7).replaceAll("\\"+"\"", "");
        if (deathDate.length() == 0) {
            deathDate = "n/a";
        }
        return deathDate;
    }
    /**
     * Get birthdate information of Seq<Seq<String>> object. Requires Seq<String> object.
     */
    public static String getBirthDate (Seq<String> indvInfo) {
        String birthDate = indvInfo.get(8).replaceAll("\\"+"\"", "");
        if (birthDate.length() == 0) {
            birthDate = "n/a";
        }
        return birthDate;
    }
}

