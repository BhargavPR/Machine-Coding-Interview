package service;

public interface ProducerService {

    void send(String topic, String data);

}
