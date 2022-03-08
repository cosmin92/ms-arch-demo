package com.uxied.arch.registry.mapper;

import com.uxied.arch.common.registry.CreateProfile;
import com.uxied.arch.common.registry.CreditCardDto;
import com.uxied.arch.common.registry.ViewProfile;
import com.uxied.arch.common.web.PageImpl;
import com.uxied.arch.registry.domain.CreditCard;
import com.uxied.arch.registry.domain.Profile;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface ProfileMappers {

    ViewProfile map(Profile profile);

    Profile map(CreateProfile profile);

    CreditCardDto map(CreditCard card);

    default PageImpl<ViewProfile> map(Page<Profile> page) {
        return new PageImpl<>(
                page.isLast(),
                page.isFirst(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumberOfElements(),
                page.getNumber(),
                PageImpl.map(page.getContent(), this::map)
        );
    }
}
