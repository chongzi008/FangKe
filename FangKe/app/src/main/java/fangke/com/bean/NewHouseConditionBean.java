package fangke.com.bean;

import java.util.List;

/**
 * Created by ChongZi007 on 2017/6/3.
 * 用来保存新房导航搜索的条件的bean
 */

public class NewHouseConditionBean {

    private Area area;
    private String price;
    private String room;
    private More more;

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public More getMore() {
        return more;
    }

    public static class Area {

        private String qu;
        private String zhen;
        private String subwayline;
        private String subwayarea;

        public String getSubwayline() {
            return subwayline;
        }

        public void setSubwayline(String subwayline) {
            this.subwayline = subwayline;
        }

        public String getSubwayarea() {
            return subwayarea;
        }

        public void setSubwayarea(String subwayarea) {
            this.subwayarea = subwayarea;
        }

        public void setQu(String qu) {
            this.qu = qu;
        }

        public String getQu() {
            return qu;
        }

        public void setZhen(String zhen) {
            this.zhen = zhen;
        }

        public String getZhen() {
            return zhen;
        }

        @Override
        public String toString() {
            return "Area{" +
                    "qu='" + qu + '\'' +
                    ", zhen='" + zhen + '\'' +
                    ", subwayline='" + subwayline + '\'' +
                    ", subwayarea='" + subwayarea + '\'' +
                    '}';
        }
    }

    public static class More {

        private List<String> feature;
        private List<String> size;
        private List<String> work;
        private List<String> hottag;

        public void setFeature(List<String> feature) {
            this.feature = feature;
        }

        public List<String> getFeature() {
            return feature;
        }

        public void setSize(List<String> size) {
            this.size = size;
        }

        public List<String> getSize() {
            return size;
        }

        public void setWork(List<String> work) {
            this.work = work;
        }

        public List<String> getWork() {
            return work;
        }

        public void setHottag(List<String> hottag) {
            this.hottag = hottag;
        }

        public List<String> getHottag() {
            return hottag;
        }

        @Override
        public String toString() {
            return "More{" +
                    "feature=" + feature +
                    ", size=" + size +
                    ", work=" + work +
                    ", hottag=" + hottag +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewHouseConditionBean{" +
                "area=" + area +
                ", price='" + price + '\'' +
                ", room='" + room + '\'' +
                ", more=" + more +
                '}';
    }
}
