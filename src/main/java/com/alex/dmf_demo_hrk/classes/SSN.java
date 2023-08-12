package com.alex.dmf_demo_hrk.classes;

public class SSN {
    private String socialNum;

    /**
     *  Getters for SSN class
     * */
    public String getSocialNum() {
        return socialNum;
    }

    /**
     *  Setters for SSN class
     * */
    public void setSocialNum(String socialNum) { this.socialNum = socialNum; }

    @Override
    public String toString() {
        return socialNum;
    }



}
