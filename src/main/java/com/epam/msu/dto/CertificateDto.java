package com.epam.msu.dto;

import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class CertificateDto implements Comparable<CertificateDto> {

    private int id;
    private String name;
    private String description;
    private long price;
    private int duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
    private List<Tag> tag;

    public CertificateDto(){}

    public CertificateDto(int id, String name, String description, long price, int duration, Timestamp createDate, Timestamp lastUpdateDate, List<Tag> tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CertificateDto)) return false;
        CertificateDto that = (CertificateDto) o;
        return getId() == that.getId() && getPrice() == that.getPrice() && getDuration() == that.getDuration() && getName().equals(that.getName()) && getDescription().equals(that.getDescription()) && getCreateDate().equals(that.getCreateDate()) && getLastUpdateDate().equals(that.getLastUpdateDate()) && getTag().equals(that.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getDuration(), getCreateDate(), getLastUpdateDate(), getTag());
    }

    @Override
    public String toString() {
        return "CertificateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", tag=" + tag +
                '}';
    }

    @Override
    public int compareTo(@NotNull CertificateDto o) {
        return this.getName().compareTo(o.getName());
    }
}
