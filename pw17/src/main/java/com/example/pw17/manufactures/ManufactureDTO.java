package com.example.pw17.manufactures;

import com.example.pw17.phones.Phone;
import com.example.pw17.phones.PhoneDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManufactureDTO {
    private Long id;
    private String name;
    private String address;

    private PhoneDTO phone;

    public static ManufactureDTO withoutPhone(Manufacture manufacture) {
        ManufactureDTO manufactureDTO = new ManufactureDTO();

        manufactureDTO.setId(manufacture.getId());
        manufactureDTO.setName(manufacture.getName());
        manufactureDTO.setAddress(manufacture.getAddress());

        return manufactureDTO;
    }

    public static ManufactureDTO withPhone(Manufacture manufacture) {
        ManufactureDTO manufactureDTO = withoutPhone(manufacture);

        manufactureDTO.setPhone(
                PhoneDTO.withoutManufacture(manufacture.getPhone())
        );

        return manufactureDTO;
    }
}
