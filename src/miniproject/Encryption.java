package miniproject;

public interface Encryption {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] data);
}
