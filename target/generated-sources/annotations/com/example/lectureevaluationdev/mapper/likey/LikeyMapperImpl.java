package com.example.lectureevaluationdev.mapper.likey;

import com.example.lectureevaluationdev.dto.likey.LikeyDTO;
import com.example.lectureevaluationdev.entity.likey.LikeyEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-17T18:19:16+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class LikeyMapperImpl implements LikeyMapper {

    @Override
    public LikeyDTO toDTO(LikeyEntity entity) {
        if ( entity == null ) {
            return null;
        }

        LikeyDTO.LikeyDTOBuilder likeyDTO = LikeyDTO.builder();

        likeyDTO.evaluation( entity.getEvaluation() );
        likeyDTO.id( entity.getId() );
        likeyDTO.userID( entity.getUserID() );
        likeyDTO.createdAt( entity.getCreatedAt() );
        likeyDTO.updatedAt( entity.getUpdatedAt() );

        return likeyDTO.build();
    }

    @Override
    public LikeyEntity toEntity(LikeyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        LikeyEntity.LikeyEntityBuilder likeyEntity = LikeyEntity.builder();

        likeyEntity.evaluation( dto.getEvaluation() );
        likeyEntity.id( dto.getId() );
        likeyEntity.userID( dto.getUserID() );
        likeyEntity.createdAt( dto.getCreatedAt() );
        likeyEntity.updatedAt( dto.getUpdatedAt() );

        return likeyEntity.build();
    }

    @Override
    public ArrayList<LikeyDTO> toDtoList(List<LikeyEntity> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<LikeyDTO> arrayList = new ArrayList<LikeyDTO>();
        for ( LikeyEntity likeyEntity : list ) {
            arrayList.add( toDTO( likeyEntity ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<LikeyEntity> toEntityList(List<LikeyDTO> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<LikeyEntity> arrayList = new ArrayList<LikeyEntity>();
        for ( LikeyDTO likeyDTO : list ) {
            arrayList.add( toEntity( likeyDTO ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(LikeyDTO dto, LikeyEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getEvaluation() != null ) {
            entity.setEvaluation( dto.getEvaluation() );
        }
        entity.setId( dto.getId() );
        if ( dto.getUserID() != null ) {
            entity.setUserID( dto.getUserID() );
        }
        entity.setEvaluationID( dto.getEvaluationID() );
        if ( dto.getCreatedAt() != null ) {
            entity.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            entity.setUpdatedAt( dto.getUpdatedAt() );
        }
    }
}
