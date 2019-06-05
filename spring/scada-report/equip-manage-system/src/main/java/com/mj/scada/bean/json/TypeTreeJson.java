package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:类别品牌树
 *Date:2019-05-31 11:29
 */
@Data
public class TypeTreeJson
{
    private EquipType type;
    private List<EquipBrand> brands;

    public TypeTreeJson(EquipType type, List<EquipBrand> brands)
    {
        this.type = type;
        this.brands = brands;
    }
}
