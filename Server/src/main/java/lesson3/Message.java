package lesson3;

import java.util.Arrays;

public class Message {

    private String fileName;
    private int lengthFile;
    private byte[] data;

    public Message() {
    }

    public Message(String fileName, int lengthFile, byte[] data) {
        this.fileName = fileName;
        this.lengthFile = lengthFile;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLength() {
        return lengthFile;
    }

    public void setLength(int lengthFile) {
        this.lengthFile = lengthFile;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "File Name='" + fileName + '\'' +
                ", Length of File = " + lengthFile +
                " bytes, data=" + Arrays.toString(data) +
                '}';
    }
}
