package com.vnpay.util;

import com.vnpay.model.Message;
import com.vnpay.model.MessageResponse;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

public class HandleSMS implements Callable<List<MessageResponse> > {
    @Override
    public List<MessageResponse> call() throws Exception {
        List<MessageResponse> responses = new ArrayList<>();
        List<Message> messages = ReadExcel.readData();
        for (Message message: messages) {
            String idMessage = UUID.randomUUID().toString();
            message.setMessageId(idMessage);
            MessageResponse response = SendDataToServer.sendToServer(message,"https://smsgw.vnpaytest.vn/smsgw/sendSms");
            responses.add(response);
        }
        Files.move(Paths.get("Processing/demo.xlsx"),Paths.get("Archive/demo.xlsx"), StandardCopyOption.REPLACE_EXISTING);
        return responses;
    }
}
