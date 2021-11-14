package com.epam.msu.dto;

import com.epam.msu.entity.Certificate;

import java.util.List;
import java.util.Objects;

public class TagDto {
    private int id;
    private String name;
    private List<Certificate> certificates;

    public TagDto(){}

    public TagDto(int id, String name, List<Certificate> certificates) {
        this.id = id;
        this.name = name;
        this.certificates = certificates;
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

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDto)) return false;
        TagDto tagDto = (TagDto) o;
        return getId() == tagDto.getId() && getName().equals(tagDto.getName()) && getCertificates().equals(tagDto.getCertificates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCertificates());
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", certificates=" + certificates +
                '}';
    }
}
