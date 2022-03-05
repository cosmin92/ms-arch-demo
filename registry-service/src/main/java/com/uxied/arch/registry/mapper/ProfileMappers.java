package com.uxied.arch.registry.mapper;

import com.uxied.arch.common.registry.CreateProfile;
import com.uxied.arch.common.registry.ViewProfile;
import com.uxied.arch.registry.domain.Profile;
import org.mapstruct.Mapper;

@Mapper
public interface ProfileMappers {

    ViewProfile map(Profile profile);

    Profile map(CreateProfile profile);

}
