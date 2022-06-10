-- Generated by Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   at:        2022-05-13 11:11:15 EDT
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE "Order" (
    id            INTEGER NOT NULL,
    product_code  INTEGER NOT NULL,
    count         INTEGER,
    cost          INTEGER
)
LOGGING;

CREATE UNIQUE INDEX order__idxv1 ON
    "Order" (
        product_code
    ASC )
        LOGGING;

ALTER TABLE "Order" ADD CONSTRAINT order_pk PRIMARY KEY ( id );

CREATE TABLE product (
    code          INTEGER NOT NULL,
    name          VARCHAR2(4000),
    vendor_id     INTEGER NOT NULL,
    warehouse_id  INTEGER
)
LOGGING;

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( code );

CREATE TABLE sale (
    id            INTEGER NOT NULL,
    count         INTEGER,
    cost          INTEGER,
    product_code  INTEGER NOT NULL
)
LOGGING;

CREATE UNIQUE INDEX sale__idxv1 ON
    sale (
        product_code
    ASC )
        LOGGING;

ALTER TABLE sale ADD CONSTRAINT sale_pk PRIMARY KEY ( id );

CREATE TABLE t_journal (
    warehouse_id  INTEGER NOT NULL,
    name          VARCHAR2(4000),
    change_count  INTEGER,
    time          DATE
)
LOGGING;

CREATE TABLE t_order (
    "start"   DATE,
    end       DATE,
    order_id  INTEGER NOT NULL
)
LOGGING;

CREATE TABLE t_sale (
    time     DATE,
    sale_id  INTEGER NOT NULL
)
LOGGING;

CREATE TABLE vendor (
    id       INTEGER NOT NULL,
    name     VARCHAR2(4000),
    address  VARCHAR2(4000),
    phone    VARCHAR2(4000)
)
LOGGING;

ALTER TABLE vendor ADD CONSTRAINT vendor_pk PRIMARY KEY ( id );

CREATE TABLE warehouse (
    id            INTEGER NOT NULL,
    count         INTEGER,
    product_code  INTEGER NOT NULL,
    order_id      INTEGER
)
LOGGING;

CREATE UNIQUE INDEX warehouse__idx ON
    warehouse (
        order_id
    ASC )
        LOGGING;

ALTER TABLE warehouse ADD CONSTRAINT warehouse_pk PRIMARY KEY ( id );

ALTER TABLE "Order"
    ADD CONSTRAINT order_product_fk FOREIGN KEY ( product_code )
        REFERENCES product ( code )
    NOT DEFERRABLE;

ALTER TABLE product
    ADD CONSTRAINT product_vendor_fk FOREIGN KEY ( vendor_id )
        REFERENCES vendor ( id )
    NOT DEFERRABLE;

ALTER TABLE product
    ADD CONSTRAINT product_warehouse_fk FOREIGN KEY ( warehouse_id )
        REFERENCES warehouse ( id )
    NOT DEFERRABLE;

ALTER TABLE sale
    ADD CONSTRAINT sale_product_fk FOREIGN KEY ( product_code )
        REFERENCES product ( code )
    NOT DEFERRABLE;

ALTER TABLE t_journal
    ADD CONSTRAINT t_journal_warehouse_fk FOREIGN KEY ( warehouse_id )
        REFERENCES warehouse ( id )
    NOT DEFERRABLE;

ALTER TABLE t_order
    ADD CONSTRAINT t_order_order_fk FOREIGN KEY ( order_id )
        REFERENCES "Order" ( id )
    NOT DEFERRABLE;

ALTER TABLE t_sale
    ADD CONSTRAINT t_sale_sale_fk FOREIGN KEY ( sale_id )
        REFERENCES sale ( id )
    NOT DEFERRABLE;

ALTER TABLE warehouse
    ADD CONSTRAINT warehouse_order_fk FOREIGN KEY ( order_id )
        REFERENCES "Order" ( id )
    NOT DEFERRABLE;

CREATE OR REPLACE TRIGGER Trg2 
    BEFORE INSERT ON Sale REFERENCING 
    NEW AS new 
IF (:new.count > (SELECT count FROM Warehouse WHERE Product_code = :new.Product_code))
INSERT INTO t_Sale (Order_id) VALUES (:new.id) ;
/

CREATE OR REPLACE TRIGGER Trg4 
    BEFORE INSERT ON "Order" 
    FOR EACH ROW 
INSERT INTO t_Order (Order_id) VALUES (:new.id) ;
/

CREATE OR REPLACE TRIGGER Trg5 
    BEFORE INSERT OR UPDATE ON Warehouse 
    FOR EACH ROW 
INSERT INTO t_journal (Warehouse_id, name, change_count, time) VALUES (:new.id,  USER, :new.count - nvl(:old.count, 0), CURRENT_DATE) ;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             3
-- ALTER TABLE                             13
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           3
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
