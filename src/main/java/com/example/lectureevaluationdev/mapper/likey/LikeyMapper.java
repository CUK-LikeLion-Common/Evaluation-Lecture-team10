package com.example.lectureevaluationdev.mapper.likey;

import com.example.lectureevaluationdev.dto.likey.LikeyDTO;
import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import com.example.lectureevaluationdev.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //* (componentModel = "spring") 이게 없으면 스프링 bean 으로 등록되지 않으므로 명시해 줘야함.
public interface LikeyMapper extends GenericMapper<LikeyDTO, LikeyEntity> {
    LikeyMapper INSTANCE = Mappers.getMapper(LikeyMapper.class);
}
