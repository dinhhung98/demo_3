package com.vnpay.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String messageId;
    private String keyword;
    private String sender;
    private String destination;
    private String shortMessage;
    private String encryptMessage = "";
    private int isEncrypt = 0;
    private int type = 0;
    private long requestTime;
    @Value("${message.partnerCode}")
    private String partnerCode = "950003";
    @Value("${message.sercretKey}")
    private String sercretKey = "5c6b322e-5d49-4ac3-9fab-1f2d9f3322d1";

    public Message(String messageId, String keyword, String sender, String destination, String shortMessage, String encryptMessage, int isEncrypt, int type, long requestTime, String partnerCode, String sercretKey) {
        this.messageId = messageId;
        this.keyword = keyword;
        this.sender = sender;
        this.destination = destination;
        this.shortMessage = shortMessage;
        this.encryptMessage = encryptMessage;
        this.isEncrypt = isEncrypt;
        this.type = type;
        this.requestTime = requestTime;
        this.partnerCode = partnerCode;
        this.sercretKey = sercretKey;
    }

    public Message() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getEncryptMessage() {
        return encryptMessage;
    }

    public void setEncryptMessage(String encryptMessage) {
        this.encryptMessage = encryptMessage;
    }

    public int getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(int isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getSercretKey() {
        return sercretKey;
    }

    public void setSercretKey(String sercretKey) {
        this.sercretKey = sercretKey;
    }
}
