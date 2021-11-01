package com.vnpay.model;

public class MessageResponse {
    String messageId;
    String status;
    String description;
    String isMnp;

    public MessageResponse(String messageId, String status, String description, String isMnp) {
        this.messageId = messageId;
        this.status = status;
        this.description = description;
        this.isMnp = isMnp;
    }

    public MessageResponse() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsMnp() {
        return isMnp;
    }

    public void setIsMnp(String isMnp) {
        this.isMnp = isMnp;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "messageId='" + messageId + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", isMnp='" + isMnp + '\'' +
                '}';
    }
}
