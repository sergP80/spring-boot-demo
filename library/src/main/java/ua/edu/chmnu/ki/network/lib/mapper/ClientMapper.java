package ua.edu.chmnu.ki.network.lib.mapper;

import org.mapstruct.*;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;
import ua.edu.chmnu.ki.network.lib.web.dto.ClientDTO;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ClientMapper {

    Client mapTo(ClientDTO source);

    ClientDTO mapTo(Client source);

    /*@Mappings({*/
    /*        @Mapping(target = "id", ignore = true)*/
    /*})*/
    /*Client mapWith(ClientDTO source, @MappingTarget Client target);*/
}
