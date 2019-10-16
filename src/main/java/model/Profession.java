package model;

import java.util.Objects;

public class Profession {
    private int id;
    private String profession;
    private String skill;
    private int experience;

    public Profession() {
    }

    public Profession(String profession, String skill, int experience) {
        this.profession = profession;
        this.skill = skill;
        this.experience = experience;
    }

    public Profession(int id, String profession, String skill, int experience) {
        this.id = id;
        this.profession = profession;
        this.skill = skill;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profession that = (Profession) o;
        return id == that.id &&
                experience == that.experience &&
                Objects.equals(profession, that.profession) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profession, skill, experience);
    }

    @Override
    public String toString() {
        return "Profession{" +
                "id=" + id +
                ", profession='" + profession + '\'' +
                ", skill='" + skill + '\'' +
                ", experience=" + experience +
                '}';
    }
}
