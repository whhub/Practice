package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.ledger.EquipLedger;
import lombok.Data;

import java.util.List;
import java.util.Map;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-14 10:26
 */
@Data
public class LedgerData
{
    private Map<Integer, Integer> total;
    private Map<String, String> status;
    private Map<String, Integer> sort;
    private List<EquipLedger> ledgers;

    public LedgerData(Map<Integer, Integer> total, Map<String, String> status, Map<String, Integer> sort, List<EquipLedger> ledgers)
    {
        this.total = total;
        this.status = status;
        this.sort = sort;
        this.ledgers = ledgers;
    }
}
