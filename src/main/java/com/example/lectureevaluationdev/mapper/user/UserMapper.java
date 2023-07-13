package com.example.lectureevaluationdev.mapper.user;


import com.example.lectureevaluationdev.dto.user.UserDTO;
import com.example.lectureevaluationdev.entity.user.UserEntity;
import com.example.lectureevaluationdev.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //* (componentModel = "spring") 이게 없으면 스프링 bean 으로 등록되지 않으므로 명시해 줘야함.
public interface UserMapper extends GenericMapper<UserDTO, UserEntity> {
    com.example.lectureevaluationdev.mapper.user.UserMapper INSTANCE = Mappers.getMapper(com.example.lectureevaluationdev.mapper.user.UserMapper.class);
}
