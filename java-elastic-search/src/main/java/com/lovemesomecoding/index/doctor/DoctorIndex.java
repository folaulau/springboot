package com.lovemesomecoding.index.doctor;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovemesomecoding.utils.ObjectUtils;

/**
 * DoctorIndex has fields that are searchable
 * 
 * @author folaukaveinga
 *
 */
public class DoctorIndex implements Serializable {

    private static final long               serialVersionUID = 1L;

    private String                          npi;

    private String                          firstName;

    private String                          middleName;

    private String                          lastName;

    private String                          fullName;

    private String                          prefix;

    private String                          suffix;

    private String                          credential;

    private String                          organizationName;

    private String                          otherLastName;

    private String                          otherFirstName;

    private String                          otherOrganizationName;

    private String                          type;

    private double                          rating;

    private int                             reviewCount;

    private String                          priceFairness;

    private String                          gender;

    // nested
    private Set<AddressIndex>               addresses;

    private Set<String>                     languages;

    // nested
    private Set<TaxonomyIndex>              taxonomies;

    private Set<OrganizationOtherNameIndex> ogranizationOtherNames;

    private Set<CareRateIndex>              careRates;

    /*
     * list of locations is in (lon and lat) instead of lat and lon
     */
    private List<List<Double>>              geoCoordinates;

    public DoctorIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<List<Double>> getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(List<List<Double>> geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }

    public void addGeoCoordinate(List<Double> geoCoordinate) {
        if (this.geoCoordinates == null) {
            this.geoCoordinates = new ArrayList<>();
        }

        this.geoCoordinates.add(geoCoordinate);
    }

    public String getPriceFairness() {
        return priceFairness;
    }

    public void setPriceFairness(String priceFairness) {
        this.priceFairness = priceFairness;
    }

    public Set<AddressIndex> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressIndex> addresses) {
        this.addresses = addresses;

        // Elastic search lon,lat as array
        for (AddressIndex addr : this.addresses) {
            addGeoCoordinate(Arrays.asList(addr.getLon(), addr.getLat()));
        }
    }

    public Set<TaxonomyIndex> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(Set<TaxonomyIndex> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public Set<OrganizationOtherNameIndex> getOgranizationOtherNames() {
        return ogranizationOtherNames;
    }

    public void setOgranizationOtherNames(Set<OrganizationOtherNameIndex> ogranizationOtherNames) {
        this.ogranizationOtherNames = ogranizationOtherNames;
    }

    public Set<CareRateIndex> getCareRates() {
        return careRates;
    }

    public void setCareRates(Set<CareRateIndex> careRates) {
        this.careRates = careRates;
    }

    public String getOtherOrganizationName() {
        return otherOrganizationName;
    }

    public void setOtherOrganizationName(String otherOrganizationName) {
        this.otherOrganizationName = otherOrganizationName;
    }

    public String getFullName() {
        StringBuilder fn = new StringBuilder();
        if (type.equals("Individual")) {
            if (firstName != null && firstName.length() > 0) {
                fn.append(firstName + " ");
            }
            if (lastName != null && lastName.length() > 0) {
                fn.append(lastName);
            }
        } else {
            fn.append(this.organizationName);
        }

        return fn.toString();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOtherLastName() {
        return otherLastName;
    }

    public void setOtherLastName(String otherLastName) {
        this.otherLastName = otherLastName;
    }

    public String getOtherFirstName() {
        return otherFirstName;
    }

    public void setOtherFirstName(String otherFirstName) {
        this.otherFirstName = otherFirstName;
    }

    public String toJson() {
        try {
            return ObjectUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static DoctorIndex fromJson(String json) {
        try {
            return ObjectUtils.getObjectMapper().readValue(json, DoctorIndex.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JsonProcessingException, msg: " + e.getLocalizedMessage());
            return null;
        }
    }

}
