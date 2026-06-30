package com.ranji.labourlink.Service;

import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.Profession;
import com.ranji.labourlink.Model.WrkWage;
import com.ranji.labourlink.Repository.ProfessionRepo;
import com.ranji.labourlink.Repository.WrkWageRepo;
import com.ranji.labourlink.dto.ProfessionWageDto;
import com.ranji.labourlink.dto.WorkDto;

@Service
public class WrkWageServ {
	@Autowired
	private ProfessionRepo professionRepo;
	
	@Autowired
	private WrkWageRepo wageRepo;

	public List<ProfessionWageDto> getAllWages() {

        List<Profession> professions = professionRepo.findAll();

        List<ProfessionWageDto> result = new ArrayList<>();

        for (Profession profession : professions) {

            List<WrkWage> works = wageRepo.findByProfession(profession);

            ProfessionWageDto dto = new ProfessionWageDto();

            dto.setProfession(profession.getName());
            dto.setDailyWage(profession.getDailyWage());
            List<WorkDto> workDtos = new ArrayList<>();

            for (WrkWage work : works) {

                workDtos.add(
                    new WorkDto(
                        work.getId(),
                        work.getName(),
                        work.getPrice(),
                        work.getEstimatedHours(),
                        work.getDescription()
                    )
                );
            }

            dto.setWorks(workDtos);

            result.add(dto);
        }

        return result;
    }

	public ResponseEntity<?> profwage(String profession) {
		Profession prof = professionRepo.findByProfession(profession);
		List<WrkWage> works = wageRepo.findByProfession(prof);
		return ResponseEntity.ok(works);
	}

}
