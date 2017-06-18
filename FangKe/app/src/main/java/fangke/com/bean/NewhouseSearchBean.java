package fangke.com.bean;

/**
 * Created by ChongZi007 on 2017/6/6.
 * 接受新房页面在popwindow选了条件之后
 */

public class NewhouseSearchBean {
    private Agent agent;
    private String haddress;
    private int harea;
    private int hid;
    private String himage;
    private String hintroduction;
    private String hname;
    private int hprice;
    private long hsaletime;
    private String hshape;
    private int htype;
    private String subt1;
    private String subt2;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public int getHarea() {
        return harea;
    }

    public void setHarea(int harea) {
        this.harea = harea;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHimage() {
        return himage;
    }

    public void setHimage(String himage) {
        this.himage = himage;
    }

    public String getHintroduction() {
        return hintroduction;
    }

    public void setHintroduction(String hintroduction) {
        this.hintroduction = hintroduction;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public int getHprice() {
        return hprice;
    }

    public void setHprice(int hprice) {
        this.hprice = hprice;
    }

    public long getHsaletime() {
        return hsaletime;
    }

    public void setHsaletime(long hsaletime) {
        this.hsaletime = hsaletime;
    }

    public String getHshape() {
        return hshape;
    }

    public void setHshape(String hshape) {
        this.hshape = hshape;
    }

    public int getHtype() {
        return htype;
    }

    public void setHtype(int htype) {
        this.htype = htype;
    }

    public String getSubt1() {
        return subt1;
    }

    public void setSubt1(String subt1) {
        this.subt1 = subt1;
    }

    public String getSubt2() {
        return subt2;
    }

    public void setSubt2(String subt2) {
        this.subt2 = subt2;
    }

    public static class Agent {
        /**
         * aid:经纪人id
         * aimage: 经纪人照片
         * aname：姓名
         * acompany:经纪人从事公司
         * atelephone:手机号码
         * awechat:微信号码
         * aservice_time: 服务年限
         * aservice_number:服务人数
         * aservice_address:服务地区（主营范围）
         */
        private Long aid;
        private String aimage;
        private String aname;
        private String acompany;
        private String atelephone;
        private String awechat;
        private Integer aservice_time;
        private Integer aservice_number;
        private String aservice_address;
        private String asex;

        public String getAsex() {
            return asex;
        }

        public void setAsex(String asex) {
            this.asex = asex;
        }

        public Long getAid() {
            return aid;
        }

        public void setAid(Long aid) {
            this.aid = aid;
        }

        public String getAimage() {
            return aimage;
        }

        public void setAimage(String aimage) {
            this.aimage = aimage;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }

        public String getAcompany() {
            return acompany;
        }

        public void setAcompany(String acompany) {
            this.acompany = acompany;
        }

        public String getAtelephone() {
            return atelephone;
        }

        public void setAtelephone(String atelephone) {
            this.atelephone = atelephone;
        }

        public String getAwechat() {
            return awechat;
        }

        public void setAwechat(String awechat) {
            this.awechat = awechat;
        }

        public Integer getAservice_time() {
            return aservice_time;
        }

        public void setAservice_time(Integer aservice_time) {
            this.aservice_time = aservice_time;
        }

        public Integer getAservice_number() {
            return aservice_number;
        }

        public void setAservice_number(Integer aservice_number) {
            this.aservice_number = aservice_number;
        }

        public String getAservice_address() {
            return aservice_address;
        }

        public void setAservice_address(String aservice_address) {
            this.aservice_address = aservice_address;
        }

    }


}
