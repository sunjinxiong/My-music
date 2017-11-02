package gaodemap.bawei.com.mymusic.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * dell 孙劲雄
 * 2017/10/10
 * 19:57
 */

public class Song implements Serializable{

    public String song;
    public String singer;
    public String path;
    public int duration;
    public long size;
    public Bitmap album_id;
    public boolean isplay;
    public String geci;

    public Song(String song, String singer, String path, int duration, long size, Bitmap album_id, boolean isplay, String geci) {
        this.song = song;
        this.singer = singer;
        this.path = path;
        this.duration = duration;
        this.size = size;
        this.album_id = album_id;
        this.isplay = isplay;
        this.geci = geci;
    }

    public Song() {
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Bitmap getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Bitmap album_id) {
        this.album_id = album_id;
    }

    public boolean isplay() {
        return isplay;
    }

    public void setIsplay(boolean isplay) {
        this.isplay = isplay;
    }

    public String getGeci() {
        return geci;
    }

    public void setGeci(String geci) {
        this.geci = geci;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song='" + song + '\'' +
                ", singer='" + singer + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", album_id=" + album_id +
                ", isplay=" + isplay +
                ", geci='" + geci + '\'' +
                '}';
    }
}
