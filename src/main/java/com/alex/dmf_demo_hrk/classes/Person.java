package com.alex.dmf_demo_hrk.classes;

public class Person {
    private String description;
    private String ssn;
    private String lastName;
    private String suffix;
    private String firstName;
    private String middleName;
    private String vCode;
    private String deathDate;
    private String birthDate;

    /**
     *  Empty constructor for Person class
     * */
    public Person () {}

    /**
     *  Constructor with all parameters for Person class
     * */
    public Person (String description, String ssn, String lastName, String suffix,
                   String firstName, String middleName, String vCode, String deathDate,
                   String birthDate) {
        this.description = description;
        this.ssn = ssn;
        this.lastName = lastName;
        this.suffix = suffix;
        this.firstName = firstName;
        this.middleName = middleName;
        this.vCode = vCode;
        this.deathDate = deathDate;
        this.birthDate = birthDate;
    }

    /**
     *  Getters for Person class
     * */
    public String getDescription () {
        if (description == null) {
            return "n/a";
        }
        return description;
    }

    public String getSsn() {
        return ssn;
    }

    public String getLastName() {
        if (lastName == null) {
            return "n/a";
        }
        return lastName;
    }

    public String getSuffix() {
        if (suffix == null) {
            return "n/a";
        }
        return suffix;
    }

    public String getFirstName() {
        if (firstName == null) {
            return "n/a";
        }
        return firstName;
    }

    public String getMiddleName() {
        if (middleName == null) {
            return "n/a";
        }
        return middleName;
    }

    public String getvCode() {
        if (vCode == null) {
            return "n/a";
        }
        return vCode;
    }

    public String getDeathDate() {
        if (deathDate == null) {
            return "n/a";
        }
        return deathDate;
    }

    public String getBirthDate() {
        if (birthDate == null) {
            return "n/a";
        }
        return birthDate;
    }

    /**
     *  Setters for Person class
     * */
    public void setDescription (String description) { this.description = description; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setvCode(String vCode) { this.vCode = vCode; }
    public void setDeathDate(String deathDate) { this.deathDate = deathDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return "Description: " + description + ", SSN: " + ssn + ", Last name: " + lastName + ", Suffix: " +
                suffix + ", First name: " + firstName + ", Middle name: " + middleName + ", vCode: " +
                vCode + ", Death date: " + deathDate + ", Birth date: " + birthDate;
    }



}
