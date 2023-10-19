package com.project.always.bar.mapper;

import com.project.always.bar.domain.Bar;
import com.project.always.bar.dto.BarDTO;
import com.project.always.bar.dto.BarDTO.BarDTOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-19T05:02:53+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class BarMapperImpl implements BarMapper {

    @Override
    public BarDTO toDto(Bar e) {
        if ( e == null ) {
            return null;
        }

        BarDTOBuilder barDTO = BarDTO.builder();

        barDTO.id( e.getId() );
        barDTO.title( e.getTitle() );
        barDTO.location( e.getLocation() );
        barDTO.rating( e.getRating() );
        barDTO.image( e.getImage() );
        barDTO.tel( e.getTel() );
        barDTO.lat( e.getLat() );
        barDTO.log( e.getLog() );
        barDTO.open_status( e.getOpen_status() );
        barDTO.group_seat( e.getGroup_seat() );
        barDTO.hit( e.getHit() );

        return barDTO.build();
    }

    @Override
    public Bar toEntity(BarDTO d) {
        if ( d == null ) {
            return null;
        }

        Bar bar = new Bar();

        return bar;
    }

    @Override
    public List<BarDTO> toDtoList(List<Bar> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BarDTO> list = new ArrayList<BarDTO>( entityList.size() );
        for ( Bar bar : entityList ) {
            list.add( toDto( bar ) );
        }

        return list;
    }

    @Override
    public List<Bar> toEntityList(List<BarDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Bar> list = new ArrayList<Bar>( dtoList.size() );
        for ( BarDTO barDTO : dtoList ) {
            list.add( toEntity( barDTO ) );
        }

        return list;
    }
}
