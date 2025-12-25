package com.sysnormal.libs.db.entities.sysnormal_entities;

import com.sysnormal.libs.db.entities.basic_entities.BaseBasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(
        name = "report_visions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "report_visions_u1",
                        columnNames = {
                                "(coalesce(parent_id, 0))","status_reg_id","data_origin_id","(coalesce(table_origin_id, 0))","(coalesce(id_at_origin, 0))",
                                "name"
                        }
                )
        }
)
public class ReportVision extends BaseBasicEntity<ReportVision> {

    @Column(name = "name", nullable = false, length = 127)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_visible", nullable = false, length = 1)
    @ColumnDefault("1")
    @Check(constraints = "is_visible in (0,1)")
    private byte isVisible = 1;

    protected static final long TABLE_ID = 10002;
    public static long getTableId() {
        return TABLE_ID;
    }

    public static long VALUES_ID = 1;
    public static long ORIGIN_DATA_ID = 2;
    public static long COMPANY_ID = 3;
    public static long BUSINESS_UNIT_ID = 4;
    public static long SUPPLIER_ID = 5;
    public static long CITY_ID = 6;
    public static long SUPERVISOR_ID = 7;
    public static long SELLER_ID = 8;
    public static long BUSINESS_AREA_ID = 9;
    public static long DEPARTMENT_ID = 10;
    public static long PRODUCT_ID = 11;
    public static long EVOLUTION_ID = 12;
    public static long CLIENT_ID = 13;
    public static long NETWORK_CLIENT_ID = 14;
    public static long ROUTE_ID = 15;
    public static long SQUARE_ID = 16;
    public static long INVOICE_ID = 17;
    public static long ITEM_INVOICE_ID = 18;
    public static long ORIGIN_BUSINESS_ID = 19;
    public static long ORIGIN_CATEGORY_ID = 20;
    public static long PLATE_ID = 21;

    public static final ReportVision VALUES = new ReportVision(){{
        setId(VALUES_ID);
        setIsSysRec((byte) 1);
        setName("VALUES");
        setIsVisible((byte) 0);
    }};
    public static final ReportVision ORIGIN_DATA = new ReportVision(){{
        setId(ORIGIN_DATA_ID);
        setIsSysRec((byte) 1);
        setName("ORIGIN DATA");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision COMPANY = new ReportVision(){{
        setId(COMPANY_ID);
        setIsSysRec((byte) 1);
        setName("COMPANY");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision BUSINESS_UNIT = new ReportVision(){{
        setId(BUSINESS_UNIT_ID);
        setIsSysRec((byte) 1);
        setName("BUSINESS UNIT");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision SUPPLIER = new ReportVision(){{
        setId(SUPPLIER_ID);
        setIsSysRec((byte) 1);
        setName("SUPPLIER");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision CITY = new ReportVision(){{
        setId(CITY_ID);
        setIsSysRec((byte) 1);
        setName("CITY");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision SUPERVISOR = new ReportVision(){{
        setId(SUPERVISOR_ID);
        setIsSysRec((byte) 1);
        setName("SUPERVISOR");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision SELLER = new ReportVision(){{
        setId(SELLER_ID);
        setIsSysRec((byte) 1);
        setName("SELLER");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision BUSINESS_AREA = new ReportVision(){{
        setId(BUSINESS_AREA_ID);
        setIsSysRec((byte) 1);
        setName("BUSINESS AREA");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision DEPARTMENT = new ReportVision(){{
        setId(DEPARTMENT_ID);
        setIsSysRec((byte) 1);
        setName("DEPARTMENT");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision PRODUCT = new ReportVision(){{
        setId(PRODUCT_ID);
        setIsSysRec((byte) 1);
        setName("PRODUCT");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision EVOLUTION = new ReportVision(){{
        setId(EVOLUTION_ID);
        setIsSysRec((byte) 1);
        setName("EVOLUTION");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision CLIENT = new ReportVision(){{
        setId(CLIENT_ID);
        setIsSysRec((byte) 1);
        setName("CLIENT");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision NETWORK_CLIENT = new ReportVision(){{
        setId(NETWORK_CLIENT_ID);
        setIsSysRec((byte) 1);
        setName("NETWORK CLIENT");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision ROUTE = new ReportVision(){{
        setId(ROUTE_ID);
        setIsSysRec((byte) 1);
        setName("ROUTE");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision SQUARE = new ReportVision(){{
        setId(SQUARE_ID);
        setIsSysRec((byte) 1);
        setName("SQUARE");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision INVOICE = new ReportVision(){{
        setId(INVOICE_ID);
        setIsSysRec((byte) 1);
        setName("INVOICE");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision ITEM_INVOICE = new ReportVision(){{
        setId(ITEM_INVOICE_ID);
        setIsSysRec((byte) 1);
        setName("ITEM INVOICE");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision ORIGIN_BUSINESS = new ReportVision(){{
        setId(ORIGIN_BUSINESS_ID);
        setIsSysRec((byte) 1);
        setName("ORIGIN BUSINESS");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision ORIGIN_CATEGORY = new ReportVision(){{
        setId(ORIGIN_CATEGORY_ID);
        setIsSysRec((byte) 1);
        setName("ORIGIN CATEGORY");
        setIsVisible((byte) 1);
    }};
    public static final ReportVision PLATE = new ReportVision(){{
        setId(PLATE_ID);
        setIsSysRec((byte) 1);
        setName("PLATE");
        setIsVisible((byte) 1);
    }};
}
