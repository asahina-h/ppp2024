package com.example.animalfortune;

//　情報ソース：https://marisol.hpplus.jp/fortune/animal
// その２：https://unkoi.com/article/animal_ookami/


import java.util.List;

public class AnimalFortune {
    private String animalName; // 動物名
    private List<String> animalTypeTraits; // タイプの特徴（リスト形式）
    private String basicPersonality; // 基本性格
    private String strengths; // 得意なこと
    private String weaknesses; // 苦手なこと
    private String loveTendencies; // 恋愛傾向
    private String marriageAndFamily; // 結婚、家庭
    private String careerAndSuitableJobs; // 仕事運、適職
    private String financialLuck; // 金運
    private String compatibility; // 相性

    // コンストラクタ
    public AnimalFortune(String animalName, List<String> animalTypeTraits, String basicPersonality, String strengths,
                          String weaknesses, String loveTendencies, String marriageAndFamily, String careerAndSuitableJobs,
                          String financialLuck, String compatibility) {
        this.animalName = animalName;
        this.animalTypeTraits = animalTypeTraits;
        this.basicPersonality = basicPersonality;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.loveTendencies = loveTendencies;
        this.marriageAndFamily = marriageAndFamily;
        this.careerAndSuitableJobs = careerAndSuitableJobs;
        this.financialLuck = financialLuck;
        this.compatibility = compatibility;
    }

    // getter/setter
    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public List<String> getAnimalTypeTraits() {
        return animalTypeTraits;
    }

    public void setAnimalTypeTraits(List<String> animalTypeTraits) {
        this.animalTypeTraits = animalTypeTraits;
    }

    public String getBasicPersonality() {
        return basicPersonality;
    }

    public void setBasicPersonality(String basicPersonality) {
        this.basicPersonality = basicPersonality;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getLoveTendencies() {
        return loveTendencies;
    }

    public void setLoveTendencies(String loveTendencies) {
        this.loveTendencies = loveTendencies;
    }

    public String getMarriageAndFamily() {
        return marriageAndFamily;
    }

    public void setMarriageAndFamily(String marriageAndFamily) {
        this.marriageAndFamily = marriageAndFamily;
    }

    public String getCareerAndSuitableJobs() {
        return careerAndSuitableJobs;
    }

    public void setCareerAndSuitableJobs(String careerAndSuitableJobs) {
        this.careerAndSuitableJobs = careerAndSuitableJobs;
    }

    public String getFinancialLuck() {
        return financialLuck;
    }

    public void setFinancialLuck(String financialLuck) {
        this.financialLuck = financialLuck;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }
}
