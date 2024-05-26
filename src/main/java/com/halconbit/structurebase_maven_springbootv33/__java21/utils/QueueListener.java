package com.halconbit.structurebase_maven_springbootv33.__java21.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halconbit.structurebase_maven_springbootv33.__java21.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueueListener implements MessageListener {

    private final ObjectMapper mapper;
    //private final TransactionsBucketService service;
    //private final BucketService bucketService;

    /**
     * This is the Rabbit listener method.
     *
     * @param message: the message read from the queue.
     */
    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        //TransactionBucketInputDto dto = null;
        try {
            //mapper.findAndRegisterModules();
            //dto = mapper.readValue(message.getBody(), TransactionBucketInputDto.class);
        } catch (Exception e) {
            log.error("Error parsing the message: {}", e.getMessage());
        }
        /*if (dto != null) {
            //log.info("Message read from queue -> {} -> {}", dto.getTransactionId(), dto.getBucketId());
            //When a new message is valid, it calls the storage method
            var stored = doStore(dto);
            log.info("Message processed -> {}", stored);
        } else {
            log.warn("Missing fields, message not stored ");
        }*/
    }

    /*public String doStore(TransactionBucketInputDto message) throws CustomException {
        if (message.getTransaction().getTransactionState() == 6)
            storeFrozen(message);
        service.saveTransactionBucket(message);
        return "ok";
    }

    private void storeFrozen(TransactionBucketInputDto trx) {
        bucketService.frozenTransaction(trx);
    }*/
}