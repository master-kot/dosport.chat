package ru.dosport.main.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.dosport.main.entities.Authority;
import ru.dosport.main.security.JwtRole;

import java.util.List;

/**
 * Маппер, преобразовывающий классы Authority в JwtRole
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper {

    JwtRole mapEntityToJwt(Authority entity);

    List<JwtRole> mapEntityToJwt(List<Authority> entities);
}
