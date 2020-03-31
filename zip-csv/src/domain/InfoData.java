package domain;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/2 11:39
 */
public class InfoData {
    String CITY_NAME;
    String COUNTY_NAME;
    String SITE_NAME;
    String SITE_NO;
    String METER_NUM;
    String MONTH_ID;
    String READING_START;
    String READING_END;
    String MONTHLY_POWER;
    String READING_START_TIME;
    String READING_END_TIME;
    String AVG_POWER;
    String CONTRACT_NO;
    String ELEC_PRICE;
    String ELEC_AMT;
    String AVG_AMT;
    String ATTACHMENT_ID;
    String ATTACHMENT_ID_RECEIPT;
    String ELEC_LOSE;
    String ELEC_TOTAL;
    String RECEIPT_ID;
    String SETTLE_POWER;
    String PROVINCE;
    String OWNER_CUST_CODE;

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public String getCOUNTY_NAME() {
        return COUNTY_NAME;
    }

    public void setCOUNTY_NAME(String COUNTY_NAME) {
        this.COUNTY_NAME = COUNTY_NAME;
    }

    public String getSITE_NAME() {
        return SITE_NAME;
    }

    public void setSITE_NAME(String SITE_NAME) {
        this.SITE_NAME = SITE_NAME;
    }

    public String getSITE_NO() {
        return SITE_NO;
    }

    public void setSITE_NO(String SITE_NO) {
        this.SITE_NO = SITE_NO;
    }

    public String getMETER_NUM() {
        return METER_NUM;
    }

    public void setMETER_NUM(String METER_NUM) {
        this.METER_NUM = METER_NUM;
    }

    public String getMONTH_ID() {
        return MONTH_ID;
    }

    public void setMONTH_ID(String MONTH_ID) {
        this.MONTH_ID = MONTH_ID;
    }

    public String getREADING_START() {
        return READING_START;
    }

    public void setREADING_START(String READING_START) {
        this.READING_START = READING_START;
    }

    public String getREADING_END() {
        return READING_END;
    }

    public void setREADING_END(String READING_END) {
        this.READING_END = READING_END;
    }

    public String getMONTHLY_POWER() {
        return MONTHLY_POWER;
    }

    public void setMONTHLY_POWER(String MONTHLY_POWER) {
        this.MONTHLY_POWER = MONTHLY_POWER;
    }

    public String getREADING_START_TIME() {
        return READING_START_TIME;
    }

    public void setREADING_START_TIME(String READING_START_TIME) {
        this.READING_START_TIME = READING_START_TIME;
    }

    public String getREADING_END_TIME() {
        return READING_END_TIME;
    }

    public void setREADING_END_TIME(String READING_END_TIME) {
        this.READING_END_TIME = READING_END_TIME;
    }

    public String getAVG_POWER() {
        return AVG_POWER;
    }

    public void setAVG_POWER(String AVG_POWER) {
        this.AVG_POWER = AVG_POWER;
    }

    public String getCONTRACT_NO() {
        return CONTRACT_NO;
    }

    public void setCONTRACT_NO(String CONTRACT_NO) {
        this.CONTRACT_NO = CONTRACT_NO;
    }

    public String getELEC_PRICE() {
        return ELEC_PRICE;
    }

    public void setELEC_PRICE(String ELEC_PRICE) {
        this.ELEC_PRICE = ELEC_PRICE;
    }

    public String getELEC_AMT() {
        return ELEC_AMT;
    }

    public void setELEC_AMT(String ELEC_AMT) {
        this.ELEC_AMT = ELEC_AMT;
    }

    public String getAVG_AMT() {
        return AVG_AMT;
    }

    public void setAVG_AMT(String AVG_AMT) {
        this.AVG_AMT = AVG_AMT;
    }

    public String getATTACHMENT_ID() {
        return ATTACHMENT_ID;
    }

    public void setATTACHMENT_ID(String ATTACHMENT_ID) {
        this.ATTACHMENT_ID = ATTACHMENT_ID;
    }

    public String getATTACHMENT_ID_RECEIPT() {
        return ATTACHMENT_ID_RECEIPT;
    }

    public void setATTACHMENT_ID_RECEIPT(String ATTACHMENT_ID_RECEIPT) {
        this.ATTACHMENT_ID_RECEIPT = ATTACHMENT_ID_RECEIPT;
    }

    public String getELEC_LOSE() {
        return ELEC_LOSE;
    }

    public void setELEC_LOSE(String ELEC_LOSE) {
        this.ELEC_LOSE = ELEC_LOSE;
    }

    public String getELEC_TOTAL() {
        return ELEC_TOTAL;
    }

    public void setELEC_TOTAL(String ELEC_TOTAL) {
        this.ELEC_TOTAL = ELEC_TOTAL;
    }

    public String getRECEIPT_ID() {
        return RECEIPT_ID;
    }

    public void setRECEIPT_ID(String RECEIPT_ID) {
        this.RECEIPT_ID = RECEIPT_ID;
    }

    public String getSETTLE_POWER() {
        return SETTLE_POWER;
    }

    public void setSETTLE_POWER(String SETTLE_POWER) {
        this.SETTLE_POWER = SETTLE_POWER;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    public String getOWNER_CUST_CODE() {
        return OWNER_CUST_CODE;
    }

    public void setOWNER_CUST_CODE(String OWNER_CUST_CODE) {
        this.OWNER_CUST_CODE = OWNER_CUST_CODE;
    }

    @Override
    public String toString() {
        return "InfoData{" +
                "CITY_NAME='" + CITY_NAME + '\'' +
                ", COUNTY_NAME='" + COUNTY_NAME + '\'' +
                ", SITE_NAME='" + SITE_NAME + '\'' +
                ", SITE_NO='" + SITE_NO + '\'' +
                ", METER_NUM='" + METER_NUM + '\'' +
                ", MONTH_ID='" + MONTH_ID + '\'' +
                ", READING_START='" + READING_START + '\'' +
                ", READING_END='" + READING_END + '\'' +
                ", MONTHLY_POWER='" + MONTHLY_POWER + '\'' +
                ", READING_START_TIME='" + READING_START_TIME + '\'' +
                ", READING_END_TIME='" + READING_END_TIME + '\'' +
                ", AVG_POWER='" + AVG_POWER + '\'' +
                ", CONTRACT_NO='" + CONTRACT_NO + '\'' +
                ", ELEC_PRICE='" + ELEC_PRICE + '\'' +
                ", ELEC_AMT='" + ELEC_AMT + '\'' +
                ", AVG_AMT='" + AVG_AMT + '\'' +
                ", ATTACHMENT_ID='" + ATTACHMENT_ID + '\'' +
                ", ATTACHMENT_ID_RECEIPT='" + ATTACHMENT_ID_RECEIPT + '\'' +
                ", ELEC_LOSE='" + ELEC_LOSE + '\'' +
                ", ELEC_TOTAL='" + ELEC_TOTAL + '\'' +
                ", RECEIPT_ID='" + RECEIPT_ID + '\'' +
                ", SETTLE_POWER='" + SETTLE_POWER + '\'' +
                ", PROVINCE='" + PROVINCE + '\'' +
                ", OWNER_CUST_CODE='" + OWNER_CUST_CODE + '\'' +
                '}';
    }
}
