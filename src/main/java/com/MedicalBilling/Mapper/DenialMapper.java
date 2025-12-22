package com.MedicalBilling.Mapper;

import com.MedicalBilling.DTO.DenialDTO;
import com.MedicalBilling.Entity.DenialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DenialMapper {

    
    @Mapping(source = "claim.claimId", target = "claimId")
    DenialDTO toDTO(DenialEntity denial);

    List<DenialDTO> toDTOList(List<DenialEntity> denials);
}