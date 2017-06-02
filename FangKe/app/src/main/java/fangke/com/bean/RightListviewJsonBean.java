package fangke.com.bean;

import java.util.ArrayList;

/**
 * Created by ChongZi007 on 2017/5/30.
 */

public class RightListviewJsonBean {

    private ArrayList<Areas> region;
    private ArrayList<Areas> subway;

    public ArrayList<Areas> getRegion() {
        return region;
    }

    public void setRegion(ArrayList<Areas> region) {
        this.region = region;
    }

    public ArrayList<Areas> getSubway() {
        return subway;
    }

    public void setSubway(ArrayList<Areas> subway) {
        this.subway = subway;
    }

    public class Areas {
        private String name;
        private ArrayList<String> areas;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getAreas() {
            return areas;
        }

        public void setAreas(ArrayList<String> areas) {
            this.areas = areas;
        }

    }


}
