package com.astro.guide.utils.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CacheManager {

    private String mPathCacheDir;

    @Inject
    public CacheManager(Context context) {
            mPathCacheDir = context.getCacheDir().toString() + "/json-cache/";
            validateDir();
            Log.i(Constants.Tag, "mPathCacheDir: "+ mPathCacheDir);
            Log.d(Constants.Tag, "[CacheManager]: Initializing new instance");
    }

    private void validateDir() {
        File dir = new File(mPathCacheDir);
        if (dir.exists()){
            return;
        }
        if (dir.mkdir()) {
            Log.d(Constants.Tag, "[CacheManager]: Create directory: " + mPathCacheDir);
        }else{
            Log.d(Constants.Tag, "[CacheManager]: Unable to create directory: " + mPathCacheDir);
        }
    }

    // =======================================
    // ========== String Read/Write ==========
    // =======================================

    /**
     * Writes a string to the given file name. The file will be placed in the current application's cache directory.
     *
     * @param toWrite  The String to write to a file.
     * @param fileName The File name that will be written to.
     * @throws CacheTransactionException Throws the exception if writing failed. Will not throw an exception in the result of a successful write.
     */
    public void write(String toWrite, String fileName) throws CacheTransactionException {
        validateDir();

        File file = new File(mPathCacheDir, fileName);

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file), 1024);
            out.write(toWrite);
            Log.d(Constants.Tag, "[CacheManager]: Writing to " + mPathCacheDir + fileName);
        } catch (IOException e) {
            Log.d(Constants.Tag, "[CacheManager]: Unsuccessful write to " + mPathCacheDir + fileName);
            e.printStackTrace();
            throw new CacheTransactionException(Constants.writeExceptionAlert);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads a string from an existing file in the cache directory and returns it.
     *
     * @param fileName The file name of an existing file in the cache directory to be read.
     * @return Returns whatever is read. Null if read fails.
     * @throws CacheTransactionException Throws the exception if reading failed. Will not throw an exception in the result of a successful read.
     */
    public String readString(String fileName) throws CacheTransactionException {
        String readString = "";
        File file = new File(mPathCacheDir, fileName);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                readString += currentLine;
            }
            Log.d(Constants.Tag, "[CacheManager]: Reading from " + mPathCacheDir + fileName);
            return readString;
        } catch (IOException e) {
            Log.d(Constants.Tag, "[CacheManager]: Unsuccessful read from " + mPathCacheDir + fileName);
            e.printStackTrace();
            throw new CacheTransactionException(Constants.readExceptionAlert);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // =======================================
    // ========== JSON Read/Write ============
    // =======================================

    /**
     * Writes a JSONObject to cache as a readable string to cache. If JSONObject stores sensitive data use writeEncrypted for the JSONObject.
     *
     * @param obj      The JSONObject to write.
     * @param fileName The File name that will be written to.
     * @throws CacheTransactionException Throws the exception if writing failed. Will not throw an exception in the result of a successful write.
     */
    public void write(JSONObject obj, String fileName) throws CacheTransactionException {
        write(obj.toString(), fileName);
    }

    /**
     * Reads a JSONObject from a string file. Initially runs readString(), so there may be logs saying there was a successful read, but the log will be followed up by another log stating that it was unable to create a JSONObject from the read string.
     *
     * @param fileName The file name that will be read from.
     * @return The JSONObject the file was storing, in the result of a successful read.
     * @throws CacheTransactionException Throws the exception if reading failed, or the creation of the JSONObject fails.
     */
    public JSONObject readJSONObject(String fileName) throws CacheTransactionException {
        String JSONString = readString(fileName); // Will throw exception here
        // if string read fails...
        try {
            JSONObject obj = new JSONObject(JSONString);
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(Constants.Tag, "[CacheManager]: Successfully read the file " + mPathCacheDir + fileName + ", but was unable to create a JSONObject from the String.");
            throw new CacheTransactionException(Constants.readExceptionAlert);
        }
    }

    // =======================================
    // ========= Bitmap Read/Write ===========
    // =======================================

    /**
     * Writes a Bitmap to the given file name. The file will be placed in the current application's cache directory.
     *
     * @param bitmap   The Bitmap to be written to cache.
     * @param format   The format that the Bitmap will be written to cache. (Either CompressFormat.PNG, CompressFormat.JPEG, or CompressFormat.WEBP)
     * @param quality  The quality that the Bitmap will be written at. 0 is the lowest quality, 100 is the highest quality. If you are writing as .PNG format, this parameter will not matter as PNG is lossless.
     * @param fileName The File name that will be written to.
     * @throws CacheTransactionException Throws the exception if writing failed. Will not throw an exception in the result of a successful write.
     */
    public void write(Bitmap bitmap, CompressFormat format, int quality, String fileName) throws CacheTransactionException {

        File file = new File(mPathCacheDir, fileName);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(format, quality, out);
        } catch (Exception e) {
            Log.d(Constants.Tag, "[CacheManager]: Unsuccessful write to " + mPathCacheDir + fileName);
            e.printStackTrace();
            throw new CacheTransactionException(Constants.writeExceptionAlert);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads a bitmap from the specified file and returns the bitmap.
     *
     * @param fileName The File name that will be read from.
     * @return Returns the bitmap in the case of a successful read.
     * @throws CacheTransactionException CacheTransactionException Throws the exception if reading failed. Will not throw an exception in the result of a successful read.
     */
    public Bitmap readBitmap(String fileName) throws CacheTransactionException {
        File file = new File(mPathCacheDir, fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        if (bitmap != null) {
            return bitmap;
        } else { // BitmapFactory.decodeFile returns null if it can't decode a
            // bitmap.
            throw new CacheTransactionException(Constants.readExceptionAlert);
        }
    }

    // =======================================
    // ========== Binary Read/Write ==========
    // =======================================

    /**
     * Writes an array of bytes to the given file name. The file will be placed in the current application's cache directory.
     *
     * @param toWrite  The byte array to write to a file.
     * @param fileName The File name that will be written to.
     * @throws CacheTransactionException Throws the exception if writing failed. Will not throw an exception in the result of a successful write.
     */
    public void write(byte[] toWrite, String fileName) throws CacheTransactionException {
        File file = new File(mPathCacheDir, fileName);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(toWrite);
        } catch (Exception e) {
            Log.d(Constants.Tag, "[CacheManager]: Unsuccessful write to " + mPathCacheDir + fileName);
            e.printStackTrace();
            throw new CacheTransactionException(Constants.writeExceptionAlert);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads an array of bytes from an existing file in the cache directory and returns it.
     *
     * @param fileName The file name of an existing file in the cache directory to be read.
     * @return The byte array that was read
     * @throws CacheTransactionException Throws the exception if reading failed. Will not throw an exception in the result of a successful read.
     */
    public byte[] readBinaryFile(String fileName) throws CacheTransactionException {
        RandomAccessFile RAFile = null;
        try {
            File file = new File(mPathCacheDir, fileName);
            RAFile = new RandomAccessFile(file, "r");
            byte[] byteArray = new byte[(int) RAFile.length()];
            RAFile.read(byteArray);
            return byteArray;
        } catch (Exception e) {
            Log.d(Constants.Tag, "[CacheManager]: Unsuccessful read from " + mPathCacheDir + fileName);
            e.printStackTrace();
            throw new CacheTransactionException(Constants.readExceptionAlert);
        } finally {
            if (RAFile != null) {
                try {
                    RAFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // ===========================================
    // ========== FileSystem Management ==========
    // ===========================================

    /**
     * Deletes a file in the cache directory.
     *
     * @param fileName The file to delete.
     */
    public boolean deleteFile(String fileName) {
        Log.d(Constants.Tag, "[CacheManager]: Deleting the file " + mPathCacheDir + fileName);
        File toDelete = new File(mPathCacheDir, fileName);
        return toDelete.delete();
    }

    /**
     * Deletes App Cache
     * @return isDeleted
     */
    public boolean deleteCacheDirContent() {
        Log.d(Constants.Tag, "[CacheManager]: Deleting " + mPathCacheDir);
        File dir = new File(mPathCacheDir);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                new File(dir, children[i]).delete();
            }
        }
        return dir.list().length == 0;
    }

    public boolean hasCacheContent() {
        Log.d(Constants.Tag, "[CacheManager]: Checking Content in " + mPathCacheDir);
        return !(new File(mPathCacheDir).list().length == 0);
    }
}
