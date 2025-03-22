package org.wikitermbase.api.DataModel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    private String arabicNormalised;
    private long[] dictionaryIds;
    private String englishNormalised;
    private String frenchNormalised;
    private Occurence[] occurences;
    private double totalRelevance;

    // Constructor
    public Group(String arabicNormalised, long[] dictionaryIds, String englishNormalised, 
                 String frenchNormalised, Occurence[] occurences, double totalRelevance) {
        this.arabicNormalised = arabicNormalised;
        this.dictionaryIds = dictionaryIds;
        this.englishNormalised = englishNormalised;
        this.frenchNormalised = frenchNormalised;
        this.occurences = occurences;
        this.totalRelevance = totalRelevance;
    }

    // Getters and Setters
    public String getArabicNormalised() {
        return arabicNormalised;
    }

    public void setArabicNormalised(String arabicNormalised) {
        this.arabicNormalised = arabicNormalised;
    }

    public long[] getDictionaryIds() {
        return dictionaryIds;
    }

    public void setDictionaryIds(long[] dictionaryIds) {
        this.dictionaryIds = dictionaryIds;
    }

    public String getEnglishNormalised() {
        return englishNormalised;
    }

    public void setEnglishNormalised(String englishNormalised) {
        this.englishNormalised = englishNormalised;
    }

    public String getFrenchNormalised() {
        return frenchNormalised;
    }

    public void setFrenchNormalised(String frenchNormalised) {
        this.frenchNormalised = frenchNormalised;
    }

    public Occurence[] getOccurences() {
        return occurences;
    }

    public void setOccurences(Occurence[] occurences) {
        this.occurences = occurences;
    }

    public double getTotalRelevance() {
        return totalRelevance;
    }

    public void setTotalRelevance(double totalRelevance) {
        this.totalRelevance = totalRelevance;
    }

    @Override
    public String toString() {
        return "Group{" +
			"arabicNormalised='" + arabicNormalised + '\'' +
			", dictionaryIds=" + Arrays.toString(dictionaryIds) +
			", englishNormalised='" + englishNormalised + '\'' +
			", frenchNormalised='" + frenchNormalised + '\'' +
			", occurences=" + Arrays.toString(occurences) +
			", totalRelevance=" + totalRelevance +
			'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Double.compare(group.totalRelevance, totalRelevance) == 0 &&
			Objects.equals(arabicNormalised, group.arabicNormalised) &&
			Arrays.equals(dictionaryIds, group.dictionaryIds) &&
			Objects.equals(englishNormalised, group.englishNormalised) &&
			Objects.equals(frenchNormalised, group.frenchNormalised) &&
			Arrays.equals(occurences, group.occurences);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(arabicNormalised, englishNormalised, frenchNormalised, totalRelevance);
        result = 31 * result + Arrays.hashCode(dictionaryIds);
        result = 31 * result + Arrays.hashCode(occurences);
        return result;
    }
}
