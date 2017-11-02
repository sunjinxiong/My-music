package gaodemap.bawei.com.mymusic.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.List;

import gaodemap.bawei.com.mymusic.bean.Song;

/**
 * 1. 音乐工具类
 * 2. @author chensi
 * 3. @date 2017/10/3 12:07
 */

public class MusicUtils {
    //搜索音乐列表 返回song_id 根据song_id搜索播放地址
    public static final String MUSIC_LIST="http://route.showapi.com/213-1?showapi_appid= 47494&showapi_sign=94c8f5950ba64183abfaa31f1c4c0df7&page=1&keyword=";


    //扫描本地音乐 返回list集合
    public static List<Song> getMusicList(Context context) {
        List<Song> slist = new ArrayList<>();
        // 媒体库查询语句
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Song s = new Song();
                s.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                s.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                s.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                s.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                s.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                s.album_id= getBitmap(Integer.valueOf(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))),context);
                s.isplay=false;
                if (s.size > 1000 * 800) {
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (s.song.contains("-")) {
                        String[] str = s.song.split("-");
                        s.singer = str[0];
                        s.song = str[1];
                    }
                    slist.add(s);
                }
            }
            // 释放资源
            cursor.close();
        }
        return slist;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }
    //根据album_id查找专辑图片
    public static String getAlbumArt(String album_id,Context context) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[] { "album_art" };
        Cursor cur = context.getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + album_id),
                projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        return album_art;
    }

    public static Bitmap getBitmap(int album_id, Context context) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[] { "album_art" };
        Cursor cur = context.getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
                projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        Bitmap bm = null;
        bm = BitmapFactory.decodeFile(album_art);
        return bm;
    }

}
