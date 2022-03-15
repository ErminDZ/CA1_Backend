package dtos;

import entities.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneDTO {
    private long id;
    private int number;
    private String description;

    public PhoneDTO(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public static List<PhoneDTO> getDtos(List<Phone> ph){
        List<PhoneDTO> pdtos =new ArrayList<>();
        ph.forEach(p->pdtos.add(new PhoneDTO(p)));
        return pdtos;
    }

    public PhoneDTO(Phone p) {
        this.id = p.getId();
        this.number = p.getNumber();
        this.description = p.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + id +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
