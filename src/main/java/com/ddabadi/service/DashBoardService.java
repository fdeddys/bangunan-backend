package com.ddabadi.service;

import java.util.Date;
import java.util.List;

/**
 * Created by deddy on 6/29/16.
 */
public interface DashBoardService {

    Double getPenerimaanByDate(Date tgl1, Date tgl2);
    Double getPenjualanByDate(Date tgl1, Date tgl2);



}
