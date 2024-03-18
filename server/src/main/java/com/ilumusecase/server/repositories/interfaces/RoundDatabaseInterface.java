package com.ilumusecase.server.repositories.interfaces;

import com.ilumusecase.server.resources.RoundDTO;

public interface RoundDatabaseInterface {
    public RoundDTO createRound(RoundDTO roundDTO);
    public RoundDTO updateRound(Long id, RoundDTO roundDTO);
}
