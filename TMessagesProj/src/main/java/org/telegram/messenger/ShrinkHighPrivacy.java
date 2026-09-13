package org.telegram.messenger;

import android.content.Context;

import androidx.exifinterface.media.ExifInterface;

import java.io.File;
import java.io.IOException;

/**
 * Privacy helpers used by explicit, user-triggered flows.
 * These helpers do not claim to erase remote copies or defeat server policy.
 */
public final class ShrinkHighPrivacy {
    private ShrinkHighPrivacy() {
    }

    /** Removes common location/device metadata from a local JPEG file in place. */
    public static boolean stripExif(File imageFile) {
        if (imageFile == null || !imageFile.isFile() || !imageFile.canWrite()) {
            return false;
        }
        try {
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            String[] tags = {
                    ExifInterface.TAG_GPS_LATITUDE,
                    ExifInterface.TAG_GPS_LATITUDE_REF,
                    ExifInterface.TAG_GPS_LONGITUDE,
                    ExifInterface.TAG_GPS_LONGITUDE_REF,
                    ExifInterface.TAG_MAKE,
                    ExifInterface.TAG_MODEL,
                    ExifInterface.TAG_SERIAL_NUMBER,
                    ExifInterface.TAG_SOFTWARE,
                    ExifInterface.TAG_DATETIME,
                    ExifInterface.TAG_DATETIME_ORIGINAL,
                    ExifInterface.TAG_USER_COMMENT
            };
            for (String tag : tags) {
                exif.setAttribute(tag, null);
            }
            exif.saveAttributes();
            return true;
        } catch (IOException | RuntimeException e) {
            FileLog.e(e);
            return false;
        }
    }

    /** Deletes app-private cache files. User-visible media and remote data are untouched. */
    public static int wipePrivateCaches(Context context) {
        if (context == null) {
            return 0;
        }
        int[] count = {0};
        deleteChildren(context.getCacheDir(), count);
        deleteChildren(context.getExternalCacheDir(), count);
        return count[0];
    }

    private static void deleteChildren(File directory, int[] count) {
        if (directory == null || !directory.isDirectory()) {
            return;
        }
        File[] children = directory.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            deleteRecursively(child, count);
        }
    }

    private static void deleteRecursively(File file, int[] count) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child, count);
                }
            }
        }
        if (file.delete()) {
            count[0]++;
        }
    }
}
