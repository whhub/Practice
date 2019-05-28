package com.mj.scada.bean.json;

import com.mj.scada.bean.domain.type.EquipBrand;
import com.mj.scada.bean.domain.type.EquipType;
import lombok.Data;

import java.util.List;

/*
 *Author:ZouHeng
 *Des:
 *Date:2019-05-14 17:09
 */
@Data
public class TypeData
{
    private List<EquipType> types;
    private List<EquipBrand> brands;

    public TypeData(List<EquipType> types, List<EquipBrand> brands)
    {
        this.types = types;
        this.brands = brands;
    }
}
