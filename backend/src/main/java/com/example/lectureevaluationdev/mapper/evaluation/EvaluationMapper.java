package com.example.lectureevaluationdev.mapper.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import com.example.lectureevaluationdev.mapper.GenericMapper;
import com.example.lectureevaluationdev.primary.EvaluationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring") //* (componentModel = "spring") 이게 없으면 스프링 bean 으로 등록되지 않으므로 명시해 줘야함.
public interface EvaluationMapper extends GenericMapper<EvaluationDTO, EvaluationEntity> {
    EvaluationMapper INSTANCE = Mappers.getMapper(EvaluationMapper.class);

//void updateFromDto(EvaluationDTO dto, EvaluationEntity entity);

}
