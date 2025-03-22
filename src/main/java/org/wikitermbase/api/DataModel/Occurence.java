package org.wikitermbase.api.DataModel;

import java.io.Serializable;
import java.util.Objects;

public class Occurence implements Serializable {
    private static final long serialVersionUID = 1L;

    private String arabic;
    private String arabicNormalised;
    private long dictionaryId;
    private String dictionaryNameArabic;
    private String dictionaryWikidataId;
    private String english;
    private String french;
    private long id;
    private double relevance;
    private String uri;

    // Constructor
    public Occurence(String arabic, String arabicNormalised, long dictionaryId, String dictionaryNameArabic, 
                     String dictionaryWikidataId, String english, String french, long id, double relevance, String uri) {
        this.arabic = arabic;
        this.arabicNormalised = arabicNormalised;
        this.dictionaryId = dictionaryId;
        this.dictionaryNameArabic = dictionaryNameArabic;
        this.dictionaryWikidataId = dictionaryWikidataId;
        this.english = english;
        this.french = french;
        this.id = id;
        this.relevance = relevance;
        this.uri = uri;
    }

    // Getters and Setters
    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getArabicNormalised() {
        return arabicNormalised;
    }

    public void setArabicNormalised(String arabicNormalised) {
        this.arabicNormalised = arabicNormalised;
    }

    public long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryNameArabic() {
        return dictionaryNameArabic;
    }

    public void setDictionaryNameArabic(String dictionaryNameArabic) {
        this.dictionaryNameArabic = dictionaryNameArabic;
    }

    public String getDictionaryWikidataId() {
        return dictionaryWikidataId;
    }

    public void setDictionaryWikidataId(String dictionaryWikidataId) {
        this.dictionaryWikidataId = dictionaryWikidataId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Occurence{" +
			"arabic='" + arabic + '\'' +
			", arabicNormalised='" + arabicNormalised + '\'' +
			", dictionaryId=" + dictionaryId +
			", dictionaryNameArabic='" + dictionaryNameArabic + '\'' +
			", dictionaryWikidataId='" + dictionaryWikidataId + '\'' +
			", english='" + english + '\'' +
			", french='" + french + '\'' +
			", id=" + id +
			", relevance=" + relevance +
			", uri='" + uri + '\'' +
			'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occurence occurence = (Occurence) o;
        return dictionaryId == occurence.dictionaryId &&
			id == occurence.id &&
			Double.compare(occurence.relevance, relevance) == 0 &&
			Objects.equals(arabic, occurence.arabic) &&
			Objects.equals(arabicNormalised, occurence.arabicNormalised) &&
			Objects.equals(dictionaryNameArabic, occurence.dictionaryNameArabic) &&
			Objects.equals(dictionaryWikidataId, occurence.dictionaryWikidataId) &&
			Objects.equals(english, occurence.english) &&
			Objects.equals(french, occurence.french) &&
			Objects.equals(uri, occurence.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arabic, arabicNormalised, dictionaryId, dictionaryNameArabic, dictionaryWikidataId, english, french, id, relevance, uri);
    }
}
