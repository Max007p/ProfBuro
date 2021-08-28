package com.org.profburo.network.responsesEntities.registration;

public class RegisterBody {
    private String secondName;
    private String name;
    private String patronymic;//userprofile
    private String birthDate;//userprofile
    private String region;//userprofile
    private String institutionName;//userprofile
    private String grade;//userprofile
    private String serialPassport;
    private String numberPassport;
    private String gender;//userprofile
    private String email;
    private String phone;//userprofile
    private String password;
    private String permission;

    public RegisterBody(String secondName, String name, String patronymic, String birthDate, String region, String institutionName, String grade, String serialPassport, String numberPassport, String gender, String email, String phone, String password, String permission) {
        this.secondName = secondName;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.region = region;
        this.institutionName = institutionName;
        this.grade = grade;
        this.serialPassport = serialPassport;
        this.numberPassport = numberPassport;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.permission = permission;
    }

    public RegisterBody(String secondName, String name, String patronymic, String birthDate, String region, String serialPassport, String numberPassport, String gender, String email, String phone, String password, String permission) {
        this.secondName = secondName;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.region = region;
        this.serialPassport = serialPassport;
        this.numberPassport = numberPassport;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSerialPassport() {
        return serialPassport;
    }

    public void setSerialPassport(String serialPassport) {
        this.serialPassport = serialPassport;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
