
package com.ranji.labourlink.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.dto.ProfessionDto;
import com.ranji.labourlink.Model.Profession;
import com.ranji.labourlink.Repository.ProfessionRepo;

@Service
public class ProfessionServ {

    @Autowired
    private ProfessionRepo profrep;

    public List<ProfessionDto> getAllProf() {

        List<Profession> professions = profrep.findAll();

        List<ProfessionDto> response = new ArrayList<>();

        for (Profession profession : professions) {

            ProfessionDto dto = new ProfessionDto(
                    profession.getId(),
                    profession.getName()
            );

            response.add(dto);
        }

        return response;
    }
}