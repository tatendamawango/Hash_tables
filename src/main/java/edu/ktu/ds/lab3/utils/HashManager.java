package edu.ktu.ds.lab3.utils;

public class HashManager {

    public enum HashType {
        DIVISION,
        MULTIPLICATION,
        JCF7, //Java Collections Framework 7
        JCF  //Java Collections Framework 8/11/14/15
    }

    public static int hash(int hashcode, int tableLength, HashType hashType) {
        switch (hashType) {
            case DIVISION:
                return Math.abs(hashcode) % tableLength;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(hashcode)) % 1) * tableLength);
            case JCF7:
                hashcode ^= (hashcode >>> 20) ^ (hashcode >>> 12);
                hashcode = hashcode ^ (hashcode >>> 7) ^ (hashcode >>> 4);
                return hashcode & (tableLength - 1);
            case JCF:
                hashcode = hashcode ^ (hashcode >>> 16);
                return hashcode & (tableLength - 1);
            default:
                throw new IllegalArgumentException("HashType is unknown");
        }
    }
}
