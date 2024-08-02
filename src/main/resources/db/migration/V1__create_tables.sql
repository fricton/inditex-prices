create table if not exists PRICES (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	BRAND_ID INTEGER NOT NULL,
	START_DATE TIMESTAMP NOT NULL,
	END_DATE TIMESTAMP NOT NULL,
	PRICE_LIST_ID INTEGER NOT NULL,
	PRODUCT_ID INTEGER NOT NULL,
	PRIORITY TINYINT NOT NULL DEFAULT 0,
	PRICE NUMERIC(20, 2) NOT NULL,
	CURR VARCHAR(3) NOT NULL,
	CONSTRAINT UK_UNIQUE_PRICE UNIQUE (BRAND_ID, PRODUCT_ID, PRICE_LIST_ID, CURR)
);

CREATE INDEX PRICE_SEARCH_IDX1 ON PRICES(BRAND_ID, PRODUCT_ID, START_DATE, END_DATE, CURR);

COMMENT ON TABLE PRICES IS 'Table storing the prices of products for different brands over specific time periods.';

COMMENT ON COLUMN PRICES.ID IS 'Auto-generated primary key.';
COMMENT ON COLUMN PRICES.BRAND_ID IS 'Foreign key identifying the brand (e.g., 1 = ZARA).';
COMMENT ON COLUMN PRICES.START_DATE IS 'The start date and time when the price is applicable.';
COMMENT ON COLUMN PRICES.END_DATE IS 'The end date and time when the price is no longer applicable.';
COMMENT ON COLUMN PRICES.PRICE_LIST_ID IS 'Identifier of the applicable price list.';
COMMENT ON COLUMN PRICES.PRODUCT_ID IS 'Identifier code of the product.';
COMMENT ON COLUMN PRICES.PRIORITY IS 'Priority of the price. If two prices have overlapping periods, the one with the higher priority (higher value) is applied.';
COMMENT ON COLUMN PRICES.PRICE IS 'The final sale price.';
COMMENT ON COLUMN PRICES.CURR IS 'ISO currency code of the price.';

