package cat.itacademy.s05.t01.repository;

import cat.itacademy.s05.t01.dto.PlayerRankingDTO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends R2dbcRepository<PlayerRankingDTO,Long> {

}
