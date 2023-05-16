package com.example.pw17.phones;

import com.example.pw17.manufactures.ManufactureDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private Long id;
    private String name;
    private int creationYear;

    private List<ManufactureDTO> manufactures;

    public static PhoneDTO withoutManufacture(Phone phone) {
        PhoneDTO phoneDTO = new PhoneDTO();

        phoneDTO.setId(phone.getId());
        phoneDTO.setName(phone.getName());
        phoneDTO.setCreationYear(phone.getCreationYear());

        return phoneDTO;
    }

    public static PhoneDTO withManufacture(Phone phone) {
        PhoneDTO phoneDTO = withoutManufacture(phone);

        phoneDTO.setManufactures(
                phone.getManufactures().stream().map(ManufactureDTO::withoutPhone).toList()
        );

        return phoneDTO;
    }
}
