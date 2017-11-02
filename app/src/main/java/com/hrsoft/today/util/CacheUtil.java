package com.hrsoft.today.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author YangCihang
 * @since 17/11/2.
 * email yangcihang@hrsoft.net
 */

public final class CacheUtil {

    private static final int MAX_SIZE = 1024 * 1024 * 50;
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static Map<String, CacheUtil> instanceMap = new HashMap<>();
    private CaCheManager mCaCheManager;

    /**
     * 构造函数
     */
    private CacheUtil(File cacheDir, long maxSize, int maxCount) {
        if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            throw new RuntimeException("can't make dirs in "
                    + cacheDir.getAbsolutePath());
        }
        mCaCheManager = new CaCheManager(maxSize, maxCount, cacheDir);
    }

    /**
     * 获取Cache工具
     */
    public static CacheUtil get(File cacheDir) {
        return get(cacheDir, MAX_SIZE, MAX_COUNT);
    }

    /**
     * 获取Cache工具
     */
    private static CacheUtil get(File cacheDir, long maxSize, int maxCount) {
        CacheUtil cacheUtil = instanceMap.get(cacheDir.getAbsolutePath() + getPid());
        if (cacheUtil == null) {
            cacheUtil = new CacheUtil(cacheDir, maxSize, maxCount);
        }
        return cacheUtil;
    }

    /**
     * 获取Pid
     *
     * @return pid
     */
    private static String getPid() {
        return "_" + android.os.Process.myPid();
    }

    /**
     * 存储String
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        File file = mCaCheManager.newFile(key);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file), 1024);
            bufferedWriter.write(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCaCheManager.addCache(file);
        }
    }

    /**
     * 获取String
     *
     * @param key key
     * @return 缓存值或者默认值
     */
    public String getString(String key) {
        File file = mCaCheManager.getCache(key);
        if (!file.exists()) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String readString = "";
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                readString += currentLine;
            }
            return readString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 存储int型数据
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        putString(key, String.valueOf(value));
    }

    /**
     * 获取int型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int defValue) {
        String strValue = getString(key);
        if (strValue != null) {
            try {
                return Integer.parseInt(strValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    /**
     * 存储long型数据
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        putString(key, String.valueOf(value));
    }

    /**
     * 获取long型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public long getLong(String key, long defValue) {
        String strValue = getString(key);
        if (strValue != null) {
            try {
                return Long.parseLong(strValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    /**
     * 存储float型数据
     *
     * @param key
     * @param value
     */
    public void putFloat(String key, float value) {
        putString(key, String.valueOf(value));
    }

    /**
     * 获取float型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public float getFloat(String key, float defValue) {
        String strValue = getString(key);
        if (strValue != null) {
            try {
                return Float.parseFloat(strValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    /**
     * 存储boolean型数据
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        putString(key, String.valueOf(value));
    }

    /**
     * 获取boolean型数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(String key, boolean defValue) {
        String strValue = getString(key);
        if (strValue != null) {
            try {
                return Boolean.parseBoolean(strValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return defValue;
    }

    /**
     * 存储byte[] 数据
     *
     * @param key
     * @param value
     */
    public void putBytes(String key, byte[] value) {
        File file = mCaCheManager.newFile(key);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCaCheManager.addCache(file);
        }
    }

    /**
     * 获取byte[]数据
     *
     * @param key
     * @return
     */
    public byte[] getBytes(String key) {
        RandomAccessFile randomAccessFile = null;
        try {
            File file = mCaCheManager.getCache(key);
            if (!file.exists()) {
                return null;
            }
            randomAccessFile = new RandomAccessFile(file, "r");
            byte[] byteArray = new byte[(int) randomAccessFile.length()];
            randomAccessFile.read(byteArray);
            return byteArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 存储JsonObject数据
     *
     * @param key
     * @param jsonObject
     */
    public void putJsonObject(String key, JSONObject jsonObject) {
        putString(key, jsonObject.toString());
    }

    /**
     * 获取JsonObject数据
     *
     * @param key
     * @return
     */
    public JSONObject getJsonObject(String key) {
        String jsonString = getString(key);
        try {
            return new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储JsonArray数据
     *
     * @param key
     * @param jsonArray
     */
    public void putJsonArray(String key, JSONArray jsonArray) {
        putString(key, jsonArray.toString());
    }

    /**
     * 获取JsonArray数据
     *
     * @param key
     * @return
     */
    public JSONArray getJsonArray(String key) {
        String JSONString = getString(key);
        try {
            return new JSONArray(JSONString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储Serializable数据
     *
     * @param key
     * @param value
     */
    public void putSerializableObj(String key, Serializable value) {
        ByteArrayOutputStream arrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            arrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(value);
            byte[] data = arrayOutputStream.toByteArray();
            putBytes(key, data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Serializable数据
     *
     * @param key
     * @return
     */
    public Object getSerializableObj(String key) {
        byte[] data = getBytes(key);
        if (data != null) {
            ByteArrayInputStream arrayInputStream = null;
            ObjectInputStream objectInputStream = null;
            try {
                arrayInputStream = new ByteArrayInputStream(data);
                objectInputStream = new ObjectInputStream(arrayInputStream);
                return objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (arrayInputStream != null) {
                        arrayInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 移除数据
     *
     * @param key
     * @return 是否移除成功
     */
    public boolean remove(String key) {
        return mCaCheManager.remove(key);
    }

    /**
     * 清除所有缓存
     */
    public void clear() {
        mCaCheManager.clear();
    }

    /**
     * Cache 管理类
     */
    public class CaCheManager {
        private final AtomicLong cacheSize;
        private final AtomicInteger cacheCount;
        private final long sizeLimit;
        private final int countLimit;
        private final Map<File, Long> lastUpdateDate =
                Collections.synchronizedMap(new HashMap<File, Long>());
        protected File cacheDir;

        /**
         * 构造函数
         *
         * @param sizeLimit  size限制
         * @param countLimit count限制
         * @param cacheDir   cache文件夹
         */
        public CaCheManager(long sizeLimit, int countLimit, File cacheDir) {
            this.sizeLimit = sizeLimit;
            this.countLimit = countLimit;
            this.cacheDir = cacheDir;
            cacheSize = new AtomicLong();
            cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        /**
         * 计算缓存size和count
         */
        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int size = 0;
                    int count = 0;
                    File[] cachedFiles = cacheDir.listFiles();
                    if (cachedFiles != null) {
                        for (File cacheFile : cachedFiles) {
                            size += calculateSize(cacheFile);
                            count += 1;
                            lastUpdateDate.put(cacheFile, cacheFile.lastModified());
                        }
                        cacheSize.set(size);
                        cacheCount.set(count);
                    }
                }
            }).start();
        }

        /**
         * 计算缓存Size
         *
         * @param cacheFile 缓存文件
         * @return size
         */
        private long calculateSize(File cacheFile) {
            return cacheFile.length();
        }

        /**
         * 创建新的缓存文件
         *
         * @param key key
         * @return 缓存文件
         */
        private File newFile(String key) {
            return new File(cacheDir, key.hashCode() + "");
        }


        /**
         * 添加新缓存时计算size和count
         *
         * @param file 新缓存
         */
        private void addCache(File file) {
            int curCacheCount = cacheCount.get();
            while (curCacheCount + 1 > countLimit) {
                long freeSize = removeNext();
                cacheSize.addAndGet(-freeSize);
                curCacheCount = cacheCount.addAndGet(-1);
            }
            cacheCount.addAndGet(1);

            long valueSize = calculateSize(file);
            long curCacheSize = cacheSize.get();
            while (curCacheSize + valueSize > sizeLimit) {
                long freeSize = removeNext();
                curCacheSize = cacheSize.addAndGet(-freeSize);
            }
            cacheSize.addAndGet(valueSize);

            long currentTime = System.currentTimeMillis();
            file.setLastModified(currentTime);
            lastUpdateDate.put(file, currentTime);
        }

        /**
         * 获取缓存时记录时间
         *
         * @param key
         * @return
         */
        private File getCache(String key) {
            File file = newFile(key);
            Long currentTime = System.currentTimeMillis();
            file.setLastModified(currentTime);
            lastUpdateDate.put(file, currentTime);
            return file;
        }

        /**
         * 移除旧文件
         *
         * @return 移除文件后剩余的size
         */
        private long removeNext() {
            if (lastUpdateDate.isEmpty()) {
                return 0;
            }

            Long oldestUsage = null;
            File mostLongUsedFile = null;
            Set<Map.Entry<File, Long>> entries = lastUpdateDate.entrySet();
            synchronized (lastUpdateDate) {
                for (Map.Entry<File, Long> entry : entries) {
                    if (mostLongUsedFile == null) {
                        mostLongUsedFile = entry.getKey();
                        oldestUsage = entry.getValue();
                    } else {
                        Long lastValueUsage = entry.getValue();
                        if (lastValueUsage < oldestUsage) {
                            oldestUsage = lastValueUsage;
                            mostLongUsedFile = entry.getKey();
                        }
                    }
                }
            }
            long fileSize = calculateSize(mostLongUsedFile);
            if (mostLongUsedFile.delete()) {
                lastUpdateDate.remove(mostLongUsedFile);
            }
            return fileSize;
        }

        /**
         * 删除某个文件
         *
         * @param key
         * @return
         */
        private boolean remove(String key) {
            File file = getCache(key);
            return file.delete();
        }

        /**
         * 删除所有文件
         */
        private void clear() {
            lastUpdateDate.clear();
            cacheSize.set(0);
            File[] files = cacheDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}
