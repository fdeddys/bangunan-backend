package com.ddabadi.domain.repository;

import com.ddabadi.domain.PenerimaanDt;
import com.ddabadi.domain.PenerimaanHd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by deddy on 5/25/16.
 */
public interface PenerimaanDtRepository extends JpaRepository<PenerimaanDt, Long> {

    Page<PenerimaanDt> findByPenerimaanHd(PenerimaanHd penerimaanHd, Pageable pageable);

    List<PenerimaanDt> findAllByPenerimaanHd(PenerimaanHd penerimaanHd);

}
