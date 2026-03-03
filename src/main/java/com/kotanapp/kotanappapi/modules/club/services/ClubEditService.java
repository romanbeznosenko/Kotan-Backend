package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubEditService {
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;

    public void editClub(UUID clubId, ClubRequest request){
        log.info("Editing club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);

        Club club = clubMapper.mapToDomain(clubDAO, new CycleAvoidingMappingContext());

        club.setName(request.name());
        club.setShortName(request.shortName());
        club.setCity(request.city());
        club.setCountry(request.country());
        club.setIsOurClub(request.isOurClub());

        clubDAO = clubMapper.mapToEntity(club, new CycleAvoidingMappingContext());
        clubManager.saveToDatabase(clubDAO);
    }
}
