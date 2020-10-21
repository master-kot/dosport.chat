package ru.dosport.chat.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.dosport.chat.entities.Authority;
import ru.dosport.chat.security.JwtRole;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Authority в JwtRole
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper {

    JwtRole mapEntityToJwt(Authority entity);

    List<JwtRole> mapEntityToJwt(List<Authority> entities);
}
