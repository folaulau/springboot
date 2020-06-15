package com.folaukaveinga.testing.user;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.folaukaveinga.testing.utility.ConversionUtil;
import com.folaukaveinga.testing.utility.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@Where(clause = "deleted = 'F'")
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long              id;

    @Column(name = "first_name")
    private String            firstName;

    @Column(name = "last_name")
    private String            lastName;

    @Column(name = "phone_number")
    private String            phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String            email;

    @Column(name = "gender")
    private String            gender;

    @Column(name = "ssn")
    private String            ssn;

    @Column(name = "ssn_last_4")
    private String            ssnLast4;

    @Basic(optional = true)
    private Date              dob;

    @Type(type = "true_false")
    @Column(name = "deleted", nullable = false)
    private boolean           deleted;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date              createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private Date              updatedAt;

    @Column(name = "deleted_at")
    private Date              deletedAt;

    @BatchSize(size = 2)
    @OrderBy("off_day")
    @ElementCollection
    @CollectionTable(name = "user_off_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "off_day")
    private Set<Date>         offDays;

    public User(String firstName, int age, String email) {
        this(firstName, null, age, email);
    }

    public User(String firstName, String lastName, int age, String email) {
        this(0, firstName, lastName, age, email);
    }

    public User(long id, String firstName, String lastName, int age, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = DateUtils.addYears(new Date(), -age);
    }

    public int getAge() {
        return (dob == null) ? 0 : Period.between(dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }

    public void addOffDay(Date offDay) {
        if (this.offDays == null) {
            this.offDays = new HashSet<>();
        }
        this.offDays.add(offDay);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(phoneNumber).append(email).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        User otherUser = (User) other;
        return new EqualsBuilder().append(this.id, otherUser.id).append(this.phoneNumber, otherUser.phoneNumber).append(this.email, otherUser.email).isEquals();
    }

    public static User fromJson(String json) {
        try {
            return ObjectUtils.getObjectMapper().readValue(json, User.class);
        } catch (IOException e) {
            return new User();
        }
    }

    public String toJson() {
        try {
            return ObjectUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @PrePersist
    private void preCreate() {
        this.deleted = false;
    }

    @PreUpdate
    private void preUpdate() {
    }

}
