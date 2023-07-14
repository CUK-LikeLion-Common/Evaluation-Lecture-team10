package com.example.lectureevaluationdev.mapper.evaluation;

import com.example.lectureevaluationdev.dto.evaluation.EvaluationDTO;
import com.example.lectureevaluationdev.entity.evaluation.EvaluationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T13:54:51+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class EvaluationMapperImpl implements EvaluationMapper {

    @Override
    public EvaluationDTO toDTO(EvaluationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EvaluationDTO.EvaluationDTOBuilder evaluationDTO = EvaluationDTO.builder();

        evaluationDTO.lectureScore( entity.getLectureScore() );
        evaluationDTO.comfortableScore( entity.getComfortableScore() );
        evaluationDTO.creditScore( entity.getCreditScore() );
        evaluationDTO.totalScore( entity.getTotalScore() );
        evaluationDTO.evaluationContent( entity.getEvaluationContent() );
        evaluationDTO.evaluationTitle( entity.getEvaluationTitle() );
        evaluationDTO.lectureDivide( entity.getLectureDivide() );
        evaluationDTO.semesterDivide( entity.getSemesterDivide() );
        evaluationDTO.lectureYear( entity.getLectureYear() );
        evaluationDTO.professorName( entity.getProfessorName() );
        evaluationDTO.lectureName( entity.getLectureName() );
        evaluationDTO.userID( entity.getUserID() );
        evaluationDTO.evaluationID( entity.getEvaluationID() );

        return evaluationDTO.build();
    }

    @Override
    public EvaluationEntity toEntity(EvaluationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EvaluationEntity.EvaluationEntityBuilder evaluationEntity = EvaluationEntity.builder();

        evaluationEntity.evaluationID( dto.getEvaluationID() );
        evaluationEntity.userID( dto.getUserID() );
        evaluationEntity.lectureName( dto.getLectureName() );
        evaluationEntity.professorName( dto.getProfessorName() );
        evaluationEntity.lectureYear( dto.getLectureYear() );
        evaluationEntity.semesterDivide( dto.getSemesterDivide() );
        evaluationEntity.lectureDivide( dto.getLectureDivide() );
        evaluationEntity.evaluationTitle( dto.getEvaluationTitle() );
        evaluationEntity.evaluationContent( dto.getEvaluationContent() );
        evaluationEntity.totalScore( dto.getTotalScore() );
        evaluationEntity.creditScore( dto.getCreditScore() );
        evaluationEntity.comfortableScore( dto.getComfortableScore() );
        evaluationEntity.lectureScore( dto.getLectureScore() );

        return evaluationEntity.build();
    }

    @Override
    public ArrayList<EvaluationDTO> toDtoList(List<EvaluationEntity> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<EvaluationDTO> arrayList = new ArrayList<EvaluationDTO>();
        for ( EvaluationEntity evaluationEntity : list ) {
            arrayList.add( toDTO( evaluationEntity ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<EvaluationEntity> toEntityList(List<EvaluationDTO> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<EvaluationEntity> arrayList = new ArrayList<EvaluationEntity>();
        for ( EvaluationDTO evaluationDTO : list ) {
            arrayList.add( toEntity( evaluationDTO ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(EvaluationDTO dto, EvaluationEntity entity) {
        if ( dto == null ) {
            return;
        }

        entity.setEvaluationID( dto.getEvaluationID() );
        if ( dto.getUserID() != null ) {
            entity.setUserID( dto.getUserID() );
        }
        if ( dto.getLectureName() != null ) {
            entity.setLectureName( dto.getLectureName() );
        }
        if ( dto.getProfessorName() != null ) {
            entity.setProfessorName( dto.getProfessorName() );
        }
        entity.setLectureYear( dto.getLectureYear() );
        if ( dto.getSemesterDivide() != null ) {
            entity.setSemesterDivide( dto.getSemesterDivide() );
        }
        if ( dto.getLectureDivide() != null ) {
            entity.setLectureDivide( dto.getLectureDivide() );
        }
        if ( dto.getEvaluationTitle() != null ) {
            entity.setEvaluationTitle( dto.getEvaluationTitle() );
        }
        if ( dto.getEvaluationContent() != null ) {
            entity.setEvaluationContent( dto.getEvaluationContent() );
        }
        if ( dto.getTotalScore() != null ) {
            entity.setTotalScore( dto.getTotalScore() );
        }
        if ( dto.getCreditScore() != null ) {
            entity.setCreditScore( dto.getCreditScore() );
        }
        if ( dto.getComfortableScore() != null ) {
            entity.setComfortableScore( dto.getComfortableScore() );
        }
        if ( dto.getLectureScore() != null ) {
            entity.setLectureScore( dto.getLectureScore() );
        }
    }
}
