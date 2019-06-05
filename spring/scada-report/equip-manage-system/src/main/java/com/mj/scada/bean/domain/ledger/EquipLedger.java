package com.mj.scada.bean.domain.ledger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
 *Author:ZouHeng
 *Des:台账管理，设备档案
 *Date:2019-03-18 13:45
 */
@Getter
@Setter
@ToString
@Table(name = "equip_ledger")
@Entity
public class EquipLedger
{
    @Id
    private Integer id;
    private String propertyName;    //资产名称
    private String equipName;       //设备名称（从底层数据取得）
    private String customName;      //自定义名称(拥有比设备名称更高的优先级，与设备名称一起定义资产名称)
    private String propertyNum;     //资产编号
    @Column(unique = true)
    private String equipNum;        //设备编号（从底层数据取得）
    private String equipSort;       //设备分类（从底层数据取得）
    private String specification;   //规格
    private String equipType;       //设备型号
    private String manufacturer;    //制造厂
    private String time;            //使用日期
    private String originalValue;   //资产原值
    private String netValue;        //资产净值
    private String installSite;     //安装地点（从底层数据取得）
    private String user;            //使用单位
    private String status;          //设备状态,0:停用；1：在用；2：闲置；3：报废
    private Integer typeId;
    private Integer brandId;

    public void setPropertyName()
    {
        if (customName==null)
        {
            this.propertyName=equipName;
        }
        else
        {
            this.propertyName=customName;
        }
    }

    public EquipLedger()
    {
    }

    public EquipLedger(String equipName, String equipNum, String equipSort, String installSite)
    {
        this.equipName = equipName;
        this.equipNum = equipNum;
        this.equipSort = equipSort;
        this.installSite = installSite;
    }
}
